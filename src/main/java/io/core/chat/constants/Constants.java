package io.core.chat.constants;

public interface Constants {

    // Repository Related Constants
    public static final Integer NonDeleted = 0;
    public static final Integer ENABLED = 0;
    public static final Integer DISABLED = 0;
    public static final Integer VERIFIED = 0;
    public static final Integer NOT_VERIFIED = 0;
    public static final Integer Deleted = 1;
    public static final Integer NotAccepted = 0;
    public static final Integer Accepted = 1;

    // Queue Related Constants
    public static final String SUCCESS = "SUCCESS";
    public static final String ERROR = "ERROR";
    public static final String IN_PROGRESS = "INPROGRESS";
    public static final String IN_QUEUE = "REQUEST_IN_QUEUE";
    public static final String FAILED = "FAILED";
    public static final String MESSAGE_QUEUE_SEND_ERROR = "MESSAGE_QUEUE_SEND_ERROR";
    public static final String MESSAGE_QUEUE_RECEIVE_ERROR = "MESSAGE_QUEUE_RECEIVE_ERROR";
    public static final int MESSAGE_RETRY_DELAY=5000;

}
