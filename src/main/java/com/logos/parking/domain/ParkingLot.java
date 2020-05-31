package com.logos.parking.domain;

import com.logos.parking.exception.NoSpaceException;
import com.logos.parking.exception.NoThoseCarException;
import java.util.HashMap;
import java.util.Optional;

public class ParkingLot {

  HashMap<Ticket, Car> parkingSpace;
  Integer size;

  public ParkingLot(Integer size) {
    this.size = size;
    parkingSpace = new HashMap<>();
  }

  public Ticket parking(Car car) {
    if (!hasSpace()) {
      throw new NoSpaceException();
    }
    Ticket ticket = new Ticket();
    parkingSpace.put(ticket, car);
    return ticket;
  }

  public boolean hasSpace() {
    return parkingSpace.size() < this.size;
  }

  public Car pickUp(Ticket ticket) {
    Optional<Car> car = Optional.ofNullable(parkingSpace.get(ticket));
    return car.orElseThrow(NoThoseCarException::new);
  }

  public Optional<Car> pickUpByBoy(Ticket ticket) {
    return Optional.ofNullable(parkingSpace.get(ticket));
  }
}
