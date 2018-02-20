package t34;

import org.junit.Test;

import java.util.concurrent.TimeUnit;

import static org.junit.Assert.*;

/**
 * Created by apple on 2/17/18.
 */
public class ImageDisplayerTest {

    ImageDisplayer displayer = new ImageDisplayer();
    @Test
    public void canDisplayImage(){
        try {
            displayer.displayImage("dog");
            TimeUnit.SECONDS.sleep(5);
            displayer.displayNext();
            TimeUnit.SECONDS.sleep(5);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

}