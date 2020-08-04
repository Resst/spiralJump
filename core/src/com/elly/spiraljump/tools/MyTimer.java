package com.elly.spiraljump.tools;

public abstract class MyTimer implements Runnable {

    private long time;

    public MyTimer(long time){
        this.time = time;
        new Thread(this).start();
    }

    @Override
    public void run() {
        try {
            Thread.sleep(time);
        } catch (InterruptedException ignored) {}
        after();
    }

    public abstract void after();
}
