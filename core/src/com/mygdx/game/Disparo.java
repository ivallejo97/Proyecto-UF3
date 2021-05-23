package com.mygdx.game;


import com.badlogic.gdx.Files;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class Disparo {
    static Texture texture = new Texture("misil.png");
    float x, y, w, h, v, f;
    Music music;
    float volume = 0.2f;

    Disparo(float xNave, float yNave) {
        w = 30;
        h = 70;
        x = xNave-w/2;
        y = yNave;
        v = 10;
        music = Gdx.audio.newMusic(Gdx.files.getFileHandle("disparo.mp3", Files.FileType.Internal));
        music.setVolume(volume);
        music.play();
    }

    void update() {
        y += v;
    }

    public void render(SpriteBatch batch) {
        batch.draw(texture, x, y, w, h);

    }
}