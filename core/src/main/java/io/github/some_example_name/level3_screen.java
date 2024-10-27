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

public class level3_screen implements Screen {
    Main game; 
    Stage stage;
    Texture mainmenu;  

    redbird red ;
    blackbird black ;
    yellowbird yellow ;

    level3_structure structure ;
    slingshot slingshot ;
    setting_stage setting ;

    public level3_screen(Main game) {
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

        structure = new level3_structure(game , 435 , 100.000015F ,300,200) ;
        setting = new setting_stage(game , 730,532.5F,70 , 70 ) ;

        game.music.stop();

        stage.addActor(slingshot);
        stage.addActor(yellow);
        stage.addActor(black);
        stage.addActor(red);
        stage.addActor(structure);
        stage.addActor(setting);


    }

    @Override
    public void render(float v) {

        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        game.batch.begin();
        game.batch.draw(mainmenu, 0, 0, 800, 600); 
        game.batch.end();


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
