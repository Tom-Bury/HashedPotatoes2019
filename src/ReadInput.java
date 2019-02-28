import com.sun.deploy.util.ArrayUtil;

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
            String[] tempArray = processLine(s.nextLine(),index); // process the line into an array
            fullList[index] = tempArray; // add to the bigger array
            toMap(tempArray);
            index++; // increment index
        }
        return fullList;
    }

    public String[] processLine(String l, int index) {
        String[] result = l.split(" ");
        if (index != 0) {
            String[] indexString = {Integer.toString(index)};
            return concat(indexString,result);
        }
        return result;



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

    // Method for concatenating
    public static <T> T[] concat(T[] first, T[] second) {
        T[] result = Arrays.copyOf(first, first.length + second.length);
        System.arraycopy(second, 0, result, first.length, second.length);
        return result;
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


//    public static Photo convertToPhoto() {
//        return null;
//    }

    public static void main(String[] args) throws FileNotFoundException {
        ReadInput r = new ReadInput("./src/a_example.txt");
        String[][] result = r.arrayFromFile();
        System.out.println("MAP:" + tagMap.toString());
        System.out.println(Arrays.deepToString(result));
        String[][] processedResult = deleteNulls(result);
        System.out.println(Arrays.deepToString(processedResult));
    }
}
