package t34;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * Created by apple on 2/15/18.
 */
public class SOTAListener {

    private int portNumber;

    public static void main(String[] args){

    }

    public void run(){

        Controller controller = new Controller();

        try {
            ServerSocket serverSocket = new ServerSocket(portNumber);
            Socket acceptSocket = serverSocket.accept();
            DataInputStream in = new DataInputStream(acceptSocket.getInputStream());
            DataOutputStream out = new DataOutputStream(acceptSocket.getOutputStream());

            while(true){
                boolean serverDown = false; //Not consider multi-users case
                int type = in.readInt(); //the type of incoming data

                switch (type){
                    case 1://incoming data is byte array of the image

                        int imageSize = in.readInt();
                        byte[] image = new byte[imageSize];
                        String label = controller.getLabel(image); //extract most relevant label from image
                        out.writeChars(label); //write label back to SOTA and inform SOTA which story to tell


                        controller.displayImage(label);//display the first story image
                        out.writeBoolean(true); //tell SOTA can start telling story

                        break;

                    case 2:
                        //inform the current audio is played
                        controller.displayNextImage();
                        out.writeBoolean(true);
                        break;

                    case 3:
                        //client side is turing down -- server side should go down as well
                        serverDown = true;
                        break;
                    default:
                        break;

                }

                if(serverDown)
                    break;
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}