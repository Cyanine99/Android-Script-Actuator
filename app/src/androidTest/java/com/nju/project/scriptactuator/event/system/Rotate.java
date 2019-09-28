package com.nju.project.scriptactuator.event.system;


import android.os.RemoteException;
import android.support.test.uiautomator.UiDevice;

import com.nju.project.scriptactuator.event.SystemEvent;

import static android.support.test.InstrumentationRegistry.getInstrumentation;

/**
 * Simulates a system event of rotation.
 * @author Sihong Zeng
 * */
public class Rotate implements SystemEvent {

    // type of this event
    private static final Type TYPE = Type.ROTATE;

    // associated UiDevice
    private UiDevice device;

    // orientation, default by 1 (natural orientation)
    private int orientation = 1;


    public Rotate() { }
    public Rotate(int orientation){ this.orientation = orientation; }


    @Override
    public String type() {
        return TYPE.toString();
    }


    @Override
    public void perform(){
        perform(orientation);
    }

    /**
     * Rotate screen according to direction param.
     * @param orientation direction. 0 for left, 1 for natural, 2 for right
     * */
    private void perform(int orientation) {
        assert orientation == 0 || orientation == 1 || orientation == 2 :
                "Direction param assertion fail: Current param is " + orientation;

        getUiDevice();

        try {
            device.unfreezeRotation();
            switch (orientation) {
                case 0:
                    device.setOrientationLeft();
                    break;
                case 1:
                    device.setOrientationNatural();
                    break;
                case 2:
                    device.setOrientationRight();
                    break;
                default:
                    break;
            }
        } catch (RemoteException e) {
            e.printStackTrace();
        }
    }

    private void getUiDevice(){
        device = UiDevice.getInstance(getInstrumentation());
    }

}
