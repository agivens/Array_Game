package givens_arrays;

import java.util.Scanner;
import java.util.Random;

public class Givens_Arrays {

    static boolean stillplaying;
    static boolean skillavailable;
    static int chanceleft = 3;
    static String moves;
    static int magicnumber;
    static char[][] grid = new char[10][10];
    static Scanner input = new Scanner(System.in);
    static Random rand = new Random();
    static Player user = new Player();
    static Coins thing = new Coins();
    static TreasureChest win = new TreasureChest();
    static Traps dmg = new Traps();

    public static void main(String[] args) {
        stillplaying = true;
        skillavailable = true;
        System.out.println("The @ is your player, the ^ are the traps. "
                + "Gather the treasure, T or c, to increase score and/or win. Please name your player.");
        user.name = input.nextLine();
        while (stillplaying) {
            moveAt();
            magicnumber = rand.nextInt(100);
        }
        while (chanceleft > 0) {
            skillavailable = true;
        }
        /*    System.out.println("Choose row(0-9):");
         int row = userinput.nextInt();
         System.out.println("Choose column(0-9):");
         int column = userinput.nextInt();
         grid[row][column] = 'x';*/

    }

    public static void printGrid() {
        grid[user.y][user.x] = user.symbol;
        grid[thing.y1][thing.x1] = thing.symbol;
        grid[thing.y2][thing.x2] = thing.symbol;
        grid[thing.y3][thing.x3] = thing.symbol;
        grid[win.y][win.x] = win.symbol;
        grid[dmg.y1][dmg.x1] = dmg.symbol;
        grid[dmg.y2][dmg.x2] = dmg.symbol;
        for (int x = 0; x <= grid[0].length - 1; x++) {
            for (int y = 0; y <= grid[1].length - 1; y++) {
                if (y < grid[1].length - 1) {
                    if (grid[x][y] != '@' && grid[x][y] != '^' && grid[x][y] != 't' && grid[x][y] != 'T'
                            && grid[x][y] != '#' && grid[x][y] != '&') {
                        System.out.print(".");
                    } else {
                        System.out.print(grid[x][y]);
                    }
                } else {
                    if (grid[x][y] != '@' && grid[x][y] != '^' && grid[x][y] != 't' && grid[x][y] != 'T'
                            && grid[x][y] != '#' && grid[x][y] != '&') {
                        System.out.println(".");
                    } else {
                        System.out.println(grid[x][y]);
                    }
                }
            }
        }
    }

    public static void moveAt() {
        user.checkHealthScore();
        checkWinLose();
        user.checkLevelSymbol();
        printGrid();
        System.out.println("Score: " + user.score);
        System.out.println("Level: " + user.level);
        System.out.println("Health: " + user.health);
        if (user.y == 0) {
            System.out.println(user.name + " is on the edge of the grid. They cannot go any farther.");
        } else {
            if (user.y == 9) {
                System.out.println(user.name + " is on the edge of the grid. They cannot go any farther.");
            }
        }
        if (user.x == 0) {
            System.out.println(user.name + " is on the edge of the grid. They cannot go any farther.");
        } else {
            if (user.x == 9) {
                System.out.println(user.name + " is on the edge of the grid. They cannot go any farther.");
            }
        }
        System.out.println(user.name + " is at the coordinates " + user.x + "," + user.y + " on a 11x10 grid.");
        System.out.println("Type 'N', 'S', 'E', or 'W' to move up, down, left, or right."
                + " Type 'skill' to use your skill."
                + " Type 'NE', 'NW', 'SE', or 'SW' to move diagonally.");
        moves = input.nextLine().toLowerCase();
        if (moves.contains("skill")) {
            user.useSkill();
        } else if (moves.contains("n")) {
            if (moves.contains("e")) {
                user.y--;
                user.x++;
            } else if (moves.contains("w")) {
                user.y--;
                user.x--;
            } else {
                user.y--;
            }
        } else if (moves.contains("s")) {
            if (moves.contains("e")) {
                user.y++;
                user.x++;
            } else if (moves.contains("w")) {
                user.y++;
                user.x--;
            } else {
                user.y++;
            }
        } else if (moves.contains("e")) {
            user.x++;

        } else {
            user.x--;
        }
    }

