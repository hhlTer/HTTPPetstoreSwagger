package goit.client.view.dialogMaker;

import goit.client.view.dialogImplementation.CaseDialog;
import goit.client.view.dialogImplementation.UserDialog;

public class DialogMakerUser implements DialogMaker{

    @Override
    public CaseDialog caseDialog() {
        return new UserDialog();
    }
}
