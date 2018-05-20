package goit.client.dialogImplementation;

import goit.client.service.DialogService;
import goit.model.User;

import java.util.Scanner;

public class UserDialog extends GeneralDialog<User> implements CaseDialog{
    User user = new User();

    @Override
    public void getDialog() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter name");
        String name = scanner.nextLine();
        user = new User();
        String path = user.getPatch() + "/" + name;
        user = (User)getEntityByPath(path, User.class);
        System.out.println(user);
        scanner.close();
    }

    @Override
    public void postDialog() {
        User user = new User();
        user.username = "Volodymyr";
        System.out.println("Create new user with name Volodymyr...");
        String result = postEntity(user);
        DialogService.printSlip("Result: ", 288);
    }

    public void login(){
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter name");
        String name = scanner.nextLine();
        System.out.println("Enter password");
        String pass = scanner.nextLine();

        String ans = getStringResult(user.getPatch() + "/login?username=" + name + "&password=" + pass);
        System.out.println(ans);
    }

    public void logout(){
        String ans = getStringResult(user.getPatch() + "/logout");
        System.out.println(ans);
    }
}
