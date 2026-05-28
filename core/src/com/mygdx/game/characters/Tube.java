package com.mygdx.game.characters;


import static com.badlogic.gdx.math.MathUtils.random;
import static com.mygdx.game.MyGdxGame.SCR_HEIGHT;
import static com.mygdx.game.MyGdxGame.SCR_WIDTH;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;

public class Tube {
    final private int gapHeight = 400;
    final private int speed = 5;
    private int yLow;
    private int yHigh;
    private int x;
    private int width;
    private int distanceBetweenTubes;
    private boolean isPointReceived;
    Texture textureUpperTube ;
    Texture textureDownTube;

     public Tube(int tubeCount, int tubeIndex) {
         int padding =  random.nextInt(this.gapHeight / 3);
         this.textureUpperTube = new Texture("tube/tube_flipped.png");
         this.textureDownTube = new Texture("tube/tube.png");
         int gapY = random.nextInt(SCR_HEIGHT - 2 * (padding) - gapHeight);
         this.yLow = padding - this.textureDownTube.getHeight();
         this.yHigh = padding + gapY + this.gapHeight;
         this.width = this.textureDownTube.getWidth();
         this.distanceBetweenTubes = (SCR_WIDTH + this.width) / (tubeCount - 1);
         this.x = distanceBetweenTubes * tubeIndex + SCR_WIDTH;
         this.isPointReceived = false;
     }

    public void draw(Batch batch) {
        batch.draw(this.textureDownTube, this.x, this.yLow);
        batch.draw(this.textureUpperTube, this.x, this.yHigh);
    }

    public void move() {
        this.x -= this.speed;
        if (this.x < - (2 * this.width)) {
            this.isPointReceived = false;
            int padding =  random.nextInt(this.gapHeight / 3);
            int gapY = random.nextInt(SCR_HEIGHT - 2 * (padding) - gapHeight);
            this.x = SCR_WIDTH + this.distanceBetweenTubes;
            this.yLow = padding - this.textureDownTube.getHeight();
            this.yHigh = padding + gapY + this.gapHeight;
        }
    }

    public boolean isHit(Bird bird) {
         boolean collitionX = ((bird.getX() <= this.x + this.width) && (bird.getX() + bird.BIRD_SIZE >= x));
         if (collitionX) { return ((bird.getY() <= (this.yLow + this.textureDownTube.getHeight()))
                 || ((bird.getY() + bird.BIRD_SIZE )>= this.yHigh)); }
         else { return false; }
    }

    public boolean needAddPoint(Bird bird) {
         boolean result = ((bird.getX() + bird.BIRD_SIZE) > (this.x + this.width)) && (! this.isPointReceived);
         if (result) { this.isPointReceived = true; }
         return result ;
    }

    public void dispose() {
        if (this.textureUpperTube != null) { this.textureUpperTube.dispose(); }
        if (this.textureDownTube != null ) { this.textureDownTube.dispose(); }
    }

}
