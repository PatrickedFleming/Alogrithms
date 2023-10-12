//Dynamic programming algorithm for TSP
//Author: Patrick Fleming
//Date: 02/10/23

import java.util.HashMap;



public class DynaTSP {
    private HashMap<Integer, Adjacencey> adjacencyGraph;
    private int[] points;

    private Path bestPath;
    private int bestWeight = Integer.MAX_VALUE;

   




    public DynaTSP(HashMap<Integer, Adjacencey> graph, int[] points){
        this.adjacencyGraph = graph;
        this.points = points;
    }

    public void run(){
        findPathWeight(points, points.length);
        printBestPath();
    }

    private void findPathWeight(int[] path, int size){
        if(size == 1){
            calculateWeight(path);
        }
        else{
            for(int i = 1; i < size; i++){
                findPathWeight(path, size - 1);
                if(size % 2 == 0){
                    int temp = path[i];
                    path[i] = path[size - 1];
                    path[size - 1] = temp;
                }
                else{
                    int temp = path[1];
                    path[1] = path[size - 1];
                    path[size - 1] = temp;
                }
            }
        }
    }

   

    private void calculateWeight(int[] path){
        int weight = 0;
        for(int i = 0; i < path.length - 1; i++){
            weight += adjacencyGraph.get(HashFunction.pairWiseHash(path[i], path[i + 1])).getWeight();
        }
        weight += adjacencyGraph.get(HashFunction.pairWiseHash(path[path.length - 1], path[0])).getWeight();
        if(weight < bestWeight){
            bestPath = new Path(path);
            bestPath.setWeight(weight);
        }
    }


    private void printBestPath(){
        System.out.println("Best Path: " + bestPath.toString() + " Weight: " + bestPath.getWeight());
    }


    

}
