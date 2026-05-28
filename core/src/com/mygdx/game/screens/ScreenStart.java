package com.mygdx.game.screens;

import static com.mygdx.game.MyGdxGame.SCR_HEIGHT;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.utils.ScreenUtils;
import com.mygdx.game.MyGdxGame;
import com.mygdx.game.components.MovingBackground;
import com.mygdx.game.components.TextButton;

public class ScreenStart implements Screen {
    private MyGdxGame myGdxGame;
    MovingBackground movingBackground;
    private TextButton button;
    private Screen screen;

    public ScreenStart(MyGdxGame myGdxGame, String text, Screen screen) {
        this.myGdxGame = myGdxGame;
        this.button = new TextButton(50, 400, text);
        this.movingBackground = new MovingBackground("background/restart_bg.png");
        this.screen = screen;
    }

    @Override
    public void show() {

    }

    private void handle(){
        if (Gdx.input.justTouched()) {
            System.out.println(String.format("X: %d, Y: %d", Gdx.input.getX(), Gdx.input.getY()));
            if (this.button.inside(Gdx.input.getX(), SCR_HEIGHT - Gdx.input.getY())) { myGdxGame.setScreen(this.screen); }
        }
    }
    @Override
    public void render(float delta) {
        this.handle();
        ScreenUtils.clear(0, 0, 0, 1);
        this.myGdxGame.camera.update();
        this.myGdxGame.batch.setProjectionMatrix(this.myGdxGame.camera.combined);
        this.myGdxGame.batch.begin();
        this.movingBackground.draw(this.myGdxGame.batch);
        this.button.draw(myGdxGame.batch);
        this.myGdxGame.batch.end();
    }

    @Override
    public void resize(int width, int height) {

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
       this.button.dispose();
       this.movingBackground.dispose();
    }

    public TextButton getButton() {
        return button;
    }

    public Screen getScreen() {
        return screen;
    }

    public MyGdxGame getMyGdxGame() {
        return myGdxGame;
    }
}
