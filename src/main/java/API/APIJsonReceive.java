package API;

import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.io.BufferedReader;
import java.io.IOException;
import com.google.gson.Gson;

// api를 통해서 json data를 수신함, data가 없으면 null을 반환함
public class APIJsonReceive {
    public String JsonCreate(int datastr, int dataend) {
        StringBuilder urlBuilder = new StringBuilder("http://openapi.seoul.go.kr:8088/");

        try {
            urlBuilder.append(URLEncoder.encode("494b797669627368343674784e4d63", "UTF-8")).append("/"); // Service Key
            urlBuilder.append(URLEncoder.encode("json", "UTF-8")).append("/"); // datatype
            urlBuilder.append(URLEncoder.encode("TbPublicWifiInfo", "UTF-8")).append("/"); // api type
            urlBuilder.append(URLEncoder.encode(String.valueOf(datastr), "UTF-8")).append("/"); // start page
            urlBuilder.append(URLEncoder.encode(String.valueOf(dataend), "UTF-8")).append("/"); // end page
            //urlBuilder.append(URLEncoder.encode("서대문구", "UTF-8")).append("/"); // 지역구명
           // urlBuilder.append(URLEncoder.encode("서소문로", "UTF-8")).append("/"); // 도로명
        } catch (UnsupportedEncodingException e) {
            throw new RuntimeException(e);
        }


        // URL and HttpURLConnection handling
        HttpURLConnection conn = null;
        String json;

        try {
            URL url = new URL(urlBuilder.toString());
            conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("GET");
            conn.setRequestProperty("Accept", "application/json");

            try (BufferedReader rd = new BufferedReader(new InputStreamReader(
                    conn.getResponseCode() >= 200 && conn.getResponseCode() <= 300 ?
                            conn.getInputStream() : conn.getErrorStream()))) {

                StringBuilder sb = new StringBuilder();
                String line;
                while ((line = rd.readLine()) != null) {
                    sb.append(line);
                }
                json = sb.toString();

            }

        } catch (IOException e) {
            throw new RuntimeException(e);
        } finally {
            if (conn != null) {
                conn.disconnect();
            }
        }
        return json;
    }

    // json의 data를 parsing후 container에 담기
    public DataContainer getDataContainer(String json) {

        // Create a Gson object
        Gson gson = new Gson();

        // Deserialize the JSON string into a WifiDataContainer object
            DataContainer datacontainer = gson.fromJson(json, DataContainer.class);



        if (datacontainer.getTbPublicWifiInfo()==null) {
            System.out.println("No rows found.");
            return null;
        }

        return datacontainer;
    }

}

