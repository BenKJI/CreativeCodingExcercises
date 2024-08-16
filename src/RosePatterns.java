import processing.core.PApplet;
import processing.core.PShape;

public class RosePatterns extends PApplet {

    public void settings() {
        size(780,1000);
    }

    public void setup() {
        colorMode(HSB);
        background(0);
        noFill();
        stroke(255);
        strokeWeight(2);
        translate(60,60);
        for(int d = 1; d <= 9; d++){
            pushMatrix();
            for(int n = 1; n<=7;n++){
                shape(getRose((float)n/d));
                translate(110,0);
            }
            popMatrix();
            translate(0,110);
        }


    }

    PShape getRose(float k){
        stroke(random(255),255,255);
        PShape rose = createShape();
        rose.beginShape();
        for(int t = 0; t <= 2880; t++){
            float r = 50 * cos(radians(k*t));
            float x = r * cos(radians(t));
            float y = r * sin(radians(t));
            rose.vertex(x,y);
        }
        rose.endShape(CLOSE);
        return rose;
    }

    public static void main(String[] args){
        PApplet.main("RosePatterns");
    }
}
