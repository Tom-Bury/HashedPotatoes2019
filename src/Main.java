import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class Main {

    public static Map<String, ArrayList<Integer>> tagMap = new HashMap<>();

    public static void main(String[] args) throws FileNotFoundException {
        ReadInput r = new ReadInput("./src/a_example.txt");
        r.arrayFromFile();
        r.deleteNulls();
//        System.out.println(Arrays.deepToString((r.resultList)));
        System.out.println("MAP:" + tagMap.toString());

        Photo[] photoList = r.toPhoto();

        System.out.println(Arrays.deepToString(photoList));
        Photo photo1 = photoList[1];
        Photo photo2 = photoList[3];
        System.out.println(photo1.toString() + " photo 2 : " + photo2.toString());
        int result1 = photo1.compare(photo2);
        int result2 = photo2.compare(photo1);
        System.out.println("photo1 compared to photo2: " + result1 + " photo2 compare to photo1 " + result2);

    }
}
