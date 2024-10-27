package io.github.some_example_name;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Touchable;

public class blackbird extends Actor {
    float x;
    float y;
    String colour;
    private Main game;
    private Texture black;
    private float offsetX, offsetY;

    // Constructor
    public blackbird(Main game, float x, float y, String colour) {
        super() ;
        this.game = game;
        this.x = x;
        this.y = y;
        this.colour = colour; // Use the colour parameter
        black = new Texture("black_removed.png");
        setBounds(x, y, 50, 50); // No need for casting to float
        setTouchable(Touchable.enabled);
    }


    @Override
    public void draw(Batch batch, float parentAlpha) {
        batch.draw(black, getX(), getY(), 50, 50);
    }


    @Override
    public void act(float delta) {
        super.act(delta);
    }


    public void dispose() {
        black.dispose();
    }
}
