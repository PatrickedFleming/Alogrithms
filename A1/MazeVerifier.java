//Maze verifier
//Author: Patrick Fleming
//Student Number: c3253586
//Date: 05/09/2023

import java.io.File;
import java.util.Stack;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Scanner;

public class MazeVerifier {

    private static File mazeFile;
    private static File solutionFile;

    private static int nodesWithNoConnections = 0;
    private static int nodesWithTooManyConnections =0;
    private static Stack<MazeNode> dfsStack = new Stack<MazeNode>();
    private static LinkedList<MazeNode> Visited = new LinkedList<MazeNode>();;
    private static LinkedList<Stack<Integer>> SolutionPaths = new LinkedList<Stack<Integer>>();






    private static Maze maze;

    public static void main(String[] args){
        try{
            mazeFile = new File(args[0]);
            solutionFile = new File(args[1]);
        }
        catch(Exception e){
            System.out.println("Invalid input");
            return;
        }

        maze = new Maze(mazeFile);

        verifyMaze();
        Stack<Integer> Sp = new Stack<Integer>();
        printMaze(Sp);
        getSolutions(solutionFile);
        //print solution paths
        for(Stack<Integer> s : SolutionPaths){
            printMaze(s);
        }
    }

    public static void verifyMaze(){
        checkNodeConnections();
        checkMazePaths(maze.getStartNode());
    }

    private static void checkNodeConnections(){
        MazeNode[][] mazeNodes = maze.getMaze();
        for(int row = 0; row < maze.getRow(); row++){
            for(int col = 0; col < maze.getColoum(); col++){
                MazeNode node = mazeNodes[row][col];
                if(node != null){
                    MazeNode[] neighbours = node.getNeighbours();
                    int count = 0;
                    for(MazeNode n : neighbours){
                        if(n == null){
                            count++;
                        }
                    }
                    switch (count){
                        case 4 -> nodesWithNoConnections++;
                        case 0 -> nodesWithTooManyConnections++;
                    }
                }
            }
        }
        System.out.println(nodesWithNoConnections + " nodes with no connections");
        System.out.println(nodesWithTooManyConnections + " nodes with too many connections");
    }


    private static void checkMazePaths(MazeNode start){
        dfsStack.push(start);
        boolean circulerPath = false;
        while(!dfsStack.isEmpty()){
            MazeNode currentNode = dfsStack.pop();
            if(currentNode.getVisited()){
                System.out.println("Circular path found");
                circulerPath = true;
            }
            Visited.add(currentNode);
            MazeNode[] neighbours = currentNode.getNeighbours();
            for(MazeNode n : neighbours){
                if(n != null && !Visited.contains(n)){
                    dfsStack.push(n);
                }
            }
        }
        if(!circulerPath){
            System.out.println("No circular paths found");
        }
        if(Visited.size() != maze.getRow()*maze.getColoum()){
            System.out.println("Not all nodes were visited");
        }
        else{
            System.out.println("All nodes were visited");
        }
    }


    //print maze to console showing all walls
    private static void printMaze(Stack<Integer> SolutionPath){
        MazeNode start = maze.getStartNode();
        MazeNode finish = maze.getFinishNode();
        MazeNode[][] mazeNodes = maze.getMaze();
        String[] output = new String[maze.getRow()*2+1];
        Arrays.fill(output, "");
        //add top wall
        for(int i = 0; i < maze.getColoum(); i++){
            output[0] += "____";
        }
        int i = 1;
        for(int row = 0; row < maze.getRow(); row++){
            output[i] += "| ";
            output[i+1] += "|";
            for(int col = 0; col < maze.getColoum(); col++){
                MazeNode node = mazeNodes[row][col];
                if(node != null){
                    if(node == start){
                        output[i] += "S";
                    }
                    else if(node == finish){
                        output[i] += "F";
                    }
                    else if (!SolutionPath.isEmpty()) {
                        if (SolutionPath.contains(node.getNodeNumber())) {
                            output[i] += "*";
                        }
                        else{
                            output[i] += " ";
                        }
                    }
                    else{
                        output[i] += " ";
                    }
                    switch (node.getNodeValue()){
                        case 0 ->{
                            output[i] += " | ";
                            output[i+1] += "---|";
                        }
                        case 1 ->{
                            output[i] += "   ";
                            output[i+1] += "----";
                        }
                        case 2 ->{
                            output[i] += " | ";//right wall
                            output[i+1] += "   |";//bottom wall
                        }
                        case 3 ->{
                            output[i] += "   ";
                            output[i+1] += "   |";
                        }
                    }
                }
                
            }
            i+= 2;
        }
        for(String s : output){
            if(s != null){
                System.out.println(s);
            }
        }
    }

    //get solution path from file
    private static void getSolutions(File file){
        try{
            Scanner sc = new Scanner(file);
            while(sc.hasNextLine()){
                Stack<Integer> Sp = new Stack<Integer>();
                String[] solution = sc.nextLine().split(":");
                solution = solution[1].split(",");
                
                solution[0] = solution[0].substring(1);
                solution[solution.length-1] = solution[solution.length-1].substring(0, solution[solution.length-1].length()-1);
                for(int i =0; i < solution.length; i++){
                    Sp.push(Integer.parseInt(solution[i]));
                }
                SolutionPaths.add(Sp);
            }
            sc.close();
        }
        catch(Exception e){
            System.out.println("Invalid input");
        }
    }
}
