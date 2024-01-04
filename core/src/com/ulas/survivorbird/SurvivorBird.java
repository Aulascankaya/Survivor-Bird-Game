package com.ulas.survivorbird;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Circle;
import com.badlogic.gdx.math.Intersector;
import com.badlogic.gdx.utils.ScreenUtils;

import java.util.Random;

public class SurvivorBird extends ApplicationAdapter {

	SpriteBatch batch;
	Texture background;
	Texture airship;
	Texture enemy1;
	Texture enemy2;
	Texture enemy3;

	Texture enemy4;
	Texture enemy5;

	float airshipX = 0;
	float airshipY = 0;
	int gameState = 0;
	float velocity = 0;
	float gravity= 0.5f;

	float enemyVelocity = 5;
	Random random;
	int score = 0;
	int scoredEnemy=0;

	BitmapFont font;
	BitmapFont font2;
	BitmapFont font3;

	Circle airshipCircle;

	ShapeRenderer shapeRenderer;




	int numberOfEnemies = 4 ;
	float [] enemyX = new float[numberOfEnemies];
	float [] enemyOffSet1 = new float[numberOfEnemies];
	float [] enemyOffSet2 = new float[numberOfEnemies];
	float [] enemyOffSet3 = new float[numberOfEnemies];
	float [] enemyOffSet4 = new float[numberOfEnemies];
	float [] enemyOffSet5 = new float[numberOfEnemies];

	float distance = 0;

	Circle [] enemyCircles1;
	Circle [] enemyCircles2;
	Circle [] enemyCircles3;
	Circle [] enemyCircles4;
	Circle [] enemyCircles5;


	
	@Override
	public void create () {
	batch = new SpriteBatch();
	background = new Texture("background.png");
	airship = new Texture("airship.png");
	enemy1 = new Texture("enemy.png");
	enemy2 = new Texture("enemy.png");
	enemy3 = new Texture("enemy.png");
	enemy4 = new Texture("enemy.png");
	enemy5 = new Texture("enemy.png");

	distance = Gdx.graphics.getWidth() / 2;

	random = new Random();

	airshipX = Gdx.graphics.getWidth() / 4;
	airshipY = Gdx.graphics.getHeight() / 2;

	shapeRenderer = new ShapeRenderer();

	airshipCircle = new Circle();
	enemyCircles1 = new Circle[numberOfEnemies];
	enemyCircles2 = new Circle[numberOfEnemies];
	enemyCircles3 = new Circle[numberOfEnemies];
	enemyCircles4 = new Circle[numberOfEnemies];
	enemyCircles5 = new Circle[numberOfEnemies];

	font= new BitmapFont();
	font.setColor(Color.WHITE);
	font.getData().setScale(4);

	font2= new BitmapFont();
	font2.setColor(Color.RED);
	font2.getData().setScale(6);

	font3= new BitmapFont();
	font3.setColor(Color.BLACK);
	font3.getData().setScale(5);

	for(int i =0 ; i< numberOfEnemies; i++){

		enemyOffSet1[i] = (random.nextFloat() - 0.5f) * (Gdx.graphics.getHeight()-200);
		enemyOffSet2[i] = (random.nextFloat() - 0.5f) * (Gdx.graphics.getHeight()-200);
		enemyOffSet3[i] = (random.nextFloat() - 0.5f) * (Gdx.graphics.getHeight()-200);
		enemyOffSet4[i] = (random.nextFloat() - 0.5f) * (Gdx.graphics.getHeight()-200);
		enemyOffSet5[i] = (random.nextFloat() - 0.5f) * (Gdx.graphics.getHeight()-200);

		enemyX [i] =Gdx.graphics.getWidth() - enemy1.getWidth()/2 + i * distance;

		enemyCircles1 [i] = new Circle();
		enemyCircles2 [i] = new Circle();
		enemyCircles3 [i] = new Circle();
		enemyCircles4 [i] = new Circle();
		enemyCircles5 [i] = new Circle();

	}


	}

