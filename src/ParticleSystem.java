import processing.core.PApplet;
import processing.core.PImage;
import processing.core.PVector;

import java.util.ArrayList;

public class ParticleSystem extends PApplet {
    PImage img;
    ArrayList<Particle> particles = new ArrayList<Particle>();

    PVector wind;
    public void settings() {
        size(600,800);
        img = loadImage("img/blackSmoke24.png");
    }

    public void setup() {
        frameRate(30);
        blendMode(ADD);
        wind = new PVector(random(-1,1),random(-1,-1)).normalize().mult(random(2));

    }

    public void draw() {
        if(frameCount % 180 == 0) {
            wind = new PVector(random(-1,1),random(-1,-1)).normalize().mult(random(2));
        }
        if(particles.size() < 128){
            for (int i = 0; i<1;i++) {
                particles.add(new Particle());
            }
        }
        background(0);
        particles.forEach(p -> {
            p.update();
            p.draw();
            p.addForce(wind.copy().mult(0.1f));
            if(p.isDead()){
                p.init();
            }
        });
        System.out.println(particles.size());
    }

    class Particle{
        PVector position;
        PVector acceleration;
        PVector velocity;
        int lifespan = 255;


        public Particle(){
            init();
        }
        void init(){
            position = new PVector(width/2,height*0.9f);
            acceleration = new PVector(random(-5, 5), random(-4, -15)).normalize().mult(random(2));
            velocity = new PVector(0,0);
            lifespan = 255;
        }
        void addForce(PVector force){
            acceleration.add(force);
            acceleration.limit(10);
        }
        void update(){
            velocity.add(acceleration);
            velocity.limit(4);
            position.add(velocity);
            lifespan -=2;
        }
        void draw(){
            tint(16, 255, 16, lifespan);
            image(img, position.x, position.y,128,128);
        }

        public boolean isDead(){
            return (lifespan <= 0);
        }
    }

    public static void main(String[] args){
    PApplet.main("ParticleSystem");
}
}
