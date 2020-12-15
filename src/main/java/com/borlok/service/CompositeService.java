package com.borlok.service;

import java.util.ArrayList;
import java.util.List;

public class CompositeService {
    private List<Service<?>> services = new ArrayList<>();

    public CompositeService addService(Service<?> service) {
        services.add(service);
        return this;
    }

    public Service<?> getService (Service<?> service) {
        return services.stream().filter(x -> x.toString().equals(service.toString())).findFirst().orElse(null);
    }
}
