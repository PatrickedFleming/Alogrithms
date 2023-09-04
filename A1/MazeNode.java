//class to hold maze cell information
//Author: Patrick Fleming
//Student Number: c3253586
//Date: 04/09/2023

public class MazeNode {
    private int nodeX;
    private int nodeY;
    private int nodeNumber;
    private int nodeValue;

    private static int nodeCount = 1;

    private MazeNode northNode = null;
    private MazeNode southNode = null;
    private MazeNode eastNode = null;
    private MazeNode westNode = null;

    private boolean visited = false;


    //constructor
    public MazeNode(int x, int y){
        nodeX = x;
        nodeY = y;
        nodeNumber = nodeCount;
        nodeCount++;
    }
    //gets
    public int getNodeNumber() {
        return nodeNumber;
    }

    public int getNodeValue() {
        return nodeValue;
    }
    
    public int getX() {
        return nodeX;
    }

    public int getY() {
        return nodeY;
    }

    public MazeNode getNorthNode() {
        return northNode;
    }

    public MazeNode getSouthNode() {
        return southNode;
    }

    public MazeNode getEastNode() {
        return eastNode;
    }

    public MazeNode getWestNode() {
        return westNode;
    }

    public void setNodeValue(){
        if(southNode == null && eastNode == null){
            nodeValue = 0;
        }
        else if (southNode == null && eastNode != null){
            nodeValue = 1;
        }
        else if (southNode != null && eastNode == null){
            nodeValue = 2;
        }
        else{
            nodeValue = 3;
        }
    }

    public void setVisited(boolean value){
        visited = value;
    }

    public boolean getVisited(){
        return visited;
    }


    //sets pointers
    public void setNorthNode(MazeNode value) {
        northNode = value;
    }

    public void setSouthNode(MazeNode value) {
        southNode = value;
    }

    public void setEastNode(MazeNode value) {
        eastNode = value;
    }

    public void setWestNode(MazeNode value) {
        westNode = value;
    }

    public MazeNode getNode(int direction){
        switch(direction){
            case 1 -> {
                return northNode;
            }
            case 2 -> {
                return southNode;
            }
            case 3 -> {
                return eastNode;
            }
            case 4 -> { 
                return westNode;
            }   
            default -> {
                return null;
            }
        }
    }

    @Override
    public String toString(){
        String out = "";
        out += nodeNumber;
        return out;
    }
}
