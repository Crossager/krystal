package net.crossager.krystal.utils;

import java.util.List;
import java.util.Random;

public class KrystalRandom extends Random {
    public boolean withChance(float chance) {
        return nextFloat() < chance;
    }

    public int range(int min, int max) {
        return nextInt(max - min) + min;
    }

    public <T> T random(List<T> list) {
        return list.get(nextInt(list.size()));
    }

    public <T> T random(T[] array) {
        return array[nextInt(array.length)];
    }
}
