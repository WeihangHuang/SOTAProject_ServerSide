package t34;

import org.junit.Test;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;


import static org.junit.Assert.*;

/**
 * Created by apple on 2/17/18.
 */
public class ImageClassifierTest {

    @Test
    public void canReturnLabels(){
        try {
            String filepath = "./resources/testImage.jpg";
            Path path = Paths.get(filepath);
            byte[] data = Files.readAllBytes(path);

            ArrayList<String> labels;

            labels = ImageClassifier.ImageClassify(data);

            assertTrue(labels.contains("dog"));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}