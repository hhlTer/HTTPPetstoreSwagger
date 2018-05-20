package goit.client.view.dialogImplementation;

import com.google.gson.reflect.TypeToken;
import goit.client.view.service.DialogService;
import goit.client.model.Category;
import goit.client.model.Entity;
import goit.client.model.Pet;
import goit.client.model.Tag;
import goit.client.model.status.PetStatus;

import java.util.Comparator;
import java.util.List;
import java.util.Scanner;

public class PetDialog<T extends Entity> extends GeneralDialog<Pet> implements CaseDialog{

    @Override
    public void getDialog() {
        int id;
        PetStatus petStatus;
        Pet pet;
        String path;
        System.out.println(
                "GET by [i]d\n    by [s]tatus");
        char answer = DialogService.getAnswer("isb");
        switch (answer){
            case 'i':
                id = (int)DialogService.getLongId();
                path = "/v2/pet/" + id;
                    pet = getEntityByPath(path, Pet.class);
                    System.out.println(pet);
                break;
            case 's':
                System.out.println("Choose status:\n" +
                        "[A]vailable, [P]ending, [S]old");
                char c = DialogService.getAnswer("aps");
                petStatus =
                        c == 'a' ? PetStatus.available :
                        c == 'p' ? PetStatus.pending :
                                PetStatus.sold;
                    path = "/v2/pet/findByStatus?status=" + petStatus.name();
                    List<Pet> petList = getListEntity(path, new TypeToken<List<Pet>>(){}.getType());
                    petList.stream()
                            .sorted(Comparator.comparingLong(p -> p.id))
                            .forEach(System.out::println);
                break;
            case 'b':
                return;
        }

    }

    @Override
    public void postDialog() {
        System.out.println("Fill the pet");
        try {
            Thread.sleep(200);
            System.out.print("Autofill");
            for (int i = 0; i < 10; i++) {
                System.out.print('.');
                Thread.sleep(100);
            }
            System.out.println();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        Pet pet = autofill();
        String s = postEntity(pet);

        DialogService.printSlip("Answer: ", 288);
        System.out.println(s);
    }

    @Override
    public void putDialog(){
        DialogService.printSlip("Update pet with name 'Spike'", 288);
        Pet pet = autofill();
        pet.name = "Spike";
        String s = putEntity(pet);
        DialogService.printSlip("Answer: ", 288);
        System.out.println(s);
    }

    @Override
    public void deleteDialog() {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter Api-Key :> ");
        String apiKey = scanner.nextLine();

        System.out.println();
        long id = DialogService.getLongId();
        String path = new Pet().getPatch() + "/" + id;
        System.out.println();
        String s = deleteEntity(path, apiKey);
        DialogService.printSlip("Answer: ", 288);
        System.out.println(s);
    }


    private Pet autofill(){
        Pet pet = new Pet();
        pet.id = (long)(Math.random() * 1000);
        pet.name = "Volt";
        pet.status = PetStatus.values()[(int) (Math.random() * 2)];

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

        return pet;
    }

    private Tag getTags(){
        Tag tag = new Tag();
        tag.id = (int)(Math.random()*100);
        tag.name = "tag name";
        return tag;
    }
}
