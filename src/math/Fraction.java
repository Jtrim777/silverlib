package silverlib.math;

public class Fraction{
  int num;
  int den;
  double dec;
  
  public Fraction(int n,int d){
    num = n;
    den = d;
    dec = (double)num/(double)den;
  }
  
  public int num(){return num;}
  public int den(){return den;}
  public double decimal(){return dec;}
  
  public void simplify(){
    int gcd = Num.GCD(num,den);
    num /= gcd;
    den /= gcd;
  }
  
  public String toString(){
    return num+"/"+den;
  }
}
