import java.awt.*;

public class Pix {

    int x, speed=1;
    Boolean collided=false;
    Color color;
    Dimension screenSize;
    Rectangle rect;

    public Pix(int x, Color color, Dimension screenSize){
        this.x=x;
        this.color=color;
        this.screenSize=screenSize;
        rect = new Rectangle(x,0,5,5);
    }

    public void tick(){
        if(rect.y+speed>screenSize.height-rect.height) collided=true;
        update();
    }

    public void update(){
        if(!collided) rect.y+=speed;

    }

    public void render(Graphics g){
        g.setColor(color);
        g.fillRect(rect.x,rect.y,rect.width,rect.height);
    }

    public Rectangle getBounds(){
        return rect;
    }



}
