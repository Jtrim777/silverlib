public class Point{
  int x;
  int y;
  
  public Point(int a, int b){
    x=a;
    y=b;
  }
  
  public int x(){
    return x;
  }
  public int y(){
    return y;
  }
  
  public String toString(){
    return "("+x+","+y+")";
  }
}
