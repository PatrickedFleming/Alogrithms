//maze class to hold all maze cells
//Author: Patrick Fleming
//Student Number: c3253586
//Date: 01/09/2023

import java.io.File;
import java.io.FileWriter;
import java.util.Scanner;

public class Maze {

    //row and coloum size of maze
    private int row;
    private int coloum;
    //start and finish nodes
    private MazeNode startNode;
    private int startNodeNumber;
    private MazeNode finishNode;
    private int finishNodeNumber;

    //node conectivity list
    private String nodeList;

    //2d array of maze nodes
    private MazeNode[][] maze;

    //maze node number
    private int nodeNumber = 1;

    

    //constructor
    public Maze(int row, int coloum){
        this.maze = new MazeNode[row][coloum];
        this.row = row;
        this.coloum = coloum;
        //fill maze with nodes
        fillMaze();
    }


    //create maze from file
    public Maze(File file){
        getMaze(file);
        fillMaze();
        for(int i = 0; i < row; i++){
            for(int j = 0; j < coloum; j++){
                if(maze[i][j].getNodeNumber() == startNodeNumber){
                    this.startNode = maze[i][j];
                }
                if(maze[i][j].getNodeNumber() == finishNodeNumber){
                    this.finishNode = maze[i][j];
                }
            }
        }
        setNodeValues();
        connectMaze();
    }

    //gets maze info from file
    private void getMaze(File mazeFile){
         try{
            Scanner sc = new Scanner(mazeFile);
            String[] mazeInfo = sc.nextLine().split(":");
            this.row = Integer.parseInt(mazeInfo[0]);
            this.coloum = Integer.parseInt(mazeInfo[1]);
            this.maze = new MazeNode[row][coloum];
            this.startNodeNumber = Integer.parseInt(mazeInfo[2]);
            this.finishNodeNumber = Integer.parseInt(mazeInfo[3]);
            this.nodeList = mazeInfo[4];
            sc.close();
        }
        catch(Exception e){
            System.out.println("Invalid input");
            return;
        }
    }

    public String getCellList() {
        return nodeList;
    }

    //method to fill maze with nodes
    private void fillMaze(){
        for(int i = 0; i < row; i++){
            for(int j = 0; j < coloum; j++){
                maze[i][j] = new MazeNode(i,j);
                maze[i][j].setNodeNumber(nodeNumber);
                nodeNumber++;
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

    public int getRow(){
        return row;
    }

    public int getColoum(){
        return coloum;
    }

    public MazeNode[][] getMaze(){
        return maze;
    }
    //sets node values based on node list from file
    private void setNodeValues(){
        int nodeValue = 0;
        for(int i = 0; i < row; i++){
            for(int j = 0; j < coloum; j++){
                maze[i][j].setNodeValue(Character.getNumericValue(nodeList.charAt(nodeValue)));
                nodeValue++;
            }
        }
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
        mazeString += "\n";
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

    //method to connect maze nodes to each other based on node values
    // 0 = both south and east null
    // 1 = east connected to next node but south null
    // 2 = south connected but east null
    // 3 = both south and east connected
    private void connectMaze(){
        for(int i = 0; i < row; i++){
            for(int j = 0; j < coloum;j++){
                switch (maze[i][j].getNodeValue()){
                    case 1 -> {
                        maze[i][j].setEastNode(maze[i][j+1]);
                        maze[i][j+1].setWestNode(maze[i][j]);
                    }
                    case 2 -> {
                        maze[i][j].setSouthNode(maze[i+1][j]);
                        maze[i+1][j].setNorthNode(maze[i][j]);
                    }
                    case 3 -> {
                        maze[i][j].setEastNode(maze[i][j+1]);
                        maze[i][j+1].setWestNode(maze[i][j]);
                        maze[i][j].setSouthNode(maze[i+1][j]);
                        maze[i+1][j].setNorthNode(maze[i][j]);
                    }
                }
            }
        }
    }
}
