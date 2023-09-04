//Cell of maze that has 4 sides
//Author: Patrick Fleming
//Student Number: c3253586
//Date: 01/09/2023


public class MazeCell {
    //position of cell in maze
    private int x;
    private int y;

    //index of cell
    private int index;

    //boolean values for each side of the cell
    private boolean northWall;
    private boolean southWall;
    private boolean eastWall;
    private boolean westWall;

    //pointers to the cells around this cell
    private MazeCell northCell;
    private MazeCell southCell;
    private MazeCell eastCell;
    private MazeCell westCell;


    //constructor
    public MazeCell(int x, int y, int ind) {
        this.x = x;
        this.y = y;
        this.index = ind;
    }

    //getters and setters
    public boolean NorthWallBlocked() {
        return northWall;
    }

    public void setNorthWall(boolean northWall) {
        this.northWall = northWall;
    }

    public boolean SouthWallBlocked() {
        return southWall;
    }

    public void setSouthWall(boolean southWall) {
        this.southWall = southWall;
    }

    public boolean EastWallBlocked() {
        return eastWall;
    }

    public void setEastWall(boolean eastWall) {
        this.eastWall = eastWall;
    }

    public boolean WestWallBlocked() {
        return westWall;
    }

    public void setWestWall(boolean westWall) {
        this.westWall = westWall;
    }

    public MazeCell getNorthCell() {
        return northCell;
    }

    public void setNorthCell(MazeCell northCell) {
        this.northCell = northCell;
    }

    public MazeCell getSouthCell() {
        return southCell;
    }

    public void setSouthCell(MazeCell southCell) {
        this.southCell = southCell;
    }

    public MazeCell getEastCell() {
        return eastCell;
    }

    public void setEastCell(MazeCell eastCell) {
        this.eastCell = eastCell;
    }

    public MazeCell getWestCell() {
        return westCell;
    }

    public void setWestCell(MazeCell westCell) {
        this.westCell = westCell;
    }

    public int getIndex() {
        return index;
    }
}