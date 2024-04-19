package com.github.moaxcp.optionalmerge;

import org.junit.jupiter.api.Test;

import static java.util.Optional.empty;
import static java.util.Optional.of;
import static org.assertj.core.api.Assertions.assertThat;

public class OptionalMergeWithOptionalTest {
    @Test
    void both_are_empty() {
        assertThat(empty().merge(empty(), (f, s) -> f)).isEmpty();
    }

    @Test
    void this_is_empty() {
        assertThat(empty().merge(of(true), (f, s) -> f)).hasValue(true);
    }

    @Test
    void other_is_empty() {
        assertThat(of(true).merge(empty(), (f, s) -> f)).hasValue(true);
    }

    @Test
    void select_this() {
        assertThat(of(true).merge(of(false), (f, s) -> f)).hasValue(true);
    }

    @Test
    void select_other() {
        assertThat(of(true).merge(of(false), (f, s) -> s)).hasValue(false);
    }
}
