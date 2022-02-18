import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;

public class CircularShifter extends Filter {
    private HashSet<String> stopWords;

    public CircularShifter(Pipe inputLinesPipe, Pipe stopWordsInputPipe, Pipe outputShiftedLinesPipe) {
        ArrayList<Pipe> inputs = new ArrayList<>();
        inputs.add(inputLinesPipe);
        inputs.add(stopWordsInputPipe);
        setInputPipes(inputs);
        addOutputPipe(outputShiftedLinesPipe);
    }

    public void shiftLines() {
        Pipe inputPipe = getInputPipe(0);
        Pipe outputPipe = getOutputPipe(0);

        while (!(inputPipe.getInputCollectionStatus() && inputPipe.isSharedMemEmpty())) {
            if (inputPipe.isSharedMemEmpty()) {
                yield();
                continue;
            }
            ArrayList<String> separated = new ArrayList<>(Arrays.asList(inputPipe.read().split(" ")));
            for (int i = 0; i < separated.size(); i++) {
                if (!this.stopWords.contains(separated.get(0))) {
                    outputPipe.write(String.join(" ", separated));
                    yield();
                }
                separated.add(separated.remove(0));
            }
        }
        outputPipe.setInputCollectionStatus(true);
    }

    @Override
    public void run() {
        // Wait until stop words have been gathered because we need all of them
        Pipe inputPipe = getInputPipe(1);
        while (!inputPipe.getInputCollectionStatus())
            yield();

        stopWords = new HashSet<>(getInputPipe(1).readAll());

        shiftLines();
    }
}
