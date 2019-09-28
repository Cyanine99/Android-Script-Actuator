package com.nju.project.scriptactuator.tools;

import android.support.test.InstrumentationRegistry;
import com.nju.project.scriptactuator.event.*;
import com.nju.project.scriptactuator.event.system.Back;
import com.nju.project.scriptactuator.event.system.Empty;
import com.nju.project.scriptactuator.event.system.Home;
import com.nju.project.scriptactuator.event.system.Menu;
import com.nju.project.scriptactuator.event.system.Rotate;
import com.nju.project.scriptactuator.event.user.AreaClick;
import com.nju.project.scriptactuator.event.user.Click;
import com.nju.project.scriptactuator.event.user.Drag;
import com.nju.project.scriptactuator.event.user.Input;
import com.nju.project.scriptactuator.event.user.LongClick;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.List;

/**
 * This class is responsible for translating an execution script.txt sequence into
 * a list of events, either system events or user events. Script language format
 * is listed below:
 *  - Every script.txt line starts with an upper case label, then a single space.
 *  - Component name(if requested) and action is separated by single space.
 *  - Package name is needed.
 *  - Script not necessarily need an ACT tag. If not given, Runner.run() will open
 *    entry activity instead.
 *  - Among supported events, some params are not necessarily needed. If not appointed,
 *    actuator will use default config instead.
 *  - Instructions after END will be ignored.
 *  ------------------------------------------------------------------------------
 *  <b>CURRENTLY SUPPORTED SCRIPT KEYWORDS:</b>
 *  [PKG] package name
 *  [ACT] activity name
 *  [EVE] an event, followed by event type and attached component name(if exists)
 *  [END] end mark
 *  ------------------------------------------------------------------------------
 *  <b>CURRENTLY SUPPORTED SCRIPT ACTIONS (EVENT FORMAT):</b>
 *  *** Note: optional parameter(s) is surrounded by []. ***
 *  click component_resource_id
 *  area-click x_coordinate y_coordinate
 *  input component_resource_id [text_content]
 *  input component_resource_id
 *  long-click component_resource_id
 *  drag component_resource_id x y
 *  back
 *  home
 *  menu
 *  rotate
 *  rotate [orientation] (0 - left, 1 - natural, 2 - right)
 *  ------------------------------------------------------------------------------
 *  <b>SCRIPT FORMAT EXAMPLE:</b>
 *  PKG com.test.package
 *  ACT initial_activity
 *  EVE click button01
 *  EVE back
 *  EVE click button02
 *  EVE home
 *  EVE long-click button03
 *  END
 *  ------------------------------------------------------------------------------
 * @see Event
 * @see com.nju.project.scriptactuator.Configuration
 * @author Sihong Zeng
 *
 * */
public class Translator {

    // generated event sequence from script file
    private List<Event> sequence;

    // file path of script
    private final String filepath;

    // package name and entry activity name of application for test
    private String packageName;
    private String activityName;

    // if activity name is logged
    private boolean flag = false;


    public Translator(String filepath){
        this.filepath = filepath;
        sequence = new LinkedList<>();
    }

    public String getPackageName(){
        return packageName;
    }

    public String getActivityName(){ return activityName; }


    /**
     * Receives file content from script and translate into executable sequences.
     * @return translation result
     * */
    public List<Event> translate() throws IOException{
        List<String> raw = fileReader();
        assert raw != null : "Fail to load script file content.";

        operation:
        for(String instruction : raw){
            switch(instruction.substring(0,3)){
                case "PKG":
                    packageName = instruction.substring(4);
                    break;
                case "ACT":
                    if(!flag){
                        activityName = instruction.substring(4);
                        flag = true;
                    } else {
                        throw new RuntimeException("Duplicate activity name info: " + activityName);
                    }
                    break;
                case "END":
                    // marks end of script
                    break operation;
                case "EVE":
                    sequence.add(generateFromStringPair(instruction.substring(4)));
                    break;
                default:
                    break;
            }
        }

        return sequence;
    }

    /**
     * Get corresponding event type from a line(pair) of instruction.
     * @param eventInfo a single event info
     * @return generated event
     * */
    private Event generateFromStringPair(String eventInfo){
        String[] info = eventInfo.split(" ");
        // event type
        String op = info[0];

        Event e = new Empty();
        switch(op){
            case "click":
                assert info.length == 2;
                e = new Click(getResourceId(info[1]));
                break;
            case "long-click":
                assert info.length == 2;
                e = new LongClick(getResourceId(info[1]));
                break;
            case "area-click":
                assert info.length == 3;
                e = new AreaClick(Integer.parseInt(info[1]),Integer.parseInt(info[2]));
                break;
            case "input":
                e = info.length == 3 ? new Input(getResourceId(info[1]),info[2])
                                     : new Input(getResourceId(info[1]));
                break;
            case "back":
                e = new Back();
                break;
            case "home":
                e = new Home();
                break;
            case "menu":
                e = new Menu();
                break;
            case "rotate":
                e = info.length == 1 ? new Rotate()
                                     : new Rotate(Integer.parseInt(info[1]));
                break;
            case "drag":
                e = new Drag(info[1],Integer.parseInt(info[2]),Integer.parseInt(info[3]));
                break;
            default:
                break;
        }

        return e;
    }


    /**
     * Read script file by lines.
     * @return result list
     * */
    private List<String> fileReader() throws IOException {
        InputStream input = InstrumentationRegistry.getTargetContext().getAssets().open(filepath);
        BufferedReader reader =
                new BufferedReader(new InputStreamReader(input));

        List<String> raw = new LinkedList<>();
        for(String line = reader.readLine(); line != null; line = reader.readLine()){
            raw.add(line);
        }

        return raw;
    }

    private String getResourceId(String compName){
        return packageName + ":id/" + compName;
    }

}
