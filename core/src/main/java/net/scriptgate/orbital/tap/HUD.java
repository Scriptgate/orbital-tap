package net.scriptgate.orbital.tap;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.utils.Align;


public class HUD {

    public Button slow = new Button(Gdx.graphics.getWidth()/2 - 50,50,50,100);

    private final Orbs orbs;
    private final Score score;

    public HUD(Orbs orbs, Score score) {
        this.orbs = orbs;
        this.score = score;
    }

    public void render(ShapeRenderer renderer, SpriteBatch batch, BitmapFont font) {
        if(slow.hover) {
            renderer.setColor(Color.valueOf("FF6764"));
        } else {
            renderer.setColor(Color.valueOf("8764FF"));
        }
        renderer.begin(ShapeRenderer.ShapeType.Filled);
        renderer.rect(slow.x, slow.y, slow.width, slow.height);

        renderer.end();

        batch.setColor(Color.WHITE);
        batch.begin();
        font.getData().setScale(1);
        font.draw(batch, "Slow\n-"+score.slowCost(), slow.x , slow.y + slow.height - 7, slow.width, Align.center, false);
        batch.end();


        batch.setColor(Color.WHITE);
        batch.begin();
        font.getData().setScale(1);
        font.draw(batch, "Orbs remaining: " + orbs.size(), 0, 285, Gdx.graphics.getWidth(), Align.center, false);
        font.getData().setScale(3);
        font.draw(batch, "Score: " + score.value(), 0, Gdx.graphics.getHeight() - 40, Gdx.graphics.getWidth(), Align.center, false);
        batch.end();
    }
}
