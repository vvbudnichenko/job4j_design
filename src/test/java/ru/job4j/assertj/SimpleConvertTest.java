package ru.job4j.assertj;

import org.assertj.core.data.Index;
import org.junit.jupiter.api.Test;

import java.util.Map;
import java.util.Set;

import static org.assertj.core.api.Assertions.*;

class SimpleConvertTest {

    @Test
    void checkArray() {
        SimpleConvert simpleConvert = new SimpleConvert();
        String[] array = simpleConvert.toArray("first", "second", "three", "four", "five");
        assertThat(array).hasSize(5)
                .contains("second")
                .contains("first", Index.atIndex(0))
                .containsAnyOf("zero", "second", "six")
                .doesNotContain("first", Index.atIndex(1));
    }

    @Test
    void checkArrayIsEmpty() {
        SimpleConvert simpleConvert = new SimpleConvert();
        String[] array = simpleConvert.toArray();
        assertThat(array).isEmpty();
    }

    @Test
    void checkToList() {
        SimpleConvert simpleConvert = new SimpleConvert();
        String[] array = simpleConvert.toArray("first", "second", "three", "four", "five");
        assertThat(simpleConvert.toList(array)).isNotEmpty();
        assertThat(simpleConvert.toList(array)).containsExactly("first", "second", "three", "four", "five");
    }

    @Test
    void checkToListWhenArrayIsEmpty() {
        SimpleConvert simpleConvert = new SimpleConvert();
        String[] array = simpleConvert.toArray();
        assertThat(simpleConvert.toList(array)).isEmpty();
    }

    @Test
    void checkToSetIsNotEqual() {
        SimpleConvert simpleConvert = new SimpleConvert();
        Set<String> set = Set.of("first", "second", "three", "four", "five");
        assertThat(simpleConvert.toSet("first", "second", "three", "four")).isNotEqualTo(set);
    }

    @Test
    void checkToSetOrderDoesNotMatter() {
        SimpleConvert simpleConvert = new SimpleConvert();
        Set<String> set = simpleConvert.toSet("ABBA", "ACDC", "RHCP");
        assertThat(set).containsExactlyInAnyOrder("ABBA", "ACDC", "RHCP");
    }

    @Test
    void checkToSetIsEqual() {
        SimpleConvert simpleConvert = new SimpleConvert();
        Set<String> set = Set.of("first", "second", "three", "four", "five");
        assertThat(simpleConvert.toSet("first", "second", "three", "four", "five")).isEqualTo(set);
    }

    @Test
    void checkToSetWhenHasSameKeyInOrder() {
        SimpleConvert simpleConvert = new SimpleConvert();
        Set<String> set = simpleConvert.toSet("first", "second", "first", "second", "second", "first");
        assertThat(set).hasSize(2).contains("first", "second");
    }

    @Test
    void checkToSetNormalCondition() {
        SimpleConvert simpleConvert = new SimpleConvert();
        Set<String> set = simpleConvert.toSet("first", "second", "tree");
        assertThat(set).hasSize(3).contains("first", "second", "tree");
    }

    @Test
    void checkToMapNormalCondition() {
        SimpleConvert simpleConvert = new SimpleConvert();
        Map<String, Integer> map = simpleConvert.toMap("first", "second", "three", "four", "five");
        assertThat(map)
                .hasSizeBetween(0, 5)
                .hasSize(5)
                .containsEntry("first", 0)
                .containsEntry("second", 1)
                .containsEntry("three", 2)
                .containsOnlyKeys("first", "second", "three", "four", "five");
    }

    @Test
    void checkMapWithSingleElement() {
        SimpleConvert simpleConvert = new SimpleConvert();
        Map<String, Integer> map = simpleConvert.toMap("single");
        assertThat(map).hasSize(1).containsEntry("single", 0);
    }

    @Test
    void checkToMapAllFirstKey() {
        SimpleConvert simpleConvert = new SimpleConvert();
        Map<String, Integer> map = simpleConvert.toMap("first", "first", "first", "first", "first");
        assertThat(map)
                .hasSizeBetween(0, 5)
                .hasSize(1)
                .containsEntry("first", 0);
    }

    @Test
    void checkToMapEmptyInput() {
        SimpleConvert simpleConvert = new SimpleConvert();
        Map<String, Integer> array = simpleConvert.toMap();
        assertThat(array).isEmpty();
    }

    @Test
    void checkToMapWhenHasDuplicateKeys() {
        SimpleConvert simpleConvert = new SimpleConvert();
        Map<String, Integer> map = simpleConvert.toMap("first", "second", "second");
        assertThat(map).hasSize(2)
                .containsEntry("first", 0)
                .containsEntry("second", 1)
                .containsEntry("first", 0);
    }

    @Test
    void checkToMapWithDuplicateKeysOnly() {
        SimpleConvert simpleConvert = new SimpleConvert();
        Map<String, Integer> map = simpleConvert.toMap("first", "first", "first");
        assertThat(map).hasSize(1).containsEntry("first", 0);
    }
}