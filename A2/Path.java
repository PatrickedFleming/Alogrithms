public class Path {
    private int[] path;
    private String pathString;
    private int weight;

    public Path(int[] p){
        this.path = p;
        this.pathString = printPath(p);
    }

    public int[] getPath(){
        return path;
    }

    private String printPath(int[] path){
        String s = "";
        for(int i = 0; i < path.length; i++){
            s += path[i] + " ";
        }
        s += path[0];
        return s;
    }

    public int getWeight(){
        return weight;
    }

    public void setWeight(int w){
        this.weight = w;
    }

    @Override
    public String toString(){
        return pathString;
    }
}
