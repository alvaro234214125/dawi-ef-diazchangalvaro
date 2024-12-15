package pe.edu.cibertec.dawi_alvarodiazchang_ef.response;

import pe.edu.cibertec.dawi_alvarodiazchang_ef.dto.CarDto;

public record FindCarsResponse(String error,
                               String code,
                               Iterable<CarDto> cars) {
}
