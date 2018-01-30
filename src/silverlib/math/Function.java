public abstract class Function{
  protected double slope;
  protected double xTrans;
  protected double yTrans;
  protected int xReflect;
  protected int yRefelct;

  public Function(double a,double h,double k){
    slope = a;
    xTrans = h;
    yTrans = k;
  }

  public double fx(double x);

  public int fx(int x);

  public float fx(float x);

  public Function translateY(double amt){
    yTrans += amt;

    return this;
  }

  public Function translateX(double amt){
    xTrans += amt;

    return this;
  }

  public Function reflectX(){
    xReflect *= -1;

    return this;
  }

  public Function reflectY(){
    yReflect *= -1;

    return this;
  }

  public String toString();
}