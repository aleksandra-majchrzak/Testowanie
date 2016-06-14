package dataProviders;

import org.testng.annotations.DataProvider;

/**
 * Created by Mohru on 11.06.2016.
 */
public class CalendarDataProvider {

    @DataProvider(name="calendarData")
    public static Object[][] dane() throws Exception{

        return new Object[][]{
                /* calendarName, description, location, errorMessage */
                {"","","", "Nie można utworzyć kalendarza bez nazwy"},
                {"testowy","","", ""},
                {"Jan","","", ""},
                {"!@#$%^&*()_+{}|:\\\"<>?/.,l\\[[]=-","opis","Kraków", ""},
                {"testowy2","opis","", ""},
                {"testowy3","","Kraków", ""}

        };

    }

    @DataProvider(name="calendarEditData")
    public static Object[][] daneEdit() throws Exception{

        return new Object[][]{
                /* oldCalendarName, newCalendarName, description, location, errorMessage */
                {"testowy","","","", "Nie można utworzyć kalendarza bez nazwy"},
                {"!@#$%^&*()_+{}|:\\\"<>?/.,l\\[[]=-","|:\\\"<>?/.,l\\[[]=-","", "", ""},
                {"Jan","Jan","","Kraków", ""},
                {"testowy3","testowy3","opis","Kraków", ""},
                {"testowy3","testowy3","opis","", ""}

        };

    }
}
