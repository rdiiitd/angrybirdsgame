package io.github.some_example_name;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.badlogic.gdx.scenes.scene2d.Touchable;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.scenes.scene2d.utils.DragListener;


public class pig extends Actor {
    private Main game ;
    private Texture pig ;
    float x ;
    float y ;
    float width ;
    float height ;
    int health ;
    
    public pig(Main game , float x , float y , float width ,  float height , int health) {
        this.game = game ;
        this.x = x ;
        this.y = y ;
        this.width = width ;
        this.height = height ;
        this.health = health ;
        pig = new Texture("pig_remove.png") ;
        setBounds((float)  x, (float) y ,width , height );
        setTouchable(Touchable.enabled);

       }

    @Override
    public void draw(Batch batch, float parentAlpha) {

        batch.draw(pig, getX(), getY(), getWidth(), getHeight());
    }




}
