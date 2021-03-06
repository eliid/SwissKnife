package de.renespeck.swissknife.time;

public class SimpleStopwatch {

  protected static long n = 0l;
  protected static long time = 0l;

  public static void start() {
    n = System.nanoTime();
    time = 0l;
  }

  public static void stop() {
    time = Math.round((System.nanoTime() - n) / Math.pow(10, 9));

  }

  public static long getTimeInSec() {
    return time;
  }
}
