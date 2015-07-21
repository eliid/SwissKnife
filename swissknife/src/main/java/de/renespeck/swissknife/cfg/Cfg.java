package de.renespeck.swissknife.cfg;

import java.io.File;

public class Cfg {
    /* default file is 'default' */
    public static String CFG_FILE   = "default";
    /* default location is 'config' */
    public static String CFG_FOLDER = "config";
    /* default location of the log4j properties file */
    public static String LOG_FILE   = CFG_FOLDER + File.separator + "log4j.properties";

}
