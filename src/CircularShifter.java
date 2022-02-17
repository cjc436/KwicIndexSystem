import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;

public class CircularShifter {
    private ArrayList<String> inputLines;
    private HashSet<String> stopWords;

    public CircularShifter(ArrayList<String> lines, HashSet<String> words) {
        this.inputLines = lines;
        this.stopWords = words;
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
}
