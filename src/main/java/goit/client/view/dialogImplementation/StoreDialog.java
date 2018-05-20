package goit.client.view.dialogImplementation;


import goit.client.view.service.DialogService;
import goit.client.model.Order;

import java.util.Scanner;

public class StoreDialog extends GeneralDialog<Order> implements CaseDialog{

    @Override
    public void getDialog() {
        System.out.println("Enter order id");
        long id = DialogService.getLongId();
        Order order = new Order();
        String path = order.getPatch() + "/" + id;

        order = getEntityByPath(path, Order.class);
        System.out.println(order);
    }

    @Override
    public void postDialog() {
        Order order = new Order();
        System.out.println("Post order into store...");
        String rezult = postEntity(order);
        DialogService.printSlip("Result: ", 288);
        System.out.println(rezult);
    }

    @Override
    public void putDialog() {
    }

    @Override
    public void deleteDialog() {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter api key :> ");
        String apiKey = scanner.nextLine();
        long id = DialogService.getLongId();
        String path = new Order().getPatch() + "/" + id;
        String result = deleteEntity(path, apiKey);
        DialogService.printSlip("Result: ", 288);
        System.out.println(result);
    }
}
