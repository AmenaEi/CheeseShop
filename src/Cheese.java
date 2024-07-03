public class Cheese {
    private int id;
    private String name;
    private double price;

    public Cheese(int id, String name, double price) {
        this.id = id;
        this.name = name;
        this.price = price;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

        public double getPrice() {
        return price;
    }

    @Override
    public String toString() {
        return "(ID: " + getId() + ") " + getName() + ": " + getPrice() + " EUR/100g";
    }
}
