package finalproject.utils.sprites;

import basicgraphics.Sprite;
import basicgraphics.SpriteComponent;
import basicgraphics.images.Picture;

public class Building extends Sprite {
    public Picture pic;

    public Building(SpriteComponent sc) {
        super(sc);
        pic = new Picture("SmoothiePlace.png");
        pic.shrinkToMinimum();
        setPicture(pic);
        setX(sc.getWidth() / 4);
        setY(sc.getY() + 150);
    }
}
