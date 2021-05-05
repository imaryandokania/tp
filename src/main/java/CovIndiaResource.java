
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import org.json.JSONArray;
import org.json.JSONObject;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;
import java.net.http.HttpResponse;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URI;
import java.net.URL;
import java.net.URLConnection;
import java.nio.charset.StandardCharsets;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;



public class CovIndiaResource extends TelegramLongPollingBot {
    @Override
    public String getBotUsername() {
        return "covindiaresource_bot";
    }

    @Override
    public String getBotToken() {
        return "1779610346:AAGIhfsCaB8HaiU0_VQErIOhsZu-hEItSyQ";
    }

    @Override
    public void onUpdateReceived(Update update) {
//    System.out.println(update.getMessage().getText());
//        System.out.println(update.getMessage().getFrom().getFirstName());

        String commands =update.getMessage().getText();

        SendMessage message=new SendMessage();
        if(commands.equals("/start"))
        {
            message.setText("Welcome! hey"+update.getMessage().getFrom().getFirstName()+" We are here to help people by providing details about Beds , Oxygen , Remdesivir etc");
        }
        if(commands.equals("/delhibeds"))
        {

            System.out.println("/delhibeds  --->"+update.getMessage().getFrom().getFirstName());
            HttpClient client = HttpClient.newHttpClient();
            HttpRequest request = HttpRequest.newBuilder()
                    .uri(URI.create("https://covidresourceindia.herokuapp.com/posts"))
                    .build();

            HttpResponse<String> response = null;
            try {
                response = client.send(request, HttpResponse.BodyHandlers.ofString());
            } catch (IOException ioException) {
                ioException.printStackTrace();
            } catch (InterruptedException interruptedException) {
                interruptedException.printStackTrace();
            }
            //   System.out.println(response.body());

            JSONArray mJsonArray = new JSONArray(response.body().toString());
            JSONObject mJsonObject = mJsonArray.getJSONObject(0);
            JSONArray mJsonArrayProperty1 = mJsonObject.getJSONArray("beds");
            String s="";
            s=s+"-----Delhi Bed Status-----"+"\n"+"\n";
            for(int i=0;i<mJsonArrayProperty1.length();i++)
            {
                JSONObject mJsonObjectProperty = mJsonArrayProperty1.getJSONObject(i);
                String location = mJsonObjectProperty.getString("Location");
                String Description = mJsonObjectProperty.getString("Description");
                String Locationcontact = mJsonObjectProperty.getString("Location-contact");
                String Verifiedat = mJsonObjectProperty.getString("Verified at");
                String Tocontact = mJsonObjectProperty.getString("Tocontact");
                s=s+"Location: "+location+"\n"+"Description: "+Description+"\n"+"Location-Contact: "+Locationcontact+"\n"+"Verified at: "+Verifiedat+"\n"+"To-contact: "+Tocontact+"\n";
                s=s+"\n";
            }
            message.setText(s);
          //  message.setText("Ruk Jao Sir!"+update.getMessage().getFrom().getFirstName());
        }

        if(commands.equals("/oxygendelhi"))
        {
            //  System.out.println();
            System.out.println("/oxygendelhi -->"+update.getMessage().getFrom().getFirstName());
            HttpClient client = HttpClient.newHttpClient();
            HttpRequest request = HttpRequest.newBuilder()
                    .uri(URI.create("https://covidresourceindia.herokuapp.com/posts"))
                    .build();

            HttpResponse<String> response = null;
            try {
                response = client.send(request, HttpResponse.BodyHandlers.ofString());
            } catch (IOException ioException) {
                ioException.printStackTrace();
            } catch (InterruptedException interruptedException) {
                interruptedException.printStackTrace();
            }

            JSONArray mJsonArray = new JSONArray(response.body().toString());
            JSONObject mJsonObject = mJsonArray.getJSONObject(0);
            JSONArray mJsonArrayProperty1 = mJsonObject.getJSONArray("Oxygeninfo");
            String s="";
            s=s+"----Oxgen Availability / Refill Status----"+"\n"+"\n";
            for(int i=0;i<mJsonArrayProperty1.length();i++)
            {
                JSONObject mJsonObjectProperty = mJsonArrayProperty1.getJSONObject(i);
                String location = mJsonObjectProperty.getString("Location");
                String Serviceavailable = mJsonObjectProperty.getString("Service available");
                String Contact = mJsonObjectProperty.getString("Contact");
                String Verifiedat = mJsonObjectProperty.getString("Verified at");
                s=s+"Location: "+location+"\n"+"Service Available: "+Serviceavailable+"\n"+"Contact: "+Contact+"\n"+"Verified at: "+Verifiedat+"\n";
                s=s+"\n";
            }
            message.setText(s);
      }

        message.setChatId(String.valueOf(update.getMessage().getChatId()));
        try {
            execute(message);
        } catch (TelegramApiException e) {
            e.printStackTrace();
        }

    }
}
