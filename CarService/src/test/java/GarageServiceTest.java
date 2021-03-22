import domain.Car;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.Mockito;
import repository.ActiveCarRepository;
import repository.FixedCarRepository;

import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class GarageServiceTest {

    private final ActiveCarRepository activeCarRepository = mock(ActiveCarRepository.class);
    private final FixedCarRepository fixedCarRepository = mock(FixedCarRepository.class);
    private final GarageService garageService = new GarageService(activeCarRepository, fixedCarRepository);
    private final ArgumentCaptor<Car> carArgumentCaptor = ArgumentCaptor.forClass(Car.class);

    @Test
    public void ifNewCarRegisteredForInvalidProductionDate(){
        //given
        String carName = "Fiat";
        String carColor = "czerwony";
        String carProductionDate = "15";
        //when
        boolean isNewCarRegistered = garageService.registerCar(carName, carColor, carProductionDate);
        //then
        assertThat(isNewCarRegistered).isFalse();
        verify(activeCarRepository, times(0)).addCar(any(Car.class));
    }

    @Test
    public void ifNewCarRegisteredForValidProductionDate(){
        //given
        String carName = "Fiat";
        String carColor = "czerwony";
        String carProductionDate = "2015";
        //when
        boolean isNewCarRegistered = garageService.registerCar(carName, carColor, carProductionDate);
        //then
        assertThat(isNewCarRegistered).isTrue();
        verify(activeCarRepository).addCar(carArgumentCaptor.capture());
        Car capturedCar = carArgumentCaptor.getValue();
        assertAll(
                ()-> assertEquals(capturedCar.getName(), carName),
                ()-> assertEquals(capturedCar.getColor(), carColor),
                ()-> assertEquals(capturedCar.getProductionDate(), carProductionDate));
        verify(activeCarRepository, times(1)).addCar(any(Car.class));
    }

    @Test
    public void areAllRegisteredCarsReturned(){
        //given
        List<Car> cars = new ArrayList<>();
        cars.add(new Car("Fiat","czerwony","2020"));
        cars.add(new Car("Toyota", "biały", "2018"));
        cars.add(new Car("Peugot", "złoty", "2021"));
        when(activeCarRepository.getAllCars()).thenReturn(cars);
        //when
        List<Car> returnedCars = garageService.getAllActiveCars();
        //then
        assertThat(returnedCars).hasSize(cars.size()).containsAll(cars);
    }

    @Test
    public void isCarFixed(){
        //given
        Car carForFix = new Car("Fiat", "czerwony", "2000");
        //when
        boolean isCarFixed = garageService.fixCar(carForFix);
        //then
        assertThat(isCarFixed).isTrue();
        verify(activeCarRepository).deleteCar(carArgumentCaptor.capture());
        Car carForDelete = carArgumentCaptor.getValue();
        assertThat(carForDelete).isEqualTo(carForFix);

        verify(fixedCarRepository).addCar(carArgumentCaptor.capture());
        Car fixedCar = carArgumentCaptor.getValue();
        assertAll(
                ()-> assertEquals(fixedCar, carForFix),
                ()-> assertTrue(fixedCar.getFixed()));
    }

}