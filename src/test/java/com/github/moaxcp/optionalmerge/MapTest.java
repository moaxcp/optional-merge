package com.github.moaxcp.optionalmerge;

import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.Map;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.entry;

public class MapTest {
    @Test
    void merge_empty() {
        var map = new HashMap<String, String>();
        map.merge("key", "value", (v1, v2) -> v2);
        assertThat(map).containsExactly(entry("key", "value"));
    }

    @Test
    void merge_value_replaced() {
        var map = new HashMap<String, String>();
        map.put("key", "replaced");
        map.merge("key", "value", (v1, v2) -> v2);
        assertThat(map).containsExactly(entry("key", "value"));
    }

    @Test
    void merge_value_not_replaced() {
        var map = new HashMap<String, String>();
        map.put("key", "not replaced");
        map.merge("key", "value", (v1, v2) -> v1);
        assertThat(map).containsExactly(entry("key", "not replaced"));
    }
}
