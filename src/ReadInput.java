import java.io.File;
import java.io.FileNotFoundException;
import java.util.Arrays;
import java.util.Scanner;

public class ReadInput {

    public ReadInput(String path) {
        inputFile = new File(path);
    }

    private File inputFile;

    public String[][] arrayFromFile() throws FileNotFoundException {
        Scanner s = new Scanner(inputFile); // new scanner for file
        String[][] fullList = new String[30][]; // the big list, has to have a fixed nb of rows. Need to remove that afterwards maybe?
        int index = 0;
        while (s.hasNextLine()) { // check for next line
            String[] tempArray = processLine(s.nextLine()); // process the line into an array
            fullList[index] = tempArray; // add to the bigger array
            index++; // increment index
        }
        return fullList;
    }

    public String[] processLine(String l) {
        return l.split(" ");

    }

    public static String[][] deleteNulls(String[][] list) {
        int listLength = list.length;
        for (int i = 0; i<listLength; i++) {
            if (list[i] == null) {
                return Arrays.copyOfRange(list,0,i);
            }
        }
        return list;
    }

    public static void main(String[] args) throws FileNotFoundException {
        ReadInput r = new ReadInput("./src/a_example.txt");
        String[][] result = r.arrayFromFile();
        System.out.println(Arrays.deepToString(result));
        String[][] processedResult = deleteNulls(result);
        System.out.println(Arrays.deepToString(processedResult));
    }
}
