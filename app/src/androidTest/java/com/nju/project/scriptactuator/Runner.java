package com.nju.project.scriptactuator;


import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.support.test.InstrumentationRegistry;
import android.util.Log;

import com.nju.project.scriptactuator.event.Event;
import com.nju.project.scriptactuator.tools.Translator;

import java.io.IOException;
import java.util.List;


/**
 * Executor of a given test event sequence. Uses configs defined in Configuration.class.
 * @see Configuration
 * @see Translator
 * @author Sihong Zeng
 * */
public class Runner {

    // event sequence for execution
    private List<Event> sequence;

    // script content translator
    private Translator translator;

    public Runner(String filepath) throws IOException {
        translator = new Translator(filepath);
        sequence = translator.translate();
    }

    /**
     * Execute every event in sequence.
     * */
    public void run(){
        int counter = 0;

        // start entry activity
        if(translator.getActivityName() == null){ openEntry(); }
        else { openSpecificActivity(translator.getActivityName()); }

        while(counter < Configuration.TOTAL_EXECUTION){
            counter ++;

            for(Event e : sequence){
                e.perform();

                // output console info for debugging
                Log.v("[SA]","Executed event: " + e.type() + ", current execution round: " + counter);

                try { Thread.sleep(Configuration.IDLE_MS); }
                catch(InterruptedException ex){ ex.printStackTrace(); }

            }
        }
    }


    /**
     * Open entry activity of application.
     * */
    private void openEntry(){
        Context context = InstrumentationRegistry.getContext();
        Intent intent =
                context.getPackageManager().getLaunchIntentForPackage(translator.getPackageName());
        context.startActivity(intent);
    }

    /**
     * Open activity by name.
     * */
    private void openSpecificActivity(String activityName) {
        Context context = InstrumentationRegistry.getContext();
        ComponentName componentName = new ComponentName(translator.getPackageName(),
                translator.getPackageName() + "." + activityName);

        try {
            Intent intent = new Intent();
            intent.setComponent(componentName);
            context.startActivity(intent);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
