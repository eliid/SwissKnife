package de.renespeck.swissknife.cfg;

import java.io.File;
import java.util.Iterator;

import org.apache.commons.configuration.ConfigurationException;
import org.apache.commons.configuration.XMLConfiguration;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

import de.renespeck.swissknife.io.FileUtil;

/**
 * 
 * @author rspeck
 * 
 */
public class CfgManager {

    public static String        CFG_FOLDER = "config";
    public static String        CFG_FILE   = "default";
    public static String        LOG_FILE   = CFG_FOLDER + File.separator + "log4j.properties";

    private static final Logger LOG        = LogManager.getLogger(CfgManager.class);

    public static XMLConfiguration getCfg(String className) {

        String file = CFG_FOLDER.concat(File.separator).concat(className).concat(".xml");
        String fileDefault = CFG_FOLDER.concat(File.separator).concat(CFG_FILE).concat(".xml");

        LOG.info("Loading: ".concat(file));

        if (FileUtil.fileExists(file))
            try {
                return new XMLConfiguration(file);
            } catch (ConfigurationException e) {
                LOG.error(e.getLocalizedMessage(), e);
            }
        else
            LOG.warn("Could not find file: ".concat(file));

        LOG.info("Loading default file: ".concat(fileDefault));
        try {
            return new XMLConfiguration(fileDefault);
        } catch (ConfigurationException e) {
            LOG.error(e.getLocalizedMessage(), e);
        }

        return null;
    }

    public static XMLConfiguration getCfg(Class<?> classs) {
        return CfgManager.getCfg(classs.getName());
    }

    public static void printKeys(XMLConfiguration cfg) {
        Iterator<String> iter = cfg.getKeys();
        while (iter.hasNext())
            LOG.info(iter.next());
    }
}
