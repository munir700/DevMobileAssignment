package assignment.cleancode.mobiledevassignment.ui;

import android.app.Dialog;
import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.Window;
import android.view.animation.AlphaAnimation;

import androidx.databinding.DataBindingUtil;

import assignment.cleancode.mobiledevassignment.R;
import assignment.cleancode.mobiledevassignment.databinding.ActionDialogBinding;
import assignment.cleancode.mobiledevassignment.interfaces.ActionDialogCallBack;


public class ActionDialog extends Dialog implements View.OnClickListener {

    private final boolean singleAction;
    private ActionDialogCallBack dialogCallBack;
    private String message;
    private Context context;
    private ActionDialogBinding binding;


    public ActionDialog(Context context, String message, ActionDialogCallBack dialogCallBack, boolean singleAction) {
        super(context);
        this.singleAction = singleAction;
        this.context = context;
        this.dialogCallBack = dialogCallBack;
        this.message = message;
        requestWindowFeature(Window.FEATURE_NO_TITLE);

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.inflate(LayoutInflater.from(getContext()), R.layout.action_dialog, null, false);
        this.setContentView(binding.getRoot());
        binding.tvMessage.setText(message);

        binding.btnOk.setVisibility(singleAction ? View.VISIBLE : View.GONE);
        binding.groupYesNoBtns.setVisibility(singleAction ? View.GONE : View.VISIBLE);

        if (singleAction) {
            binding.btnOk.setOnClickListener(this);
        } else {
            binding.btnYes.setOnClickListener(this);
            binding.btnNo.setOnClickListener(this);
        }

        if (getWindow() != null) {
            getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        }
    }

    @Override
    public void onClick(View v) {
        if (dialogCallBack == null) {
            dismiss();
            return;
        }
        final AlphaAnimation alphaAnimator = new AlphaAnimation(1F, 0.8F);
        switch (v.getId()) {
            case R.id.btn_yes:
                binding.btnYes.startAnimation(alphaAnimator);
                dialogCallBack.onDialogPositiveButton();
                dismiss();
                break;
            case R.id.btn_no:
                binding.btnNo.startAnimation(alphaAnimator);
                dialogCallBack.onDialogNegativeButton();
                dismiss();
                break;
            case R.id.btn_ok:
                dialogCallBack.onDialogNeutralButton();
                dismiss();
                break;
            default:
                break;
        }
        dismiss();
    }


}