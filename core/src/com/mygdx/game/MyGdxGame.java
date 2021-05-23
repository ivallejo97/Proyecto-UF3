package com.mygdx.game;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Files.FileType;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.audio.Sound;
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
	List<Meteorito> meteoritos;
	List<Disparo> disparosAEliminar;
	List<Enemigo> enemigosAEliminar;
	List<Enemigo2> enemigosAEliminar2;
	List<Meteorito> meteoritosAEliminar;
	Temporizador temporizadorNuevoEnemigo;
	Temporizador temporizadorNuevoEnemigo2;
	Temporizador temporizadorNuevoMeteorito;
	ScoreBoard scoreboard;
	boolean gameover;
	Sound sound[] = new Sound[3];
	Music music;
	float volume = 0.5f;


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
		meteoritos = new ArrayList<>();
		temporizadorNuevoEnemigo = new Temporizador(120);
		temporizadorNuevoEnemigo2 = new Temporizador(240);
		temporizadorNuevoMeteorito = new Temporizador(240);
		disparosAEliminar = new ArrayList<>();
		enemigosAEliminar = new ArrayList<>();
		enemigosAEliminar2 = new ArrayList<>();
		meteoritosAEliminar = new ArrayList<>();
		scoreboard = new ScoreBoard();
		sound[0] = Gdx.audio.newSound( Gdx.files.getFileHandle("boo.mp3", FileType.Internal) );
		sound[0].setVolume(1,volume);
		sound[1] = Gdx.audio.newSound( Gdx.files.getFileHandle("kingboo.mp3", FileType.Internal) );
		sound[1].setVolume(1,volume);
		sound[2] = Gdx.audio.newSound( Gdx.files.getFileHandle("meteorito.mp3", FileType.Internal) );
		sound[2].setVolume(1,volume);
		music = Gdx.audio.newMusic(Gdx.files.getFileHandle("cancion_fondo.mp3", FileType.Internal));
		music.setVolume(volume);
		music.play();
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
						sound[0].play();
						break;
					}
				}

				if (!gameover && !jugador.muerto && Utils.solapan(enemigo.x, enemigo.y, enemigo.w, enemigo.h, jugador.x, jugador.y, jugador.w, jugador.h)) {
					jugador.morir();
					enemigosAEliminar.add(enemigo);
					sound[0].play();
					if (jugador.vidas == 0){
						gameover = true;
					}
				}
				if (jugador.puntos >= 20){
					temporizadorNuevoEnemigo.frecuencia = 80;
				}
				if (jugador.puntos >= 60){
					temporizadorNuevoEnemigo.frecuencia = 40;
				}
				if (jugador.puntos >= 90){
					temporizadorNuevoEnemigo.frecuencia = 30;
				}
				if (enemigo.y < -enemigo.w)enemigosAEliminar.add(enemigo);
			}



		if (jugador.puntos >= 30){

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
							sound[1].play();
							break;
						}
					}
				}

				if (!gameover && !jugador.muerto && Utils.solapan(enemigo2.x, enemigo2.y, enemigo2.w, enemigo2.h, jugador.x, jugador.y, jugador.w, jugador.h)) {
					jugador.morir();
					enemigo2.morir();
					if (enemigo2.vidas == 0){
						enemigosAEliminar2.add(enemigo2);
						sound[1].play();
					}
					if (jugador.vidas == 0){
						gameover = true;
					}
				}
				if (jugador.puntos >= 50){
					temporizadorNuevoEnemigo.frecuencia = 120;
				}
				if (jugador.puntos >= 80){
					temporizadorNuevoEnemigo.frecuencia = 80;
				}
				if (jugador.puntos >= 120){
					temporizadorNuevoEnemigo.frecuencia = 60;
				}
				if (enemigo2.y < -enemigo2.w) enemigosAEliminar2.add(enemigo2);
			}
		}

		if (jugador.puntos >=2){
			if (temporizadorNuevoMeteorito.suena()){
				meteoritos.add(new Meteorito());
				sound[2].play();
			}

			for (Meteorito meteorito : meteoritos) meteorito.update();              // enemigos.forEach(Enemigo::update);

			for (Meteorito meteorito : meteoritos) {
				for (Disparo disparo : jugador.disparos) {
					if (Utils.solapan(disparo.x, disparo.y, disparo.w, disparo.h, meteorito.x, meteorito.y, meteorito.w, meteorito.h)) {
						disparosAEliminar.add(disparo);
						meteoritosAEliminar.add(meteorito);
						break;
					}
				}

				if (!gameover && !jugador.muerto && Utils.solapan(meteorito.x, meteorito.y, meteorito.w, meteorito.h, jugador.x, jugador.y, jugador.w, jugador.h)) {
					jugador.morir();
					meteoritosAEliminar.add(meteorito);
					if (jugador.vidas == 0){
						gameover = true;
					}
				}
				if (jugador.puntos >= 35){
					temporizadorNuevoEnemigo.frecuencia = 120;
				}
				if (jugador.puntos >= 90){
					temporizadorNuevoEnemigo.frecuencia = 60;
				}
				if (jugador.puntos >= 130){
					temporizadorNuevoEnemigo.frecuencia = 40;
				}
				if (meteorito.y < -meteorito.w)meteoritosAEliminar.add(meteorito);
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
		if (jugador.puntos>=2){
			for (Meteorito meteorito : meteoritosAEliminar) meteoritos.remove(meteorito);
			meteoritosAEliminar.clear();
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
		if (jugador.puntos>=2){
			for (Meteorito meteorito : meteoritos) meteorito.render(batch);
		}
		font.draw(batch, "" + jugador.vidas, 55, 430);
		font.draw(batch, "" + jugador.puntos, 55, 470);


		if (gameover){
			scoreboard.render(batch, font);
		}
		batch.end();

	}
}