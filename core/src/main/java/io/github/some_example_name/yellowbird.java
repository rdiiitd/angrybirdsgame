package io.github.some_example_name;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.badlogic.gdx.scenes.scene2d.Touchable;
import com.badlogic.gdx.scenes.scene2d.utils.DragListener;

public class yellowbird extends Actor {
    float x ;
    float y ;
    String colour ;
    private Main game ;
    private Texture yellow ;


    yellowbird(Main game  ,float x , float y , String colour ){
        this.game = game ;
        this.x = x ;
        this.y = y ;
        this.colour = "yellow" ;
        yellow = new Texture("yellow_remove.png") ;
        setBounds((float)  x, (float) y ,50 , 50 );
        setTouchable(Touchable.enabled);


    }

    public void draw(Batch batch, float parentAlpha) {

        batch.draw(yellow, getX(), getY(), 50, 50);
    }




}
