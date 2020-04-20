import processing.core.*; 

public class Bezier {
  public   float[] s, o;
  public PVector[]  p;
  public PVector[] dp;
  PApplet parent;
  
  Boolean showHandles;
  
  public Bezier(PApplet parent) {
    this.parent = parent;
    
    this.s = new float[2];
    this.o = new float[2];
    
    this.p  = new PVector[2];
    this.dp = new PVector[2];
    
    this.s[0] = 1;
    this.s[1] = 1;
    
    this.o[0] = 0;
    this.o[1] = 0;
    
    this.p[0] = new PVector( 0,  0);
    this.p[1] = new PVector(10, 10);
    
    this.dp[0] = new PVector( 10,  10);
    this.dp[1] = new PVector(-10, -10);
    
    showHandles = true;
  }
  public Bezier(PApplet parent, float x1, float y1, float dx1, float dy1, float x2, float y2, float dx2, float dy2) {
    this.parent = parent;
    
    this.s = new float[2];
    this.o = new float[2];
    
    this.p  = new PVector[2];
    this.dp = new PVector[2];
    
    this.s[0] = 1;
    this.s[1] = 1;
    
    this.o[0] = 0;
    this.o[1] = 0;
    
    this.p[0] = new PVector(x1, y1);
    this.p[1] = new PVector(x2, y2);
    
    this.dp[0] = new PVector(dx1, dy1);
    this.dp[1] = new PVector(dx2, dy2);
    
    showHandles = true;
  }
  
  public void draw() {
    PVector[] calculatedDp = new PVector[2];
    
    calculatedDp[0] = new PVector(dp[0].x, dp[0].y);
    calculatedDp[0].rotate(o[0]);
    calculatedDp[0].mult(s[0]);
    calculatedDp[0].add(p[0]);
    
    calculatedDp[1] = new PVector(dp[1].x, dp[1].y);
    calculatedDp[1].rotate(o[1]);
    calculatedDp[1].mult(s[1]);
    calculatedDp[1].add(p[1]);
    
    parent.noFill();
    
    parent.bezier(
                 p[0].x,            p[0].y, 
      calculatedDp[0].x, calculatedDp[0].y,
      calculatedDp[1].x, calculatedDp[1].y,
                 p[1].x,            p[1].y
    );
    
    if( showHandles ) {
      handle(p[0], calculatedDp[0]);
      handle(p[1], calculatedDp[1]);
    }
  }
  protected void handle(PVector p, PVector dp) {
    parent.ellipseMode(parent.CENTER);
    parent.rectMode(parent.CENTER);
    parent.stroke(0,0,200, 150);
    
    parent.rect(p.x, p.y, 5, 5);
    parent.ellipse(dp.x, dp.y, 5, 5);
    parent.line(p.x, p.y, dp.x, dp.y);
    
    parent.stroke(0);
  }
  public void scaleHandle(double s, int i) {
    this.s[i] = (float) s;
  }
  
  public void rotateHandle(double o, int i) {
    this.o[i] = (float) o;
  }
  
  public void toggleHandles() {
    showHandles = !showHandles;
  }
  
}
