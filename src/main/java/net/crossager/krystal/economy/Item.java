package net.crossager.krystal.economy;

import net.crossager.krystal.KrystalContext;
import net.crossager.krystal.utils.Identifiable;

public class Item implements Identifiable {
    private final ItemCategory category;
    private final int id;
    private final String name;
    private final String display;
    private final long minPrice;
    private final long maxPrice;
    private final int totalStock;

    private boolean lastIteration = true;
    private long currentPrice;
    private int currentStock;

    public Item(ItemCategory category, int id, String name, String display, long minPrice, long maxPrice, int totalStock) {
        this.category = category;
        this.id = id;
        this.name = name;
        this.display = display;
        this.minPrice = minPrice;
        this.maxPrice = maxPrice;
        this.totalStock = totalStock;
        this.currentStock = totalStock;
        this.currentPrice = (int) (minPrice + ((maxPrice - minPrice) * 0.5));
    }

    public void doPriceIteration(KrystalContext context) {
        if (minPrice == maxPrice) return;
        long priceRange = maxPrice - minPrice;
        boolean thisIteration = context.random().nextBoolean();
        if (thisIteration != lastIteration) thisIteration = context.random().nextBoolean();
        if (thisIteration) {
            currentPrice += (maxPrice - currentPrice) * (0.08 / priceRange) * priceRange;
        } else {
            currentPrice -= (currentPrice - minPrice) * (0.08 / priceRange) * priceRange;
        }
        if (currentPrice > maxPrice) currentPrice = maxPrice;
        if (currentPrice < minPrice) currentPrice = minPrice;
        lastIteration = thisIteration;
    }

    public String getName() {
        return name;
    }

    public String getDisplay() {
        return display;
    }

    public ItemCategory getCategory() {
        return category;
    }

    public boolean isLimitedSupply() {
        return totalStock != 0;
    }

    public int getCurrentStock() {
        return currentStock;
    }

    public void setCurrentStock(int currentStock) {
        this.currentStock = currentStock;
    }

    public void removeStock(int amount) {
        if (!isLimitedSupply()) return;
        this.currentStock -= amount;
    }

    public long getCurrentPrice() {
        return currentPrice;
    }

    public void setCurrentPrice(long currentPrice) {
        this.currentPrice = currentPrice;
    }

    @Override
    public int id() {
        return id;
    }
}
