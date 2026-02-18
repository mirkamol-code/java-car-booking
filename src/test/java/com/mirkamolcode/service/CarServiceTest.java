package com.mirkamolcode.service;

import com.mirkamolcode.dao.CarDAO;
import com.mirkamolcode.model.Car;
import com.mirkamolcode.model.enums.Brand;
import com.mirkamolcode.model.enums.ResponseMessage;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.*;

import static com.mirkamolcode.model.enums.ResponseMessage.*;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.BDDMockito.given;
import static org.mockito.BDDMockito.then;

@ExtendWith(MockitoExtension.class)
class CarServiceTest {

    @Mock
    private CarDAO carDAO;

    @InjectMocks
    private CarService underTest;

    @Test
    void shouldGetAllCars() {
        // given
        List<Car> expected = new ArrayList<>(Arrays.asList(
                new Car("1111", 20.0, Brand.AUDI, false),
                new Car("2222", 10.0, Brand.MERCEDES, true)
        ));
        given(carDAO.selectAllCars()).willReturn(expected);

        // when
        List<Car> actual = underTest.getAllCars();

        // then
        assertThat(actual).isEqualTo(expected);
    }

    @Test
    void shouldReturnEmptyListWhenCarListIsEmpty() {
        // given
        given(carDAO.selectAllCars()).willReturn(new ArrayList<>());

        // then
        assertThat(underTest.getAllCars()).isEmpty();
    }

    @Test
    void shouldGetElectricCars() {
        // given
        List<Car> expected = new ArrayList<>(Arrays.asList(
                new Car("2222", 10.0, Brand.MERCEDES, true),
                new Car("3333", 15.0, Brand.MERCEDES, true)
        ));
        given(carDAO.selectAllCars()).willReturn(expected);
        // when
        List<Car> actual = underTest.getElectricCars();
        // then
        assertThat(actual).isEqualTo(expected);
    }

    @Test
    void shouldThrowExceptionWhenElectricCarListIsEmpty() {
        // given
        given(underTest.getElectricCars()).willReturn(new ArrayList<>());

        // then
        assertThat(underTest.getAllCars()).isEmpty();
    }

    @Test
    void shouldGetCarByRegNumber() {
        // given
        Car expected = new Car("2222", 10.0, Brand.MERCEDES, true);
        given(carDAO.selectCarByRegNumber(expected.getRegNumber())).willReturn(Optional.of(expected));
        // when
        Car actual = underTest.getCarByRegNumber(expected.getRegNumber());
        // then
        assertThat(actual).isEqualTo(expected);
    }

    @Test
    void shouldThrowWhenCarNotFound() {
        // given
        given(carDAO.selectCarByRegNumber(anyString())).willReturn(Optional.empty());
        // when
        assertThatThrownBy(() -> underTest.getCarByRegNumber(anyString()))
                .hasMessageContaining(ResponseMessage.CAR_NOT_FOUND.getMessage())
                .isInstanceOf(NoSuchElementException.class);
    }


    @Test
    void shouldDeleteCar() {
        // given
        Optional<Car> expected = Optional.of(new Car("2222", 10.0, Brand.MERCEDES, true));
        String registrationNumber = expected.get().getRegNumber();
        given(carDAO.selectCarByRegNumber(registrationNumber)).willReturn(expected);
        given(carDAO.removeCarByRegNumber(registrationNumber)).willReturn(true);
        // when
        underTest.deleteCar(registrationNumber);
        // then
        then(carDAO).should().removeCarByRegNumber(registrationNumber);
        then(carDAO).shouldHaveNoMoreInteractions();
        assertThat(carDAO.removeCarByRegNumber(registrationNumber)).isTrue();
    }

    @Test
    void shouldThrowWhenRegNumberIsNotFoundToDeleteCar() {
        // given
        given(carDAO.selectCarByRegNumber(anyString())).willReturn(Optional.empty());
        given(carDAO.removeCarByRegNumber(anyString())).willReturn(false);
        // then
        then(carDAO).shouldHaveNoInteractions();
        assertThat(carDAO.removeCarByRegNumber(anyString())).isFalse();
        assertThatThrownBy(() -> underTest.deleteCar(anyString()))
                .hasMessageContaining(CAR_NOT_FOUND.getMessage())
                .isInstanceOf(NoSuchElementException.class);
    }
}