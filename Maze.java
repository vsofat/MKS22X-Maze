import java.util.*;
import java.io.*;
public class Maze{

    private char[][]maze;
    private boolean animate;//false by default
    private int Ecounter, Scounter;
    private int[][] moves;
    private int count;

    public static void main(String[] args) throws FileNotFoundException{
      try{
      Maze one = new Maze("Maze1.txt");
      Maze two = new Maze("Maze2.txt");
      Maze three = new Maze("Maze3.txt");
      //System.out.println(one.solve());
      //System.out.println(one);
      System.out.println(two.solve());
      System.out.println(two);
      //System.out.println(three.solve());
      //System.out.println(three);
    }
    catch (FileNotFoundException e){
      System.out.println("File not found");
    }
    }

    /*Constructor loads a maze text file, and sets animate to false by default.

      1. The file contains a rectangular ascii maze, made with the following 4 characters:
      '#' - Walls - locations that cannot be moved onto
      ' ' - Empty Space - locations that can be moved onto
      'E' - the location of the goal (exactly 1 per file)
      'S' - the location of the start(exactly 1 per file)

      2. The maze has a border of '#' around the edges. So you don't have to check for out of bounds!

      3. When the file is not found OR the file is invalid (not exactly 1 E and 1 S) then:
         throw a FileNotFoundException or IllegalStateException
    */
    public Maze(String filename) throws FileNotFoundException{
        animate = false;
        int countS = 0;
        int countE = 0;
        moves = new int[][] {{0, 1}, {1, 0}, {0, -1}, {-1, 0}};
        try {
          File data = new File(filename);
          Scanner info = new Scanner(data);
          int x = 0;
          int y = 0;
          while (info.hasNextLine()) {
            x = info.nextLine().length(); // rows
            y ++; //colums
          }
          maze = new char[y][x];
          info = new Scanner(data);
          int a = 0;
          while (info.hasNextLine()) {
            String line = info.nextLine();
            for (int b = 0; b < x; b ++) {
              maze[a][b] = line.charAt(b);
              if (line.charAt(b) == 'S'){
                countS++;
              }
              if (line.charAt(b) == 'E'){
                countE++;
              }
            }
            a ++;
          }
        }
        catch (FileNotFoundException e) {
          System.out.println("Please recheck file name to make sure it is correct.");
        }
        if (countS != 1 || countE != 1){
          throw new IllegalStateException("The given file does not a start and exit point. Please make sure file only has one S, marking start, and one E, marking the exit.");
        }

    }

    private void wait(int millis){
         try {
             Thread.sleep(millis);
         }
         catch (InterruptedException e) {
         }
     }

    public void setAnimate(boolean b){
        animate = b;
    }

    public void clearTerminal(){
        //erase terminal, go to top left of screen.
        System.out.println("\033[2J\033[1;1H");
    }

   /*Return the string that represents the maze.
     It should look like the text file with some characters replaced.
    */

    public String toString(){
      String result = "";
      for (int row =0 ; row < maze.length; row++){
        for (int col=0 ; col < maze[0].length; col++){
          result += maze[row][col];
        }
        result += "\n";
      }
      return result;
    }

    /*Wrapper Solve Function returns the helper function
      Note the helper function has the same name, but different parameters.
      Since the constructor exits when the file is not found or is missing an E or S, we can assume it exists.
    */
    public int solve(){
      int colVal = 0;
      int rowVal = 0;
      for (int row = 0 ; row < maze.length; row++){
        for (int col = 0 ; col < maze[0].length; col++){
          if(maze[row][col] == 'S'){
            maze[row][col] = ' ';
            rowVal = row;
            colVal = col;
          }
        }
      }
      return solve(rowVal,colVal);
    }

    /*
      Recursive Solve function:

      A solved maze has a path marked with '@' from S to E.

      Returns the number of @ symbols from S to E when the maze is solved,
      Returns -1 when the maze has no solution.

      Postcondition:
        The S is replaced with '@' but the 'E' is not.
        All visited spots that were not part of the solution are changed to '.'
        All visited spots that are part of the solution are changed to '@'
    */

    private int solve(int row, int col){ //you can add more parameters since this is private
        //automatic animation! You are welcome.
        if(animate){
            clearTerminal();
            System.out.println(this);
            wait(140);
        }
        if (maze[row][col] == 'E'){
          return 1;
        }
        else if (maze[row][col] == ' '){
          maze[row][col] = '@';
          count++;
          for (int i = 0; i < 4; i++){
            int newRow = row + moves[i][0];
            int newCol = col + moves[i][1];
            if (solve(newRow,newCol) != -1){
              return count;
            }
          }
          maze[row][col] = '.'; 
          count--;
        }

        //COMPLETE SOLVE
        return -1; //so it compiles
    }

} //closing
