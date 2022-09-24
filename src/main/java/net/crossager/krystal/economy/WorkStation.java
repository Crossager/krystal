package net.crossager.krystal.economy;

import net.crossager.krystal.utils.Identifiable;

public record WorkStation(int id, String name, long coolDown, int min, int max) implements Identifiable {
}

