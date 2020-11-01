package it.unipr.ingegneria.repo;

import it.unipr.ingegneria.entities.Wine;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import org.apache.log4j.Logger;

public class WineRepository implements CommonOperations<Wine> {
    private List<Wine> items;
    private static WineRepository INSTANCE;

    private WineRepository() {
        this.items = new ArrayList<>();

    }

    public static synchronized WineRepository getInstance() {
        if (INSTANCE == null) {
            INSTANCE = new WineRepository();
        }
        return INSTANCE;
    }

    Logger logger = Logger.getLogger(WineRepository.class);


    public void add(Wine... items) {
        this.items.addAll(Arrays.asList(items));
    }

    public void remove(Wine... items) {
        this.items.removeAll(Arrays.asList(items));
    }

    public List<Wine> getItems() {
        return items;
    }

    public List<Wine> findByName(final String name) {
        return items.stream()
                .filter(i -> i.getName().equals(name))
                .collect(Collectors.toList());
    }

}
