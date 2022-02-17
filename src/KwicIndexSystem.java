import java.util.ArrayList;
import java.util.HashSet;

public class KwicIndexSystem {

    public KwicIndexSystem() {
    }

    public void start() {




        Pipe inputToCircularShifter = new Pipe();
        Pipe inputStopWordsToCircularShifter = new Pipe();

        InputHandler inputHandler = new InputHandler(inputToCircularShifter, inputStopWordsToCircularShifter);

        Pipe shiftedLinesToAlphabetizerPipe = new Pipe();

        CircularShifter circularShifter = new CircularShifter(inputToCircularShifter,inputStopWordsToCircularShifter,shiftedLinesToAlphabetizerPipe);

        Pipe alphabetizedLinesToOutput = new Pipe();

        Alphabetizer alphabetizer = new Alphabetizer(shiftedLinesToAlphabetizerPipe,alphabetizedLinesToOutput);

        OutputHandler outputHandler = new OutputHandler(alphabetizedLinesToOutput);

        try {
            inputHandler.start();
            inputHandler.join();
            circularShifter.start();
            circularShifter.join();
            alphabetizer.start();
            alphabetizer.join();
            outputHandler.start();
            outputHandler.join();
        } catch (InterruptedException e) {

        }
    }
}
