package service;

import model.Package;

import repository.PackageRepository;

public class BookingService {

    private final PackageRepository packageRepository = new PackageRepository();

    public void book(String name, UserService userService) {
        Package p = packageRepository.findByName(name).get(0);
        p.addUser(userService.book(p));
        packageRepository.update(p);
    }
}
