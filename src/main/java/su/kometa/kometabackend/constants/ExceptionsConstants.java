package su.kometa.kometabackend.constants;

public class ExceptionsConstants {

    private static final int SERVER_ERROR = 100;

    private static final int API_ERROR = 200;

    private static final int USER_ERROR = 300;

    private static final int MODEL_ERROR = 400;

    private static final int CHAT_ERROR = 500;

    private static final int MESSAGE_ERROR = 600;

    public enum Messages {
        USER_NOT_FOUND("User not found"),
        NEED_TO_AUTHORIZE("You need to authorize first"),
        MODEL_NOT_FOUND("Model not found"),
        CHAT_NOT_FOUND("Chat not found"),
        MESSAGE_NOT_FOUND("Message not found"),
        SERVER_EXCEPTION("Server exception ({}, {}, {}) occurred"),
        INTERNAL_ERROR("An internal server error has occurred"),
        ROUTE_NOT_FOUND("Route not found"),
        REQUEST_BODY_EMPTY("Request body cannot be empty"),
        SERVER_EXCEPTION_STACKTRACE("Server exception stacktrace:"), 
        WRONG_MODEL_RESPONSE("Model API returned wrong response");

        private final String message;

        Messages(String message) {
            this.message = message;
        }

        public String getValue() {
            return message;
        }
    }

    public enum User {
        NOT_FOUND,
        NEED_TO_AUTHORIZE;

        public int getValue() {
            return USER_ERROR + this.ordinal();
        }
    }

    public enum Model {
        NOT_FOUND,
        WRONG_RESPONSE;

        public int getValue() {
            return MODEL_ERROR + this.ordinal();
        }
    }

    public enum Chat {
        NOT_FOUND;

        public int getValue() {
            return CHAT_ERROR + this.ordinal();
        }
    }

    public enum Message {
        NOT_FOUND;

        public int getValue() {
            return MESSAGE_ERROR + this.ordinal();
        }
    }

    public enum API {
        EMPTY_BODY,
        VALIDATION_ERROR,
        ROUTE_NOT_FOUND;

        public int getValue() {
            return API_ERROR + this.ordinal();
        }
    }

    public enum Server {
        UNKNOWN;

        public int getValue() {
            return API_ERROR + this.ordinal();
        }
    }
}
