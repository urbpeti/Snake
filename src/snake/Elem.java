package snake;

import java.io.Serializable;

public class Elem implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 4135946243773609557L;
	

	public enum elemtipus{
		Fejfel,Fejjobbra,Fejbalra,Fejle,Test,Alma
	};
	
	
	private int x;
	private int y;
	private elemtipus type;

	
	/**
	 * Alapértelmezett értékeket add az elemnek. ((1,1) hely és kígyó test).
	 */
	public Elem(){
		x = 1;
		y = 1;
		type = elemtipus.Test;
	}
	
	/**
	 * construktor ami megadott helyre teszi az Elemet.
	 * @param x 
	 * @param y
	 * @param type
	 */
	public Elem(int x, int y, elemtipus type){
		this.x = x;
		this.y = y;
		this.type = type;
	}
	
	/**
	 * @return Elem x koordinátája
	 */
	public int getX() {
		return x;
	}
	
	/**
	 * 
	 * @param x Elem x koordinátáját beállítja.
	 */
	public void setX(int x) {
		this.x = x;
	}
	
	/**
	 * 
	 * @return Elem y koordinátája.
	 */
	public int getY() {
		return y;
	}
	
	/**
	 * 
	 * @param y Elem y koordinátáját beállítja.
	 */
	public void setY(int y) {
		this.y = y;
	}
	
	/**
	 * 
	 * @return Elem típusa
	 */
	public elemtipus getType() {
		return type;
	}
	
	/**
	 * 
	 * @param type Elem típusát beállítja.
	 */
	public void setType(elemtipus type) {
		this.type = type;
	}
	
	/**
	 * Lemásolja egy elem helyzetét.
	 * @param cpy másolandó elem 
	 */
	public void copy(Elem cpy){
		x = cpy.x;
		y = cpy.y;
	}
	
	/**
	 * Megvizsgálja hogy két elem ugyan azon a helyen van-e.
	 * @param eq Az elem amivel össze akarjuk hasonlítani az elemünk.
	 * @return ha egy helyen vannak akkor igaz.
	 */
	public boolean equalsPlace(Elem eq){
		if(x == eq.getX() && y == eq.getY())
			return true;
		return false;
	}
	
	/**
	 * Megvizsgálja hogy az elem ezeken a kordinátákon van-e.
	 * @param x 
	 * @param y
	 * @return ha ezen a helyen van akkor igaz
	 */
	public boolean equalsPlace(int x, int y){
		if(this.x == x && this.y == y)
			return true;
		return false;
	}
	
}
