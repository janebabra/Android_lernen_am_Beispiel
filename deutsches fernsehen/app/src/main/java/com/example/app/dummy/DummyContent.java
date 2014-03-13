package com.example.app.dummy;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Helper class for providing sample text for user interfaces created by
 * Android template wizards.
 * <p>
 * TODO: Replace all uses of this class before publishing your app.
 */
public class DummyContent {

    /**
     * An array of sample (dummy) items.
     */
    public static List<DummyItem> ITEMS = new ArrayList<DummyItem>();

    /**
     * A map of sample (dummy) items, by ID.
     */
    public static Map<String, DummyItem> ITEM_MAP = new HashMap<String, DummyItem>();

    static {
        addItem(new DummyItem("1", "Das Erste" , "http://live.daserste.de/"));
        addItem(new DummyItem("2", "ZDF", "http://webapp.zdf.de/mediathek"));
        addItem(new DummyItem("3", "Sat1", "http://www.sat1.de"));
        addItem(new DummyItem("4", "RTL Now", "http://www.rtl.de/"));
        addItem(new DummyItem("5", "Kabel1", "http://www.kabeleins.de"));
        addItem(new DummyItem("6", "n-tv", "http://www.n-tv.de"));
        addItem(new DummyItem("7", "tagesschau", "http://www.tagesschau.de/multimedia/livestreams/"));
        addItem(new DummyItem("8", "VOX", "http://www.vox.de"));
        addItem(new DummyItem("9", "n-tv", "http://www.n-tv.de"));

    }

    private static void addItem(DummyItem item) {
        ITEMS.add(item);
        ITEM_MAP.put(item.id, item);
    }

    /**
     * A dummy item representing a piece of text.
     */
    public static class DummyItem {
        public String id;
        public String title;
        public String website;


        public DummyItem(String id, String title, String content ) {
            this.id = id;
            this.title = title;
            this.website = content;

        }

        public String toString()
        {
            return title;
        }
    }
}
