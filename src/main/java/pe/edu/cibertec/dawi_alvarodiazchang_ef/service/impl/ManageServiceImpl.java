package pe.edu.cibertec.dawi_alvarodiazchang_ef.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pe.edu.cibertec.dawi_alvarodiazchang_ef.dto.CarDetailDto;
import pe.edu.cibertec.dawi_alvarodiazchang_ef.dto.CarDto;
import pe.edu.cibertec.dawi_alvarodiazchang_ef.entity.Car;
import pe.edu.cibertec.dawi_alvarodiazchang_ef.repository.CarRepository;
import pe.edu.cibertec.dawi_alvarodiazchang_ef.service.ManageService;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class ManageServiceImpl implements ManageService {

    @Autowired
    CarRepository carRepository;

    @Override
    public List<CarDto> getAllCars() throws Exception {
        List<CarDto> cars = new ArrayList<CarDto>();
        Iterable<Car> iterable = carRepository.findAll();
        iterable.forEach(car -> {
            cars.add(new CarDto(car.getCarId(),
                    car.getOwnerName(),
                    car.getOwnerContact(),
                    car.getLicensePlate()));
        });
        return cars;
    }

    @Override
    public Optional<CarDetailDto> getCarById(int id) throws Exception{
        Optional<Car> optional = carRepository.findById(id);
        return optional.map(user -> new CarDetailDto(user.getCarId(),
                user.getMake(),
                user.getModel(),
                user.getYear(),
                user.getVin(),
                user.getLicensePlate(),
                user.getOwnerName(),
                user.getOwnerContact(),
                user.getPurchaseDate(),
                user.getMileage(),
                user.getEngineType(),
                user.getColor(),
                user.getInsuranceCompany(),
                user.getInsurancePolicyNumber(),
                user.getRegistrationExpirationDate(),
                user.getServiceDueDate()));
    }

    @Override
    public boolean updateCar(CarDto carDto) throws Exception {
        Optional<Car> optional = carRepository.findById(carDto.carId());
        return optional.map(car -> {
            car.setOwnerName(carDto.ownerName());
            car.setOwnerContact(carDto.ownerContact());
            car.setLicensePlate(carDto.licensePlate());
            carRepository.save(car);
            return true;
        }).orElse(false);
    }

    @Override
    public boolean deleteCarById(int id) throws Exception {
        Optional<Car> optional = carRepository.findById(id);
        return optional.map(car -> {
            carRepository.delete(car);
            return true;
        }).orElse(false);
    }

    @Override
    public boolean addCar(CarDetailDto carDetailDto) throws Exception {
        Optional<Car> optional = carRepository.findById(carDetailDto.carId());
        if (optional.isPresent()) {
            return false;
        }else {
            Car car = new Car();
            car.setOwnerName(carDetailDto.ownerName());
            car.setOwnerContact(carDetailDto.ownerContact());
            car.setLicensePlate(carDetailDto.licensePlate());
            car.setMake(carDetailDto.make());
            car.setModel(carDetailDto.model());
            car.setYear(carDetailDto.year());
            car.setVin(carDetailDto.vin());
            car.setLicensePlate(carDetailDto.licensePlate());
            car.setOwnerName(carDetailDto.ownerName());
            car.setOwnerContact(carDetailDto.ownerContact());
            car.setPurchaseDate(carDetailDto.purchaseDate());
            car.setMileage(carDetailDto.mileage());
            car.setEngineType(carDetailDto.engineType());
            car.setColor(carDetailDto.color());
            car.setInsuranceCompany(carDetailDto.insuranceCompany());
            car.setInsurancePolicyNumber(carDetailDto.insurancePolicyNumber());
            car.setRegistrationExpirationDate(carDetailDto.registrationExpirationDate());
            car.setServiceDueDate(carDetailDto.serviceDueDate());
            carRepository.save(car);
            return true;
        }
    }
}
