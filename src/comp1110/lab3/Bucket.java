package comp1110.lab3;

/**
 * @author XIN
 * @create 2022-08-10 12:11
 */
public class Bucket {
     private double capacity;
     private double contents;
//     构造函数
     Bucket(final double capacity){
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
     //清空桶
     public double empty(){
          double flow = contents;
          contents = 0;
          return flow;
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
          Bucket small = new Bucket(1.0);
          Bucket big = new Bucket(10.0);

          small.add(20.0);
          big.add(20.0);
          big.empty();
          System.out.println(big.getContents());

          big.add(big.empty());
          System.out.println(small.getContents());
          System.out.println(big.getContents());
     }
}
