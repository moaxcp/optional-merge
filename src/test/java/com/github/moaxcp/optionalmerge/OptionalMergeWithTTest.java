package com.github.moaxcp.optionalmerge;

import org.junit.jupiter.api.Test;

import static java.util.Optional.empty;
import static java.util.Optional.of;
import static org.assertj.core.api.Assertions.assertThat;

public class OptionalMergeWithTTest {
    @Test
    void both_are_empty() {
        assertThat(empty().merge((Boolean) null, (f, s) -> f)).isEmpty();
    }

    @Test
    void this_is_empty() {
        assertThat(empty().merge(true, (f, s) -> f)).hasValue(true);
    }

    @Test
    void other_is_empty() {
        assertThat(of(true).merge((Boolean) null, (f, s) -> f)).hasValue(true);
    }

    @Test
    void select_this() {
        assertThat(of(true).merge(false, (f, s) -> f)).hasValue(true);
    }

    @Test
    void select_other() {
        assertThat(of(true).merge(false, (f, s) -> s)).hasValue(false);
    }
}
