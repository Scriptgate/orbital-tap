package net.scriptgate.orbital.tap;

import com.badlogic.gdx.Gdx;

public class Rectangle {

    public float x;
    public float y;
    public float height;
    public float width;

    public Rectangle(float x, float y, float height, float width) {
        this.x = x;
        this.y = y;
        this.height = height;
        this.width = width;
    }

    public boolean contains(float screenX, float screenY) {
        float renderY = Gdx.graphics.getHeight() - screenY;
        return screenX >= getX() && screenX <= getX() + getWidth() && renderY >= getY() && renderY <= getY() + getHeight();
    }

    public float getX() {
        return x * Gdx.graphics.getWidth();
    }

    public float getY() {
        return y * Gdx.graphics.getHeight();
    }

    public float getWidth() {
        return width * Gdx.graphics.getWidth();
    }

    public float getHeight() {
        return height * Gdx.graphics.getHeight();
    }
}
