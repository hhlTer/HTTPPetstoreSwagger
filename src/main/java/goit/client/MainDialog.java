package goit.client;

import goit.client.dialogMaker.DialogMaker;
import goit.client.dialogMaker.DialogMakerPet;
import goit.client.service.DialogService;
import goit.client.dialogImplementation.CaseDialog;

public class MainDialog {
    private boolean exit = false;
    CaseDialog caseDialog;
    DialogMaker dialogMaker;

    public void getRequestMethod(){
        do {
            System.out.println("Choose entity");
            System.out.println("[P]et, [S]tore, [U]ser, [E]xit");
            char answer = DialogService.getAnswer("psu");
                dialogMaker =
                        answer == 'p' ? new DialogMakerPet() :
                        answer == 's' ? new DialogMakerPet() :
                        answer == 'u' ? new DialogMakerPet() :
                        null;
                if (answer == 'e') exit = true;
                else
                {
                    boolean back = false;
                    do {
                        System.out.println("Choose command: \n" +
                                "|| <- [b]ack ||\n" +
                                "||    [G]ET  || [P]UT || P[O]ST || [D]ELETE ||");
                        answer = DialogService.getAnswer("gpodeb");
                        switch (answer) {
                            case 'g':
                                dialogMaker.caseDialog().getDialog();
                                break;
                            case 'b':
                                back = true;
                        }
                    }while (!back);
                }
        } while (!exit);
        System.out.println("Bye!");
    }
}
