package com.nju.project.scriptactuator;

import org.junit.Test;
import java.io.IOException;

public class TestStarter {

    @Test
    public void startTest() throws IOException {
        Runner runner = new Runner(Configuration.FILENAME);
        runner.run();
    }

}
