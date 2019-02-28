import javax.print.DocFlavor;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class SlideshowCreator {
    String[][] allPhotos;
    Map<String, ArrayList<Integer>> tagMap;
    ArrayList<Integer> usedIDs = new ArrayList<>();
    int nbPhotos;
    String[] lastSlide;


    public SlideshowCreator(String[][] allPhotos, Map<String, ArrayList<Integer>> tagMap) {
        this.allPhotos = allPhotos;
        this.tagMap = tagMap;
        this.nbPhotos = Integer.parseInt(allPhotos[0][0]);
    }

    /**
     * find the best match for this photo
     * @param photo
     */
    public int match(String[] photo) {
        int goal = Integer.parseInt(photo[2])/2;
        int bestCount = Integer.MAX_VALUE;
        int bestID = 0;
        int i = 3;
        Map<Integer,Integer> count = new HashMap<>();
        while (i < photo.length) {
            ArrayList<Integer> list = tagMap.get(photo[i]);
            for (int id : list) {
                if (id != Integer.parseInt(photo[0]) && !usedIDs.contains(id)) {
                    if (count.containsKey(id)) {
                        int curr = count.get(id);
                        curr += 1;
                        count.put(id,curr);
                    }
                    else {
                        count.put(id,1);
                    }
                }
            }
            i++;
        }

        if (count.isEmpty()) {
            int newID =  Integer.parseInt(photo[0]) + 1;
            usedIDs.add(newID);
            return newID;
        }

        for (int id : count.keySet()) {
            if (Math.abs(count.get(id) - goal) < bestCount) {
                bestID = id;
                bestCount = Math.abs(count.get(id) - goal);
            }
        }

        usedIDs.add(bestID);
        lastSlide = allPhotos[bestID];
        return bestID;
    }

    public ArrayList<Integer> slideshow() {
        int id = 1;
        ArrayList<Integer> slideShow = new ArrayList<>();
        slideShow.add(1);
        usedIDs.add(1);
        while (usedIDs.size() < nbPhotos) {
            int nextID = match(allPhotos[id]);
            slideShow.add(nextID);
            id = nextID;
            System.out.println(usedIDs.size());
        }

        return slideShow;
    }

}
