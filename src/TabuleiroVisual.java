import java.awt.GridLayout;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;
import java.awt.Color;
import java.awt.Dimension;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;

@SuppressWarnings("serial")
public class TabuleiroVisual extends JFrame implements ActionListener {

	File replayFile = null;
	JButton[][] tabVisual = new JButton[8][8];
	TabuleiroLogico tabLogico;
	Dimension screen = Toolkit.getDefaultToolkit().getScreenSize();
	TabuleiroVisual tabuleiroOposto;
	boolean invertido = false;
	
	public TabuleiroVisual(File replayFile, TabuleiroLogico TabuleiroLogico,boolean inverter) {
		super(inverter ? "Pretas":"Brancas");
		this.setSize(600, 600);
		this.setLocation((inverter ? (screen.width-600) : 0),0);
		this.getContentPane().setLayout(new GridLayout(8, 8));
		this.tabLogico = TabuleiroLogico;
		this.invertido = inverter;
		
		JButton[] listaDeBotoes = new JButton[64]; 

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
				buttom.addActionListener(this);
				tabVisual[i][j] = buttom;
				listaDeBotoes[i*8+j] = buttom;
			}
		}
		
		for (int i = 0; i < listaDeBotoes.length; i++) {
			int index = inverter ? listaDeBotoes.length-1-i : i;
			this.getContentPane().add(listaDeBotoes[index]);
		}
		
		pintarBase(tabVisual);
		this.replayFile = replayFile;
		this.setVisible(true);
	}

	public ImageIcon getIconPeca(corDaPeca cor, tipoDePecas peca) {
		return new ImageIcon(new ImageIcon("icons/" + cor + "/" + peca + ".png").getImage().getScaledInstance(64, 64,java.awt.Image.SCALE_SMOOTH));
	}

	public void actionPerformed(ActionEvent e) {
		for (int linha = 0; linha < tabVisual.length; linha++) {
			for (int coluna = 0; coluna < tabVisual[linha].length; coluna++) {
				if (e.getSource() == (tabVisual[linha][coluna])) {
					Movimento mov = null;
					try {
						mov = new Movimento(this, linha, coluna, tabLogico.playerAtu, replayFile);
					} catch (IOException e1) {
						e1.printStackTrace();
					}
					if (mov.pintarBase) pintarBase(tabVisual);
					if (mov.movimentoFinalizado) trocarJogador(tabLogico.playerAtu);
				}
			}
		}
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

	public void trocarJogador(corDaPeca playerAtu) {
		if (playerAtu == corDaPeca.BRANCA)
			tabLogico.playerAtu = corDaPeca.PRETA;
		else
			tabLogico.playerAtu = corDaPeca.BRANCA;
	}

	public TabuleiroVisual getTabuleiroOposto() {
		return tabuleiroOposto;
	}

	public void setTabuleiroOposto(TabuleiroVisual tabuleiroOposto) {
		this.tabuleiroOposto = tabuleiroOposto;
	}
	
	public void atualizar(){
		for (int i = 0; i < tabLogico.posicoesPecas.length; i++) {
			for (int j = 0; j < tabLogico.posicoesPecas[i].length; j++) {
				tabVisual[i][j].setIcon(getIconPeca(tabLogico.posicoesPecas[i][j].cor, tabLogico.posicoesPecas[i][j].tipo));
				if (tabLogico.posicoesPecas[i][j].tipo != null)
					tabVisual[i][j].setEnabled(true);
				else
					tabVisual[i][j].setEnabled(false);
			}
		} 
	}

	public void vitoria() {
		setTransparentPNG("icons/Vitoria.png");
	}

	public void derrota() {
		setTransparentPNG("icons/Derrota.png");
	}
	
	public void setTransparentPNG(String caminhoImagem){
		JFrame janela = new JFrame("Meu primeiro frame em Java");
		JLabel vitoria = new JLabel(new ImageIcon(new ImageIcon(caminhoImagem).getImage().getScaledInstance(600, 200, java.awt.Image.SCALE_SMOOTH)));
		
		janela.setSize(600, 200);
		janela.setLocation((invertido ? (screen.width-600) : 0),200);
		janela.setUndecorated(true);
		janela.setBackground(new Color(1.0f,1.0f,1.0f,0.6f));		
		
		vitoria.setSize(600,200);
		vitoria.setLocation(0,0);
		
		janela.add(vitoria);
		janela.setVisible(true);
		this.setEnabled(false);
	}

	public File getReplayFile() {
		return replayFile;
	}

	public void setReplayFile(File replayFile) {
		this.replayFile = replayFile;
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

	public Dimension getScreen() {
		return screen;
	}

	public void setScreen(Dimension screen) {
		this.screen = screen;
	}

	public boolean isInvertido() {
		return invertido;
	}

	public void setInvertido(boolean invertido) {
		this.invertido = invertido;
	}
	
}