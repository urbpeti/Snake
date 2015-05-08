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
	 * Alap�rtelmezett �rt�keket add az elemnek. ((1,1) hely �s k�gy� test).
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
	 * @return Elem x koordin�t�ja
	 */
	public int getX() {
		return x;
	}
	
	/**
	 * 
	 * @param x Elem x koordin�t�j�t be�ll�tja.
	 */
	public void setX(int x) {
		this.x = x;
	}
	
	/**
	 * 
	 * @return Elem y koordin�t�ja.
	 */
	public int getY() {
		return y;
	}
	
	/**
	 * 
	 * @param y Elem y koordin�t�j�t be�ll�tja.
	 */
	public void setY(int y) {
		this.y = y;
	}
	
	/**
	 * 
	 * @return Elem t�pusa
	 */
	public elemtipus getType() {
		return type;
	}
	
	/**
	 * 
	 * @param type Elem t�pus�t be�ll�tja.
	 */
	public void setType(elemtipus type) {
		this.type = type;
	}
	
	/**
	 * Lem�solja egy elem helyzet�t.
	 * @param cpy m�soland� elem 
	 */
	public void copy(Elem cpy){
		x = cpy.x;
		y = cpy.y;
	}
	
	/**
	 * Megvizsg�lja hogy k�t elem ugyan azon a helyen van-e.
	 * @param eq Az elem amivel �ssze akarjuk hasonl�tani az elem�nk.
	 * @return ha egy helyen vannak akkor igaz.
	 */
	public boolean equalsPlace(Elem eq){
		if(x == eq.getX() && y == eq.getY())
			return true;
		return false;
	}
	
	/**
	 * Megvizsg�lja hogy az elem ezeken a kordin�t�kon van-e.
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
