//HashFunction for 2 points
//Author Patrick Fleming
//Date: 12/10/23

public class HashFunction {
    //credit: http://szudzik.com/ElegantPairing.pdf
    public static int pairWiseHash(int x, int y){
        if(x >= y){
            return x * x + x + y;
        }
        else{       //changed from original to make it the same result weither or not its x,y or y,x
            return y * y + y + x;
        }
    }
}
