package com.elly.spiraljump.tools;

import com.badlogic.gdx.physics.box2d.Contact;
import com.badlogic.gdx.physics.box2d.ContactImpulse;
import com.badlogic.gdx.physics.box2d.ContactListener;
import com.badlogic.gdx.physics.box2d.Fixture;
import com.badlogic.gdx.physics.box2d.Manifold;
import com.elly.spiraljump.gameplay.Obstacles.Obstacle;
import com.elly.spiraljump.gameplay.Player;

public class WorldContactListener implements ContactListener {
    @Override
    public void beginContact(Contact contact) {
        Fixture a = contact.getFixtureA();
        Fixture b = contact.getFixtureB();
        if (isPlayer(a))
            contactWithPlayer(((Player) a.getBody().getUserData()), b, true);
        else if (isPlayer(b))
            contactWithPlayer(((Player) b.getBody().getUserData()), a, true);
    }

    @Override
    public void endContact(Contact contact) {
        Fixture a = contact.getFixtureA();
        Fixture b = contact.getFixtureB();
        if (isPlayer(a))
            contactWithPlayer(((Player) a.getBody().getUserData()), b, false);
        else if (isPlayer(b))
            contactWithPlayer(((Player) b.getBody().getUserData()), a, false);
    }

    @Override
    public void preSolve(Contact contact, Manifold oldManifold) {

    }

    @Override
    public void postSolve(Contact contact, ContactImpulse impulse) {

    }

    private void contactWithPlayer(Player player, Fixture fixture, boolean begin){
        short category = fixture.getFilterData().categoryBits;
        if (category == Constants.GROUND_BIT)
            player.canJump = begin;
        else if (category == Constants.OBSTACLE_BIT)
            ((Obstacle) fixture.getBody().getUserData()).contactWithPlayer(player);
    }

    private boolean isPlayer(Fixture fixture){
        return fixture.getFilterData().categoryBits == Constants.PLAYER_BIT;
    }
}
