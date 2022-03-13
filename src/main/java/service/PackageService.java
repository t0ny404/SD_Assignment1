package service;

import model.Package;
import repository.PackageRepository;

import java.sql.Date;

public class PackageService {

    private final PackageRepository packageRepository = new PackageRepository();
    private final Validator validator = new Validator();

    public Object[][] getAllPackages() {
        return packageRepository.findAll().stream().map(Package::getPackage).toArray(Object[][]::new);
    }

    public Object[][] getDestPackages(Integer id) {
        return packageRepository.findByDest(id).stream().map(Package::getPackageAgency).toArray(Object[][]::new);
    }

    public Object[][] getAvailablePackages() {
        return packageRepository.findAvailable().stream().map(Package::getPackage).toArray(Object[][]::new);
    }

    public String[] getColumns() {
        return new String[]{"Name", "price", "Start date", "End date", "Details",
                "Hotel", "City", "Country", "Status"};
    }

    public String[] getColumnsAgency() {
        return new String[]{"id", "Name", "price", "Start date", "End date", "Details", "Limit", "Status"};
    }

    public void insert(Integer destId, String name, String price, String limit, String details, String start, String end) throws InvalidDestinationException {
        validator.validatePackage(destId, name, price, limit, details);
        Date startD = validator.validateDate(start);
        Date endD = validator.validateDate(end);

        packageRepository.insert(new Package(name, Double.parseDouble(price), Integer.parseInt(limit), details,
                startD, endD), destId);
    }

    public void delete(Integer id) {
        packageRepository.delete(id);
    }

    public void edit(Integer id, Integer destId, String name, String price, String limit, String details, String start, String end) throws InvalidDestinationException {
        validator.validatePackage(destId, name, price, limit, details);
        Date startD = validator.validateDate(start);
        Date endD = validator.validateDate(end);

        packageRepository.delete(id);
        packageRepository.insert(new Package(name, Double.parseDouble(price), Integer.parseInt(limit), details,
                startD, endD), destId);
    }
}
