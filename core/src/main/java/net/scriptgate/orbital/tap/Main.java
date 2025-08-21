package net.scriptgate.orbital.tap;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputMultiplexer;
import com.badlogic.gdx.assets.loaders.resolvers.InternalFileHandleResolver;
import com.badlogic.gdx.assets.loaders.resolvers.ResolutionFileResolver;
import com.badlogic.gdx.assets.loaders.resolvers.ResolutionFileResolver.Resolution;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGenerator;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.utils.ScreenUtils;

/**
 * {@link com.badlogic.gdx.ApplicationListener} implementation shared by all platforms.
 */
public class Main extends ApplicationAdapter {

    private ResolutionFileResolver fileResolver;
    private OrthographicCamera camera;
    private GameMode gameMode;
    private HUD hud;
    private ShapeRenderer shapeRenderer;
    private SpriteBatch batch;
    private BitmapFont font;
    private Texture texture;
    private Orbs orbs;
    private Score score;

    @Override
    public void create() {
        fileResolver = new ResolutionFileResolver(new InternalFileHandleResolver(),
            new Resolution(800, 480, "480"),
            new Resolution(1280, 720, "720"),
            new Resolution(1920, 1080, "1080"));
        texture = new Texture(fileResolver.resolve("data/orbital-tap.png"));
        camera = new OrthographicCamera();
        this.gameMode = new GameMode();
        orbs = new Orbs(gameMode);
        score = new Score();

        hud = new HUD(gameMode, texture, orbs, score);
        shapeRenderer = new ShapeRenderer();
        batch = new SpriteBatch();

        FreeTypeFontGenerator generator = new FreeTypeFontGenerator(Gdx.files.internal("font/VCR_OSD_MONO_1.001.ttf"));
        FreeTypeFontGenerator.FreeTypeFontParameter parameter = new FreeTypeFontGenerator.FreeTypeFontParameter();
        parameter.size = (int) (Gdx.graphics.getHeight() * 0.02f);
        parameter.borderWidth = 1;
        parameter.color = Color.WHITE;
        parameter.shadowOffsetX = 1;
        parameter.shadowOffsetY = 1;
        parameter.shadowColor = Color.GRAY;
        font = generator.generateFont(parameter);
        generator.dispose();

        InputMultiplexer multiplexer = new InputMultiplexer();
        multiplexer.addProcessor(new HUDInputProcessor(gameMode, hud, orbs, score));
        multiplexer.addProcessor(new OrbInputProcessor(gameMode, orbs, score));
        Gdx.input.setInputProcessor(multiplexer);
    }

    @Override
    public void resize(int width, int height) {
        camera.setToOrtho(false, width, height);
        batch.setProjectionMatrix(camera.combined);
        shapeRenderer.setProjectionMatrix(camera.combined);
    }

    @Override
    public void render() {
        orbs.update();
        ScreenUtils.clear(0f, 0f, 0f, 1f);
        orbs.render(shapeRenderer);
        hud.render(shapeRenderer, batch, font);
    }

    @Override
    public void dispose() {
        shapeRenderer.dispose();
        batch.dispose();
    }
}
