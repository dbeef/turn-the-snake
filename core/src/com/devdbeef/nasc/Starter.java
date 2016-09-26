package com.devdbeef.nasc;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Preferences;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.Texture.TextureFilter;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.ParticleEffect;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGenerator;
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGenerator.FreeTypeFontParameter;
import com.badlogic.gdx.graphics.glutils.FrameBuffer;
//import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGenerator;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.World;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.TimeUtils;


public class Starter extends Game  {

	public Preferences prefs;

	public AdsController adsController;
	
	  public Starter(AdsController adsController){
	        this.adsController = adsController;
	    }

	  public int best = 0;
	  public int bestMulti = 0;
	  
	  
	public boolean shown = false;
	//Starter objects declarations from here -
	
	public boolean pentagonChoosen = false;
	public boolean triangleChoosen = false;
	public boolean squareChoosen = true;
	
	public Texture yellowSquare;
	public Sprite yellowSquareSprite;
	public Texture blackLine;
	
	  public OrthographicCamera camera;
	  
	 public SpriteBatch batch;
	public boolean fromGameScreen = false;
	public Sprite candy;
	public Sprite menuSquare;
	public Sprite menuPentagon;
	public Sprite spritePentagon;
	public Texture yellowRectangle;
	public Sprite yellowRectangleSprite;
	public Sprite menuTriangle;
	public Texture menuTriangle2;
	public Texture yellowTriangle;
	public boolean firstLaunch = true;
	
	public Texture snakeShape;
	public Texture logo;
	public Texture menu;
	public ParticleEffect efektPentagon;
	public Sprite multiPentagon;
	public Texture yellowPentagon2;
	public Texture yellowPentagon;
	public Texture gameOver;
	public Texture backHome;
	public Texture tryAgain;
	public Texture blackRectangle;
	public Texture yellowSquare2;
	
	public BitmapFont font12;
	public BitmapFont font52;
	
	// - till here
	
public Sound punkt;
public 	Sound obrot;
public 	Sound przegrana;
public 	Sound klik;

public FrameBuffer fbo;


public FrameBuffer fbo2;

public Sprite sps;
public Sprite sps2;
	
public Texture menuPentagon2;

	public void create() {
	
	//if(shown == false)adsController.showBannerAd();
		

prefs  = Gdx.app.getPreferences("My Preferences");

best = prefs.getInteger("best");
bestMulti = prefs.getInteger("bestMulti");

		punkt = Gdx.audio.newSound(Gdx.files.internal("punkt.mp3"));
		obrot = Gdx.audio.newSound(Gdx.files.internal("obrot.mp3"));
		przegrana = Gdx.audio.newSound(Gdx.files.internal("przegrana.mp3"));
		klik = Gdx.audio.newSound(Gdx.files.internal("klik.mp3"));
		
		yellowSquare2 = new Texture("yellowSquare2.png");
	//	yellowSquare2.setFilter(TextureFilter.Linear, TextureFilter.Linear);
		
		yellowPentagon = new Texture("yellowPentagon.png");
		multiPentagon = new Sprite(yellowPentagon);
		multiPentagon.setScale(0.6f);
				logo = new Texture("logo.png");
		tryAgain = new Texture("tryAgain.png");
		backHome = new Texture("backHome.png");
		menu = new Texture("menu.png");
		blackRectangle = new Texture("blackRectangle.png");
		gameOver = new Texture("gameOver.png");
		snakeShape = new Texture("snakeShape.png");
		yellowPentagon2 = new Texture("yellowPentagon2.png");
		yellowPentagon2.setFilter(TextureFilter.Linear, TextureFilter.Linear);
		blackLine = new Texture("blackLine.png");
		yellowSquare = new Texture("yellowSquare.png");
		yellowSquare.setFilter(TextureFilter.Linear, TextureFilter.Linear);
		yellowSquareSprite = new Sprite(yellowSquare);
		
	//	yellowSquareSprite.setPosition(0, 200);
	//	yellowSquareSprite.setScale(0.95f);
		
		menuPentagon2 = new Texture("menuPentagon.png");
		menuPentagon2.setFilter(TextureFilter.Linear, TextureFilter.Linear);

		efektPentagon = new ParticleEffect();
		
		FileHandle from = Gdx.files.internal("efektPentagon");
		efektPentagon.loadEmitters(from);
from = Gdx.files.internal("");
efektPentagon.loadEmitterImages(from);

		yellowTriangle = new Texture("yellowTriangle.png");
		yellowTriangle.setFilter(TextureFilter.Linear, TextureFilter.Linear);
		
		
		menuTriangle2 = new Texture("menuTriangle.png");
		menuTriangle2.setFilter(TextureFilter.Linear, TextureFilter.Linear);
		
		logo.setFilter(TextureFilter.Linear, TextureFilter.Linear);
		tryAgain.setFilter(TextureFilter.Linear, TextureFilter.Linear);
		backHome.setFilter(TextureFilter.Linear, TextureFilter.Linear);
		menu.setFilter(TextureFilter.Linear, TextureFilter.Linear);
		blackRectangle.setFilter(TextureFilter.Linear, TextureFilter.Linear);
		snakeShape.setFilter(TextureFilter.Linear, TextureFilter.Linear);
		yellowPentagon.setFilter(TextureFilter.Linear, TextureFilter.Linear);
		gameOver.setFilter(TextureFilter.Linear, TextureFilter.Linear);
		spritePentagon = new Sprite(yellowPentagon2);
		spritePentagon.setScale(0.5f);
		menuSquare = new Sprite(yellowSquare2);
		menuPentagon = new Sprite(menuPentagon2);
		candy = new Sprite(yellowPentagon2);
	//	menuPentagon.setScale(1.15f);
		yellowRectangle = new Texture("blackRectangle.png");
		yellowRectangle.setFilter(TextureFilter.Linear, TextureFilter.Linear);
		yellowRectangleSprite = new Sprite(yellowPentagon);
		yellowRectangleSprite.setScale(1f);
		yellowRectangleSprite.setPosition(100, 100);
		menuTriangle = new Sprite(menuTriangle2);
		FreeTypeFontGenerator generator = new FreeTypeFontGenerator(Gdx.files.internal("myfont.ttf"));
		FreeTypeFontParameter parameter = new FreeTypeFontParameter();
		parameter.size = 28;
		parameter.magFilter = TextureFilter.Linear;
		parameter.minFilter = TextureFilter.Linear;
		
		font12 = generator.generateFont(parameter); // font size 12 pixels
		
		font12.setColor(0, 0, 0, 1f);

		parameter.size = 56;
		parameter.magFilter = TextureFilter.Linear;
		parameter.minFilter = TextureFilter.Linear;
		
		font52 = generator.generateFont(parameter); // font size 12 pixels
		generator.dispose(); // don't forget to dispose to avoid memory leaks!
		
		font52.setColor(0, 0, 0, 1f);
menuTriangle.setScale(0);
menuPentagon.setScale(0);
menuSquare.setScale(0);

menuTriangle.setAlpha(0.2f);
menuPentagon.setAlpha(0.2f);
menuSquare.setAlpha(1);

		
		this.setScreen(new LogoScreen(this));
	}
	
 
	public void render() {
		super.render(); // important!
	}
 
	public void dispose() {
	
	
	}
}