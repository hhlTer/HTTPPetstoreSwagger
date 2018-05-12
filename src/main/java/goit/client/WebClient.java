package goit.client;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.*;

public class WebClient {
    public void start() throws IOException{

        String url = "http://google.com/";
//        String url = "http://petstore.swagger.io/";
        URL urlObj = new URL(url);

        HttpURLConnection connection = (HttpURLConnection) urlObj.openConnection();

        connection.setRequestMethod("GET");

        int responceCode = connection.getResponseCode();

        BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getInputStream()));

        String inputLine;

        StringBuilder sb = new StringBuilder();
        while ((inputLine = reader.readLine()) != null){
            sb.append(inputLine);
        }
        reader.close();
        System.out.println(sb);

//        Socket socket = new Socket("petstore.swagger.io", 80);
//
//        System.out.println(socket.getInetAddress().getHostAddress());
//        System.out.println(socket.getLocalPort());
//
//        StringBuilder sb = new StringBuilder();
//
//        sb.append(socket.getInetAddress().getHostAddress())
//                .append(socket.getLocalPort());
//
//        socket.getOutputStream().write(sb.toString().getBytes());
//
//        byte[] buf = new byte[64*1024];
//        int r = socket.getInputStream().read(buf);
//        String data = new String(buf, 0, r);
//        System.out.println(data);

    }
}
