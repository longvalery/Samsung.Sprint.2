package com.mygdx.game.components;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.GlyphLayout;

public class TextButton {

    BitmapFont font;

    String text;
    Texture texture;

    int x, y;
    int textX, textY;
    int buttonWidth, buttonHeight;
    int textWidth, textHeight;

    public TextButton(int x, int y, String text) {
        this.text = text;
        this.x = x;
        this.y = y;

        this.font = new BitmapFont();
        this.font.getData().scale(5f);
        this.font.setColor(Color.WHITE);

        GlyphLayout gl = new GlyphLayout(font, this.text);
        this.textWidth = (int) gl.width;
        this.textHeight = (int) gl.height;

        this.texture = new Texture("button/button_bg.png");
        this.buttonWidth = this.texture.getWidth();
        this.buttonHeight = this.texture.getHeight();

        this.textX = x + (this.buttonWidth - this.textWidth) / 2;
        this.textY = y + (this.buttonHeight + this.textHeight) / 2;
    }

    public void draw(Batch batch) {
        batch.draw(this.texture, this.x, this.y, this.buttonWidth, this.buttonHeight);
        font.draw(batch, this.text, this.textX, this.textY);
    }

    public void dispose() {
        texture.dispose();
        font.dispose();
    }

    public boolean inside(int x, int y) {
        System.out.println(String.format("x %d y %d this.x %d this.y %d ", x, y, this.x, this.y));
        return (x >= this.x) && (x < (this.x + this.buttonWidth))
               && (y >= this.y) && (y < (this.y + this.buttonHeight)) ;
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

    public int getButtonWidth() {
        return buttonWidth;
    }

    public void setButtonWidth(int buttonWidth) {
        this.buttonWidth = buttonWidth;
    }

    public int getButtonHeight() {
        return buttonHeight;
    }

    public void setButtonHeight(int buttonHeight) {
        this.buttonHeight = buttonHeight;
    }
}