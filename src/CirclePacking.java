import processing.core.PApplet;
import processing.core.PImage;
import processing.core.PVector;

import java.awt.*;
import java.util.ArrayList;
import java.util.Random;
import java.util.concurrent.atomic.AtomicBoolean;

public class CirclePacking extends PApplet {
    PImage image;
    int spawnRate = 16;
    int minimumDistance = 0;
    float sizeIncrease = 0.5f;
    int maxSize = 18;

    ArrayList<Circle> circles = new ArrayList<Circle>();
    Random rnd = new Random();

    int sizeX;
    int sizeY;
    public void settings() {
        image = loadImage("../img/ImagePixelationSource.jpg");
        size(image.width,image.height);
    }

    public void setup() {
        background(38, 1, 59);
        frameRate(10);
        noStroke();
        colorMode(HSB,360,100,100);
        frameRate(360);

        sizeX = image.width;
        sizeY = image.height;

        image(image,0,0);
        filter(GRAY);
        filter(BLUR,5);
    }

    boolean start = false;

    public void draw() {
        if(!start){
            return;
        }
        for(int i = 0; i <= spawnRate; i++){
            Circle newCircle = createNewCircle();
            circles.add(newCircle);
        }


        circles.forEach(circle -> {
            circle.draw();
            circles.forEach(other -> {
                if(circle != other) {
                    if (circle.checkCollision(other)) {
                        circle.stopGrowing();
                    }
                }
            });
            circle.grow();
        });
    }

    //Redundancy for new Circles not to Overlap => SpawnRate won't slow down
    Circle createNewCircle(){
        Circle newCircle = new Circle(new PVector(rnd.nextInt(sizeX),rnd.nextInt(sizeY)),0);
        AtomicBoolean collision = new AtomicBoolean(false);
        circles.forEach(other ->{
            if (newCircle.checkCollision(other)) {
                collision.set(true);
            }
        });
        if(collision.get()){
            return createNewCircle();
        }
        return newCircle;
    }

    @Override
    public void keyPressed() {
        if(key == ' '){
            start = true;
        }
    }

    public static void main(String[] args) {
        PApplet.main("CirclePacking");
    }

    class Circle {
        PVector center;
        float size;
        int color;

        boolean growing = true;
        public Circle(PVector pCenter, int pColor){
            center = pCenter;
            size = 0;
            color = pColor;
        }

        void grow() {
            if(growing && size < maxSize) {
                size += sizeIncrease;
            }
        }

        boolean checkCollision(Circle other) {
            boolean collision = true;
            float distance = center.dist(other.center);
            if (distance > (size+sizeIncrease)/2 + (other.size)/2 + minimumDistance) {
                collision = false;
            }
            return collision;
        }

        void stopGrowing() {
            growing = false;
        }

        void draw() {
            int imgColor = image.get((int)center.x,(int)center.y);
            fill(imgColor);
            circle(center.x,center.y, size);
        }
    }
}
