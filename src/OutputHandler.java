import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

public class OutputHandler extends Filter {
    private String outputFileName;

    public OutputHandler(Pipe alphabetizedLinesInputPipe, String outputFileName) {
        addInputPipe(alphabetizedLinesInputPipe);
        this.outputFileName = outputFileName;
    }

    public void outputStringArrayToFile() {
        ArrayList<String> lines = getInputPipe(0).readAll();
        try {
            FileWriter fileWriter = new FileWriter(new File(outputFileName));
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

    @Override
    public void run() {
        outputStringArrayToFile();
    }

    public static String getValidFileName() {
        System.out.println("Please provide a file name to output the results to:");
        String fileName = "output/"+InputHelper.readStr(null);
        File file = new File(fileName);
        while (file.exists()) {
            System.out.println("The file name provided already exists! Please provide another:");
            fileName = "output/"+InputHelper.readStr(null);
            file = new File(fileName);
        }
        return fileName;
    }
}
