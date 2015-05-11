import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import static org.junit.Assert.*;

/**
 * User: Brent Baker
 * Date: 2/9/15
 * Time: 8:56 PM
 */
public class TestFactoryPattern {

    private final ByteArrayOutputStream outContent = new ByteArrayOutputStream();

    @Before
    public void setUpStreams() {
            System.setOut(new PrintStream(outContent));
    }

    @After
    public void cleanUpStreams() {
        System.setOut(null);
    }

    @Test
    public void testUFOEnemyShip() {
        EnemyShip newShip = new UFOEnemyShip();
        String shipName = "UFO Enemy Ship";
        double damage = 20.0;

        assertEquals(shipName, newShip.getName());
        assertEquals(damage, newShip.getDamage(), 0);

        outContent.reset();
        newShip.displayEnemyShip();
        assertEquals(shipName + " is on the screen", outContent.toString().trim());
        outContent.reset();
        newShip.followHeroShip();
        assertEquals(shipName + " is following the hero", outContent.toString().trim());

        outContent.reset();
        newShip.enemyShipShoots();
        assertEquals(shipName + " attacks and does " + String.valueOf(damage) + " damage to hero", outContent.toString().trim());
    }

    @Test
    public void testRocketEnemyShip() {
        EnemyShip newShip = new RocketEnemyShip();
        String shipName = "Rocket Enemy Ship";
        double damage = 10.0;

        assertEquals(shipName, newShip.getName());
        assertEquals(damage, newShip.getDamage(), 0);

        outContent.reset();
        newShip.displayEnemyShip();
        assertEquals(shipName + " is on the screen", outContent.toString().trim());
        outContent.reset();
        newShip.followHeroShip();
        assertEquals(shipName + " is following the hero", outContent.toString().trim());

        outContent.reset();
        newShip.enemyShipShoots();
        assertEquals(shipName + " attacks and does " + String.valueOf(damage) + " damage to hero", outContent.toString().trim());
    }

    @Test
    public void testBigUFOEnemyShip() {
        EnemyShip newShip = new BigUFOEnemyShip();
        String shipName = "Big UFO Enemy Ship";
        double damage = 40.0;

        assertEquals(shipName, newShip.getName());
        assertEquals(damage, newShip.getDamage(), 0);

        outContent.reset();
        newShip.displayEnemyShip();
        assertEquals(shipName + " is on the screen", outContent.toString().trim());
        outContent.reset();
        newShip.followHeroShip();
        assertEquals(shipName + " is following the hero", outContent.toString().trim());

        outContent.reset();
        newShip.enemyShipShoots();
        assertEquals(shipName + " attacks and does " + String.valueOf(damage) + " damage to hero", outContent.toString().trim());
    }

    @Test
    public  void testEnemyShipFactory() {
        EnemyShipFactory shipFactory = new EnemyShipFactory();

        EnemyShip newShip = shipFactory.makeEnemyShip("U");
        assertEquals("UFO Enemy Ship", newShip.getName());
        assertEquals(20.0, newShip.getDamage(), 0);

        newShip = shipFactory.makeEnemyShip("R");
        assertEquals("Rocket Enemy Ship", newShip.getName());
        assertEquals(10.0, newShip.getDamage(), 0);

        newShip = shipFactory.makeEnemyShip("B");
        assertEquals("Big UFO Enemy Ship", newShip.getName());
        assertEquals(40.0, newShip.getDamage(), 0);

        newShip = shipFactory.makeEnemyShip("Z");
        assertNull(newShip);
    }
}
