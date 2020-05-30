package com.logos.parking.boy;

import com.logos.parking.Car;
import com.logos.parking.NoSpaceException;
import com.logos.parking.ParkingLot;
import com.logos.parking.Ticket;
import java.util.List;

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
}
