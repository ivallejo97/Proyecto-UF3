package com.mygdx.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class ScoreBoard {

    class Score {
        String nombre;
        int puntuacion;

        public Score(String nombre, int puntuacion) {
            this.nombre = nombre;
            this.puntuacion = puntuacion;
        }
    }

    Texture background = new Texture("back.png");
    char[] nombre = {'A','A','A','A'};  // 65:A -> 90:Z
    int index = 0;  // 0=1a letra; 1=2a letra; 2=3a letra; 3=replay; 4=exit
    private boolean saved;

    List<Score> scoreList = new ArrayList<>();
    List<Score> top = new ArrayList<>();
    /**
     * @param puntos
     * @return
     *         0 = seguir mostrando el scoreboard
     *         1 = replay
     *         2 = exit
     */
    int update(int puntos){
        if(index < 4 && Gdx.input.isKeyJustPressed(Input.Keys.D)) {
            nombre[index]++;
            if(nombre[index] > 90) {
                nombre[index] = 65;
            }
        }
        if(index < 4 && Gdx.input.isKeyJustPressed(Input.Keys.RIGHT)) {
            nombre[index]++;
            if(nombre[index] > 90) {
                nombre[index] = 65;
            }
        }
        if(index < 4 && Gdx.input.isKeyJustPressed(Input.Keys.A)) {
            nombre[index]--;
            if(nombre[index] < 65) {
                nombre[index] = 90;
            }
        }
        if(index < 4 && Gdx.input.isKeyJustPressed(Input.Keys.LEFT)) {
            nombre[index]--;
            if(nombre[index] < 65) {
                nombre[index] = 90;
            }
        }

        if(Gdx.input.isKeyJustPressed(Input.Keys.ENTER)) {
            if(index == 4) {
                return 1;
            } else if (index == 5) {
                return 2;
            }
            index++;
        }

        if(index > 3 && Gdx.input.isKeyJustPressed(Input.Keys.D)) {
            if (index == 4) index = 5; else index = 4;
        }
        if(index > 3 && Gdx.input.isKeyJustPressed(Input.Keys.RIGHT)) {
            if (index == 4) index = 5; else index = 4;
        }
        if(index > 3 && Gdx.input.isKeyJustPressed(Input.Keys.A)) {
            if (index == 4) index = 5; else index = 4;
        }
        if(index > 3 && Gdx.input.isKeyJustPressed(Input.Keys.LEFT)) {
            if (index == 4) index = 5; else index = 4;
        }

        if(index > 3 && !saved) {
            guardarPuntuacion(puntos);
            saved = true;
        }
        return 0;
    }

    void render(SpriteBatch batch, BitmapFont font) {
        batch.draw(background, 60, 120, 520, 320);

        if(!saved) {
            font.draw(batch, "INTRODUZCA  SU  NOMBRE", 140, 400);

            font.getData().setScale(3);
            font.getRegion().getTexture().setFilter(Texture.TextureFilter.Linear, Texture.TextureFilter.Linear);
            for (int i = 0; i < 4; i++) {
                if(index == i){
                    font.setColor(Color.RED);
                }
                font.draw(batch, ""+ nombre[i], 250+40*i, 280);
                font.setColor(Color.WHITE);
            }
            font.getData().setScale(2);
        }else {
            font.draw(batch, "MAXIMAS PUNTUACIONES", 140, 400);

            for (int i = 0; i < 5 && i < top.size(); i++) {
                font.draw(batch, top.get(i).nombre, 200, 340 - i * 40);
                font.draw(batch, "" + top.get(i).puntuacion, 380, 340 - i * 40);
            }

            if(index == 4) font.setColor(Color.RED);
            font.draw(batch, "VOLVER A JUGAR", 100, 60);
            font.setColor(Color.WHITE);

            if(index == 5) font.setColor(Color.RED);
            font.draw(batch, "SALIR", 400, 60);
            font.setColor(Color.WHITE);
        }
    }

    void guardarPuntuacion(int puntuacion) {
        try {
            FileWriter fileWriter = new FileWriter("scores.txt", true);
            fileWriter.write(""+ nombre[0]+ nombre[1]+ nombre[2] + nombre[3] + "," + puntuacion + "\n");
            fileWriter.close();

        } catch (IOException e) {
            e.printStackTrace();
        }

        leerPuntuaciones();
    }

    void leerPuntuaciones() {
        try {
            Scanner scanner = new Scanner(new File("scores.txt"));
            scanner.useDelimiter(",|\n");

            while (scanner.hasNext()) {
                String nombre = scanner.next();
                int puntos = scanner.nextInt();

                scoreList.add(new Score(nombre, puntos));
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        while(scoreList.size() > 0) {
            int posMax = 0;
            for (int i = 0; i < scoreList.size(); i++) {
                if (scoreList.get(i).puntuacion > scoreList.get(posMax).puntuacion) {
                    posMax = i;
                }
            }
            top.add(scoreList.get(posMax));
            scoreList.remove(posMax);
        }
    }
}