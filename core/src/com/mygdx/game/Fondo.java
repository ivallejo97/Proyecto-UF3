package com.mygdx.game;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class Fondo {

    Animacion animacion = new Animacion(5,

            new Texture("fondo1.gif"),
            new Texture("fondo2.gif"),
            new Texture("fondo3.gif"),
            new Texture("fondo4.gif"),
            new Texture("fondo5.gif"),
            new Texture("fondo6.gif"),
            new Texture("fondo7.gif"),
            new Texture("fondo8.gif"),
            new Texture("fondo9.gif"),
            new Texture("fondo10.gif"),
            new Texture("fondo11.gif"),
            new Texture("fondo12.gif"),
            new Texture("fondo13.gif"),
            new Texture("fondo14.gif"),
            new Texture("fondo15.gif"),
            new Texture("fondo16.gif"),
            new Texture("fondo17.gif"),
            new Texture("fondo18.gif"),
            new Texture("fondo19.gif"),
            new Texture("fondo20.gif"),
            new Texture("fondo21.gif"),
            new Texture("fondo22.gif"),
            new Texture("fondo23.gif"),
            new Texture("fondo24.gif"),
            new Texture("fondo25.gif"),
            new Texture("fondo26.gif"),
            new Texture("fondo27.gif"),
            new Texture("fondo28.gif"),
            new Texture("fondo29.gif")

    );

    public void render(SpriteBatch batch) {

            batch.draw(animacion.obtenerFrame(), 0, 0, 640, 480);


    }
}