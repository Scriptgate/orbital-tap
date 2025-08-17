package net.scriptgate.orbital.tap;

import com.badlogic.gdx.Gdx;

public class Rectangle {

    public int x;
    public int y;
    public int height;
    public int width;

    public Rectangle(int x, int y, int height, int width) {
        this.x = x;
        this.y = y;
        this.height = height;
        this.width = width;
    }

    public boolean contains(int screenX, int screenY) {
        int renderY = Gdx.graphics.getHeight() - screenY;
        return screenX >= x && screenX <= x + width && renderY >= y && renderY <= y + height;
    }
}
