package io.github.lucariatias.harmonicmoon.world;

public enum Direction {

    UP(0, -1), DOWN(0, 1), LEFT(-1, 0), RIGHT(1, 0);

    private Vector vector;

    private Direction(int i, int j) {
        this(new Vector(i, j));
    }

    private Direction(Vector vector) {
        this.vector = vector;
    }

    public Vector asVector() {
        return vector;
    }

}
