package goit.client.dialogMaker;

import goit.client.dialogImplementation.CaseDialog;
import goit.client.dialogImplementation.UserDialog;

public class DialogMakerUser implements DialogMaker{

    @Override
    public CaseDialog caseDialog() {
        return new UserDialog();
    }
}
