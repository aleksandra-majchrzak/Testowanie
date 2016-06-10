import org.testng.annotations.Test;

import static org.testng.Assert.assertEquals;

/**
 * Created by Mohru on 2016-06-07.
 */
public class ReminderTest  extends BaseTest{

    @Test(groups = { "chrome"})//, "firefox", "opera", "safari" })
    public void emptyTest1(){
        assertEquals(true, true);
    }

    @Test(groups = { "chrome"})//, "firefox", "opera", "safari" })
    public void emptyTest2(){
        assertEquals(true, true);
    }

    @Test(groups = { "chrome"})//, "firefox", "opera", "safari" })
    public void emptyTest3(){
        assertEquals(true, true);
    }
}
