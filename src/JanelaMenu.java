import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileNotFoundException;
import java.io.IOException;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

@SuppressWarnings("serial")
public class JanelaMenu extends JFrame {

	public JanelaMenu() {
		super("XADREZ");
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		this.setSize(610, 515);
		this.setLocationRelativeTo(null);
		this.getContentPane().setLayout(new GridLayout(8, 8));

		JPanel menu = new JPanel();
		JLabel capa = new JLabel();
		JButton buttomJogar = new JButton("Jogar");
		JButton buttomReplay = new JButton("Ver Replay");

		menu.setLayout(null);

		// Icones
			capa.setIcon(setImagem("icons/Capa.jpg",600, 600));
			buttomJogar.setIcon(setImagem("icons/play.png",40, 40));
			buttomReplay.setIcon(setImagem("icons/replay.png",50, 50));

		// Posição e tamanho
			capa.setLocation(0, 0);
			capa.setSize(600, 400);
			buttomJogar.setLocation(300, 400);
			buttomJogar.setSize(300, 80);
			buttomReplay.setLocation(0, 400);
			buttomReplay.setSize(300, 80);

		// Alinhamento
			buttomJogar.setVerticalTextPosition(SwingConstants.CENTER);
			buttomJogar.setHorizontalTextPosition(SwingConstants.RIGHT);
			buttomReplay.setVerticalTextPosition(SwingConstants.CENTER);
			buttomReplay.setHorizontalTextPosition(SwingConstants.RIGHT);

		// Ação
			buttomJogar.addActionListener(new ActionListener() {
				@SuppressWarnings("unused")
				public void actionPerformed(ActionEvent arg0) {
					try {
						Jogar Jogo = new Jogar();
					} catch (IOException e) {
						e.printStackTrace();
					}
				}
			});
			buttomReplay.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent arg0) {
					Replay Replay = new Replay();
					try {
						Replay.getFile();
						if (Replay.fileSelected){
							Replay.criarTabuleiro();
							Replay.play();
						}
					} catch (FileNotFoundException e) {
						e.printStackTrace();
					}
				}
			});

		menu.add(capa);
		menu.add(buttomJogar);
		menu.add(buttomReplay);
		setContentPane(menu);
	}
	
	public ImageIcon setImagem(String caminhoImagem,int x,int y){
		return new ImageIcon(new ImageIcon(caminhoImagem).getImage().getScaledInstance(x, y, java.awt.Image.SCALE_SMOOTH));
	}
}