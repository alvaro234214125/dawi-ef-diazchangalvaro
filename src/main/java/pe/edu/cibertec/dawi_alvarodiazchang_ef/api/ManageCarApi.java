package pe.edu.cibertec.dawi_alvarodiazchang_ef.api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import pe.edu.cibertec.dawi_alvarodiazchang_ef.dto.CarDetailDto;
import pe.edu.cibertec.dawi_alvarodiazchang_ef.dto.CarDto;
import pe.edu.cibertec.dawi_alvarodiazchang_ef.response.*;
import pe.edu.cibertec.dawi_alvarodiazchang_ef.service.ManageService;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/manage-car")
public class ManageCarApi {

    @Autowired
    ManageService manageService;

    @GetMapping("/all")
    public FindCarsResponse findCars() {
        try {
            List<CarDto> cars = manageService.getAllCars();
            if (!cars.isEmpty())
                return new FindCarsResponse("01",null, cars);
            else
                return new FindCarsResponse("02","Cars not found", null);

        } catch (Exception e) {
            e.printStackTrace();
            return new FindCarsResponse("99","An error ocurred", null);
        }
    }

    @GetMapping("/detail")
    public FindCarResponse findCar(@RequestParam(value ="id", defaultValue = "0") String id){
        try {
            Optional< CarDetailDto > optional = manageService.getCarById(Integer.parseInt(id));
            return optional.map(car -> new FindCarResponse("01", null, car)
            ).orElse(
                    new FindCarResponse("02", "car not found", null)
            );
        } catch (Exception e) {
            e.printStackTrace();
            return new FindCarResponse("99","An error ocurred", null);
        }
    }

    @PutMapping("/update")
    public UpdateCarResponse updateCar(@RequestBody CarDto carDto){
        try {
            if(manageService.updateCar(carDto))
                return new UpdateCarResponse("01",null);
            else
                return new UpdateCarResponse("02","car not found");
        } catch (Exception e) {
            e.printStackTrace();
            return new UpdateCarResponse("99", "An error ocurred");
        }
    }

    @DeleteMapping("/delete/{id}")
    public DeleteCarResponse deleteCar(@PathVariable String id){
        try {
            if(manageService.deleteCarById(Integer.parseInt(id)))
                return new DeleteCarResponse("01", null);
            else
                return new DeleteCarResponse("02", "car not found");
        } catch (Exception e) {
            e.printStackTrace();
            return new DeleteCarResponse("99", "An error ocurred");
        }
    }

    @PostMapping("/create")
    public CreateCarResponse createCar(@RequestBody CarDetailDto carDetailDto){
        try {
            if(manageService.addCar(carDetailDto))
                return new CreateCarResponse("01",null);
            else
                return new CreateCarResponse("02","car already exists");
        } catch (Exception e) {
            e.printStackTrace();
            return new CreateCarResponse("99", "An error ocurred");
        }
    }

}
