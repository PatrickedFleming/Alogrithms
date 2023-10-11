public class Adjacencey {
    private int pointA;
    private int pointB;
    private int weight;


    public Adjacencey(int a, int b, int w){
        this.pointA = a;
        this.pointB = b;
        this.weight = w;
    }

    public int getWeight(){
        return weight;
    }

    public boolean contains(int a, int b){
        if(pointA == a && pointB == b){
            return true;
        }
        else if(pointA == b && pointB == a){
            return true;
        }
        else{
            return false;
        }
    }
    

    @Override
    public boolean equals(Object e){
        boolean result = false;
        if (e instanceof Adjacencey){
            Adjacencey that = (Adjacencey) e;
            result = (this.pointA == that.pointA && this.pointB == that.pointB) || (this.pointA == that.pointB && this.pointB == that.pointA);
        }

        return result;

    }
}
