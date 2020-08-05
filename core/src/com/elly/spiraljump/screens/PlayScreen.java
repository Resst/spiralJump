package com.elly.spiraljump.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.physics.box2d.Box2DDebugRenderer;
import com.badlogic.gdx.physics.box2d.World;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.elly.spiraljump.GameClass;
import com.elly.spiraljump.gameplay.Levels.Level;
import com.elly.spiraljump.tools.Constants;

public class PlayScreen implements Screen {

    private GameClass game;
    private Level level;
    private Box2DDebugRenderer renderer;    //TODO delete this

    private FitViewport viewport;
    private OrthographicCamera camera;

    public PlayScreen(GameClass game){
        this.game = game;
        level = new Level(this);

        renderer = new Box2DDebugRenderer();

        camera = new OrthographicCamera();
        viewport = new FitViewport(Constants.WIDTH_IN_CELLS, Constants.HEIGHT_IN_CELLS, camera);

    }

    @Override
    public void show() {

    }

    @Override
    public void render(float delta) {

        Gdx.gl.glClearColor(.5f, .5f, .5f, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        update(delta);

        game.getBatch().setProjectionMatrix(camera.combined);
        game.getBatch().begin();

        level.draw(game.getBatch());
        game.getBatch().end();

        renderer.render(level.getWorld(), game.getBatch().getProjectionMatrix());

    }

    public void update(float dt){
        level.update(dt);

        if (Gdx.input.isKeyPressed(Input.Keys.A))
            camera.translate(-10, 0);
        if (Gdx.input.isKeyPressed(Input.Keys.D))
            camera.translate(10, 0);
        if (Gdx.input.isKeyPressed(Input.Keys.W))
            camera.translate(0, 10);
        if (Gdx.input.isKeyPressed(Input.Keys.S))
            camera.translate(0, -10);
        camera.update();
    }

    @Override
    public void resize(int width, int height) {
        viewport.update(width, height);
    }

    @Override
    public void pause() {

    }

    @Override
    public void resume() {

    }

    @Override
    public void hide() {

    }

    @Override
    public void dispose() {

    }

    public GameClass getGame(){
        return game;
    }

    public OrthographicCamera getCamera(){
        return camera;
    }
}
