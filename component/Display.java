package component;

public class Display implements Observer {

    @Override
    public void update(int floor, String direction) {
        System.out.println("[Display] Floor: " + floor + " Direction: " + direction);
    }
}