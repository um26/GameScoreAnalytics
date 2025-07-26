// import static org.junit.jupiter.api.Assertions.assertEquals;

// import org.junit.jupiter.api.Test;

import dao.GameDAO;
import dao.PlayerDAO;
import java.util.Scanner;

public class Main {
  public static void main(String[] args) {
    Scanner sc= new Scanner(System.in);
    boolean running = true;

    while (running){
      System.out.println("\n=== Game Score Analytics System ===");
      System.out.println("1. Add Player");
      System.out.println("2. Add Game Score");
      System.out.println("3. List Players");
      System.out.println("4. Show Top Players");
      System.out.println("5. Exit");
      System.out.print("Choose an option: ");
      int choice = sc.nextInt();
      sc.nextLine();

      try{
        switch (choice) {
          case 1:
            System.out.print("Enter player name: ");
            String name = sc.nextLine();
            PlayerDAO.insertPlayer(name);
            break;
            
          case 2:
            System.out.print("Enter player ID: ");
            int playerId = sc.nextInt();
            System.out.print("Enter score: ");
            int score = sc.nextInt();
            GameDAO.insertGame(playerId, score);
            break;
            
          case 3:
            PlayerDAO.listPlayers();
            break;
            
          case 4:
            GameDAO.showTopPlayers();
            break;
            
          case 5:
            running = false;
            break;
            
          default:
            System.out.println("Invalid option. Please try again.");
        }
      } catch (Exception e){
        e.printStackTrace();
      }
    }
    sc.close();
  }

  // @Test
  // void addition() {
  //     assertEquals(2, 1 + 1);
  // }
}