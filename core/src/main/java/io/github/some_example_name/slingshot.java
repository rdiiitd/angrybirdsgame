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


public class slingshot extends Actor {
    private Main game ;
    private Texture sling_shot ;
    float x ;
    float y ;
    float width ;
    float height ;

    private float offsetX, offsetY;
    public slingshot(Main game , float x , float y , float width ,  float height ) {
        this.game = game ;
        this.x = x ;
        this.y = y ;
        this.width = width ;
        this.height = height ;

        sling_shot = new Texture(Gdx.files.internal("slingshot_remove.png"));
        setBounds((float)  x, (float) y ,width , height );
        setTouchable(Touchable.enabled);

         }




    @Override
    public void draw(Batch batch, float parentAlpha) {

        batch.draw(sling_shot, getX(), getY(), getWidth(), getHeight());
    }











}
