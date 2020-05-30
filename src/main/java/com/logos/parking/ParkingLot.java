package com.logos.parking;

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
    if (parkingSpace.size() >= this.size) {
      throw new NoSpaceException();
    }
    Ticket ticket = new Ticket();
    parkingSpace.put(ticket, car);
    return ticket;
  }

  public Car pickUp(Ticket ticket) {
    Optional<Car> car = Optional.ofNullable(parkingSpace.get(ticket));
    return car.orElseThrow(NoThoseCarException::new);
  }
}
