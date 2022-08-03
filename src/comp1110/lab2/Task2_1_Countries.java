package comp1110.lab2;

/**
 * @author XIN
 * @create 2022-08-03 13:15
 */
public class Task2_1_Countries {
    public static void main(String[] args) {
        String[] array= {"Germany", "Argentina", "Netherlands", "Brazil"};
        for(int count = 0; count < 4; count++)
        {
            if (count == 1)
            {
                continue;
            }
            System.out.print(array[count]+" ");
        }

    }
}
