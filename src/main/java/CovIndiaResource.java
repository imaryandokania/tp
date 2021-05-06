
import org.json.JSONArray;
import org.json.JSONObject;
import org.jsoup.Jsoup;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import java.net.http.HttpResponse;

import java.io.IOException;
import java.net.URI;

import java.net.http.HttpClient;
import java.net.http.HttpRequest;


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
            message.setText("Welcome! "+update.getMessage().getFrom().getFirstName()+" We are here to help people by providing details about Beds , Oxygen , Remdesivir etc");
        }
        if(commands.equals("/plasmadelhi"))
        {
            System.out.println("/plasmadelhi  --->"+update.getMessage().getFrom().getFirstName());
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
            JSONArray mJsonArrayProperty1 = mJsonObject.getJSONArray("plasma");
            String s="";
            s=s+"-----Plasma Donor Status-----"+"\n"+"\n";
            for(int i=0;i<mJsonArrayProperty1.length();i++)
            {
                JSONObject mJsonObjectProperty = mJsonArrayProperty1.getJSONObject(i);
                String Bloodgroup = mJsonObjectProperty.getString("BloodGroupAvailable");
                String contact = mJsonObjectProperty.getString("Contact");
                String Verifiedat = mJsonObjectProperty.getString("Verified at");
                s=s+"BloodGroupAvailable: "+Bloodgroup+"\n"+"Contact: "+contact+"\n"+"Verified at: "+Verifiedat+"\n";
                s=s+"\n";
            }
            message.setText(s);
        }
        if(commands.equals("/medicinedelhi"))
        {
            System.out.println("/medicinedelhi  --->"+update.getMessage().getFrom().getFirstName());
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
            JSONArray mJsonArrayProperty1 = mJsonObject.getJSONArray("Remdesivir & Tocilizumab");
            String s="";
            s=s+"-----Remdesivir & Tocilizumab Status-----"+"\n"+"\n";
            for(int i=0;i<mJsonArrayProperty1.length();i++)
            {
                JSONObject mJsonObjectProperty = mJsonArrayProperty1.getJSONObject(i);
                String location = mJsonObjectProperty.getString("Location");
                String contact = mJsonObjectProperty.getString("Contact");
                String Verifiedat = mJsonObjectProperty.getString("Verified at");
                s=s+"Locatiion: "+location+"\n"+"Contact: "+contact+"\n"+"Verified at: "+Verifiedat+"\n";
                s=s+"\n";
            }
            message.setText(s);
        }


        if(commands.equals("/delhibeds"))
        {

//            System.out.println("/delhibeds  --->"+update.getMessage().getFrom().getFirstName());
//            HttpClient client = HttpClient.newHttpClient();
//            HttpRequest request = HttpRequest.newBuilder()
//                    .uri(URI.create("https://covidresourceindia.herokuapp.com/posts"))
//                    .build();
//
//            HttpResponse<String> response = null;
//            try {
//                response = client.send(request, HttpResponse.BodyHandlers.ofString());
//            } catch (IOException ioException) {
//                ioException.printStackTrace();
//            } catch (InterruptedException interruptedException) {
//                interruptedException.printStackTrace();
//            }
//            //   System.out.println(response.body());
//
//            JSONArray mJsonArray = new JSONArray(response.body().toString());
//            JSONObject mJsonObject = mJsonArray.getJSONObject(0);
//            JSONArray mJsonArrayProperty1 = mJsonObject.getJSONArray("beds");
//            String s="";
//            s=s+"-----Delhi Bed Status-----"+"\n"+"\n";
//            for(int i=0;i<mJsonArrayProperty1.length();i++)
//            {
//                JSONObject mJsonObjectProperty = mJsonArrayProperty1.getJSONObject(i);
//                String location = mJsonObjectProperty.getString("Location");
//                String Description = mJsonObjectProperty.getString("Description");
//                String Locationcontact = mJsonObjectProperty.getString("Location-contact");
//                String Verifiedat = mJsonObjectProperty.getString("Verified at");
//                String Tocontact = mJsonObjectProperty.getString("Tocontact");
//                s=s+"Location: "+location+"\n"+"Description: "+Description+"\n"+"Location-Contact: "+Locationcontact+"\n"+"Verified at: "+Verifiedat+"\n"+"To-contact: "+Tocontact+"\n";
//                s=s+"\n";
//            }
//            message.setText(s);
            String d = "";
            try {
                //String uri = "https://studyhub.vinnovateit.com/courses/603f82d34b48f40004358e53";
                String uri = "https://studyhub.vinnovateit.com/courses/603f82d34b48f40004358e53";
                Document doc= Jsoup.connect(uri).get();
                Elements data = doc.select("div.note");
                Elements p = data.select("p");
                for (Element x : p) {
                    d += d + x.text();
                    if (!x.text().equals("")) {
                      //  t.setText(t.getText() + x.text().trim());
                    }
                  //  t.setText(t.getText() + "\n" + x.select("a").attr("abs:href").trim() + "\n");
                }

            }catch (Exception e) {
                e.printStackTrace();
            }
            message.setText(d);
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
