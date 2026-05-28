package com.mygdx.game.characters;

import static com.mygdx.game.MyGdxGame.SCR_HEIGHT;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;

public class Bird {
    final int BIRD_SIZE = 150;
    Texture texture;
    private int x = 0;


    private int y = 0;
    int speed = 5;


    private boolean jump = false;
    int jumpHeight = 0;
    final int maxHeightOfJump = 100;
    private Texture[] framesArray;
    private int frameCounter = 0;

    // Bird(int x, int y, Texture texture, int speed){
    public Bird(int x, int y, int speed){
        this.x = x;
        this.y = y;
        this.speed = speed;

        this.framesArray = new Texture[]{
                new Texture("bird/bird0.png"),
                new Texture("bird/bird1.png"),
                new Texture("bird/bird2.png"),
                new Texture("bird/bird1.png"),
        };
        /*
        this.framesArray = new Texture[]{
                createScaledTexture("bird/bird0.png", this.BIRD_SIZE, this.BIRD_SIZE),
                createScaledTexture("bird/bird1.png", this.BIRD_SIZE, this.BIRD_SIZE),
                createScaledTexture("bird/bird2.png", this.BIRD_SIZE, this.BIRD_SIZE),
                createScaledTexture("bird/bird1.png", this.BIRD_SIZE, this.BIRD_SIZE),
        };
         */

    }

//    // Вспомогательный метод (добавьте в ваш класс)
//    private Texture createScaledTexture(String path, int width, int height) {
//        Pixmap original = new Pixmap(new FileHandle(path));
//        Pixmap scaled = new Pixmap(width, height, original.getFormat());
//        scaled.drawPixmap(original, 0, 0, original.getWidth(), original.getHeight(), 0, 0, width, height);
//        Texture texture = new Texture(scaled);
//        original.dispose();
//        scaled.dispose();
//        return texture;
//    }

    public boolean isInField() {
        if (this.y + this.BIRD_SIZE < 0) return false;
        if (y > SCR_HEIGHT) return false;
        return true;
                               }
    public void onClick() {
        this.jump = true;
        this.jumpHeight = this.maxHeightOfJump + this.y;
    }
    private void update() {
//        this.x += this.speed;
//        this.y += this.speed;
        // Jump
        if (this.y >= this.jumpHeight) { this.jump = false; }
        if (this.jump) { this.y += this.speed; }
        else           { this.y -= this.speed; }
        // Fly

//        this.texture = this.framesArray[this.frameCounter];
//        this.frameCounter +=1;
//        if (this.frameCounter >= this.framesArray.length) { this.frameCounter = 0; }
        int frameMultiplier = 10;
        this.texture = this.framesArray[this.frameCounter / frameMultiplier];
        if (this.frameCounter++ == this.framesArray.length * frameMultiplier - 1) { frameCounter = 0; }
    }
    public void draw(Batch batch) {
        this.update();
        batch.draw(this.texture, this.x, this.y, this.BIRD_SIZE, this.BIRD_SIZE);

    }

    public void dispose() {
        if (this.texture != null) { this.texture.dispose();}
    }
    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }

    public boolean isJump() {
        return jump;
    }

    public void setJump(boolean jump) {
        this.jump = jump;
    }
}
