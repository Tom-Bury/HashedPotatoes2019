import java.util.Arrays;

public class Photo {
    private int id;
    private Boolean horizontal;
    private int tagCount;
    private String[] tags;


public Photo(String[] in){
        id = Integer.parseInt(in[0]);
        if (in[1] == "H"){ horizontal = true;}
        else horizontal = false;
        tagCount = Integer.parseInt(in[2]);
        tags = Arrays.copyOfRange(in, 3, tagCount + 3);
}


}
