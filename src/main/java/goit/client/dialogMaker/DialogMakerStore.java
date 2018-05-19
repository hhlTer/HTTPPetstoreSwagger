package goit.client.dialogMaker;

import goit.client.dialogImplementation.CaseDialog;
import goit.client.dialogImplementation.StoreDialog;

public class DialogMakerStore implements DialogMaker{
    @Override
    public CaseDialog caseDialog() {
        return new StoreDialog();
    }
}
