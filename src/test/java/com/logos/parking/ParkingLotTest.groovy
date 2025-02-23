package com.logos.parking

import com.logos.parking.domain.Car
import com.logos.parking.domain.ParkingLot
import com.logos.parking.exception.NoSpaceException
import com.logos.parking.exception.NoThoseCarException
import spock.lang.Specification

class ParkingLotTest extends Specification {

    def "should return ticket when parking given car and parking lot with space"() {
        given:
        def parkingLot = new ParkingLot(10);
        def car = new Car();
        when:
        def ticket = parkingLot.parking(car);
        then:
        ticket != null;
    }

    def "should throw NoSpaceException when parking given car and parking lot with no space"() {
        given:
        def lot = new ParkingLot(1);
        def car1 = new Car();
        def car2 = new Car();
        lot.parking(car1);
        when:
        lot.parking(car2);
        then:
        thrown(NoSpaceException)
    }

    def "should return car given when pickUp given parking lot parked a car and the ticket parking return"() {
        given:
        def lot = new ParkingLot(2);
        def car = new Car();
        def ticket = lot.parking(car)
        when:
        def  pickUpCar = lot.pickUp(ticket)
        then:
        car == pickUpCar
    }

    def "should throw NoThoseCarException when pick up given parking lot not parked those car"() {
        given:
        def parkedLot = new ParkingLot(1)
        def car = new Car()
        def ticket = parkedLot.parking(car)
        def otherLot = new ParkingLot(1)
        when:
        otherLot.pickUp(ticket)
        then:
        thrown(NoThoseCarException)
    }
}
