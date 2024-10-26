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

public class level2_screen implements Screen {
     Main game; // Reference to the main game class
    Stage stage;
    Texture mainmenu;  // Moved texture to class level

    redbird red ;
    blackbird black ;
    yellowbird yellow ;

    level2_structure structure ;
    slingshot slingshot ;
    setting_stage setting ;



    public level2_screen(Main game) {
        this.game = game;
    }

    @Override
    public void show() {

        stage = new Stage(game.viewport);

        Gdx.input.setInputProcessor(stage); // Set input processor to the stage
        mainmenu = new Texture("level.jpeg"); // Load the texture

        game.music.stop();

        red = new redbird(game, 119F, 83F,"red") ;
        yellow = new yellowbird(game, 66F, 83.999985F,"yellow") ;
        black = new blackbird(game, 10F, 82F,"black") ;
        slingshot = new slingshot(game,161.00002F , 84.999985F,45,80) ;

        structure = new level2_structure(game , 435 , 100.000015F ,300,200) ;
        setting = new setting_stage(game , 730,532.5F,70 , 70 ) ;

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
        game.batch.draw(mainmenu, 0, 0, 800, 600); // Specify position and size
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
