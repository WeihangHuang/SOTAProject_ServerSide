package t34;

import org.junit.Test;
import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.*;

import java.util.ArrayList;

public class DatabaseHandlerTest {

    //test to check the returned returns of the sql query and the validity of the database connection
    @Test
    public void returnsCorrectStoryFromGivenLabel(){
        DatabaseHandler dBHandler = new DatabaseHandler();
        ArrayList<String> storyList = new ArrayList<>();
        storyList.addAll(dBHandler.queryLabelsReturned("ball"));
        assertThat(storyList.size(), is(1));


    }

}
