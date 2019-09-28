package com.nju.project.scriptactuator.event.user;

import android.support.test.uiautomator.UiDevice;
import android.support.test.uiautomator.UiObject;
import android.support.test.uiautomator.UiObjectNotFoundException;
import android.support.test.uiautomator.UiSelector;

import com.nju.project.scriptactuator.event.UserEvent;

import static android.support.test.InstrumentationRegistry.getInstrumentation;

/**
 * Simulates an user event of dragging a component to screen coordinate(x,y).
 * @author Sihong Zeng
 * */
public class Drag implements UserEvent {

    // type of this event
    private static final Type TYPE = Type.DRAG;

    // component name for fetching
    private final String component;

    // event attached object
    private UiObject performerObj;

    // x, y coordinate to be dragged to
    private final int x;
    private final int y;

    public Drag(String component,int x,int y){
        this.component = component;
        this.x = x;
        this.y = y;
    }

    @Override
    public String type() {
        return TYPE.toString();
    }

    /**
     * Drag component to position (x,y) on screen.
     * */
    @Override
    public void perform() {
        findObjectByName();

        try {
            performerObj.dragTo(x,y,40);
        } catch (UiObjectNotFoundException e) {
            e.printStackTrace();
        }
    }

    @Override
    public String attachedComponent() {
        return component;
    }

    private void findObjectByName(){
        performerObj = getDevice().findObject(new UiSelector().resourceId(component));
    }

    private UiDevice getDevice(){
        return UiDevice.getInstance(getInstrumentation());
    }

}
