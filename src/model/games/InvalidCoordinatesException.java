package model.games;

import java.util.List;

public class InvalidCoordinatesException extends Exception {

    private List<Integer> coordinates;

    public List<Integer> getCoordinates() {
        return coordinates;
    }

    public InvalidCoordinatesException(String message, List<Integer> coordinates) {
        super(message);
        this.coordinates = coordinates;
    }
}
