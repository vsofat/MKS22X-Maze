import java.util.*;
import java.io.*;
public class Maze{

    private char[][]maze;
    private boolean animate;//false by default

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
          int Ecounter;
          int Scounter;
          while (info.hasNextLine()) {
            String line = info.nextLine();
            for (int b = 0; b < x; b ++) {
              maze[a][b] = line.charAt(b);
              if (line.charAt(b) == "S"){
                SCounter++;
              }
              if (line.charAt(b) == "E"){
                ECounter++;
              }
            }
            a ++;
          }
        }
        catch (FileNotFoundException e) {
          System.out.println("Please recheck file name to make sure it is correct.");
        }
        if (Scounter != 1 || Ecounter != 1){
          throw IllegalStateException;
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
            return "WRITE THIS METHOD";
    }


    /*Wrapper Solve Function returns the helper function
      Note the helper function has the same name, but different parameters.
      Since the constructor exits when the file is not found or is missing an E or S, we can assume it exists.
    */
    public int solve(){
            //find the location of the S.

            //erase the S

            //and start solving at the location of the s.
            //return solve(???,???);
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
            wait(20);
        }

        //COMPLETE SOLVE
        return -1; //so it compiles
    }

} //closing
