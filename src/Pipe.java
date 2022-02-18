import java.util.ArrayList;

public class Pipe {
    private ArrayList<String> sharedMem;
    private boolean allInputCollected;

    public Pipe () {
        sharedMem = new ArrayList<>();
        allInputCollected = false;
    }

    public boolean isSharedMemEmpty() {
        return sharedMem.isEmpty();
    }

    public String read() {
        return sharedMem.remove(0);
    }

    public ArrayList<String> readAll() {
        ArrayList<String> tempSharedMem = (ArrayList<String>) sharedMem.clone();
        sharedMem.clear();
        return tempSharedMem;
    }

    public void write(String newValue) {
        sharedMem.add(newValue);
    }

    public void writeAll(ArrayList<String> newValues) {
        sharedMem.addAll(newValues);
    }

    public boolean getInputCollectionStatus() {
        return allInputCollected;
    }

    public void setInputCollectionStatus(boolean status) {
        allInputCollected = status;
    }
}
