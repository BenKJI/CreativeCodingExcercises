import processing.core.*;

import java.util.Random;

public class HelloWorld extends PApplet{
    Random rnd = new Random();
    int sizeX = 800;
    int sizeY = 600;
    boolean start = false; //For recording
    public void settings() {
        size(sizeX, sizeY);
    }

    public void setup() {
        background(38, 1, 59);
        frameRate(10);
        noStroke();
        colorMode(HSB,360,100,100);
    }


    public void draw() {
        if(start) {
            fill(getNextColor());
            circle(rnd.nextInt(sizeX), rnd.nextInt(sizeY), rnd.nextInt(10, 60));
        }
    }

    private int getNextColor(){
        return color(278,rnd.nextInt(50,100),rnd.nextInt(10,100),128);
    }
    //public void mousePressed() { saveFrame("output-####.png"); }

    @Override
    public void keyPressed() {
        if(key == ' '){
            start = true;
        }
    }

    public static void main(String[] args) {
        PApplet.main("HelloWorld");
    }
}
