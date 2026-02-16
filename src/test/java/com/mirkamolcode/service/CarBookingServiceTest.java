package com.mirkamolcode.service;

import com.mirkamolcode.dao.CarBookingDAO;
import com.mirkamolcode.model.Car;
import com.mirkamolcode.model.CarBooking;
import com.mirkamolcode.model.User;
import com.mirkamolcode.model.enums.Brand;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.*;

import static com.mirkamolcode.model.enums.ResponseMessage.*;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.mockito.BDDMockito.then;


@ExtendWith(MockitoExtension.class)
class CarBookingServiceTest {
    @Mock
    private CarBookingDAO carBookingDAO;
    @Mock
    private CarService carService;
    @Mock
    private UserService userService;
    @InjectMocks
    private CarBookingService underTest;
    @Captor
    ArgumentCaptor<CarBooking> carBookingArgumentCaptor;

    @Test
    void shouldGetAllBookings() {
        // given
        User user = new User(UUID.randomUUID(), "James");
        Car car = new Car("2222", 20, Brand.AUDI, false);
        CarBooking carBooking = new CarBooking(user, car);
        List<CarBooking> expected = Arrays.asList(carBooking);
        given(carBookingDAO.selectAllBookings()).willReturn(expected);
        // when
        List<CarBooking> actual = underTest.getAllBookings();
        // then
        assertThat(actual).isNotNull();
        assertThat(actual).isEqualTo(expected);
    }

    @Test
    void shouldReturnEmptyListWhenCarBookingIsNull() {
        // given
        given(carBookingDAO.selectAllBookings()).willReturn(null);
        // then
        assertThat(underTest.getAllBookings()).isEmpty();
    }

    @Test
    void shouldReturnEmptyListCarBookingListIsEmpty() {
        // given
        given(carBookingDAO.selectAllBookings()).willReturn(new ArrayList<>());
        // then
        assertThat(underTest.getAllBookings()).isEmpty();
    }

    @Test
    void shouldBookCar() {
        // given
        User user = new User(UUID.randomUUID(), "James");
        Car car = new Car("2222", 20, Brand.AUDI, false);

        UUID bookingId = UUID.randomUUID();
        given(carService.getCarByRegNumber(car.getRegNumber())).willReturn(car);
        given(userService.getUserById(user.getId())).willReturn(user);
        given(carBookingDAO.saveCarBooking(any())).willReturn(bookingId);
        // when
        underTest.bookCar(car.getRegNumber(), user.getId());
        // then
        then(carService).should().getCarByRegNumber(car.getRegNumber());
        then(userService).should().getUserById(user.getId());

        then(carBookingDAO).should().saveCarBooking(carBookingArgumentCaptor.capture());
        CarBooking actual = carBookingArgumentCaptor.getValue();
        assertThat(actual.getCar()).isEqualTo(car);
        assertThat(actual.getUser()).isEqualTo(user);
        then(carService).should().deleteCar(car.getRegNumber());

        then(carService).shouldHaveNoMoreInteractions();
        then(userService).shouldHaveNoMoreInteractions();
        then(carBookingDAO).shouldHaveNoMoreInteractions();
    }

    @Test
    void shouldThrowWhenUserNotFoundToBookCar() {
        // given
        given(userService.getUserById(any())).willReturn(null);
        // when
        assertThatThrownBy(() -> underTest.bookCar("1111", any()))
                .isInstanceOf(NoSuchElementException.class)
                .hasMessageContaining(UNKNOWN_USER.getMessage());
        then(carBookingDAO).shouldHaveNoInteractions();
    }

    @Test
    void shouldThrowWhenCarNotFoundToBookCar() {
        // given
        User user = new User(UUID.randomUUID(), "Jame");
        given(carService.getCarByRegNumber(any())).willReturn(null);
        given(userService.getUserById(any())).willReturn(user);
        // when
        assertThatThrownBy(() -> underTest.bookCar(any(), user.getId()))
                .isInstanceOf(NoSuchElementException.class)
                .hasMessageContaining(CAR_NOT_FOUND.getMessage());
        then(carBookingDAO).shouldHaveNoInteractions();
        then(carService).shouldHaveNoMoreInteractions();
    }

