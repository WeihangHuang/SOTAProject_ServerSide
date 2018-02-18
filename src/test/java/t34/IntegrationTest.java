package t34;

import org.junit.Test;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.concurrent.TimeUnit;

import static org.junit.Assert.*;

/**
 * Created by apple on 2/17/18.
 */
public class IntegrationTest {

    @Test
    public void canGetLabelsAndDisplayImage(){
        Controller controller = new Controller();
        String imagePath = "./resources/testImage.jpg";

        try {
            Path path = Paths.get(imagePath);
            byte[] data = Files.readAllBytes(path);
            String label = controller.getLabel(data);

            assertEquals(label, "dog");

            controller.displayImage(label);
            TimeUnit.SECONDS.sleep(5);
            controller.displayNextImage();
            TimeUnit.SECONDS.sleep(5);
        } catch (Exception e){
            e.printStackTrace();
        }
    }

}