package com.nju.project.scriptactuator.event;

/**
 * Simulation of user events, such as clicking button or area,
 * hold, long-hold, etc.
 * @see com.nju.project.scriptactuator.event.user.AreaClick
 * @see com.nju.project.scriptactuator.event.user.Click
 * @see com.nju.project.scriptactuator.event.user.Input
 * @see com.nju.project.scriptactuator.event.user.LongClick
 * @see com.nju.project.scriptactuator.event.user.Drag
 * @author Sihong Zeng
 * */
public interface UserEvent extends Event{

    /**
     * Enumerates types of user events (currently supported)
     * */
    enum Type {
        AREA_CLICK,
        CLICK,
        INPUT,
        LONG_CLICK,
        DRAG
    }

    /**
     * Returns type of event.
     * */
    String type();

    /**
     * Performs behavior according to type.
     * */
    void perform();

    /**
     * Returns component name (if exists).
     * */
    String attachedComponent();

}
