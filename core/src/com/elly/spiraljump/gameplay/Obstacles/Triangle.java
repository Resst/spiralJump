package com.elly.spiraljump.gameplay.Obstacles;

import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.CircleShape;
import com.badlogic.gdx.physics.box2d.FixtureDef;
import com.elly.spiraljump.gameplay.Planet;

public class Triangle extends Obstacle {

    public Triangle(Planet planet) {
        super(planet);
    }

    @Override
    public void defineCollider() {
        FixtureDef fdef = new FixtureDef();
        CircleShape shape = new CircleShape();
        shape.setPosition(new Vector2(0, Planet.RADIUS + size.y / 2));
        shape.setRadius(size.y / 2);
        fdef.shape = shape;
        body.createFixture(fdef);
    }

    @Override
    public void setUpSprite() {
        sprite = new Sprite(planet.getLevel().getScreen().getGame().getManager().planet.getTexture());
    }
}
