//maze class to hold all maze cells
//Author: Patrick Fleming
//Student Number: c3253586
//Date: 01/09/2023

public class Maze {

    //start and finish cells
    private MazeCell startCell;
    private MazeCell finishCell;

    //cell conectivity list
    private MazeCell[] cellList;

    //2d array of maze cells
    private MazeCell[][] maze;


    //constructor

    public Maze(int row, int coloum){
        maze = new MazeCell[row][coloum];
        cellList = new MazeCell[row*coloum];
    }


    //getters and setters
    public MazeCell getStartCell() {
        return startCell;
    }

    public void setStartCell(MazeCell startCell) {
        this.startCell = startCell;
    }

    public MazeCell getFinishCell() {
        return finishCell;
    }

    public void setFinishCell(MazeCell finishCell) {
        this.finishCell = finishCell;
    }

    public MazeCell[] getCellList() {
        return cellList;
    }

    public void setCellList(MazeCell[] cellList) {
        this.cellList = cellList;
    }

    public MazeCell[][] getMaze() {
        return maze;
    }

    public void setMaze(MazeCell[][] maze) {
        this.maze = maze;
    }

    

}
