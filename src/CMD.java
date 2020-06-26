import java.util.Scanner;

public class CMD {

    static Scanner userInput = new Scanner(System.in);

    public void printMainMeun() {
        String temp = "";
        while (!temp.equals("EXIT")) {
            System.out.print("\nMenu:\n" +
                    "  (CHK) - Check an item\n" +
                    "  (RMV) - Remove an item\n" +
                    "  (ADD) - Add an item\n" +
                    "  (MDF) - Modify an item\n" +
                    "  (EXT) - EXIT\n" +
                    "Please select an option: ");

            temp = userInput.nextLine();
            int inventoryNumber;
            Item item;
            switch (temp.toUpperCase()) {
                case "CHK":
                    System.out.println("Enter the Inventory Number");
                    inventoryNumber = userInput.nextInt();
                    userInput.nextLine();
                    String info = "Inventory Number | Item Description | Quantity | Price | Date Purchased | Timestamp \n" +
                            "------------------------------------------------------------------------------------\n";
                    item = GroceryStore.gtdb.getItemByInventoryNumber(inventoryNumber);
                    if (item == null) {
                        info = "Item did not exist.";
                    } else {
                        info += item.toStringOutput();
                    }
                    System.out.println(info);
                    break;
                case "RMV":
                    System.out.println("Enter the Inventory Number");
                    inventoryNumber = userInput.nextInt();
                    userInput.nextLine();
                    GroceryStore.gtdb.removeItemByInventoryNumber(inventoryNumber);
                    break;

                case "ADD":
                    System.out.println("Enter the item's description");
                    String description = userInput.nextLine();
                    System.out.println("Enter the item's quantity");
                    int quantity = userInput.nextInt();
                    System.out.println("Enter the item's price");
                    double price = userInput.nextDouble();
                    userInput.nextLine();
                    System.out.println("Enter the item's date of purchase");
                    String datePurchase = userInput.nextLine();
                    item = new Item(description, quantity, price, datePurchase);
                    GroceryStore.gtdb.putItem(item);
                    System.out.println("Your inventory number is " + item.getInventoryNumber());
                    break;

                case "MDF":
                    System.out.println("Which inventory number of item you want to modify?");
                    int number = userInput.nextInt();
                    userInput.nextLine();
                    printSubMenu();
                    int type = userInput.nextInt();
                    userInput.nextLine();
                    if (type == 5) {
                        printMainMeun();
                    }
                    GroceryStore.gtdb.modifyItem(number, type);
                    break;

                case "EXT":
                    GroceryStore.gtdb.getReport();
                    GroceryStore.gtdb.saveFiles();
                    System.exit(0);
            }
        }
    }

    public void printSubMenu() {
        System.out.print("\nMenu:\n" +
                "  (1) - Modify item's description\n" +
                "  (2) - Modify item's quantity\n" +
                "  (3) - Modify item's price\n" +
                "  (4) - Modify item's date of purchase\n" +
                "  (5) - Back to main menu\n" +
                "Please select an option: ");
    }

}
