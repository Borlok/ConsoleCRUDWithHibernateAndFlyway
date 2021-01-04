package com.borlok.controller;

import java.util.ArrayList;
import java.util.List;

public class CompositeController {
    private List<Controller<?>> controllers = new ArrayList<>();

    public CompositeController addController(Controller<?> controller) {
        controllers.add(controller);
        return this;
    }

    public Controller<?> getController(Controller<?> controller) {
        return controllers.stream().filter(x -> x.toString().equals(controller.toString())).findFirst().orElse(null);
    }
}
