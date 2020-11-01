package it.unipr.ingegneria.repo;

import it.unipr.ingegneria.entities.Wine;

import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
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

    public List<Wine> findByYear(final int year) {
        return items.stream()
                .filter(i -> workWithDate(i.getYear()) == year)
                .collect(Collectors.toList());
    }

    private int workWithDate(Date date) {
        return LocalDate.parse(new SimpleDateFormat("yyyy-MM-dd").format(date)).getYear();
    }

}
