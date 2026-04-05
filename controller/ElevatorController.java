package controller;

import model.Elevator;
import request.*;

import java.util.*;

public class ElevatorController {

    private List<Elevator> elevators;
    private Queue<Request> pendingRequests;
    private SchedulerStrategy strategy;

    public ElevatorController(int numElevators, int floors) {
        elevators = new ArrayList<>();
        pendingRequests = new LinkedList<>();
        strategy = new ScanScheduler();

        for (int i = 0; i < numElevators; i++) {
            elevators.add(new Elevator(i, floors));
        }
    }

    public void submitExternalRequest(ExternalRequest request) {
        pendingRequests.offer(request);
    }

    public void submitInternalRequest(int elevatorId, InternalRequest request) {
        elevators.get(elevatorId).addRequest(request);
    }

    public void assignElevator() {
        while (!pendingRequests.isEmpty()) {
            Request req = pendingRequests.poll();

            Elevator e = strategy.selectElevator(elevators, req);
            e.addRequest(req);
        }
    }

    public void step() {
        assignElevator();
        for (Elevator e : elevators) {
            e.move();
        }
    }
}