import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.File;

public class ReadFile {
    BufferedReader bufferedReader;
    public ReadFile(String fileName){
        try{
            bufferedReader = new BufferedReader(new FileReader(fileName));
        }catch (FileNotFoundException e){ System.out.println("Can not find the file"); }
    }

    public ReadFile(File file){
        try{
            bufferedReader = new BufferedReader(new FileReader(file));
        }catch (FileNotFoundException e){ System.out.println("Can not find the file"); }
    }

    public void readInput(){
        String temp = "";
        String description = "";
        int quantity = 0;
        double price = 0;
        String datePurchase = "";
        try{
            while ((temp = bufferedReader.readLine()) != null){
                if (temp.length() == 0) continue;
                String[] ready = temp.split(" ");
                description = ready[1];
                quantity = Integer.parseInt(ready[2]);
                price = Double.parseDouble(ready[3]);
                datePurchase = ready[4];
                Item item = new Item(description,quantity,price,datePurchase);
                GroceryStore.gtdb.putItem(item);
            }
            this.close();
        }catch (IOException e){}
    }
    public void close(){
        try{
            bufferedReader.close();
        }catch (IOException E){ System.out.println("Must first initiate the file reader"); }
    }
}

