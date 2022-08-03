package comp1110.lab2;

/**
 * @author XIN
 * @create 2022-08-03 13:25
 */
public class Task_2_2_Reverse {
    public static void main(String[] args) {
        int number = 30;
        while (number >= 10)
        {
            System.out.print(" " + number);
            number--;
        }
        number = 30;
        System.out.println();
        do
        {
            System.out.print(" " + number);
            number--;
        }
        while (number >= 10);
    }
}
