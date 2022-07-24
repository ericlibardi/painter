package org.academiadecodigo.unbitables.utilities;

import org.academiadecodigo.simplegraphics.mouse.Mouse;
import org.academiadecodigo.simplegraphics.mouse.MouseEvent;
import org.academiadecodigo.simplegraphics.mouse.MouseEventType;
import org.academiadecodigo.simplegraphics.mouse.MouseHandler;
import org.academiadecodigo.unbitables.components.Scenary;

public class MouseLogic implements MouseHandler {

    private Scenary scenario;
    private Mouse mouse;

    public MouseLogic (Scenary scenario) {

        this.scenario = scenario;
    }

    public void init() {
        this.mouse = new Mouse(this);
        mouse.addEventListener(MouseEventType.MOUSE_CLICKED);
    }


    @Override
    public void mouseClicked(MouseEvent mouseEvent) {
        scenario.changeColor(mouseEvent.getX(), mouseEvent.getY());
    }

    @Override
    public void mouseMoved(MouseEvent mouseEvent) {

    }
}
