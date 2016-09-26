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
import com.badlogic.gdx.math.Matrix4;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.TimeUtils;
import com.devdbeef.nasc.Starter;
 
public class GameScreenSquare implements Screen {


	  public OrthographicCamera camera;
	  public SpriteBatch batch;

	
public boolean lost = false;
public double lostTimer = 0;
public double timerOver = 0;
public boolean kolizja = false;
public double timerSnakeHead = 0;
public boolean clicked = false;

public double timerOut = 0;
boolean tryAgainBoolean = false;
boolean backHomeBoolean = false;

public double scaleApple = 0;
public boolean czyZmniejszacApple = false;

public double ogniwa = 2;
public double timerScore = 0;
public double timerMove = 0;
public int score = 0;
public double actualRed = 1;
public double actualGreen = 0.996f;
public double actualBlue = 0.87f; 
	
public double red[] = new double[5];
public double green[] = new double[5];
public double blue[] = new double[5];
	
public int backgroundColour = -1;
public int figureColour = 0;
public boolean uploaded = false;
public Rectangle snakeHead = new Rectangle();
public Rectangle snakeBodyRectangle = new Rectangle();
public Rectangle apple = new Rectangle();

public Rectangle tryAgain = new Rectangle();
public Rectangle backHome = new Rectangle();
public Rectangle mouse = new Rectangle();

public Rectangle[] rects0= new Rectangle[400];
public Rectangle[] rects1= new Rectangle[400];
public Rectangle[] rects2= new Rectangle[400];
public Rectangle[] rects3= new Rectangle[400];

	
	boolean dontZoom = false;
	boolean clickedBefore = false;
	boolean updateCam = false;
	double positionCam = 400;
	double zoomCam = 1;
	double buforCam = 0;
	double timerBufor = 0;
	int way = 0;
	double waysX[] = new double[4];
	double waysY[] = new double[4];
	double positions[] = new double[4];
	double degrees[] = new double[4];
	
	public Array snakePositionsX = new Array();
	public Array snakePositionsY = new Array();
	public Array snakeDegrees = new Array();
	public Array snakeVisibility = new Array();
	
	double timerPentagon = 0;
	
	double snakeX = 240;
	double snakeY = 500;
	Iterator iterX;
	Iterator iterY;
	Iterator iterDegrees;
	Iterator iterVisibility;
	final Starter game;

  double timerSnake = 0;
  
public GameScreenSquare(final Starter myGdxGame) {
	
		game = myGdxGame;

		red[0] = 2/255f;        //blekitny
		green[0] = 238/255f;
		blue[0] = 251/255f;

		red[1] = 251/255f;      //czerwony
		green[1] = 15/255f;
		blue[1] = 2/255f;
		
		red[2] = 84/255f;       //zielony
		green[2] = 210/255f;
		blue[2] = 28/255f;
		
		red[3] = 154/255f;             //fioletowy
		green[3] = 28/255f;
		blue[3] = 210/255f;
		
		red[4] = 200/255f;      //pomarancz
		green[4] = 146/255f;
		blue[4] = 1/255f;
		
		
		waysX[0] = 1;
		waysX[1] = 0;
		waysX[2] = -1;
		waysX[3] = 0f;


		waysY[0] = 0f;
		waysY[1] = -1;
		waysY[2] = 0;
		waysY[3] = 1;

		
		degrees[0] = 0;
		degrees[1] = 90;
		degrees[2] = 180;
		degrees[3] = 270;

		
		positions[0] = 400;
		positions[1] = 440;
		positions[2] = 440;
		positions[3] = 440;
		
		game.camera = new OrthographicCamera();
		game.batch = new SpriteBatch();
		game.camera.setToOrtho(false, 480, 800);
		game.batch.setProjectionMatrix(game.camera.combined);

		camera = new OrthographicCamera();
		batch = new SpriteBatch();
		camera.setToOrtho(false, 480, 800);

		game.pentagonChoosen = false;
		game.squareChoosen = true;
		game.triangleChoosen = false;
		
		spawnFigureRectangles();
		snakeHead.setSize(8);
		snakeBodyRectangle.setSize(3);
		apple.setSize(16);

	tryAgain.setSize(363,65);
	backHome.setSize(363,65);
	
	tryAgain.setPosition(60,245);
	backHome.setPosition(60,135);
	
	mouse.setSize(1);	
game.candy.setAlpha(1);
game.candy.setScale(1);
spawnApple();
}
	
