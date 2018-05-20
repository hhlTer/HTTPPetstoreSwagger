package goit.client.view.dialogMaker;

import goit.client.view.dialogImplementation.CaseDialog;
import goit.client.view.dialogImplementation.StoreDialog;

public class DialogMakerStore implements DialogMaker{
    @Override
    public CaseDialog caseDialog() {
        return new StoreDialog();
    }
}
