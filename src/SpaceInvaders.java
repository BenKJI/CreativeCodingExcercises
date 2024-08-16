import processing.core.PApplet;
import processing.core.PImage;

import java.util.Random;

public class SpaceInvaders extends PApplet {

    Random rnd = new Random();
    int border = 5;
    int invaderSize = 7;
    int sizeMultiplier = 4;
    int height = 20;
    int width = 25;
    int backgroundColor = color(0,0,0);
    //int backgroundColor = color(255,255,255);

    @Override
    public void settings() {
        size((invaderSize*sizeMultiplier+border)*width + border, (invaderSize*sizeMultiplier+border)* height + border);
        noSmooth();
        noLoop();
    }

    @Override
    public void setup() {

    }

    @Override
    public void draw() {
        background(backgroundColor);

        for(int i = 0; i<width;i++){
            for(int j = 0; j<height;j++){
                image(getSpaceInvader(),(invaderSize*sizeMultiplier+border)*i + border,(invaderSize*sizeMultiplier+border)* j + border,invaderSize*sizeMultiplier,invaderSize*sizeMultiplier);
            }
        }
    }

    private PImage getSpaceInvader(){
        PImage invader = createImage(invaderSize,invaderSize,ARGB);

        int[] colors = {color(0,0,0,0),color(0,0,0,0),color(0,0,0,0),getRandomColor(),getRandomColor(),getRandomColor()};

        int columns = invaderSize/2;
        if(invaderSize%2 == 1){
            columns ++;
        }

        for(int i = 0; i < columns; i ++){
            for (int j = 0; j < invaderSize; j++){
                int color = colors[rnd.nextInt(0,6)];
                invader.set(i,j,color);
                if(columns*2 <= invaderSize || i != columns -1){
                    invader.set((invaderSize - i -1), j,color);
                }
            }
        }

        return invader;
    }

    private int getRandomColor(){
        return color(random(255),random(255),random(255));
    }

    @Override
    public void mousePressed() {
        redraw();
    }

    public static void main(String[] args) {
        PApplet.main("SpaceInvaders");
    }
}