    public static void checkWinLose() {
        if (user.health == 0) {
            lose();
        }
        if (user.x == win.x && user.y == win.y) {
            win();
        }
    }

    public static void lose() {
        stillplaying = false;
        System.out.println("Final Score: " + user.score);
        System.out.println("Final Level: " + user.level);
        System.out.println("You lose.");
        System.exit(0);
    }

    public static void win() {
        stillplaying = false;
        System.out.println("Final Score: " + user.score);
        System.out.println("Final Level: " + user.level);
        System.out.println("You win.");
        System.exit(0);
    }

    static class Player {

        int y = 5;
        int x = 5;
        int score = 0;
        int health = 20;
        int level = 1;
        char symbol;
        String name;

        public int getY() {
            return y;
        }

        public int getX() {
            return x;
        }

        public int getScore() {
            return score;
        }

        public int getHealth() {
            return health;
        }

        public int getLevel() {
            return level;
        }

        public char getSymbol() {
            return symbol;
        }

        public String getName() {
            return name;
        }

        public void checkHealthScore() {
            if (x == thing.x1 && y == thing.y1) {
                score = score + 20;
            }
            if (x == thing.x2 && y == thing.x2) {
                score = score + 20;
            }
            if (x == thing.x3 && y == thing.x3) {
                score = score + 20;
            }
            if (x == dmg.x1 && y == dmg.y1) {
                health = health - 10;
            }
            if (x == dmg.x2 && y == dmg.y2) {
                health = health - 10;
            }
        }

        public void checkLevelSymbol() {
            if (score >= 30) {
                level = 2;
            }
            if (score >= 60) {
                level = 3;
            }
            if (level == 1) {
                symbol = '@';
            }
            if (level == 2) {
                symbol = '#';
            }
            if (level == 3) {
                symbol = '&';
            }
        }

        public void useSkill() {
            System.out.println("You have chosen to use your speed skill. You can now move 5 spaces in one turn."
                    + "Type 'N', 'S', 'E', or 'W' to move up, down, left, or right."
                    + " Type 'NE', 'NW', 'SE', or 'SW' to move diagonally.");
            System.out.println("You have " + chanceleft + " more chances left.");
            if (chanceleft > 0) {
                moveAt();
            } else {
                chances0();
            }
            if (moves.contains("n")) {
                if (moves.contains("e")) {
                    y = y - 5;
                    x = x + 5;
                } else if (moves.contains("w")) {
                    y = y - 5;
                    x = x - 5;
                } else {
                    y = y - 5;
                }
            } else if (moves.contains("s")) {
                if (moves.contains("e")) {
                    y = y + 5;
                    x = x + 5;
                } else if (moves.contains("w")) {
                    y = y + 5;
                    x = x - 5;
                } else {
                    y = y + 5;
                }
            } else if (moves.contains("e")) {
                x = x + 5;

            } else {
                x = x - 5;
            }

        }

        public void chances0() {
            skillavailable = false;
            System.out.println("Your three chances have all been used. Type 'okay' to take your turn.");
            if (moves.contains("okay")) {
                moveAt();
            }
        }
    }

    static class Coins {

        int y1 = 5;
        int x1 = 4;
        int y2 = 4;
        int x2 = 4;
        int y3 = 3;
        int x3 = 4;
        char symbol = 'c';

        public int getY1() {
            return y1;
        }

        public int getX1() {
            return x1;
        }

        public int getY2() {
            return y2;
        }

        public int getX2() {
            return x2;
        }

        public int getY3() {
            return y3;
        }

        public int getX3() {
            return x3;
        }

        public char getSymbol() {
            return symbol;
        }

    }

    static class TreasureChest {

        int y = 5;
        int x = 3;
        char symbol = 'T';

        public int getY() {
            return y;
        }

        public int getX() {
            return x;
        }

        public char getSymbol() {
            return symbol;
        }

    }

    static class Traps {

        int y1 = 5;
        int x1 = 6;
        int y2 = 6;
        int x2 = 6;
        char symbol = '^';

        public int getY1() {
            return y1;
        }

        public int getX1() {
            return x1;
        }

        public int getY2() {
            return y2;
        }

        public int getX2() {
            return x2;
        }

        public char getSymbol() {
            return symbol;
        }
    }
}
//To Format: Alt-Shift-F
