import java.util.HashMap;

public class CheeseShop {

    private static HashMap<Cheese, Integer> cart = new HashMap<>();

    public static void addCheeseToCart(int id, int amount) {
            var cheeseAsKey = CheeseService.getCheeseById(id);
            Integer previousAmount = cart.get(cheeseAsKey);
            int finalAmount = amount;
            if (previousAmount != null) {
                finalAmount += previousAmount;
            }
            if (finalAmount > 10) {
                finalAmount = 10;
            }
            cart.put(cheeseAsKey, finalAmount);

    }

    public static void removeCheeseFromCart(int id) throws Exception {
        var cart = getCart();
        if (cart == null || cart.isEmpty()) {
            throw new Exception("The cart is empty");
        } else {
            Cheese cheese = getCheeseByIdFromCart(id);
            if (cheese == null) {
                throw new Exception("Cheese with ID " + id + " not found.");
            } else {
                cart.remove(cheese);
            }
        }
    }

    public static Cheese getCheeseByIdFromCart(int id) {
        return cart.keySet().stream()
                .filter(c -> c.getId() == id)
                .findFirst()
                .orElse(null);
    }

    public static int getBiggestIdFromCart() throws Exception {
        return cart.keySet().stream()
                .mapToInt(Cheese::getId)
                .max()
                .orElseThrow(() -> new Exception("No cheeses in the cart available"));
    }

    public static HashMap<Cheese, Integer> getCart(){
        return cart;
    }

    public static double calculateTotalPrice() {
        HashMap<Cheese, Integer> cart = CheeseShop.getCart();
        final double[] totalPrice = {0};

        cart.forEach((cheese, amount) -> {
            totalPrice[0] += cheese.getPrice() * amount;
        });

        return totalPrice[0];
    }

    public static void emptyCart() {
        cart = new HashMap<>();
    }
}
