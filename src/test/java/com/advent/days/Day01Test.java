package com.advent.days;

import com.advent.TestBase;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class Day01Test extends TestBase {

    @Test
    public void testParts() throws IOException {
        List<String> input = loadInputResource("input/day01.txt");
        Day01 day = new Day01();

        assertEquals(15, day.part1(input));
        assertEquals(5, day.part2(input));
    }

    @Test
    public void testTurnDial() {
        Day01 day = new Day01();

        // Standard add, subtract
        assertEquals(14, day.turnDial(0, Direction.Right, 14));
        assertEquals(80, day.turnDial(20, Direction.Right, 60));
        assertEquals(20, day.turnDial(80, Direction.Left, 60));
        // Roll over from zero
        assertEquals(96, day.turnDial(0, Direction.Left, 4));
        assertEquals(2, day.turnDial(98, Direction.Right, 4));
        assertEquals(51, day.turnDial(1, Direction.Left, 150));
        assertEquals(21, day.turnDial(90, Direction.Left, 169));
    }

    @Test
    public void testMultipleRotations() {
        int pos = 0;
        Day01 day = new Day01(0);
        
        pos = day.turnDial(0, Direction.Right, 250);
        assertEquals(50, pos);
        assertEquals(day.getNumberOfClicks(), 0);
        assertEquals(day.getClicksDuringRotation(), 2);
    }

    // Manual sequence test including click incrementing
    @Test
    public void testExampleSequence() {
        int pos = 50;
        Day01 day = new Day01(pos);

        // L68
        pos = day.turnDial(pos, Direction.Left, 68);
        assertEquals(82, pos);
        //L30
        pos = day.turnDial(pos, Direction.Left, 30);
        assertEquals(52, pos);
        // R48
        pos = day.turnDial(pos, Direction.Right, 48);
        assertEquals(0, pos);
        assertEquals(1, day.getNumberOfClicks());
        // L5
        pos = day.turnDial(pos, Direction.Left, 5);
        assertEquals(95, pos);
        // R60
        pos = day.turnDial(pos, Direction.Right, 60);
        assertEquals(55, pos);
        // L55
        pos = day.turnDial(pos, Direction.Left, 55);
        assertEquals(0, pos);
        assertEquals(2, day.getNumberOfClicks());
        // L1
        pos = day.turnDial(pos, Direction.Left, 1);
        assertEquals(99, pos);
        // L99
        pos = day.turnDial(pos, Direction.Left, 99);
        assertEquals(0, pos);
        // R14
        pos = day.turnDial(pos, Direction.Right, 14);
        assertEquals(14, pos);
        // L82
        pos = day.turnDial(pos, Direction.Left, 82);
        assertEquals(32, pos);

        assertEquals(3, day.getNumberOfClicks());
    }

    @Test
    public void testParseDirections() {
        List<String> input = List.of(
            "L68",
            "L30",
            "R48",
            "L5",
            "R60",
            "L55",
            "L1",
            "L99",
            "R14",
            "L82"
        );
        Day01 day = new Day01(50);
        day.part1(input);

        assertEquals(32, day.getDialPosition());
        assertEquals(3, day.getNumberOfClicks());
    }

        // Manual sequence test including click incrementing
    @Test
    public void testExampleSequence2() {
        int pos = 50;
        Day01 day = new Day01(pos);

        // L68
        pos = day.turnDial(pos, Direction.Left, 68);
        assertEquals(82, pos);
        assertEquals(1, day.getClicksDuringRotation());
        assertEquals(1, day.getTotalClicks());
        //L30
        pos = day.turnDial(pos, Direction.Left, 30);
        assertEquals(52, pos);
        assertEquals(1, day.getClicksDuringRotation());
        assertEquals(1, day.getTotalClicks());
        // R48
        pos = day.turnDial(pos, Direction.Right, 48);
        assertEquals(0, pos);
        assertEquals(1, day.getNumberOfClicks());
        assertEquals(1, day.getClicksDuringRotation());
        assertEquals(2, day.getTotalClicks());
        // L5
        pos = day.turnDial(pos, Direction.Left, 5);
        assertEquals(95, pos);
        assertEquals(1, day.getNumberOfClicks());
        assertEquals(1, day.getClicksDuringRotation());
        assertEquals(2, day.getTotalClicks());
        // R60
        pos = day.turnDial(pos, Direction.Right, 60);
        assertEquals(55, pos);
        assertEquals(1, day.getNumberOfClicks());
        assertEquals(2, day.getClicksDuringRotation());
        assertEquals(3, day.getTotalClicks());
        // L55
        pos = day.turnDial(pos, Direction.Left, 55);
        assertEquals(0, pos);
        assertEquals(2, day.getNumberOfClicks());
        // L1
        pos = day.turnDial(pos, Direction.Left, 1);
        assertEquals(99, pos);
        // L99
        pos = day.turnDial(pos, Direction.Left, 99);
        assertEquals(0, pos);
        // R14
        pos = day.turnDial(pos, Direction.Right, 14);
        assertEquals(14, pos);
        // L82
        pos = day.turnDial(pos, Direction.Left, 82);
        assertEquals(32, pos);
        assertEquals(3, day.getClicksDuringRotation());

        assertEquals(3, day.getNumberOfClicks());
    }

    @Test
    public void testParseDirections2() {
        List<String> input = List.of(
            "L68",
            "L30",
            "R48",
            "L5",
            "R60",
            "L55",
            "L1",
            "L99",
            "R14",
            "L82"
        );
        Day01 day = new Day01(50);
        day.part2(input);

        assertEquals(32, day.getDialPosition());
        assertEquals(3, day.getNumberOfClicks());
        assertEquals(3, day.getClicksDuringRotation());
    }

    @Test
    public void testCountClicks() {
        List<String> input = List.of(
            "L68",
            "L30",
            "R48",
            "L5",
            "R60",
            "L55",
            "L1",
            "L99",
            "R14",
            "L82"
        );
        Day01 day = new Day01(50);
        day.part2New(input);

        assertEquals(32, day.getDialPosition());
        assertEquals(6, day.getNumberOfClicks());
    }
}
