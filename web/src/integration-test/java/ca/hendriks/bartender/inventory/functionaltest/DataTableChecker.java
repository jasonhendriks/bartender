package ca.hendriks.bartender.inventory.functionaltest;

import org.junit.jupiter.api.Assertions;

import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import java.util.function.BiConsumer;
import java.util.function.Function;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class DataTableChecker<T> {
    private final Map<String, String> entries;
    private final T model;
    private final Set<String> knownKeys;

    public DataTableChecker(final Map<String, String> expectedEntries, final T actualModel) {
        this.entries = expectedEntries;
        this.model = actualModel;
        knownKeys = new HashSet<>();
    }

    public void check(final String key, final BiConsumer<String, T> assertion) {
        knownKeys.add(key);
        if (entries.containsKey(key)) {
            final String bddValue = entries.get(key);
            assertion.accept(bddValue, model);
        }
    }

    public <X> void assertEquals(final Function<String, X> expectedFn, final Function<T, X> actualFn, final String key) {
        assertEquals(expectedFn, actualFn, key, key);
    }

    public <X> void assertEquals(final Function<String, X> expectedFn, final Function<T, X> actualFn, final String key, final String message) {
        knownKeys.add(key);
        if (entries.containsKey(key)) {
            final String bddValue = entries.get(key);
            final X expected = expectedFn.apply(bddValue);
            final X actual = actualFn.apply(model);
            Assertions.assertEquals(expected, actual, message);
        }
    }

    public void validateMapKeys() {
        for (final String key : entries.keySet()) {
            assertTrue(knownKeys.contains(key), "key \"" + key + "\" should be not recognized");
        }
    }

}
