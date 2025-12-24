package com.advent.days;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;
import com.advent.days.Day02.Range;

public class Day02Test {
    @Test
    public void testUnwindRange() {
        Day02 day2 = new Day02();

        Range range = day2.unwindRange("11-19");
        assertEquals("111213141516171819", range.getSequence());
        range = day2.unwindRange("50-54");
        assertEquals("5051525354", range.getSequence());
        range = day2.unwindRange("1-12");
        assertEquals("123456789101112", range.getSequence());
    }

    @Test
    public void testSearchSequence() {
        Day02 day2 = new Day02();

        testSequence("11-22", List.of("11", "22"));
        testSequence("95-115", List.of("99"));
        testSequence("998-1012", List.of("1010"));
        testSequence("1188511880-1188511890", List.of("1188511885"));
        testSequence("222220-222224", List.of("222222"));
        testSequence("1698522-1698528", List.of());
        testSequence("446443-446449", List.of("446446"));
        testSequence("38593856-38593862", List.of("38593859"));
    }

    @Test
    private void testSequence(String input, List<String> expected) {
        Day02 day = new Day02();
        Range range = day.unwindRange(input);
        List<String> result = day.searchSequence(range);

        assertEquals(expected, result);
    }
    
    @Test
    public void testFullPart1() {
        Day02 day = new Day02();
        int result = day.part1(List.of("11-22,95-115,998-1012,1188511880-1188511890,222220-222224,1698522-1698528,446443-446449,38593856-38593862,565653-565659,824824821-824824827,2121212118-2121212124"));
        assertEquals(1227775554, result);
    }
}
