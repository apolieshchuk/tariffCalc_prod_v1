import java.io.IOException;

public class Main {

    public static void main(String[] args) throws IOException {
        //Auto man = new Auto("Man","TGA",(float)0.25,(float)0.09);
        new InitialData();
        new Gui();
        new Gui_SettingsPanel();


       /* final String baseUrl = "https://maps.googleapis.com/maps/api/distancematrix/json"; // путь к Distance Matrix по HTTP
        final Map<String, String> params = new HashMap<>();
        String requestUrl = baseUrl+"?origins=Kirovohrad&destinations=Миколаїв&key=AIzaSyB22QMNLYYO5KxkhLfaPltzXpqktM1JQJU";
        System.out.println(requestUrl);*/

    }

}
