package snake;

import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;

import javax.swing.AbstractAction;
import javax.swing.JComponent;
import javax.swing.KeyStroke;

import snake.Snake.directions;

@SuppressWarnings("serial")
public class Keyboard extends JComponent{
	
	Game game;
	
	public Keyboard(Game game){
		this.game = game;
		keys();
		setVisible(true);
	}
	
	/**
	 * Beállítja a billentyûket és hozzájuk köti az Actionoket.
	 */
	void keys(){
		getInputMap(WHEN_IN_FOCUSED_WINDOW).put(KeyStroke.getKeyStroke(KeyEvent.VK_UP, 0), "fel");
		getInputMap(WHEN_IN_FOCUSED_WINDOW).put(KeyStroke.getKeyStroke(KeyEvent.VK_DOWN, 0), "le");
	    getInputMap(WHEN_IN_FOCUSED_WINDOW).put(KeyStroke.getKeyStroke(KeyEvent.VK_LEFT, 0), "balra");
		getInputMap(WHEN_IN_FOCUSED_WINDOW).put(KeyStroke.getKeyStroke(KeyEvent.VK_RIGHT, 0), "jobbra");
		
		getActionMap().put("fel", new AbstractAction() {
			public void actionPerformed(ActionEvent arg0) {
				if(game.snake.getIrany() != directions.Down && game.snake.getIrany() != directions.Up && !game.snake.iranyitva){
					game.snake.setIrany(directions.Up);
					game.snake.iranyitva = true;
					}
			}
		});
		
		getActionMap().put("le", new AbstractAction() {
			public void actionPerformed(ActionEvent arg0) {
				if(game.snake.getIrany() != directions.Up && game.snake.getIrany() != directions.Down && !game.snake.iranyitva){
					game.snake.setIrany(directions.Down);
					game.snake.iranyitva = true;}
			}
		});
		
		getActionMap().put("balra", new AbstractAction() {
			public void actionPerformed(ActionEvent arg0) {
				if(game.snake.getIrany() != directions.Right && game.snake.getIrany() != directions.Left && !game.snake.iranyitva){
					game.snake.setIrany(directions.Left);
					game.snake.iranyitva = true;
					}
			}
		});
		
		getActionMap().put("jobbra", new AbstractAction() {
			public void actionPerformed(ActionEvent arg0) {
				if(game.snake.getIrany() != directions.Left && game.snake.getIrany() != directions.Right && !game.snake.iranyitva){
					game.snake.setIrany(directions.Right);
					game.snake.iranyitva = true;
				}
			}
		});
		
		
	}
}