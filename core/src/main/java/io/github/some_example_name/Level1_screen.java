package io.github.some_example_name;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.Viewport;

public class Level1_screen implements Screen {
    Main game;
    Stage stage;
    Texture mainmenu;
    redbird red ;
    blackbird black ;
    yellowbird yellow ;
    wood wood ;
    wood wood_2 ;
    pig pig_1 ;


    wood wood_3 ;
    wood wood_4 ;
    pig pig_3 ;


    wood wood_5 ;
    wood wood_6 ;
    pig pig_5 ;




    slingshot slingshot ;
    setting_stage setting ;



    public Level1_screen(Main game) {
        this.game = game;
    }

    @Override
    public void show() {

        stage = new Stage(game.viewport);
        Gdx.input.setInputProcessor(stage);

        mainmenu = new Texture("level.jpeg");


         red = new redbird(game, 119F, 83F,"red") ;
        yellow = new yellowbird(game, 66F, 83.999985F,"yellow") ;
        black = new blackbird(game, 10F, 82F,"black") ;
         slingshot = new slingshot(game,161.00002F , 84.999985F,45,80) ;

         setting = new setting_stage(game , 730,532.5F,70 , 70 ) ;

        wood = new wood(game , 578 , 206,85,25,100,"horizontal_wood");
        wood_2 = new wood(game , 607 , 73,25,150,100,"straight_wood");
        pig_1 = new pig(game , 589,228,50,50,100) ;



        wood_3 = new wood(game , 719F, 125.999985F,50,15,100,"horizontal_wood");
        wood_4 = new wood(game , 731 , 82,25,50,100,"straight_wood");
        pig_3 = new pig(game , 726,137.00002F,35,35,100) ;


        wood_5 = new wood(game , 480F, 124.999985F,50,15,100,"horizontal_wood");
        wood_6 = new wood(game , 492 , 81,25,50,100,"straight_wood");
        pig_5 = new pig(game , 487,136.00002F,35,35,100) ;

        game.music.stop();
         stage.addActor(slingshot);
         stage.addActor(yellow);
         stage.addActor(black);
        stage.addActor(red);
        stage.addActor(wood);
        stage.addActor(wood_2);
        stage.addActor(pig_1);


        stage.addActor(wood_3);
        stage.addActor(wood_4);
        stage.addActor(pig_3);


        stage.addActor(wood_5);
        stage.addActor(wood_6);
        stage.addActor(pig_5);



        stage.addActor(setting);

    }

    @Override
    public void render(float v) {

        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        game.batch.begin();
        game.batch.draw(mainmenu, 0, 0, 800, 600); // Specify position and size
        game.batch.end();

        // Update and draw the stage
        stage.act(Gdx.graphics.getDeltaTime());

        stage.draw();



    }

    @Override
    public void resize(int width, int height) {
        game.viewport.update(width, height);
    }

    @Override
    public void pause() {

    }

    @Override
    public void resume() {

    }

    @Override
    public void hide() {

    }

    @Override
    public void dispose() {

        stage.dispose();
        mainmenu.dispose();
        game.batch.dispose();
    }
}
