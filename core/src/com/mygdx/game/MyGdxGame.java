package com.mygdx.game;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.mygdx.game.screens.ScreenFinish;
import com.mygdx.game.screens.ScreenGame;
import com.mygdx.game.screens.ScreenStart;

public class MyGdxGame extends Game {

	public static final int SCR_WIDTH = 1280;
	public static final int SCR_HEIGHT = 720;
	public OrthographicCamera camera;
	public SpriteBatch batch;
	public ScreenGame game;
	public ScreenStart start, finish;

	@Override
	public void create () {
		this.batch = new SpriteBatch();
		this.camera = new OrthographicCamera();
		this.camera.setToOrtho(false, SCR_WIDTH, SCR_HEIGHT);
		this.game = new ScreenGame(this);
		this.start = new ScreenStart(this, "Start", this.game);
		this.finish = new ScreenFinish(this, "Restart", this.game, "Exit");
		this.setScreen(this.start);
	}

	@Override
	public void render () {
		this.screen.render(1);
		// this.game.render(1);
	}

//	@Override
//	public void render () {
//		this.update();
//		ScreenUtils.clear(1, 0, 0, 1);
//		batch.begin();
//		batch.draw(birdTexture, this.birdX, this.birdX);
//		batch.end();
//	}
	
	@Override
	public void dispose () {
		this.batch.dispose();
		this.game.dispose();
		this.start.dispose();
		this.finish.dispose();
		super.dispose();
	}
}
