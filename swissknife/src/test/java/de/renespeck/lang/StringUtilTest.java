package de.renespeck.lang;

import java.util.Arrays;
import java.util.HashSet;

import de.renespeck.swissknife.lang.StringUtil;
import junit.framework.Test;
import junit.framework.TestCase;
import junit.framework.TestSuite;

public class StringUtilTest extends TestCase {

  public static Test suite() {
    return new TestSuite(StringUtilTest.class);
  }

  public void testindices() {
    assertTrue(
        StringUtil.indices("Aa aa a aaa aa.", "aa").equals(new HashSet<>(Arrays.asList(3, 12))));
  }

  public void replaceWords() {
    assertTrue(StringUtil.replaceWords("A B C D.", "C D", "xx").equals("A B xx xx."));
  }
}
