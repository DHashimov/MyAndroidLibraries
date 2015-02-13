package com.dcorp.GameObjects;

import java.util.Random;

import com.badlogic.gdx.math.Circle;
import com.badlogic.gdx.math.Intersector;
import com.badlogic.gdx.math.Vector2;
import com.dcorp.ZBHelpers.AssetLoader;

public class Candys extends Scrollable {

	// private Vector2 position;

	private Random r;
	// private int width;
	// private float height;
	//
	// private float originalY;
	private float groundY;
	private Circle boundingCircle;
	private boolean isScored = false;

	public Candys(float x, float y, int width, int height, float scrollSpeed,
			float groundY) {
		super(x, y, width, height, scrollSpeed);
		r = new Random();
		// this.width = width;
		// this.height = height;
		// this.originalY = y;
		// position = new Vector2(x, y);
		boundingCircle = new Circle();

		this.groundY = groundY;
	}

	@Override
	public void update(float delta) {
		// Call the update method in the superclass (Scrollable)
		super.update(delta);

		boundingCircle.set(position.x, position.y + 6, 6.5f);

	}

	@Override
	public void reset(float newX) {
		// Call the reset method in the superclass (Scrollable)
		super.reset(newX);
		// Change the height to a random number
		position.set(position.x, r.nextInt(90) + 15);
		setScored(false);
	}

	public boolean collides(Bird bird) {
		if (position.x < bird.getX() + bird.getWidth()) {
			return (Intersector.overlaps(bird.getBoundingCircle(),
					boundingCircle));

		}
		return false;
	}

	public float getX() {
		return position.x;
	}

	public float getY() {
		return position.y;
	}

	public Circle getBoundingCircle() {
		return boundingCircle;
	}

	public boolean isScored() {
		return isScored;
	}

	public void setScored(boolean b) {
		isScored = b;
	}
	
	public void onRestart(float x, float scrollSpeed) {
		velocity.x = scrollSpeed;
		reset(x);
	}

}
