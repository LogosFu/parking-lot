package com.logos.parking;

import java.util.ArrayList;
import java.util.List;

public class ParkingLot {

  List<Car> parkingSpace;
  Integer size;

  public ParkingLot(Integer size) {
    this.size = size;
    parkingSpace = new ArrayList<>(size);
  }
}
