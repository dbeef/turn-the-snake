package com.devdbeef.nasc;

import java.util.Iterator;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.FPSLogger;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.Texture.TextureFilter;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.utils.TimeUtils;
import com.devdbeef.nasc.Starter;
 
public class LogoScreen implements Screen {

  final Starter game;
  double timerEnd = 0;
  boolean timer = false;
  boolean pause = false;
  double timerPause = 0;
  
public LogoScreen(final Starter myGdxGame) {
	
		game = myGdxGame;
		game.camera = new OrthographicCamera();
		game.batch = new SpriteBatch();
		game.camera.setToOrtho(false, 480, 800);
		game.batch.setProjectionMatrix(game.camera.combined);
}
	
	@Override
	public void render(float delta) {
		Gdx.gl.glClearColor(1, 0.996f, 0.89f, 180);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		game.batch.begin();
		game.batch.setColor((float)timerEnd,(float)timerEnd,(float)timerEnd,1);
		game.batch.draw(game.logo,0,0);
		game.batch.setColor(1,1,1,1);
		game.batch.end();
		
		if(timerEnd < 1 && timer ==false)timerEnd += Gdx.graphics.getDeltaTime()*3;
		if(timerEnd > 1 && timer == false){pause = true;timerEnd = 1;}
		if(pause == true){
			timerPause += Gdx.graphics.getDeltaTime();
			if(timerPause >=2.5f)timer = true;
		}
		if(timerEnd > 0 && timer == true)timerEnd -= Gdx.graphics.getDeltaTime()*3f;	
		if(timerEnd <= 0 && timer == true){game.setScreen(new MenuScreen(game));}
	}

	
	@Override
	public void resize(int width, int height) {
	}
 
	@Override
	public void show() {
	}
	
 
	@Override
	public void hide() {
	}
 
	@Override
	public void pause() {
	}
 
	@Override
	public void resume() {
	}
 
	@Override
	public void dispose() {
	}

//	void panieMietkuLadujPanTekstury(){
//		}

}

