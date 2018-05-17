package goit.controler.commands;

import goit.model.Entity;
import goit.model.Pet;

import java.io.IOException;
import java.net.Socket;

public interface Request<T extends Entity> {
    T getById(int id);
    T getByPath(String patch, Class<T> tClass) throws IOException;
    void POST(T t);
    void PUT(T t);
    void DELETE(T t);
}
