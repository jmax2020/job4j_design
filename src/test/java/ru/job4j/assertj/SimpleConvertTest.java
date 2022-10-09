package ru.job4j.assertj;

import org.assertj.core.data.Index;
import org.junit.jupiter.api.Test;

import java.util.List;
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
                .doesNotContain("first", Index.atIndex(1))
                .anyMatch(e -> e.contains("ree"));
    }

    @Test
    void checkList() {
        SimpleConvert sc = new SimpleConvert();
        List<String> list = sc.toList("one", "two", "three");
        assertThat(list).hasSize(3)
                .startsWith("one")
                .containsSequence("two", "three")
                .anyMatch(e -> e.length() > 4)
                .element(2).isNotNull()
                .matches(e -> e.contains("ee"));

    }

    @Test
    void checkSet() {
        SimpleConvert sc = new SimpleConvert();
        Set<String> set = sc.toSet("one", "two", "three");
        assertThat(set).allMatch(e -> e.length() > 2)
                .filteredOn(e -> e.contains("t"))
                .first().isEqualTo("two");

    }

    @Test
    void checkMap() {
        SimpleConvert sc = new SimpleConvert();
        Map<String, Integer> map = sc.toMap("one", "two", "three");
        assertThat(map).hasSize(3)
                .containsKeys("two", "one")
                .containsValue(2)
                .doesNotContainKey("four")
                .doesNotContainValue(5);
    }
}