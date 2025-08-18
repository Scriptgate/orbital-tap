package net.scriptgate.orbital.tap;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;

public class Orb {

    double speed = Math.random() * 3 + 3;
    double angle = 270;
    float radius = 0.03f;
    float x = 0;
    float y = 0;

    double orbitWidth = 0.2f + (Math.random() * 0.1f);
    double orbitHeight = 0.08f + (Math.random() * 0.1f);

    float red = (float) Math.min(Math.floor(this.speed*45),255);
    float green = (float) Math.min(Math.floor(this.orbitWidth),255);
    float blue = (float) Math.min(Math.floor(this.speed*85),255);;

    Color color = new Color(red/255f, green/255f, blue/255f, 1f);

    public void update(float speedModifier) {
        this.angle += this.speed * speedModifier;
        if(this.angle > 360) {
            this.angle -= 360;
        }

        this.x = (float) Math.floor(this.orbitWidth * Gdx.graphics.getWidth() * Math.cos(this.angle * Math.PI/180));
        this.y = (float) Math.floor(this.orbitHeight * Gdx.graphics.getHeight() * Math.sin(this.angle * Math.PI/180));
    }

    public boolean contains(int screenX, int screenY) {
        return Math.pow(screenX - x - Gdx.graphics.getWidth() / 2f, 2) + Math.pow(screenY + y - Gdx.graphics.getHeight() / 2f, 2) < Math.pow(radius * Gdx.graphics.getWidth(), 2);
    }
}
