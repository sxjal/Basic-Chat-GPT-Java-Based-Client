import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import org.json.JSONObject;

//main class of the program
public class ChatGPT {
  
    //try throw catch
    public static void chatGPT(String text) throws Exception {
      
      //url for the chat gpt API
      
      String url = "https://api.openai.com/v1/completions";
        //establishing api connection
        HttpURLConnection con = (HttpURLConnection) new URL(url).openConnection();

        con.setRequestMethod("POST");
        con.setRequestProperty("Content-Type", "application/json");
        //api authentication. 
        //api key - "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"
      
        con.setRequestProperty("Authorization", "Bearer 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");
        //specifing details on the data needs to be extracted on the based of different parameters
      
        JSONObject data = new JSONObject();
        data.put("model", "text-davinci-003");
        data.put("prompt", text);
        data.put("max_tokens", 4000);
        data.put("temperature", 1.0);

        
        con.setDoOutput(true);
        con.getOutputStream().write(data.toString().getBytes());

        String output = new BufferedReader(new InputStreamReader(con.getInputStream())).lines()
                .reduce((a, b) -> a + b).get();

        System.out.println(new JSONObject(output).getJSONArray("choices").getJSONObject(0).getString("text"));
    }

    //the main string for which the response needs to be generated.
    public static void main(String[] args) throws Exception {
        chatGPT("Hello, how are you?");
    }
}
