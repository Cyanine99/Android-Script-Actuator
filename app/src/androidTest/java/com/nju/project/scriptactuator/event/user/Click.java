package com.nju.project.scriptactuator.event.user;

import android.support.test.uiautomator.*;
import android.util.Log;

import com.nju.project.scriptactuator.event.UserEvent;

import static android.support.test.InstrumentationRegistry.getInstrumentation;

/**
 * Simulates an user event of clicking a component, often a button.
 * @author Sihong Zeng
 * */
public class Click implements UserEvent {

    // type of this event
    private static final Type TYPE = Type.CLICK;

    // component name for fetching
    private final String component;

    // event attached object
    private UiObject performerObj;


    public Click(String comp_name){
        this.component = comp_name;
    }

    private UiDevice getDevice(){
        return UiDevice.getInstance(getInstrumentation());
    }


    private void findObjectByName(){
        performerObj = getDevice().findObject(new UiSelector().resourceId(component));
    }

    @Override
    public String type(){
        return TYPE.toString();
    }

    /**
     * Perform a click.
     *
     * */
    @Override
    public void perform(){
        findObjectByName();

        try {
            if(!performerObj.isClickable()){
                Log.e("[SA]",
                        "Component not clickable! Error at trying to click " + component);
            } else {
                performerObj.click();
            }
        } catch(UiObjectNotFoundException e) {
            e.printStackTrace();
        }
    }

    @Override
    public String attachedComponent(){
        return component;
    }


}
