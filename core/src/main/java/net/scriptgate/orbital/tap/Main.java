package net.scriptgate.orbital.tap;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputMultiplexer;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGenerator;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.utils.ScreenUtils;

/**
 * {@link com.badlogic.gdx.ApplicationListener} implementation shared by all platforms.
 */
public class Main extends ApplicationAdapter {
    private HUD hud;
    private ShapeRenderer shapeRenderer;
    private SpriteBatch batch;
    private BitmapFont font;
    private Orbs orbs;
    private Score score;

    @Override
    public void create() {
        orbs = new Orbs();
        score = new Score();

        hud = new HUD(orbs, score);
        shapeRenderer = new ShapeRenderer();
        batch = new SpriteBatch();

        FreeTypeFontGenerator generator = new FreeTypeFontGenerator(Gdx.files.internal("font/VCR_OSD_MONO_1.001.ttf"));
        FreeTypeFontGenerator.FreeTypeFontParameter parameter = new FreeTypeFontGenerator.FreeTypeFontParameter();
        parameter.size = 20;
        parameter.borderWidth = 1;
        parameter.color = Color.WHITE;
        parameter.shadowOffsetX = 1;
        parameter.shadowOffsetY = 1;
        parameter.shadowColor = Color.GRAY;
        font = generator.generateFont(parameter);
        generator.dispose();

        InputMultiplexer multiplexer = new InputMultiplexer();
        multiplexer.addProcessor(new HUDInputProcessor(hud, orbs, score));
        multiplexer.addProcessor(new OrbInputProcessor(orbs, score));
        Gdx.input.setInputProcessor(multiplexer);
    }

    @Override
    public void resize(int width, int height) {

    }

    @Override
    public void render() {
        orbs.update();
        ScreenUtils.clear(0.15f, 0.15f, 0.2f, 1f);
        orbs.render(shapeRenderer, batch, font);
        hud.render(shapeRenderer, batch, font);
    }

    @Override
    public void dispose() {
        shapeRenderer.dispose();
    }
}
