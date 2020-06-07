package com.logos.parking

import spock.lang.Specification

class ParkingLotTest extends Specification {

    def "should return ticket when parking given a car and parking lot with space"() {
        given:
        def car = new Car()
        def lot = new ParkingLot(1);
        when:
        def ticket = lot.parking(car)
        then:
        ticket != null
    }

    def "should throw NoSpaceException When parking given a car and lot with no space"() {
        given:
        def car = new Car()
        def lot = new ParkingLot(1)
        lot.parking(car)
        def car1 = new Car()
        when:
        def ticket = lot.parking(car1)
        then:
        thrown(NoSpaceException)

    }

    def "should return a car when pick up given a ticket  parking return "() {
        given:
        def car = new Car()
        def lot = new ParkingLot(1)
        def ticket = lot.parking(car)
        when:
        def carReturn = lot.pickUp(ticket)
        then:
        carReturn == car
    }

    def "should throw NoCarException when pick up given a ticket not parking return"() {
        given:
        def ticket = new Ticket()
        def lot = new ParkingLot(1)
        when:
        lot.pickUp(ticket)
        then:
        thrown(NoCarException)
    }
}
