import java.util.ArrayList;
import java.util.HashSet;

public class KwicIndexSystem {

    public KwicIndexSystem() {
    }

    public void start() {
        InputHandler inputHandler = new InputHandler();
        ArrayList<String> inputLines = inputHandler.getInputLines();
        HashSet<String> stopWords = new HashSet<>(inputHandler.getStopWords());

        CircularShifter circularShifter = new CircularShifter(inputLines,stopWords);
        ArrayList<String> shiftedLines = circularShifter.shiftLines();

        Alphabetizer alphabetizer = new Alphabetizer(shiftedLines);
        ArrayList<String> alphabetizedLines = alphabetizer.alphabetize();

        OutputHandler outputHandler = new OutputHandler();
        outputHandler.outputStringArrayToFile(alphabetizedLines);
    }
}
