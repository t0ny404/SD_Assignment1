package controller;

import service.Utils.InvalidDestinationException;
import service.PackageService;
import service.Utils.InvalidFilterException;

import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;

public class PackageController {

    private final PackageService packageService = new PackageService();

    public TableModel getCustomerTable() {
        return new DefaultTableModel(packageService.getAvailablePackages(), packageService.getColumns());
    }

    public TableModel getAgencyTable(Integer id) {
        return new DefaultTableModel(packageService.getDestPackages(id), packageService.getColumnsAgency());
    }

    public TableModel getAllTable() {
        return new DefaultTableModel(packageService.getAllPackages(), packageService.getColumnsAgency());
    }

    public String add(Integer destId, String name, String price, String limit,
                      String details, String start, String end) {

        try {
            packageService.insert(destId, name, price, limit, details, start, end);
        } catch (InvalidDestinationException e) {
            return e.getMessage();
        }
        return "Success!";
    }

    public void delete(Integer id) {
        packageService.delete(id);
    }

    public String edit(Integer id, String name, String price, String limit,
                     String details, String start, String end) {
        try {
        packageService.edit(id, name, price, limit, details, start, end);
        } catch (InvalidDestinationException e) {
            return e.getMessage();
        }
        return "Success!";
    }

    public TableModel filter(String by, String val) {
        if (by == null || val == null || val.equals(""))
            return getCustomerTable();
        try {
            return new DefaultTableModel(packageService.filterPackages(by, val), packageService.getColumns());
        } catch (InvalidFilterException e) {
            return new DefaultTableModel(new Object[][]{{e.getMessage()}}, new String[]{"error"});
        }
    }
}
