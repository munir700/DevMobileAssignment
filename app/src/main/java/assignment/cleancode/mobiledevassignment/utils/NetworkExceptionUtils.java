package assignment.cleancode.mobiledevassignment.utils;

import android.accounts.NetworkErrorException;

import org.apache.http.conn.ConnectTimeoutException;

import java.net.SocketException;
import java.net.SocketTimeoutException;
import java.net.UnknownHostException;

import javax.net.ssl.SSLException;

import assignment.cleancode.mobiledevassignment.R;
import assignment.cleancode.mobiledevassignment.base.BaseViewModel;
import assignment.cleancode.mobiledevassignment.enums.ViewModelEventsEnum;
import okhttp3.internal.http2.ErrorCode;
import okhttp3.internal.http2.StreamResetException;

public class NetworkExceptionUtils {
    public static ExceptionViewModel getNetworkException(BaseViewModel viewModel, Throwable throwable) {

        ExceptionViewModel exceptionViewModel = null;
        if (throwable instanceof SocketException) {
            exceptionViewModel = new ExceptionViewModel(ViewModelEventsEnum.ON_API_REQUEST_FAILURE, throwable.getMessage());
        } else if (throwable instanceof SocketTimeoutException) {
            exceptionViewModel = new ExceptionViewModel(ViewModelEventsEnum.ON_API_REQUEST_FAILURE, viewModel.getAppManager().getResourceString(R.string.STR_SERVER_TIME_OUT_ERROR));
        } else if (throwable instanceof ConnectTimeoutException) {
            exceptionViewModel = new ExceptionViewModel(ViewModelEventsEnum.ON_API_REQUEST_FAILURE, viewModel.getAppManager().getResourceString(R.string.STR_SERVER_TIME_OUT_ERROR));
        } else if (throwable instanceof UnknownHostException) {
            exceptionViewModel = new ExceptionViewModel(ViewModelEventsEnum.ON_API_REQUEST_FAILURE, viewModel.getAppManager().getResourceString(R.string.STR_SERVER_NOT_REACHABLE_ERROR));
        } else if (throwable instanceof NetworkErrorException) {
            exceptionViewModel = new ExceptionViewModel(ViewModelEventsEnum.ON_API_REQUEST_FAILURE, viewModel.getAppManager().getResourceString(R.string.STR_SERVER_TIME_OUT_ERROR));
        } else if (throwable instanceof SSLException) {
            exceptionViewModel = new ExceptionViewModel(ViewModelEventsEnum.ON_API_REQUEST_FAILURE, viewModel.getAppManager().getResourceString(R.string.STR_SERVER_NOT_REACHABLE_ERROR));
        } else if (throwable instanceof StreamResetException && ((StreamResetException) throwable).errorCode == ErrorCode.CANCEL) {
            exceptionViewModel = new ExceptionViewModel(ViewModelEventsEnum.ON_API_REQUEST_FAILURE, viewModel.getAppManager().getResourceString(R.string.STR_SERVER_NOT_REACHABLE_ERROR));
        } else {
            exceptionViewModel = new ExceptionViewModel(ViewModelEventsEnum.ON_API_REQUEST_FAILURE, throwable.getMessage());
        }
        return exceptionViewModel;

    }

    public static class ExceptionViewModel {
        ViewModelEventsEnum viewModelEventsEnum;
        Object message;

        public ExceptionViewModel(ViewModelEventsEnum viewModelEventsEnum, Object message) {
            this.viewModelEventsEnum = viewModelEventsEnum;
            this.message = message;
        }

        public ViewModelEventsEnum getViewModelEventsEnum() {
            return viewModelEventsEnum;
        }

        public Object getMessage() {
            return message;
        }
    }

}
