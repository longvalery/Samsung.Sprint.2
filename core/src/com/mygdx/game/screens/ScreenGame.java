package com.mygdx.game.screens;

import static com.mygdx.game.MyGdxGame.SCR_HEIGHT;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.utils.ScreenUtils;
import com.mygdx.game.MyGdxGame;
import com.mygdx.game.characters.Bird;
import com.mygdx.game.characters.Tube;
import com.mygdx.game.components.MovingBackground;
import com.mygdx.game.components.PointCounter;

public class ScreenGame implements Screen {
    private int tubeCount;
    MyGdxGame myGdxGame;
    Bird bird;
//    Tube tube;
    Tube[] tubes;
    private boolean isGameOver;
    private int score;

    private final int SCORE_X = 5;
    private final int SCORE_Y = SCR_HEIGHT - 30;

    PointCounter pointCounter;
    MovingBackground movingBackground;


    public ScreenGame(MyGdxGame myGdxGame) {
        this.myGdxGame = myGdxGame;
        // this.bird = new Bird(0,0,new Texture("bird/bird0.png"), 5);
        this.bird = new Bird(0,SCR_HEIGHT / 2, 5);
//        this.tube = new Tube();
        this.tubeCount = 3;
        this.tubes = new Tube[this.tubeCount];
        for (int i = 0; i < this.tubeCount; i++) { this.tubes[i] = new Tube(this.tubeCount, i); }
        this.pointCounter = new PointCounter(SCORE_X, SCORE_Y);
        this.movingBackground = new MovingBackground("background/game_bg.png");
    }

    @Override
    public void show() {
        this.isGameOver = false;
        this.score = 0;
        this.bird.setX(0);
        this.bird.setY(SCR_HEIGHT / 2);
        this.bird.setJump(false);

    }
    private void update() {
        if (Gdx.input.justTouched()) { this.bird.onClick();}
        this.myGdxGame.camera.update();
        this.myGdxGame.batch.setProjectionMatrix(this.myGdxGame.camera.combined);
        for (Tube tube : this.tubes) {
            tube.move();
            if (tube.isHit(this.bird)) {
                isGameOver = true;
            }
            else {
                if (tube.needAddPoint(this.bird)) {
                    this.score += 100;
                                                  }
                  }
        }
        if (! bird.isInField()) { isGameOver = true; }
        this.movingBackground.move();
        if (this.isGameOver) { myGdxGame.setScreen(myGdxGame.finish); }
    }

    @Override
    public void render(float delta) {
//        if (Gdx.input.justTouched()) { System.out.println("Just touched"); }
        ScreenUtils.clear(1, 0, 0, 1);
        this.update();
        this.myGdxGame.batch.begin();
        this.movingBackground.draw(this.myGdxGame.batch);
        // this.myGdxGame.batch.draw(this.birdTexture, this.x, this.y);
        this.bird.draw(this.myGdxGame.batch);
        for (Tube tube : this.tubes) { tube.draw(this.myGdxGame.batch); }
        this.pointCounter.draw(this.myGdxGame.batch, this.score);
//        this.tube.draw(this.myGdxGame.batch);
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
        for (Tube tube : this.tubes) { tube.dispose(); }
//        this.tube.dispose();
        this.bird.dispose();
        this.pointCounter.dispose();
        this.movingBackground.dispose();

    }

    public int getScore() {
        return score;
    }
}
