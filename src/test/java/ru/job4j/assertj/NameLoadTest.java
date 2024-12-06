package ru.job4j.assertj;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.assertj.core.api.Assertions.assertThat;

class NameLoadTest {
    @Test
    void checkEmptyIsEmpty() {
        NameLoad nameLoad = new NameLoad();
        assertThatThrownBy(nameLoad::getMap)
                .isInstanceOf(IllegalStateException.class)
                .hasMessageContaining("no data");
    }

    @Test
    void checkParseMethodInputEmpty() {
        NameLoad nameLoad = new NameLoad();
        assertThatThrownBy(nameLoad::parse)
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("Names array is empty");
    }

    @Test
    void checkParseMethodNameWithoutEqualsInName() {
        NameLoad nameLoad = new NameLoad();
        String name = "   Ivan1";
        assertThatThrownBy(() -> nameLoad.parse(name))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("this name: Ivan1 does not contain the symbol '='");
    }

    @Test
    void checkParseMethodNameWithoutKey() {
        NameLoad nameLoad = new NameLoad();
        String name = "   =1";
        assertThatThrownBy(() -> nameLoad.parse(name))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("this name: =1 does not contain a value");
    }

    @Test
    void checkParseNormalCondition() {
        NameLoad nameLoad = new NameLoad();
        String name1 = "  Ivan = 1";
        String name2 = " Vladimir  = 2";
        nameLoad.parse(name1, name2);
        assertThat(nameLoad.getMap())
                .containsEntry("Ivan", "1")
                .containsEntry("Vladimir", "2");
    }

    @Test
    void checkParseWhenComesSameNameWithDifferentKey() {
        NameLoad nameLoad = new NameLoad();
        String name1 = "  Ivan = 1";
        String name2 = " Vladimir  = 2";
        String name3 = " Vladimir  = 3";
        nameLoad.parse(name1, name2, name3);
        assertThat(nameLoad.getMap())
                .containsEntry("Ivan", "1")
                .containsEntry("Vladimir", "2 + 3");
    }
}