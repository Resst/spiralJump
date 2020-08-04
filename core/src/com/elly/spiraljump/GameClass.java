package com.elly.spiraljump;

import com.badlogic.gdx.*;
import com.badlogic.gdx.ai.msg.MessageManager;
import com.badlogic.gdx.ai.msg.Telegraph;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.elly.spiraljump.tools.assets.MyAssetManager;

//Assist
//TODO сделать UI
//TODO сделать управление
//TODO сделать скрины

//Resst
//TODO сделать препятствия
//TODO сделать игрока
//TODO сделать мир и коллайдеры
//TODO сделать физику
//TODO сделать философию

public class GameClass extends Game {
	SpriteBatch batch;
	Texture img;

	private MessageManager messageManager;
	private MyAssetManager manager;

	@Override
	public void create () {
		batch = new SpriteBatch();
		img = new Texture("badlogic.jpg");
		manager = new MyAssetManager();
		messageManager = MessageManager.getInstance();
	}

	@Override
	public void render () {
		super.render();
	}

	@Override
	public void dispose () {
		batch.dispose();
		img.dispose();
	}

	public SpriteBatch getBatch() {
		return batch;
	}


	public MyAssetManager getManager(){
		return manager;
	}
}
