//maze class to hold all maze cells
//Author: Patrick Fleming
//Student Number: c3253586
//Date: 01/09/2023

import java.io.File;
import java.io.FileWriter;

public class Maze {

    //row and coloum size of maze
    private int row;
    private int coloum;
    //start and finish nodes
    private MazeNode startNode;
    private MazeNode finishNode;

    //node conectivity list
    private MazeNode[] nodeList;

    //2d array of maze nodes
    private MazeNode[][] maze;

    

    //constructor
    public Maze(int row, int coloum){
        this.maze = new MazeNode[row][coloum];
        this.nodeList = new MazeNode[row*coloum];
        this.row = row;
        this.coloum = coloum;
        //fill maze with nodes
        fillMaze();
    }

    public MazeNode[] getCellList() {
        return nodeList;
    }

    //method to fill maze with nodes
    private void fillMaze(){
        for(int i = 0; i < row; i++){
            for(int j = 0; j < coloum; j++){
                maze[i][j] = new MazeNode(i,j);
            }
        }
    }

    public void setStartNode(MazeNode node){
        this.startNode = node;
    }

    public void setFinishNode(MazeNode node){
        this.finishNode = node;
    }

    public MazeNode getStartNode(){
        return startNode;
    }

    public MazeNode getFinishNode(){
        return finishNode;
    }

    public MazeNode getNode(int x, int y){
        return maze[x][y];
    }



    @Override
    public String toString(){
        String mazeString = "";
        mazeString += row + ":" + coloum + ":"+ startNode + ":" + finishNode + ":";
        for(int i = 0; i < row; i++){
            for(int j = 0; j < coloum; j++){
                mazeString += maze[i][j].getNodeValue();
            }
        }
        return mazeString;
    }

    public void writeToFile(File file){
        try{
            FileWriter writer = new FileWriter(file);
            writer.write(toString());
            writer.close();
        }
        catch(Exception e){
            System.out.println("Error writing to file");
        }
    }
    
}
