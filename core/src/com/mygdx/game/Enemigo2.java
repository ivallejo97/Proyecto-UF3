package com.mygdx.game;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

import java.util.ArrayList;
import java.util.List;

public class Enemigo2 {
    Texture texture = new Texture("alien2.png");
    float x, y, w, h, vx, vy;
    Temporizador cambioVelocidad = new Temporizador(100);
    int vidas = 2;
    boolean muerto = false;



    Enemigo2() {
        x = Utils.random.nextInt(620);
        y = 450;
        w = 75;
        h = 75;
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
        if(x > 490 + w) x = 490 +w;
        if(y > 428 + h) y = 428 +h;
    }

    void render(SpriteBatch batch) {
        if (vidas == 0){
            muerto = true;
        } else {
            batch.draw(texture, x, y, w, h);
        }
    }
    public void morir() {
        vidas--;
        muerto = true;
    }
}
