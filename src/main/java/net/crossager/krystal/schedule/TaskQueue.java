package net.crossager.krystal.schedule;

import java.util.*;

public class TaskQueue {
    private final List<TaskExecutor> activeExecutors = new LinkedList<>();

    public TaskExecutor getAvailableExecutor() {
        for (TaskExecutor commandWorker : activeExecutors) {
            if (!commandWorker.isRunning()) return commandWorker;
        }
        TaskExecutor worker = new TaskExecutor();
        activeExecutors.add(worker);
        return worker;
    }
}
