package net.crossager.krystal.schedule;

import java.time.Instant;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Timer;

public class Scheduler {
    private final List<ScheduledTask> tasks = new ArrayList<>();
    private final Timer timer = new Timer();
    private final TaskQueue taskQueue = new TaskQueue();

    public void runTaskAsynchronously(Runnable task) {
        taskQueue.getAvailableExecutor().execute(task);
    }

    public ScheduledTask getTask(int id) {
        synchronized (tasks) {
            for (ScheduledTask timerTask : tasks) {
                if (timerTask.getId() == id) return timerTask;
            }
        }
        return null;
    }

    private int getNewId() {
        int id = (int) (System.nanoTime() % Integer.MAX_VALUE);
        if (getTask(id) == null) return id;
        return getNewId();
    }

    public int runTaskLater(Runnable task, long delay) {
        return scheduleRepeatingTask(task, Date.from(Instant.ofEpochMilli(delay)), 0, true);
    }

    public int scheduleRepeatingTask(Runnable task, long delay, long period) {
        return scheduleRepeatingTask(task, delay, period, false);
    }

    public int scheduleRepeatingTask(Runnable task, long delay, long period, boolean runAsync) {
        return scheduleRepeatingTask(task, Date.from(Instant.ofEpochMilli(delay)), period, runAsync);
    }

    public void cancelTask(int id) {
        synchronized (tasks) {
            for (ScheduledTask task : tasks) {
                if (task.getId() == id) {
                    tasks.remove(task);
                    task.cancel();
                    return;
                }
            }
            throw new IllegalStateException("Invalid id " + id);
        }
    }

    public int scheduleRepeatingTask(Runnable task, Date date, long period, boolean runAsync) {
        int id = getNewId();
        ScheduledTask timerTask = new ScheduledTask(id, runAsync ? () -> taskQueue.getAvailableExecutor().execute(task) : task);
        synchronized (tasks) {
            tasks.add(timerTask);
            if (period <= 0)
                timer.schedule(timerTask, date);
            else
                timer.scheduleAtFixedRate(timerTask, date, period);
        }
        return timerTask.getId();
    }
}
