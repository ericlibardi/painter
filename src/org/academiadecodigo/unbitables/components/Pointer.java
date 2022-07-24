package org.academiadecodigo.unbitables.components;

import org.academiadecodigo.simplegraphics.graphics.Color;
import org.academiadecodigo.simplegraphics.graphics.Rectangle;
import org.academiadecodigo.unbitables.utilities.CheckBoundaries;
import org.academiadecodigo.unbitables.utilities.Directions;

public class Pointer {

    private Rectangle pointer;
    private Scenary background;
    private CheckBoundaries checkBoundaries;
    private boolean isSpacePressed = false;

    public Pointer() {
        initPointer(Scenary.PADDING, Scenary.PADDING);
    }

    public void setScenary(Scenary background) {
        this.background = background;
        checkBoundaries = new CheckBoundaries(background);
    }

    public void initPointer(int x, int y) {
        pointer = new Rectangle(x, y, Scenary.PIXELS, Scenary.PIXELS);
        pointer.setColor(Color.GREEN);
        pointer.fill();
    }

    public void moveUp() {

        if (checkBoundaries.checkPointerBoundaries(pointer.getX(), pointer.getY(), Directions.UP)) {
            return;
        }

        pointer.translate(0, -Scenary.PIXELS);

        if (isSpacePressed) {
            paint();
        }
    }

    public void hidePointer() {
        pointer.delete();
    }

    public void moveDown() {
        if (checkBoundaries.checkPointerBoundaries(pointer.getX(), pointer.getY(), Directions.DOWN)) {
            return;
        }

        pointer.translate(0, Scenary.PIXELS);

        if (isSpacePressed) {
            paint();
        }
    }

    public void moveLeft() {
        if (checkBoundaries.checkPointerBoundaries(pointer.getX(), pointer.getY(), Directions.LEFT)) {
            return;
        }

        pointer.translate(-Scenary.PIXELS, 0);

        if (isSpacePressed) {
            paint();
        }
    }

    public void moveRight() {
        if (checkBoundaries.checkPointerBoundaries(pointer.getX(), pointer.getY(), Directions.RIGHT)) {
            return;
        }

        pointer.translate(Scenary.PIXELS, 0);

        if (isSpacePressed) {
            paint();
        }
    }

    public void spacePressed(boolean status) {

        isSpacePressed = status;

        if (status) {
            paint();
        }

    }

    public void paint() {

        int col = background.xToCol(pointer.getX());
        int row = background.yToRow(pointer.getY());

        background.paintSquare(col, row);

        hidePointer();

        initPointer(background.colToX(col), background.rowToY(row));
    }

    public int getX() {
        return pointer.getX();
    }

    public int getY() {
        return pointer.getY();
    }

}
