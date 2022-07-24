package org.academiadecodigo.unbitables.utilities;

import org.academiadecodigo.simplegraphics.keyboard.Keyboard;
import org.academiadecodigo.simplegraphics.keyboard.KeyboardEvent;
import org.academiadecodigo.simplegraphics.keyboard.KeyboardEventType;
import org.academiadecodigo.simplegraphics.keyboard.KeyboardHandler;
import org.academiadecodigo.unbitables.components.Pointer;
import org.academiadecodigo.unbitables.components.Scenary;

import java.util.Set;
import java.util.TreeSet;

import static org.academiadecodigo.simplegraphics.keyboard.KeyboardEvent.*;

public class KeyboardLogic implements KeyboardHandler {

    private Pointer pointer;
    private Scenary background;

    private Keyboard keyboard;
    private Set<Integer> pressedKeys = new TreeSet<>();

    public KeyboardLogic(Pointer pointer, Scenary background) {

        this.pointer = pointer;
        this.background = background;

    }

    public void init() {

        keyboard = new Keyboard(this);

        KeyboardEvent up = new KeyboardEvent();
        up.setKey(KEY_W);
        up.setKeyboardEventType(KeyboardEventType.KEY_PRESSED);

        KeyboardEvent down = new KeyboardEvent();
        down.setKey(KEY_S);
        down.setKeyboardEventType(KeyboardEventType.KEY_PRESSED);

        KeyboardEvent left = new KeyboardEvent();
        left.setKey(KEY_A);
        left.setKeyboardEventType(KeyboardEventType.KEY_PRESSED);

        KeyboardEvent right = new KeyboardEvent();
        right.setKey(KEY_D);
        right.setKeyboardEventType(KeyboardEventType.KEY_PRESSED);

        KeyboardEvent paintPress = new KeyboardEvent();
        paintPress.setKey(KEY_SPACE);
        paintPress.setKeyboardEventType(KeyboardEventType.KEY_PRESSED);

        KeyboardEvent paintRelease = new KeyboardEvent();
        paintRelease.setKey(KEY_SPACE);
        paintRelease.setKeyboardEventType(KeyboardEventType.KEY_RELEASED);

        KeyboardEvent clear = new KeyboardEvent();
        clear.setKey(KEY_C);
        clear.setKeyboardEventType(KeyboardEventType.KEY_PRESSED);

        KeyboardEvent save = new KeyboardEvent();
        save.setKey(KEY_B);
        save.setKeyboardEventType(KeyboardEventType.KEY_PRESSED);

        KeyboardEvent load = new KeyboardEvent();
        load.setKey(KEY_L);
        load.setKeyboardEventType(KeyboardEventType.KEY_PRESSED);

        keyboard.addEventListener(up);
        keyboard.addEventListener(down);
        keyboard.addEventListener(left);
        keyboard.addEventListener(right);
        keyboard.addEventListener(paintPress);
        keyboard.addEventListener(paintRelease);
        keyboard.addEventListener(clear);
        keyboard.addEventListener(save);
        keyboard.addEventListener(load);

    }


    @Override
    public void keyPressed(KeyboardEvent keyboardEvent) {

        switch (keyboardEvent.getKey()) {
            case KEY_W:
                pointer.moveUp();
                break;
            case KEY_S:
                pointer.moveDown();
                break;
            case KEY_A:
                pointer.moveLeft();
                break;
            case KEY_D:
                pointer.moveRight();
                break;
            case KEY_SPACE:
                if (pressedKeys.contains(keyboardEvent.getKey())) {
                    return;
                }
                pointer.spacePressed(true);
                pressedKeys.add(keyboardEvent.getKey());
                break;
            case KEY_C:
                background.clean();
                break;
            case KEY_B:
                background.save();
                break;
            case KEY_L:
                int x = pointer.getX();
                int y = pointer.getY();

                background.load();

                pointer.hidePointer();
                pointer.initPointer(x, y);

                break;
        }


    }

    @Override
    public void keyReleased(KeyboardEvent keyboardEvent) {
        switch (keyboardEvent.getKey()) {

            case KEY_SPACE:

                if (pressedKeys.contains(keyboardEvent.getKey())) {
                    pressedKeys.remove(keyboardEvent.getKey());
                }

                pointer.spacePressed(false);
                break;

        }
    }
}
