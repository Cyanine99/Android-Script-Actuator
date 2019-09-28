package com.nju.project.scriptactuator.event;

/**
 * Simulation of system events (including pushing hardware keys,
 * virtual keys, and other events such as phone call, rotation)
 * @see com.nju.project.scriptactuator.event.system.Back
 * @see com.nju.project.scriptactuator.event.system.Home
 * @see com.nju.project.scriptactuator.event.system.Menu
 * @see com.nju.project.scriptactuator.event.system.Rotate
 * @author Sihong Zeng
 * */
public interface SystemEvent extends Event {

    /**
     * Enumerates types of system events (currently supported)
     * */
    enum Type {
        BACK,
        EMPTY,
        HOME,
        MENU,
        ROTATE
    }

    /**
     * Returns type of event.
     * */
    String type();

    /**
     * Performs behavior according to type.
     * */
    void perform();

}
