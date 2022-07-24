package org.academiadecodigo.unbitables.components;

import org.academiadecodigo.simplegraphics.graphics.Color;
import org.academiadecodigo.simplegraphics.graphics.Rectangle;

import java.util.ArrayList;

public class ColorMenu {

    private final int SQUARESIZE = 40;

    private Rectangle colorMenu;
    private ArrayList<Squares> colours;
    private ArrayList<Color> possibleColours;
    private int menuX;

    public ColorMenu(int maxX) {

        this.menuX = maxX + Scenary.PADDING;

        possibleColours = new ArrayList<>();
        possibleColors();

        System.out.println("possible colours" + possibleColours.size());

        colours = new ArrayList<>();
        drawSquares();

        colorMenu = new Rectangle(maxX + Scenary.PADDING, Scenary.PADDING, SQUARESIZE, possibleColours.size() * SQUARESIZE);
        colorMenu.draw();


    }

    public void drawSquares() {

        for (int i = 0; i < possibleColours.size(); i++) {

            colours.add(new Squares(possibleColours.get(i), i));

        }

    }

    public Color checkColor(double x, double y) {

        //System.out.println(y - 18);
        //System.out.println(colorMenu.getHeight());
        //System.out.println(colorMenu.getY() + colorMenu.getHeight());

        int yIntoRow = (int) ((y - 18)/ SQUARESIZE); //- Scenary.PADDING;


        if (x >= colorMenu.getX() &&
                x < colorMenu.getX() + colorMenu.getWidth() &&
                y - 20 > colorMenu.getY() &&
                y - 20 <= colorMenu.getY() + colorMenu.getHeight()) {

            System.out.println(yIntoRow);

            /*for (Squares color : colours) {

                if ((color.row * SQUARESIZE) + Scenary.PADDING < x) {

                    System.out.println(color.color);

                    break;

                }

            }*/

        }

        return Color.RED;

    }

    private void possibleColors() {

        possibleColours.add(Color.BLACK);
        possibleColours.add(Color.BLUE);
        possibleColours.add(Color.MAGENTA);
        possibleColours.add(Color.GREEN);
        possibleColours.add(Color.ORANGE);
        possibleColours.add(Color.WHITE);
        possibleColours.add(Color.CYAN);
        possibleColours.add(Color.DARK_GRAY);
        possibleColours.add(Color.GRAY);
        possibleColours.add(Color.LIGHT_GRAY);
        possibleColours.add(Color.PINK);
        possibleColours.add(Color.RED);
        possibleColours.add(Color.YELLOW);


    }

    private class Squares {

        private Color color;
        private Rectangle square;
        private int row;

        private Squares(Color color, int i) {
            this.row = i;
            this.color = color;

            square = new Rectangle(menuX, Scenary.PADDING + (SQUARESIZE * i), SQUARESIZE, SQUARESIZE);
            square.setColor(color);
            square.fill();
        }


    }

}
