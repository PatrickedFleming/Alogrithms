//Deapth First Solver
//Author: Patrick Fleming
//Student Number: c3253586
//Date: 05/09/2023

import java.util.Stack;

public class DFS {

    private Maze maze;
    private Stack<MazeNode> Solution = new Stack<MazeNode>();
    private int PathStepCount;

    private long timeTaken;

    private MazeNode currentNode;
    

    public DFS(Maze maze){
        this.maze = maze;
    }

    public void solve(){
        long start = System.currentTimeMillis();
        MazeNode startNode = maze.getStartNode();
        MazeNode finishNode = maze.getFinishNode();
        currentNode = startNode;
        dfs(currentNode, finishNode);
        long end = System.currentTimeMillis();
        timeTaken = end - start;
        System.out.println(this);
    }

    private void dfs(MazeNode node, MazeNode finishNode){
        while(currentNode != finishNode){
            currentNode.setVisited(true);
            PathStepCount++;
            Solution.push(currentNode);
            currentNode = getNextNode(finishNode);
            if(currentNode == null){
                Solution.pop();
                currentNode = Solution.pop();
            }
        }
    }

    private MazeNode getNextNode(MazeNode finish){
        MazeNode[] nodes = currentNode.getNeighbours();
        MazeNode nextNode = null;
        for(MazeNode n : nodes){
            if(n != null && !n.getVisited()){
                nextNode = n;
            }
        }
        if(nextNode == finish){
            Solution.push(nextNode);
            PathStepCount++;
        }
        return nextNode;
    }


    @Override
    public String toString(){
        String output = "DFS\n";
        output += "(";
        for(MazeNode n : Solution){
            output += n.getNodeNumber() + ",";
        }
        output = output.substring(0, output.length()-1);
        output += ")\n";
        output +=  Solution.size()-1 + "\n";
        if(PathStepCount != Solution.size()-1){
            output +=  PathStepCount+"\n";
        }
        output += timeTaken;
        return output;
    }

    public String fileFormat(){
        String output = "";
        output += Solution.size()-1 + ":(";

        for(MazeNode n : Solution){
            output += n.getNodeNumber() + ",";
        }
        output = output.substring(0, output.length()-1);
        output += ")\n";
        return output;
    }


}
