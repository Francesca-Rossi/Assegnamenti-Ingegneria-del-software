package it.unipr.ingegneria.utils;

import it.unipr.ingegneria.repo.WineRepository;

public class Toolbox {
    public static WineRepository wineRepo;

    public Toolbox(){
        Toolbox.wineRepo = new WineRepository();
    }
}
