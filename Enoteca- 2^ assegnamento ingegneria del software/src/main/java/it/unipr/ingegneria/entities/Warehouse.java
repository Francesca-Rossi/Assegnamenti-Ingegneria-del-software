package it.unipr.ingegneria.entities;

import it.unipr.ingegneria.entities.api.IObservable;
import it.unipr.ingegneria.entities.exception.AvailabilityException;
import it.unipr.ingegneria.entities.exception.RequiredValueException;
import it.unipr.ingegneria.repo.WineRepository;
import it.unipr.ingegneria.utils.Params;
import it.unipr.ingegneria.builders.WineBuilder;
import org.apache.log4j.Logger;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class Warehouse implements IObservable<WineShop> {
    private WineRepository wineRepo = WineRepository.getInstance();
    private static final Logger logger = Logger.getLogger(Warehouse.class);
    private List<WineShop> observers = new ArrayList<>();

    public void add(Map<Params, Object> elements) throws RequiredValueException {
        isValidValues(elements);
        String name = (String) elements.get(Params.NAME);
        Integer quantity = Integer.parseInt((String) elements.get(Params.QTY));
        logger.info("Add in Warehouse Wine");
        Date now = new Date();
        List<Wine> items = IntStream.range(0, quantity)
                .mapToObj(i ->
                        new WineBuilder()
                                .setId(Long.valueOf(i))
                                .setName(name)
                                .setProducer("")
                                .setYear(now)
                                .setTechNotes("")
                                .build())
                .collect(Collectors.toList());

        this.wineRepo.add(items
                .stream()
                .limit(quantity)
                .toArray(n -> new Wine[n]));
        updateSubscribers();
    }


    public void remove(Map<Params, Object> elements) throws RequiredValueException, AvailabilityException {
        isValidValues(elements);
        String name = (String) elements.get(Params.NAME);
        Integer quantity = Integer.parseInt((String) elements.get(Params.QTY));
        logger.info("Check in Warehouse for availability of Wine");
        List<Wine> foundedWines = this.wineRepo.findByName(name);
        int sizes = foundedWines.size();


        if (sizes == 0 || sizes < quantity)
            throw new AvailabilityException();

        if (quantity <= sizes) {
            logger.info("Wine in Warehouse are available");
            this.wineRepo
                    .remove(foundedWines
                            .stream()
                            .limit(quantity)
                            .toArray(n -> new Wine[n]));
        }

    }

    public List<Wine> getAvailableWines(){
        return this.wineRepo.getItems();
    }

    private void isValidValues(Map<Params, Object> elements) throws RequiredValueException {
        String name = (String) elements.get(Params.NAME);
        // ToDo: RequiredValueException
        if (name == null || name.isEmpty())
            throw new RequiredValueException(Params.NAME.name());

        Integer quantity = Integer.parseInt((String) elements.get(Params.QTY));
        if (quantity == null || quantity < 0)
            throw new RequiredValueException(Params.QTY.name());
    }


    @Override
    public void addObserver(WineShop wineShop) {
        this.observers.add(wineShop);
    }

    @Override
    public void removeObserver(WineShop wineShop) {
        this.observers.remove(wineShop);
    }

    public void updateSubscribers() {
        for (WineShop observer : this.observers) {
            String messageUpdated = "UPDATED";
            observer.update(messageUpdated);
        }
    }
}
