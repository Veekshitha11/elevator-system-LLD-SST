package request;

import model.Direction;

public interface Request {
    int getSourceFloor();
    int getDestinationFloor();
    Direction getDirection();
}
