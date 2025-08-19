package net.scriptgate.orbital.tap;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;

public class Orbs {

    private static final float SLOW_TIME = 7;
    private static final float SPEED_DEFAULT = 1;
    private static final float SPEED_SLOW = 0.1f;

    private final GameMode gameMode;

    private final List<Orb> orbs;
    private float speedModifier = SPEED_DEFAULT;
    private float slowTimeRemaining = 0;

    public Orbs(GameMode gameMode) {
        this.gameMode = gameMode;
        orbs = new ArrayList<>();
        spawnAll();
    }

    public void spawnAll() {
        for (int i = 0; i < 100; i++) {
            spawn();
        }
    }

    public void spawn() {
        orbs.add(new Orb());
    }

    public void update() {
        if(slowTimeRemaining > 0) {
            slowTimeRemaining -= Gdx.graphics.getDeltaTime();
        }
        if(slowTimeRemaining < 0) {
            slowTimeRemaining = 0;
        }
        if(slowTimeRemaining == 0) {
            if(speedModifier < SPEED_DEFAULT) {
                speedModifier += SPEED_SLOW / 10;
            }
        }
        for (Orb orb : orbs) {
            orb.update(speedModifier);
        }
    }

    public void render(ShapeRenderer shapeRenderer) {
        if(gameMode.state != GameMode.State.GAME) {
            return;
        }
        for (Orb orb : orbs) {
            shapeRenderer.setColor(orb.color);
            shapeRenderer.begin(ShapeRenderer.ShapeType.Filled);
            shapeRenderer.circle(
                Gdx.graphics.getWidth() / 2f + orb.x,
                Gdx.graphics.getHeight() / 2f + orb.y,
                orb.radius * Gdx.graphics.getWidth());
            shapeRenderer.end();
            shapeRenderer.setColor(Color.WHITE);
            shapeRenderer.begin(ShapeRenderer.ShapeType.Line);
            shapeRenderer.circle(
                Gdx.graphics.getWidth() / 2f + orb.x,
                Gdx.graphics.getHeight() / 2f + orb.y,
                orb.radius * Gdx.graphics.getWidth());
            shapeRenderer.end();
        }
    }

    public Stream<Orb> stream() {
        return orbs.stream();
    }

    public void remove(Orb orb) {
        orbs.remove(orb);
    }

    public int size() {
        return orbs.size();
    }

    public void slow() {
        slowTimeRemaining = SLOW_TIME;
        speedModifier = SPEED_SLOW;
    }

    public boolean isSlowed() {
        return slowTimeRemaining > 0;
    }

    public boolean isEmpty() {
        return orbs.isEmpty();
    }
}
