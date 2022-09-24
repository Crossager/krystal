package net.crossager.krystal.utils;

import net.crossager.krystal.KrystalContext;

public class TimeoutListener {
    private final Runnable onStop;
    private final long delay;
    private int task;
    private boolean running;
    private final KrystalContext context;

    public TimeoutListener(KrystalContext context, Runnable onStop, long delay) {
        this.onStop = onStop;
        this.delay = delay;
        this.context = context;
        start();
    }

    public TimeoutListener(KrystalContext context, Runnable onStop) {
        this(context, onStop, 30000);
    }

    public void start() {
        task = context.scheduler().runTaskLater(onStop, delay);
        running = true;
    }

    public void ping() {
        context.scheduler().cancelTask(task);
        task = context.scheduler().runTaskLater(onStop, delay);
    }

    public void stop() {
        if (!running) return;
        context.scheduler().cancelTask(task);
        running = false;
    }
}
