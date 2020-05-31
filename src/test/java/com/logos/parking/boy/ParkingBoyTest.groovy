package com.logos.parking.boy

import com.logos.parking.Car
import com.logos.parking.NoSpaceException
import com.logos.parking.NoThoseCarException
import com.logos.parking.ParkingLot
import spock.lang.Specification

class ParkingBoyTest extends Specification {

    def "should return ticket and can pick up from 2 lot when parking given parking boy with 1 full lot 2 has space lot 3 has space lot"() {
        given:
        def lot1 = new ParkingLot(1)
        def car1 = new Car()
        lot1.parking(car1)
        def lot2 = new ParkingLot(2)
        def lot3 = new ParkingLot(10)
        def boy = new ParkingBoy([lot1, lot2, lot3])
        def carToParking = new Car()
        when:
        def ticket = boy.parking(carToParking)
        then:
        ticket != null
        def pickUp = lot2.pickUp(ticket)
        pickUp == carToParking
    }

    def "ld throw NoSpaceException when parking given a car and boy with 3 full lot"() {
        given:
        def boy = new ParkingBoy([buildFullParkingLot(), buildFullParkingLot(), buildFullParkingLot()])
        def car = new Car()
        when:
        boy.parking(car)
        then:
        thrown(NoSpaceException)
    }

    def "should return car A when pick up given a boy with parking lot which parking car A"() {
        given:
        def lot = new ParkingLot(1)
        def carA = new Car()
        def ticket = lot.parking(carA)
        def boy = new ParkingBoy([lot,buildFullParkingLot()])
        when:
        def car = boy.pickUp(ticket)
        then:
        car == carA
    }

    def "should throw NoThoseCarException when pickUp given boy with no parking lot parked car A"() {
        given:
        def carA = new Car();
        def lot = new ParkingLot(1);
        def ticket = lot.parking(carA)
        def boy = new ParkingBoy([buildFullParkingLot(), buildFullParkingLot()])
        when:
        boy.pickUp(ticket)
        then:
        thrown(NoThoseCarException)
    }

    static ParkingLot buildFullParkingLot() {
        def lot1 = new ParkingLot(1)
        def car1 = new Car()
        lot1.parking(car1)
        return lot1
    }
}
