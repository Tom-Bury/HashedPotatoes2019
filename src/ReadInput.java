import com.sun.deploy.util.ArrayUtil;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;

public class ReadInput {

    public ReadInput(String path) {
        inputFile = new File(path);
        try {
            arrayFromFile();
        } catch (Exception e) {
            System.err.println(e);
        }
    }

    private File inputFile;
    private static String[][] resultList;
    private static Map<String, ArrayList<Integer>> tagMap = new HashMap<>();

    public void arrayFromFile() throws FileNotFoundException {
        Scanner s = new Scanner(inputFile); // new scanner for file
        String[][] fullList = new String[80020][]; // the big list, has to have a fixed nb of rows. Need to remove that afterwards maybe?
        int index = 0;
        while (s.hasNextLine()) { // check for next line
            String[] tempArray = processLine(s.nextLine(),index); // process the line into an array
            fullList[index] = tempArray; // add to the bigger array
            toMap(tempArray); // sort tags
            index++; // increment index
        }
        resultList = fullList;
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

    public void deleteNulls() {
        int listLength = resultList.length;
        for (int i = 0; i<listLength; i++) {
            if (resultList[i] == null) {
                resultList = Arrays.copyOfRange(resultList,0,i);
                break;
            }
        }
    }

    public Photo[] toPhoto() {
        int size = Integer.parseInt(resultList[0][0]);
        Photo[] photoList = new Photo[size];
        for (int i = 1; i<size; i++) {
            photoList[i] = new Photo(resultList[i]);
        }
        return photoList;
    }

    public static void main(String[] args) throws FileNotFoundException {
        ReadInput r = new ReadInput("./src/b_lovely_landscapes.txt");
        System.out.println("MAP:" + r.tagMap.toString());
        System.out.println("RESULT: \n" + Arrays.deepToString(resultList));
        //String[][] processedResult = deleteNulls(result);
        //System.out.println(Arrays.deepToString(processedResult));


//        String[] photo1 = {"1", "H", "3", "cat", "beach", "sun"};
//        String[] photo2 = {"0", "H", "21", "tr5fv", "t99tm", "t5nnd", "tj4f", "twzgl", "t6zkl", "tcz3g", "tf5wv", "tv1h01",
//                "tv7zt", "t5xl1", "th8wv", "tkp5g", "tb5p6", "tzdr4", "t111w", "tc2j5", "t1c76", "tsmcf", "t2hzq", "tbj57"};
        SlideshowCreator creator = new SlideshowCreator(r.resultList, r.tagMap);
//        System.out.println("Best match= " + creator.match(photo2));
//
////        System.out.println("Photo 0 " + Arrays.deepToString(photo2));
//        System.out.println("Photo 18144 " + Arrays.deepToString(result[18144]));

        ArrayList<Integer> slideShow = creator.slideshow();
        System.out.println("SLIDESHOW\n" + slideShow.toString() );
        Output op = new Output(slideShow);
        op.writeToFile("lovelyLandscapesSolution.txt");

    }
}
