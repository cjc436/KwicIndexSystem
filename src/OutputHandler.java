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
        Pipe inputPipe = getInputPipe(0);
        try {
            FileWriter fileWriter = new FileWriter(new File(outputFileName));
            while (!(inputPipe.getInputCollectionStatus() && inputPipe.isSharedMemEmpty())) {
                if (inputPipe.isSharedMemEmpty()) {
                    yield();
                    continue;
                }
                fileWriter.write(inputPipe.read() + "\n");
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
