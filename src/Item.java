import java.util.Date;
public class Item{
    private int InventoryNumber;
    private String itemDescription;
    private int quantiy;
    private double price;
    private String datePurchased;
    private String timeStamp;
    public Item() {}

    public Item(String itemDescription, int quantiy, double price, String datePurchased) {
        setItemDescription(itemDescription);
        setQuantiy(quantiy);
        setPrice(price);
        setDatePurchased(datePurchased);
    }
    public int getInventoryNumber() {
        return this.InventoryNumber;
    }

    public void setInventoryNumber(int InventoryNumber) {
        this.InventoryNumber = InventoryNumber;
    }

    public String getItemDescription() {
        return this.itemDescription;
    }

    public void setItemDescription(String itemDescription) {
        this.itemDescription = itemDescription;
    }

    public int getQuantiy() {
        return this.quantiy;
    }

    public void setQuantiy(int quantiy) {
        if(quantiy <= 0){
            System.out.println("The quantiy can not less than 0");
            return;
        }
        this.quantiy = quantiy;
    }

    public double getPrice() {
        return this.price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public String getDatePurchased() {
        return this.datePurchased;
    }

    public void setDatePurchased(String datePurchased) {
        this.datePurchased = datePurchased;
    }

    public String getTimeStamp() {
        return this.timeStamp;
    }

    public void setTimeStamp(String timeStamp) {
        this.timeStamp = timeStamp;
    }

    public String toStringOutput() {

        String str =String.format("%-17d| %-17s| %-9d| %-6.2f|   %-13s|%s",
                InventoryNumber, itemDescription, quantiy, price, datePurchased, timeStamp);
        return str;
    }

    @Override
    public String toString() {
        return "Item{" +
                "InventoryNumber=" + InventoryNumber +
                ", itemDescription='" + itemDescription + '\'' +
                ", quantiy=" + quantiy +
                ", price=" + price +
                ", datePurchased='" + datePurchased + '\'' +
                '}';
    }

    public String createTimestamp(){
        return String.format("%1$s %2$tm-%2$td-%2$tY","", new Date());
    }

}