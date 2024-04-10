package finalproject.utils.sprites;

import basicgraphics.Sprite;
import basicgraphics.SpriteCollisionEvent;
import basicgraphics.SpriteComponent;
import basicgraphics.images.Picture;

public class HappyCustomer extends Sprite {
    private final int originalStart;
    private Picture pic;

    public HappyCustomer(SpriteComponent sc) {
        super(sc);
        pic = new Picture("HappyPerson-7.png");
        setPicture(pic);
        originalStart = (sc.getWidth() / 4) + 75;
        setX(originalStart);
        setY(sc.getY() + 145);
        setVel(1.5, 0);
    }

    @Override
    public void processEvent(SpriteCollisionEvent ev) {
        if(ev.xhi || ev.xlo){
            setX(originalStart);
        }
    }
}
