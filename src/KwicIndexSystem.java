public class KwicIndexSystem {

    public KwicIndexSystem() {
    }

    public void start() {

        // Normally file names would be given before setting up the pipes and filters, so I pulled them out of the InputHandler and asked for them here
        String mainFileName = InputHandler.getExistingFileName("Please provide a file path to a file containing lines to be indexed:");
        String stopWordsFileName = null;
        System.out.println("Do you wish to provide a file of stop words? (y/n)");
        String response = InputHelper.readStr(null);
        while (!(response.equals("y") || response.equals("n"))) {
            System.out.println("Please provide a valid response. (y/n)");
            response = InputHelper.readStr(null);
        }
        if (response.equals("y"))
            stopWordsFileName = InputHandler.getExistingFileName("Please provide a file path to a file containing the stop words.\nIn this file, there should be one stop word on each line.");

        String outputFileName = OutputHandler.getValidFileName();

        // Setting up pipes and filters
        Pipe inputToCircularShifter = new Pipe();
        Pipe inputStopWordsToCircularShifter = new Pipe();

        InputHandler inputHandler = new InputHandler(inputToCircularShifter, inputStopWordsToCircularShifter, mainFileName, stopWordsFileName);

        Pipe shiftedLinesToAlphabetizerPipe = new Pipe();

        CircularShifter circularShifter = new CircularShifter(inputToCircularShifter,inputStopWordsToCircularShifter,shiftedLinesToAlphabetizerPipe);

        Pipe alphabetizedLinesToOutput = new Pipe();

        Alphabetizer alphabetizer = new Alphabetizer(shiftedLinesToAlphabetizerPipe,alphabetizedLinesToOutput);

        OutputHandler outputHandler = new OutputHandler(alphabetizedLinesToOutput, outputFileName);

        inputHandler.start();
        circularShifter.start();
        alphabetizer.start();
        outputHandler.start();
    }
}
