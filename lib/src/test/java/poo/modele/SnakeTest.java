package poo.modele;  

import org.junit.Test;
import static org.junit.Assert.*;

public class SnakeTest {

    @Test
    public void testNoCollision() {
        Snake<Double> snake = new Snake<>(0.0, 0.0);
        assertFalse("Il ne devrait y avoir aucune collision au début", snake.detecterAutoCollision());
    }

    @Test
    public void testSelfCollision() {
        Snake<Double> snake = new Snake<>(0.0, 0.0);
        snake.addLength(new Position<>(1.0, 0.0));
        snake.addLength(new Position<>(2.0, 0.0));
        snake.addLength(new Position<>(3.0, 0.0));
        snake.seDeplacer(new Position<>(2.0, 0.0));  
        assertTrue("Collision avec lui-même", snake.detecterAutoCollision());
    }

    @Test
    public void testCollisionWithOtherSnake() {
        Snake<Double> snake1 = new Snake<>(0.0, 0.0);
        Snake<Double> snake2 = new Snake<>(2.0, 0.0);
        snake1.seDeplacer(new Position<>(1.0, 0.0));  
        snake2.seDeplacer(new Position<>(1.0, 0.0));  
        assertTrue("Collision avec un autre serpent", snake1.detecterCollisionEntreSerpents(snake2));
    }
    @Test
    public void testAddLength() {
        Snake<Double> snake = new Snake<>(0.0, 0.0);
        snake.addLength(new Position<>(1.0, 0.0));
        assertEquals("La longueur doit être de 2 après l'ajout", 2, snake.getBody().size());
    }

    @Test
    public void testMove() {
        Snake<Double> snake = new Snake<>(0.0, 0.0);
        snake.seDeplacer(new Position<>(1.0, 0.0));
        assertEquals("La tête du serpent doit être à la nouvelle position", new Position<>(1.0, 0.0), snake.getHead().getPosition());
    }

}
