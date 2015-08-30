package de.renespeck.io;

import java.io.NotSerializableException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import junit.framework.Test;
import junit.framework.TestCase;
import junit.framework.TestSuite;
import de.renespeck.swissknife.io.SerializationUtil;

public class SerializationUtilTest extends TestCase {
    public SerializationUtilTest(String name) {
        super(name);
    }

    public static Test suite() {
        return new TestSuite(SerializationUtilTest.class);
    }

    public void testSimpleSerializeDeserialize() {

        SerializationUtil.setRootFolder(System.getProperty("java.io.tmpdir"));
        String file = "test.data";
        List<String> list = Arrays.asList("a", "b", "c");
        try {
            SerializationUtil.serialize(file, new ArrayList<String>(list), true);
        } catch (NotSerializableException e) {
            e.printStackTrace();
        }
        List<?> dl = SerializationUtil.deserialize(file, ArrayList.class);
        for (int i = 0; i < dl.size(); i++) {
            if (!((String) dl.get(i)).equals(list.get(i))) {
                assertTrue(false);
            }
        }
        assertTrue(true);
    }
}
