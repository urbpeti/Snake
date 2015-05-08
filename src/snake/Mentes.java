package snake;

import java.awt.FileDialog;
import java.awt.Frame;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

import javax.swing.JFileChooser;
import javax.swing.JFrame;

public class Mentes {

	/**
	 * Elmenti a megadott j�t�kot.
	 * 
	 * @param game
	 */
	public static void save(Game game) {
		try {
			JFileChooser fd = new JFileChooser();
			if (fd.showSaveDialog(new Frame()) != JFileChooser.CANCEL_OPTION) {
				//TODO
				FileOutputStream fo = new FileOutputStream(fd.getSelectedFile());
				ObjectOutputStream oo = new ObjectOutputStream(fo);
				oo.writeObject(game.snake);
				oo.writeObject(game.alma);
				oo.close();
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	/**
	 * Bet�lt egy elmentett j�t�kot a param�t�rk�nt megadott j�t�k hely�re.
	 * 
	 * @param game
	 *            Ebbe a j�t�kba t�lti bele.
	 */
	@SuppressWarnings("deprecation")
	public static void load(Game game) {
		try {
			FileDialog fd = new FileDialog(new JFrame());
			fd.show();
			if (fd.getFile() != null) {
				FileInputStream fi = new FileInputStream(fd.getFile());
				ObjectInputStream oi = new ObjectInputStream(fi);

				game.restart();
				game.snake = (Snake) oi.readObject();
				game.alma = (Elem) oi.readObject();
				oi.close();
			}
		} catch (IOException | ClassNotFoundException e) {
			e.printStackTrace();
		}
	}

}
