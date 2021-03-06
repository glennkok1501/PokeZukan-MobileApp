package com.gmail.gk_dev_software.pokezukan.Model;

import java.io.Serializable;

public class MoveBrief implements Serializable {
    private String name;
    private String type;
    private String link;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link;
    }

    public MoveBrief() {
    }

    public MoveBrief(String name, String type, String link) {
        this.name = name;
        this.type = type;
        this.link = link;
    }
}
