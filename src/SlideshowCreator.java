import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class SlideshowCreator {
    Map<String, ArrayList<Integer>> tagMap;
    ArrayList<Integer> usedIDs = new ArrayList<>();

    /**
     * find the best match for this photo
     * @param photo
     */
    private int match(String[] photo) {
        int goal = Integer.parseInt(photo[2])/2;
        int bestCount = Integer.MAX_VALUE;
        int bestID = 0;
        int i = 3;
        Map<Integer,Integer> count = new HashMap<>();
        while (i < photo.length) {
            ArrayList<Integer> list = tagMap.get(photo[i]);
            for (int id : list) {
                if (id != Integer.parseInt(photo[0])) {
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
        }

        for (int id : count.keySet()) {
            if (Math.abs(count.get(id) - goal) < bestCount) {
                bestID = id;
                bestCount = Math.abs(count.get(id) - goal);
            }
        }

        return bestID;
    }

}
