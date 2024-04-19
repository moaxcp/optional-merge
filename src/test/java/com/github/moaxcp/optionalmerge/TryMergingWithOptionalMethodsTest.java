package com.github.moaxcp.optionalmerge;

import org.junit.jupiter.api.Test;

import java.time.LocalTime;
import java.util.Optional;
import java.util.function.BinaryOperator;

import static java.util.Optional.empty;
import static java.util.Optional.of;
import static optionalmerge.extensions.java.util.Optional.OptionalExtras.merge;
import static org.assertj.core.api.Assertions.assertThat;

public class TryMergingWithOptionalMethodsTest {

    @Test
    void both_are_empty() {
        var first = Optional.<LocalTime>empty();
        var second = Optional.<LocalTime>empty();
        var result = merge(first, second, (f, s) -> f.isBefore(s) ? f : s);

        assertThat(result).isEmpty();
    }

    @Test
    void first_has_value() {
        var first = Optional.of(LocalTime.parse("05:30"));
        var second = Optional.<LocalTime>empty();
        var result = merge(first, second, (f, s) -> f.isBefore(s) ? f : s);

        assertThat(result).hasValue(LocalTime.parse("05:30"));
    }

    @Test
    void second_has_value() {
        var first = Optional.<LocalTime>empty();
        var second = Optional.of(LocalTime.parse("17:45"));
        var result = merge(first, second, (f, s) -> f.isBefore(s) ? f : s);

        assertThat(result).hasValue(LocalTime.parse("17:45"));
    }

    @Test
    void both_have_value_select_first() {
        var first = Optional.of(LocalTime.parse("05:30"));
        var second = Optional.of(LocalTime.parse("17:45"));
        var result = merge(first, second, (f, s) -> f.isBefore(s) ? f : s);

        assertThat(result).hasValue(LocalTime.parse("05:30"));
    }

    @Test
    void both_have_value_select_second() {
        var first = Optional.of(LocalTime.parse("17:45"));
        var second = Optional.of(LocalTime.parse("05:30"));
        var result = merge(first, second, (f, s) -> f.isBefore(s) ? f : s);

        assertThat(result).hasValue(LocalTime.parse("05:30"));
    }
}
