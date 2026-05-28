package com.mygdx.game.screens;

import static com.mygdx.game.MyGdxGame.SCR_HEIGHT;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.utils.ScreenUtils;
import com.mygdx.game.MyGdxGame;
import com.mygdx.game.components.PointCounter;
import com.mygdx.game.components.TextButton;

public class ScreenFinish extends ScreenStart {
    private TextButton exitButton;
    PointCounter pointCounter;
    private final int SCORE_X = 5;
    private final int SCORE_Y = SCR_HEIGHT - 30;
    public ScreenFinish(MyGdxGame myGdxGame, String text, Screen screen, String exit_text ) {
        super(myGdxGame, text, screen);
        this.exitButton = new TextButton(700, 400, exit_text);
        this.pointCounter = new PointCounter(SCORE_X, SCORE_Y);
    }

    private void handle(){
        if (Gdx.input.justTouched()) {
            int x, y;
            x = Gdx.input.getX();
            y = Gdx.input.getY();
            if (this.getButton().inside(x, SCR_HEIGHT - y)) { getMyGdxGame().setScreen(this.getScreen()); }
            if (this.exitButton.inside(x, SCR_HEIGHT - y)) {
                System.out.println(String.format("X: %d, Y: %d", x, y));
                Gdx.app.exit();
                                                               }
        }
    }

    @Override
    public void render(float delta) {
        this.handle();
        ScreenUtils.clear(0, 0, 0, 1);
        this.getMyGdxGame().camera.update();
        this.getMyGdxGame().batch.setProjectionMatrix(this.getMyGdxGame().camera.combined);
        this.getMyGdxGame().batch.begin();
        this.movingBackground.draw(this.getMyGdxGame().batch);
        this.getButton().draw(getMyGdxGame().batch);
        this.exitButton.draw(getMyGdxGame().batch);
        this.pointCounter.draw(getMyGdxGame().batch, getMyGdxGame().game.getScore());
        this.getMyGdxGame().batch.end();
    }

    @Override
    public void dispose() {
        this.exitButton.dispose();
        super.dispose();
    }
}
