package t34;

import java.awt.image.AreaAveragingScaleFilter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

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

        if (labels.contains("bottle")) {
            return "story_one";
        }
        else if (labels.contains("hand"))
            return "story_two";
        else
            return null;
    }

    public String getStoryFromDb(byte[] image) {
        ArrayList<String> labels = new ArrayList<>();
        try {
            labels = ImageClassifier.ImageClassify(image);
        } catch (Exception e) {
            e.printStackTrace();
        }
        //Once we have obtained our labels, for each label we will query the database to see if we have an existing story that matches
        //We will store all the query results gathered into an arraylist
        ArrayList<String> queryResults = new ArrayList<>();
        //instantiate a new database handler
        DatabaseHandler dbHandler = new DatabaseHandler();
        for (String label : labels){
            queryResults.addAll(dbHandler.queryLabelsReturned(label));
        }
        return (mostCommon(queryResults));

    }

        public void displayImage(String label) {
        imageDisplayer.displayImage(label);
    }

    public void displayNextImage(){
        imageDisplayer.displayNext();
    }

    public void closeImage() { imageDisplayer.closeImage();}

    //method returns the most common element in an ArrayList
    public static <T> T mostCommon(ArrayList<T> list) {
        Map<T, Integer> map = new HashMap<>();

        for (T t : list) {
            Integer val = map.get(t);
            map.put(t, val == null ? 1 : val + 1);
        }

        Map.Entry<T, Integer> max = null;

        for (Map.Entry<T, Integer> e : map.entrySet()) {
            if (max == null || e.getValue() > max.getValue())
                max = e;
        }
        return max.getKey();
    }

}
