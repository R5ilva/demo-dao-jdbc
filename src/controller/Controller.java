package controller;

import model.facade.SistemaVeterinarioFacade;

public class Controller implements IController {
    protected final SistemaVeterinarioFacade facade;
    
    public Controller() {
        this.facade = new SistemaVeterinarioFacade();
    }
    
    @Override
    public SistemaVeterinarioFacade getFacade() {
        return this.facade;
    }
}
