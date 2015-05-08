package snake;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import snake.Elem.elemtipus;

public class GameTest {
	
	private Game jatek;

	@Before
	public void setUp(){
		jatek = new Game();
	}

	@Test
	public void ujAlmatest() {
		
		for(int i=0;i<10000;i++){
		jatek.newApple();
		if(jatek.alma.getX()>24)
			fail("Túl nagyot randomol x-nek");
		if(jatek.alma.getX()<0)
			fail("Túl kicsit randomol x-nek");
		if(jatek.alma.getY()>24)
			fail("Túl nagyot randomol y-nak");
		if(jatek.alma.getY()<0)
			fail("Túl kicsit randomol y-nak");
		if(jatek.snake.rajtavan(jatek.alma))
			fail("Rajta van a kígyón");
		}		
	}
	
	@Test
	public void checkAppletest(){
		jatek.alma = new Elem(2, 1, elemtipus.Alma);
		assertEquals(3, jatek.snake.body.size());
		jatek.checkApple();
		assertEquals(4, jatek.snake.body.size());
		assertFalse(jatek.alma.equalsPlace(new Elem(2,1, elemtipus.Alma)));	
	}
	
	@Test
	public void checktest(){
		
		int elorement = 0;
		while(jatek.snake.check()){
			jatek.snake.move();
			elorement++;
		}
		assertEquals(22, elorement);
	}
	
	
	

}
