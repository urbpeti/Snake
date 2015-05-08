package snake;

import java.awt.Container;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

public class Menu extends JPanel{
	/**
	 * 
	 */
	private static final long serialVersionUID = -8226834808539194083L;
	
	
	Game game;
	
	/**
	 * Elhelyezi a gombokat a Menu panelen �s hozz�juk rendeli az esem�nyeket.
	 * @param game
	 */
	public Menu(final Game game){
		this.game = game;
	    final JButton start = new JButton("New Game");
	    final JButton pause = new JButton("Start");
	    final JButton save = new JButton("Save");
	  	final JButton load = new JButton("Load");
	    final JButton ai = new JButton("AI On");
		JButton exit = new JButton("Exit");
		setPreferredSize(new Dimension(100,100));
		
		save.setEnabled(false);
		load.setEnabled(false);
		
		
		/**
		 * Elind�tja a j�t�kot.
		 */
		start.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				game.restart();
				game.pause = true;
				pause.setText("Start");
				save.setEnabled(false);
				load.setEnabled(false);
				
			}
		});
		
		/**
		 * Sz�neteli a j�t�kot vagy ha m�r sz�netel akkor elind�tja.
		 */
		pause.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				game.pause = !game.pause;
				if(game.pause){
					pause.setText("Resume");
					save.setEnabled(true);
					load.setEnabled(true);
				}
				else{
					pause.setText("Pause");
					save.setEnabled(false);
					load.setEnabled(false);
				}
				
			}
		});
		
		
		/**
		 * Be ki kapcsolja a Mesters�ges intelligenci�t.
		 */
		ai.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				game.ai = !game.ai;
				if(game.ai){
					ai.setText("AI Off");
				}
				else{
					ai.setText("AI On");
				}
				
			}
		});
		
		/**
		 * Elmenti a j�t�kot.
		 */
		save.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				Mentes.save(game);
			}
		});
		
		/**
		 * Bet�lti a j�t�kot.
		 */
		load.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				Mentes.load(game);
				
			}
		});
		
		/**
		 * Kil�p a j�t�kb�l.
		 */
		exit.addActionListener(new ActionListener() {
	        public void actionPerformed(ActionEvent e){
	            Container frame = getParent();
	            do 
	                frame = frame.getParent(); 
	            while (!(frame instanceof JFrame));                                      
	            ((JFrame) frame).dispose();
	        }
	    });
		
		
		
		add(start);
		add(pause);
		add(ai);
		add(save);
		add(load);
		add(exit);
	}

}
