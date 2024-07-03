import java.util.ArrayList;
import java.util.NoSuchElementException;

public class CheeseService {

    private static ArrayList<Cheese> cheeses = new ArrayList<>();

    static {
        addCheeseToAssortment(1, "Cheddar", 1.50);
        addCheeseToAssortment(2, "Brie", 2.25);
        addCheeseToAssortment(3, "Gouda", 1.80);
        addCheeseToAssortment(4, "Mozzarella", 1.65);
        addCheeseToAssortment(5, "Parmesan", 2.40);
        addCheeseToAssortment(6, "Swiss", 2.10);
        addCheeseToAssortment(7, "Blue Cheese", 1.95);
        addCheeseToAssortment(8, "Feta", 1.50);
        addCheeseToAssortment(9, "Camembert", 2.25);
        addCheeseToAssortment(10, "Monterey Jack", 1.80);
    }

    public static void addCheese(Cheese cheese) {
        cheeses.add(cheese);
    }

    public static void addCheeseToAssortment(int id, String name, double price) {
        addCheese(new Cheese(id, name, price));
    }

    public static void removeCheeseFromAssortment(int id) {
        try {
            Cheese cheese = getCheeseById(id);
            cheeses.remove(cheese);
        } catch (IllegalArgumentException e) {
            System.out.println(e.getMessage());
        }
    }

    public static Cheese getCheeseById(int id) {
        return cheeses.stream()
                .filter(c -> c.getId() == id)
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("Cheese with ID " + id + " not found."));
    }

    public static int getBiggestId() {
        return cheeses.stream()
                .mapToInt(Cheese::getId)
                .max()
                .orElseThrow(() -> new NoSuchElementException("No cheeses available"));
    }

    public static ArrayList<Cheese> getCheeses() {
        return cheeses;
    }

}
