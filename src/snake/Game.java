package snake;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.JLabel;
import javax.swing.JPanel;

import snake.Elem.elemtipus;
import snake.Snake.directions;

@SuppressWarnings("serial")
public class Game extends JPanel implements ActionListener {

	public Snake snake;

	public boolean inGame = true;
	public boolean pause = true;
	public boolean ai = false;

	public static int maxSzel = 24;
	public static int maxMag = 24;

	public Elem alma = new Elem(-1, -1, elemtipus.Alma);
	private javax.swing.Timer timer;
	private JLabel szoveg;

	private BufferedImage fejfel;
	private BufferedImage fejjobbra;
	private BufferedImage fejbalra;
	private BufferedImage fejle;
	private BufferedImage test;

	static Integer[][] tomb;

	private int alamxkoordinataja;

	private int alamaykoordinataja;

	/**
	 * Game construktora, be�ll�tja a j�t�k alaphelyzet�t
	 */
	public Game() {
		// �j k�gy�
		snake = new Snake(this);

		// h�tt�r be�ll�t�sa
		setBackground(Color.white);
		// K�gy� k�peinek beimport�l�sa
		kepImport();

		// �j alma elhelyez�se
		newApple();

		// K�gy�m�ret�nek ki�r�sa
		szoveg = new JLabel(Integer.toString(snake.body.size()));

		add(szoveg);
		// Billenty�zet kezel� hozz�ad�sa
		add(new Keyboard(this));

		// Timer be�ll�t�s
		// timer = new
		// javax.swing.Timer((int)(180.0/Math.log(snake.body.size()/10.0+2)),
		// this);
		timer = new javax.swing.Timer(40, this);
		timer.start();
		setPreferredSize(new Dimension(700, 700));
		setFocusable(true);
	}

	/**
	 * Beimport�lja a k�peket a k�gy�hoz
	 */
	private void kepImport() {
		try {
			fejfel = ImageIO.read(getClass().getResourceAsStream(
					"Kepek/kigyofejfel.png"));
			fejjobbra = ImageIO.read(getClass().getResourceAsStream(
					"Kepek/kigyofejjobbra.png"));
			fejbalra = ImageIO.read(getClass().getResourceAsStream(
					"Kepek/kigyofejbalra.png"));
			fejle = ImageIO.read(getClass().getResourceAsStream(
					"Kepek/kigyofejle.png"));
			test = ImageIO.read(getClass().getResourceAsStream(
					"Kepek/kigyotest.png"));
		} catch (IOException e) {
			setBackground(Color.black);
			e.printStackTrace();
		}
	}

	/**
	 * 
	 * @return K�gy� m�ret�vel ami a pontoknak felel meg
	 */
	public int pont() {
		return snake.body.size();
	}

	/**
	 * A j�t�k befejez�sekor sz�ks�ges dolgokat v�gez el
	 */
	public void gameOver() {
		timer.stop();
	}

	/**
	 * Random kitesz egy �j alm�t �gy hogy az ne ker�lhessen olyan helyre ahol a
	 * k�gy� van.
	 */
	public void newApple() {
		do {
			almaUjHelye();
			this.alma = new Elem(alamxkoordinataja, alamaykoordinataja,
					elemtipus.Alma);
		} while (snake.rajtavan(alma));
	}

	private void almaUjHelye() {
		alamxkoordinataja = (int) (Math.random() * maxSzel);
		alamaykoordinataja = (int) (Math.random() * maxMag);
	}

	/**
	 * Be�ll�tja a kezdeti helyzetbe a param�tereket
	 */

	public void restart() {
		snake = new Snake(this);
		// timer.setDelay((int)(150.0/Math.log(snake.body.size()/10.0+2)));
		timer.setDelay(40);
		timer.start();
		inGame = true;
	}

	/**
	 * Megvizsg�lja hogy a fej ugyan azon a helyen van-e mint az alma ha igen
	 * akkor megn�veli a k�gy�t �s letesz egy �j alm�t.
	 */
	public void checkApple() {
		if (alamaaFejen()) {
			kigyoNoveles();
			newApple();
			// timer.setDelay((int)(180.0/Math.log(snake.body.size()/10.0+2)));
			timer.setDelay(40);
		}
	}

	private void kigyoNoveles() {
		Elem uj = new Elem();
		uj.copy(snake.body.get(snake.body.size() - 1));
		snake.add(uj);
	}

	private boolean alamaaFejen() {
		return snake.body.get(0).equalsPlace(alma);
	}

