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

    public void write(String newValue) {
        sharedMem.add(newValue);
    }

    public void writeAll(ArrayList<String> newValues) {
        sharedMem.addAll(newValues);
    }
}
