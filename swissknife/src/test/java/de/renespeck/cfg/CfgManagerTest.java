package de.renespeck.cfg;

import java.io.File;

import junit.framework.Test;
import junit.framework.TestCase;
import junit.framework.TestSuite;

import org.apache.commons.configuration.ConfigurationException;
import org.apache.commons.configuration.XMLConfiguration;

import de.renespeck.swissknife.cfg.CfgManager;

public class CfgManagerTest extends TestCase {

    public static Test suite() {
        return new TestSuite(CfgManagerTest.class);
    }

    public void testCfgManager() {
        XMLConfiguration c = new XMLConfiguration();
        String file = CfgManager.CFG_FOLDER.concat(File.separator).concat(CfgManagerTest.class.getName()).concat(".xml");
        try {
            c.save(file);
        } catch (ConfigurationException e) {
            e.printStackTrace();
        }
        assertTrue(CfgManager.getCfg(CfgManagerTest.class).getFileName().equals(file));
        new File(file).delete();
    }
}
