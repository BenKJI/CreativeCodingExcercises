import processing.core.PApplet;

import java.util.Random;

public class QuarterCircles extends PApplet {
    Random rnd = new Random();
    int border = 2;
    int[] colors = new int[3];
    int quarterCircleSize = 20;
    int height = 30;
    int width = 20;
    //int backgroundColor = color(0,0,0);
    //int backgroundColor = color(255,255,255);


    @Override
    public void settings() {
        size((quarterCircleSize+border)*width+border,(quarterCircleSize+border)*height+border);
    }

    @Override
    public void setup() {
        colorMode(HSB,360,100,100);
        noLoop();
    }

    @Override
    public void draw() {
        background(0);
        initColors();
        noStroke();
        for(int i = 0; i<width;i++){
            for(int j = 0; j<height;j++){
                drawArc((quarterCircleSize+border)*i+border,(quarterCircleSize+border)*j+border);
            }
        }
    }

    private void drawArc(int x, int y){
        int orientation = rnd.nextInt(5);
        int shiftedX = x;
        int shiftedY = y;
        float arcStart = 0;

        switch (orientation){
            case 0: return;
            case 1: break;
            case 2: shiftedX+=quarterCircleSize;
                    arcStart += HALF_PI;
                    break;
            case 3: shiftedX+= quarterCircleSize;
                    shiftedY+= quarterCircleSize;
                    arcStart += PI;
                    break;
            case 4: shiftedY+= quarterCircleSize;
                    arcStart += PI + HALF_PI;
                    break;
        }
        fill(colors[rnd.nextInt(3)]);
        arc(shiftedX,shiftedY,quarterCircleSize*2,quarterCircleSize*2,arcStart,arcStart+HALF_PI);
    }
    private void initColors(){
        int hue = rnd.nextInt(360);
        for(int i = 0; i < 3; i++) {
            colors[i] = color(rnd.nextInt(hue - 10, hue + 11), rnd.nextInt(80, 101), rnd.nextInt(60, 101));
        }
    }

    public void mousePressed() {
        redraw();
    }
    public static void main(String[] args) {
        PApplet.main("QuarterCircles");
    }
}
