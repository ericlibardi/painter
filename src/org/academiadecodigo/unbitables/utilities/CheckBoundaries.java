package org.academiadecodigo.unbitables.utilities;

import org.academiadecodigo.unbitables.components.Pointer;
import org.academiadecodigo.unbitables.components.Scenary;

public class CheckBoundaries {

    Scenary background;

    public CheckBoundaries(Scenary background) {

        this.background = background;

    }

    public boolean checkPointerBoundaries(int pointerX, int pointerY, Directions directions) {



        switch (directions) {
            case UP:
                if (pointerY <= background.PADDING) {
                    return true;
                }
                break;
            case DOWN:
                if (pointerY + background.PIXELS >= background.getMaxY()) {
                    return true;
                }
                break;
            case LEFT:
                if (pointerX <= background.PADDING) {
                    return true;
                }
                break;
            case RIGHT:
                if (pointerX + background.PIXELS >= background.getMaxX()) {
                    return true;
                }
                break;

        }

        return false;
    }


}
