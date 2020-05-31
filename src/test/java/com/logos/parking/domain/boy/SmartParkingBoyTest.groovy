package com.logos.parking.domain.boy

import com.logos.parking.domain.Car
import com.logos.parking.domain.ParkingLot
import com.logos.parking.exception.NoSpaceException
import spock.lang.Specification

import java.util.stream.IntStream

class SmartParkingBoyTest extends Specification {
    def "should car A parking to No.3 parking lot when picking given car A No.1 full No.2 has 3 space No.3 has 5 space"() {
        given:
        def lot1 = buildFullParkingLot()
        def lot3 = buildHasSpaceParkingLot(10, 5)
        def lot2 = buildHasSpaceParkingLot(10, 3)
        def boy = new SmartParkingBoy([lot1, lot2, lot3])
        def carA = new Car()
        when:
        def ticket = boy.parking(carA)
        then:
        def carPickUp = lot3.pickUp(ticket)
        carPickUp == carA
    }


    def "should throw NoSpaceException when parking given a car and boy with 3 full lot"() {
        given:
        def boy = new SmartParkingBoy([buildFullParkingLot(), buildFullParkingLot(), buildFullParkingLot()])
        def car = new Car()
        when:
        boy.parking(car)
        then:
        thrown(NoSpaceException)
    }

    private static ParkingLot buildHasSpaceParkingLot(int size, int emptySpace) {
        def lot = new ParkingLot(size)
        IntStream.range(0, size - emptySpace).forEach(i -> lot.parking(new Car()))
        lot
    }


    private static ParkingLot buildFullParkingLot() {
        def lot1 = new ParkingLot(1)
        def car1 = new Car()
        lot1.parking(car1)
        return lot1
    }
}
