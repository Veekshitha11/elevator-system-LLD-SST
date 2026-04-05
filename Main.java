import controller.ElevatorController;
import model.Direction;
import request.ExternalRequest;
import request.InternalRequest;

public class Main {

    public static void main(String[] args) {

        ElevatorController controller = new ElevatorController(3, 10);

        controller.submitExternalRequest(new ExternalRequest(2, Direction.UP));
        controller.submitExternalRequest(new ExternalRequest(7, Direction.DOWN));

        controller.submitInternalRequest(0, new InternalRequest(0, 5));

        for (int i = 0; i < 6; i++) {
            System.out.println("---- STEP " + i + " ----");
            controller.step();
        }
    }
}