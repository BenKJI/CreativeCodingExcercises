package Flocking;

import processing.core.PApplet;
import processing.core.PVector;

import java.util.ArrayList;
import java.util.Random;
import java.util.random.RandomGenerator;

public class FlockingMain extends PApplet {
    FlowField flowField;
    Agent agent;
    ArrayList<Agent> agents = new ArrayList<Agent>();
    @Override
    public void settings() {
        size(800,800);
    }

    @Override
    public void setup() {
        background(0);
        flowField = new FlowField(width,20);
        flowField.drawField();
    }

    @Override
    public void draw() {
        background(0);
        flowField.drawField();
        agents.forEach(a->{
            a.update();
            a.draw();
        });
    }

    @Override
    public void mousePressed() {
        agents.add(new Agent(new PVector(mouseX,mouseY)));
    }

    public static void main(String[] args){
        PApplet.main("Flocking.FlockingMain");
    }

    class FlowField{
        int columns;
        int rows;

        int resolution;

        PVector[][] field;
        public FlowField(int size,int pResolution){
            resolution = pResolution;
            columns = size/resolution;
            rows = size/resolution;
            field = new PVector[columns][rows];

            initField();
        }

        void initField(){
            Random rnd = new Random();
            float x = rnd.nextFloat(20)+20;
            float y = rnd.nextFloat(20);
            float inc = 0.15f;
            for (int i = 0; i < columns; i++){
                for (int j = 0; j < rows; j++){
                    float angle = noise(x+inc*i,y+inc*j)*TWO_PI;

                    //Walls
                    if(i == 0){
                        angle = 0;
                    }
                    if(i == columns-1){
                        angle = PI;
                    }
                    if(j == 0){
                        angle = HALF_PI;
                    }
                    if(j == rows-1){
                        angle = PI+HALF_PI;
                    }

                    field[i][j] = new PVector();
                    field[i][j].set(cos(angle),sin(angle));
                    System.out.println(x + " | " + y);
                }
            }
            System.out.println(field.toString());
        }
        void drawField(){
            for (int i = 0; i < columns; i++){
                for (int j = 0; j < rows; j++){
                    drawVector(field[i][j], i * resolution, j * resolution);
                }
            }
        }

        void drawVector(PVector vector, int x, int y){
            //System.out.println("Drawing at: " + x + " | " + y );
            //circle(x+resolution/2,y+resolution/2,5);
            fill(255);
            stroke(255);
            PVector tip = new PVector(x+resolution/2,y+resolution/2);
            PVector a = PVector.mult(vector, 0.5f * resolution/2f);
            PVector b = vector.copy().rotate(HALF_PI).mult(0.25f * resolution/2f);
            PVector c = PVector.mult(b, -1);

            PVector start = PVector.add(tip,PVector.mult(vector, -0.5f * resolution));
            line(tip.x, tip.y, start.x, start.y);
            triangle(PVector.add(tip,a).x, PVector.add(tip,a).y, PVector.add(tip,b).x, PVector.add(tip,b).y, PVector.add(tip,c).x, PVector.add(tip,c).y);
        }

        PVector getDirection(PVector pos){

            int col = (int) pos.x/resolution;
            int row = (int) pos.y/resolution;
            col = constrain(col,0,columns-1);
            row = constrain(row,0,rows-1);
            return field[col][row];
        }

    }

    class Agent{
        PVector position;
        PVector velocity = new PVector();
        PVector acceleration = new PVector();
        public Agent(PVector pPosition){
            position = pPosition;
        }
        void update(){
        acceleration.add(flowField.getDirection(position).normalize());
        acceleration.limit(8);
        velocity.add(acceleration);
        velocity.limit(8);
        position.add(velocity);
        }
        void draw(){
            pushMatrix();
            translate(position.x, position.y);
            float angle = atan2(velocity.y, velocity.x);
            rotate(angle+PI);
            fill(255,0,0);
            triangle(0,0,25,8,25,-8);
            popMatrix();
        }
    }
}
