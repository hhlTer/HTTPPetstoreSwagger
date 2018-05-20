package goit.controler.web;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import goit.client.service.DialogService;
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
    private static Socket socket;
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
            System.out.println(toJson);                                   ///DEBUG
            String json = toJson.substring(toJson.indexOf("\r\n\r\n"));
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
    public String POST(Map<String, String> headers, T entity) {
        this.headers = headers;
        String body = new Gson().toJson(entity);
        headers.put("body", body);
        headers.put(HttpHeaders.CONTENT_LENGTH.getName(), String.valueOf(body.getBytes().length));
        headers.put(HttpHeaders.CONTENT_TYPE.getName(), HttpHeaders.CONTENT_TYPE.getDefaultValue());
        try {
            String result = getResponseResult();
            headers.remove(HttpHeaders.CONTENT_LENGTH.getName());
            headers.remove(HttpHeaders.CONTENT_TYPE.getName());
            return result;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return "";
    }

    @Override
    public void PUT(Map<String, String> headers, T entity) {
        this.headers = headers;

    }

    @Override
    public void DELETE(Entity entity) {

    }

    public String getResponceString(Map<String, String> headers) throws IOException{
        this.headers = headers;
        return getResponseResult();
    }

    private String getResponseResult() throws IOException {

        initSocket(HttpHeaders.HOST.getDefaultValue(), 80);
        BufferedWriter writer = null;
        writer = new BufferedWriter(
                new OutputStreamWriter(socket.getOutputStream())
        );
        DialogService.printSlip("response: ", 288);
        System.out.println(headers.get("startline"));

        String body = null;
        if (headers.containsKey("body")) {
            body = headers.remove("body");
        }

//        writer = new BufferedWriter(new FileWriter("3.txt"));
        writer.write(headers.remove("startline") + "\r\n");
        for (Map.Entry<String, String> e:
             headers.entrySet()) {
            System.out.print(e.getKey() + ": " + e.getValue() + "\r\n");
            writer.write(e.getKey() + ": " + e.getValue() + "\r\n");
        }
        writer.write("\r\n");
        if (null != body){
            writer.write(body + "\r\n");
        }
        System.out.println();  //--------------------------_DEBUG POINT
        writer.flush();


        BufferedReader reader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
        StringBuilder sb = new StringBuilder();
        String line;
        while ((line = reader.readLine()) != null){
            sb.append(line).append("\r\n");
        }
        String result = String.valueOf(sb);
        writer.close();
        socket.close();
        return result;
    }

}

//if (headers.containsKey(HttpHeaders.CONTENT_LENGTH)){
//        writer.write(HttpHeaders.CONTENT_LENGTH.getName() + ": " +
//        headers.remove(HttpHeaders.CONTENT_LENGTH.getName()) + "\r\n");
//        }
//
//        if (headers.containsKey("body")) {
//        writer.write("\r\n");
//        writer.write(headers.remove("body"));
//        }
//
//
////        BufferedWriter writer = new BufferedWriter(new FileWriter("3.txt"));
//        writer.write(headers.remove("startline") + "\r\n");
//        for (Map.Entry<String, String> e:
//        headers.entrySet()) {
//        System.out.print(e.getKey() + ": " + e.getValue() + "\r\n");
//        writer.write(e.getKey() + ": " + e.getValue() + "\r\n");
//        }
//
//
//        writer.write("\r\n");
//        writer.flush();
//
//
//        BufferedReader reader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
//        StringBuilder sb = new StringBuilder();
//        String line;
//        while ((line = reader.readLine()) != null){
//        sb.append(line).append("\r\n");
//        }
//        String result = String.valueOf(sb);
//        socket.close();
//        return result;
//        }
