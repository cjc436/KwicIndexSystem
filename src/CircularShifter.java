import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;

public class CircularShifter extends Filter {
    private ArrayList<String> inputLines;
    private HashSet<String> stopWords;

    public CircularShifter(Pipe inputLinesPipe, Pipe stopWordsInputPipe, Pipe outputShiftedLinesPipe) {
        ArrayList<Pipe> inputs = new ArrayList<>();
        inputs.add(inputLinesPipe);
        inputs.add(stopWordsInputPipe);
        setInputPipes(inputs);
        addOutputPipe(outputShiftedLinesPipe);
    }

    public ArrayList<String> shiftLines() {
        ArrayList<String> shiftedLines = new ArrayList<>();
        for (String inputLine : this.inputLines) {
            ArrayList<String> separated = new ArrayList<>(Arrays.asList(inputLine.split(" ")));
            for (int i = 0; i < separated.size(); i++) {
                if (!this.stopWords.contains(separated.get(0)))
                    shiftedLines.add(String.join(" ",separated));
                separated.add(separated.get(0));
                separated.remove(0);
            }
        }
        return shiftedLines;
    }

    @Override
    public void run() {
        inputLines = getInputPipe(0).readAll();
        stopWords = new HashSet<>(getInputPipe(1).readAll());

        getOutputPipe(0).writeAll(shiftLines());
    }
}
