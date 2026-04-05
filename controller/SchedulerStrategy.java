package controller;

import model.Elevator;
import request.Request;

import java.util.List;

public interface SchedulerStrategy {
    Elevator selectElevator(List<Elevator> elevators, Request request);
}