package com.nju.project.scriptactuator.event.user;

import android.support.test.uiautomator.UiDevice;

import com.nju.project.scriptactuator.event.UserEvent;

import static android.support.test.InstrumentationRegistry.getInstrumentation;

/**
 * Simulates an user event of clicking a point(x,y), which is given in construction method.
 * @author Sihong Zeng
 * */
public class AreaClick implements UserEvent {

    // type of this event
    private static final Type TYPE = Type.AREA_CLICK;

    // coordination of x,y
    private final int x;
    private final int y;

    public AreaClick(int x,int y){
        this.x = x;
        this.y = y;
    }

    @Override
    public String type(){
        return TYPE.toString();
    }

    /**
     * Performs a click on screen position (x, y)
     * */
    @Override
    public void perform(){
        UiDevice.getInstance(getInstrumentation()).click(x,y);
    }

    /**
     * area click does not require any attached component(although it may
     * overlap with existing component areas)
     * */
    @Override
    public String attachedComponent(){
        return "NO_COMPONENT";
    }

}
