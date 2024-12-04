package ru.job4j.assertj;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.withPrecision;

class BoxTest {
    @Test
    void isThisSphere() {
        Box box = new Box(0, 10);
        String name = box.whatsThis();
        double getArea = box.getArea();
        boolean checkVertexExist = box.isExist();
        int checkVertexNumber = box.getNumberOfVertices();
        assertThat(name).isEqualTo("Sphere");
        assertThat(getArea).isCloseTo(1256.63, withPrecision(0.01));
        assertThat(checkVertexExist).isEqualTo(true);
        assertThat(checkVertexNumber).isEqualTo(0);
    }

    @Test
    void isThisSphereWhenEdgeIs8() {
        Box box = new Box(0, 8);
        String name = box.whatsThis();
        double getArea = box.getArea();
        boolean checkVertexExist = box.isExist();
        int checkVertexNumber = box.getNumberOfVertices();
        assertThat(name).isEqualTo("Sphere");
        assertThat(getArea).isCloseTo(804.25, withPrecision(0.01));
        assertThat(checkVertexExist).isEqualTo(true);
        assertThat(checkVertexNumber).isEqualTo(0);
    }

    @Test
    void isTetrahedron() {
        Box box = new Box(4, 10);
        String name = box.whatsThis();
        double getArea = box.getArea();
        boolean checkVertexExist = box.isExist();
        int checkVertexNumber = box.getNumberOfVertices();
        assertThat(name).isEqualTo("Tetrahedron");
        assertThat(getArea).isCloseTo(173.20, withPrecision(0.01));
        assertThat(checkVertexExist).isEqualTo(true);
        assertThat(checkVertexNumber).isEqualTo(4);
    }

    @Test
    void isTetrahedronWhenEdgeIs12() {
        Box box = new Box(4, 12);
        String name = box.whatsThis();
        double getArea = box.getArea();
        boolean checkVertexExist = box.isExist();
        int checkVertexNumber = box.getNumberOfVertices();
        assertThat(name).isEqualTo("Tetrahedron");
        assertThat(getArea).isCloseTo(249.41, withPrecision(0.01));
        assertThat(checkVertexExist).isEqualTo(true);
        assertThat(checkVertexNumber).isEqualTo(4);
    }

    @Test
    void isCube() {
        Box box = new Box(8, 10);
        String name = box.whatsThis();
        double getArea = box.getArea();
        boolean checkVertexExist = box.isExist();
        int checkVertexNumber = box.getNumberOfVertices();
        assertThat(name).isEqualTo("Cube");
        assertThat(getArea).isCloseTo(600.0, withPrecision(0.01));
        assertThat(checkVertexExist).isEqualTo(true);
        assertThat(checkVertexNumber).isEqualTo(8);
    }

    @Test
    void isCubeWhenEdgeIs10() {
        Box box = new Box(8, 100);
        String name = box.whatsThis();
        double getArea = box.getArea();
        boolean checkVertexExist = box.isExist();
        int checkVertexNumber = box.getNumberOfVertices();
        assertThat(name).isEqualTo("Cube");
        assertThat(getArea).isCloseTo(60000.0, withPrecision(0.01));
        assertThat(checkVertexExist).isEqualTo(true);
        assertThat(checkVertexNumber).isEqualTo(8);
    }

    @Test
    void isUnknown() {
        Box box = new Box(12, 12);
        String name = box.whatsThis();
        double getArea = box.getArea();
        boolean checkVertexExist = box.isExist();
        int checkVertexNumber = box.getNumberOfVertices();
        assertThat(name).isEqualTo("Unknown object");
        assertThat(getArea).isCloseTo(0.0, withPrecision(0.01));
        assertThat(checkVertexExist).isEqualTo(false);
        assertThat(checkVertexNumber).isEqualTo(-1);
    }

    @Test
    void isUnknownWhenVertexIs0AndEdgeIs0() {
        Box box = new Box(0, 0);
        String name = box.whatsThis();
        double getArea = box.getArea();
        boolean checkVertexExist = box.isExist();
        int checkVertexNumber = box.getNumberOfVertices();
        assertThat(name).isEqualTo("Unknown object");
        assertThat(getArea).isCloseTo(0.0, withPrecision(0.01));
        assertThat(checkVertexExist).isEqualTo(false);
        assertThat(checkVertexNumber).isEqualTo(-1);
    }
}