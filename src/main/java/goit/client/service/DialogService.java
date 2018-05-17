package goit.client.service;

import java.util.Scanner;

public class DialogService {
    public static char getAnswer(String crud) {
        System.out.println(":>");
        Scanner sc = new Scanner(System.in);
        String answer = sc.nextLine();
        char c = ' ';
        if (answer.length() > 0) {
            c = answer.toLowerCase().charAt(0);
        }
        if (!crud.contains("" + c))
            getAnswer(crud);
        return c;
    }

    public static long getLongId() {
        try {
            Thread.sleep(1000);
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
}
