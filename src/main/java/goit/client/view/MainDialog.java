package goit.client.view;

import goit.client.view.dialogImplementation.UserDialog;
import goit.client.view.dialogMaker.DialogMaker;
import goit.client.view.dialogMaker.DialogMakerPet;
import goit.client.view.dialogMaker.DialogMakerStore;
import goit.client.view.dialogMaker.DialogMakerUser;
import goit.client.view.service.DialogService;

class MainDialog {
    private boolean exit = false;
    private DialogMaker dialogMaker;

    void getRequestMethod(){
        do {
            System.out.println("Choose entity");
            System.out.println("[P]et, [S]tore, [U]ser, [E]xit");
            char answer = DialogService.getAnswer("psue");
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
                        char answer2 = DialogService.getAnswer("gpodeblt");
                        switch (answer2) {
                            case 'g':
                                dialogMaker.caseDialog().getDialog();
                                break;
                            case 'b':
                                back = true;
                                break;
                            case 'o':
                                dialogMaker.caseDialog().postDialog();
                                break;
                            case 'd':
                                dialogMaker.caseDialog().deleteDialog();
                                break;
                            case 'l':
                                new UserDialog().login();
                                break;
                            case 't':
                                new UserDialog().logout();
                                break;
                            case 'p':
                                dialogMaker.caseDialog().putDialog();
                                break;
                        }
                    }while (!back);
                }
        } while (!exit);
        System.out.println("Bye!");
    }
}
