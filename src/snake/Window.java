package snake;

import java.awt.BorderLayout;
import java.awt.Dimension;

import javax.swing.JFrame;

public class Window extends JFrame {
	/**
	 * 
	 */
	private static final long serialVersionUID = 2141590699506837098L;
	
	/**
	 *  Létre hozza az ablakkot amiben benne van a menu és a játék panele.
	 * @param string
	 * @throws InterruptedException
	 */	
	public Window(String string) throws InterruptedException{
		super(string);
		setResizable(false);
		setLayout(new BorderLayout());
		Game jatek = new Game();
		add(jatek,BorderLayout.CENTER);
		Menu menu = new Menu(jatek);
		add(menu,BorderLayout.EAST);
		pack();
		setPreferredSize(new Dimension(700,700));
		setVisible(true);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
	}

}
