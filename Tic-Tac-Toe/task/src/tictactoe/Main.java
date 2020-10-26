package tictactoe;

import java.util.Arrays;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);

        var input = scanner.nextLine();
        char[][] matrix = printTable(input.toCharArray());


        while (true) {
            input = scanner.nextLine();
            var cells = validateInput(input.toCharArray());

            if (cells.length == 0)
                continue;

            if(IsMatrixOccupied(cells[0], cells[1], matrix))
                continue;

            matrix[cells[0]][cells[1]] = 'X';

            printTable(matrix);
            break;
        }

        /*System.out.println(getState(matrix));*/

    }

    private static boolean IsMatrixOccupied(int x, int y, char[][] matrix)
    {
        var cell = matrix[x][y];
        if(cell != '_')
        {
            System.out.println("This cell is occupied! Choose another one!");
            return true;
        }

        return false;
    }

    private static int[] validateInput(char[] input) {


        try {
            int[] value = new int[2];

            int col = Integer.parseInt(String.valueOf(input[0]));
            int row = Integer.parseInt(String.valueOf(input[2]));

            if (row > 3 || col > 3) {
                System.out.println("Coordinates should be from 1 to 3!");
                return new int[0];
            }

            value[0] = (row == 2)
                    ? 1
                    : (row == 3)
                    ? 0
                    : 2;

            value[1] = col - 1;

            return value;
        }
        catch (Exception ignore){
            System.out.println("You should enter numbers!");
            return new int[0];
        }

    }

    private static char[][] printTable(char[] input) {
        char[][] matrix = new char[3][3];

        System.out.println("---------");
        for (int i = 0; i < 3; i++) {
            System.out.print("| ");
            for (int j = 0; j < 3; j++) {
                var c = input[i * 3 + j];
                matrix[i][j] = c;
                System.out.print(c);
                System.out.print(' ');
            }
            System.out.print("|");
            System.out.println();
        }
        System.out.println("---------");

        return matrix;
    }

    private static char[][] printTable(char[][] matrix) {

        System.out.println("---------");
        for (int i = 0; i < 3; i++) {
            System.out.print("| ");
            for (int j = 0; j < 3; j++) {
                System.out.print(matrix[i][j]);
                System.out.print(' ');
            }
            System.out.print("|");
            System.out.println();
        }
        System.out.println("---------");

        return matrix;
    }

    static String getState(char[][] matrix)
    {
        var state = "";


        for (int i = 0; i < 3; i++) {
            // rows
            var a = matrix[i][0];
            var b = matrix[i][1];
            var c = matrix[i][2];
            if (a == b && b == c)
                state = String.format("%c wins", a);

            // columns
            a = matrix[0][i];
            b = matrix[1][i];
            c = matrix[2][i];
            if (a == b && b == c)
                if (state.equals("")) {
                    state = String.format("%c wins", a);
                } else {
                    return "Impossible";
                }
        }

        var a = matrix[0][0];
        var b = matrix[1][1];
        var c = matrix[2][2];
        if (a == b && b == c)
            if (state.equals("")) {
                state = String.format("%c wins", a);
            } else {
                return "Impossible";
            }

        a = matrix[0][2];
        b = matrix[1][1];
        c = matrix[2][0];
        if (a == b && b == c)
            if (state.equals("")) {
                state = String.format("%c wins", a);
            } else {
                return "Impossible";
            }

        var countX=0;
        var countO=0;
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                if(matrix[i][j] == 'X')
                    countX++;
                else if(matrix[i][j] == 'O')
                    countO++;
                else if(matrix[i][j] == '_' && state.equals(""))
                {
                    state = "Game not finished";
                }
            }
        }

        if(countX + 1 < countO || countO + 1 < countX)
            return "Impossible";


        return (state.equals("")) ? "Draw" : state;
    }


}
