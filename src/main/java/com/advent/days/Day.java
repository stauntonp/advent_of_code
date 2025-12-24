package com.advent.days;

import java.util.List;

public interface Day<T> {
    Long part1(List<String> input);
    T part2(List<String> input);
}
