package goit.client.view.dialogMaker;

import goit.client.view.dialogImplementation.CaseDialog;
import goit.client.view.dialogImplementation.PetDialog;

public class DialogMakerPet implements DialogMaker {
    @Override
    public CaseDialog caseDialog() {
        CaseDialog petCaseDialog = new PetDialog();
        return petCaseDialog;
    }
}
