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
        System.out.println(Arrays.deepToString((r.resultList)));
        System.out.println("MAP:" + tagMap.toString());

        Photo[] photoList = r.toPhoto();
        // Roep hier de order methode op voor elk photo object

    }
}
