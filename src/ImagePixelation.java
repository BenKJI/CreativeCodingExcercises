import processing.core.*;

import java.util.Random;

public class ImagePixelation extends PApplet{
    PImage image;
    @Override
    public void settings() {
        image = loadImage("../img/ImagePixelationSource.jpg");
        size(image.width,image.height);
    }
    Random rnd = new Random();
    @Override
    public void setup() {
        //image(image,0,0);
        background(0,0,0);
        noStroke();
        int circleSize = 50;
        int lastJShift = -1;
        for(int i = 0; i < this.width;i=i){
            //Shift Columns individually and vertically
            int jShift = rnd.nextInt(circleSize);
            while (jShift == lastJShift){
                jShift = rnd.nextInt(circleSize);
            }
            lastJShift = jShift;

            for(int j = 0-circleSize; j < this.height; j=j){

                //Get Image Color (At y=1 if real y is negative)
                int colorPickHeight = Math.max(j+(circleSize/2), 1);
                fill(image.get(i+(circleSize/2),colorPickHeight));

                //Draw circle
                circle(i+(circleSize/2), j+(circleSize/2)+jShift,circleSize);
                j += circleSize;

            }

            i+= circleSize;
            circleSize = getNextCircleSize(i);
        }
    }

    private int getNextCircleSize(int widthProgr){
        int newCircleSize;
        newCircleSize = 30 * (this.width - widthProgr)/this.width;
        if(newCircleSize < 1){
            newCircleSize = 1;
        }
        newCircleSize += 3;
        return newCircleSize;
    }
    public static void main(String[] args) {
        PApplet.main("ImagePixelation");
    }
}
