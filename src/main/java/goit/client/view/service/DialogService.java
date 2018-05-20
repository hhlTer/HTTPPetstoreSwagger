package goit.client.view.service;

import java.util.Scanner;

public class DialogService {
    public static char getAnswer(String crud) {
        System.out.print(":>");
        Scanner sc = new Scanner(System.in);
        String answer = sc.nextLine();
        char c = ' ';
        if (answer.length() > 0) {
            c = answer.toLowerCase().charAt(0);
        }
        if (crud.contains("" + c)){
            return c;
        }
         return getAnswer(crud);
    }

    public static long getLongId() {
        System.out.print("Enter id ");
        try {
            Thread.sleep(200);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.print(":>");
        Scanner sc = new Scanner(System.in);
        long id = 0;
        try {
            id = sc.nextLong();
            return id;
        } catch (Exception e) {
            System.out.println("Wrong input format");
            getLongId();
        }
        return id;
    }

    public static void printSlip(String text, long ms){
        System.out.println(text);
        try {
            Thread.sleep(ms);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