    @Test
    void getUserBookedCarsByUserId() {
        // given
        User user = new User(UUID.randomUUID(), "John");
        Car bookedCar1 = new Car("1111", 20.0, Brand.AUDI, false);
        Car bookedCar2 = new Car("2222", 40.0, Brand.MERCEDES, true);
        CarBooking carBooking = new CarBooking(user, bookedCar1);
        CarBooking carBooking2 = new CarBooking(user, bookedCar2);
        List<CarBooking> expected = List.of(carBooking, carBooking2);
        given(userService.getUserById(user.getId())).willReturn(user);
        given(underTest.getUserBookedCarsByUserId(user.getId())).willReturn(expected);
        // when
        List<CarBooking> actual = underTest.getUserBookedCarsByUserId(user.getId());
        // then
        assertThat(actual).isEqualTo(expected);
    }

    @Test
    void shouldThrowWhenUserNotFoundToGetUserBookedCars() {
        // given
        given(userService.getUserById(any())).willReturn(null);
        // then
        assertThatThrownBy(() -> underTest.getUserBookedCarsByUserId(any()))
                .isInstanceOf(NoSuchElementException.class)
                .hasMessageContaining(UNKNOWN_USER.getMessage());
    }

    @Test
    void deleteCarBooking() {
        // given
        UUID carBookingId = UUID.randomUUID();
        User user = new User(UUID.randomUUID(), "John");
        Car bookedCar1 = new Car("1111", 20.0, Brand.AUDI, false);
        Car bookedCar2 = new Car("2222", 40.0, Brand.MERCEDES, true);

        CarBooking carBooking = new CarBooking(user, bookedCar1);
        CarBooking carBooking2 = new CarBooking(user, bookedCar2);
        List<CarBooking> carBookings = List.of(carBooking, carBooking2);

        given(carBookingDAO.selectAllBookings()).willReturn(carBookings);
        given(carBookingDAO.getCarBookingById(carBookingId)).willReturn(Optional.of(carBooking));
        given(carBookingDAO.deleteCarBooking(carBookingId)).willReturn(true);
        // when
        boolean actual = underTest.deleteCarBooking(carBookingId);
        // then
        assertThat(actual).isTrue();
        then(carBookingDAO).should().deleteCarBooking(carBookingId);
    }

    @Test
    void shouldThrowWhenBookingListIsEmptyToDeleteCarBooking() {
        // given
        given(carBookingDAO.selectAllBookings()).willReturn(new ArrayList<>());
        // then
        assertThatThrownBy(() -> underTest.deleteCarBooking(UUID.randomUUID()))
                .isInstanceOf(NoSuchElementException.class)
                .hasMessageContaining(NO_BOOKINGS.getMessage());
       then(carBookingDAO).shouldHaveNoMoreInteractions();
    }

    @Test
    void shouldThrowWhenBookingIdNotFoundToDeleteCarBooking() {
        // given
        User user = new User(UUID.randomUUID(), "John");
        Car bookedCar1 = new Car("1111", 20.0, Brand.AUDI, false);
        Car bookedCar2 = new Car("2222", 40.0, Brand.MERCEDES, true);

        CarBooking carBooking = new CarBooking(user, bookedCar1);
        CarBooking carBooking2 = new CarBooking(user, bookedCar2);
        List<CarBooking> carBookings = List.of(carBooking, carBooking2);

        given(carBookingDAO.selectAllBookings()).willReturn(carBookings);
        given(carBookingDAO.getCarBookingById(any())).willReturn(Optional.empty());
        // then
        assertThatThrownBy(() -> underTest.deleteCarBooking(UUID.randomUUID()))
                .isInstanceOf(NoSuchElementException.class)
                .hasMessageContaining(BOOKING_ID_NOT_FOUND.getMessage());
        then(carBookingDAO).shouldHaveNoMoreInteractions();
    }
}