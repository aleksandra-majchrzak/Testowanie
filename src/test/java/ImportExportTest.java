import org.testng.annotations.Test;

import static org.testng.Assert.assertEquals;

/**
 * Created by Mohru on 2016-06-07.
 */
public class ImportExportTest extends BaseTest{

    @Test(priority=2, groups = { "chrome"}, enabled = false)//, "firefox", "opera", "safari" })
    public void emptyTest1(){
        assertEquals(true, true);
    }

    @Test(priority=2, groups = { "chrome"}, enabled = false)//, "firefox", "opera", "safari" })
    public void emptyTest2(){
        assertEquals(true, true);
    }

    @Test(priority=2, groups = { "chrome"}, enabled = false)//, "firefox", "opera", "safari" })
    public void emptyTest3(){
        assertEquals(true, true);
    }
}
