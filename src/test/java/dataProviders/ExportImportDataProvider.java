package dataProviders;

import org.testng.annotations.DataProvider;
import tests.TestBase;

/**
 * Created by Mohru on 13.06.2016.
 */
public class ExportImportDataProvider extends TestBase{

    @DataProvider(name="daneExport")
    public static Object[][] daneExport() throws Exception{

        return new Object[][]{
                {"testowy","C:\\Users\\Mohru\\Downloads","zlekonto6@gmail.com.ical.zip"}
        };

    }

    @DataProvider(name="daneImport")
    public static Object[][] daneImport() throws Exception{

        return new Object[][]{
                {"testowy-a","Jan","Przetworzono zero wydarzeń."},
                {"b","Jan","Przetworzono wydarzenia (7)."}
        };

    }
}
