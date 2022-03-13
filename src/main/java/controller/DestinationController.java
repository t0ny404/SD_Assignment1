package controller;

import service.DestinationService;
import service.InvalidDestinationException;

import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;

public class DestinationController {

    private final DestinationService destinationService = new DestinationService();

    public TableModel getTable() {
        return new DefaultTableModel(destinationService.getAllPackages(), destinationService.getColumns());
    }

    public String add(String city, String country, String hotel, Boolean safe) {
        try {
            destinationService.insert(city, country, hotel, safe);
        } catch (InvalidDestinationException e) {
            return e.getMessage();
        }
        return "Success!";
    }

    public void delete(Integer id) {
        destinationService.delete(id);
    }
}
