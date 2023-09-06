//Breadth first solver class
//Author: Patrick Fleming
//Student Number: c3253586
//Date: 05/09/2023

import java.util.LinkedList;
import java.util.Queue;


public class BFS {
    private Maze maze;
    private LinkedList<MazeNode> Solution = new LinkedList<MazeNode>();
    private Queue<MazeNode> queue = new LinkedList<MazeNode>();
    private int PathStepCount;

    private long timeTaken;

    private MazeNode currentNode;

    public BFS(Maze maze){
        this.maze = maze;
    }

    public void solve(){
        long start = System.currentTimeMillis();
        MazeNode startNode = maze.getStartNode();
        MazeNode finishNode = maze.getFinishNode();
        currentNode = startNode;
        queue.add(currentNode);
        bfs(currentNode, finishNode);
        long end = System.currentTimeMillis();
        timeTaken = end - start;
        System.out.println(this);
    }

    private void bfs(MazeNode node, MazeNode finishNode){
        while(!queue.isEmpty()){
            currentNode.setVisited(true);
            currentNode = queue.remove();
            addNeighboursToQueue(currentNode);
            PathStepCount++;
            if(currentNode == finishNode){
                traceParents(currentNode, maze.getStartNode());
                break;
            }
        }
    }

    private void addNeighboursToQueue(MazeNode finish){
        MazeNode[] nodes = currentNode.getNeighbours();
        for(MazeNode n : nodes){
            if(n != null && !n.getVisited()){
                n.setParent(currentNode);
                queue.add(n);
            }
        }
        
    }

    private void traceParents(MazeNode node,MazeNode startNode){
        while(node != startNode){
            Solution.addFirst(node);
            node = node.getParent();
        }
        Solution.addFirst(startNode);
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

    @Override
    public String toString(){
        String output = "BFS\n";
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
}
