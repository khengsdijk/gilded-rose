package com.gildedrose;

import java.util.Arrays;

class GildedRose {
    Item[] items;

    private final int MAX_QUALITY = 50;
    private final int MIN_QUALITY = 0;
    private final String SULFARAS = "sulfuras, hand of ragnaros";
    private final String BACKSTAGE_PASS = "backstage passes to a tafkal80etc concert";
    private final String AGED_BRIE = "aged brie";
    private final String CONJURED = "conjured";
    private final int DAILY_QUALITY_DECREASE = 1;

    public GildedRose(Item[] items) {
        this.items = items;
    }

    /**
     * updates the quality of the current stock
     *
     *  if the item does not belong to any of the special categories it
     *  gets updated with a standard method.
     */
    public void updateQuality(){
        for(Item item: items){
            if(updateBrie(item))
                continue;

            if(updateBackstagePass(item))
                continue;

            if(checkSulfaras(item))
                continue;

            lowerQuality(item, DAILY_QUALITY_DECREASE);
        }

    }

    /**
     * Lowers the quality of normal and conjured items that dont belong in a special category
     * Conjured items decrease in quality twice as fast
     */
    public void lowerQuality(Item item, int decrease){

        if(item.name.toLowerCase().contains(CONJURED))
            decrease += decrease;

        if(item.sellIn <= 0 && item.quality > MIN_QUALITY){
            item.quality -= decrease * 2;
        } else if(item.quality > MIN_QUALITY){
            item.quality -= decrease;
        }

        if(item.quality < MIN_QUALITY)
            item.quality = MIN_QUALITY;
        item.sellIn -= 1;
    }

    /**
     * the quality of sulfaras does not increase or decrease so it is not updated.
     */
    public boolean checkSulfaras(Item item){
        return item.name.toLowerCase().equals(SULFARAS);
    }

    /**
     * updates a backstage pass
     * Increases the quality of the item by 2 if the sellin date is 10 or lower
     * Increases the quality of the item by 3 if the sellin date is 5 or lower
     *
     */
    public boolean updateBackstagePass(Item item){
        if(item.name.toLowerCase().equals(BACKSTAGE_PASS)){

            if(item.sellIn <= 0){
                item.quality = MIN_QUALITY;
                return true;
            }

            if(item.sellIn <= 5){
                item.quality += 3;
            } else if(item.sellIn <= 10){
                item.quality += 2;
            }

            if(item.quality > MAX_QUALITY)
                item.quality = MAX_QUALITY;

            item.sellIn -= 1;
            return true;
        }
        return false;
    }

    /**
     * updates an aged brie
     * the quality of the brie increases as it gets older so the quality is always increased
     */
    public boolean updateBrie(Item item){
        if(item.name.toLowerCase().equals(AGED_BRIE)){
            if(item.quality < MAX_QUALITY)
                item.quality += 1;

            item.sellIn -= 1;
            return true;
        }
        return false;
    }
}