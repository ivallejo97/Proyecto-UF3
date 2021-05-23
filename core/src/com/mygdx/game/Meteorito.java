package com.mygdx.game;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class Meteorito {
    Texture texture = new Texture("meteorito.png");
    float x, y, w, h, vx, vy;
    Temporizador cambioVelocidad = new Temporizador(60);



    Meteorito() {
        x = Utils.random.nextInt(620);
        y = 450;
        w = 50;
        h = 50;
        vx = 0;
        vy = -0;
    }

    public void update() {
        y += vy;
        x += vx;

            vy = -5;
            vx = 0;

        if(x < 0) x = 0;
        if(x > 540 + w) x = 540 +w;
        if(y > 428 + h) y = 428 +h;
    }

    void render(SpriteBatch batch) {
        batch.draw(texture, x, y, w, h);


    }
}
