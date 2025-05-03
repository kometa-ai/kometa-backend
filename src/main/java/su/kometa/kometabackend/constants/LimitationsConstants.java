package su.kometa.kometabackend.constants;

import su.kometa.kometabackend.configs.CommonConfig;

public class LimitationsConstants {

    public static final int USERNAME_MAX = 32;

    public static final int USERNAME_MIN = 3;

    public static final int PASSWORD_MAX = 256;

    public static final int PASSWORD_MIN = 4;

    public static final int TITLE_MAX = 32;

    public static final int TITLE_MIN = 1;

    public static final int MESSAGE_CONTENT_MAX = 4096;

    public static final int MESSAGE_CONTENT_MIN = 1;

    public static final int MESSAGE_ATTACHMENTS_MAX = 10;

    public static final int MESSAGE_ATTACHMENTS_MIN = 0;

    public static final int MODEL_API_KEY_MAX = 256;

    public static final int MODEL_API_KEY_MIN = 8;

    public static final int MODEL_TOKENS_MAX = 2048;

    public static final int MODEL_TOKENS_MIN = 8;
}