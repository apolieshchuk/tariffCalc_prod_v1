import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.util.ArrayList;

class InitialData {

    private ArrayList<String> file;

    static float FUEL_PRICE_WO_NDS; //валюты за литр без НДС
    static float FUEL_DT_CONSUM_CARGOPER100TKM; //доп расход на загруженную массу ДТ на 100 ткм
    static float AMORTIZATION_IN_TKM_WO_NDS; // амортизация 3.3 грн на км без перегруза (0.135грн на тКм)
    static int DRIVER_COMMANDING; // коммандировочные водителей
    static int PROFIT_NONCASH; //ожидаемая прибыль без перегруза БЕЗНАЛ
    static int MIDDLE_WEIGHT; //средний тоннаж
    static String STRING_FORMAT_INFOTEXT; //формат строки для инфы
    static int PROFIT_CASH; //ожидаемая прибыль без перегруза НАЛ
    static int DRIVER_SALARY; //зарплата водителя
    static float EMPTY_PER_KM; //потребление пустого авто на 100км
    static String [] CURRENCIES; //валюты
    static String CURRENT_CURRENCY; // текущая валюта


    InitialData(){
        file = new ArrayList<String>();
        boolean fromInet = true;
        if(fromInet){
            try{
                urlReader();
                establishVarFromInet();
            } catch(IOException e){
                establishDefaultVar();
            }
        }else{
            establishDefaultVar();
        }

    }

    private void establishVarFromInet() {
        FUEL_PRICE_WO_NDS = Float.parseFloat(file.get(0)); //валюты за литр без НДС
        FUEL_DT_CONSUM_CARGOPER100TKM = Float.parseFloat(file.get(1)); //доп расход на загруженную массу ДТ на 100 ткм
        AMORTIZATION_IN_TKM_WO_NDS = Float.parseFloat(file.get(2)); // амортизация 3.3 грн на км
        DRIVER_COMMANDING = Integer.parseInt(file.get(3)); // коммандировочные водителей
        PROFIT_NONCASH = Integer.parseInt(file.get(4)); //ожидаемая прибыль в сутки БЕЗНАЛ
        MIDDLE_WEIGHT = Integer.parseInt(file.get(5)); //средний тоннаж
        STRING_FORMAT_INFOTEXT = file.get(6); //формат строки для инфы
        PROFIT_CASH = Integer.parseInt(file.get(7)); //ожидаемая прибыль в сутки НАЛ
        DRIVER_SALARY = Integer.parseInt(file.get(8)); // зарплата водителя %
        EMPTY_PER_KM = Float.parseFloat(file.get(9)); //потребление пустого авто на 100км
        CURRENCIES = file.get(10).split(","); //валюты
    }

    private void establishDefaultVar(){
        //Добавить инфу и надпись по доступу к интернету
        //Сохранить последние настройки?
        FUEL_PRICE_WO_NDS = (float)21.8; //валюты за литр c НДС
        FUEL_DT_CONSUM_CARGOPER100TKM = (float)1; //доп расход на загруженную массу ДТ 1л на 100 ткм
        AMORTIZATION_IN_TKM_WO_NDS = (float)0.135; // 0,162 с НДС амортизация 3.3 грн на км без перегруза (0.135грн на тКм без НДС)
        DRIVER_COMMANDING = 150; // коммандировочные водителей
        PROFIT_NONCASH = 1200; //ожидаемая прибыль в сутки по безналу
        MIDDLE_WEIGHT = 25; //средний тоннаж
        STRING_FORMAT_INFOTEXT = "%(.0f"; //формат строки для инфы
        PROFIT_CASH = 800; //ожидаемая прибыль в сутки НАЛ
        DRIVER_SALARY = 9; //зарплата водителя %
        EMPTY_PER_KM = 25; //потребление пустого авто на 100км
        CURRENCIES = new String[]{"грн","руб"}; //валюты
    }

    private void urlReader() throws IOException {
        URL confFile = new URL("http://trans-tariff.at.ua/_ld/0/1_conf.txt");
        BufferedReader in = new BufferedReader(
                new InputStreamReader(confFile.openStream(),"Cp1251"));
        String inputLine;
        while ((inputLine = in.readLine()) != null)
            file.add(inputLine.split("/")[0]);
        in.close();
    }


}
