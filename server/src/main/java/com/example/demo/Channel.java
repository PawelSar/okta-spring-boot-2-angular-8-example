package com.example.demo;

import java.util.ArrayList;
import java.util.List;

public class Channel {
    private String link;
    private String pubDate;
    private String title;
    private String description;
    private Item item;
    List<Item> items = new ArrayList<Item>();
    public String getLink() {
        return link;
    }

    public void setLink(String value) {
        this.link = value;
    }

    public String getPubDate() {
        return pubDate;
    }

    public void setPubDate(String value) {
        this.pubDate = value;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String value) {
        this.title = value;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String value) {
        this.description = value;
    }

    public List<Item> getItem() { return items; }
    public void setItem(Item value) {

        items.add(value);

        this.item = value; }
}
