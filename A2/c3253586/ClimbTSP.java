//Hill climbing algorithm for TSP
//Author: Patrick Fleming
//Date: 02/10/23


public class ClimbTSP {

    private int[][] adjacencyGraph;

    private int iterations;
    private int restarts;

    private int[] bestPath;

    
    //constructor sets up all data structures
    public ClimbTSP(int[] points, int[][] graph, int n, int p){
        this.adjacencyGraph = graph;
        this.iterations = n;
        this.restarts = p;
        this.bestPath = new int[points.length + 1];

        for(int i = 0; i < points.length; i++){
            bestPath[i] = i;
        }
        bestPath[points.length] = 0;

    }


    //runs the algorithm
    public void run(){
        long startTime = System.nanoTime();
        solve(bestPath);
        long endTime = System.nanoTime();
        System.out.println("Hill Climbing");
        System.out.print("Solution: ");
        for(int i = 0; i < bestPath.length; i++){
            System.out.print(bestPath[i] + " ");
        }
        System.out.println();
        System.out.println("Length: " + pathLength(bestPath));
        System.out.println("Time: " + (endTime - startTime) + " ns");
    }


    //iterates through the algorithm n times and restarts p times
    private void solve(int[] path){
       int n = 0;
       while(n < iterations){
            int[] newPath = randomPath(path);
            for(int i = 0; i < restarts; i++){
                int[] swapPath = swapPath(newPath);
                if(pathLength(swapPath) < pathLength(newPath)){
                    bestPath = swapPath;
                    i = restarts;
                }
                else{
                    bestPath = newPath;
                    i = restarts;
                }
            }
            n++;
        }
    }


    //reverse the path between n-1 and i where i is a random number between 0 < n-1
    private int[] swapPath(int[] path){
        int i = (int)(Math.random() * (path.length - 2)) + 1;
        int[] newPath = new int[path.length];
        for(int j = 0; j < i; j++){
            newPath[j] = path[j];
        }
        int k = 0;
        for(int j = i; j < path.length - 1; j++){
            newPath[j] = path[path.length - 2 - k];
            k++;
        }
        newPath[path.length - 1] = path[path.length - 1];
        return newPath;
    }

    //randomly swaps two points in the path
    private int[] randomPath(int[] path){
        int[] newPath = new int[path.length];
        for(int i = 0; i < path.length ; i++){
            newPath[i] = path[i];
        }
        int i = (int)(Math.random() * (path.length - 2)) + 1;
        int j = (int)(Math.random() * (path.length - 2)) + 1;
        int temp = newPath[i];
        newPath[i] = newPath[j];
        newPath[j] = temp;
        return newPath;
    }


    //calculates the length of the path
    private int pathLength(int[] path){
        int length = 0;
        for(int i = 0; i < path.length - 1; i++){
            length += adjacencyGraph[path[i]][path[i + 1]];
        }
        return length;
    }

}
