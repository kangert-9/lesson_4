package lesson_4;

import java.util.Random;
import java.util.Scanner;

public class MyFirstGame {

    private static final int SIZE = 5;
    private static final int DOTS_TO_WIN = 4;
    private static final char DOT_EMPTY = '-';
    private static final char DOT_X = 'X';
    private static final char DOT_O = 'O';
    private static char[][] map;
    private static final Scanner sc = new Scanner(System.in);
    private static final Random rand = new Random();

    public static void main(String[] args) {
        initMap();
        printMap();
        while (true) {
            humanTurn();
            printMap();
            if (isWin(DOT_X)){
                System.out.println("Вы выиграли, УРА!");
                break;
            }
            if (isMapFull()){
                System.out.println("Ничья");
                break;
            }
            aiTurn();
            printMap();
            if (isWin(DOT_O)){
                System.out.println("Выиграл искусственный интеллект, увы...");
                break;
            }
            if (isMapFull()){
                System.out.println("Ничья");
                break;
            }
        }
        System.out.println("Игра окончена");
    }

    private static void initMap() {
        map = new char[SIZE][SIZE];
        for (int i = 0; i < SIZE; i++) {
            for (int j = 0; j < SIZE; j++) {
                map[i][j] = DOT_EMPTY;
            }
        }
    }

    private static void printMap() {
        for (int i = 0; i <= SIZE; i++) {
            System.out.print(i + " ");
        }
        System.out.println();
        for (int i = 0; i < SIZE; i++) {
            System.out.print(i + 1 + " ");
            for (int j = 0; j < SIZE; j++) {
                System.out.print(map[i][j]+" ");
            }
            System.out.println();
        }
        System.out.println();
    }

    private static void humanTurn() {
        int x, y;
        do {
            System.out.println("Введите координату по вертикали");
            x = sc.nextInt() - 1;
            System.out.println("Введите координату по горизонтали");
            y = sc.nextInt() - 1;
        } while (isCellInvalid(x, y));
        map[y][x] = DOT_X;
    }

    private static boolean isCellInvalid(int x, int y) {
        return (x < 0 || x >= SIZE)
                || (y < 0 || y >= SIZE)
                || map[y][x] != DOT_EMPTY;
    }


    private static boolean isWin(char symb) {
           int hor = 0;
           int vert = 0;
           int diag_one = 0;
           int diag_two = 0;
           for (int i = 0; i < SIZE; i++) {
               for (int j = 0; j < SIZE; j++) {
                   if(map[i][j] == symb) {
                       hor++;
                       if(hor == DOTS_TO_WIN) {
                           return true;
                       }
                       } else {
                           hor=0;
                       }
                   if(map[j][i] == symb) {
                       vert++;
                       if(vert == DOTS_TO_WIN) {
                           return true;
                       }
                       } else {
                           vert=0;
                       }
                   if (i==j) {
                       if (map[i][j] == symb) {
                           diag_one++;
                           if (diag_one == DOTS_TO_WIN) {
                               return true;
                           }
                       } else {
                           diag_one = 0;
                       }
                   }
                   if (i+j==(SIZE-1)) {
                       if (map[i][j] == symb) {
                           diag_two++;
                           if (diag_two == DOTS_TO_WIN) {
                               return true;
                           }
                       } else {
                           diag_two = 0;
                       }
                   }
                   }
                   }
           return false;
           }

    private static boolean isMapFull() {
        for (int i = 0; i < SIZE; i++) {
            for (int j = 0; j < SIZE; j++) {
                if (map[i][j] == DOT_EMPTY) {
                    return false;
                }
            }
        }
        return true;
    }

    private static void aiTurn() {
        int x, y;
        do {
            x = rand.nextInt(SIZE);
            y = rand.nextInt(SIZE);
        } while (isCellInvalid(x, y));
        System.out.printf("Компьютер походил в точку %d %d\n", x + 1, y + 1);
        map[y][x] = DOT_O;
    }
}