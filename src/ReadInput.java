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
        String[][] fullList = new String[80020][]; // the big list, has to have a fixed nb of rows. Need to remove that afterwards maybe?
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


    public static void main(String[] args) throws FileNotFoundException {
        ReadInput r = new ReadInput("./src/b_lovely_landscapes.txt");
        String[][] result = r.arrayFromFile();
        System.out.println("MAP:" + tagMap.toString());
        System.out.println("RESULT: \n" + Arrays.deepToString(result));
        String[][] processedResult = deleteNulls(result);
        //System.out.println(Arrays.deepToString(processedResult));


        String[] photo1 = {"1", "H", "3", "cat", "beach", "sun"};
        String[] photo2 = {"0", "H", "21", "tr5fv", "t99tm", "t5nnd", "tj4f", "twzgl", "t6zkl", "tcz3g", "tf5wv", "tv1h01",
                "tv7zt", "t5xl1", "th8wv", "tkp5g", "tb5p6", "tzdr4", "t111w", "tc2j5", "t1c76", "tsmcf", "t2hzq", "tbj57"};
        SlideshowCreator creator = new SlideshowCreator(tagMap);
        System.out.println("Best match= " + creator.match(photo2));

        System.out.println("Photo 0 " + Arrays.deepToString(photo2));
        System.out.println("Photo 18144 " + Arrays.deepToString(result[18144]));


    }
}
