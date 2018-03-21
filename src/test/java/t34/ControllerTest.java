package t34;

import org.jmock.Expectations;
import org.jmock.integration.junit4.JUnitRuleMockery;
import org.junit.Rule;
import org.junit.Test;



import java.util.ArrayList;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.*;

/**
 * Created by apple on 2/15/18.
 */
public class ControllerTest {

    @Rule
    public JUnitRuleMockery context = new JUnitRuleMockery();

    ImageDisplayerInterface displayer = context.mock(ImageDisplayerInterface.class);

    Controller controller = new Controller(displayer);

    String fakeLabel = "Fake Label";

    @Test

    public void canAskDisplayerToShowImage(){

        context.checking(new Expectations() {{
            exactly(1).of(displayer).displayImage(fakeLabel);
        }});

        controller.displayImage(fakeLabel);
    }


    @Test
    public void canAskDisplayNext(){
        context.checking(new Expectations() {{
            exactly(1).of(displayer).displayNext();
        }});

        controller.displayNextImage();
    }

    @Test
    public void returnsMostCommonElementfromArrayList(){
        ArrayList<Integer> rndIntArray = new ArrayList<>();
        rndIntArray.add(2);
        rndIntArray.add(1);
        rndIntArray.add(1);
        assertThat(Controller.mostCommon(rndIntArray), is(1));
    }



}