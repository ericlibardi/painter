package org.academiadecodigo.unbitables;

import org.academiadecodigo.simplegraphics.graphics.Rectangle;
import org.academiadecodigo.unbitables.components.Pointer;
import org.academiadecodigo.unbitables.components.Scenary;
import org.academiadecodigo.unbitables.utilities.KeyboardLogic;
import org.academiadecodigo.unbitables.utilities.MouseLogic;

public class Game {

    private Scenary scenary;
    private Pointer pointer;


    public void init() {

        this.scenary = new Scenary();

        this.pointer = new Pointer();

        pointer.setScenary(scenary);

        KeyboardLogic keyboardLogic = new KeyboardLogic(pointer, scenary);
        keyboardLogic.init();

        MouseLogic mouseLogic = new MouseLogic(scenary);
        mouseLogic.init();

    }

}
