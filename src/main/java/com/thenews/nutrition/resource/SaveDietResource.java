package com.thenews.nutrition.resource;

import javax.persistence.Column;
import javax.persistence.Lob;
import javax.validation.constraints.NotNull;

public class SaveDietResource {


    @Column(unique = true)
    @NotNull
    private String title;

    @NotNull
    @Lob
    private String description;

    public String getTitle() {
        return title;
    }

    public SaveDietResource setTitle(String title) {
        this.title = title;
        return this;
    }

    public String getDescription() {
        return description;
    }

    public SaveDietResource setDescription(String description) {
        this.description = description;
        return this;
    }
}
