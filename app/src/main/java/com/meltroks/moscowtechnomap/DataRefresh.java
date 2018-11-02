package com.meltroks.moscowtechnomap;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

public class DataRefresh {

    public String result = null;
    public String textResult = null;

    public void sendGet() throws IOException {
        String url = "https://api.vk.com/method/wall.search?domain=testing11mi3&query=События&owners_only=0&access_token=faefcc8d16559eba98335ceb6c2bb9cf29e5c3ad26d092b7ccb2f4dc2e19f318a95fd4342348ef61bed1a&count=1&v=5.58";
        // faefcc8d16559eba98335ceb6c2bb9cf29e5c3ad26d092b7ccb2f4dc2e19f318a95fd4342348ef61bed1a
        URL obj;
        {
            try {
                obj = new URL(url);
            } catch (MalformedURLException e) {
                e.printStackTrace();
                obj = null;
            }
        }

        HttpURLConnection connection = (HttpURLConnection) obj.openConnection();
        connection.setRequestMethod("GET");
        BufferedReader in;
        {
            try {
                in = new BufferedReader(new InputStreamReader((connection.getInputStream())));
            } catch (IOException e) {
                e.printStackTrace();
                in = null;
            }
        }

        String inputLine;
        StringBuffer response = new StringBuffer();

        while ((inputLine = in.readLine()) != null) {
            response.append(inputLine);
        }
        in.close();

        result = response.toString();
    }

    public void dataRefill(){
        if(result != null) {
            String vkText = "0";
            JSONObject vkJson;

            try {
                vkJson = new JSONObject(result);
            } catch (JSONException e) {
                e.printStackTrace();
                vkJson = null;
            }

            try {

                vkText = vkJson.getString("response");
                vkJson = new JSONObject(vkText);
                vkText = vkJson.getString("items");
                JSONArray vkArray = new JSONArray(vkText);
                JSONObject vkFinal = vkArray.getJSONObject(0);
                vkText = vkFinal.toString();
                vkFinal = new JSONObject(vkText);
                vkText = vkFinal.getString("text");

            } catch (JSONException e) {
                e.printStackTrace();
            }

            if(vkText != null) textResult = vkText;
        }
    }
}
