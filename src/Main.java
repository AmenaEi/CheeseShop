import java.util.HashMap;
import java.util.InputMismatchException;
import java.util.Scanner;

public class Main {

    public static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {

        int userChoice = -1;
        String ownerPassword = "111";

        System.out.println("Welcome to the Awesome Cheese Store!");

        while (userChoice != 0) {
            System.out.println("\nPlease enter: ");
            System.out.println("\t* 1 to authorize as customer.");
            System.out.println("\t* 2 to authorize as shop owner.");
            System.out.println("\t* 0 to exit.");

            userChoice = getUserChoice(2);

            System.out.println("You selected option: " + userChoice);
            switch (userChoice) {
                case 0:
                    System.out.println("Thank you for visiting our Awesome Cheese Store. Have a nice day.");
                    return;
                case 1:
                    System.out.println("Hello, Mx.Customer!");
                    customerOptions();
                    break;
                case 2:
                    String userPass = "";
                    System.out.println("Please enter your password: ");
                    userPass = scanner.nextLine();
                    if (userPass.equals(ownerPassword)) {
                        System.out.println("Hello, Mrs.Owner!");
                        ownerOptions();
                    } else {
                        System.out.println("Sorry, you entered an incorrect password.");
                    }
            }
        }
    }

    public static int getUserChoice(int limit) {
        int choice = -1;
        while (true) {
            try {
                choice = Integer.parseInt(scanner.nextLine().trim());
                if (choice < 0 || choice > limit) {
                    throw new InputMismatchException("Problem occurred!");
                } else {
                    break;
                }
            } catch (InputMismatchException e) {
                System.out.println("Invalid integer. Please enter integer from 0 to " + limit);
            } catch (NumberFormatException e) {
                System.out.println("Invalid input. Please enter a valid integer.");
            }
        }
        return choice;
    }

    public static void ownerOptions(){
        int ownerChoice = -1;

        while (ownerChoice != 0) {
            System.out.println("\nPlease enter: ");
            System.out.println("\t* 1 to show the full assortment.");
            System.out.println("\t* 2 to add a cheese.");
            System.out.println("\t* 3 to remove a cheese.");
            System.out.println("\t* 0 to exit to main menu.");

            ownerChoice = getUserChoice(3);

            System.out.println("You selected option: " + ownerChoice);

            switch (ownerChoice) {
                case 0:
                    System.out.println("Thank you for visiting our Awesome Cheese Store. Have a nice day.");
                    break;
                case 1:
                    showAssortment();
                    continue;
                case 2:
                    addCheeseToAssortmentBasedOnUserInput();
                    continue;
                case 3:
                    showAssortment();
                    removeCheeseFromAssortmentBasedOnUserInput();
            }
        }

    }

    public static void customerOptions(){
        int customerChoice = -1;

        while (customerChoice != 0) {
            System.out.println("\nPlease enter: ");
            System.out.println("\t* 1 to show the full assortment of the Awesome Cheese Store.");
            System.out.println("\t* 2 to add cheese to cart.");
            System.out.println("\t* 3 to remove cheese from cart.");
            System.out.println("\t* 4 to view your cart.");
            System.out.println("\t* 5 to buy cheese in your cart.");
            System.out.println("\t* 6 to view your wallet.");
            System.out.println("\t* 7 to add money to your wallet.");
            System.out.println("\t* 8 to view your orders.");
            System.out.println("\t* 0 to exit to main menu.");

            customerChoice = getUserChoice(8);

            System.out.println("You selected option: " + customerChoice);

            switch (customerChoice) {
                case 0:
                    System.out.println("Thank you for visiting our Awesome Cheese Store. We look forward to seeing you again.");
                    break;
                case 1:
                    showAssortment();
                    continue;
                case 2:
                    showAssortment();
                    addCheeseToCartBasedOnUserInput();
                    continue;
                case 3:
                    showCart();
                    removeCheeseFromCartBasedOnUserInput();
                    continue;
                case 4:
                    showCart();
                    continue;
                case 5:
                    showCart();
                    buyCart();
                    continue;
                case 6:
                    showWallet();
                    continue;
                case 7:
                    addFundsToWalletBasedOnUserInput();
                    continue;
                case 8:
                    showOrders();
            }
        }
    }

    public static void showAssortment() {
        System.out.println("\nAvailable cheeses in the Awesome Cheese Store:");
        for (Cheese cheese : CheeseService.getCheeses()) {
            System.out.println(cheese);
        }
    }

    public static void addCheeseToAssortmentBasedOnUserInput() {
        double price = 0.0;
        int id = 0;

        System.out.println("Please enter the name of the cheese: ");
        String name = scanner.nextLine();

        System.out.println("Please enter the cheese price per 100g: ");
        while (true) {
            String input = scanner.nextLine();
            try {
                price = Double.parseDouble(input);
                if (price <= 0) {
                    throw new InputMismatchException("Problem occurred!");
                } else {
                    break;
                }
            } catch (NumberFormatException e) {
                System.out.println("Invalid input. Please enter a valid double value.");
            } catch (InputMismatchException e) {
                System.out.println("Invalid input. Please enter a value greater than 0");
            }
        }

        id = CheeseService.getBiggestId() + 1;
        CheeseService.addCheeseToAssortment(id, name, price);
        System.out.println("Cheese has been added to assortment successfully.");
    }

