import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

public class InputHandler extends Filter {
    private String inputLinesFileName;
    private String inputStopWordsFileName;

    public InputHandler(Pipe mainOutputPipe, Pipe stopWordsOutputPipe,String mainInputFileName, String stopWordsFileName) {
        ArrayList<Pipe> outputs = new ArrayList<>();
        outputs.add(mainOutputPipe);
        outputs.add(stopWordsOutputPipe);
        setOutputPipes(outputs);

        inputLinesFileName = mainInputFileName;
        inputStopWordsFileName = stopWordsFileName;

    }

    private void getInputLines() {
        readLinesFromFile(new File(inputLinesFileName),true, getOutputPipe(0));
    }

    private void getStopWords() {
        readLinesFromFile(new File(inputStopWordsFileName),false, getOutputPipe(1));

    }

    private void readLinesFromFile(File file, boolean lineByLine,Pipe outputPipe) {
        ArrayList<String> lines = new ArrayList<>();
        try {
            Scanner fileReader = new Scanner(file);
            while (fileReader.hasNextLine()) {
                String line = fileReader.nextLine().trim();
                if (!(line.isEmpty() || line.equals(""))) {
                    if (lineByLine) {
                        outputPipe.write(line);
                        // yield
                    } else {
                        lines.add(line);
                    }
                }
            }
        } catch (FileNotFoundException e) {

        }
        if (!lineByLine)
            outputPipe.writeAll(lines);
    }

    @Override
    public void run() {
        if (inputStopWordsFileName != null)
            getStopWords();
        getInputLines();
    }

    public static String getExistingFileName(String prompt) {
        System.out.println(prompt);
        String response = InputHelper.readStr(null);
        File file = new File(response);
        while (!file.canRead()) {
            System.out.println("The file path provided was invalid! Please provide a valid file path or exit with \"exit!\":");
            response = InputHelper.readStr(null);
            if (response.equals("exit!"))
                return null;
            file = new File(response);
        }
        return response;
    }
}
