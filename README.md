# Advent of Code 2025 - Java Workspace

Overview
- This is a minimal Java Maven project scaffold to solve Advent of Code puzzles.
- It contains an `InputFetcher` utility to fetch puzzle inputs via URL or local files and a `Day` interface with a sample `Day01` implementation.
- Tests are setup using JUnit 5. Each day should have a corresponding JUnit test.

Fetch real puzzle inputs
- You can fetch inputs directly using `InputFetcher` and passing a URL.
- If you need to download your private puzzle input from Advent of Code, set an environment variable named `AOC_SESSION` to your session cookie value. InputFetcher will add a `Cookie: session=...` header to HTTP requests.

How to run
- Run tests with:
```bash
mvn test
```
- Run a single day test (example):
```bash
mvn -Dtest=com.advent.days.Day01Test test
```
- Run a day from the command line using the `Runner` (defaults to the test input file if URL omitted):

```bash
# run day 1 using test resource
java -cp target/classes;target/test-classes com.advent.Runner 1

# run day 1 using the AoC input URL (requires AOC_SESSION env var for private input)
java -cp target/classes;target/test-classes com.advent.Runner 1 https://adventofcode.com/2025/day/1/input
```

Project layout
- `src/main/java` — main source code (InputFetcher and day implementations)
- `src/test/java` — test code (JUnit)
- `src/test/resources` — input files used by tests

Add new day
1. Add a new class in `com.advent.days` that implements `Day<T>`.
2. Add a test in `src/test/java/com/advent/days` with a resource in `src/test/resources/input`.
3. Implement the day logic and update the expected assertions in the test.

Notes
- This scaffold uses Java 17 and JUnit Jupiter. You can change the Java target in `pom.xml`.
