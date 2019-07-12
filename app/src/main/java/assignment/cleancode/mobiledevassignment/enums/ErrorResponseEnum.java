package assignment.cleancode.mobiledevassignment.enums;


import assignment.cleancode.mobiledevassignment.R;

public enum ErrorResponseEnum {

    NO_INTERNET_CONNECTION(
            R.drawable.ic_error_large_internet,
            R.string.NO_INTERNET_CONNECTIVITY_HEAD,
            R.string.NO_INTERNET_CONNECTIVITY,
            R.string.STR_RETRY
    ),

    API_REQUEST_FAILURE(
            R.drawable.ic_error_large_server,
            R.string.STR_SERVER_ERROR_TITLE,
            R.string.STR_SERVER_NOT_REACHABLE_ERROR,
            R.string.STR_RETRY
    ),

    NO_DATA_RECEIVED(
            R.drawable.ic_error_large_nodata,
            R.string.STR_NO_RESULTS,
            R.string.STR_NO_RESULT,
            R.string.STR_RETRY
    );

    private int resourceId;
    private int messageTitle;
    private int messageDetails;
    private int actionMessage;

    ErrorResponseEnum(int resourceId,
                      int messageTitle,
                      int messageDetails,
                      int actionMessage) {

        this.resourceId = resourceId;
        this.messageTitle = messageTitle;
        this.messageDetails = messageDetails;
        this.actionMessage = actionMessage;
    }


    public int getResourceId() {
        return resourceId;
    }

    public int getMessageTitle() {
        return messageTitle;
    }

    public int getMessageDetails() {
        return messageDetails;
    }

    public int getActionMessage() {
        return actionMessage;
    }
}
