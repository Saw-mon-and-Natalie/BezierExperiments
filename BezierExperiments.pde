Bezier b[][][];

float handleScale = 1;

void setup() {
  size(600,600);
  b = new Bezier[2][7][7];
  
  for(int i = 0; i < 7; i++) {
    for(int j = 0; j < 7; j++) {
      b[0][i][j] = new Bezier(
        this,
        100 * j, 100 * (i + 0), 0,  25,
        100 * j, 100 * (i + 1), 0, -25
      );
      
      b[1][i][j] = new Bezier(
        this,
        100 * (j + 0), 100 * i, 25,  0,
        100 * (j + 1), 100 * i, -25, 0
      );
    }
  }
}

void draw() {
  background(255);
  drawGrid();
  //b.rotateHandle((mouseX * 1.0 / width) * 2 * Math.PI, 0);
  //b.rotateHandle(2 + Math.cos(0.4 * frameCount/Math.PI), 1);
}

void keyPressed() {
  if( key == 'h' || key == 'H' ) {
    for(int i = 0; i < 7; i++) {
      for(int j = 0; j < 7; j++) {
        for(int k = 0; k < 2; k++) {
          b[k][i][j].toggleHandles();
        }
      }
    }
  } else if ( key == 'a' || key == 'A' ) {
    handleScale *= 1.1;
  } else if ( key == 's' || key == 's' ) {
    handleScale /= 1.1;
  }
}

void drawGrid() {
  for(int i = 0; i < 7; i++) {
    for(int j = 0; j < 7; j++) {
      for(int k = 0; k < 2; k++) {
        b[k][i][j].draw();
        
        b[k][i][j].rotateHandle((mouseX * 1.0 / width) * 2 * Math.PI, 0);
        b[k][i][j].rotateHandle((mouseX * 1.0 / width) * 2 * Math.PI, 1);
        
        b[k][i][j].scaleHandle((mouseY * 1.0 / height) * 10 * handleScale, 0);
        b[k][i][j].scaleHandle((mouseY * 1.0 / height) * 10 * handleScale, 1);
      }
      
    }
  }
}
