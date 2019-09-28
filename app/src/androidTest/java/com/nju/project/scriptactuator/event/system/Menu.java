package com.nju.project.scriptactuator.event.system;

import android.support.test.uiautomator.UiDevice;

import com.nju.project.scriptactuator.event.SystemEvent;

import static android.support.test.InstrumentationRegistry.getInstrumentation;

/**
 * Simulates a system event of pressing virtual MENU button.
 * @author Sihong Zeng
 * */
public class Menu implements SystemEvent {

    // type of this event
    private static final Type TYPE = Type.MENU;

    // associated UiDevice
    private UiDevice device;


    @Override
    public String type(){
        return TYPE.toString();
    }

    @Override
    public void perform(){
        getUiDevice();
        device.pressMenu();
    }

    private void getUiDevice(){
        device = UiDevice.getInstance(getInstrumentation());
    }

}
