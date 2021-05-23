package com.mygdx.game;

import com.badlogic.gdx.Files;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

import java.util.ArrayList;
import java.util.List;

public class Jugador {
    Animacion animacion = new Animacion(25,
            new Texture("nave.png"),
            new Texture("nave2.png"),
            new Texture("nave3.png")
    );

    Animacion animaciontocada = new Animacion(25,
            new Texture("naveTocada.png"),
            new Texture("naveTocada2.png"),
            new Texture("naveTocada3.png")
    );
    Animacion animacionmuerta = new Animacion(25,
            new Texture("naveMuerta.png"),
            new Texture("naveMuerta2.png"),
            new Texture("naveMuerta3.png")
    );
    float x, y, w, h, v, t;
    List<Disparo> disparos = new ArrayList<>();
    int vidas = 3;
    int puntos = 0;
    boolean muerto = false;
    Temporizador temporizadorFireRate = new Temporizador(10);
    Temporizador temporizadorRespawn = new Temporizador(120, false);
    Music music[] = new Music[2];
    float volume = 1.5f;


    Jugador() {
        x = 100;
        y = 100;
        w = 100;
        h = 100;
        v = 5;
        t = 2;
    }

    void update() {
        for (Disparo disparo : disparos) disparo.update();

        if (Gdx.input.isKeyPressed(Input.Keys.D)) x += v;
        if (Gdx.input.isKeyPressed(Input.Keys.A)) x -= v;
        if (Gdx.input.isKeyPressed(Input.Keys.W)) y += v;
        if (Gdx.input.isKeyPressed(Input.Keys.S)) y -= v;
        if (Gdx.input.isKeyPressed(Input.Keys.D) && Gdx.input.isKeyPressed(Input.Keys.SHIFT_LEFT)) x += v*t;
        if (Gdx.input.isKeyPressed(Input.Keys.A)&& Gdx.input.isKeyPressed(Input.Keys.SHIFT_LEFT)) x -= v*t;
        if (Gdx.input.isKeyPressed(Input.Keys.W)&& Gdx.input.isKeyPressed(Input.Keys.SHIFT_LEFT)) y += v*t;
        if (Gdx.input.isKeyPressed(Input.Keys.S)&& Gdx.input.isKeyPressed(Input.Keys.SHIFT_LEFT)) y -= v*t;

        if (Gdx.input.isKeyPressed(Input.Keys.RIGHT)) x += v;
        if (Gdx.input.isKeyPressed(Input.Keys.LEFT)) x -= v;
        if (Gdx.input.isKeyPressed(Input.Keys.UP)) y += v;
        if (Gdx.input.isKeyPressed(Input.Keys.DOWN)) y -= v;
        if (Gdx.input.isKeyPressed(Input.Keys.RIGHT) && Gdx.input.isKeyPressed(Input.Keys.SHIFT_LEFT)) x += v*t;
        if (Gdx.input.isKeyPressed(Input.Keys.LEFT)&& Gdx.input.isKeyPressed(Input.Keys.SHIFT_LEFT)) x -= v*t;
        if (Gdx.input.isKeyPressed(Input.Keys.UP)&& Gdx.input.isKeyPressed(Input.Keys.SHIFT_LEFT)) y += v*t;
        if (Gdx.input.isKeyPressed(Input.Keys.DOWN)&& Gdx.input.isKeyPressed(Input.Keys.SHIFT_LEFT)) y -= v*t;
        if (Gdx.input.isKeyPressed(Input.Keys.SPACE) && temporizadorFireRate.suena() && !muerto) {
            disparos.add(new Disparo(x+w/2, y+h));
        }
        if(x < 0) x = 0;
        if(x > 440 + w) x = 440 +w;
        if(y < 0) y = 0;
        if(y > 290 + h) y = 290 + h;

        if (temporizadorRespawn.suena()) {
            muerto = false;
        }
    }

    void render(SpriteBatch batch) {

        if (vidas == 0){
            batch.draw(animacionmuerta.obtenerFrame(), x, y, w, h);
            music[1].stop();
            music[0] = Gdx.audio.newMusic(Gdx.files.getFileHandle("explosion.mp3", Files.FileType.Internal));
            music[0].setVolume(volume);
            music[0].play();
        } else if (muerto){
            batch.draw(animaciontocada.obtenerFrame(), x, y, w, h);
            music[1] = Gdx.audio.newMusic(Gdx.files.getFileHandle("impacto.mp3", Files.FileType.Internal));
            music[1].setVolume(volume);
            music[1].play();
        } else{
            batch.draw(animacion.obtenerFrame(), x, y, w, h);
        }

        for (Disparo disparo : disparos) disparo.render(batch);
    }

    public void morir() {
        vidas--;
        muerto = true;
        temporizadorRespawn.activar();
    }
}