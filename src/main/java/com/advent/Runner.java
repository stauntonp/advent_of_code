package com.advent;

import com.advent.days.Day01;
import com.advent.days.Day;
import com.advent.parser.InputFetcher;

import java.io.IOException;
import java.util.List;

public class Runner {

    public static void main(String[] args) throws IOException, InterruptedException {
        if (args.length < 1) {
            System.err.println("Usage: java -jar <jar> <day> [url]");
            System.exit(1);
        }

        String dayArg = args[0];
        String url = args.length > 1 ? args[1] : "src/test/resources/input/day" + dayArg + ".txt";

        List<String> input = InputFetcher.fetchLines(url);

        switch (dayArg) {
            case "01":
            case "1":
                // Wrong answer as started on 0 first
                int startingPosition = 50;
                Day<Integer> day = new Day01(startingPosition);
                System.out.println("Day 1 - part1: " + day.part1(input));
                day = new Day01(startingPosition);
                System.out.println("Day 1 - part2: " + day.part2(input));
                break;
            default:
                System.err.println("Day not implemented: " + dayArg);
        }
    }
}
