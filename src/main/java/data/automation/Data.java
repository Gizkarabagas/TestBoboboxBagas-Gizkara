package data.automation;

import utilities.JavaHelpers;

public class Data {
    private static final String RESOURCE = "src/main/resources/automation.properties";

    public static final String ENV = System.getProperty("env")!=null
        ?System.getProperty("env")
        :JavaHelpers.getPropertyValue(RESOURCE, "env");
    public static final String URL = JavaHelpers.getPropertyValue(RESOURCE, "url");
}
