package dataProviders;

import org.testng.annotations.DataProvider;

/**
 * Created by Mohru on 11.06.2016.
 */
public class CalendarDataProvider {

    @DataProvider(name="calendar")
    public static Object[][] dane() throws Exception{

        return new Object[][]{
                {"","","Podaj adres e-mail.",null},
                {"9879hggfdgsd","","Google nie rozpoznaje tego adresu e-mail.",null},
                {"zlekonto6","",null,"Podaj hasło."},
                {"zlekonto6","ks",null,"Podany przez Ciebie adres e-mail i hasło nie zgadzają się."},
                {"zlekonto6","zlekonto66",null,null}//,
                //{"coproject.example@gmail.com","co.example1",null,null}
        };

    }
}
