import processing.core.PApplet;
import processing.core.PVector;

import java.util.ArrayList;

public class AttractedTriangles extends PApplet {

    ArrayList<Triangle> balls = new ArrayList<Triangle>();
    int ballCount = 20;
    int ballSize = 10;
    public void settings() {
        size(600,600);
    }

    public void setup() {
        for (int i = 0; i < ballCount; i++){
            balls.add(new Triangle(new PVector((int)random(width),(int)random(height))));
        }
    }
    PVector mousePos = new PVector(mouseX,mouseY);

    public void draw() {
        background(255);

        mousePos = new PVector(mouseX,mouseY);

        balls.forEach(ball -> {
            ball.update();
            ball.draw();
        });

        if(mousePressed){
            fill(255,0,0);
        }else {
            fill(0,255,0);
        }
        circle(mouseX,mouseY,10);
    }

    class Triangle{
        PVector position;
        PVector velocity = new PVector(0,0);

        public Triangle(PVector pos){
            position = pos;
        }
        void update(){
            PVector pathToMouse = PVector.sub(mousePos,position);
            float mouseDist = pathToMouse.mag();
            if(mousePressed){
                pathToMouse.mult(-1);
            }

            velocity.add(pathToMouse.normalize().mult(0.8f));

            if (position.x > width || position.x < 0){
                velocity.x = velocity.x * -1;
            }
            if (position.y > width || position.y < 0){
                velocity.y = velocity.y * -1;
            }

            velocity.limit(13);

            position = PVector.add(velocity,position);
        }
        void draw(){
            stroke(0);
            fill(255);
            pushMatrix();
            translate(position.x, position.y);
            float angle = atan2(velocity.y, velocity.x);
            rotate(angle);
            triangle(10,0,0, -3,0, 3);
            popMatrix();
        }
    }
    public static void main(String[] args){
        PApplet.main("AttractedTriangles");
    }
}
