import java.util.ArrayList;

public class Pipe {
    private ArrayList<String> sharedMem;

    public Pipe () {
        sharedMem = new ArrayList<>();
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
}
