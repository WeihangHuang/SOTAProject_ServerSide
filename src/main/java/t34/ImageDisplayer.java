package t34;

import java.awt.FlowLayout;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;
import javax.swing.*;

/**
 * Created by apple on 2/15/18.
 */
public class ImageDisplayer implements ImageDisplayerInterface {

    private String filepath;
    private String story;
    private int counter;
    private JFrame frame;

    public ImageDisplayer(){
        this.filepath = null;
        this.story = null;
        this.counter = 0;
    }

    @Override
    public void displayImage(String label) {
        story = label;
        filepath = "./resources/" + story + "_" + counter + ".jpg"; //need to adjust

        display(filepath);
    }

    @Override
    public void displayNext() {
        counter++;
        closePreviouse();
        filepath = "./resources/" + story + "_" + counter + ".jpg";

        display(filepath);
    }

    private void display(String filepath){
        BufferedImage img= null;
        try {
            img = ImageIO.read(new File(filepath));
            ImageIcon icon = new ImageIcon(img);
            this.frame = new JFrame();
            frame.setLayout(new FlowLayout());
            frame.setSize(1400, 900);
            JLabel lbl = new JLabel();
            lbl.setIcon(icon);
            frame.add(lbl);
            frame.setVisible(true);
            frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    private void closePreviouse(){
        frame.dispose();
    }
}
