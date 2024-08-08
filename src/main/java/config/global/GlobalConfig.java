package config.global;

import utilities.JavaHelpers;

public class GlobalConfig {
    private static final String GLOBAL_CONFIG_PROPERTIES = "src/main/resources/global-config.properties";
    public static final String BROWSER_NAME = JavaHelpers.getPropertyValue(GLOBAL_CONFIG_PROPERTIES, "browserName");
    public static final boolean HEADLESS = Boolean.parseBoolean(JavaHelpers.getPropertyValue(GLOBAL_CONFIG_PROPERTIES, "headless"));
    public static final boolean DEV_TOOLS = Boolean.parseBoolean(JavaHelpers.getPropertyValue(GLOBAL_CONFIG_PROPERTIES, "devTools"));
    public static final double SLOW_MO = Double.parseDouble(JavaHelpers.getPropertyValue(GLOBAL_CONFIG_PROPERTIES, "slowMo"));
    public static final boolean SET_ACCEPT_DOWNLOAD = Boolean.parseBoolean(JavaHelpers.getPropertyValue(GLOBAL_CONFIG_PROPERTIES, "setAcceptDownload"));
    public static final boolean SET_VIDEO_RECORD = Boolean.parseBoolean(JavaHelpers.getPropertyValue(GLOBAL_CONFIG_PROPERTIES, "setVideoRecord"));
    public static final boolean SET_TRACING = Boolean.parseBoolean(JavaHelpers.getPropertyValue(GLOBAL_CONFIG_PROPERTIES, "setTracing"));
    public static final int SCREEN_WIDTH = Boolean.parseBoolean(JavaHelpers.getPropertyValue(GLOBAL_CONFIG_PROPERTIES, "ci/cd")) ?  1920 : JavaHelpers.getScreenWidth();
    public static final int SCREEN_HEIGHT = Boolean.parseBoolean(JavaHelpers.getPropertyValue(GLOBAL_CONFIG_PROPERTIES, "ci/cd")) ? 1080 : JavaHelpers.getScreenHeight();
    public static final double DEFAULT_NAVIGATION_TIMEOUT = Double.parseDouble(JavaHelpers.getPropertyValue(GLOBAL_CONFIG_PROPERTIES, "defaultNavigationTimeout"));
    public static final double DEFAULT_TIMEOUT = Double.parseDouble(JavaHelpers.getPropertyValue(GLOBAL_CONFIG_PROPERTIES, "defaultTimeout"));

}
