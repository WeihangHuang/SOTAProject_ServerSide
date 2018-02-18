package t34;

import java.util.ArrayList;

/**
 * Created by apple on 2/15/18.
 */
public class Controller {

    private ImageDisplayerInterface imageDisplayer;

    public Controller(){
        this.imageDisplayer = new ImageDisplayer();
    }

    public Controller(ImageDisplayerInterface imageDisplayer){
        this.imageDisplayer = imageDisplayer;
    }


    public String getLabel(byte[] image) {
        ArrayList<String> labels = new ArrayList<>();

        try {
            labels = ImageClassifier.ImageClassify(image);
        } catch (Exception e) {
            e.printStackTrace();
        }

        return labels.get(0);
    }

    public void displayImage(String label) {
        imageDisplayer.displayImage(label);
    }

    public void displayNextImage(){
        imageDisplayer.displayNext();
    }
}