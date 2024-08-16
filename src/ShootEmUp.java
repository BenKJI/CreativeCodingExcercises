import processing.core.PApplet;
import processing.core.PImage;
import processing.core.PVector;

import java.util.ArrayList;
import java.util.Random;

public class ShootEmUp extends PApplet {
    int invaderSize = 7;
    int invaderSizeMultiplier = 5;
    float spawnInterval = 1000;
    float speedBaseline = 0.5f;
    Random rnd = new Random();

    int sizeX = 600;
    int sizeY = 800;
    ArrayList<SpaceInvader> invaders = new ArrayList<>();

    int score = 0;

    public void settings() {
        size(sizeX, sizeY);
        noSmooth();
    }

    public void setup() {
        imageMode(CENTER);
        ellipseMode(CENTER);
        rectMode(CENTER);
        noStroke();
    }

    int lastSpawn = 0;
    public void draw() {
        background(0);
        if(millis() - lastSpawn > spawnInterval){
            invaders.add(new SpaceInvader((int)random(sizeX-invaderSize*invaderSizeMultiplier)+invaderSize*invaderSizeMultiplier/2, rnd.nextFloat(speedBaseline,speedBaseline+1)));
            lastSpawn = millis();
        }

        invaders.forEach(invader -> {
            invader.update();
        });

        rect(pmouseX,pmouseY,1,20);
        rect(pmouseX,pmouseY,20,1);

        textSize(16);
        text("Score: " + score, sizeX -75, sizeY - 25);

        //raise Difficulty
        spawnInterval -= 0.5f;
        if(spawnInterval < 333){
            spawnInterval = 333;
        }
        speedBaseline += 0.001;

    }

    public void mousePressed(){
        ArrayList<SpaceInvader> toRemove = new ArrayList<SpaceInvader>();
        invaders.forEach(spaceInvader -> {
            if(spaceInvader.checkCollission(pmouseX,pmouseY)){
                toRemove.add(spaceInvader);
            }
        });
        toRemove.forEach(invader -> {
            invaders.remove(invader);
            score++;
        });
    }

    class SpaceInvader{
        final PImage image;
        int xPosition;
        float yPosition = 0;
        float fallSpeed;

        public SpaceInvader(int pXPosition, float pFallSpeed){
            xPosition = pXPosition;
            fallSpeed = pFallSpeed;

            image = createImage(invaderSize,invaderSize,ARGB);

            int[] colors = {color(0,0,0,0),color(0,0,0,0),color(0,0,0,0),getRandomColor(),getRandomColor(),getRandomColor()};

            int columns = invaderSize/2;
            if(invaderSize%2 == 1){
                columns ++;
            }

            for(int i = 0; i < columns; i ++){
                for (int j = 0; j < invaderSize; j++){
                    int color = colors[rnd.nextInt(0,6)];
                    image.set(i,j,color);
                    if(columns*2 <= invaderSize || i != columns -1){
                        image.set((invaderSize - i -1), j,color);
                    }
                }
            }
        }

        void update(){
            yPosition += fallSpeed;
            if(yPosition >= sizeY){
                loseGame();
            }
            image(image, xPosition, yPosition,invaderSize*invaderSizeMultiplier,invaderSize*invaderSizeMultiplier);
        }

        boolean checkCollission(int posX, int posY)
        {
            int halfTotalSize = invaderSize * invaderSizeMultiplier / 2;
            if(posX < xPosition + halfTotalSize && posX > xPosition - halfTotalSize && posY < yPosition + halfTotalSize && posY > yPosition - halfTotalSize){
                return true;
            }
            return false;
        }
    }

    void loseGame(){
        noLoop();
    }
    private int getRandomColor(){
        return color(random(255),random(255),random(255));
    }

    public static void main(String[] args){
        PApplet.main("ShootEmUp");
    }
}
