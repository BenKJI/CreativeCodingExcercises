import processing.core.*;

import java.util.Random;

public class SpaceInvader extends PApplet {
    Random rnd = new Random();
    int border = 10;
    int invaderSize = 8;
    int pixelSize = 30;
    int backgroundColor = color(0,0,0);
    int[] colors = {backgroundColor,backgroundColor,backgroundColor,getRandomColor(),getRandomColor(),getRandomColor()};
    @Override
    public void settings() {
        size(invaderSize*pixelSize + border*2, invaderSize*pixelSize + border*2);
    }

    @Override
    public void setup() {
        background(0,0,0);
        int columns;
        columns = invaderSize/2;
        if(invaderSize%2 == 1){
            columns ++;
        }
        System.out.println(columns);
        for(int i = 0; i < columns; i ++){
            for (int j = 0; j < invaderSize; j++){
                fill(colors[rnd.nextInt(0,6)]);
                rect(i*pixelSize +border,j*pixelSize +border,pixelSize,pixelSize);

                if(columns*2 <= invaderSize || i != columns -1){
                    rect((invaderSize - i -1) * pixelSize + border, j * pixelSize + border, pixelSize, pixelSize);
                }
            }
        }
    }

    private int getRandomColor(){
        return color(random(255),random(255),random(255));
    }
    public static void main(String[] args) {
        PApplet.main("SpaceInvader");
    }
}
