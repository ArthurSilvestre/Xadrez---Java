import java.awt.Color;
import java.awt.GridLayout;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;

@SuppressWarnings("serial")
public class Replay extends JFrame implements Runnable {

	Scanner replayFile;
	corDaPeca playerAtu;
	JButton[][] tabVisual;
	TabuleiroLogico tabLogico;
	boolean fileSelected = false;
	
	public Replay() {
		this.replayFile = null;
		this.playerAtu  = corDaPeca.BRANCA;
		this.tabVisual  = new JButton[8][8];
		this.tabLogico  = new TabuleiroLogico();
	}
	
	public void criarTabuleiro(){
		this.setTitle("Xadrez");
		this.setSize(600, 600);
		this.setLocationRelativeTo(null);
		this.getContentPane().setLayout(new GridLayout(8, 8));
		
		for (int i = 0; i < tabVisual.length; i++) {
			for (int j = 0; j < tabVisual.length; j++) {
	
				JButton buttom = new JButton();
				buttom.setEnabled(false);
	
				tipoDePecas[] tipos = {tipoDePecas.TORRE,tipoDePecas.CAVALO,tipoDePecas.BISPO,tipoDePecas.RAINHA,tipoDePecas.REI,tipoDePecas.BISPO,tipoDePecas.CAVALO,tipoDePecas.TORRE};
				for (int k = 0; k < corDaPeca.values().length; k++) {
					int posPecas = k == 0 ? 0 : 7;
					int posPeoes = k == 0 ? 1 : 6;
					corDaPeca cor = corDaPeca.values()[k];
					tipoDePecas peca = i == posPeoes ? tipoDePecas.values()[0] : tipos[j];
	
					if ((i == posPeoes) || (i == posPecas)) {
						buttom.setIcon(getIconPeca(cor, peca));
						buttom.setEnabled(true);
					}
				}
				tabVisual[i][j] = buttom;
				this.getContentPane().add(buttom);
			}
		}
		pintarBase(tabVisual);
		this.setVisible(true);
	}
	
	public void getFile() throws FileNotFoundException{
		JFileChooser abrir = new JFileChooser();  
		int retorno = abrir.showOpenDialog(null);  
       
		if (retorno==JFileChooser.APPROVE_OPTION){
			this.replayFile = new Scanner(new File(abrir.getSelectedFile().getAbsolutePath()));  
			this.fileSelected = true;
		}
	}
	
	public void play(){
		new Thread(this).start();
	}
	
	public ImageIcon getIconPeca(corDaPeca Cor, tipoDePecas peca) {
		return new ImageIcon(new ImageIcon("icons/" + Cor + "/" + peca + ".png").getImage().getScaledInstance(64, 64,java.awt.Image.SCALE_SMOOTH));
	}
	
	public void pintarBase(JButton[][] tabVisual) {
		for (int i = 0; i < tabVisual.length; i++) {
			for (int j = 0; j < tabVisual.length; j++) {
				if (((i % 2) == 0) && ((j % 2) == 0))
					tabVisual[i][j].setBackground(Color.BLACK);
				else if (((i % 2) != 0) && ((j % 2) != 0))
					tabVisual[i][j].setBackground(Color.BLACK);
				else
					tabVisual[i][j].setBackground(Color.WHITE);
			}
		}
	}

	@Override
	public void run() {
		while (replayFile.hasNext()) {
			String[] movimentos = replayFile.nextLine().split(";");
			for (int i = 0; i < movimentos.length; i++) {
				try {
					Thread.sleep(1000);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
				new Movimento(this, Integer.valueOf(movimentos[i].substring(3,4)) , Integer.valueOf(movimentos[i].substring(5,6)) , Integer.valueOf(movimentos[i].substring(10,11)) , Integer.valueOf(movimentos[i].substring(12,13)), tipoDePecas.valueOf(movimentos[i].substring(16).replaceAll("}","")));
			}
		}
		
	}

	public Scanner getReplayFile() {
		return replayFile;
	}

	public void setReplayFile(Scanner replayFile) {
		this.replayFile = replayFile;
	}

	public corDaPeca getPlayerAtu() {
		return playerAtu;
	}

	public void setPlayerAtu(corDaPeca playerAtu) {
		this.playerAtu = playerAtu;
	}

	public JButton[][] getTabVisual() {
		return tabVisual;
	}

	public void setTabVisual(JButton[][] tabVisual) {
		this.tabVisual = tabVisual;
	}

	public TabuleiroLogico getTabLogico() {
		return tabLogico;
	}

	public void setTabLogico(TabuleiroLogico tabLogico) {
		this.tabLogico = tabLogico;
	}

	public boolean isFileSelected() {
		return fileSelected;
	}

	public void setFileSelected(boolean fileSelected) {
		this.fileSelected = fileSelected;
	}
	
}
