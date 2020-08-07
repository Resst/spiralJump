package com.elly.spiraljump;

import com.badlogic.gdx.*;
import com.badlogic.gdx.ai.msg.MessageManager;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.elly.spiraljump.screens.PlayScreen;
import com.elly.spiraljump.tools.assets.MyAssetManager;

//Assist
//TODO сделать UI
//TODO сделать управление
//TODO сделать скрины


public class GameClass extends Game {
	SpriteBatch batch;

	private MessageManager messageManager;
	private MyAssetManager manager;

	@Override
	public void create () {
		batch = new SpriteBatch();
		manager = new MyAssetManager();
		messageManager = MessageManager.getInstance();
		setScreen(new PlayScreen(this));
	}

	@Override
	public void render () {
		super.render();
	}

	@Override
	public void dispose () {
		batch.dispose();
		manager.dispose();
	}

	public SpriteBatch getBatch() {
		return batch;
	}


	public MyAssetManager getManager(){
		return manager;
	}
}
