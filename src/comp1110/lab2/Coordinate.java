package comp1110.lab2;

/**
 * @author XIN
 * @create 2022-08-03 13:29
 */
public class Coordinate {
    private static int x;
    private static int y;
    public Coordinate(int x, int y){
        this.x= x;
        this.y= y;
    }
    public Coordinate(int v){
        this.x = v;
        this.y = v;
    }

    public int getX(){return x;}
    public int getY(){return y;}
    public void setX(int x) {this.x = x;}
    public void setY(int y) {this.y = y;}

    @Override

    public String toString() {
        return "("+x+", "+y+")";
    }

    public static void main(String[] args) {
        Coordinate x = new Coordinate(1,3);
        String s = x.toString();
        System.out.println(s);
    }
}
