package base.utils;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

public class Grid {

  private Map<Coord, String> grid;

  public Grid(List<String> in){
    grid = new HashMap<>();
    for (int r = 0; r < in.size(); r++) {
      String[] inputLine = in.get(r).split("");
      for (int c = 0; c < inputLine.length; c++) {
        grid.put(new Coord(r, c), inputLine[c]);
      }
    }
  }

  public List<Coord> findTargets(String target){
    List<Coord> targets = new ArrayList<>();
    for (Entry<Coord, String> coordStringEntry : grid.entrySet()) {
      if (coordStringEntry.getValue().equals(target)){
        targets.add(coordStringEntry.getKey());
      }
    }
    return targets;
  }

}
