package com.elly.spiraljump.gameplay.Levels;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.World;
import com.elly.spiraljump.gameplay.Player;
import com.elly.spiraljump.screens.PlayScreen;
import com.elly.spiraljump.gameplay.Planet;
import com.elly.spiraljump.tools.WorldContactListener;

public class Level {

    private PlayScreen screen;
    private World world;
    private Planet planet;
    private Player player;


    public Level(PlayScreen screen){
        this.screen = screen;
        world = new World(new Vector2(0, -10), false);
        world.setContactListener(new WorldContactListener());
        planet = new Planet(this);
        player = new Player(this);

    }

    public void draw(SpriteBatch batch){
        planet.draw(batch);
        player.draw(batch);
    }

    public void update(float dt){
        planet.update(dt);
        player.update(dt);
        world.step(dt,5,5);
    }

    public PlayScreen getScreen(){
        return screen;
    }

    public World getWorld(){
        return world;
    }

    public Planet getPlanet(){
        return planet;
    }

}
