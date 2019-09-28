package com.nju.project.scriptactuator.event.system;

import android.support.test.uiautomator.UiDevice;

import com.nju.project.scriptactuator.event.SystemEvent;

import static android.support.test.InstrumentationRegistry.getInstrumentation;

/**
 * Simulates a system event of pressing virtual BACK button.
 * @author Sihong Zeng
 * */
public class Back implements SystemEvent {

    // type of this event
    private static final Type TYPE = Type.BACK;

    // associated UiDevice
    private UiDevice device;


    @Override
    public String type(){
        return TYPE.toString();
    }

    @Override
    public void perform(){
        getUiDevice();
        device.pressBack();
    }

    private void getUiDevice(){
        device = UiDevice.getInstance(getInstrumentation());
    }

}
