package de.renespeck.swissknife.io;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.SequenceInputStream;
import java.io.UnsupportedEncodingException;
import java.net.URL;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

/**
 *
 * FileHelper with static methods.
 *
 * @author rspeck
 *
 */
public class FileUtil {

  public static final Logger LOG = LogManager.getLogger(FileUtil.class);

  /**
   * Opens a BufferedReader to read all files.
   *
   * @param list of files
   * @return BufferedReader
   */
  public static BufferedReader openFileToRead(final List<String> files) {
    BufferedReader br = null;
    try {
      br = new BufferedReader(new InputStreamReader(
          new SequenceInputStream(new FilesEnumeration(files.toArray(new String[files.size()]))),
          "UTF-8"));
    } catch (final UnsupportedEncodingException e) {
      LOG.error(e.getLocalizedMessage(), e);
    }
    return br;
  }

  /**
   * Opens a BufferedReader to read a file.
   *
   * @param pathToFile path to the file
   * @return BufferedReader
   */
  public static BufferedReader openFileToRead(final String pathToFile) {
    return openFileToRead(pathToFile, "UTF-8");
  }

  /**
   * Opens a BufferedReader to read a file.
   *
   * @param pathToFile path to the file
   * @param encoding used encoding (e.g.,"UTF-8")
   * @return BufferedReader
   */
  public static BufferedReader openFileToRead(final String pathToFile, final String encoding) {
    try {
      return Files.newBufferedReader(new File(pathToFile).toPath(), Charset.forName(encoding));
    } catch (final IOException e) {
      LOG.error("\n", e);
      return null;
    }
  }

  /**
   * Opens a BufferedWriter to write a file.
   *
   * @param pathToFile path to the file
   * @return BufferedWriter
   */
  public static BufferedWriter openFileToWrite(final String pathToFile) {
    return openFileToWrite(pathToFile, "UTF-8");
  }

  /**
   * Opens a BufferedWriter to write a file.
   *
   * @param pathToFile path to the file
   * @param encoding used encoding (e.g.,"UTF-8")
   * @return BufferedWriter
   */
  public static BufferedWriter openFileToWrite(final String pathToFile, final String encoding) {
    try {
      return Files.newBufferedWriter(new File(pathToFile).toPath(), Charset.forName(encoding));
    } catch (final IOException e) {
      LOG.error("\n", e);
      return null;
    }
  }

  /**
   * Reads a file to List.
   *
   * @param pathToFile path to the file
   * @param commentSymbol a line in the given file starting with the commentSymbole will be ignored
   * @return list of lines
   */
  public static List<String> fileToList(final String pathToFile, final String commentSymbol) {
    return fileToListCatched(pathToFile, "UTF-8", commentSymbol);
  }

  /**
   * Reads a file to List.
   *
   * @param pathToFile path to the used file
   * @return list of lines
   */
  public static List<String> fileToList(final String pathToFile) {
    return fileToListCatched(pathToFile, "UTF-8", "");
  }

  /**
   * Reads a file to List.
   *
   * @param pathToFile path to the used file
   * @param encoding used encoding (e.g.,"UTF-8")
   * @param commentSymbol a line in the given file starting with the commentSymbole will be ignored
   * @return list of lines
   */
  public static List<String> fileToList(final String pathToFile, final String encoding,
      final String commentSymbol) throws IOException {
    final BufferedReader br = openFileToRead(pathToFile, encoding);
    final List<String> results = new ArrayList<String>();
    String line;
    while ((line = br.readLine()) != null) {
      if (commentSymbol.isEmpty()) {
        results.add(line);
      } else if (!commentSymbol.isEmpty() && !line.startsWith(commentSymbol)) {
        results.add(line);
      }
    }
    br.close();
    return results;
  }

  public static List<String> fileToListCatched(final String pathToFile, final String encoding,
      final String commentSymbol) {
    try {
      return fileToList(pathToFile, encoding, commentSymbol);
    } catch (final IOException e) {
      LOG.error("\n", e);
      return new ArrayList<String>();
    }
  }

  /**
   *
   * Downloads and copies to file.
   *
   * @param url source to download and copy
   * @param file path to the file
   */
  public static void download(final URL url, final String file) {
    download(url, new File(file));
  }

  /**
   *
   * Downloads and copies to file.
   *
   * @param url source to download and copy
   * @param file path to the file
   */
  public static void download(final URL url, final File file) {
    if (!fileExists(file)) {
      try {
        org.apache.commons.io.FileUtils.copyURLToFile(url, file);
      } catch (final IOException e) {
        final String msg = "" + "\n Error while downloading " + url.toString() + " and copying to "
            + file.toString();
        LOG.error(msg, e);
      }
    }
  }

  /**
   * Checks if a file exists.
   *
   * @param file
   * @return true if the file exists.
   */
  public static boolean fileExists(final String file) {
    return fileExists(new File(file));
  }

  /**
   * Checks if a file exists.
   *
   * @param file
   * @return true if the file exists.
   */
  public static boolean fileExists(final File file) {
    if (file.exists() && !file.isDirectory()) {
      LOG.debug("File " + file.toString() + " exists.");
      return true;
    }
    return false;
  }
}
