import java.util.*;
import java.io.BufferedReader;

public class Database {
    static WriteFile log;
    static WriteFile output;
    HashMap<Integer,Item> DB = new HashMap<>();
    BufferedReader br;
    int counter;

    Scanner userInput = new Scanner(System.in);
    Queue<Integer> queue = new LinkedList<>();
    public Database() {
        counter = 1; }

    public void putItem(Item item) throws NullPointerException{
        if(item == null) {
            log.writeLine(getCurrentTime() + "Failed to add into database.");
            throw new NullPointerException();
        }
        if(!queue.isEmpty()){
            int InventoryNumber = queue.remove();
            item.setInventoryNumber(InventoryNumber);
            DB.put(InventoryNumber, item);
        }
        else{
            item.setInventoryNumber(counter);
            DB.put(counter++, item);
        }
        item.setTimeStamp(item.createTimestamp());
        log.writeLine(getCurrentTime() + item.toString() + " has been added into database");
    }

    public void removeItemByInventoryNumber(int InventoryNumber) throws NullPointerException{
        if(getItemByInventoryNumber(InventoryNumber)==null){
            System.out.println("Can not find this inventory number.");
        }

        log.writeLine(getCurrentTime() + DB.get(InventoryNumber) + " has been removed from the database");
        queue.add(InventoryNumber);
        DB.remove(InventoryNumber);
    }

    public Item getItemByInventoryNumber(int inventoryNumber) {
        return DB.get(inventoryNumber);
    }

    public void modifyItem(int InventoryNumber, int mode) throws IndexOutOfBoundsException {
        if(InventoryNumber < 1 || InventoryNumber > DB.size())
            throw new IndexOutOfBoundsException();
        switch(mode) {
            case 1:
                System.out.println("Enter the new item description");
                String itemDescription = userInput.nextLine();
                DB.get(InventoryNumber).setItemDescription(itemDescription);
                log.writeLine(getCurrentTime() + "The description of this item has been reset as: " + itemDescription);
                break;

            case 2:
                try{
                    System.out.println("Enter the new quantity");
                    int quantiy = userInput.nextInt();
                    DB.get(InventoryNumber).setQuantiy(quantiy);
                    log.writeLine(getCurrentTime() + "The quantity has been reset to: " + quantiy);
                }catch(InputMismatchException e){System.out.println(e);}
                break;

            case 3:
                try{
                    System.out.println("Enter the new price");
                    double price = userInput.nextDouble();
                    DB.get(InventoryNumber).setPrice(price);
                    log.writeLine(getCurrentTime() + "The price has been reset as: " + price);
                }catch(InputMismatchException e){System.out.println(e);}
                break;

            case 4:
                System.out.println("Enter the new date of purchase");
                String datePurchased = userInput.nextLine();
                DB.get(InventoryNumber).setDatePurchased(datePurchased);
                log.writeLine(getCurrentTime() + "The date of purchase has been reset as: " + datePurchased);
                break;
        }
    }

    private String getCurrentTime() {
        return String.format("%2$tT %2$tm-%2$td-%2$tY: ","",new Date());
    }
    public void getReport(){
        output.writeLine("Inventory Number | Item Description | Quantity | Price | Date Purchased | Timestamp \n" +
                "------------------------------------------------------------------------------------");
        for(int i = 1; i <= DB.size(); i++){
            if(DB.get(i)!=null){
                output.writeLine(DB.get(i).toStringOutput());
            }
        }

    }
    public void saveFiles(){
        output.close();
        log.close();
    }
}