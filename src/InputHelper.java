import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * Please note that this file/code is from SE310's student repository, which is available for all students to use freely.
 *
 *
 */
public class InputHelper {
    // If file is null, read from System.in
    public static String readStr(String file) {
        BufferedReader in = null;
        String line = "-1";
        try{
            if(file == null)
                in = new BufferedReader(new InputStreamReader(System.in));
            else
                in = new BufferedReader(new FileReader(file));
            // CJ Criniti - added the trim
            line = in.readLine().trim();
            // Verify the input exists
            while(line == null || line.length() == 0){
                System.out.println("Please enter valid input of at least 1 char");
                // CJ Criniti - added the trim
                line = in.readLine().trim();
            }
        }
        catch(IOException e){
            e.printStackTrace();
        }
        return line;
    }

    // If file is null, read from System.in
    public static int readInt(String file) {
        BufferedReader in = null;
        String line = "-1";
        try{
            if(file == null)
                in = new BufferedReader(new InputStreamReader(System.in));
            else
                in = new BufferedReader(new FileReader(file));
            // CJ Criniti - added the trim
            line = in.readLine().trim();
            while(line == null || line.length() == 0 || !isInt(line)) {
                System.out.println("Please enter a valid int");
                // CJ Criniti - added the trim
                line = in.readLine().trim();
            }
        }
        catch(IOException e){
            e.printStackTrace();
        }
        return Integer.parseInt(line);
    }

    // If file is null, read from System.in
    public static double readDouble(String file) {
        BufferedReader in = null;
        String line = "-1";
        try{
            if(file == null)
                in = new BufferedReader(new InputStreamReader(System.in));
            else
                in = new BufferedReader(new FileReader(file));
            line = in.readLine();
            while(line == null || line.length() == 0 || !isDouble(line)) {
                System.out.println("Please enter a valid double");
                line = in.readLine();
            }
        }
        catch(IOException e){
            e.printStackTrace();
        }
        return Double.parseDouble(line);
    }

    // Returns true if the input can be parsed to an int, else false
    private static boolean isInt(String num) {
        try {
            Integer.parseInt(num);
            return true;
        }
        catch(Exception e){
            return false;
        }
    }

    // Returns true if the input can be parsed to a double, else false
    private static boolean isDouble(String num) {
        try {
            Double.parseDouble(num);
            return true;
        }
        catch(Exception e){
            return false;
        }
    }
}
