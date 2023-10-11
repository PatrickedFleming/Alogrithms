//Dynamic programming algorithm for TSP
//Author: Patrick Fleming
//Date: 02/10/23

import java.util.HashMap;



public class DynaTSP {
    private int[][] adjacencyGraph;
    private int[] points;

    private int count = 0;
    private HashMap<Integer, Path> paths = new HashMap<Integer, Path>();
    private HashMap<Integer, Integer> pathWeights = new HashMap<Integer, Integer>();

    private Path bestPath;

   




    public DynaTSP(int[][] graph){
        this.adjacencyGraph = graph;
        points = new int[graph.length];
        for(int i = 0; i < graph.length; i++){
            points[i] = i;
        }
    }

    public void run(){
        findPathWeight(points, points.length, count);

        setShortestPath();
        printBestPath();
    }

    private void findPathWeight(int[] path, int size, int n){
        if(size == 1){
            Path temp = new Path(path);
            temp.setWeight(calculateWeight(path));
            paths.put(count, temp);
            pathWeights.put(count, temp.getWeight());
            count++;
        }
        else{
            for(int i = 1; i < size; i++){
                findPathWeight(path, size - 1, n);
                if(size % 2 == 1){
                    int temp = path[1];
                    path[1] = path[size - 1];
                    path[size - 1] = temp;
                }
                else{
                    int temp = path[i];
                    path[i] = path[size - 1];
                    path[size - 1] = temp;
                }
            }
        }
    }

   

    private int calculateWeight(int[] path){
        int weight = 0;
        for(int i = 0; i < path.length - 1; i++){
            weight += adjacencyGraph[path[i]][path[i + 1]];
        }
        weight += adjacencyGraph[path[path.length - 1]][path[0]];
        return weight;
    }

    private void setShortestPath(){
        int shortest = Integer.MAX_VALUE;
        for(int i = 0; i < pathWeights.size(); i++){
            if(pathWeights.get(i) < shortest){
                shortest = pathWeights.get(i);
                bestPath = paths.get(i);
            }
        }
    }


    private void printBestPath(){
        System.out.println("Best Path: " + bestPath.toString() + " Weight: " + bestPath.getWeight());
    }


    

}
