package com.elly.spiraljump.gameplay.Obstacles;

import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.CircleShape;
import com.badlogic.gdx.physics.box2d.Fixture;
import com.badlogic.gdx.physics.box2d.FixtureDef;
import com.badlogic.gdx.physics.box2d.PolygonShape;
import com.elly.spiraljump.gameplay.Planet;
import com.elly.spiraljump.tools.assets.ObstacleAssets;

public class Triangle extends Obstacle {

    public Triangle(Planet planet) {
        super(planet);
    }

    @Override
    public Fixture defineCollider() {
        FixtureDef fdef = new FixtureDef();

        PolygonShape shape = new PolygonShape();
        Vector2[] vertices = new Vector2[3];
        vertices[0] = new Vector2(-size.x / 2, Planet.RADIUS);
        vertices[1] = new Vector2(0, Planet.RADIUS + size.y);
        vertices[2] = new Vector2(size.x / 2, Planet.RADIUS);
        shape.set(vertices);
        fdef.shape = shape;

        return body.createFixture(fdef);
    }

    @Override
    public void setUpSprite() {
        sprite = new Sprite(assets.getTexture(ObstacleAssets.SPIKES));
    }
}
