package net.crossager.krystal.schedule;

public class TaskExecutor {
    private Runnable currentTask;
    private final ExecutorThread thread = new ExecutorThread();

    public TaskExecutor() {
        thread.start();
    }

    public boolean isRunning() {
        return currentTask != null;
    }

    public void execute(Runnable task) {
        if (isRunning()) throw new IllegalStateException("Task is already executing");
        currentTask = task;
        synchronized (thread) {
            thread.notify();
        }
    }

    private class ExecutorThread extends Thread {
        private void startWaiting() {
            if (currentTask != null) return;
            try {
                wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        @Override
        public void run() {
            startWaiting();
            while (true) {
                synchronized (this) {
                    try {
                        currentTask.run();
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                    currentTask = null;
                    startWaiting();
                }
            }
        }
    }
}
