package assignment.cleancode.mobiledevassignment.enums;


public enum ViewModelEventsEnum {

    NO_INTERNET_CONNECTION(0), ON_API_REQUEST_FAILURE(2), ON_API_CALL_START(3), ON_API_CALL_STOP(4), ON_DATA_RECEIVED(5), ON_NO_DATA_RECEIVED(6), ON_FIRST_PAGE_LOAD(7), ON_INVALID_AUTH_KEY(8), ON_API_REQUEST_CANCEL(10), ON_DATA_ALREADY_EXIST(11);

    ViewModelEventsEnum(int value) {

    }
}
