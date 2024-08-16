import processing.core.PApplet;

import java.util.Random;

public class MovingCircles extends PApplet {
    int innerCircleSize = 10;
    int outerCircleSize = 20;
    int border = 2;
    int height = 20;
    int width = 30;
    float movementMultiplicator = 0.01f;
    long seed;
    int startMouseX = mouseX;
    int startMouseY = mouseY;
    Random rnd = new Random();
    @Override
    public void settings() {
        seed = rnd.nextLong();
        randomSeed(seed);
        size((outerCircleSize+border)*width+border,(outerCircleSize+border)*height+border);
    }

    @Override
    public void setup() {
        noStroke();
    }

    @Override
    public void draw() {
        background(255);
        for(int i = 0; i<width;i++){
            for(int j = 0; j<height;j++){
                float xDir =random(-1,1);
                float yDir =random(-1,1);

                float mouseTravel = (startMouseX-mouseX)+(startMouseY-mouseY);

                int xShift = (int)  (mouseTravel* xDir * movementMultiplicator);
                int yShift = (int)  (mouseTravel* yDir * movementMultiplicator);

                int x = (outerCircleSize+border)*i+border+outerCircleSize/2;
                int y = (outerCircleSize+border)*j+border+outerCircleSize/2;
                fill(color(0));
                circle(x+xShift,y+yShift,outerCircleSize);
                fill(color(255));
                circle(x,y,innerCircleSize);
            }
        }
        randomSeed(seed);
    }

    public void mousePressed() {
        seed = rnd.nextLong();
        randomSeed(seed);
    }
    public static void main(String[] args) {
        PApplet.main("MovingCircles");
    }
}
