package panel;

import java.util.*;

public class Panel {

    private List<Button> buttons;

    public Panel(int floors) {
        buttons = new ArrayList<>();
        for (int i = 0; i < floors; i++) {
            buttons.add(new Button(i));
        }
    }

    public void pressButton(int floor) {
        buttons.get(floor).press();
    }
}