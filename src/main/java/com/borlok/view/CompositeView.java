package com.borlok.view;

import java.util.ArrayList;
import java.util.List;

public class CompositeView {
    private final List<View> views = new ArrayList<>();

    public CompositeView addView(View view) {
        views.add(view);
        return this;
    }

    public void getAllView() {
        for (int i = 0; i < views.size(); i++) {
            System.out.println((i + 1) + ": " + views.get(i));
        }
    }

    public List<View> getListOfView() {
        return views;
    }
}
