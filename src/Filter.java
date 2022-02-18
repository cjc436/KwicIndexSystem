import java.util.ArrayList;

public abstract class Filter extends Thread {
    private ArrayList<Pipe> inputPipes = new ArrayList<>();
    private ArrayList<Pipe> outputPipes = new ArrayList<>();

    public Pipe getInputPipe(int index) {
        return inputPipes.get(index);
    }
    public Pipe getOutputPipe(int index) {
        return outputPipes.get(index);
    }

    public void addInputPipe(Pipe newPipe) {
        inputPipes.add(newPipe);
    }
    public void addOutputPipe(Pipe newPipe) {
        outputPipes.add(newPipe);
    }
    public void setInputPipes(ArrayList<Pipe> newInputPipes) {
        inputPipes = newInputPipes;
    }
    public void setOutputPipes(ArrayList<Pipe> newOutputPipes) {
        outputPipes = newOutputPipes;
    }

    public abstract void run();
}
