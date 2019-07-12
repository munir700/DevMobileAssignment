package assignment.cleancode.mobiledevassignment.utils;


import assignment.cleancode.mobiledevassignment.enums.ErrorResponseEnum;

public class ErrorResponse {

    private int resourceId;
    private int messageTitle;
    private int messageDetails;
    private int actionMessage;

    private ErrorResponse() {

    }

    public static class Builder {

        private int resourceId;
        private int messageTitle;
        private int messageDetails;
        private int actionMessage;

        public Builder(ErrorResponseEnum type) {
            resourceId = type.getResourceId();
            messageTitle = type.getMessageTitle();
            messageDetails = type.getMessageDetails();
            actionMessage = type.getActionMessage();

        }

        public Builder resourceId(int resourceId) {
            this.resourceId = resourceId;
            return this;
        }

        public Builder title(int messageTitle) {
            this.messageTitle = messageTitle;
            return this;
        }

        public Builder details(int messageDetails) {
            this.messageDetails = messageDetails;
            return this;
        }

        public Builder actionMessage(int actionMessage) {
            this.actionMessage = actionMessage;
            return this;
        }

        public ErrorResponse build() {
            return new ErrorResponse(resourceId, messageTitle, messageDetails, actionMessage);
        }

    }

    private ErrorResponse(int resourceId, int messageTitle, int messageDetails, int actionMessage) {
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