package com.advent.days;

import java.util.AbstractMap;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class Day01 implements Day<Integer> {
    private int dialPosition;
    private int numberOfClicks;
    private int clicksDuringRotation;

    public Day01() {
        new Day01(0);
    }

    public Day01(int startingPosition) {
        this.dialPosition = startingPosition;
        this.numberOfClicks = 0;
        this.clicksDuringRotation = 0;
    }

    public int getDialPosition() {
        return dialPosition;
    }

    @Override
    public Integer part1(List<String> input) {
        var parsed = parseDirections(input);
        System.out.println("Parsed " + parsed.size() + " steps.");

        for (var step: parsed) {
            dialPosition = turnDial(dialPosition, step.getKey(), step.getValue());
        }
        return getNumberOfClicks();
    }

    @Override
    public Integer part2(List<String> input) {
                var parsed = parseDirections(input);
        System.out.println("Parsed " + parsed.size() + " steps.");

        for (var step: parsed) {
            dialPosition = turnDial(dialPosition, step.getKey(), step.getValue());
        }
        return getTotalClicks();
    }

    /**
     * Uses Naive implementation of dial-turning.
     * Unnecessary but kept for reference - error was with the test runner.
     */
    public Integer part2New(List<String> input) {
        var parsed = parseDirections(input);
        System.out.println("Parsed " + parsed.size() + " steps.");

        for (var step: parsed) {
            turnDialNaive(step.getKey(), step.getValue());
        }
        return getNumberOfClicks();
    }

    private List<Integer> toIntList(List<String> input) {
        return input.stream()
                .filter(s -> !s.isBlank())
                .map(Integer::parseInt)
                .collect(Collectors.toList());
    }

    /**
     * Parses a list of strings into a list of direction/step pairs.
     * @param input eg. L68, R30
     * @return
     */
    List<AbstractMap.SimpleEntry<Direction, Integer>> parseDirections(List<String> input) {
        List<AbstractMap.SimpleEntry<Direction, Integer>> result = new ArrayList<>();
        for (String line : input) {
            if (line == null || line.isBlank()) continue;
            Direction dir = line.charAt(0) == 'L' ? Direction.Left : Direction.Right;
            int value = Integer.parseInt(line.substring(1));
            result.add(new AbstractMap.SimpleEntry<>(dir, value));
        }
        return result;
    }

    /**
     * Turns the dial in the given direction by the given number of steps.
     * Will roll over to 0 if it goes past 99.
     * Java % operator is remainder, not modulo, so we have to handle negative values manually.
     * Increments Day01.numberOfClicks if the dial lands on 0.
     * @param direction
     * @param steps
     * @return Next position of the dial
     */
    int turnDial(int prev, Direction direction, int steps) {
        if (steps >= 100) {
            clicksDuringRotation += Math.floorDiv(steps, 100);
            steps = steps % 100;
        }

        var next = prev + (direction.getVal() * steps);
        if (prev != 0 && (next < 0 || next > 100)) {
            clicksDuringRotation++;
        }
        
        next = ((next % 100) + 100) % 100;

        if (next == 0) {
            numberOfClicks++;
        }

        dialPosition = next;
        return next;
    }

    /**
     * Naive implementation of turnDial as turnDial wasn't giving correct answer.
     * Issue was using the same Day object for both puzzles
     * @param direction
     * @param steps
     */
    void turnDialNaive(Direction direction, int steps) {
        while (steps > 0) {
            var next = dialPosition + direction.getVal();
            steps--;

            dialPosition = ((next % 100) + 100) % 100;

            if (dialPosition == 0) {
                numberOfClicks++;
            }
        }
    }

    /** The number of times that the dial landed on 0 */
    int getNumberOfClicks() {
        return numberOfClicks;
    }

    /** The number of times that the dial passed 0 without stopping */
    int getClicksDuringRotation() {
        return clicksDuringRotation;
    }

    int getTotalClicks() {
        return numberOfClicks + clicksDuringRotation;
    } 
}

enum Direction {
    Left(-1),
    Right(1);

    final int val;

    Direction(int val) {
        this.val = val;
    }

    public int getVal() {
        return val;
    }
}