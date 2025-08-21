package net.scriptgate.orbital.tap;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.utils.Align;


public class HUD {

    public Button start = new Button(0.4f, 0.05f, 0.1f, 0.2f);
    public Button slow = new Button(0.4f, 0.05f, 0.1f, 0.2f);
    public Button restart = new Button(0.4f, 0.05f, 0.1f, 0.2f);

    private final GameMode gameMode;
    private final Texture texture;
    private final Orbs orbs;
    private final Score score;

    public HUD(GameMode gameMode, Texture texture, Orbs orbs, Score score) {
        this.gameMode = gameMode;
        this.texture = texture;
        this.orbs = orbs;
        this.score = score;
    }

    public void render(ShapeRenderer renderer, SpriteBatch batch, BitmapFont font) {
        switch (gameMode.state) {
            case START_SCREEN:
                if (start.hover) {
                    renderer.setColor(Color.valueOf("FF6764"));
                } else {
                    renderer.setColor(Color.valueOf("8764FF"));
                }
                renderer.begin(ShapeRenderer.ShapeType.Filled);
                renderer.rect(start.getX(), start.getY(), start.getWidth(), start.getHeight());
                renderer.end();

                batch.setColor(Color.WHITE);
                batch.begin();
                font.getData().setScale(1);
                font.draw(batch, "START",
                    start.getX(),
                    start.getY() + (start.getHeight() / 1.7f),
                    start.getWidth(),
                    Align.center, false);
                batch.end();

                batch.begin();

                batch.draw(texture, (Gdx.graphics.getWidth() - texture.getWidth()) / 2f, 0.9f*Gdx.graphics.getHeight() - texture.getHeight());
                batch.end();

                break;
            case GAME:
                if (slow.hover || orbs.isSlowed()) {
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
                font.draw(batch, "Slow\n-" + score.slowCost(),
                    slow.getX(),
                    slow.getY() + slow.getHeight() / 1.5f,
                    slow.getWidth(),
                    Align.center, false);
                font.getData().setScale(1);
                font.draw(batch, "Orbs remaining: " + orbs.size(), 0, 0.25f * Gdx.graphics.getHeight(), Gdx.graphics.getWidth(), Align.center, false);
                font.getData().setScale(3);
                font.draw(batch, "Score: " + score.value(), 0, 0.95f * Gdx.graphics.getHeight(), Gdx.graphics.getWidth(), Align.center, false);
                batch.end();
                break;
            case CREDITS:
                if (restart.hover) {
                    renderer.setColor(Color.valueOf("FF6764"));
                } else {
                    renderer.setColor(Color.valueOf("8764FF"));
                }
                renderer.begin(ShapeRenderer.ShapeType.Filled);
                renderer.rect(restart.getX(), restart.getY(), restart.getWidth(), restart.getHeight());
                renderer.end();

                batch.setColor(Color.WHITE);
                batch.begin();
                font.getData().setScale(1);
                font.draw(batch, "RESTART",
                    restart.getX(),
                    restart.getY() + restart.getHeight() / 1.7f,
                    restart.getWidth(),
                    Align.center, false);
                font.draw(batch, "WINNER! WINNER! ORBITAL DINNER!", 0, 0.25f * Gdx.graphics.getHeight(), Gdx.graphics.getWidth(), Align.center, false);
                font.getData().setScale(3);
                font.draw(batch, "Score: " + score.value(), 0, 0.95f * Gdx.graphics.getHeight(), Gdx.graphics.getWidth(), Align.center, false);
                batch.end();
                break;
        }


    }
}
