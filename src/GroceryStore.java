import javax.swing.*;
import java.io.File;
import java.util.Arrays;

public class GroceryStore {
    static Database gtdb = new Database();
    static JFileChooser fileChooser = new JFileChooser(System.getProperty("user.dir"));

    public static void main(String[] args) {
    	System.out.println(Arrays.toString(args));
        int i = 0;
        String arg, inputFile = "", logFile = "", outputFile = "";
        File fileDirectory = null;
        boolean iFlag = false, lFlag = false, oFlag = false, dFlag = false;
        ReadFile inputReader = null;
        while (i < args.length && args[i].startsWith("-")) {
            arg = args[i++];

            if (arg.equals("-d")) {
                if (i < args.length) {
                    fileDirectory = new File(args[i++]);
                    dFlag = true;
                } else {
                    System.err.println("-d requires path... Setting path to default");
                }

            } else if (arg.equals("-i")) {
                if (i < args.length) {
                    inputFile = args[i++];
                    if (!inputFile.endsWith(".txt"))
                        System.err.println("Not a valid txt file");
                    else
                        iFlag = true;
                } else {
                    System.err.println("-i requires a filename");
                }
            } else if (arg.equals("-l")) {
                if (i < args.length) {
                    logFile = args[i++];
                    if (!logFile.endsWith(".txt"))
                        System.err.println("Not a valid txt file");
                    else
                        lFlag = true;
                } else {
                    System.err.println("-l requires a filenae");
                }
            } else if (arg.equals("-o")) {
                if (i < args.length) {
                    outputFile = args[i++];
                    if (!outputFile.endsWith(".txt"))
                        System.err.println("Not a valid txt file");
                    else
                        oFlag = true;
                } else {
                    System.err.println("-o requires a filename");
                }
            }
        }

        //check path
        if (dFlag) {
            fileChooser.setCurrentDirectory(fileDirectory);
        }

            //check input file
            if (iFlag) {
                inputReader = new ReadFile(fileDirectory != null ? (fileDirectory.getPath()+ "/" + inputFile) : inputFile);
            } else {
                if (fileChooser.showOpenDialog(null) == fileChooser.APPROVE_OPTION) {
                    inputReader = new ReadFile(fileChooser.getSelectedFile());
                } else {
                    JOptionPane.showMessageDialog(null, "Error... Please select a input file!");
                    System.exit(1);
                }
            }

            //check log file
            if (lFlag) {
                Database.output = new WriteFile(logFile);
            } else {
                fileChooser.setSelectedFile(new File("log.txt"));
                if (fileChooser.showSaveDialog(null) == fileChooser.APPROVE_OPTION) {
                    Database.log = new WriteFile(fileChooser.getSelectedFile());
                } else {
                    JOptionPane.showMessageDialog(null, "Error... Please select the save location of log file!");
                    System.exit(1);
                }
            }

            //check output file
            if (oFlag) {
                Database.log = new WriteFile(outputFile);
            } else {
                fileChooser.setSelectedFile(new File("output.txt"));
                if (fileChooser.showSaveDialog(null) == fileChooser.APPROVE_OPTION) {
                    Database.output = new WriteFile(fileChooser.getSelectedFile());
                } else {
                    JOptionPane.showMessageDialog(null, "Error... Please select the save location of output file!");
                    System.exit(1);
                }
            }

            inputReader.readInput();
            CMD cmds = new CMD();
            cmds.printMainMeun();

        }

}