	@Override
	public void render(float delta) {
		game.batch.setProjectionMatrix(game.camera.combined);
	
		Gdx.gl.glClearColor((float)actualRed, (float)actualGreen, (float)actualBlue, 180);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
	
		game.batch.begin();
		game.batch.setColor(1,1,1,(float)timerPentagon);
		game.batch.draw(game.yellowSquare,0,200);
	//	game.yellowSquareSprite.setAlpha((float)timerPentagon);
	//	game.yellowSquareSprite.draw(game.batch);
		game.batch.setColor(1,1,1,1);
		drawSnake();
		regulateColours();
		//drawFigureRectangles();
		drawApple();
	
		Matrix4 uiMatrix = camera.combined.cpy();
		uiMatrix.setToOrtho2D(0, 0, 480	, 800);
		game.batch.setProjectionMatrix(uiMatrix);

		game.font52.setColor(0, 0, 0, (float)timerScore);
		
		if(lost == true){
			if(timerScore > 0)timerScore -= Gdx.graphics.getDeltaTime()*3;
			if(timerScore <0)timerScore =0;
		}
		else
		{	if(timerScore < 1)timerScore += Gdx.graphics.getDeltaTime()*3;
		if(timerScore > 1)timerScore = 1;
		}

		if(score <= 9)
		game.font52.draw(game.batch, Integer.toString(score), 223, 750);
		if(score > 9 && score < 99)
		game.font52.draw(game.batch, Integer.toString(score), 208, 750);
		if(score >= 99)
		game.font52.draw(game.batch, Integer.toString(score), 193, 750);
			
		
		game.batch.end();
		
		
		drawOverScreen();

		checkInput();
		
		if(lost == false){
	checkFigureCollisions();
		spawnSnake();
		updateCamera();
		if(timerPentagon < 1)timerPentagon += Gdx.graphics.getDeltaTime() * 3;
		if(timerPentagon > 1)timerPentagon =1;
		
}
else
{
	if(lostTimer < 0.85f)lostTimer += Gdx.graphics.getDeltaTime();
	if(lostTimer > 0.85f){
	
	if(timerPentagon > 0)timerPentagon -= Gdx.graphics.getDeltaTime()*2;
	if(timerPentagon < 0)timerPentagon =0;
	backgroundColour = -1;
						}
	if(timerPentagon == 0 && timerOver < 0.9f){
		timerOver += Gdx.graphics.getDeltaTime()*3;
	if(timerOver > 0.8f )timerOver -= Gdx.graphics.getDeltaTime() * 1.1f * (float)(1 - timerOver);
		if(timerOver > 0.9f)timerOver = 0.9f;
	}
	goOut();
	
	if(uploaded == false){
		if(score > game.best){
		game.prefs.putInteger("best", score);
		game.best = score;
		game.prefs.flush();
		}
		uploaded = true;
	}
	
}
		//System.out.println("positionCam " + positionCam);
		//System.out.println("faktyczna pozycja " +game.camera.position);

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

void spawnSnake(){
	timerMove += Gdx.graphics.getDeltaTime();
	timerSnake += Gdx.graphics.getDeltaTime();
	if(timerSnake > 0.08f-(float)((ogniwa - 2)/75)){

		timerSnake = 0;
		snakePositionsX.add(snakeX);
		snakePositionsY.add(snakeY);		
		snakeDegrees.add(way);
		snakeVisibility.add(1f);
	}

	if(timerMove > 0.0000005f){
	snakeX = (float) snakeX + waysX[way]*(float)(ogniwa - 0.3f);
	snakeY = (float) snakeY + waysY[way]*(float)(ogniwa - 0.3f);
	
	timerMove =0;
	}
	
}
void drawSnake(){
	
	if(timerSnakeHead < 1)timerSnakeHead += Gdx.graphics.getDeltaTime()*3;
	if(timerSnakeHead > 1)timerSnakeHead = 1;
	
	
	double x= 0;
	double y= 0;
	iterX = snakePositionsX.iterator();
	iterY = snakePositionsY.iterator();
	iterDegrees = snakeDegrees.iterator();
	iterVisibility = snakeVisibility.iterator();
	while (iterX.hasNext() && iterY.hasNext() && iterDegrees.hasNext() && iterVisibility.hasNext()){
		x = (Double)  iterX.next();
		y = (Double) iterY.next();
		float visibility =  (Float) iterVisibility.next();
		int degrees1 =  (Integer) iterDegrees.next();

		
game.spritePentagon.setAlpha(visibility);
game.spritePentagon.setRotation((float) degrees[(int)degrees1]);
game.spritePentagon.setPosition((int)x,(int) y);
game.spritePentagon.draw(game.batch);
if(lost == true && visibility > 0)visibility -= Gdx.graphics.getDeltaTime()*2;
if(visibility > 0)visibility -= Gdx.graphics.getDeltaTime()*1/(ogniwa*(0.1f * ogniwa));
visibility -= 0.01f;
if(visibility < 0){
iterX.remove();
iterY.remove();
iterVisibility.remove();
iterDegrees.remove();
}
else
{
	iterVisibility.remove();
	snakeVisibility.add(visibility);
	}

snakeBodyRectangle.setPosition((float)x, (float)y);
snakeHead.setPosition((float)snakeX, (float)snakeY);

if(snakeHead.overlaps(snakeBodyRectangle) && visibility < 0.1f){
//	System.out.println("kolizja" + visibility);
lost = true;
if(kolizja == false)game.przegrana.play();
kolizja = true;

}
	}

	if(lost == true)
game.spritePentagon.setAlpha((float)timerPentagon);
	else
game.spritePentagon.setAlpha((float)timerSnakeHead);

game.spritePentagon.setRotation((float) degrees[way]);
game.spritePentagon.setPosition((float)snakeX,(float) snakeY);
game.spritePentagon.draw(game.batch);

}

public void checkInput(){


	
if (Gdx.input.isTouched()) {
	Vector3 touchPos = new Vector3();
       touchPos.set(Gdx.input.getX(), Gdx.input.getY(), 0);
      game.camera.unproject(touchPos);
mouse.setPosition(touchPos.x, touchPos.y);
if(timerOver >= 0.9f){
	//System.out.println(timerOver);
	
	if(mouse.overlaps(tryAgain)){
		tryAgainBoolean = true;
	
if(clicked== false)		game.klik.play();
clicked = true;	
	//System.out.println("tryAgain");
	}
	if(mouse.overlaps(backHome)){
		backHomeBoolean = true;

if(clicked == false)game.klik.play();
clicked = true;
		//System.out.println("backHome");
	}	
}
      
if(clickedBefore == false){clickedBefore = true;}


}
else{
	if(clickedBefore == true && updateCam == false){
		if(way < 3)way ++;
		else if(way == 3)way = 0;
	clickedBefore = false;
	updateCam = true;
	buforCam = 90f;
//New colour - randomizer 
if(lost == false)game.obrot.play();
if(lost == false)
backgroundColour += 1;
if(backgroundColour > 4)backgroundColour = 0;


figureColour = MathUtils.random(0,4);


//System.out.println(way);
	}
}

							}

public void updateCamera(){
	if(updateCam == true){
		
		if(zoomCam < 1.5f && dontZoom == false)zoomCam += Gdx.graphics.getDeltaTime()*4;
		if(zoomCam >= 1.5f)dontZoom = true;
		if(zoomCam >1 && dontZoom == true)zoomCam -= Gdx.graphics.getDeltaTime()*4;
		
		timerBufor += Gdx.graphics.getDeltaTime();
		if(timerBufor > 0.0000001f){
	if(buforCam > 0)buforCam -= 5f;
	if(buforCam <= 0){buforCam = 0;
	updateCam = false;
	timerBufor = 0;	
	zoomCam = 1;
	dontZoom = false;
	}
	
		timerBufor = 0;
		
	game.camera.zoom = (float)zoomCam;
	game.camera.rotate(5.0f);

	
	if(positionCam < positions[way]){
	positionCam += 2.5f;
	}
	if(positionCam > positions[way]){
	positionCam -= 2.5f;
	}
	game.camera.position.y = (int)positionCam;
		}
	game.camera.update();
	}
}

public void spawnFigureRectangles(){

//	waysX[0] = 1;
//	waysX[1] = -0.32f;
//	waysX[2] = -1;
//	waysX[3] = -0.32f;
//	waysX[4] = 1;
	
//	waysY[0] = -0.725f;
//	waysY[1] = -1;
//	waysY[2] = 0;
//	waysY[3] = 1;
//	waysY[4] = 0.725;
	
	float lastX = 20; // done
	float lastY = 240; //
	for(int a = 0;a<400;a++){
		
		rects0[a] = new Rectangle();
		rects0[a].setSize(2);
		rects0[a].setPosition((float)(waysX[3] + lastX),(float)(waysY[3] + lastY ));
	lastX += waysX[3];
	lastY += waysY[3];
	}
	
	lastX = 25; // done
	lastY = 631; // 
	for(int a = 0;a<400;a++){
		
		rects1[a] = new Rectangle();
		rects1[a].setSize(2);
		rects1[a].setPosition((float)(waysX[0] + lastX),(float)(waysY[0] + lastY));
	lastX += waysX[0] ;
	lastY += waysY[0] ;
	}
	
	lastX = 442; // done
	lastY = 635; // 
	for(int a = 0;a<400;a++){
		
		rects2[a] = new Rectangle();
		rects2[a].setSize(2);
		rects2[a].setPosition((float)(waysX[1] + lastX),(float)(waysY[1] + lastY));
	lastX += waysX[1];
	lastY += waysY[1];
	}

	lastX = 450; // done
	lastY = 226; //
	for(int a = 0;a<400;a++){
		
		rects3[a] = new Rectangle();
		rects3[a].setSize(2);
		rects3[a].setPosition((float)(waysX[2] + lastX),(float)(waysY[2] + lastY));
	lastX += waysX[2];
	lastY += waysY[2];
	}



}
public void drawFigureRectangles(){
	for(int a =0; a<400;a++){
		game.batch.draw(game.snakeShape,rects0[a].getX(),rects0[a].getY());
	}
	for(int a =0; a<400;a++){
		game.batch.draw(game.snakeShape,rects1[a].getX(),rects1[a].getY());
	}
	for(int a =0; a<400;a++){
		game.batch.draw(game.snakeShape,rects2[a].getX(),rects2[a].getY());
	}
	for(int a =0; a<400;a++){
		game.batch.draw(game.snakeShape,rects3[a].getX(),rects3[a].getY());
	}


}
public void checkFigureCollisions(){
snakeHead.setPosition((float)snakeX, (float)snakeY);
for(int a =0;a<300;a++){
	if(rects0[a].overlaps(snakeHead)){lost = true;if(kolizja == false)game.przegrana.play();
	kolizja = true;}
	if(rects1[a].overlaps(snakeHead)){lost = true;if(kolizja == false)game.przegrana.play();
	kolizja = true;}
	if(rects2[a].overlaps(snakeHead)){lost = true;if(kolizja == false)game.przegrana.play();
	kolizja = true;}
	if(rects3[a].overlaps(snakeHead)){lost = true;if(kolizja == false)game.przegrana.play();
	kolizja = true;}


	if(way > 3)way = 0;

if(snakeHead.overlaps(apple)){
//Score!
//	spawnApple();
	apple.setPosition(0,0);	
score ++;
	ogniwa += 0.1f;
	czyZmniejszacApple = true;
	game.punkt.play();
							}

}

						}
public void spawnApple(){
int random = MathUtils.random(1, 7);
	int x= MathUtils.random(120,350);
	int y= MathUtils.random(250,500);
	apple.setPosition(x - 3,y - 3);	
	game.candy.setPosition(x, y);
czyZmniejszacApple = false;
}

public void drawApple(){
if(czyZmniejszacApple == true){
	if(scaleApple > 0)scaleApple -= Gdx.graphics.getDeltaTime()*3;
}
if(czyZmniejszacApple == false){
	if(scaleApple < 1)scaleApple += Gdx.graphics.getDeltaTime()*3;
}
if(scaleApple <= 0){scaleApple =0 ; spawnApple();}
if(scaleApple >= 1){scaleApple =1 ;}
game.candy.setScale((float)scaleApple);
if(lost == true){game.candy.setAlpha((float)timerPentagon);}
game.candy.draw(game.batch);
}
public void regulateColours(){
	if(backgroundColour != -1){
if(actualRed < red[backgroundColour]){actualRed += Gdx.graphics.getDeltaTime()*3;}
if(actualRed > red[backgroundColour]){actualRed -= Gdx.graphics.getDeltaTime()*3;}
if(actualGreen < green[backgroundColour]){actualGreen += Gdx.graphics.getDeltaTime()*3;}
if(actualGreen > green[backgroundColour]){actualGreen -= Gdx.graphics.getDeltaTime()*3;}
if(actualBlue < blue[backgroundColour]){actualBlue += Gdx.graphics.getDeltaTime()*3;}
if(actualBlue > blue[backgroundColour]){actualBlue -= Gdx.graphics.getDeltaTime()*3;}
	}
	else
	{
		if(actualRed < 1){actualRed += Gdx.graphics.getDeltaTime()*3;}
		if(actualRed > 1){actualRed -= Gdx.graphics.getDeltaTime()*3;}
		if(actualGreen < 0.996f){actualGreen += Gdx.graphics.getDeltaTime()*3;}
		if(actualGreen > 0.996f){actualGreen -= Gdx.graphics.getDeltaTime()*3;}
		if(actualBlue < 0.87f){actualBlue += Gdx.graphics.getDeltaTime()*3;}
		if(actualBlue > 0.87f){actualBlue -= Gdx.graphics.getDeltaTime()*3;}		

		if(actualRed > 0.95f && actualRed < 1)actualRed = 1;
		if(actualGreen > 0.91f && actualGreen < 0.996f)actualGreen = 0.996f;
		if(actualBlue > 0.82f && actualBlue < 0.87f)actualBlue = 0.87f;
		
	}
}
public void drawOverScreen(){
	if(timerPentagon == 0){
		game.camera = new OrthographicCamera();
		game.batch = new SpriteBatch();
		game.camera.setToOrtho(false, 480, 800);
		game.batch.setProjectionMatrix(game.camera.combined);
game.batch.begin();
if(timerOut == 0){
game.batch.setColor(1,1,1,(float)timerOver);
//game.batch.draw(game.gameOver,60,690);
game.batch.draw(game.blackRectangle,115,345);
game.batch.draw(game.tryAgain,130,245);
game.batch.draw(game.tryAgain,130,135);
game.batch.setColor(1,1,1,1);
game.font12.setColor(1, 1, 1, (float)timerOver);
game.font52.setColor(0, 0, 0, (float)timerOver);
game.font12.draw(game.batch, "Score:", 200, 625);
game.font12.draw(game.batch, "Best:", 205, 505);
game.font52.draw(game.batch, "Game Over!", 75, 760);
game.font52.setColor(1,1,1,(float)timerOver);

if(score >= 100)
game.font52.draw(game.batch, Integer.toString(score), 188, 575);
if(game.best >= 100)
game.font52.draw(game.batch, Integer.toString(game.best), 188, 450);

if(score >= 0 && score <= 9)
	game.font52.draw(game.batch, Integer.toString(score), 223, 575);
	if(game.best >= 0 && game.best <= 9)
	game.font52.draw(game.batch, Integer.toString(game.best), 223, 450);
	

	if(score >= 10 && score <= 99)
		game.font52.draw(game.batch, Integer.toString(score), 208, 575);
		if(game.best >= 10 && game.best <= 99)
		game.font52.draw(game.batch, Integer.toString(game.best), 208, 450);
		

game.font12.draw(game.batch, "TRY AGAIN", 165, 286);
game.font12.draw(game.batch, "BACK HOME", 160, 176);

}
else
{
	game.batch.setColor(1,1,1,(float)0.9f - (float)timerOut);
//	game.batch.draw(game.gameOver,60,690);
	game.batch.draw(game.blackRectangle,115,345);
	game.batch.draw(game.tryAgain,130,245);
	game.batch.draw(game.tryAgain,130,135);
	game.batch.setColor(1,1,1,1);
	game.font12.setColor(1, 1, 1, (float)1 - (float)timerOut);
	game.font52.setColor(0, 0, 0, (float)1 - (float)timerOut);
	game.font12.draw(game.batch, "Score:", 200, 625);
	game.font12.draw(game.batch, "Best:", 205, 505);
	game.font52.draw(game.batch, "Game Over!", 75, 760);
	game.font52.setColor(1,1,1,(float)1 - (float)timerOut);
	
	if(score >= 100)
	game.font52.draw(game.batch, Integer.toString(score), 188, 575);
	if(game.best >= 100)
	game.font52.draw(game.batch, Integer.toString(game.best), 188, 450);

	if(score >= 0 && score <= 9)
	game.font52.draw(game.batch, Integer.toString(score), 223, 575);
	if(game.best >= 0 && game.best <= 9)
	game.font52.draw(game.batch, Integer.toString(game.best), 223, 450);
	
	if(score >= 10 && score <= 99)
		game.font52.draw(game.batch, Integer.toString(score), 208, 575);
		if(game.best >= 10 && game.best <= 99)
		game.font52.draw(game.batch, Integer.toString(game.best), 208, 450);
		
	
	game.font12.draw(game.batch, "TRY AGAIN", 165, 286);
	game.font12.draw(game.batch, "BACK HOME", 160, 176);

}
game.batch.end();
							}
}
public void goOut(){
	if(tryAgainBoolean == true || backHomeBoolean == true && timerOut < 1){timerOut += Gdx.graphics.getDeltaTime()*3.2f;}
	if(timerOut > 0.9f && tryAgainBoolean == true || backHomeBoolean == true)timerOut -= (float)(1-timerOut)*Gdx.graphics.getDeltaTime()*1.1f;
	if(timerOut > 0.9f)timerOut = 0.9f;
	
	if(tryAgainBoolean == true && timerOut == 0.9f){
		
		if(game.pentagonChoosen ==true)
		game.setScreen(new GameScreen(game));
		if(game.triangleChoosen ==true)
		game.setScreen(new GameScreenTriangle(game));
		if(game.squareChoosen ==true)
		game.setScreen(new GameScreenSquare(game));
		
	}
	if(backHomeBoolean == true && timerOut == 0.9f){
		game.fromGameScreen = true;
		game.menuSquare.setScale(0);
		game.menuTriangle.setScale(0);
		game.menuPentagon.setScale(0);
		
		game.menuSquare.setAlpha(1);
		game.menuTriangle.setAlpha(0.2f);
		game.menuPentagon.setAlpha(0.2f);
		
		
		game.setScreen(new MenuScreen(game));
	}
	
}
//END
}
