package net.scriptgate.orbital.tap;

import com.badlogic.gdx.InputProcessor;

import java.util.Optional;

public class OrbInputProcessor implements InputProcessor {
    private final Orbs orbs;
    private final Score score;

    public OrbInputProcessor(Orbs orbs, Score score) {
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
        Optional<Orb> orbClicked = orbs.stream().filter(orb -> orb.contains(screenX, screenY)).findFirst();
        if(orbClicked.isPresent()) {
            orbs.remove(orbClicked.get());
            if(orbs.isSlowed()) {
                score.increase(1);
            } else {
                score.increase(2);
            }
        } else {
            orbs.spawn();
            score.decrease(1);
        }
        return true;
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
        return false;
    }

    @Override
    public boolean scrolled(float amountX, float amountY) {
        return false;
    }
}
