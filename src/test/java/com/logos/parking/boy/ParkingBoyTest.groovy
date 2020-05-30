package com.logos.parking.boy

import com.logos.parking.Car
import com.logos.parking.NoSpaceException
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

    def "should throw NoSpaceException when parking given a car and boy with 3 full lot"() {
        given:
        def boy = new ParkingBoy([buildFullParkingLot(), buildFullParkingLot(), buildFullParkingLot()])
        def car = new Car()
        when:
        boy.parking(car)
        then:
        thrown(NoSpaceException)
    }

    static ParkingLot buildFullParkingLot() {
        def lot1 = new ParkingLot(1)
        def car1 = new Car()
        lot1.parking(car1)
        return lot1
    }
}
