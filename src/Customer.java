import java.util.ArrayList;
import java.util.HashMap;

public class Customer {

    private static double wallet = 0;
    private static ArrayList<HashMap<Cheese, Integer>> orders = new ArrayList<>();

    public static double getWallet() {
        return wallet;
    }

    public static void addFunds(int money) {
        Customer.wallet += money;
    }

    public static void reduceFunds(double money) {
        Customer.wallet -= money;
    }

    public static void addCartToOrders(HashMap<Cheese, Integer> cart) {
        orders.add(cart);
    }

    public static ArrayList<HashMap<Cheese, Integer>> getOrders() {
        return orders;
    }

    public static String printOrder(HashMap<Cheese, Integer> order) {
        StringBuilder sb = new StringBuilder();
        for (var orderEntry : order.entrySet()) {
            Cheese cheese = orderEntry.getKey();
            Integer quantity = orderEntry.getValue();
            sb.append("\t");
            sb.append(cheese.toString());
            sb.append(" x ");
            sb.append(quantity);
            sb.append("\n");
        }
        return sb.toString();
    }

}
