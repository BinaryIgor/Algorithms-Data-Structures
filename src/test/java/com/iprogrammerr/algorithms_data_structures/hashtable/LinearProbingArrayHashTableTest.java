package com.iprogrammerr.algorithms_data_structures.hashtable;

import org.hamcrest.MatcherAssert;
import org.hamcrest.Matchers;
import org.junit.Test;

import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Objects;

public class LinearProbingArrayHashTableTest {

    private static final Map<NaiveHash, Integer> MAP = new LinkedHashMap<>();

    static {
        MAP.put(new NaiveHash("a"), 1);
        MAP.put(new NaiveHash("b"), 2);
        MAP.put(new NaiveHash("c"), 3);
        MAP.put(new NaiveHash("za"), 4);
    }

    @Test
    public void removesFirstLinearlyProbed() {
        removesLinearlyProbed(new NaiveHash("a"));
    }

    private void removesLinearlyProbed(NaiveHash toRemove) {
        LinearProbingArrayHashTable<NaiveHash, Integer> table = new LinearProbingArrayHashTable<>();
        MAP.forEach(table::save);

        table.remove(toRemove);

        MatcherAssert.assertThat(table.size(), Matchers.equalTo(MAP.size() - 1));
        MAP.forEach((k, v) -> {
            if (!k.equals(toRemove)) {
                MatcherAssert.assertThat(table.value(k), Matchers.equalTo(v));
            }
        });
    }

    @Test
    public void removesSecondLinearlyProbed() {
        removesLinearlyProbed(new NaiveHash("b"));
    }

    @Test
    public void removesThirdLinearlyProbed() {
        removesLinearlyProbed(new NaiveHash("c"));
    }


    static class NaiveHash {

        public final String id;

        public NaiveHash(String id) {
            this.id = id;
        }

        @Override
        public int hashCode() {
            return id.compareTo("z") < 1 ? 1 : id.hashCode();
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            NaiveHash naiveHash = (NaiveHash) o;
            return Objects.equals(id, naiveHash.id);
        }
    }
}
