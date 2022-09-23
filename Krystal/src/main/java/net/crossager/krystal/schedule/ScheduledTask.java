package net.crossager.krystal.schedule;

import java.util.TimerTask;

public class ScheduledTask extends TimerTask {
    private final int id;
    private final Runnable task;

    public ScheduledTask(int id, Runnable task){
        this.id = id;
        this.task = task;
    }

    public int getId() {
        return id;
    }

    @Override
    public void run() {
        try {
            task.run();
        } catch (Exception e){
            System.err.printf("Timer Task %s had an uncaught exception%n", id);
            e.printStackTrace();
        }
    }

    @Override
    public int hashCode() {
        return task.hashCode();
    }

    @Override
    public boolean equals(Object obj) {
        return task.equals(obj);
    }

    @Override
    public String toString() {
        return task.toString();
    }
}
