public abstract class Filter extends Thread {
    private Pipe input;
    private Pipe output;
    private boolean stop = false;

    public Pipe getInput() {
        return input;
    }
    public Pipe getOutput() {
        return output;
    }
    public void setInput(Pipe newInput) {
        input = newInput;
    }
    public void setOutput(Pipe newOutput) {
        output = newOutput;
    }

    public abstract void run();
}
