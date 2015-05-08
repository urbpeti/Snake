package snake;

import java.io.Serializable;
import java.util.ArrayList;

import snake.Elem.elemtipus;

@SuppressWarnings("serial")
public class Snake implements Serializable {
	
	//irányok
	public enum directions {
		Right, Left, Up ,Down
	};
	
	public ArrayList<Elem> body;
	private directions irany;
	public boolean iranyitva;
	private transient Game game;
	
	//konstruktor
	public Snake(Game game) {
		body = new ArrayList<Elem>();
		body.add(new Elem(2,1,elemtipus.Fejjobbra));
		body.add(new Elem(1,1,elemtipus.Test));
		body.add(new Elem(0,1,elemtipus.Test));
		
		this.setGame(game);
		irany = directions.Right;
	}
	
	
	/**
	 * A kígyó mozgásáért felelõs függvény.
	 * A kígyó fejét a megfelelõ irányba tovább teszi.
	 * Ezek után a test elemeit az elöttük lévõ helyére teszi.
	 */
	public void  move(){
		int x,y;
		switch (irany) {
		case Left:
			x = -1;
			y = 0;
			body.get(0).setType(elemtipus.Fejbalra);
			break;
		case Right:
			x = 1;
			y = 0;
			body.get(0).setType(elemtipus.Fejjobbra);
			break;
		case Up:
			y = -1;
			x = 0;
			body.get(0).setType(elemtipus.Fejfel);
			break;
		case Down:
			y = 1;
			x = 0;
			body.get(0).setType(elemtipus.Fejle);
			break;
		default:
			x=0;
			y=0;
			break;
		}
		//test
		for(int i = body.size()-1; i>0;i--){
			body.get(i).copy(body.get(i-1));
		}
		//fej
		body.get(0).setX(body.get(0).getX()+x);
		body.get(0).setY(body.get(0).getY()+y);
		iranyitva = false;
	}
	
	/**
	 * Megnézi hogy egy elem rajta van-e a kígyó testén
	 * @param e A vizsgálandó elem
	 * @return igaz ha rajta van
	 */
	public boolean rajtavan(Elem e){
		for (Elem elem : body) {
			if(elem.equalsPlace(e))
				return true;
		}
		return false;
	}
	
	/**
	 * Új elemet add a kígyó testéhez
	 * @param uj Hozzáadandó elem.
	 */
	public void add(Elem uj){
		body.add(uj);
	}
	
	/**
	 * Beállítja hogy merre fog menni a kígyó.
	 * @param irany Ezt az irányt állítja be.
	 */
	 public void setIrany(directions irany){
		this.irany =irany;
	}
	 
	 /**
	  * Visszaadja az aktuális megadott irányt
	  * @return Aktuális irány
	  */
	 public directions getIrany(){
		 return irany;
	 }


	/**
	 * Visszadaja az aktuális Game-et
	 * @return Aktuális Game
	 */
	public Game getGame() {
		return game;
	}


	/**
	 *  Beállítja az aktuális Game-et
	 * @param game Aktuális Game
	 */
	public void setGame(Game game) {
		this.game = game;
	}


	/**
	 * Megvizsgálja hogy a kígyó olyan helyre lépett-e ahol még folytatódhat a
	 * játék.
	 * 
	 * @param game TODO
	 * @return Játékban van-e még a kígyó.
	 */
	public boolean check() {
		boolean inGame = true;
	
		for (int i = body.size() - 1; i > 0; i--) {
			if (body.get(i).equalsPlace(body.get(0)))
				inGame = false;
		}
	
		if (body.get(0).getX() < 0)
			inGame = false;
		else if (body.get(0).getY() < 0)
			inGame = false;
		else if (body.get(0).getX() >= Game.maxSzel)
			inGame = false;
		else if (body.get(0).getY() >= Game.maxMag)
			inGame = false;
		return inGame;
	}
	
}
