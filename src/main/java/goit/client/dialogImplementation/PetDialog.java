package goit.client.dialogImplementation;

import com.google.gson.reflect.TypeToken;
import goit.client.service.DialogService;
import goit.controler.util.HttpHeaders;
import goit.controler.web.WebClient2;
import goit.model.Entity;
import goit.model.Pet;
import goit.model.status.PetStatus;

import java.io.IOException;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;

public class PetDialog<T extends Entity> extends GeneralDialog<Pet> implements CaseDialog{

    @Override
    public void getDialog() {
        int id = -1;
        PetStatus petStatus;
        Pet pet;
        Pet[] pets;
        String path = null;
        System.out.println(
                "      || by [i]d" +
                "      || by [s]tatus");
        char answer = DialogService.getAnswer("isb");
        switch (answer){
            case 'i':
                id = (int)DialogService.getLongId();
                path = "/v2/pet/" + id;
                try {
                    petWebClient2 = new WebClient2<>(HttpHeaders.HOST.getDefaultValue(), 80);
//                    pet = (Pet)petWebClient2.getByPath(path, Pet.class);
                    pet = getEntityByPath(path, Pet.class);
                    System.out.println(pet);
                } catch (IOException e) {
                    e.printStackTrace();
                }
                break;
            case 's':
                System.out.println("Choose status:\n" +
                        "[A]vailable, [P]ending, [S]old");
                char c = DialogService.getAnswer("aps");
                petStatus =
                        c == 'a' ? PetStatus.available :
                        c == 'p' ? PetStatus.pending :
                                PetStatus.sold;
                try {
                    path = "/v2/pet/findByStatus?status=" + petStatus.name();
                    petWebClient2 = new WebClient2<>(HttpHeaders.HOST.getDefaultValue(), 80);
                    List<Pet> petList = getListEntity(path, new TypeToken<List<Pet>>(){}.getType());
                    petList.stream()
                            .sorted(Comparator.comparingLong(p -> p.id))
                            .forEach(System.out::println);

                } catch (IOException e) {
                    e.printStackTrace();
                }
                break;
            case 'b':
                return;
        }


    }

}
