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
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGenerator;
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGenerator.FreeTypeFontParameter;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.utils.TimeUtils;
import com.devdbeef.nasc.Starter;
 
public class MenuScreen implements Screen {

public Rectangle square;
public Rectangle triangle;
public Rectangle pentagon;


//

public boolean pentagonBoolean = false;
public boolean squareBoolean = true;
public boolean triangleBoolean = false;
public double timerClick = 0;

public double timerPentagonFull = 0f;
public double timerPentagonZero = 0;

public double timerSquareFull = 0f;
public double timerSquareZero = 0;

public double timerTriangleFull = 0f;
public double timerTriangleZero = 0;

public double timerAlfaPentagon = 0.2f;
public double timerAlfaSquare = 1f;
public double timerAlfaTriangle = 0.2f;
public boolean clickedTim = false;
/*
 * 
 * game.menuSquare.setScale(3.32f);
		game.menuPentagon.setScale(0.23f);
		game.menuTriangle.setScale(0.34f);
		
		game.menuSquare.setAlpha(1);
		game.menuSquare.setAlpha(0.2f);
		game.menuSquare.setAlpha(0.2f);
		
 */
public boolean notHard= false;
public boolean multishape = false;
//
double timerFromGameScreen = 0;
  final Starter game;
  OrthographicCamera camera;
  SpriteBatch batch;
  double timer = 0;
  Rectangle button;
  Rectangle buttonMultishape;
  Rectangle buttonNotHard;
  Rectangle mouse;
  boolean moveScreen = false;
  double timerScreen = 0.9f;
  boolean clicked = false;
 
public MenuScreen (final Starter myGdxGame) {
	
	
	
	square = new Rectangle();
	triangle = new Rectangle();
	pentagon = new Rectangle();
	
	
	square.setSize(100,100);
	triangle.setSize(100,100);
	pentagon.setSize(100,100);

	triangle.setPosition(100,235);
	square.setPosition(220,235);
	pentagon.setPosition(350,235);

	
	game = myGdxGame;

	button = new Rectangle();
	button.setSize(360,150);
	button.setPosition(65,520);

	buttonMultishape = new Rectangle();
	buttonMultishape.setSize(360,120);
	buttonMultishape.setPosition(65,375);
	
	buttonNotHard = new Rectangle();
	buttonNotHard.setSize(360,120);
	buttonNotHard.setPosition(65,225);
	
	
	
	mouse = new Rectangle();
	mouse.setSize(1);
		camera = new OrthographicCamera();
		batch = new SpriteBatch();
		camera.setToOrtho(false, 480, 800);
		batch.setProjectionMatrix(camera.combined);

//		game.menuSquare.setScale(3.32f);
//		game.menuPentagon.setScale(0.23f);
//		game.menuTriangle.setScale(0.34f);
		
		if(game.squareChoosen == true){

pentagonBoolean = false;
squareBoolean = true;
triangleBoolean = false;


 timerPentagonFull = 0f;
  timerPentagonZero = 0;

 timerSquareFull = 0f;
 timerSquareZero = 0;

 timerTriangleFull = 0f;
 timerTriangleZero = 0;

 timerAlfaPentagon = 0.2f;
 timerAlfaSquare = 1f;
 timerAlfaTriangle = 0.2f;

 game.menuPentagon.setScale(0);
 game.menuSquare.setScale(0);
 game.menuTriangle.setScale(0);
//timerPentagonFull = 0f; 
//timerTriangleFull =0f;
//timerSquareFull = 0f;
//	game.firstLaunch = false;
		}
		if(game.triangleChoosen == true){

pentagonBoolean = false;
squareBoolean = false;
triangleBoolean = true;


 timerPentagonFull = 0f;
  timerPentagonZero = 0;

 timerSquareFull = 0f;
 timerSquareZero = 0;

 timerTriangleFull = 0f;
 timerTriangleZero = 0;

 timerAlfaPentagon = 0.2f;
 timerAlfaSquare = 0.2f;
 timerAlfaTriangle = 1f;

 game.menuPentagon.setScale(0);
 game.menuSquare.setScale(0);
 game.menuTriangle.setScale(0);
 
 //timerPentagonFull = 0f; 
//timerTriangleFull =0f;
//timerSquareFull = 0f;
	//	game.firstLaunch = false;
		}		
		if(game.pentagonChoosen == true){

			 game.menuPentagon.setScale(0);
			 game.menuSquare.setScale(0);
			 game.menuTriangle.setScale(0);
pentagonBoolean = true;
squareBoolean = false;
triangleBoolean = false;


 timerPentagonFull = 0f;
  timerPentagonZero = 0;

 timerSquareFull = 0f;
 timerSquareZero = 0;

 timerTriangleFull = 0f;
 timerTriangleZero = 0;

 timerAlfaPentagon = 1f;
 timerAlfaSquare = 0.2f;
 timerAlfaTriangle = 0.2f;

 //timerPentagonFull = 0f; 
//timerTriangleFull =0f;
//timerSquareFull = 0f;
	//	game.firstLaunch = false;
		}
		
		
}
	
