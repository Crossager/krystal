package net.crossager.krystal.economy;

import java.util.AbstractList;
import java.util.List;

public class DefaultWorkStations extends AbstractList<WorkStation> {
    private final List<WorkStation> list = List.of(new WorkStation(3215234, "cool", 10000, 400, 600), new WorkStation(12124214, "better", 30000, 100, 1241));

    @Override
    public WorkStation get(int index) {
        return list.get(index);
    }

    @Override
    public int size() {
        return list.size();
    }
}
