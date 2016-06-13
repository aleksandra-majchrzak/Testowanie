package dataProviders;

import org.testng.annotations.DataProvider;

/**
 * Created by Mohru on 13.06.2016.
 */
public class EventDataProvider {

    @DataProvider(name="eventData")
    public static Object[][] eventData() throws Exception{

        return new Object[][]{
                /* nazwaWydarzenia, dataPoczatkowa, godzinaPoczatkowa, godzinaKonckowa, dataKoncowa, lokalizacja, kalendarz, opis, całyDzien, powtarzaj (jak czesto?), powtRodzaj, powtCzestosc, kolor, errorMsg */
                {"wydarzenie zly koniec", "6/14/16", "17:40", "5/10/16", "17:40", "Kraków", "testowy", "opis", false, false, 0, 0, 1, "Nie można utworzyć wydarzenia kończącego się przed rozpoczęciem." },
                {"wydarzenie brak poczatek", "6/14/16", "", "6/15/16", "17:40", "Kraków", "testowy", "opis", false, false, 0, 0,2, ""  },
                {"wydarzenie brak poczatek", "", "17:40", "6/15/16", "17:40", "Kraków", "testowy", "opis", false, false, 0, 0,3, ""  },
                {"wydarzenie brak koniec", "6/14/16", "17:40", "6/15/16", "", "Kraków", "testowy", "opis", false, false, 0, 0,3, ""  },
                {"wydarzenie brak koniec", "6/14/16", "17:40", "", "17:40", "Kraków", "testowy", "opis", false, false, 0, 0,4, ""   },
                {"wydarzenie zly koniec czas", "6/14/16", "17:40", "6/15/16", "30:40", "Kraków", "testowy", "opis", false, false, 0, 0,5, ""  },
                {"wydarzenie zly koniec czas", "6/14/16", "17:40", "6/15/16", "17:70", "Kraków", "testowy", "opis", false, false, 0, 0,6, ""  },
                {"wydarzenie zly poczatek czas ", "6/14/16", "40:40", "6/15/16", "17:40", "Kraków", "testowy", "opis", false, false, 0, 0,7, ""  },
                {"wydarzenie zly poczatek czas", "6/14/16", "ple", "6/15/16", "17:40", "Kraków", "testowy", "opis", false, false, 0, 0,8, ""  },
                {"wydarzenie zly koniec czas", "6/14/16", "17:40", "6/15/16", "ple", "Kraków", "testowy", "opis", false, false, 0, 0,9, ""  },
                {"wydarzenie inny kalendarz", "6/14/16", "17:40", "6/15/16", "17:40", "Kraków", "Jan", "opis", false, false, 0, 0,10, ""  },
                {"wydarzenie brak miejsca", "6/14/16", "17:40", "6/15/16", "17:40", "", "Jan", "opis", false, false, 0, 0,11, "" },
                {"wydarzenie brak opisu", "6/14/16", "17:40", "6/14/16", "17:40", "Karków", "Jan", "", false, false, 0, 0,10, ""  },
                {"wydarzenie taki sam czas", "6/14/16", "17:40", "6/15/16", "17:40", "Kraków", "testowy", "opis", false, false, 0, 0,9, ""  },
                {"", "6/14/16", "17:40", "6/14/16", "17:40", "Kraków", "testowy", "opis", false, false, 0, 0,8, "" },
                {"wydarzenie", "6/14/16", "17:40", "6/15/16", "17:40", "Kraków", "testowy", "opis", false, false, 0, 0,7, ""  },
                {"wydarzenie !@#$%^&*()_+{}|:\\ \\\" <>?-.,';[][=/" , "6/14/16", "17:40", "6/15/16", "17:40", "Kraków", "testowy", "opis", false, false, 0, 0,6, ""  },
                {"wydarzenie return 0; main null", "6/14/16", "17:40", "6/15/16", "17:40", "Kraków", "testowy", "opis", false, false, 0, 0,5, ""  },
                {"wydarzenie powtarzaj", "6/14/16", "17:40", "6/15/16", "17:40", "Kraków", "testowy", "opis", false, true, 5, 1, 4, ""  },
                {"wydarzenie caly dzien", "6/14/16", "17:40", "6/15/16", "17:40", "Kraków", "testowy", "opis", true, false, 0, 0,3, ""  },
                {"wydarzenie caly dzien powratzaj", "6/14/16", "17:40", "6/15/16", "17:40", "Kraków", "testowy", "opis", true, true, 2, 5, 2, ""  }
        };

    }
}
