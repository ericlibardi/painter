package org.academiadecodigo.unbitables.components;

import org.academiadecodigo.simplegraphics.graphics.Color;
import org.academiadecodigo.simplegraphics.graphics.Rectangle;

import java.io.*;
import java.util.ArrayList;

public class Scenary {

    public static final int PADDING = 10;
    public static final int PIXELS = 20;

    private Rectangle backGround;
    private ColorMenu colorMenu;

    private ArrayList<Square> squares = new ArrayList();

    public Scenary() {

        backGround = new Rectangle(PADDING, PADDING, 1200, 800);
        backGround.draw();
        drawSquares();

        colorMenu = new ColorMenu(getMaxX());

    }

    private void drawSquares() {

        try {
            FileReader reader = new FileReader("/Users/codecadet/workspace/txtFiles/saveFile.txt");

            BufferedReader bReader = new BufferedReader(reader);

            if (bReader.readLine() == null) {

                for (int i = 0; i < rows(); i++) {

                    for (int j = 0; j < cols(); j++) {

                        squares.add(new Square(j, i));
                    }
                }

                bReader.close();

                return;
            } else {
                load();
            }


        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }

    public void load() {

        squares.clear();

        String line = "";
        int row = 0;

        try {
            FileReader reader = new FileReader("/Users/codecadet/workspace/txtFiles/saveFile.txt");

            BufferedReader bReader = new BufferedReader(reader);

            while ((line = bReader.readLine()) != null) {

                for (int i = 0; i < line.length(); i++) {

                    String tempWord = java.lang.String.valueOf(line.substring(i, i + 1));

                    if (tempWord.equals("0")) {

                        squares.add(new Square(i, row));
                    } else if (tempWord.equals("1")) {

                        squares.add(new Square(i, row, true));
                    }

                }
                row++;
            }

        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }


    public int colToX(int col) {
        return col * PIXELS + PADDING;
    }

    public int rowToY(int row) {
        return row * PIXELS + PADDING;
    }

    public int xToCol(int x) {
        return (x - PADDING) / PIXELS;
    }

    public int yToRow(int y) {
        return (y - PADDING) / PIXELS;
    }

    public int cols() {
        return backGround.getWidth() / PIXELS;
    }

    public int rows() {
        return backGround.getHeight() / PIXELS;
    }

    public int getX() {
        return backGround.getX();
    }

    public int getY() {
        return backGround.getY();
    }

    public int getMaxX() {
        return backGround.getX() + backGround.getWidth();
    }

    public int getMaxY() {
        return backGround.getY() + backGround.getHeight();
    }


    public void paintSquare(int col, int row) {
        for (Square square : squares) {

            if (col == square.getCol() && row == square.getRow()) {
                square.paint();
                return;
            }
        }

    }

    public void clean() {
        for (Square square : squares) {
            square.clearSquare();
        }
    }

    public void save() {

        try {
            FileWriter writer = new FileWriter("/Users/codecadet/workspace/txtFiles/saveFile.txt");

            BufferedWriter bWriter = new BufferedWriter(writer, squares.size());

            for (Square square : squares) {


                if (square.isFilled()) {
                    bWriter.write(String.valueOf(1));
                } else {
                    bWriter.write(String.valueOf(0));
                }

                if (square.getCol() >= cols() - 1) {
                    bWriter.newLine();
                }

            }

            bWriter.flush();
            bWriter.close();


        } catch (IOException e) {
            throw new RuntimeException(e);
        }


    }

    public void changeColor(double x, double y) {

        Color chosenColor = colorMenu.checkColor(x, y);

    }

    private class Square {

        Rectangle square;

        Color currentColor;

        private int col;
        private int row;

        public Square(int col, int row) {

            init(col, row);
            this.col = col;
            this.row = row;
            currentColor = Color.BLUE;
        }

        public Square(int col, int row, boolean isPainted) {

            init(col, row);
            this.col = col;
            this.row = row;

            currentColor = Color.BLUE;

            if (isPainted) {
                paint();
            }

        }

        public int getCol() {
            return col;
        }

        public int getRow() {
            return row;
        }

        public void paint() {

            if (square.isFilled()) {
                square.setColor(Color.BLACK);
                square.draw();
            } else {
                square.setColor(currentColor);
                square.fill();
            }
        }

        private void init(int col, int row) {

            square = new Rectangle(colToX(col), rowToY(row), PIXELS, PIXELS);
            square.draw();

        }

        private void clearSquare() {
            if (square.isFilled()) {
                square.delete();
                square.setColor(Color.BLACK);
                square.draw();
            }
        }

        private boolean isFilled() {
            return square.isFilled();
        }

        private void changeColor(Color color) {
            currentColor = color;
        }

    }




}
