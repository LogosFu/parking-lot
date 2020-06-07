package com.logos.parking;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

public class ParkingLot {

  Map<Ticket, Car> space = new HashMap<>();
  Integer size;

  public ParkingLot(Integer size) {
    this.size = size;
  }

  public Ticket parking(Car car) {
    if (hasSpace()) {
      Ticket ticket = new Ticket();
      space.put(ticket, car);
      return ticket;
    } else {
      throw new NoSpaceException();
    }
  }

  private boolean hasSpace() {
    return space.size() < this.size;
  }

  public Car pickUp(Ticket ticket) {
    return Optional.ofNullable(space.get(ticket)).orElseThrow(NoCarException::new);
  }
}
