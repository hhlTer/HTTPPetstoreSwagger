package goit.controler.web;

import goit.model.Entity;
import goit.model.Pet;

import java.io.IOException;
import java.lang.reflect.Type;
import java.net.Socket;
import java.util.List;
import java.util.Map;

public interface Request<T> {
    T getById(int id);
//    List<T> getList(String patch, Type tClass);
//    T getByPath(String patch, Class<T> tClass) throws IOException;
    T GET(Map<String, String> headers, Class<T> tClass);
    List<T> GETList(Map<String, String> headers, Type tClass);
    String POST(Map<String, String> headers, T t);
    void PUT(Map<String, String> headers, T t);
    void DELETE(T t);
}
