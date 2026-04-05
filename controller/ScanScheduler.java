package controller;

import model.Elevator;
import request.Request;

import java.util.List;

public class ScanScheduler implements SchedulerStrategy {

    @Override
    public Elevator selectElevator(List<Elevator> elevators, Request request) {

        Elevator best = null;
        int minDistance = Integer.MAX_VALUE;

        for (Elevator e : elevators) {

            int distance = Math.abs(e.getCurrentFloor() - request.getSourceFloor());

            if (e.getDirection() == request.getDirection() || e.isIdle()) {
                if (distance < minDistance) {
                    best = e;
                    minDistance = distance;
                }
            }
        }

        if (best == null) {
            best = elevators.get(0);
        }

        return best;
    }
}