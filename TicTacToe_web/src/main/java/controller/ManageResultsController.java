package controller;

import dao.ManageResultsDAO;
import dao.ManageResultsDAOImpl;

public class ManageResultsController {

    private ManageResultsDAO dao = new ManageResultsDAOImpl();
    private static ManageResultsController single_instance = null;

    private ManageResultsController() {

    }

    public static ManageResultsController getInstance(){
        if(single_instance == null){
            single_instance = new ManageResultsController();
        }
        return single_instance;
    }

    public boolean addResult(String result) {
        return dao.addResult(result);
    }
}
