import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

public class Output {

    ArrayList<Integer> data;
    FileWriter fw;


    public Output(ArrayList<Integer> data) {
        this.data = data;
    }

    public void writeToFile(String name) {
        try {
            File f = new File(name);
            FileWriter fw = new FileWriter(f, false);

            fw.write(Integer.toString(data.size()));

            for (int d : data) {
                System.out.println("loopen");
                fw.write("\r\n");
                fw.write(Integer.toString(d));
            }

            System.out.println("DONE");

            fw.close();
        }
        catch (IOException e) {
            System.err.println(e);
        }
    }

}
