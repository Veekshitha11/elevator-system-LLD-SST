package request;

import model.Direction;

public class InternalRequest implements Request {

    private int sourceFloor;
    private int destinationFloor;

    public InternalRequest(int sourceFloor, int destinationFloor) {
        this.sourceFloor = sourceFloor;
        this.destinationFloor = destinationFloor;
    }

    @Override
    public int getSourceFloor() {
        return sourceFloor;
    }

    @Override
    public int getDestinationFloor() {
        return destinationFloor;
    }

    @Override
    public Direction getDirection() {
        return destinationFloor > sourceFloor ? Direction.UP : Direction.DOWN;
    }
}