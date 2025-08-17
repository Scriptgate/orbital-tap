package net.scriptgate.orbital.tap;

import com.badlogic.gdx.InputProcessor;

public class HUDInputProcessor implements InputProcessor {
    private final HUD hud;
    private final Orbs orbs;
    private final Score score;

    public HUDInputProcessor(HUD hud, Orbs orbs, Score score) {
        this.hud = hud;
        this.orbs = orbs;
        this.score = score;
    }

    @Override
    public boolean keyDown(int keycode) {
        return false;
    }

    @Override
    public boolean keyUp(int keycode) {
        return false;
    }

    @Override
    public boolean keyTyped(char character) {
        return false;
    }

    @Override
    public boolean touchDown(int screenX, int screenY, int pointer, int button) {
        if(hud.slow.contains(screenX, screenY)) {
            if(score.value() >= score.slowCost()) {
                orbs.slow();
                score.decrease(score.slowCost());
            }
            return true;
        }
        return false;
    }

    @Override
    public boolean touchUp(int screenX, int screenY, int pointer, int button) {
        return false;
    }

    @Override
    public boolean touchCancelled(int screenX, int screenY, int pointer, int button) {
        return false;
    }

    @Override
    public boolean touchDragged(int screenX, int screenY, int pointer) {
        return false;
    }

    @Override
    public boolean mouseMoved(int screenX, int screenY) {
        hud.slow.hover = hud.slow.contains(screenX, screenY);
        return false;
    }

    @Override
    public boolean scrolled(float amountX, float amountY) {
        return false;
    }
}
