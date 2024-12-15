package pe.edu.cibertec.dawi_alvarodiazchang_ef.service;

import pe.edu.cibertec.dawi_alvarodiazchang_ef.dto.CarDetailDto;
import pe.edu.cibertec.dawi_alvarodiazchang_ef.dto.CarDto;

import java.util.List;
import java.util.Optional;

public interface ManageService {
    List<CarDto> getAllCars() throws Exception;
    Optional<CarDetailDto> getCarById(int id) throws Exception;
    boolean updateCar(CarDto carDto) throws Exception;
    boolean deleteCarById(int id) throws Exception;
    boolean addCar(CarDetailDto carDetailDto) throws Exception;
}