	@Override
	public void render () {

		batch.begin();
		batch.draw(background,0,0, Gdx.graphics.getWidth(),Gdx.graphics.getHeight());

		if(gameState ==1){


			if(enemyX[scoredEnemy] < Gdx.graphics.getWidth() / 4){
				score ++;
				if(scoredEnemy < numberOfEnemies-1){
					scoredEnemy++;
				}else {
					scoredEnemy = 0;
				}


			}

			if(Gdx.input.justTouched()){
				velocity = -12;
			}

			for( int i =0; i< numberOfEnemies;i++){

				if(enemyX[i] < -enemy1.getWidth()){
					enemyX[i] = enemyX[i] + numberOfEnemies * distance;

					enemyOffSet1[i] = (random.nextFloat() - 0.5f) * (Gdx.graphics.getHeight()-200);
					enemyOffSet2[i] = (random.nextFloat() - 0.5f) * (Gdx.graphics.getHeight()-200);
					enemyOffSet3[i] = (random.nextFloat() - 0.5f) * (Gdx.graphics.getHeight()-200);
					enemyOffSet4[i] = (random.nextFloat() - 0.5f) * (Gdx.graphics.getHeight()-200);
					enemyOffSet5[i] = (random.nextFloat() - 0.5f) * (Gdx.graphics.getHeight()-200);

				}else {
					enemyX[i] = enemyX[i] - enemyVelocity;
				}


				batch.draw(enemy1,enemyX[i],Gdx.graphics.getHeight()/2 + enemyOffSet1[i],Gdx.graphics.getWidth() /17,Gdx.graphics.getHeight()/15);
				batch.draw(enemy2,enemyX[i],Gdx.graphics.getHeight()/2 + enemyOffSet2[i],Gdx.graphics.getWidth() /17,Gdx.graphics.getHeight()/15);
				batch.draw(enemy3,enemyX[i],Gdx.graphics.getHeight()/2 + enemyOffSet3[i],Gdx.graphics.getWidth() /17,Gdx.graphics.getHeight()/15);
				batch.draw(enemy4,enemyX[i],Gdx.graphics.getHeight()/2 + enemyOffSet4[i],Gdx.graphics.getWidth() /17,Gdx.graphics.getHeight()/15);
				batch.draw(enemy5,enemyX[i],Gdx.graphics.getHeight()/2 + enemyOffSet5[i],Gdx.graphics.getWidth() /17,Gdx.graphics.getHeight()/15);

				enemyCircles1[i] = new Circle(enemyX[i] +Gdx.graphics.getWidth() /34, Gdx.graphics.getHeight()/2 + enemyOffSet1[i]+Gdx.graphics.getHeight()/30,Gdx.graphics.getWidth() /50);
				enemyCircles2[i] = new Circle(enemyX[i] +Gdx.graphics.getWidth() /34, Gdx.graphics.getHeight()/2 + enemyOffSet2[i]+Gdx.graphics.getHeight()/30,Gdx.graphics.getWidth() /50);
				enemyCircles3[i] = new Circle(enemyX[i] +Gdx.graphics.getWidth() /34, Gdx.graphics.getHeight()/2 + enemyOffSet3[i]+Gdx.graphics.getHeight()/30,Gdx.graphics.getWidth() /50);
				enemyCircles4[i] = new Circle(enemyX[i] +Gdx.graphics.getWidth() /34, Gdx.graphics.getHeight()/2 + enemyOffSet4[i]+Gdx.graphics.getHeight()/30,Gdx.graphics.getWidth() /50);
				enemyCircles5[i] = new Circle(enemyX[i] +Gdx.graphics.getWidth() /34, Gdx.graphics.getHeight()/2 + enemyOffSet5[i]+Gdx.graphics.getHeight()/30,Gdx.graphics.getWidth() /50);

			}




			if(airshipY>0 && airshipY < Gdx.graphics.getHeight()) {
				velocity = velocity + gravity;
				airshipY = airshipY - velocity;
			}else{
				gameState = 2;
			}


		}else if (gameState == 0) {
			if(Gdx.input.justTouched()){
				gameState = 1;
			}
		} else if (gameState == 2) {

			font2.draw(batch,"Game Over!  Tap To Play Again!",250,Gdx.graphics.getHeight()/2 );
			font3.draw(batch,"Last Score : " + score,250,Gdx.graphics.getHeight()/3 );

			if(Gdx.input.justTouched()){
				gameState = 1;

				airshipY = Gdx.graphics.getHeight() / 2;

				for(int i =0 ; i< numberOfEnemies; i++){

					enemyOffSet1[i] = (random.nextFloat() - 0.5f) * (Gdx.graphics.getHeight()-200);
					enemyOffSet2[i] = (random.nextFloat() - 0.5f) * (Gdx.graphics.getHeight()-200);
					enemyOffSet3[i] = (random.nextFloat() - 0.5f) * (Gdx.graphics.getHeight()-200);
					enemyOffSet4[i] = (random.nextFloat() - 0.5f) * (Gdx.graphics.getHeight()-200);
					enemyOffSet5[i] = (random.nextFloat() - 0.5f) * (Gdx.graphics.getHeight()-200);

					enemyX [i] =Gdx.graphics.getWidth() - enemy1.getWidth()/2 + i * distance;

					enemyCircles1 [i] = new Circle();
					enemyCircles2 [i] = new Circle();
					enemyCircles3 [i] = new Circle();
					enemyCircles4 [i] = new Circle();
					enemyCircles5 [i] = new Circle();

				}
				velocity = 0 ;
				scoredEnemy=0;
				score=0;
			}
		}


		batch.draw(airship,airshipX,airshipY,Gdx.graphics.getWidth() /17,Gdx.graphics.getHeight()/15);

		font.draw(batch,String.valueOf(score),100,200);


		batch.end();

		airshipCircle.set(airshipX + Gdx.graphics.getWidth() / 34,airshipY + Gdx.graphics.getHeight()/30,Gdx.graphics.getWidth() /50);
		//begin(ShapeRenderer.ShapeType.Filled);
		//shapeRenderer.setColor(Color.BLACK);
		//shapeRenderer.circle(airshipCircle.x,airshipCircle.y,airshipCircle.radius);



		for (int i = 0 ; i< numberOfEnemies ; i++){
			//shapeRenderer.circle(enemyX[i] +Gdx.graphics.getWidth() /34, Gdx.graphics.getHeight()/2 + enemyOffSet1[i]+Gdx.graphics.getHeight()/30,Gdx.graphics.getWidth() /50);
			//shapeRenderer.circle(enemyX[i] +Gdx.graphics.getWidth() /34, Gdx.graphics.getHeight()/2 + enemyOffSet2[i]+Gdx.graphics.getHeight()/30,Gdx.graphics.getWidth() /50);
			//shapeRenderer.circle(enemyX[i] +Gdx.graphics.getWidth() /34, Gdx.graphics.getHeight()/2 + enemyOffSet3[i]+Gdx.graphics.getHeight()/30,Gdx.graphics.getWidth() /50);
			//shapeRenderer.circle(enemyX[i] +Gdx.graphics.getWidth() /34, Gdx.graphics.getHeight()/2 + enemyOffSet4[i]+Gdx.graphics.getHeight()/30,Gdx.graphics.getWidth() /50);
			//shapeRenderer.circle(enemyX[i] +Gdx.graphics.getWidth() /34, Gdx.graphics.getHeight()/2 + enemyOffSet5[i]+Gdx.graphics.getHeight()/30,Gdx.graphics.getWidth() /50);

			if(Intersector.overlaps(airshipCircle,enemyCircles1[i]) || Intersector.overlaps(airshipCircle,enemyCircles2[i]) || Intersector.overlaps(airshipCircle,enemyCircles3[i]) || Intersector.overlaps(airshipCircle,enemyCircles4[i]) || Intersector.overlaps(airshipCircle,enemyCircles5[i])){
				gameState = 2;
			}
		}

		//shapeRenderer.end();

	}
	
	@Override
	public void dispose () {

	}
}
