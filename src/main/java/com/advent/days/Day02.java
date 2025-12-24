package com.advent.days;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Day02 implements Day<Long> {
    @Override
    public Long part1(java.util.List<String> input) {
        var ranges = toStringList(input.get(0));
        
        List<String> allResults = new ArrayList<String>();
        for (String rangeStr : ranges) {
            var range = unwindRange(rangeStr);
            var results = searchSequence(range);
            allResults.addAll(results);
        }

        return sumResults(allResults);
    }

    @Override
    public Long part2(java.util.List<String> input) {
        return null;
    }
    
    /**
     * @param input single comma-separated line
     * @return list of each number range i.e. 11-22 
     */
    List<String> toStringList(String input) {
        return Arrays.asList(input.split(","));
    }

    /**
     * Unwinds a range string into all numbers in that range. eg. 11-15 -> "1112131415"
     * @param range
     * @return string of all numbers in range
     */
    Range unwindRange(String input) {
        var parts = input.split("-");
        if (parts.length != 2) {
            throw new IllegalArgumentException("Invalid range: " + input);
        }
        var range = new Range(Long.parseLong(parts[0]), Long.parseLong(parts[1]));

        return range;
    }

    /**
     * 
     * @param sequence: String e.g. "11-22"
     */
    List<String> searchSequence(Range range) {
        List<String> repeated = new ArrayList<String>();

        for (long i = range.start; i <= range.end; i++) {
            // System.out.println("Inspecting: " + i + " to " + range.end);
            String numStr = Long.toString(i);
            // Ignore leading zeros
            if (numStr.charAt(0) == '0') continue;
            // Only inspecting even length numbers for repeating sequence
            if (numStr.length() % 2 == 1) continue;

            // System.out.println("Checking number: " + numStr);
            int len = numStr.length() / 2;
            String left = numStr.substring(0, len);
            String right = numStr.substring(len);

            if (left.equals(right)) {
                repeated.add(numStr);
            }
        }

        return repeated;
    }

    private long sumResults(List<String> results) {
        long sum = 0;
        for (String id : results) {
            sum += Long.parseLong(id);
        }
        return sum;
    }

    public static class Range {
        long start;
        long end;
        String sequence;

        public Range(long start, long end) {
            this.start = start;
            this.end = end;

            this.setSequence();
        }

        public void setSequence() {
            StringBuilder sb = new StringBuilder();
            for (long i = start; i <= end; i++) {
                sb.append(i);
            }
            this.sequence = sb.toString();
        }

        public String getSequence() {
            return sequence;
        }
    }
}