	/**
	 * A mesters�ges intelligenci��rt felel�s f�ggv�ny. Megkeresi a legr�videbb
	 * �tvonalat az alm�hoz �s billenty� le�t�st szimul�lva odavezeti a k�gy�t.
	 * Ha nem tal�l utat akkor random ir�nyba megy.
	 */
	@SuppressWarnings("deprecation")
	public void gep() {
		KeyEvent key = null;
		tomb = new Integer[maxMag + 2][maxSzel + 2];

		int fejx, fejy;

		for (int i = 0; i < maxMag + 2; i++)
			for (int j = 0; j < maxSzel + 2; j++)
				tomb[i][j] = 0;

		for (int i = 0; i < maxMag + 2; i++)
			tomb[i][0] = 9999;
		for (int i = 0; i < maxMag + 2; i++)
			tomb[i][maxSzel + 1] = 9999;
		for (int i = 0; i < maxSzel + 2; i++)
			tomb[maxMag + 1][i] = 9999;
		for (int i = 0; i < maxSzel + 2; i++)
			tomb[0][i] = 9999;

		for (Elem elem : snake.body) {
			tomb[(elem.getY() + 1)][(elem.getX() + 1)] = 9999;
		}
		fejx = snake.body.get(0).getX() + 1;
		fejy = snake.body.get(0).getY() + 1;
		tomb[fejy][fejx] = 5;

		rekur(snake.body.get(0).getY() + 1, snake.body.get(0).getX() + 1, 1);
		boolean van = utvonal(alma.getY() + 1, alma.getX() + 1, 7777);

		if (!van) {
			int x, y;
			x = (int) (Math.random() * maxSzel) + 1;
			y = (int) (Math.random() * maxMag) + 1;
			while (tomb[x][y] == 9999 || !utvonal(x, y, 7777)) {
				x = (int) (Math.random() * maxSzel) + 1;
				y = (int) (Math.random() * maxMag) + 1;
				rekur(snake.body.get(0).getY() + 1,
						snake.body.get(0).getX() + 1, 1);
			}
			;
		}
		// paint();

		if (tomb[fejy][fejx + 1] == 7777)
			key = new KeyEvent(this, KeyEvent.KEY_PRESSED, 0, 0,
					KeyEvent.VK_RIGHT);
		else if (tomb[fejy][fejx - 1] == 7777)
			key = new KeyEvent(this, KeyEvent.KEY_PRESSED, 0, 0,
					KeyEvent.VK_LEFT);
		else if (tomb[fejy - 1][fejx] == 7777)
			key = new KeyEvent(this, KeyEvent.KEY_PRESSED, 0, 0, KeyEvent.VK_UP);
		else if (tomb[fejy + 1][fejx] == 7777)
			key = new KeyEvent(this, KeyEvent.KEY_PRESSED, 0, 0,
					KeyEvent.VK_DOWN);
		if (key != null)
			dispatchEvent(key);

	}

	/*
	 * public static boolean vankiut(){ for(int i=1;i<26;i++) for(int j =
	 * 1;j<26;j++) if(tomb[i][j] != 9999 && tomb[i][j] != 7777 ) tomb[i][j] = 0;
	 * 
	 * for(int i=1;i<26;i++) for(int j = 1;j<26;j++) if(tomb[i][j] == 0){
	 * kiszinez(i, j); i=27; break; } for(int i=1;i<26;i++) for(int j =
	 * 1;j<26;j++) if(tomb[i][j] == 0) return false; return true;
	 * 
	 * 
	 * }
	 */

	/*
	 * public static void kiszinez(int x ,int y){ if(x <1 || y < 1 || x > 25 ||
	 * y > 25 || tomb[x][y] == 333) return; if(tomb[x][y] == 0) tomb[x][y] =
	 * 333; else return; kiszinez(x-1,y); kiszinez(x+1,y); kiszinez(x,y-1);
	 * kiszinez(x,y+1); }
	 */

	/*
	 * public static void paint(){ for(int i = 0; i < 27;i++){ for(int j=0;j <
	 * 27 ;j++) System.out.format("%4d ",tomb[i][j]); System.out.println(""); }
	 * }
	 */

	/**
	 * Kezdeti pontt�l kit�lt egy k�tdimenzi�s t�mb�t sz�mokkal annak
	 * f�gv�ny�ben hogy mennyi l�p�s oda�ig el�rni.
	 * 
	 * @param x
	 *            kezdeti hely x koordin�t�ja.
	 * @param y
	 *            kezdeti hely y koordin�t�ja.
	 * @param tav
	 *            milyen messze ker�lt eddig.
	 */
	public static void rekur(int x, int y, Integer tav) {
		if (x < 0 || y < 0 || x >= maxSzel + 2 || y >= maxMag + 2)
			return;
		if (tomb[x][y] == 9999)
			return;
		if (tomb[x][y] == 7777)
			return;
		if (tomb[x][y] > tav || tomb[x][y] == 0) {
			tomb[x][y] = tav;
			rekurMeghivasaMindenIranyban(x, y, tav);
		}
	}

