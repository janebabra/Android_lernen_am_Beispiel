package com.example.app.dummy;

import com.example.app.R;

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

        addItem(new DummyItem("1", "Mein Haus" , " Mein Haus", R.drawable.myhouse));
        addItem(new DummyItem("2", "Mein Auto", "Mein Auto", R.drawable.mycar));
        addItem(new DummyItem("3", "Mein Boot", "Mein Boot", R.drawable.myboat));
        addItem(new DummyItem("4", "Meine Familie", "Meine TV-Familie", R.drawable.myfamily2));
        addItem(new DummyItem("5", "Meine Apps", "Einer der vielen Aps", R.drawable.tagesschau));
        addItem(new DummyItem("6", "Meine Androids", "Einer meiner Androids", R.drawable.samsung));
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
        public String text;
        public int image;

        public DummyItem(String id, String title, String content, int image) {
            this.id = id;
            this.title = title;
            this.text = content;
            this.image = image;
        }

        public String toString()
        {
            return title;
        }
    }
}
