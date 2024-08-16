import processing.core.PApplet;

public class RecursionRefraction extends PApplet {
    int recursionDepth = 1;

    int size = 800;
    int circleSize = 800;
    int sizeY;

    public void settings() {
        size(size,size);
    }

    public void setup() {
        background(0);
        noStroke();
        ellipseMode(CENTER);
    }

    int rotation = 0;
    public void draw() {
        int mouseDistX = Math.abs(size/2 - mouseX);
        int mouseDistY = Math.abs(size/2 - mouseY);
        recursionDepth = (int)map(mouseDistX,0,size/2,1,7);
        circleSize = size-mouseDistY;
        background(0);
        translate(size/2,size/2);
        circles(recursionDepth);
        rotation++;
    }

    void circles(int depth){
        if(depth == 0){
            circle(0,0,circleSize);
            return;
        }
        rotate(radians(rotation));
        scale(0.44f);

        pushMatrix();
        scale(-1,1);
        translate(size/2,size/2);
        circles(depth-1);
        popMatrix();

        pushMatrix();
        scale(-1,-1);
        translate(size/2,size/2);
        circles(depth-1);
        popMatrix();

        pushMatrix();
        scale(1,-1);
        translate(size/2,size/2);
        circles(depth-1);
        popMatrix();

        pushMatrix();
        scale(1,1);
        translate(size/2,size/2);
        circles(depth-1);
        popMatrix();
    }
public static void main(String[] args){
    PApplet.main("RecursionRefraction");
}
}
