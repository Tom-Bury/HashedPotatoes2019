import java.util.Arrays;
import java.util.Iterator;
import java.util.List;

public class Photo {
    private int id;
    private Boolean horizontal;
    private int tagCount;
    private String[] tags;

    public int getId() {
        return id;
    }

    public Boolean getHorizontal() {
        return horizontal;
    }

    public int getTagCount() {
        return tagCount;
    }

    public String[] getTags() {
        return tags;
    }

    public Photo(String[] in){
        id = Integer.parseInt(in[0]);
        if (in[1] == "H"){ horizontal = true;}
        else horizontal = false;
        tagCount = Integer.parseInt(in[2]);
        tags = Arrays.copyOfRange(in, 3, tagCount + 3);
}

public int compare(Photo photo2){
    int common = 0;
    int only1 = 0;
    int only2 = 0;
    String [] tags2 = photo2.getTags();
    List<String> tagList = Arrays.asList(tags2);
    Iterator<String> itr = tagList.iterator();
    String currentTag2 = itr.next();
    int c;
    for (String tag : tags){
        c = tag.compareTo(currentTag2);
            if (c==0){
                common += 1; currentTag2 = itr.next();
            }
            else if (c > 0 && itr.hasNext()){
                currentTag2 = itr.next();
                only2 +=1;
            }
            else only1 ++;
    }

    while (itr.hasNext()){
        only2 ++;
    }
    return Math.min(common, Math.min(only1, only2));
}
}
