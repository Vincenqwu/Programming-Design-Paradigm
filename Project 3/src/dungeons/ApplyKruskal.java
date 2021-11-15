package dungeons;

import java.util.ArrayList;
import java.util.List;

/**
 * The revised kruskal algorithm that find all tunnels that should be removed
 * in order to satisfy the 0 interconnectivity.
 */
public final class ApplyKruskal {

  private static class CaveSubset {
    Location parent;
    Location self;
    int rank;
  }

  /**
   * The static method that apply the kruskal algorithm.
   *
   * @param caveMap     2d list that includes all the initially constructed caves
   * @param tunnelsList list of all tunnels that connects caves in the caveMap
   * @param random      random number generator
   * @return a list of leftover tunnels to be removed from the dungeons.
   */
  public static List<Location> applyKruskal(List<List<Location>> caveMap,
                                            List<Location> tunnelsList, Random random) {
    int numOfCaves = caveMap.size() + caveMap.get(0).size();
    List<Location> leftOverTunnels = new ArrayList<>();

    // create an array of the caves of type Subset for the subsets of cave
    List<CaveSubset> subsetList = new ArrayList<>(numOfCaves);

    // it is used to create subset with single element
    for (List<Location> locations : caveMap) {
      for (Location location : locations) {
        CaveSubset newSubset = new CaveSubset();
        newSubset.parent = location;
        newSubset.self = location;
        subsetList.add(newSubset);
      }
    }

    List<Integer> indexList = new ArrayList<>();
    for (int i = 0; i < tunnelsList.size(); i++) {
      indexList.add(i);
    }
    int selectedIdx;
    while (indexList.size() > 0) {
      selectedIdx = indexList.get(random.nextInt(indexList.size()));
      indexList.remove(Integer.valueOf(selectedIdx));
      Location tunnel = tunnelsList.get(selectedIdx);

      Location nextSrc = findSetOfElement(subsetList, tunnel.getSrcEntrance());
      Location nextDst = findSetOfElement(subsetList, tunnel.getDstEntrance());
      if (nextSrc != nextDst) {
        performUnion(subsetList, nextSrc, nextDst);

      } else {
        leftOverTunnels.add(tunnel);
      }
    }

    return leftOverTunnels;

  }

  private static Location findSetOfElement(List<CaveSubset> subsetArray, Location location) {
    int currIdx;
    CaveSubset currSubset;
    currIdx = findLocationIndex(subsetArray, location);
    currSubset = subsetArray.get(currIdx);
    while (currSubset.parent != location) {
      currIdx = findLocationIndex(subsetArray, currSubset.parent);
      currSubset = subsetArray.get(currIdx);
      location = currSubset.parent;
    }
    return currSubset.parent;
  }

  private static int findLocationIndex(List<CaveSubset> subsetArray, Location location) {
    for (int i = 0; i < subsetArray.size(); i++) {
      if (subsetArray.get(i).self == location) {
        return i;
      }
    }
    return -1;
  }

  private static void performUnion(List<CaveSubset> subsetArray,
                                   Location sourceRoot, Location destinationRoot) {
    Location nextSourceRoot = findSetOfElement(subsetArray, sourceRoot);
    Location nextDestinationRoot = findSetOfElement(subsetArray, destinationRoot);
    int srcIdx = findLocationIndex(subsetArray, nextSourceRoot);
    int dstIdx = findLocationIndex(subsetArray, nextDestinationRoot);
    if (subsetArray.get(srcIdx).rank < subsetArray.get(dstIdx).rank) {
      subsetArray.get(srcIdx).parent = nextDestinationRoot;
    } else if (subsetArray.get(srcIdx).rank > subsetArray.get(dstIdx).rank) {
      subsetArray.get(dstIdx).parent = nextSourceRoot;
    } else {
      subsetArray.get(dstIdx).parent = nextSourceRoot;
      subsetArray.get(srcIdx).rank += 1;
    }
  }
}
