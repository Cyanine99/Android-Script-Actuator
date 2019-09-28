package com.nju.project.scriptactuator;

/**
 * Stores default configs of execution.
 * @author Sihong Zeng
 * */
public final class Configuration {

    // decides how many times test sequence will be executed
    public static final int TOTAL_EXECUTION = 5;

    // waiting duration after an event has been performed
    public static final int IDLE_MS = 1000;

    // default text content (for text field filling)
    public static final String DEFAULT_TEXT = "default";

    // script file name
    public static final String FILENAME = "script.txt";

}
