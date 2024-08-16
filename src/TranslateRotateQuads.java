import processing.core.PApplet;
import processing.core.PVector;

import java.util.Random;

public class TranslateRotateQuads extends PApplet {
    int height = 24;
    int width = 12;
    int rectSize = 30;
    int border = 10;
    float progress = 0;
    int duration = 30;
    float movementSpeed = 1f;
    long seed;
    Random rnd = new Random();
    @Override
    public void settings() {
        size(width*rectSize+border*2,height*rectSize+border*2);
        seed = rnd.nextLong();
        randomSeed(seed);
    }

    @Override
    public void setup() {
        frameRate(60);
        rectMode(CENTER);
        noFill();
    }

    @Override
    public void draw() {
        background(255);
        translate(border+rectSize/2,border+rectSize/2); //Account for RectangleMode and Border
        for(int i = 0; i<height;i++){
            pushMatrix();

            translate(0,i*rectSize);
            for (int j = 0; j<width;j++){
                pushMatrix();

                PVector shift = new PVector(random(-1,1),random(-1,1));
                shift.normalize();
                float angleDir = flipCoin();

                float multiplier = progress*i/height * movementSpeed;
                translate(j*rectSize + shift.x*multiplier,shift.y*multiplier);
                rotate(radians(angleDir*multiplier));
                rect(0,0,rectSize,rectSize);

                popMatrix();
            }

            popMatrix();
        }

        countFrame();
        randomSeed(seed);
    }

    private int flipCoin(){
        int result = (int) random(0,2);
        if(result == 1){
            result++;
        }
        result--;
        return result;
    }

    int countDir = 1;
    private void countFrame(){
        progress += countDir;
        if(progress <= -duration || progress >= duration){
            countDir *= -1;
        }
        System.out.println(progress);
    }
    public static void main(String[] args) {
        PApplet.main("TranslateRotateQuads");
    }
}
