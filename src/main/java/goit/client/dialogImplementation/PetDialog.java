package goit.client.dialogImplementation;

import goit.client.service.DialogService;
import goit.controler.util.HttpHeaders;
import goit.controler.web.WebClient2;
import goit.model.Entity;
import goit.model.Pet;
import goit.model.status.PetStatus;

import java.io.IOException;

public class PetDialog<T extends Entity> extends GeneralDialog implements CaseDialog{
    WebClient2<Pet> petWebClient2;

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
                    pet = (Pet)petWebClient2.getByPath(path, Pet.class);
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
                    path = "/v2/pet/findByStatus?status=" + c;
                    petWebClient2 = new WebClient2<>(HttpHeaders.HOST.getDefaultValue(), 80);
                    pet = (Pet)petWebClient2.getByPath(path, Pet.class);
                    System.out.println(pet);
                } catch (IOException e) {
                    e.printStackTrace();
                }
//                pet = (Pet)getEntityByStatus(Pet.class, petStatus.name());
//                System.out.println(pet.toString());
                break;
            case 'b':
                return;
        }


    }

}
