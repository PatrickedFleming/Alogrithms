//class to generate maze using a random walk
//Author: Patrick Fleming
//Student Number: c3253586
//Date: 01/09/2023

import java.io.File;
import java.util.ArrayList;
import java.util.Stack;

public class MazeGenerator { 
    
    
    private static int row;
    private static int coloum;
    private static Maze gMaze;

    private static MazeNode currentNode;

    //Stack to hold nodes chosen because of the random walk
    private static Stack<MazeNode> queue = new Stack<MazeNode>();
    

    public static void main(String[] args) {
        File outPutFile;
        //check if input is valid
        try{
            row = Integer.parseInt(args[0]);
            coloum = Integer.parseInt(args[1]);
            if(row < 1 || coloum < 1){
                System.out.println("Invalid input");
                return;
            }
            outPutFile = new File(args[2]);
        }
        catch(Exception e){
            System.out.println("Invalid input");
            return;
        }

        gMaze = new Maze(row, coloum);

        startMaze();

        System.out.println(gMaze);
        
        gMaze.writeToFile(outPutFile);

    }

    //method to generate a random maze
    private static void startMaze(){
        //set start cell
        gMaze.setStartNode(gMaze.getNode(getRandom(row), getRandom(coloum)));
        //irative method to generate maze
        randomWalk(gMaze.getStartNode());
    }

    private static void randomWalk(MazeNode node){
        currentNode = node;
        int moves = 0;
        int finishNodeMoves = 0;
        queue.push(currentNode);
        while(!queue.isEmpty()){
            //get direction
            int direction = getDirection();
            //set node to visited
            currentNode.setVisited(true);
            
            switch (direction){
                case 1 -> { //north
                    currentNode.setNorthNode(gMaze.getNode(currentNode.getX() - 1, currentNode.getY()));
                    currentNode = currentNode.getNorthNode();
                    currentNode.setSouthNode(queue.peek());
                    queue.push(currentNode);
                    moves++;
                }
                case 2 -> { //south
                    currentNode.setSouthNode(gMaze.getNode(currentNode.getX() + 1, currentNode.getY()));
                    currentNode = currentNode.getSouthNode();
                    currentNode.setNorthNode(queue.peek());
                    queue.push(currentNode);
                    moves++;
                }
                case 3 -> { //east
                    currentNode.setEastNode(gMaze.getNode(currentNode.getX(), currentNode.getY() + 1));
                    currentNode = currentNode.getEastNode();
                    currentNode.setWestNode(queue.peek());
                    queue.push(currentNode);
                    moves++;
                }
                case 4 -> { //west
                    currentNode.setWestNode(gMaze.getNode(currentNode.getX(), currentNode.getY() - 1));
                    currentNode = currentNode.getWestNode();
                    currentNode.setEastNode(queue.peek());
                    queue.push(currentNode);
                    moves++;
                }
                default -> { //dead end go back
                    if(moves > finishNodeMoves){
                        gMaze.setFinishNode(currentNode);
                        finishNodeMoves = moves;
                    }
                    moves--;
                    currentNode.setNodeValue();
                    queue.pop();
                    if(!queue.isEmpty()){
                        currentNode = queue.peek();
                    }
                }
            }
        }

    }

    private static int getRandom(int size){
        return (int)Math.floor((Math.random()*size));
    }

    //returns valid random direction
    private static int getDirection(){
        ArrayList<Integer> directions = new ArrayList<Integer>();
        if(currentNode.getX() > 0 && !gMaze.getNode(currentNode.getX()-1, currentNode.getY()).getVisited()){
            directions.add(1);
        }
        if(currentNode.getX() < row - 1 && !gMaze.getNode(currentNode.getX()+1, currentNode.getY()).getVisited()){
            directions.add(2);
        }
        if(currentNode.getY() < coloum - 1 && !gMaze.getNode(currentNode.getX(), currentNode.getY()+1).getVisited()){
            directions.add(3);
        }
        if(currentNode.getY() > 0 && !gMaze.getNode(currentNode.getX(), currentNode.getY()-1).getVisited()){
            directions.add(4);
        }
        if(directions.size() == 0){
            return 0;
        }
        return directions.get(getRandom(directions.size()));
    }
}
