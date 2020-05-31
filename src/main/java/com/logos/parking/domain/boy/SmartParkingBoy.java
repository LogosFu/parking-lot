package com.logos.parking.domain.boy;

import com.logos.parking.domain.Car;
import com.logos.parking.domain.ParkingLot;
import com.logos.parking.domain.Ticket;
import com.logos.parking.exception.NoSpaceException;
import java.util.Comparator;
import java.util.List;

public class SmartParkingBoy {

  List<ParkingLot> lots;

  public SmartParkingBoy(List<ParkingLot> lots) {
    this.lots = lots;
  }

  public Ticket parking(Car car) {
    ParkingLot hasMaxVacancyLot = lots.stream().max(Comparator.comparingInt(ParkingLot::vacancy))
        .orElse(null);
    if (hasMaxVacancyLot.hasSpace()) {
      return hasMaxVacancyLot.parking(car);
    } else {
      throw new NoSpaceException();
    }
  }
}
