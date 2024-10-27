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

import java.util.Objects;


public class steel extends Actor {
    private Main game ;
    private Texture steelx ;
    float x ;
    float y ;
    float width ;
    float height ;
    int health ;
    String type ;

    public steel(Main game , float x , float y , float width ,  float height , int health,String type) {
        this.game = game ;
        this.x = x ;
        this.y = y ;
        this.width = width ;
        this.height = height ;
        this.health = health ;
        this.type = type ;
        if(Objects.equals(type, "box")){
            steelx = new Texture("box.png") ;
        }
        else{
            steelx = new Texture("wood.png") ;
        }

        setBounds((float)  x, (float) y ,width , height );
        setTouchable(Touchable.enabled);


    }

    @Override
    public void draw(Batch batch, float parentAlpha) {
        batch.draw(steelx, getX(), getY(), getWidth(), getHeight());
    }











}
