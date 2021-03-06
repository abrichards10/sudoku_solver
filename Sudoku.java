/* Group: Aaron Brion, Angela Richards, Kassy Pak
 *
 * Requirements:
 *      - must accept a partially filled Sudoku board from user (81 inputs)
 *      - generate completely filled board according to back tracking
 *      - must print correctly-generated Sudoku board
 * */

import java.util.Scanner;

public class Sudoku {
    Scanner input = new Scanner(System.in);
    private final int[][] arr;
    private final int end;

    public Sudoku() {
        arr = new int[9][9];
        end = arr.length;
        enterBoard();
    }

    /* allows users to input values sudoku board */
    private void enterBoard() {
        int num;

        for (int row=0; row<end; row++) {
            for (int column=0; column<end; column++) {
                do {
                    printBoard();
                    System.out.print("Please input a num (0 for blank or 1-9)\nfor ROW " + row + " COLUMN " + column + " : ");
                    num = input.nextInt();

                } while ( num!=0 && !isValid(num, row, column) );
                arr[row][column] = num;
            }
        }
        printBoard(); // prints before
        solveBoard();
        printBoard(); // prints after
    }

    /* checks if the input is a valid number and valid for the cell. */
    private boolean isValid(int userNum, int currRow, int currCol) {

        /* if the user input is not 0-9, return invalid */
        if(userNum<1 || userNum>9) {
            System.out.println("ONLY INPUT NUMBERS 0-9");
            return false;
        }
        if (!checkColumns(userNum, currRow)) {
            System.out.println("CONFLICT IN COLUMNS");
            return false;
        }
        if (!checkRows(userNum, currCol)) {
            System.out.println("CONFLICT IN ROWS");
            return false;
        }
        if (!checkSubGrid(userNum, currRow, currCol)) {
            System.out.println("CONFLICT IN SUBGRID");
            return false;
        }
        /* inputs valid num in cell and increments row/column respectively */
        return true;
    }

    private boolean checkColumns(int userNum, int currRow) {
        /* checks for conflict in column */
        for (int column=0; column<end; column++) {
            /* loops through current row's columns, return false if invalid num.*/
            if (arr[currRow][column] == userNum && arr[currRow][column]!=0) {
                return false;
            }
        }
        return true;
    }

    private boolean checkRows(int userNum, int currCol) {
        /* checks for conflict in row */
        for (int row=0; row<end; row++){
            /* loops through current column's rows, return false if invalid num.*/
            if (arr[row][currCol] == userNum && arr[row][currCol]!=0) {
                return false;
            }
        }
        return true;
    }

    private boolean checkSubGrid(int userNum, int currRow, int currCol) {
        int r = ( (currRow/3)*3);
        System.out.println(r);

        int c = ( (currCol/3)*3);
        System.out.println(c);

        int rowCount=1, colCount=1;

        for (; rowCount<3; r++){
            for (; colCount<3; c++) {
                System.out.println("ahahah");
                if (arr[r][c] == userNum && arr[r][c]!=0) {
                    System.out.println("uh oh");
                    return false;
                }
                colCount++;
            }
            colCount=1;
            rowCount++;
        }
        return true;
    }

    /* solves sudoku board recursively */
    private void solveBoard() {

    }

    /* displays sudoku board */
    public void printBoard() {
        System.out.println("---------------------------");

        for (int row=0; row<arr.length; row++) {
            /* prints the | on the left side for each new row */
            System.out.print(" | ");

            for (int column=0; column<arr.length; column+=3) {
                /* prints each 3 column numbers for the current row, and then separates at the end with | */
                System.out.print(arr[row][column] + " " + arr[row][column+1] + " " + arr[row][column+2] + " " + "| ");
            }

            /* makes a new line after one row of 9 numbers are filled */
            System.out.println();
            if(row%3==2) {
                /* adds a border bracket */
                System.out.println("---------------------------");
            }
        }
    }

    public static void main(String[] args) {
        Sudoku game = new Sudoku();
    }

}