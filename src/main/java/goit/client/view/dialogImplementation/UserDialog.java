package goit.client.view.dialogImplementation;

import goit.client.view.service.DialogService;
import goit.client.model.User;

import java.util.Scanner;

public class UserDialog extends GeneralDialog<User> implements CaseDialog{
    private User user = new User();

    @Override
    public void getDialog() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter name");
        String name = scanner.nextLine();
        user = new User();
        String path = user.getPatch() + "/" + name;
        user = getEntityByPath(path, User.class);
        System.out.println(user);
        scanner.close();
    }

    @Override
    public void postDialog() {
        user = new User();
        user.username = "Volodymyr";

        System.out.println("Create [o]ne user, or [l]ist of users?");
        String result;
        char answer = DialogService.getAnswer("ol");
        if (answer == 'o') {

            System.out.println("Create new user with name Volodymyr...");
            result = postEntity(user);
        }else {
            User user1 = new User();
            user1.username = "Dmytro";
            System.out.println("Create new users with names Volodymyr, Dmytro...");
            User[] users = new User[]{
                    user,
                    user1
            };
            result = postEntity(users);
        }
        DialogService.printSlip("Result: ", 288);
        System.out.println(result);
    }

    @Override
    public void putDialog() {
        User user = new User();
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter username :> ");
        user.username = scanner.nextLine();
        System.out.println("Change user with name "+ user.username+"...");
        user.setPath(user.getPatch() + "/" + user.username);
        String result = putEntity(user);
        DialogService.printSlip("Result: ", 288);
        System.out.println(result);

    }

    @Override
    public void deleteDialog() {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter api key :> ");
        String apiKey = scanner.nextLine();
        System.out.print("\nEnter user name :> ");
        String name = scanner.nextLine();
        String path = new User().getPatch() + "/" + name;
        String result = deleteEntity(path, apiKey);
        DialogService.printSlip("Result: ", 288);
        System.out.println(result);
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
