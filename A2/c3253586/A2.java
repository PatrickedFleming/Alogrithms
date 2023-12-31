//main class for A2 Alogrithms used to generate adjacency graph and run the algorithms
//Author: Patrick Fleming
//Date: 02/10/23

import java.io.File;
import java.util.Scanner;

public class A2 {
    
    private static int[][] adjacencyGraph;
    private static int[][] graphPoints;
    private static int[] points;

    

    public static void main(String[] args) {
        File graph = new File(args[0]);
        int N = Integer.parseInt(args[1]);
        int P = Integer.parseInt(args[2]);

        getGraph(graph);
        generateAdjacencyGraph();

        //run the algorithms
        DynaTSP dyna = new DynaTSP(adjacencyGraph, points);
        dyna.run();
        ClimbTSP climb = new ClimbTSP(points, adjacencyGraph, N, P);
        climb.run();
    }



    //reads in the graph from the file and stores the points in graphPoints
    private static void getGraph(File file){
        try{
            Scanner sc = new Scanner(file);
            int size = sc.nextInt();
            graphPoints = new int[size][2];
            adjacencyGraph = new int[size][size];
            for(int i = 0; i < size; i++){
                graphPoints[i][0] = sc.nextInt();
                graphPoints[i][1] = sc.nextInt();
            }
            sc.close();
        }
        catch(Exception e){
            System.out.println("Error: " + e);
        }
        points = new int[graphPoints.length];
        for(int i = 0; i < graphPoints.length; i++){
            points[i] = i;
        }
    }


    //calculates the distance between two points using the distance formula and stores in adjacency graph
    private static void generateAdjacencyGraph(){
        for(int i = 0; i < graphPoints.length; i++){
            for(int j = 0; j < graphPoints.length; j++){
                adjacencyGraph[i][j] = (int)Math.sqrt(Math.pow((graphPoints[i][0] - graphPoints[j][0]), 2) + Math.pow((graphPoints[i][1] - graphPoints[j][1]), 2));
            }
        }
    }
}
