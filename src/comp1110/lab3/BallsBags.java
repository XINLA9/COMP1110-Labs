package comp1110.lab3;

/**
 * @author XIN
 * @create 2022-08-10 12:08
 */
public class BallsBags {
    private  String owner;
    private int number;

    BallsBags(String name){
        this.owner = name;
    }

    void addball(){
        number++;
    }

    void AddBalls(int amount){
        number+=amount;
    }

    void dropball(){
        number--;
    }

    void dropballs(int amount){
        number-=amount;
    }

    @Override
    public String toString() {
        return "BallsBags{" +
                "owner='" + owner + '\'' +
                ", number=" + number +
                '}';
    }

    public static void main(String[] args) {
        BallsBags bagforleo = new BallsBags("leo");
        System.out.println(bagforleo);
        bagforleo.addball();
        System.out.println(bagforleo);
        bagforleo.AddBalls(3);
        System.out.println(bagforleo);
        bagforleo.dropballs(4);
        System.out.println(bagforleo);
    }
}
