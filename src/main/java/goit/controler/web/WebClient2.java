package goit.controler.web;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import goit.controler.util.HttpHeaders;
import goit.controler.util.HttpVersion;
import goit.model.Entity;

import java.io.*;
import java.lang.reflect.Type;
import java.net.*;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class WebClient2<T extends Entity> implements Request<T>{
    private Socket socket;
    private String host;
    private InetAddress ipHost;
    private int port;
    private HttpHeaders httpHeaders;
    private Map<String, String> headers;

    public WebClient2(){
    }
    public WebClient2(String hostname, int port) throws UnknownHostException, IOException {
        initSocket(hostname, port);
//        initHeaders();
    }

    public void setPort(int port) {
        this.port = port;
    }
    public void setHost(String host) {
        this.host = host;
    }
    public void setIpHost(InetAddress ipHost) {
        this.ipHost = ipHost;
    }
    public void setSocket(Socket socket) {
        this.socket = socket;
    }

    public Socket getSocket(){
        return this.socket;
    }

    public void initSocket(String hostname, int port) throws IOException{
        this.host = hostname;
        this.ipHost = InetAddress.getByName(hostname);
        socket = new Socket(ipHost, port);
    }

    private void initHeaders(){
        headers = new HashMap<>();
        headers.put(HttpHeaders.HOST.getName(), host);
        headers.put(HttpHeaders.ACCEPT.getName(), HttpHeaders.ACCEPT.getDefaultValue());
        headers.put(HttpHeaders.ACCEPT_LANGUAGE.getName(), HttpHeaders.ACCEPT_LANGUAGE.getDefaultValue());
        headers.put(HttpHeaders.ACCEPT_ENCODING.getName(), HttpHeaders.ACCEPT_ENCODING.getDefaultValue());
        headers.put(HttpHeaders.CONNECTION.getName(), HttpHeaders.CONNECTION.getDefaultValue());
    }

    @Override
    public T getById(int id) {
        return null;
    }

//    @Override
//    public List<T> getList(String path, Type clazz) {
//        StringBuilder sb = new StringBuilder();
//        sb.append("GET ").append(path).append(" ").append(HttpVersion.HTTP_1_1.getName());
//        headers.put("startline", String.valueOf(sb));
//
//        try {
//            String toJson = getResponseResult();
////            System.out.println(toJson.substring(toJson.indexOf("\r\n\r\n")+3));
//            String json = toJson.substring(toJson.indexOf("\r\n\r\n")+4);
//            Gson gson = new Gson();
////            clazz = new TypeToken<List<Pet>>(){}.getType();
////            json = "[{\"id\":4375893,\"category\":{\"id\":0,\"name\":\"string\"},\"name\":\"barack\",\"photoUrls\":[\"string\"],\"tags\":[{\"id\":0,\"name\":\"string\"}],\"status\":\"available\"},{\"id\":898988992,\"category\":{\"id\":0,\"name\":\"string\"},\"name\":\"doggie\",\"photoUrls\":[\"string\"],\"tags\":[{\"id\":0,\"name\":\"string\"}],\"status\":\"available\"}]\n";
//            List<T> list = gson.fromJson(json, clazz);
//            return list;
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//        return null;
//    }

    @Override
    public T GET(Map<String, String> headers, Class<T> clazz) {
        try {
            this.headers = headers;
            String toJson = getResponseResult();
            System.out.println(toJson);
            String json = toJson.substring(toJson.indexOf("\r\n\r\n")+3);
            return new Gson().fromJson(json, (Class<T>) clazz);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

//    @Override
//    public T getByPath(String path, Class clazz) throws IOException {
//        headers = new HashMap<>();
//
//        StringBuilder sb = new StringBuilder();
//        sb.append("GET ").append(path).append(" ").append(HttpVersion.HTTP_1_1.getName());
//
//        headers.put("startline", String.valueOf(sb));
//        headers.put(HttpHeaders.HOST.getName(), host);
//        headers.put(HttpHeaders.ACCEPT.getName(), HttpHeaders.ACCEPT.getDefaultValue());
//        headers.put(HttpHeaders.ACCEPT_LANGUAGE.getName(), HttpHeaders.ACCEPT_LANGUAGE.getDefaultValue());
//        headers.put(HttpHeaders.ACCEPT_ENCODING.getName(), HttpHeaders.ACCEPT_ENCODING.getDefaultValue());
//        headers.put(HttpHeaders.CONNECTION.getName(), HttpHeaders.CONNECTION.getDefaultValue());
//
//        return (T) GET(headers, clazz);
//    }

    public List<T> GETList(Map<String, String> headers, Type clazz) {
        try {
            this.headers = headers;
            String toJson = getResponseResult();
            System.out.println(toJson);
            String json = toJson.substring(toJson.indexOf("\r\n\r\n")+3);
            return new Gson().fromJson(json, clazz);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public void POST(Entity entity) {

    }

    @Override
    public void PUT(Entity entity) {

    }

    @Override
    public void DELETE(Entity entity) {

    }

    private String getResponseResult() throws IOException {
        BufferedWriter writer = new BufferedWriter(
                new OutputStreamWriter(socket.getOutputStream())
        );
//        BufferedWriter writer = new BufferedWriter(new FileWriter("3.txt"));
        writer.write(headers.remove("startline") + "\r\n");
        for (Map.Entry<String, String> e:
             headers.entrySet()) {
            System.out.print(e.getKey() + ": " + e.getValue() + "\r\n");
            writer.write(e.getKey() + ": " + e.getValue() + "\r\n");
        }
        writer.write("\r\n");
        writer.flush();


        BufferedReader reader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
        StringBuilder sb = new StringBuilder();
        String line;
        while ((line = reader.readLine()) != null){
            sb.append(line).append("\r\n");
        }
        return String.valueOf(sb);
    }

}
