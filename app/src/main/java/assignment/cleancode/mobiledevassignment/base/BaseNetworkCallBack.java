package assignment.cleancode.mobiledevassignment.base;

import android.accounts.NetworkErrorException;

import com.bumptech.glide.load.HttpException;

import org.apache.http.conn.ConnectTimeoutException;

import java.net.SocketException;
import java.net.SocketTimeoutException;
import java.net.UnknownHostException;

import javax.net.ssl.SSLException;

import assignment.cleancode.mobiledevassignment.R;
import assignment.cleancode.mobiledevassignment.enums.ViewModelEventsEnum;
import assignment.cleancode.mobiledevassignment.utils.ApiUtils;
import okhttp3.internal.http2.ErrorCode;
import okhttp3.internal.http2.StreamResetException;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class BaseNetworkCallBack<T> implements Callback<T> {
    private final BaseViewModel viewModel;

    public BaseNetworkCallBack(BaseViewModel viewModel) {
        this.viewModel = viewModel;
        init();
    }

    private void init() {
        notifyObserver(ViewModelEventsEnum.ON_API_CALL_START, null);
    }

    public void notifyObserver(ViewModelEventsEnum viewModelEvent, Object message) {
        viewModel.notifyObserver(viewModelEvent, message);

    }

    @Override
    public void onResponse(Call<T> call, Response<T> response) {
        notifyObserver(ViewModelEventsEnum.ON_API_CALL_STOP, null);
    }

    @Override
    public void onFailure(Call<T> call, Throwable throwable) {
        if (throwable.getMessage() == null) {
            notifyObserver(ViewModelEventsEnum.ON_API_REQUEST_FAILURE, viewModel.getAppManager().getResourceString(R.string.STR_SERVER_NOT_REACHABLE_ERROR));
            notifyObserver(ViewModelEventsEnum.ON_API_CALL_STOP, viewModel.getAppManager().getResourceString(R.string.STR_SERVER_NOT_REACHABLE_ERROR));
            return;
        }
        boolean isHandled = false;
        if (throwable instanceof HttpException) {
            HttpException httpException = (HttpException) throwable;
            if (httpException.getStatusCode() == 404) {
                isHandled = true;
                notifyObserver(ViewModelEventsEnum.ON_INVALID_AUTH_KEY, throwable);
            }
        }
        if (!isHandled) {
            if (throwable.getMessage().contains(ApiUtils.INVALID_AUTH_TOKEN)) {
                notifyObserver(ViewModelEventsEnum.ON_INVALID_AUTH_KEY, throwable.getMessage());
            } else if (throwable.getMessage().contains(ApiUtils.MALFORMED_JSON)) {
                notifyObserver(ViewModelEventsEnum.ON_API_REQUEST_FAILURE, viewModel.getAppManager().getResourceString(R.string.STR_SERVER_NOT_REACHABLE_ERROR));
            } else if (throwable.getMessage().contains(ApiUtils.CANCELED) || throwable.getMessage().contains(ApiUtils.SOCKET)) {
            } else if (throwable instanceof SocketException) {
                notifyObserver(ViewModelEventsEnum.ON_API_REQUEST_FAILURE, throwable.getMessage());
            } else if (throwable instanceof SocketTimeoutException) {
                notifyObserver(ViewModelEventsEnum.ON_API_REQUEST_FAILURE, viewModel.getAppManager().getResourceString(R.string.STR_SERVER_TIME_OUT_ERROR));
            } else if (throwable instanceof ConnectTimeoutException) {
                notifyObserver(ViewModelEventsEnum.ON_API_REQUEST_FAILURE, viewModel.getAppManager().getResourceString(R.string.STR_SERVER_TIME_OUT_ERROR));
            } else if (throwable instanceof UnknownHostException) {
                notifyObserver(ViewModelEventsEnum.ON_API_REQUEST_FAILURE, viewModel.getAppManager().getResourceString(R.string.STR_SERVER_NOT_REACHABLE_ERROR));
            } else if (throwable instanceof NetworkErrorException) {
                notifyObserver(ViewModelEventsEnum.ON_API_REQUEST_FAILURE, viewModel.getAppManager().getResourceString(R.string.STR_SERVER_TIME_OUT_ERROR));
            } else if (throwable instanceof SSLException) {
                notifyObserver(ViewModelEventsEnum.ON_API_REQUEST_FAILURE, viewModel.getAppManager().getResourceString(R.string.STR_SERVER_NOT_REACHABLE_ERROR));
            } else if (throwable instanceof StreamResetException && ((StreamResetException) throwable).errorCode == ErrorCode.CANCEL) {
                notifyObserver(ViewModelEventsEnum.ON_API_REQUEST_FAILURE, viewModel.getAppManager().getResourceString(R.string.STR_SERVER_NOT_REACHABLE_ERROR));
            } else {
                notifyObserver(ViewModelEventsEnum.ON_API_REQUEST_FAILURE, throwable.getMessage());
            }
        }
        notifyObserver(ViewModelEventsEnum.ON_API_CALL_STOP, viewModel.getAppManager().getResourceString(R.string.STR_SERVER_NOT_REACHABLE_ERROR));

    }
}
