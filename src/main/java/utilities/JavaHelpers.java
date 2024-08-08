package utilities;

import data.automation.Data;
import org.apache.commons.lang3.StringUtils;

import java.awt.*;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.*;

public class JavaHelpers {


    // --- Get Part --- \\
    public static int getScreenHeight() {
        Dimension size = Toolkit.getDefaultToolkit().getScreenSize();
        return (int) size.getHeight();
    }

    public static int getScreenWidth() {
        Dimension size = Toolkit.getDefaultToolkit().getScreenSize();
        return (int) size.getWidth();
    }
    // --- Get Part --- \\

    /**
     * Access properties and return as Properties
     * @param propertyfile desired properties file
     * @return Properties data type
     */
    public static Properties accessPropertiesFile(String propertyfile) {
        Properties prop = new Properties();
        // try retrieve data from file
        try {
            prop.load(new FileInputStream(propertyfile));
        }
        // catch exception in case properties file does not exist
        catch (IOException e) {
            e.printStackTrace();
        }
        return prop;
    }

    /**
     * Get property file value
     * @param propertyFile property file location path
     * @param propertyName property name
     * @return String data type
     */
    public static String getPropertyValue(String propertyFile, String propertyName) {
        Properties prop = accessPropertiesFile(propertyFile);
        String variable = prop.getProperty(propertyName);
        if (variable != null) {
            return variable;
        } else {
            propertyName = StringUtils.removeEnd(propertyName, "_" + Data.ENV);
            return prop.getProperty(propertyName);
        }
    }
}