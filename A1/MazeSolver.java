//Maze solver class 
//Author: Patrick Fleming
//Student Number: c3253586
//Date: 05/09/2023

import java.io.File;
import java.io.FileWriter;

public class MazeSolver {

    private static File inputFile;
    private static File outputFile;

    //Maze for solvers
    private static Maze mazeDFS;
    private static Maze mazeBFS;

    //Solvers
    private static DFS dfs;
    private static BFS bfs;

    public static void main(String[] args){
        try{
            inputFile = new File(args[0]);
            outputFile = new File(args[1]);
        }
        catch(Exception e){
            System.out.println("Invalid input");
            return;
        }

        mazeDFS = new Maze(inputFile);
        mazeBFS = new Maze(inputFile);

        dfs = new DFS(mazeDFS);
        bfs = new BFS(mazeBFS);

        dfs.solve();
        bfs.solve();


        String output = dfs.fileFormat() + bfs.fileFormat();
        writeToFile(output);

    }

    private static void writeToFile(String out){
        try{
            FileWriter writer = new FileWriter(outputFile);
            writer.write(out);
            writer.close();
        }
        catch(Exception e){
            System.out.println("Error writing to file");
        }
    }
    
}
