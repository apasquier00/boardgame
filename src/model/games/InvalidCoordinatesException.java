package model.games;

import java.util.List;

public class InvalidCoordinatesException extends Exception {

    private final List<Integer> COORDINATES;

    public List<Integer> getCoordinates() {
        return COORDINATES;
    }

    public InvalidCoordinatesException(String message, List<Integer> coordinates) {
        super(message);
        this.COORDINATES = coordinates;
    }
}
