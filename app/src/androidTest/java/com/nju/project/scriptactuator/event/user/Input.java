package com.nju.project.scriptactuator.event.user;


import android.support.test.uiautomator.UiDevice;
import android.support.test.uiautomator.UiObject;
import android.support.test.uiautomator.UiObjectNotFoundException;
import android.support.test.uiautomator.UiSelector;

import com.nju.project.scriptactuator.Configuration;
import com.nju.project.scriptactuator.event.UserEvent;

import static android.support.test.InstrumentationRegistry.getInstrumentation;


/**
 * Simulates event of text input. Text content must be either appointed or
 * use default content in config.
 * @see com.nju.project.scriptactuator.Configuration
 * @author Sihong Zeng
 * */
public class Input implements UserEvent {

    // type of this event
    private static final Type TYPE = Type.INPUT;

    // component name for fetching
    private final String component;

    // event attached object
    private UiObject performerObj;

    // appointed input text content
    private final String text;

    /**
     * Input text content is given.
     * @param comp_name performer component name
     * @param text appointed text
     * */
    public Input(String comp_name,String text){
        this.component = comp_name;
        this.text = text;
    }

    /**
     * Input text content not given.
     * */
    public Input(String comp_name){
        this.component = comp_name;
        this.text = Configuration.DEFAULT_TEXT;
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
     * Fills in a text box using content.
     * */
    @Override
    public void perform(){
        findObjectByName();

        try{
            performerObj.clearTextField();
            performerObj.setText(text);
        }catch(UiObjectNotFoundException e){
            e.printStackTrace();
        }
    }

    @Override
    public String attachedComponent(){
        return component;
    }

}
