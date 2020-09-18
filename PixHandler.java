import java.awt.*;
import java.lang.reflect.Array;
import java.util.ArrayList;

public class PixHandler {
     ArrayList<Pix> object = new ArrayList<>();
     ArrayList<Pix> falling = new ArrayList<>();

    //Runs the tick method for all objects in the LinkedList
    public  void tick(){
        for(Pix pix : object){
            collision(pix);
            if(pix.collided) {
                falling.remove(pix);
                continue;
            }
                falling.add(pix);
                for(Pix fall : falling){
                    fall.tick();
            }
        }
    }

    //Runs the render method for all objects in the LinkedList
    public  void render(Graphics g){
        for(Pix pix : object){
                pix.render(g);
        }
    }

    public void collision(Pix pix){
            for(Pix pix2 : falling){
                if(pix2==pix) continue;
                if(pix.getBounds().intersects(pix2.getBounds())){
                    pix.collided=true;
                    pix2.collided=true;
                }
            }
            if(pix.rect.y+pix.speed>pix.screenSize.height-pix.rect.height) pix.collided=true;
    }

    //adding and removing objects from the ArrayList
    public void addObject(Pix object){
        this.object.add(object);
    }

    public void removeObject(Pix object){
        this.object.remove(object);
    }

    public void addFalling(Pix falling){
        this.falling.add(falling);
    }

    public void removeFalling(Pix falling){
        this.falling.remove(falling);
    }


}
