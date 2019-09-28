package com.nju.project.scriptactuator.event.system;

import com.nju.project.scriptactuator.event.SystemEvent;

/**
 * Void event. Performs nothing.
 * @author Sihong Zeng
 * */
public class Empty implements SystemEvent {

    // type of this event
    private static final Type TYPE = Type.EMPTY;

    @Override
    public String type(){
        return TYPE.toString();
    }

    @Override
    public void perform(){}

}
