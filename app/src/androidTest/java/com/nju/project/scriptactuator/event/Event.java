package com.nju.project.scriptactuator.event;

/**
 * Event is defined as a single action performed by either software operations
 * (click buttons, drag toggles, etc.) or hardware operations(pushing menu,
 * back, etc.).
 * @see SystemEvent
 * @see UserEvent
 * @author Sihong Zeng
 * */
public interface Event {

    /**
     * Returns type of event.
     * */
    String type();

    /**
     * Performs behavior according to type.
     * */
    void perform();

}