	@Override
	public void render(float delta) {	
		Gdx.gl.glClearColor(1, 0.996f, 0.89f, 180);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		
	//  System.out.println(timerPentagonFull + " pentagon");
	//	System.out.println(timerTriangleFull + " triangle");
	//	System.out.println(timerSquareFull + " square");
		if(clickedTim == true)
		timerClick+= Gdx.graphics.getDeltaTime();
		if(timerClick > 0.3f){
			timerClick = 0;
			clickedTim = false;
		}
		
		game.batch.begin();
	if(game.fromGameScreen == false)	game.batch.setColor((float)timer,(float)timer,(float)timer,1);
	else
	{game.batch.setColor(1,1,1,(float)timerFromGameScreen);}
	if(timerFromGameScreen < 1){timerFromGameScreen += Gdx.graphics.getDeltaTime()*3.9f;}
	if(timerFromGameScreen > 1)timerFromGameScreen = 1;
	if(moveScreen == true)
game.batch.setColor((float)timerScreen,(float)timerScreen,(float)timerScreen,(float)timerScreen);

	
	if(timerAlfaPentagon > 0)timerAlfaPentagon -= Gdx.graphics.getDeltaTime()*5;
	if(timerAlfaPentagon < 0)timerAlfaPentagon = 0;
	
	if(timerAlfaSquare > 0)timerAlfaSquare -= Gdx.graphics.getDeltaTime()*5;
	if(timerAlfaSquare < 0)timerAlfaSquare = 0;
	
	if(timerAlfaTriangle > 0)timerAlfaTriangle -= Gdx.graphics.getDeltaTime()*5;
	if(timerAlfaTriangle < 0)timerAlfaTriangle = 0;
	
	game.menuPentagon.setAlpha((float)timerAlfaPentagon);
	game.menuSquare.setAlpha((float)timerAlfaSquare);
	game.menuTriangle.setAlpha((float)timerAlfaTriangle);
	
	
		game.batch.draw(game.menu,0,0);
		//game.batch.draw(game.blackLine,0,225);
		game.batch.setColor(1,1,1,1);
		game.font12.setColor(0, 0, 0, (float)timerScreen);
		game.font52.setColor(0,0,0,(float)timerScreen);
		game.font12.draw(game.batch, "Best:", 170, 518);
		game.font12.draw(game.batch, Integer.toString(game.best), 260, 518);
		game.font52.draw(game.batch, "Turn The Snake", 35, 735);
		game.font12.draw(game.batch, "Select shape", 160, 200);
		game.font12.draw(game.batch, "Best:", 170, 364);
		game.font12.draw(game.batch, Integer.toString(game.bestMulti), 260, 364);
		
		//game.font12.draw(game.batch, "Best:", 170, 214);
	 timerPentagonZero = 0.07f;

	 timerSquareZero = 0.9f;

	 timerTriangleZero = 0.09f;

		
		
//
	
		game.menuSquare.setPosition(230, 253);
		game.menuPentagon.setPosition(105,-139+150);
		game.menuTriangle.setPosition(-15,-12+150);
		game.menuSquare.draw(game.batch);
		game.menuPentagon.draw(game.batch);
		game.menuTriangle.draw(game.batch);
		
		//		
		game.batch.end();
		
		if(timer < 1)timer+= Gdx.graphics.getDeltaTime()*4;
		if(timer > 1)timer =1;

		
		changeScreen();
		checkInput();
		changeShape();
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

public void checkInput(){

if (Gdx.input.isTouched()) {

	Vector3 touchPos = new Vector3();
       touchPos.set(Gdx.input.getX(), Gdx.input.getY(), 0);
      game.camera.unproject(touchPos);

 // 	System.out.println("touched x:" + touchPos.x + "y : " +  touchPos.y );
mouse.setPosition(touchPos.x,touchPos.y);
if(mouse.overlaps(button)){moveScreen = true;
if(clicked == false)game.klik.play();
clicked = true;
}

//if(mouse.overlaps(buttonNotHard)){moveScreen = true;
//if(clicked == false)game.klik.play();
//clicked = true;
//notHard = true;
//}

if(mouse.overlaps(buttonMultishape)){moveScreen = true;
if(clicked == false)game.klik.play();
clicked = true;
multishape = true;
}

if(mouse.overlaps(square)){
squareBoolean = true;
triangleBoolean = false;
pentagonBoolean = false;

game.pentagonChoosen = false;
game.squareChoosen = true;
game.triangleChoosen = false;
if(clickedTim == false){
//game.klik.play();
clickedTim = true;
}
}
if(mouse.overlaps(pentagon)){
	squareBoolean = false;
	triangleBoolean = false;
	pentagonBoolean = true;

	game.pentagonChoosen = true;
	game.squareChoosen = false;
	game.triangleChoosen = false;
	if(clickedTim == false){
		//game.klik.play();
		clickedTim = true;
		}
	}
if(mouse.overlaps(triangle)){
	squareBoolean = false;
	triangleBoolean = true;
	pentagonBoolean = false;

	game.pentagonChoosen = false;
	game.squareChoosen = false;
	game.triangleChoosen = true;
	if(clickedTim == false){
		//game.klik.play();
		clickedTim = true;
		}
}




}


						}
public void changeScreen(){
	if(moveScreen == true){
		timerScreen -= 2.9*Gdx.graphics.getDeltaTime();
	if(timerScreen > 0.9f){timerScreen -= Gdx.graphics.getDeltaTime() * 1.2f * (float)(1 - timerScreen);}
	
	}
	if(timerScreen <= 0){
	
		if(notHard == true){
			game.setScreen(new NotHard(game));
				
		}
		else
		if(multishape == true){
	//		game.adsController.hideBannerAd();
			
			if(squareBoolean == true)
			game.setScreen(new MultishapeSquare(game));
		if(triangleBoolean == true)
			game.setScreen(new MultishapeTriangle(game));
		if(pentagonBoolean == true)
			game.setScreen(new Multishape(game));
	}
		else
	if(multishape == false){
	if(squareBoolean == true)
		game.setScreen(new GameScreenSquare(game));
	if(triangleBoolean == true)
		game.setScreen(new GameScreenTriangle(game));
	if(pentagonBoolean == true)
		game.setScreen(new GameScreen(game));
							}
	
	}
							}

public void changeShape(){
/*
 
public double timerPentagonFull = 0.23f;
public double timerPentagonZero = 0.05;

public double timerSquareFull = 3.32f;
public double timerSquareZero = 0.05;

public double timerTriangleFull = 0.34f;
public double timerTriangleZero = 0.05;

 */
	
	if( pentagonBoolean == true && squareBoolean == false && triangleBoolean == false){
		if(timerPentagonFull < 0.23f)timerPentagonFull += Gdx.graphics.getDeltaTime();
		if(timerPentagonFull > 0.23f)timerPentagonFull = 0.23f;
	
		if(timerSquareFull > timerSquareZero)timerSquareFull -= 10*Gdx.graphics.getDeltaTime();
		if(timerSquareFull < timerSquareZero)timerSquareFull = timerSquareZero;
	
		if(timerTriangleFull > timerTriangleZero)timerTriangleFull -= Gdx.graphics.getDeltaTime();
		if(timerTriangleFull < timerTriangleZero)timerTriangleFull = timerTriangleZero;
		
if(moveScreen == false){
		if(timerAlfaPentagon < 1){
			timerAlfaPentagon += 5*Gdx.graphics.getDeltaTime();
			if(timerAlfaPentagon < 1)timerAlfaPentagon = 1;
			
		}
		
		if(timerAlfaSquare > 0.2f)timerAlfaSquare -= 5*Gdx.graphics.getDeltaTime();
		if(timerAlfaSquare < 0.2f)timerAlfaSquare = 0.2f;
		if(timerAlfaTriangle > 0.2f)timerAlfaTriangle -= 5*Gdx.graphics.getDeltaTime();
		if(timerAlfaTriangle < 0.2f)timerAlfaTriangle  = 0.2f;
		
	
	}
	}
	
		
			

	if( squareBoolean == true && pentagonBoolean == false && triangleBoolean == false){
		if(timerSquareFull < 3.32f)timerSquareFull += 10*Gdx.graphics.getDeltaTime();
		if(timerSquareFull > 3.32f)timerSquareFull = 3.32f;
	
		if(timerPentagonFull > timerPentagonZero)timerPentagonFull -= Gdx.graphics.getDeltaTime();
		if(timerPentagonFull < timerPentagonZero)timerPentagonFull = timerPentagonZero;
	
		if(timerTriangleFull > timerTriangleZero)timerTriangleFull -= Gdx.graphics.getDeltaTime();
		if(timerTriangleFull < timerTriangleZero)timerTriangleFull = timerTriangleZero;
	
		if(moveScreen == false){
		if(timerAlfaSquare < 1){
			timerAlfaSquare += 5*Gdx.graphics.getDeltaTime();
			if(timerAlfaSquare < 1)timerAlfaSquare = 1;
		}

		if(timerAlfaPentagon > 0.2f)timerAlfaPentagon -= 5*Gdx.graphics.getDeltaTime();
		if(timerAlfaPentagon < 0.2f)timerAlfaPentagon   = 0.2f;
		if(timerAlfaTriangle > 0.2f)timerAlfaTriangle -= 5*Gdx.graphics.getDeltaTime();
		if(timerAlfaTriangle < 0.2f)timerAlfaTriangle   = 0.2f;
		}

	}
	if( triangleBoolean == true && squareBoolean == false && pentagonBoolean == false){
		if(timerTriangleFull < 0.34f)timerTriangleFull += Gdx.graphics.getDeltaTime();
		if(timerTriangleFull > 0.34f)timerTriangleFull = 0.34f;
	
		if(timerSquareFull > timerSquareZero)timerSquareFull -= 10*Gdx.graphics.getDeltaTime();
		if(timerSquareFull < timerSquareZero)timerSquareFull = timerSquareZero;
	
		if(timerPentagonFull > timerPentagonZero)timerPentagonFull -= Gdx.graphics.getDeltaTime();
		if(timerPentagonFull < timerPentagonZero)timerPentagonFull = timerPentagonZero;
		
		if(moveScreen == false){
		if(timerAlfaTriangle < 1){
		timerAlfaTriangle += 5*Gdx.graphics.getDeltaTime();
		if(timerAlfaTriangle < 1)timerAlfaTriangle = 1;
	}

		if(timerAlfaSquare > 0.2f)timerAlfaSquare -= 5*Gdx.graphics.getDeltaTime();
		if(timerAlfaSquare < 0.2f)timerAlfaSquare   = 0.2f;
		if(timerAlfaPentagon > 0.2f)timerAlfaPentagon -= 5*Gdx.graphics.getDeltaTime();
		if(timerAlfaPentagon < 0.2f)timerAlfaPentagon   = 0.2f;
	
		
	}
	}
	
game.menuPentagon.setScale((float)timerPentagonFull);
game.menuTriangle.setScale((float)timerTriangleFull);
game.menuSquare.setScale((float)timerSquareFull);

game.menuPentagon.setAlpha((float)timerAlfaPentagon);
game.menuTriangle.setAlpha((float)timerAlfaTriangle);
game.menuSquare.setAlpha((float)timerAlfaSquare);



}


}