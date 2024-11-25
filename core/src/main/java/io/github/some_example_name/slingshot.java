package io.github.some_example_name;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Touchable;

import java.util.ArrayList;
import java.util.List;

public class slingshot extends Actor {
    private final Texture slingShotTexture;  // Renamed to match proper Java naming convention
    private final Body birdBody;
    private static final float PPM = 100f; // Pixels per meter

    private final Vector2 startPosition = new Vector2();
    private final Vector2 endPosition = new Vector2();
    private boolean isDragging = false;

    private final ShapeRenderer shapeRenderer = new ShapeRenderer();
    private final List<Vector2> trajectoryPoints = new ArrayList<>();

    public slingshot(float x, float y, float width, float height, Body birdBody) {
        // Scale slingshot dimensions based on screen size
        this.setX(x * Gdx.graphics.getWidth() / 800f); // Assume base screen width of 800
        this.setY(y * Gdx.graphics.getHeight() / 600f); // Assume base screen height of 600
        this.setWidth(width * Gdx.graphics.getWidth() / 800f);
        this.setHeight(height * Gdx.graphics.getHeight() / 600f);

        this.birdBody = birdBody;

        slingShotTexture = new Texture(Gdx.files.internal("slingshot_remove.png"));
        setBounds(this.getX(), this.getY(), this.getWidth(), this.getHeight());
        setTouchable(Touchable.enabled);
    }

    private void drawStrings(ShapeRenderer shapeRenderer) {
        shapeRenderer.begin(ShapeRenderer.ShapeType.Filled);

        // Anchor points for the catapult strings
        float anchorLeftX = getX() + getWidth() * 0.2f;  // Left side of the catapult
        float anchorLeftY = getY() + getHeight() * 0.8f; // Top of the catapult
        float anchorRightX = getX() + getWidth() * 0.8f; // Right side of the catapult
        float anchorRightY = anchorLeftY;

        // Bird position in screen coordinates
        float birdX = birdBody.getPosition().x * PPM;
        float birdY = birdBody.getPosition().y * PPM;

        // Draw left string
        shapeRenderer.rectLine(anchorLeftX, anchorLeftY, birdX, birdY, 3); // Line width = 3
        // Draw right string
        shapeRenderer.rectLine(anchorRightX, anchorRightY, birdX, birdY, 3);

        shapeRenderer.end();
    }

    @Override
    public void draw(Batch batch, float parentAlpha) {
        // Draw the slingshot texture (no color change here)
        batch.draw(slingShotTexture, getX(), getY(), getWidth(), getHeight());

        // Draw the strings using a ShapeRenderer
        batch.end(); // End the batch before using ShapeRenderer
        ShapeRenderer shapeRenderer = new ShapeRenderer();
        drawStrings(shapeRenderer);
        shapeRenderer.dispose(); // Dispose of the ShapeRenderer after use
        batch.begin(); // Resume batch rendering
    }

    private void showTrajectory(Vector2 start, Vector2 direction) {
        // Example: Draw trajectory dots using ShapeRenderer
        ShapeRenderer shapeRenderer = new ShapeRenderer();
        shapeRenderer.begin(ShapeRenderer.ShapeType.Filled);
        shapeRenderer.setColor(1, 1, 1, 0.7f); // White with transparency (Trajectory color)

        float timeStep = 0.1f; // Smaller values = smoother trajectory
        float totalTime = 2f; // Total time to simulate trajectory
        Vector2 gravity = new Vector2(0, -9.8f); // Box2D gravity
        Vector2 velocity = direction.scl(10); // Adjust scale for visual feedback

        for (float t = 0; t <= totalTime; t += timeStep) {
            // Calculate position at time t
            Vector2 position = new Vector2(
                start.x + velocity.x * t,
                start.y + velocity.y * t + 0.5f * gravity.y * t * t
            );
            position.scl(PPM); // Convert to pixel scale

            // Draw trajectory point
            shapeRenderer.circle(position.x, position.y, 3); // Radius of 3 pixels
        }

        shapeRenderer.end();
        shapeRenderer.dispose();
    }

    public boolean touchDragged(InputEvent event, float screenX, float screenY, int pointer) {
        // Convert screen coordinates to world coordinates
        Vector2 dragPosition = new Vector2(screenX, Gdx.graphics.getHeight() - screenY); // Flip y-axis
        dragPosition.scl(1 / PPM); // Convert to Box2D scale

        // Calculate distance from anchor
        float anchorX = getX() + getWidth() / 2f; // Center of the slingshot
        float anchorY = getY() + getHeight() * 0.8f; // Top of the slingshot
        Vector2 anchorPosition = new Vector2(anchorX / PPM, anchorY / PPM); // Box2D scale

        float maxStretchDistance = 2.0f; // Maximum distance in Box2D meters
        Vector2 direction = dragPosition.sub(anchorPosition);
        if (direction.len() > maxStretchDistance) {
            direction.setLength(maxStretchDistance); // Restrict to maximum stretch
        }

        // Update bird position
        if (birdBody != null) {
            birdBody.setTransform(anchorPosition.add(direction), 0); // Keep the bird within limits
        }

        // Trigger trajectory preview (optional, requires trajectory drawing logic)
        showTrajectory(anchorPosition, direction);

        return true;
    }

    private void updateTrajectory() {
        trajectoryPoints.clear();

        // Calculate trajectory using physics formula: x = v0 * t * cos(θ), y = v0 * t * sin(θ) - (1/2) * g * t²
        Vector2 initialVelocity = startPosition.sub(endPosition).scl(0.1f); // Adjust scaling factor as needed
        float timeStep = 0.1f; // Time step for prediction
        float totalTime = 2.0f; // Simulate up to 2 seconds
        float g = -9.8f; // Gravity

        for (float t = 0; t < totalTime; t += timeStep) {
            float x = birdBody.getPosition().x + initialVelocity.x * t;
            float y = birdBody.getPosition().y + initialVelocity.y * t + 0.5f * g * t * t;

            trajectoryPoints.add(new Vector2(x * 100, y * 100)); // Convert back to pixels
        }
    }

    public boolean touchUp(InputEvent event, float x, float y, int pointer, int button) {
        if (birdBody != null) {
            // Calculate launch force
            Vector2 anchorPosition = new Vector2(
                (getX() + getWidth() / 2f) / PPM,
                (getY() + getHeight() * 0.8f) / PPM
            );
            Vector2 birdPosition = birdBody.getPosition();
            Vector2 launchForce = anchorPosition.sub(birdPosition).scl(10); // Adjust scaling factor

            // Apply force to the bird
            birdBody.applyLinearImpulse(launchForce, birdBody.getWorldCenter(), true);
        }
        return true;
    }

    public void dispose() {
        slingShotTexture.dispose();  // Dispose of the texture correctly
        shapeRenderer.dispose();
    }
}
