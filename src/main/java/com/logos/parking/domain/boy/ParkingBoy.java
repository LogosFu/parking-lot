package com.logos.parking.domain.boy;

import com.logos.parking.domain.Car;
import com.logos.parking.exception.NoSpaceException;
import com.logos.parking.exception.NoThoseCarException;
import com.logos.parking.domain.ParkingLot;
import com.logos.parking.domain.Ticket;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

public class ParkingBoy {

  List<ParkingLot> lots;

  public ParkingBoy(List<ParkingLot> lots) {
    this.lots = lots;
  }

  public Ticket parking(Car car) {
    ParkingLot parkingLot = lots.stream().filter(ParkingLot::hasSpace)
        .findFirst().orElseThrow(NoSpaceException::new);
    return parkingLot.parking(car);
  }

  public Car pickUp(Ticket ticket) {
    List<Optional<Car>> cars = lots.stream().map(lot -> lot.pickUpByBoy(ticket))
        .collect(Collectors.toList());
    return cars.stream().filter(Optional::isPresent).map(Optional::get).findFirst().orElseThrow(
        NoThoseCarException::new);
  }
}
