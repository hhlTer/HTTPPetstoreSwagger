package goit.client.dialogImplementation;

import goit.controler.util.HttpHeaders;
import goit.controler.util.HttpVersion;
import goit.controler.util.RequestCommands;
import goit.controler.web.WebClient2;
import goit.model.Entity;
import java.lang.reflect.Type;
import goit.model.GeneralEntity;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class GeneralDialog<T extends Entity> {
    static WebClient2 petWebClient2;

    static{
        try {
            petWebClient2 = new WebClient2(HttpHeaders.HOST.getDefaultValue(), 80);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
//    private static Socket socket = WebClient.getInstance().getSocket();
    protected Map<String, String> headers = new HashMap<>();

    GeneralDialog(){
        headers.put(HttpHeaders.HOST.getName(), HttpHeaders.HOST.getDefaultValue());
        headers.put(HttpHeaders.ACCEPT.getName(), HttpHeaders.ACCEPT.getDefaultValue());
        headers.put(HttpHeaders.ACCEPT_LANGUAGE.getName(), HttpHeaders.ACCEPT_LANGUAGE.getDefaultValue());
        headers.put(HttpHeaders.ACCEPT_ENCODING.getName(), HttpHeaders.ACCEPT_ENCODING.getDefaultValue());
        headers.put(HttpHeaders.REFERER.getName(), HttpHeaders.REFERER.getDefaultValue());
        headers.put(HttpHeaders.CONNECTION.getName(), HttpHeaders.CONNECTION.getDefaultValue());
    }

    T getEntityByPath(String path, Class clazz){
        StringBuilder sb = new StringBuilder();
        headers.put("startline", startGenerate(RequestCommands.GET, path, HttpVersion.HTTP_1_1));
        T t = (T) petWebClient2.GET(headers, clazz);
        return t;
    }

    List<T> getListEntity(String path, Type clazz){
        String startline = startGenerate(RequestCommands.GET, path, HttpVersion.HTTP_1_1);
        headers.put("startline", startline);
        List<T> ts = (List<T>)petWebClient2.GETList(headers, clazz);
        return ts;
    }

    String getStringResult(String path){
        String startline = startGenerate(RequestCommands.GET, path, HttpVersion.HTTP_1_1);
        headers.put("startline", startline);
        try {
            return petWebClient2.getResponceString(headers);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    String postEntity(T t){
        String startLine = startGenerate(RequestCommands.POST, t.getPatch(), HttpVersion.HTTP_1_1);
        headers.put("startline", startLine);
        return petWebClient2.POST(headers, t);
    }

    private String startGenerate(RequestCommands commands, String path, HttpVersion version){
        StringBuilder sb = new StringBuilder();
        sb.append(commands.name()).append(" ").append(path).append(" ").append(version.getName());
        return String.valueOf(sb);
    }
}
