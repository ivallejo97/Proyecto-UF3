package com.mygdx.game;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

import java.util.ArrayList;
import java.util.List;

public class Enemigo {
    Texture texture = new Texture("alien.png");
    float x, y, w, h, vx, vy;
    Temporizador cambioVelocidad = new Temporizador(60);



    Enemigo() {
        x = Utils.random.nextInt(620);
        y = 450;
        w = 50;
        h = 50;
        vx = 0;
        vy = -2;
    }

    public void update() {
        y += vy;
        x += vx;

        if (cambioVelocidad.suena()) {
            vy = Utils.random.nextInt(6)-3;
            vx = Utils.random.nextInt(6)-3;
        }
        if(x < 0) x = 0;
        if(x > 540 + w) x = 540 +w;
        if(y > 428 + h) y = 428 +h;
    }

    void render(SpriteBatch batch) {
        batch.draw(texture, x, y, w, h);


    }
}
