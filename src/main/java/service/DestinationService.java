package service;

import model.Destination;
import repository.DestinationRepository;
import service.Utils.InvalidDestinationException;
import service.Utils.Validator;

public class DestinationService {

    private final DestinationRepository destinationRepository = new DestinationRepository();
    private final Validator validator = new Validator();

    public Object[][] getAllPackages() {
        return destinationRepository.findAll().stream().map(Destination::getDestination).toArray(Object[][]::new);
    }

    public String[] getColumns() {
        return new String[]{"id", "Hotel", "City", "Country", "Safe"};
    }

    public void insert(String city, String country, String hotel, Boolean safe) throws InvalidDestinationException {
        validator.validateDestination(city, country, hotel, safe);
        destinationRepository.insert(new Destination(city, country, hotel, safe));
    }

    public void delete(Integer id) {
        destinationRepository.delete(id);
    }
}
