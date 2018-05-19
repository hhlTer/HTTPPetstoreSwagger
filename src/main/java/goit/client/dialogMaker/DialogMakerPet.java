package goit.client.dialogMaker;

import goit.client.dialogImplementation.CaseDialog;
import goit.client.dialogImplementation.PetDialog;
import goit.model.Pet;

public class DialogMakerPet implements DialogMaker {
    @Override
    public CaseDialog caseDialog() {
        CaseDialog petCaseDialog = new PetDialog();
        return petCaseDialog;
    }
}
