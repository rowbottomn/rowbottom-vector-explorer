class Axis {

  int xScl;
  int yScl;
  int xLines;
  int yLines;

  Axis(int x, int y) {
    setAxis(x, y);
  } 

  void setAxis(int x, int y) {
    xScl = x;
    yScl = y;
    xLines = width/xScl;
    yLines = height/yScl;
  }  

  void drawAxis() {
    //draw coord axis
    strokeWeight(1);
    stroke(150, 70);
    line(0, height/2, width, height/2);
    line(width/2, 0, width/2, height);
    strokeWeight(2);
    for (int i = 0; i <= width; i += xScl) {
      line (i, height/2 - 5, i, height/2 + 5);
    }
    for (int j = 0; j <= height; j += yScl) {
      line (width/2 - 5, j, width/2 + 5, j);
    }
  }

  void drawGrid() {
    strokeWeight(0.5);
    stroke(150, 70);    
    for (int i = 0; i <= width; i += xScl) {
      line (i, 0, i, height);
    }
    for (int j = 0; j <= height; j += yScl) {
      line (0, j, width, j);
    }
  }
}