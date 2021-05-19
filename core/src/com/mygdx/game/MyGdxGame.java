package com.mygdx.game;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

import java.util.ArrayList;
import java.util.List;

public class MyGdxGame extends ApplicationAdapter {
	SpriteBatch batch;
	BitmapFont font;
	Fondo fondo;
	Jugador jugador;
	Emoticonos emoticonos;
	List<Enemigo> enemigos;
	List<Enemigo2> enemigos2;
	List<Disparo> disparosAEliminar;
	List<Enemigo> enemigosAEliminar;
	List<Enemigo2> enemigosAEliminar2;
	Temporizador temporizadorNuevoEnemigo;
	Temporizador temporizadorNuevoEnemigo2;
	ScoreBoard scoreboard;
	boolean gameover;


	@Override
	public void create() {
		batch = new SpriteBatch();
		font = new BitmapFont();
		font.setColor(Color.WHITE);
		font.getRegion().getTexture().setFilter(Texture.TextureFilter.Linear, Texture.TextureFilter.Linear);
		font.getData().setScale(2f);

		inicializarJuego();

	}
	void inicializarJuego(){

		fondo = new Fondo();
		jugador = new Jugador();
		emoticonos = new Emoticonos();
		enemigos = new ArrayList<>();
		enemigos2 = new ArrayList<>();
		temporizadorNuevoEnemigo = new Temporizador(120);
		temporizadorNuevoEnemigo2 = new Temporizador(240);
		disparosAEliminar = new ArrayList<>();
		enemigosAEliminar = new ArrayList<>();
		enemigosAEliminar2 = new ArrayList<>();
		scoreboard = new ScoreBoard();

		gameover = false;
	}

	void update() {
		Temporizador.framesJuego += 1;

		if (temporizadorNuevoEnemigo.suena()) enemigos.add(new Enemigo());

		if(!gameover) {
			jugador.update();
		}

		for (Enemigo enemigo : enemigos) enemigo.update();              // enemigos.forEach(Enemigo::update);

			for (Enemigo enemigo : enemigos) {
				for (Disparo disparo : jugador.disparos) {
					if (Utils.solapan(disparo.x, disparo.y, disparo.w, disparo.h, enemigo.x, enemigo.y, enemigo.w, enemigo.h)) {
						disparosAEliminar.add(disparo);
						enemigosAEliminar.add(enemigo);
						jugador.puntos++;
						break;
					}
				}

				if (!gameover && !jugador.muerto && Utils.solapan(enemigo.x, enemigo.y, enemigo.w, enemigo.h, jugador.x, jugador.y, jugador.w, jugador.h)) {
					jugador.morir();
					enemigosAEliminar.add(enemigo);
					if (jugador.vidas == 0){
						gameover = true;
					}
				}
				if (jugador.puntos >= 20){
					temporizadorNuevoEnemigo.frecuencia = 60;
				}
				if (enemigo.y < -enemigo.w)enemigosAEliminar.add(enemigo);
			}



		if (jugador.puntos >= 20){

			if (temporizadorNuevoEnemigo2.suena()) enemigos2.add(new Enemigo2());
			for (Enemigo2 enemigo2 : enemigos2) enemigo2.update();

			for (Enemigo2 enemigo2 : enemigos2) {
				for (Disparo disparo : jugador.disparos) {
					if (Utils.solapan(disparo.x, disparo.y, disparo.w, disparo.h, enemigo2.x, enemigo2.y, enemigo2.w, enemigo2.h)) {
						disparosAEliminar.add(disparo);
						enemigo2.morir();
						if (enemigo2.vidas == 0){
							enemigosAEliminar2.add(enemigo2);
							jugador.puntos++;
						}
						break;
					}
				}

				if (!gameover && !jugador.muerto && Utils.solapan(enemigo2.x, enemigo2.y, enemigo2.w, enemigo2.h, jugador.x, jugador.y, jugador.w, jugador.h)) {
					jugador.morir();
					enemigo2.morir();
					if (enemigo2.vidas == 0){
						enemigosAEliminar2.add(enemigo2);
					}
					if (jugador.vidas == 0){
						gameover = true;
					}
				}

				if (enemigo2.y < -enemigo2.w) enemigosAEliminar2.add(enemigo2);
			}
		}


		for (Disparo disparo : jugador.disparos)
			if (disparo.x > 640)
				disparosAEliminar.add(disparo);

		for (Disparo disparo : disparosAEliminar) jugador.disparos.remove(disparo);       // disparosAEliminar.forEach(disparo -> jugador.disparos.remove(disparo));
		for (Enemigo enemigo : enemigosAEliminar) enemigos.remove(enemigo);               // enemigosAEliminar.forEach(enemigo -> enemigos.remove(enemigo));
		if (jugador.puntos >= 20){
			for (Enemigo2 enemigo2 : enemigosAEliminar2) enemigos2.remove(enemigo2);
			enemigosAEliminar2.clear();
		}
		disparosAEliminar.clear();
		enemigosAEliminar.clear();

		if(gameover) {
			int result = scoreboard.update(jugador.puntos);
			if(result == 1) {
				inicializarJuego();
			} else if (result == 2) {
				Gdx.app.exit();
			}
		}

	}

	@Override
	public void render() {
		Gdx.gl.glClearColor(0, 0, 0, 0);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

		update();

		batch.begin();
		fondo.render(batch);
		jugador.render(batch);
		emoticonos.render(batch);
		for (Enemigo enemigo : enemigos) enemigo.render(batch);
		if (jugador.puntos >= 20){
			for (Enemigo2 enemigo2 : enemigos2) enemigo2.render(batch);
		}
		font.draw(batch, "" + jugador.vidas, 55, 430);
		font.draw(batch, "" + jugador.puntos, 55, 470);



		if (gameover){
			scoreboard.render(batch, font);
		}
		batch.end();

	}
}