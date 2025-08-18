package net.scriptgate.orbital.tap;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.utils.Align;


public class HUD {

    public Button slow = new Button(0.4f,0.05f,0.1f,0.2f);

    private final Orbs orbs;
    private final Score score;

    public HUD(Orbs orbs, Score score) {
        this.orbs = orbs;
        this.score = score;
    }

    public void render(ShapeRenderer renderer, SpriteBatch batch, BitmapFont font) {
        if(slow.hover || orbs.isSlowed()) {
            renderer.setColor(Color.valueOf("FF6764"));
        } else {
            renderer.setColor(Color.valueOf("8764FF"));
        }
        renderer.begin(ShapeRenderer.ShapeType.Filled);
        renderer.rect(slow.getX(), slow.getY(), slow.getWidth(), slow.getHeight());

        renderer.end();

        batch.setColor(Color.WHITE);
        batch.begin();
        font.getData().setScale(1);
        font.draw(batch, "Slow\n-"+score.slowCost(),
            slow.getX(),
            slow.getY() + slow.getHeight() / 1.5f,
            slow.getWidth(),
            Align.center, false);
        batch.end();


        batch.setColor(Color.WHITE);
        batch.begin();
        font.getData().setScale(1);
        font.draw(batch, "Orbs remaining: " + orbs.size(), 0, 0.25f * Gdx.graphics.getHeight(), Gdx.graphics.getWidth(), Align.center, false);
        font.getData().setScale(3);
        font.draw(batch, "Score: " + score.value(), 0, 0.95f * Gdx.graphics.getHeight(), Gdx.graphics.getWidth(), Align.center, false);
        batch.end();
    }
}
