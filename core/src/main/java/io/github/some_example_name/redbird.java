package io.github.some_example_name;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.badlogic.gdx.scenes.scene2d.Touchable;
import com.badlogic.gdx.scenes.scene2d.utils.DragListener;

public class redbird extends Actor {
    float x ;
    float y ;
    String colour ;
    private Main game ;
    private Texture red ;


    redbird(Main game  ,float x , float y , String colour ){
        super() ;
        this.game = game ;
        this.x = x ;
        this.y = y ;
        this.colour = "red" ;
        red = new Texture("red_remove.png") ;
        setBounds((float)  x, (float) y ,50 , 50 );
        setTouchable(Touchable.enabled);


    }
    @Override
    public void draw(Batch batch, float parentAlpha) {

        batch.draw(red, getX(), getY(), 50, 50);
    }


}
