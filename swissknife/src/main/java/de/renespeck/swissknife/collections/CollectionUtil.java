package de.renespeck.swissknife.collections;

import java.text.Normalizer;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.apache.commons.lang.StringUtils;

public class CollectionUtil {

  /**
   * Transforms all strings in the given list to lower case.
   *
   * @param list list with strings
   * @return list with lower case strings
   */
  public static List<String> toLowerCase(final List<String> list) {
    final List<String> lowerCase = new ArrayList<>();
    for (final String s : list) {
      lowerCase.add(s.toLowerCase());
    }
    return lowerCase;
  }

  /**
   *
   * Adds normalized string (without accents) to the given set.
   *
   * @param set set with strings
   * @return set with normalized strings
   */
  public static Set<String> addNonAccent(final Set<String> set) {
    final Set<String> normalized = new HashSet<String>();
    for (final String s : set) {
      String normal = Normalizer.normalize(s, Normalizer.Form.NFD);
      normal = normal.replaceAll("\\p{InCombiningDiacriticalMarks}+", "");
      if (!normal.equals(s)) {
        normalized.add(normal);
      }
    }
    set.addAll(normalized);
    return set;
  }

  /**
   * Converts a String in list format (["1st element",2nd element"]) to a list of Strings.
   *
   * @param listString Sting in list format
   * @return List of String
   *
   */
  public static List<String> toList(final String listString) {
    return Arrays.asList(toArray(listString));
  }

  /**
   * Converts a String in list format (["1st element",2nd element"]) to a Array of Strings.
   *
   * @param listString Sting in list format
   * @return Array of String
   *
   */
  public static String[] toArray(final String listString) {
    final String[] a =
        listString.trim().substring(1).substring(0, listString.length() - 2).split("\\s*,\\s*");

    final List<String> list = new ArrayList<String>(Arrays.asList(a));
    list.removeAll(Collections.singleton(""));
    return list.toArray(new String[list.size()]);

  }

  /**
   * Converts a String in list format (["1st element",2nd element"]) to a String with space
   * separation.
   *
   * @param listString Sting in list format
   * @return String
   *
   */
  public static String arrayToString(final String listString) {
    return StringUtils.join(CollectionUtil.toArray(listString), " ").replaceAll(" +", " ");
  }

  public static String replaceLast(final String sentence, final String word,
      final String replacement) {
    final int i = sentence.lastIndexOf(word);
    return sentence.substring(0, i) + replacement + sentence.substring(i + word.length());
  }
}
