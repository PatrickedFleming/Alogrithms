//Dynamic programming algorithm for TSP
//Author: Patrick Fleming
//Date: 02/10/23


public class DynaTSP {
    //int array to hold the distance between each point
    private int[][] adjacencyGraph;
    private int[] points;

    private int[][] dpStates;
    private int limit;

    private int[] bestPath;

    //constructor sets up all data structures
    public DynaTSP(int[][] graph, int[] points){
        this.adjacencyGraph = graph;
        this.points = points;
        this.limit = (int) Math.pow(2, points.length) - 1;
        this.bestPath = new int[points.length + 1];
        dpStates = new int[points.length][limit];
        for(int i = 0; i < points.length; i++){
            for(int j = 0; j < limit; j++){
                dpStates[i][j] = -1;
            }
        }
    }

    //runs the algorithm
    public void run(){
        long startTime = System.nanoTime();
        int ans = solve(0, 1, 0);
        long endTime = System.nanoTime();
        System.out.println("Dynamic Programming");
        System.out.print("Solution: ");
        for(int i = 0; i < bestPath.length; i++){
            System.out.print(bestPath[i] + " ");
        }
        System.out.println();
        System.out.println("Length: " + ans);
        System.out.println("Time: " + (endTime - startTime) + " ns");
    }

    //recursive function to solve the problem   
    public int solve(int index, int mask, int step){
        if(mask == limit){
            return adjacencyGraph[index][0];
        }

        if(dpStates[index][mask] != -1){
            return dpStates[index][mask];
        }

        int min = Integer.MAX_VALUE;
        step++;
        for(int i = 0; i < points.length; i++){
            if((mask & (1 << i)) == 0){
                int newMask = mask | (1 << i);
                min = Math.min(min, solve(i, newMask, step) + adjacencyGraph[index][i]);
            }
            
        }
        

        return dpStates[index][mask] = min;
    }   
}
