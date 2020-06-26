import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.io.File;

public class WriteFile {
    String fileName;
    BufferedWriter bufferedWriter;

    public WriteFile(String fileName){
        try{
            FileWriter fileWriter = new FileWriter(fileName);
            bufferedWriter = new BufferedWriter(fileWriter);
            this.fileName = fileName;
        }
        catch (IOException e){ System.out.println("Can not open " + fileName); }
    }

    public WriteFile(File file){
        try{
            FileWriter fileWriter = new FileWriter(file);
            bufferedWriter = new BufferedWriter(fileWriter);
        }
        catch (IOException e){ System.out.println("Can not open " + fileName); }
    }

    public void writeLine(String line){
        try{
            bufferedWriter.write(line);
            bufferedWriter.write("\n");
        }catch (IOException e){ System.out.println("Must first initiate the file writer"); }
    }

    public void close() {
        try {
            bufferedWriter.flush(); //In case something got stuck in the buffer
            bufferedWriter.close(); //Properly close the file and release control
        } catch (NullPointerException np) { System.out.println("Must first initiate the file writer");
        } catch (IOException e) {           System.out.println("Must first initiate the file writer"); }
    }
}

