package snake;

import java.io.Serializable;
import java.util.ArrayList;

import snake.Elem.elemtipus;

@SuppressWarnings("serial")
public class Snake implements Serializable {
	
	//ir�nyok
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
	 * A k�gy� mozg�s��rt felel�s f�ggv�ny.
	 * A k�gy� fej�t a megfelel� ir�nyba tov�bb teszi.
	 * Ezek ut�n a test elemeit az el�tt�k l�v� hely�re teszi.
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
	 * Megn�zi hogy egy elem rajta van-e a k�gy� test�n
	 * @param e A vizsg�land� elem
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
	 * �j elemet add a k�gy� test�hez
	 * @param uj Hozz�adand� elem.
	 */
	public void add(Elem uj){
		body.add(uj);
	}
	
	/**
	 * Be�ll�tja hogy merre fog menni a k�gy�.
	 * @param irany Ezt az ir�nyt �ll�tja be.
	 */
	 public void setIrany(directions irany){
		this.irany =irany;
	}
	 
	 /**
	  * Visszaadja az aktu�lis megadott ir�nyt
	  * @return Aktu�lis ir�ny
	  */
	 public directions getIrany(){
		 return irany;
	 }


	/**
	 * Visszadaja az aktu�lis Game-et
	 * @return Aktu�lis Game
	 */
	public Game getGame() {
		return game;
	}


	/**
	 *  Be�ll�tja az aktu�lis Game-et
	 * @param game Aktu�lis Game
	 */
	public void setGame(Game game) {
		this.game = game;
	}


	/**
	 * Megvizsg�lja hogy a k�gy� olyan helyre l�pett-e ahol m�g folytat�dhat a
	 * j�t�k.
	 * 
	 * @param game TODO
	 * @return J�t�kban van-e m�g a k�gy�.
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
