package com.gildedrose;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class GildedRoseTest {

    @Test
    void testSingleDaySingleItem() {

        Item[] items = new Item[]{
                new Item("dagger", 10, 5)
        };

        GildedRose app = new GildedRose(items);
        app.updateQuality();
        assertEquals(4, app.items[0].quality);
    }

    @Test
    void testQualityDecreaseAfterSellInDateExpired() {

        Item[] items = new Item[]{
                new Item("dagger", 1, 10)
        };

        GildedRose app = new GildedRose(items);
        app.updateQuality();
        app.updateQuality();
        app.updateQuality();

        assertEquals(5, app.items[0].quality);
    }

    @Test
    void testMaxQuality() {

        Item[] items = new Item[]{
                new Item("Aged Brie", 1, 50)
        };

        GildedRose app = new GildedRose(items);
        app.updateQuality();
        app.updateQuality();
        app.updateQuality();

        assertEquals(50, app.items[0].quality);
    }

    @Test
    void testSellInReduction() {

        Item[] items = new Item[]{
                new Item("dagger", 10, 20)
        };

        GildedRose app = new GildedRose(items);
        app.updateQuality();
        app.updateQuality();
        app.updateQuality();

        assertEquals(7, app.items[0].sellIn);
    }

    @Test
    void testQualityIsNotNegative() {

        Item[] items = new Item[]{
                new Item("dagger", 1, 1)
        };

        GildedRose app = new GildedRose(items);
        app.updateQuality();
        app.updateQuality();
        app.updateQuality();

        assertEquals(0, app.items[0].quality);
    }

    @Test
    void testAgedBrieThreeDaysQualityIncrease() {

       Item[] items = new Item[]{
                new Item("Aged Brie", 11, 5)
        };
        GildedRose app = new GildedRose(items);

        app.updateQuality();
        app.updateQuality();
        app.updateQuality();

        assertEquals(8, app.items[0].quality);
    }

    @Test
    void testBrieAfterExpiration(){
        Item[] items = new Item[]{
                new Item("Aged Brie", 1, 20)
        };
        GildedRose app = new GildedRose(items);
        app.updateQuality();
        app.updateQuality();
        assertEquals(22, app.items[0].quality);
    }


    @Test
    void testBackStageQualityIncrease(){
        Item[]  items = new Item[]{
                new Item("Backstage passes to a TAFKAL80ETC concert", 10, 10)
        };
        GildedRose app = new GildedRose(items);
        app.updateQuality();
        app.updateQuality();
        assertEquals(14, app.items[0].quality);
    }

    @Test
    void testBackStageMaxQuality(){
        Item[]  items = new Item[]{
                new Item("Backstage passes to a TAFKAL80ETC concert", 10, 50)
        };
        GildedRose app = new GildedRose(items);
        app.updateQuality();
        app.updateQuality();
        assertEquals(50, app.items[0].quality);
    }

    @Test
    void testBackStageAfterExpiration(){
        Item[]  items = new Item[]{
                new Item("Backstage passes to a TAFKAL80ETC concert", 1, 20)
        };
        GildedRose app = new GildedRose(items);
        app.updateQuality();
        app.updateQuality();
        assertEquals(0, app.items[0].quality);
    }

    @Test
    void testConjuredItemIsReducedTwiceAsFast(){
        Item[]  items = new Item[]{
                new Item("conjured apple pie", 20, 20)
        };

        GildedRose app = new GildedRose(items);

        app.updateQuality();
        app.updateQuality();

        assertEquals(16, app.items[0].quality);
    }

    @Test
    void testSulfaras(){
        Item[]  items = new Item[]{
                new Item("sulfuras, hand of ragnaros", 20, 80)
        };

        GildedRose app = new GildedRose(items);

        app.updateQuality();
        app.updateQuality();

        assertEquals(80, app.items[0].quality);
    }
}
