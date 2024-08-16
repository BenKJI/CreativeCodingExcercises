import processing.core.PApplet;

public class ScaleRotateQuads extends PApplet {
    @Override
    public void settings() {
        size(800,600);
    }

    @Override
    public void setup() {
        frameRate(8);
        rectMode(CENTER);
    }

    int frameCount = 0;
    @Override
    public void draw() {
        background(255);
        translate(width/2,height/2);
        float factor = 0.95f-(map(1,0,frameCount,0,0.1f));
        System.out.println(factor);

        pushMatrix();
        for (int i = 0; i<frameCount;i++){
            scale(factor);
            rotate(radians(factor*2.5f));
            rect(0,0,width,height);
        }
        popMatrix();

        frameCount++;
        frameCount %= 100;
    }

    public static void main(String[] args) {
        PApplet.main("ScaleRotateQuads");
    }
}
