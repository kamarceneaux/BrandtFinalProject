package finalproject.utils.sprites;

import basicgraphics.Sprite;
import basicgraphics.SpriteCollisionEvent;
import basicgraphics.SpriteComponent;
import basicgraphics.images.Picture;

public class AngryCustomer extends Sprite {

    private final int originalStart;
    private Picture pic;

    public AngryCustomer(SpriteComponent sc) {
        super(sc);
        pic = new Picture("AngryPerson.png");
        setPicture(pic);
        originalStart = (sc.getWidth() / 4) + 75;
        setX(originalStart);
        setY(sc.getY() + 200);
        setVel(1.5, 0);
    }

    @Override
    public void processEvent(SpriteCollisionEvent ev) {
       if(ev.xhi || ev.xlo){
           setActive(false);
       }
    }
}
