package goit.client;

import goit.client.dialogImplementation.UserDialog;
import goit.client.dialogMaker.DialogMaker;
import goit.client.dialogMaker.DialogMakerPet;
import goit.client.dialogMaker.DialogMakerStore;
import goit.client.dialogMaker.DialogMakerUser;
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
                        answer == 's' ? new DialogMakerStore() :
                        answer == 'u' ? new DialogMakerUser() :
                        null;
                if (answer == 'e') exit = true;
                else
                {
                    boolean back = false;
                    do {
                        System.out.println("Choose command: \n" +
                                "|| <- [b]ack  ||\n" +
                                (answer == 's' ?
                                "||    [G]ET   ||  P[O]ST || [D]ELETE ||" :
                                "||    [G]ET   || [P]UT || P[O]ST || [D]ELETE ||"));
                        if (answer == 'u'){
                            System.out.println(
                                "||    [L]ogin || Logou[t]");
                        }
                        answer = DialogService.getAnswer("gpodeblt");
                        switch (answer) {
                            case 'g':
                                dialogMaker.caseDialog().getDialog();
                                break;
                            case 'b':
                                back = true;
                                break;
                            case 'o':
                                dialogMaker.caseDialog().postDialog();
                                break;
                            case 'l':
                                new UserDialog().login();
                                break;
                            case 't':
                                new UserDialog().logout();
                                break;
                        }
                    }while (!back);
                }
        } while (!exit);
        System.out.println("Bye!");
    }
}
