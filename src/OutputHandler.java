import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

public class OutputHandler {
    public OutputHandler() {
    }

    public void outputStringArrayToFile(ArrayList<String> lines) {
        System.out.println("Please provide a file name to output the results to:");
        try {
            File file = new File("output/"+InputHelper.readStr(null));
            while (!file.createNewFile()) {
                System.out.println("The file name provided already exists! Please provide another:");
                file = new File("output/"+InputHelper.readStr(null));
            }
            FileWriter fileWriter = new FileWriter(file);
            for (String line : lines) {
                fileWriter.write(line + "\n");
            }
            fileWriter.close();
            System.out.println("The file was successfully created in the \"output\" directory.");
        } catch(IOException e) {
            System.out.println("The output file could not be created due to this reason:");
            System.out.println(e.getMessage());
        }
    }
}