    public static void removeCheeseFromAssortmentBasedOnUserInput() {
        int userChoice = -1;

        while (userChoice != 0) {
            System.out.println("\nPlease enter:");
            System.out.println("\t* the ID of the cheese you would like to remove from assortment");
            System.out.println("  - OR -");
            System.out.println("\t* 0 to return to owner menu.");

            try {
                userChoice = getUserChoice(CheeseService.getBiggestId());
                if (userChoice == 0) {
                    System.out.println("The removal is stopped.");
                    break;
                } else {
                    CheeseService.removeCheeseFromAssortment(userChoice);
                    System.out.println("Cheese has been removed from assortment successfully.");
                }
            } catch (Exception e) {
                System.out.println(e.getMessage());
                break;
            }
        }
    }

    public static void showCart(){
        HashMap<Cheese, Integer> cart = CheeseShop.getCart();

        System.out.println("\nYour cart: ");
        cart.forEach((cheese, amount) -> {
            String message = cheese + " x " + amount + " block";
            if (amount > 1) {
                message = message + "s"; // plural
            }
            System.out.println(message);
        });

        double totalPrice = CheeseShop.calculateTotalPrice();
        System.out.println("\nTotal price: " + totalPrice + " EUR");
    }

    public static void addCheeseToCartBasedOnUserInput() {
        int amount = -1;
        int userChoice = -1;

        while (userChoice != 0) {
            System.out.println("\nPlease enter:  ");
            System.out.println("\t* the id of the cheese you would like to add to cart");
            System.out.println("  - OR - ");
            System.out.println("\t* 0 to return to customer menu.");
            try {
                userChoice = getUserChoice(CheeseService.getBiggestId());

                if (userChoice == 0) {
                    System.out.println("The addition is stopped.");
                    break;
                } else {
                    CheeseService.getCheeseById(userChoice);
                    System.out.println("Please enter the cheese block amount (1 block = 100g, 1 cheese type limit per order = 10 blocks): ");
                    amount = getUserChoice(10);
                    if (amount == 0) {
                        System.out.println("The addition is stopped.");
                        break;
                    } else {
                        CheeseShop.addCheeseToCart(userChoice, amount);
                        System.out.println("Cheese has been added to cart successfully.");
                    }
                }
            } catch (IllegalArgumentException e) {
                System.out.println(e.getMessage());
            }

        }
    }

    public static void removeCheeseFromCartBasedOnUserInput() {
        int userChoice = -1;

        while (userChoice != 0) {
            try {
                var limitId = CheeseShop.getBiggestIdFromCart();
                System.out.println("\nPlease enter:");
                System.out.println("\t* the ID of the cheese you would like to remove from cart");
                System.out.println("  - OR -");
                System.out.println("\t* 0 to return to customer menu.");
                userChoice = getUserChoice(limitId);
                if (userChoice == 0) {
                    System.out.println("The removal is stopped.");
                    break;
                } else {
                    CheeseShop.removeCheeseFromCart(userChoice);
                    System.out.println("Cheese has been removed from cart successfully.");
                }
            } catch (Exception e) {
                System.out.println(e.getMessage());
                break;
            }

        }
    }

    public static void showWallet() {
        System.out.println("Available funds in the wallet: " + Customer.getWallet() + " EUR");
    }

    public static void addFundsToWalletBasedOnUserInput() {
        int userChoice = -1;
        int limit = 999;

        System.out.println("\nPlease enter:");
        System.out.println("\t* the sum you would like to add to your wallet (the transaction limit is " + limit + " EUR)");
        System.out.println("  - OR -");
        System.out.println("\t* 0 to return to customer menu.");
        userChoice = getUserChoice(limit);

        if (userChoice == 0) {
            System.out.println("The addition is stopped.");
            return;
        } else {
            Customer.addFunds(userChoice);
            System.out.println("Sum has been added to your wallet successfully.");
        }
    }

    public static void buyCart() {
        if (Customer.getWallet() - CheeseShop.calculateTotalPrice() < 0) {
            System.out.println("You don't have enough funds.");
            showWallet();
        } else {
            Customer.reduceFunds(CheeseShop.calculateTotalPrice());
            Customer.addCartToOrders(CheeseShop.getCart());
            System.out.println("\nThank you for your purchase!");
            CheeseShop.emptyCart();
        }
    }

    public static void showOrders() {
        int count = 1;
        System.out.println("\nYour previous orders in the Awesome Cheese Store:\n");
        for (HashMap<Cheese, Integer> order : Customer.getOrders()) {
            System.out.println("Order #" + count++ + ": "); //uses count first, adds +1 second
            System.out.println(Customer.printOrder(order));
        }
    }
}