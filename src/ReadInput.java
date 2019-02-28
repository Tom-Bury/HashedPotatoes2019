import org.junit.Test;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;

public class ReadInput {

    public ReadInput(String path) {
        inputFile = new File(path);
    }

    private File inputFile;
    private static Map<String, ArrayList<Integer>> tagMap = new HashMap<>();

    public String[][] arrayFromFile() throws FileNotFoundException {
        Scanner s = new Scanner(inputFile); // new scanner for file
        String[][] fullList = new String[30][]; // the big list, has to have a fixed nb of rows. Need to remove that afterwards maybe?
        int index = 0;
        while (s.hasNextLine()) { // check for next line
            String[] tempArray = processLine(s.nextLine()); // process the line into an array
            fullList[index] = tempArray; // add to the bigger array
            toMap(tempArray);
            index++; // increment index
        }
        return fullList;
    }

    public String[] processLine(String l) {
        return l.split(" ");

    }

    public void toMap(String[] photo) {
        int id = Integer.parseInt(photo[0]);
        int i = 3;
        while (i < photo.length) {
            if (tagMap.containsKey(photo[i])) {
                tagMap.get(photo[i]).add(id);
            }
            else {
                ArrayList<Integer> list = new ArrayList<>();
                list.add(id);
                tagMap.put(photo[i],list);
            }
            i++;
        }
    }

    public static void main(String[] args) throws FileNotFoundException {
        ReadInput r = new ReadInput("./src/a_example.txt");
        String[][] result = r.arrayFromFile();
        System.out.println("MAP:" + tagMap.toString());
        System.out.println(Arrays.deepToString(result));
    }
}
