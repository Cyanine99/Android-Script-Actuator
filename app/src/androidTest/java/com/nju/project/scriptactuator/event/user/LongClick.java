package com.nju.project.scriptactuator.event.user;

import android.support.test.uiautomator.UiDevice;
import android.support.test.uiautomator.UiObject;
import android.support.test.uiautomator.UiObjectNotFoundException;
import android.support.test.uiautomator.UiSelector;
import android.util.Log;

import com.nju.project.scriptactuator.event.UserEvent;

import static android.support.test.InstrumentationRegistry.getInstrumentation;

/**
 * Simulates an user event of long-clicking a component, often a button.
 * @author Sihong Zeng
 * */
public class LongClick implements UserEvent {

    //type of this event
    private static final Type TYPE = Type.LONG_CLICK;

    // component name for fetching
    private final String component;

    // event attached object
    private UiObject performerObj;


    public LongClick(String comp_name){
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
     * Performs a long-click.
     * */
    @Override
    public void perform(){
        findObjectByName();

        try{
            if(!performerObj.isLongClickable()){
                Log.e("[SA]",
                        "Component not long-clickable! Error at trying to long-click " + component);
            } else {
                performerObj.longClick();
            }
        }catch(UiObjectNotFoundException e){
            e.printStackTrace();
        }
    }

    @Override
    public String attachedComponent(){
        return component;
    }

}
