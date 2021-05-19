package com.mygdx.game;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class Emoticonos {
    Texture texture = new Texture("vida.png");
    Texture texture1 = new Texture("puntos.png");


    public void render(SpriteBatch batch) {
        batch.draw(texture, 7, 395, 45, 35);
        batch.draw(texture1, 7, 440, 45, 35);

    }


}
