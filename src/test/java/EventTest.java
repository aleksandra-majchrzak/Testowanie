
import org.testng.annotations.*;
import static org.testng.Assert.assertEquals;

/**
 * Created by Mohru on 2016-06-07.
 */
public class EventTest  extends BaseTest{


    @Parameters("browserType")
    @BeforeClass(alwaysRun = true)
    public void setUp(@Optional("chrome") String browserType) throws Exception {

    }

    @Test(priority=3,groups = { "chrome"})//, "firefox", "opera", "safari" })
    public void emptyTest2(){
        assertEquals(true, true);
    }

}
