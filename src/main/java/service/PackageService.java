package service;

import model.Package;
import model.User;
import repository.DestinationRepository;
import repository.PackageRepository;
import service.Utils.InvalidDestinationException;
import service.Utils.InvalidFilterException;
import service.Utils.Validator;

import javax.persistence.PersistenceException;
import javax.persistence.RollbackException;
import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

public class PackageService {

    private final PackageRepository packageRepository = new PackageRepository();
    private final Validator validator = new Validator();

    public Object[][] filterPackages(String by, String val) throws InvalidFilterException {
        switch (by) {
            case "Destination" -> {
                List<Package> packages = new ArrayList<>();
                for (Integer dest : new DestinationRepository().findByLoc(val)) {
                    packages.addAll(packageRepository.findByDest(dest));
                }
                return packages.stream().map(Package::getPackage).toArray(Object[][]::new);
            }
            case "Price" -> {
                double price;
                try {
                    price = Double.parseDouble(val);
                } catch (NumberFormatException e) {
                    throw new InvalidFilterException();
                }
                return packageRepository.findByPrice(price).stream().map(Package::getPackage).toArray(Object[][]::new);
            }
            case "Period" -> {
                int period;
                try {
                    period = Integer.parseInt(val);
                } catch (NumberFormatException e) {
                    throw new InvalidFilterException();
                }
                return packageRepository.findAvailable().stream().filter(p -> p.getPeriod() <= period).map(Package::getPackage).toArray(Object[][]::new);
            }
        }
        return null;
    }

    public Object[][] getAllPackages() {
        return packageRepository.findAll().stream().map(Package::getPackageAgency).toArray(Object[][]::new);
    }

    public Object[][] getDestPackages(Integer id) {
        return packageRepository.findByDest(id).stream().map(Package::getPackageAgency).toArray(Object[][]::new);
    }

    public Object[][] getAvailablePackages() {
        return packageRepository.findAvailable().stream().map(Package::getPackage).toArray(Object[][]::new);
    }

    public String[] getColumns() {
        return new String[]{"Name", "price", "Start date", "End date", "Details",
                "Hotel", "City", "Country"};
    }

    public String[] getColumnsAgency() {
        return new String[]{"id", "Name", "price", "Start date", "End date", "Details", "Limit", "Status"};
    }

    public void insert(Integer destId, String name, String price, String limit, String details, String start, String end) throws InvalidDestinationException {
        validator.validatePackage(name, price, limit, details);
        Date startD = validator.validateDate(start);
        Date endD = validator.validateDate(end);
        if (startD.after(endD))
            throw new InvalidDestinationException("Start date can't be after end date!");

        try {
            packageRepository.insert(new Package(name, Double.parseDouble(price), Integer.parseInt(limit), details,
                startD, endD), destId);
        } catch (RollbackException e) {
            throw new InvalidDestinationException("Package already exists!");
        }

    }

    public void delete(Integer id) {
        packageRepository.delete(id);
    }

    public void edit(Integer id, String name, String price, String limit, String details, String start, String end) throws InvalidDestinationException {
        validator.validatePackage(name, price, limit, details);
        Date startD = validator.validateDate(start);
        Date endD = validator.validateDate(end);
        if (startD.after(endD))
            throw new InvalidDestinationException("Start date can't be after end date!");

        try {
            packageRepository.edit(new Package(id, name, Double.parseDouble(price), startD, endD, details, Integer.parseInt(limit)));
        } catch (PersistenceException e) {
            throw new InvalidDestinationException("Package already exists!");
        }
    }
}
