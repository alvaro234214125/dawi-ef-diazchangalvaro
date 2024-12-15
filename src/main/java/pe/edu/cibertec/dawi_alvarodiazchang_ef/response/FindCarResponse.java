package pe.edu.cibertec.dawi_alvarodiazchang_ef.response;

import pe.edu.cibertec.dawi_alvarodiazchang_ef.dto.CarDetailDto;

public record FindCarResponse(String error,
                              String code,
                              CarDetailDto car) {
}