	private static void rekurMeghivasaMindenIranyban(int x, int y, Integer tav) {
		rekur(x + 1, y, tav + 1);
		rekur(x, y + 1, tav + 1);
		rekur(x - 1, y, tav + 1);
		rekur(x, y - 1, tav + 1);
	}

	/**
	 * A rekur f�ggv�ny �ltal kit�lt�tt t�bl�n megkeresi a legr�videbb utat egy
	 * magadott pontt�l a rekur �ltal megadott pontig.
	 * 
	 * @param x
	 *            innen keresi az utat
	 * @param y
	 *            innen keresi az utat
	 * @param ertek
	 *            ilyen �rt�kkel t�lti fel az �tvonalat a t�mbben.
	 * @return Megtal�lta-e az utat.
	 */
	public static boolean utvonal(int x, int y, int ertek) {
		if (tomb[x][y] == 1)
			return true;
		if (tomb[x][y] == 9999)
			return false;
		directions[] iranyok;
		iranyok = new directions[4];
		iranyok[0] = directions.Right;
		iranyok[1] = directions.Left;
		iranyok[2] = directions.Up;
		iranyok[3] = directions.Down;
		for (int i = 0; i < 4; ++i) {
			int r = (int) (Math.random() * 4);
			directions temp = iranyok[i];
			iranyok[i] = iranyok[r];
			iranyok[r] = temp;
		}

		int min = 8000;
		int x1 = -1, y1 = 0;
		for (int i = 0; i < 4; i++)
			switch (iranyok[i]) {
			case Right:
				if (min > tomb[x - 1][y]) {
					min = tomb[x - 1][y];
					x1 = -1;
					y1 = 0;
				}
				break;
			case Left:
				if (min > tomb[x + 1][y]) {
					min = tomb[x + 1][y];
					x1 = 1;
					y1 = 0;
				}
				break;
			case Up:
				if (min > tomb[x][y - 1]) {
					min = tomb[x][y - 1];
					x1 = 0;
					y1 = -1;
				}
				break;
			case Down:
				if (min > tomb[x][y + 1]) {
					min = tomb[x][y + 1];
					x1 = 0;
					y1 = 1;
				}
				break;
			default:
				break;
			}
		if (min >= tomb[x][y])
			return false;
		tomb[x][y] = ertek;
		return utvonal(x + x1, y + y1, ertek);
	}

	/**
	 * Timer haszn�lja ez l�pteti a k�gy�t �s itt ellen�rzi le, hogy a j�t�kban
	 * vagyunk-e m�g. Min�l gyorsabban h�vjuk meg ann�l gyorsabb lesz a j�t�k.
	 */
	public void actionPerformed(ActionEvent e) {
		if (inGame && !pause) {
			if (ai)
				gep();
			snake.move();
			inGame = snake.check();
			szoveg.setText(Integer.toString(snake.body.size()));
		}
		if (inGame) {
			repaint();
			checkApple();
		} else {
			gameOver();
		}
	}

	/**
	 * Az elemek kirajzol�s��rt felel a Panelen.
	 */
	public void paint(Graphics g) {
		super.paint(g);
		Graphics2D g2d = (Graphics2D) g;
		g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING,
				RenderingHints.VALUE_ANTIALIAS_ON);
		double szelarany = getWidth() / maxSzel;
		double magarany = getHeight() / maxMag;

		for (Elem elem : snake.body) {
			switch (elem.getType()) {
			case Fejfel:
				g2d.drawImage(fejfel, (int) (elem.getX() * szelarany),
						(int) (elem.getY() * magarany), (int) szelarany,
						(int) magarany, null);
				break;
			case Fejjobbra:
				g2d.drawImage(fejjobbra, (int) (elem.getX() * szelarany),
						(int) (elem.getY() * magarany), (int) szelarany,
						(int) magarany, null);
				break;
			case Fejbalra:
				g2d.drawImage(fejbalra, (int) (elem.getX() * szelarany),
						(int) (elem.getY() * magarany), (int) szelarany,
						(int) magarany, null);
				break;
			case Fejle:
				g2d.drawImage(fejle, (int) (elem.getX() * szelarany),
						(int) (elem.getY() * magarany), (int) szelarany,
						(int) magarany, null);
				break;
			default:
				g2d.drawImage(test, (int) (elem.getX() * szelarany),
						(int) (elem.getY() * magarany), (int) szelarany,
						(int) magarany, null);
				break;
			}
		}
		g.setColor(Color.red);
		g.fillOval((int) (alma.getX() * szelarany),
				(int) (alma.getY() * magarany), (int) szelarany, (int) magarany);
	}

}