package com.mygdx.game;


import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class Disparo {
    static Texture texture = new Texture("misil.png");
    float x, y, w, h, v, f;

    Disparo(float xNave, float yNave) {
        w = 30;
        h = 70;
        x = xNave-w/2;
        y = yNave;
        v = 10;
    }

    void update() {
        y += v;
    }

    public void render(SpriteBatch batch) {
        batch.draw(texture, x, y, w, h);
    }
}