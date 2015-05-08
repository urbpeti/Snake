package snake;

import static org.junit.Assert.*;

import java.util.ArrayList;

import org.junit.Before;
import org.junit.Test;

import snake.Elem.elemtipus;

public class SnakeTest {
	
	Snake kigyo;
	
	@Before
	public void setUp(){
		kigyo = new Snake(new Game());
	}
	
	@Test
	public void rajtavanTest(){	
		boolean rajtavan = kigyo.rajtavan(new Elem(1,1,elemtipus.Test));
		boolean nincsrajta = kigyo.rajtavan(new Elem(2,2,elemtipus.Test));
		assertTrue(rajtavan);
		assertFalse(nincsrajta);
	}
	
	@Test
	public void jobbramozdul(){	
		ArrayList<Elem> hely = new ArrayList<Elem>();
		hely.add(new Elem(3, 1, elemtipus.Fejjobbra));
		hely.add(new Elem(2, 1, elemtipus.Test));
		hely.add(new Elem(1, 1, elemtipus.Test));
		kigyo.move();
		for(int i = 0; i<kigyo.body.size();i++){
			assertTrue(hely.get(i).equalsPlace(kigyo.body.get(i)));
		}
	}
}
