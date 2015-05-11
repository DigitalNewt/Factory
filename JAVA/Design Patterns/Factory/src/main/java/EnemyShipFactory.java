/**
 * User: Brent Baker
 * Date: 10/15/13
 * Time: 2:08 PM
 */
public class EnemyShipFactory {

    /**
     * This could be used as a static method if we
     * are willing to give up sub classing it
     * @param newShipType Ship Type
     * @return Enemy Ship Object or Null
     */
    public EnemyShip makeEnemyShip(String newShipType){
        EnemyShip newShip = null;

        if (newShipType.equals("U")){
            return new UFOEnemyShip();
        } else if (newShipType.equals("R")){
            return new RocketEnemyShip();
        } else if (newShipType.equals("B")){
            return new BigUFOEnemyShip();
        } else return null;
    }
}
