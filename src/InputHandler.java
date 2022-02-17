import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

public class InputHandler extends Filter {
    private String inputLinesFileName;
    private String inputStopWordsFileName;

    public InputHandler(Pipe mainOutputPipe, Pipe stopWordsOutputPipe) {
        ArrayList<Pipe> outputs = new ArrayList<>();
        outputs.add(mainOutputPipe);
        outputs.add(stopWordsOutputPipe);
        setOutputPipes(outputs);


    }

    public ArrayList<String> getInputLines() {
        System.out.println("Please provide a file path to a file containing lines to be indexed:");
//        return readLinesFromFile(new File(InputHelper.readStr(null)));
        return readLinesFromFile(new File("test.txt"));
    }

    public ArrayList<String> getStopWords() {
        System.out.println("Do you wish to provide a file of stop words? (y/n)");
        String response = InputHelper.readStr(null);
        while (!(response.equals("y") || response.equals("n"))) {
            System.out.println("Please provide a valid response. (y/n)");
            response = InputHelper.readStr(null);
        }
        if (response.equals("n"))
            return new ArrayList<>();
        System.out.println("Please provide a file path to a file containing the stop words.");
        System.out.println("In this file, there should be one stop word on each line.");
        return readLinesFromFile(new File(InputHelper.readStr(null)));

    }

    private ArrayList<String> readLinesFromFile(File file) {
        while (!file.canRead()) {
            System.out.println("The file path provided was invalid! Please provide a valid file path or exit with \"exit!\":");
            String response = InputHelper.readStr(null);
            if (response.equals("exit!"))
                return new ArrayList<>();
            file = new File(response);
        }
        ArrayList<String> lines = new ArrayList<>();
        try {
            Scanner fileReader = new Scanner(file);
            while (fileReader.hasNextLine()) {
                String line = fileReader.nextLine().trim();
                if (!(line.isEmpty() || line.equals(""))) {
                    lines.add(line);
                }
            }
        } catch (FileNotFoundException e) {
            return lines;
        }
        return lines;
    }

    @Override
    public void run() {
        ArrayList<Pipe> outputPipes = getAllOutputPipes();

        Pipe inputLinePipes = outputPipes.get(0);
        inputLinePipes.writeAll(getInputLines());

        //Pipe stopWordsPipe = outputPipes.get(1);
        //stopWordsPipe.writeAll(getStopWords());
    }
}
