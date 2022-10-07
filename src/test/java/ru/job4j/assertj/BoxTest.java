package ru.job4j.assertj;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.*;

class BoxTest {

    @Test
    void thisIsSphere() {
        Box box = new Box(0, 4);
        assertThat(box.whatsThis()).isEqualTo("Sphere");
        assertThat(box.isExist()).isEqualTo(true);
    }

    @Test
    void thisIsCube() {
        Box box = new Box(8, 5);
        assertThat(box.whatsThis()).isEqualTo("Cube");
        assertThat(box.getArea()).isEqualTo(150);
    }

    @Test
    void thisIsUNKNOWN() {
        Box box = new Box(8, 0);
        assertThat(box.whatsThis()).isEqualTo("Unknown object");
        assertThat(box.getArea()).isEqualTo(0);
    }

    @Test
    void thisIsTetrahedron() {
        Box box = new Box(4, 10);
        assertThat(box.getNumberOfVertices()).isEqualTo(4);
        assertThat(box.isExist()).isEqualTo(true);
    }
}