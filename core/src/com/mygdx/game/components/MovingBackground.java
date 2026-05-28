package com.mygdx.game.components;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.mygdx.game.MyGdxGame;

public class MovingBackground {
    private int texture1X,  texture2X;
    private int speed = 2;
    Texture texture;

    public MovingBackground(String path) {
        this.texture1X =0;
        texture2X = MyGdxGame.SCR_WIDTH;
        texture = new Texture(path);
    }
    public void draw(Batch batch) {
        batch.draw(this.texture, this.texture1X, 0, MyGdxGame.SCR_WIDTH, MyGdxGame.SCR_HEIGHT);
        batch.draw(this.texture, this.texture2X, 0, MyGdxGame.SCR_WIDTH, MyGdxGame.SCR_HEIGHT);
    }

    public void move() {
        this.texture1X -= this.speed;
        this.texture2X -= this.speed;
        if (this.texture1X <= -MyGdxGame.SCR_WIDTH) { this.texture1X = MyGdxGame.SCR_WIDTH; }
        if (this.texture2X <= -MyGdxGame.SCR_WIDTH) { this.texture2X = MyGdxGame.SCR_WIDTH; }
    }
    public void dispose() {
        this.texture.dispose();
    }

}