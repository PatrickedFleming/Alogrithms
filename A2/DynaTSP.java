//Dynamic programming algorithm for TSP
//Author: Patrick Fleming
//Date: 02/10/23

import java.util.HashMap;
import java.util.HashSet;

public class DynaTSP {
    private int[][] adjacencyGraph;
    private int[][] graphPaths;
    private HashMap<Integer, Integer> pathLengths;
    private HashSet<Integer> pathSets = new HashSet<Integer>();


    public DynaTSP(int[][] graph){
        this.adjacencyGraph = graph;
        graphPaths = new int[graph.length][graph.length];
        run();
    }

    private void run(){
        
    }

   


}
