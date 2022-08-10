package comp1110.lab2;

/**
 * @author XIN
 * @create 2022-08-03 13:15
 */
public class Countries {
    public static void main(String[] args) {
        String[] array= {"Germany", "Netherlands", "Argentina", "Brazil"};
        for(int count = 0; count < 4; count++)
        {
            if (array[count] != "Argentina")
            {
                System.out.println(array[count]);
            }
        }
    }
}
