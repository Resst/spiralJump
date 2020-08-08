package com.elly.spiraljump.gameplay;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.Filter;
import com.badlogic.gdx.physics.box2d.FixtureDef;
import com.badlogic.gdx.physics.box2d.PolygonShape;
import com.elly.spiraljump.gameplay.Levels.Level;
import com.elly.spiraljump.tools.Constants;
import com.elly.spiraljump.tools.assets.PlanetAssets;

public class Player {

    private Level level;

    private Body body;
    private Vector2 size;
    private Sprite sprite;

    public boolean canJump = false;
    private boolean setToDestroy = false;
    private boolean destroyed = false;

    private float jumpSpeed = 10;
    private boolean prevPressed;
    private float jumpTime = 0;

    public Player(Level level) {
        this.level = level;

        size = new Vector2(.7f, .7f);
        definePlayer();
        defineSprite();
    }

    public void update(float dt) {

        if (!destroyed && setToDestroy)
            destroy();

        if (!destroyed) {
            sprite.setOriginBasedPosition(body.getPosition().x, body.getPosition().y);

            jump(dt);
        }
    }

    public void draw(SpriteBatch batch) {
        if (!destroyed)
            sprite.draw(batch);
    }

    public void jump(float dt) {
        if (canJump && Gdx.input.isKeyJustPressed(Input.Keys.W)) {
            prevPressed = true;
            body.applyLinearImpulse(0, 7, body.getPosition().x, body.getPosition().y, true);
            canJump = false;
            jumpTime += dt;
        } else if (prevPressed && Gdx.input.isKeyPressed(Input.Keys.W)) {
            jumpTime += dt;
            body.applyForceToCenter(new Vector2(0, 17 - jumpTime), true);
        } else {
            prevPressed = false;
            jumpTime = 0;
        }
    }

    public void ground() {
        canJump = true;
        prevPressed = false;
    }

    public void die() {
        setToDestroy = true;
    }

    public void destroy() {
        level.getWorld().destroyBody(body);
        destroyed = true;
    }

    public void definePlayer() {
        BodyDef bdef = new BodyDef();
        bdef.type = BodyDef.BodyType.DynamicBody;
        bdef.allowSleep = false;
        bdef.position.set(level.getPlanet().getCenter().x, level.getPlanet().getCenter().y + level.getPlanet().getRadius() + size.y / 2);
        bdef.gravityScale = 3;
        body = level.getPlanet().getLevel().getWorld().createBody(bdef);
        body.setUserData(this);

        FixtureDef fdef = new FixtureDef();
        PolygonShape shape = new PolygonShape();
        shape.setAsBox(size.x / 2, size.y / 2);
        fdef.shape = shape;
        fdef.filter.categoryBits = Constants.PLAYER_BIT;
        fdef.filter.maskBits = Constants.GROUND_BIT | Constants.OBSTACLE_BIT;
        body.createFixture(fdef);
    }

    public void defineSprite() {
        sprite = new Sprite(level.getPlanet().getLevel().getScreen().getGame().getManager().planet.getTexture(PlanetAssets.PLANET));

        sprite.setSize(size.x, size.y);
        sprite.setOrigin(size.x / 2, size.y / 2);
    }


}
