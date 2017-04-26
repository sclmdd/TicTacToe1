import static java.lang.System.out;
import java.util.Scanner;
 
    public class TicTacToe
{
    static char dots = '.';
    static Scanner keyboard = new Scanner(System.in);

    public static void printWelcome()
	{
		  int game[][] = new int [3][3];
        game[0][0] = 1;
        game[0][1] = 2;
        game[0][2] = 3;
        game[1][0] = 4;
        game[1][1] = 5;
        game[1][2] = 6;
        game[2][0] = 7;
        game[2][1] = 8;
        game[2][2] = 9;
        System.out.println("Welcome to Tic Tac Toe!");
        System.out.println("");
        System.out.println("To play, enter a number for which box you want to play in: ");
        for (int rows = 0; rows < 3; rows++)
        {
            for(int columns = 0; columns < 3; columns++)
            {
                System.out.print(game[rows][columns] + " ");
            }
        System.out.println("");
        }
        System.out.println("You'll need a buddy to play!");
        System.out.println("X moves First");
	}
    
    public static void drawBoard(char[][] board)
    {
        System.out.println();
        for (int row = 0; row < 3; row++)
        {
            System.out.println(" " + board[row][0] + " | " + board[row][1] + " | " + board[row][2]);
        }
        System.out.println();
    }

    public static char[][] createEmptyBoard()
    {
        char[][] gameBoard = new char[3][3];
        for (int row = 0; row < 3; row++)
        {
            for (int col = 0; col < 3; col++)
            {
                gameBoard[row][col] = dots;
            }
        }
        return gameBoard;
    }
    
    public static boolean isWin(char[][] board)
    {
        return isHorizontalWin(board) || isVerticalWin(board) || isDiagonalWin(board);
    }
    
    public static boolean isFull(char[][] board) 
    {
        for (int row = 0; row < 3; row++) {
            for (int col = 0; col < 3; col++) {
                if (board[row][col] == dots) {
                    return false;
                }
            }
        }
        return true;
    }

    public static boolean isHorizontalWin(char[][] board)
    {
        for (int row = 0; row < 3; row++)
        {
            if (board[row][0] != dots && board[row][0] == board[row][1]
                    && board[row][1] == board[row][2])
            {
                return true;
            }
        }
        return false;
    }

    public static boolean isVerticalWin(char[][] board)
    {
        for (int col = 0; col < 3; col++)
        {
            if (board[0][col] != dots && board[0][col] == board[1][col]
                    && board[1][col] == board[2][col])
            {
                return true;
            }
        }
        return false;
    }

    public static boolean isDiagonalWin(char[][] board)
    {
        if (board[0][0] != dots && board[0][0] == board[1][1]
                && board[1][1] == board[2][2])
        {
            return true;
        }
        if (board[0][2] != dots && board[0][2] == board[1][1]
                && board[1][1] == board[2][0])
        {
            return true;
        }
        return false;
    }

    public static char getTokenAtPosition(int position, char[][] board)
    {
        int row = (position - 1) / 3;
        int col = (position - 1) % 3;
        return board[row][col];
    }
    
    public static void placeToken(int position, char[][] board, boolean isXTurn)
    {
        int row = (position - 1) / 3;
        int col = (position - 1) % 3;
        if (isXTurn) {
            board[row][col] = 'x';
        } else {
            board[row][col] = 'o';
        }
    }

    public static void getPositionAndPlaceToken(char[][] board, boolean isXTurn)
    {
        boolean invalidInput = true;
        boolean full = true;
        int position = 0;

        do {
            if (isXTurn) {
                System.out.print("x where? ");
            } else {
                System.out.print("o where? ");
            }
            position = keyboard.nextInt();
            invalidInput = (position < 1 || position > 9);
            if (invalidInput) {
                System.out.println("Sorry, position " + position + " is not valid.  Try 1-9.");
            } else {
                full = (getTokenAtPosition(position, board) != dots);
                if (full) {
                    System.out.println("Sorry, position " + position + " is already full.  Pick another.");
                }
            }
        } while (full || invalidInput);

        placeToken(position, board, isXTurn);
    }

    public static void game()
    {
        int xWins = 0;
        int oWins = 0;
        int draws = 0;
        boolean doesXStart = true;
        boolean isXTurn = doesXStart;
        printWelcome();
        do 
        {
            char[][] gameBoard = createEmptyBoard();
            isXTurn = doesXStart;
            doesXStart = ! doesXStart;
            boolean gameStillGoing = true;
            drawBoard(gameBoard);
            do {
                getPositionAndPlaceToken(gameBoard, isXTurn);
                drawBoard(gameBoard);
                if (isWin(gameBoard)) {
                    gameStillGoing = false;
                    if (isXTurn) {
                        xWins++;
                        System.out.println("X wins!");
                    } else {
                        oWins++;
                        System.out.println("O wins!");
                    }
                } else if (isFull(gameBoard)) {
                    gameStillGoing = false;
                    draws++;
                    System.out.println("The game is a draw.  Nobody wins.");
                } else {
                }
                isXTurn = ! isXTurn;
            } while (gameStillGoing);
            System.out.println("Score: X=" + xWins + ", O=" + oWins + ", draws=" + draws);
            
        } while (wantsToPlayAgain());
    }
    
    public static boolean wantsToPlayAgain()
    {
        System.out.print("Would you like to play again?");
        keyboard.nextLine();
        String answer = keyboard.nextLine();
        return (answer.equals("y"));
    }
    
    public static void main(String[] args)
    {
        game();
        System.out.println("Goodbye!");
    }
}

