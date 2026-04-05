package model;

import component.Display;
import component.Door;
import component.Observer;
import panel.Panel;
import request.Request;

import java.util.*;

public class Elevator {

    private int id;
    private int currentFloor;
    private Direction direction;
    private ElevatorState state;

    private TreeSet<Integer> upStops = new TreeSet<>();
    private TreeSet<Integer> downStops = new TreeSet<>(Collections.reverseOrder());

    private Panel panel;
    private Door door;
    private Display display;

    private List<Observer> observers = new ArrayList<>();

    public Elevator(int id, int floors) {
        this.id = id;
        this.currentFloor = 0;
        this.direction = Direction.IDLE;
        this.state = ElevatorState.IDLE;

        this.panel = new Panel(floors);
        this.door = new Door();
        this.display = new Display();

        addObserver(display);
    }

    public void addObserver(Observer o) {
        observers.add(o);
    }

    private void notifyObservers() {
        for (Observer o : observers) {
            o.update(currentFloor, direction.toString());
        }
    }

    public void addRequest(Request req) {
        if (req.getDirection() == Direction.UP) {
            upStops.add(req.getDestinationFloor());
        } else {
            downStops.add(req.getDestinationFloor());
        }
    }

    public void move() {

        if (direction == Direction.UP) {

            if (!upStops.isEmpty()) {
                currentFloor = upStops.pollFirst();
            } else {
                direction = Direction.DOWN;
                return;
            }

        } else if (direction == Direction.DOWN) {

            if (!downStops.isEmpty()) {
                currentFloor = downStops.pollFirst();
            } else {
                direction = Direction.UP;
                return;
            }

        } else {
            if (!upStops.isEmpty()) {
                direction = Direction.UP;
                return;
            } else if (!downStops.isEmpty()) {
                direction = Direction.DOWN;
                return;
            } else {
                state = ElevatorState.IDLE;
                return;
            }
        }

        state = ElevatorState.MOVING;

        notifyObservers();
        door.open();
        door.close();
    }

    public boolean isIdle() {
        return state == ElevatorState.IDLE;
    }

    public int getCurrentFloor() {
        return currentFloor;
    }

    public Direction getDirection() {
        return direction;
    }

    public int getId() {
        return id;
    }
}