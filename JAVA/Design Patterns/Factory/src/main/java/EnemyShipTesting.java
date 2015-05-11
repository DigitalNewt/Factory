import java.util.Scanner;

/**
 * User: Brent Baker
 * Date: 10/15/13
 * Time: 2:05 PM
 */
public class EnemyShipTesting {
    public static void main(String[] args){

        // Create the factory object
        EnemyShipFactory shipFactory = new EnemyShipFactory();

        // Enemy ship object
        EnemyShip theEnemy = null;
        Scanner userInput = new Scanner(System.in);
        int test = 0;
        System.out.print("What type of ship? (U / R / B)" + String.valueOf(test));

        if (userInput.hasNextLine()){
            String typeOfShip = userInput.nextLine();
            theEnemy = shipFactory.makeEnemyShip(typeOfShip);

            if(theEnemy != null){
                doStuffEnemy(theEnemy);
            } else System.out.print("Please enter U, R, or B next time");
        }

        /*
          EnemyShip theEnemy = null;
          // Old way of creating objects
          // When we use new we are not being dynamic
          EnemyShip ufoShip = new UFOEnemyShip();
          doStuffEnemy(ufoShip);
          System.out.print("\n");

          // -----------------------------------------
          // This allows me to make the program more dynamic
          // It doesn't close the code from being modified
          // and that is bad!

          // Defines an input stream to watch: keyboard
          Scanner userInput = new Scanner(System.in);
          String enemyShipOption = "";
          System.out.print("What type of ship? (U or R)");

          if (userInput.hasNextLine()){
              enemyShipOption = userInput.nextLine();
          }

          if (enemyShipOption == "U"){
              theEnemy = new UFOEnemyShip();
          } else if (enemyShipOption == "R"){
              theEnemy = new RocketEnemyShip();
          } else {
              theEnemy = new BigUFOEnemyShip();
          }
          doStuffEnemy(theEnemy);
          // --------------------------------------------
          */
    }

    /**
     * Executes methods of the super class
     * @param anEnemyShip Enemy Ship Object
     */
    public static void doStuffEnemy(EnemyShip anEnemyShip){
        anEnemyShip.displayEnemyShip();
        anEnemyShip.followHeroShip();
        anEnemyShip.enemyShipShoots();
    }

}
