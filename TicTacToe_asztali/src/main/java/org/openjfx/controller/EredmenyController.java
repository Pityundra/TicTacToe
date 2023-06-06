package org.openjfx.controller;

import org.openjfx.dao.EredmenyDAO;
import org.openjfx.dao.EredmenyDaoImpl;

public class EredmenyController {
    private EredmenyDAO dao = new EredmenyDaoImpl();
    private static EredmenyController single_instance = null;

    private EredmenyController() {

    }

    public static EredmenyController getInstance(){
        if(single_instance == null){
            single_instance = new EredmenyController();
        }
        return single_instance;
    }

    public boolean addResult(String result) {
        return dao.addResult(result);
    }

}
