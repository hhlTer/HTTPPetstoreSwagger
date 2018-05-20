package goit.client.dialogImplementation;


import goit.client.service.DialogService;
import goit.model.Order;

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
    }
}
