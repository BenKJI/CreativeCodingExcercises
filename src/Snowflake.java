import processing.core.PApplet;

public class Snowflake extends PApplet {
    int mirrorings = 6;

    @Override
    public void settings() {
        size(600,800);
    }

    @Override
    public void setup() {
        strokeWeight(width/100);
        colorMode(HSB,360,100,100);
        background(0);
    }

    int hue = 0;
    @Override
    public void draw() {
        stroke(color(hue,100,100)); // set the stroke color to black
        hue%=360;

        translate(width/2,height/2); //centerMode

        if(mousePressed){


            float mirrorStep = 360 / (mirrorings);

            for(int i = 0; i <= mirrorings; i++){

                line(pmouseX-width/2, pmouseY-height/2, mouseX-width/2, mouseY-height/2); // draw a line from the previous mouse position to the current mouse position
                pushMatrix();
                scale(1,-1);
                line(pmouseX-width/2, pmouseY-height/2, mouseX-width/2, mouseY-height/2);
                popMatrix();

                rotate(radians(mirrorStep));
            }

            hue++;
        }
    }

    @Override
    public void keyPressed() {
        background(0);
    }

    public static void main(String[] args) {
        PApplet.main("Snowflake");
    }
}
