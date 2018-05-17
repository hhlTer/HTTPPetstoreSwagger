package goit.client;

import com.google.gson.Gson;
import goit.model.Category;
import goit.model.Pet;
import goit.model.Tag;
import goit.model.status.PetStatus;

import java.io.*;

public class Main {
    public static void main(String[] args) throws IOException {
        new MainDialog().getRequestMethod();
    }


    private void testJson(){
        Pet pet = new Main().fillPet();
        Gson gson = new Gson();
        String json = gson.toJson(pet);
        System.out.println(json);
//        WebClient client = new WebClient();
//        client.start();
    }
    private Pet fillPet(){
        Pet pet = new Pet();

        Category category = new Category();
        category.id = 0;
        category.name = "category string";
        pet.category = category;

        Tag[] tags = new Tag[1];
        tags[0] = getTags();
        pet.tags = tags;

        String[] photos = new String[1];
        photos[0] = "photoURI1";
        pet.photoUrls = photos;

        pet.id = 1;
        pet.name = "Butuz";

        pet.status = PetStatus.available;

        return pet;
    }

    private Tag getTags(){
        Tag tag = new Tag();
        tag.id = (int)(Math.random()*100);
        tag.name = "tag name";
        return tag;
    }
}
/*
//        WebClient client = new WebClient();
//        client.start();
        String params = URLEncoder.encode("status", "UTF-8") + "=" +
                URLEncoder.encode("available", "UTF-8");

        String hostname = "petstore.swagger.io";
        int port = 80;

        String path = "#/pet/addPet";
//        String path = "/v2/pet";
//        String param = "?status=available";
        InetAddress inetAddress = InetAddress.getByName(hostname);
        Socket socket = new Socket(inetAddress, port);
//        Socket socket = new Socket(hostname, port);

        Pet pet = new Main().fillPet();
        String petJson = new Gson().toJson(pet);
        byte[] byteJson = petJson.getBytes();
        //headers
        BufferedWriter writer = new BufferedWriter(
                new OutputStreamWriter(socket.getOutputStream(), "UTF-8"));
//        BufferedWriter writer = new BufferedWriter(
//                new BufferedWriter(new FileWriter("2.txt")));
        writer.write("POST /v2/pet HTTP/1.1\r\n");
        writer.write("Host: petstore.swagger.io\r\n");
        writer.write("Content-Length: " + byteJson.length + "\r\n");
        writer.write("api-key: special-key" + "\r\n");
        writer.write("Content-Type: application/json" + "\r\n\r\n");
        writer.write(petJson + "\r\n");
//        writer.write("Content-Type: application/x-www-form-urlencoded\r\n\r\n");

        // Send parameters
        writer.flush();

        //get response

        BufferedReader br = new BufferedReader(new InputStreamReader(socket.getInputStream()));
        String line;

        while ((line = br.readLine()) != null){
            System.out.println(line);
        }

        writer.close();
        br.close();



    }

    private Pet fillPet(){
        Pet pet = new Pet();

        Category category = new Category();
        category.id = 0;
        category.name = "category string";
        pet.category = category;

        Tag[] tags = new Tag[1];
        tags[0] = getTags();
        pet.tags = tags;

        String[] photos = new String[1];
        photos[0] = "photoURI1";
        pet.photoUrls = photos;

        pet.id = 1;
        pet.name = "Butuz";

        pet.status = PetStatus.available;

        return pet;
    }

    private Tag getTags(){
        Tag tag = new Tag();
        tag.id = (int)(Math.random()*100);
        tag.name = "tag name";
        return tag;
    }
}
*/