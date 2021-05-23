package com.mygdx.game;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class Fondo {

    Animacion animacion = new Animacion(2,

            new Texture("fondo/fondo (1).gif"),
            new Texture("fondo/fondo (2).gif"),
            new Texture("fondo/fondo (3).gif"),
            new Texture("fondo/fondo (4).gif"),
            new Texture("fondo/fondo (5).gif"),
            new Texture("fondo/fondo (6).gif"),
            new Texture("fondo/fondo (7).gif"),
            new Texture("fondo/fondo (8).gif"),
            new Texture("fondo/fondo (9).gif"),
            new Texture("fondo/fondo (10).gif"),
            new Texture("fondo/fondo (11).gif"),
            new Texture("fondo/fondo (12).gif"),
            new Texture("fondo/fondo (13).gif"),
            new Texture("fondo/fondo (14).gif"),
            new Texture("fondo/fondo (15).gif"),
            new Texture("fondo/fondo (16).gif"),
            new Texture("fondo/fondo (17).gif"),
            new Texture("fondo/fondo (18).gif"),
            new Texture("fondo/fondo (19).gif"),
            new Texture("fondo/fondo (20).gif"),
            new Texture("fondo/fondo (21).gif"),
            new Texture("fondo/fondo (22).gif"),
            new Texture("fondo/fondo (23).gif"),
            new Texture("fondo/fondo (24).gif"),
            new Texture("fondo/fondo (25).gif"),
            new Texture("fondo/fondo (26).gif"),
            new Texture("fondo/fondo (27).gif"),
            new Texture("fondo/fondo (28).gif"),
            new Texture("fondo/fondo (29).gif"),
            new Texture("fondo/fondo (30).gif"),
            new Texture("fondo/fondo (31).gif"),
            new Texture("fondo/fondo (32).gif"),
            new Texture("fondo/fondo (33).gif"),
            new Texture("fondo/fondo (34).gif"),
            new Texture("fondo/fondo (35).gif"),
            new Texture("fondo/fondo (36).gif"),
            new Texture("fondo/fondo (37).gif"),
            new Texture("fondo/fondo (38).gif"),
            new Texture("fondo/fondo (39).gif"),
            new Texture("fondo/fondo (40).gif"),
            new Texture("fondo/fondo (41).gif"),
            new Texture("fondo/fondo (42).gif"),
            new Texture("fondo/fondo (43).gif"),
            new Texture("fondo/fondo (44).gif"),
            new Texture("fondo/fondo (45).gif"),
            new Texture("fondo/fondo (46).gif"),
            new Texture("fondo/fondo (47).gif"),
            new Texture("fondo/fondo (48).gif"),
            new Texture("fondo/fondo (49).gif"),
            new Texture("fondo/fondo (50).gif"),
            new Texture("fondo/fondo (51).gif"),
            new Texture("fondo/fondo (52).gif"),
            new Texture("fondo/fondo (53).gif"),
            new Texture("fondo/fondo (54).gif"),
            new Texture("fondo/fondo (55).gif"),
            new Texture("fondo/fondo (56).gif"),
            new Texture("fondo/fondo (57).gif"),
            new Texture("fondo/fondo (58).gif"),
            new Texture("fondo/fondo (59).gif"),
            new Texture("fondo/fondo (60).gif"),
            new Texture("fondo/fondo (61).gif"),
            new Texture("fondo/fondo (62).gif"),
            new Texture("fondo/fondo (63).gif"),
            new Texture("fondo/fondo (64).gif"),
            new Texture("fondo/fondo (65).gif"),
            new Texture("fondo/fondo (66).gif"),
            new Texture("fondo/fondo (67).gif"),
            new Texture("fondo/fondo (68).gif"),
            new Texture("fondo/fondo (69).gif"),
            new Texture("fondo/fondo (70).gif"),
            new Texture("fondo/fondo (71).gif"),
            new Texture("fondo/fondo (72).gif"),
            new Texture("fondo/fondo (73).gif"),
            new Texture("fondo/fondo (74).gif"),
            new Texture("fondo/fondo (75).gif"),
            new Texture("fondo/fondo (76).gif"),
            new Texture("fondo/fondo (77).gif"),
            new Texture("fondo/fondo (78).gif"),
            new Texture("fondo/fondo (79).gif"),
            new Texture("fondo/fondo (80).gif"),
            new Texture("fondo/fondo (81).gif")







            );

    public void render(SpriteBatch batch) {

            batch.draw(animacion.obtenerFrame(), 0, 0, 640, 480);


    }
}