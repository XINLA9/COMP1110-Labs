package comp1110.lab3;

/**
 * @author XIN
 * @create 2022-08-10 12:11
 */
public class bucket {
     private double capacity;
     private double contents;
//     构造函数
     bucket(final double capacity){
          this.capacity = capacity;
     }
//获取容量
     public double getCapacity() {
          return capacity;
     }
     //获取当前容量
     public double getContents() {
          return contents;
     }

     public void setContents(double contents) {
          this.contents = contents;
     }

     public void setCapacity(double capacity) {
          this.capacity = capacity;
     }

     //清空桶
     void empty(){
          this.contents = 0;
     }
//     往桶中加水
     void add(double amount){
          if ((amount+contents)<=capacity){
               contents = contents + amount;
          }
          else{
               contents = capacity;
          }
     }

     public static void main(String[] args) {
          bucket small = new bucket (1.0);
          bucket big = new bucket(10.0);
          small.add(20.0);
          big.add(20.0);
          big.empty();
          System.out.println(big.getContents());
          big.add(small.getContents());
          big.empty();
          System.out.println(small.getContents());
          System.out.println(big.getContents());
     }
}
