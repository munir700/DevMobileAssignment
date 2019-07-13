package assignment.cleancode.mobiledevassignment.views;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.webkit.WebChromeClient;
import android.webkit.WebView;


import assignment.cleancode.mobiledevassignment.R;
import assignment.cleancode.mobiledevassignment.base.BaseActivity;
import assignment.cleancode.mobiledevassignment.base.BaseViewModel;
import assignment.cleancode.mobiledevassignment.databinding.ActivityWebViewBinding;
import assignment.cleancode.mobiledevassignment.utils.Utils;
import im.delight.android.webview.AdvancedWebView;

/**
 * Created by Munir Ahmad
 */

public class AdvWebActivity extends BaseActivity<BaseViewModel, ActivityWebViewBinding> implements AdvancedWebView.Listener {
    public static final int WEB_VIEW = 212;
    public static final String WEB_URL = "webUrl";
    public static final String WEB_VIEW_TITLE = "title";
    public static final String WEB_USER_AGENT = "userAgentPostFix";

    private String url;
    private String headerTitle;
    private String userAgentPostFix;


    @Override
    public Class<BaseViewModel> getViewModel() {
        return BaseViewModel.class;
    }

    @Override
    public int getLayoutRes() {
        return R.layout.activity_web_view;
    }

    public static void open(Activity homeActivity, String title, String webUrl, String userAgent) {
        Intent openAdvWebViewActivity = new Intent(homeActivity, AdvWebActivity.class);
        openAdvWebViewActivity.putExtra(WEB_VIEW_TITLE, title);
        openAdvWebViewActivity.putExtra(WEB_URL, webUrl);
        openAdvWebViewActivity.putExtra(WEB_USER_AGENT, userAgent);
        homeActivity.startActivityForResult(openAdvWebViewActivity, WEB_VIEW);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding.webview.setListener(this, this);
        binding.loader.setVisibility(View.VISIBLE);
        Bundle intent = getIntent().getExtras();

        try {
            headerTitle = intent.containsKey(WEB_VIEW_TITLE) ? intent.getString(WEB_VIEW_TITLE) : "";
            url = intent.containsKey(WEB_URL) ? intent.getString(WEB_URL) : "";
            userAgentPostFix = intent.containsKey(WEB_USER_AGENT) ? intent.getString(WEB_USER_AGENT) : "";
        } catch (Exception e) {
            Log.e("WebActivity", "Error getting extra: " + e);
        }


        binding.webview.addPermittedHostname(url);
        binding.webview.loadUrl(url);
        binding.webview.setWebChromeClient(new WebChromeClient() {
            @Override
            public void onProgressChanged(WebView view, int newProgress) {
                super.onProgressChanged(view, newProgress);
                Log.e("View progress", newProgress + "");
                binding.loader.setProgress(newProgress);
            }
        });

        setSupportActionBar(binding.layoutToolbar.toolbar);
        if(getSupportActionBar() != null){
            getSupportActionBar().setDisplayHomeAsUpEnabled(false);
            getSupportActionBar().setDisplayShowHomeEnabled(false);
            binding.layoutToolbar.setTitle(headerTitle);
        }

        ammendHeader();

    }

    private void ammendHeader() {

        String userAgent = binding.webview.getSettings().getUserAgentString();

        userAgent = userAgent.replaceAll("Chrome/(\\d+\\.?)+", "Chrome/" + Utils.getAppVersionName(this));

        userAgent += userAgentPostFix; //" bayut-mobile-app";

        binding.webview.getSettings().setUserAgentString(userAgent);
    }

    @SuppressLint("NewApi")
    @Override
    protected void onResume() {
        super.onResume();
        binding.webview.onResume();
        // ...
    }

    @SuppressLint("NewApi")
    @Override
    protected void onPause() {
        binding.webview.onPause();
        // ...
        super.onPause();
    }

    @Override
    protected void onDestroy() {
        binding.webview.onDestroy();
        // ...
        super.onDestroy();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent intent) {
        super.onActivityResult(requestCode, resultCode, intent);
        binding.webview.onActivityResult(requestCode, resultCode, intent);
        // ...
    }

    @Override
    public void onBackPressed() {
        if (!binding.webview.onBackPressed()) {
            return;
        }
        // ...
        super.onBackPressed();
    }

    @Override
    public void onPageStarted(String url, Bitmap favicon) {
        binding.loader.setVisibility(View.VISIBLE);
    }

    @Override
    public void onPageFinished(String url) {
        binding.loader.setVisibility(View.GONE);
    }

    @Override
    public void onPageError(int errorCode, String description, String failingUrl) {
        binding.loader.setVisibility(View.GONE);
    }

    @Override
    public void onDownloadRequested(String url, String suggestedFilename, String mimeType, long contentLength, String contentDisposition, String userAgent) {
    }

    @Override
    public void onExternalPageRequest(String url) {

        if (url.contains(getString(R.string.STR_BLOG_OPEN_IN_APP_PHRASE))) {
            binding.webview.loadUrl(url);
        } else {
            Intent browserIntent = new Intent(Intent.ACTION_VIEW,
                    Uri.parse(url));
            startActivity(browserIntent);
        }
    }

    @Override
    public boolean onSupportNavigateUp() {
        super.onBackPressed();
        return super.onSupportNavigateUp();
    }

    public void onBackButtonPressed(View view) {
        onSupportNavigateUp();
    }
}
