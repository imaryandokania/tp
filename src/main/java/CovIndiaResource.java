
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
            message.setChatId(String.valueOf(update.getMessage().getChatId()));
            try {
                execute(message);
            } catch (TelegramApiException e) {
                e.printStackTrace();
            }
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
            message.setChatId(String.valueOf(update.getMessage().getChatId()));
            try {
                execute(message);
            } catch (TelegramApiException e) {
                e.printStackTrace();
            }
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
            message.setChatId(String.valueOf(update.getMessage().getChatId()));
            try {
                execute(message);
            } catch (TelegramApiException e) {
                e.printStackTrace();
            }

          //  message.setText("Ruk Jao Sir!"+update.getMessage().getFrom().getFirstName());
        }
        if(commands.equals("/govtdelhibed")) {
            String d = "";
            try {
                String uri = "https://delhifightscorona.in/data/covid-icu-beds/";
                Document doc = Jsoup.connect(uri).get();
                Elements ty = doc.select("body > div > div > div.cell.auto > div.grid-x.grid-margin-x");
                String u=ty.text();
                String[] splitStr1 = u.split("Occ");
                String[] splitStr2 = splitStr1[1].split("Va");
                String l="";
                l=splitStr1[0]+"\n"+"Occ"+splitStr2[0]+"\n"+"Va"+splitStr2[1]+"\n";
                message.setText(l);
                message.setChatId(String.valueOf(update.getMessage().getChatId()));
                try {
                    execute(message);
                } catch (TelegramApiException e) {
                    e.printStackTrace();
                }
                Elements tables = doc.select("#beds");
                for (Element table : tables) {
                    Elements tableRows = table.select("#beds > tbody > tr");
                    for (Element tableRow : tableRows) {

                        Elements tableData = tableRow.select("td");
                        Elements tableData4 = tableRow.select("div.numbers");
                        Elements tableData5 = tableRow.select("div.numbers > a");
                        Elements tableData2 = tableRow.select("td.text-right");
                        Elements tableData1 = tableData.select("address");
                        Elements tableData3 = tableRow.select("h5");
                        String s1 = tableData1.text();
                        String[] parts = s1.split("Last");
                        String s = tableData2.text();
                        String[] splitStr = s.split("\\s+");

                        int k = Integer.parseInt(splitStr[1].trim());
                        if (k > 0)
                        {
                            d = "\n" + "Hospital:" + tableData3.text() + "\n" + "Location: " + parts[0] + "\n"+"Contact: "+tableData4.text().replace("Location","")+"\n"+"Map :"+tableData5.attr("abs:href").trim()+"\n" +"Last" + parts[1] + "\n" + "Total: " + splitStr[0] + "\n" + "Vacant: " + splitStr[1] + "\n";
                        message.setText(d);
                        message.setChatId(String.valueOf(update.getMessage().getChatId()));
                        try {
                            execute(message);
                        } catch (TelegramApiException e) {
                            e.printStackTrace();
                        }
                    }

                    }

                }

            }
            catch (Exception e) {

            }
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

            message.setChatId(String.valueOf(update.getMessage().getChatId()));
            try {
                execute(message);
            } catch (TelegramApiException e) {
                e.printStackTrace();
            }
      }
        if(commands.equals("/bengaluruhospitalstatus"))
        {
            String d="";
            try {
                String uri = "https://bbmpgov.com/chbms/";
                Document doc= Jsoup.connect(uri).get();
                Elements tables = doc.select("#GovernmentMedical");
                for(Element table : tables)
                {
                    Elements tableRows = table.select( "tbody > tr");
                    int k=1;
                    d="";
                    for(Element tableRow : tableRows)
                    {
                        Elements tableData = tableRow.select("td");
                        int i=0;
                        k=k+1;
                      //  System.out.println("K value:"+k);
                        if(k==132)
                        {
                            break;
                        }
                        for(Element p:tableData)
                        {
                            if(i==0)
                            {
                               // System.out.println("#:"+p.text());
                                d=d+"#:"+p.text()+"\n";
                            }
                            if(i==1)
                            {
                               // System.out.println("Hospital:"+p.text());
                                d=d+"Hospital:"+p.text()+"\n";
                            }
                            if(i==12)
                            {
                               // System.out.println("Gen:"+p.text());
                                d=d+"Gen:"+p.text()+"\n";
                            }
                            if(i==13)
                            {
                                //System.out.println("HDU:"+p.text());
                                d=d+"HDU:"+p.text()+"\n";
                            }
                            if(i==14)
                            {
                               // System.out.println("ICU:"+p.text());
                                d=d+"ICU:"+p.text()+"\n";
                            }
                            if(i==15)
                            {
                               // System.out.println("ICU Vent:"+p.text());
                                d=d+"ICU Vent:"+p.text()+"\n";
                            }
                            if(i==16)
                            {
                              //  System.out.println("Total:"+p.text());
                                d=d+"Total:"+p.text()+"\n";
                            }
                            i=i+1;


                        }
                        message.setText(d);
                        message.setChatId(String.valueOf(update.getMessage().getChatId()));
                        try {
                            execute(message);
                        } catch (TelegramApiException e) {
                            e.printStackTrace();
                        }

                    }

                }

            }catch (Exception e) {
                System.out.println(e);
            }
        }



    }
}
