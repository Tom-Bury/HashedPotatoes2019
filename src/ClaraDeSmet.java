import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;

public class ClaraDeSmet {

    public static void main(String [] args) throws FileNotFoundException {
        System.out.println("Hash code here we come!");
        System.out.println("This is a test." );
        int[][] result = intFromTxt(new File ("./src/a_example.in")) ;
        System.out.println(Arrays.deepToString(result));
    }

    /**
     * Scan a txt (or in) file for integers and put them into a 2-dimensional array
     * @param f the file to scan
     * @return an array of dimension 2
     * @throws FileNotFoundException
     */
    public static int[][] intFromTxt(File f) throws FileNotFoundException {
        Scanner s = new Scanner(f); // new scanner for file
        int[][] fullList = new int[30][]; // the big list, has to have a fixed nb of rows. Need to remove that afterwards maybe?
        int index = 0;
        while (s.hasNextLine()) { // check for next line
            int[] tempArray = processLine(s.nextLine()); // process the line into an array
            fullList[index] = tempArray; // add to the bigger array
            index++; // increment index
        }
        return fullList;
    }

    /**
     * Given a line of integers, process them into an array
     * @param l The line to scan
     * @return an array of integers
     */
    public static int[] processLine(String l) {
        Scanner s = new Scanner(l); // new scanner for the line
        List<Integer> list = new ArrayList<>();

        while (s.hasNextInt()) { // check for next integer
            list.add(s.nextInt()); // add a new integer to the integer array
        }

        int[] ints = new int[list.size()]; // make the ints array only as big as necessary

        for (int index = 0; index < list.size(); index++) {
            ints[index] = list.get(index); // transfer integers from list to ints
        }
        return ints;
    }
}
