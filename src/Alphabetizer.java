import java.util.ArrayList;

public class Alphabetizer extends Filter {
    private ArrayList<String> lines;

    public Alphabetizer(Pipe shiftedLinesInputPipe, Pipe alphabetizedOutputPipe) {
        addInputPipe(shiftedLinesInputPipe);
        addOutputPipe(alphabetizedOutputPipe);
    }

    public ArrayList<String> alphabetize() {
        ArrayList<String> alphabetizedLines = new ArrayList<>();
        alphabetizedLines.add(lines.get(0));
        for (int i = 1; i < lines.size(); i++) {
            String key = lines.get(i);
            int j = i - 1;
            while (j > -1) {
                boolean comp = alphabeticalComparison(key,alphabetizedLines.get(j));
                if (!comp) {
                    break;
                }
                j--;
            }
            alphabetizedLines.add(j+1,key);

        }
        return alphabetizedLines;
    }

    /**
     * This method compares two strings and returns true if "s1" is alphabetically before or equal to "s2"
     * @param s1
     * @param s2
     * @return boolean
     */
    private boolean alphabeticalComparison(String s1, String s2) {
        int s1Length = s1.length();
        int s2Length = s2.length();
        int sLength = s1Length;
        if (s2Length < sLength)
            sLength = s2Length;
        for (int i = 0; i < sLength; i++) {
            char c1 = s1.charAt(i);
            char c2 = s2.charAt(i);

            // handle for equal characters
            if (c1 == c2) {
                continue;
            }

            // symbols and numbers
            if (!((c1 > 96 && c1 < 123) || (c1 > 64 && c1 < 91))) {
                if (!((c2 > 96 && c2 < 123) || (c2 > 64 && c2 < 91))) {
                    return c1 < c2;
                } else {
                    return true;
                }
            } else {
                if (!((c2 > 96 && c2 < 123) || (c2 > 64 && c2 < 91))) {
                    return false;
                }
            }

            // handle for one letter being a capital
            if (c1 > 96 && c1 < 123) {
                if (c2 > 64 && c2 < 91) {
                    return (c1 - 32) <= c2;
                }
            } else {
                if (c2 > 96 && c2 < 123) {
                    return (c1 + 32) < c2;
                }
            }

            // handle letters with the same case
            return c1 < c2;
        }
        return s1Length <= s2Length;
    }

    @Override
    public void run() {
        lines = getInputPipe(0).readAll();
        getOutputPipe(0).writeAll(alphabetize());
    }
}
