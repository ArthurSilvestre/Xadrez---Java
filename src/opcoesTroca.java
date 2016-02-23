import java.awt.Color;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;

@SuppressWarnings("serial")
public class opcoesTroca extends JFrame implements ActionListener {

	int linha = 0;
	int coluna = 0;
	JButton[] opcoesPecas = new JButton[4];
	TabuleiroVisual tabuleiro;
	
	public opcoesTroca(TabuleiroVisual tabuleiro, corDaPeca Cor, int L, int C) {
		super("Selecione uma peça.");
		this.setSize(400, 130);
		this.setLocationRelativeTo(null);
		this.getContentPane().setLayout(new GridLayout(1, 4));

		for (int i = 1; i < (tipoDePecas.values().length - 1); i++) {
			JButton buttom = new JButton();
			tipoDePecas Peca = tipoDePecas.values()[i];
			buttom.setIcon(getIconPeca(Cor, Peca));
			buttom.setBackground(Color.WHITE);
			buttom.addActionListener(this);
			opcoesPecas[i - 1] = buttom;
			this.getContentPane().add(buttom);
		}

		this.linha = L;
		this.coluna = C;
		this.tabuleiro = tabuleiro;
		this.setVisible(true);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		Peca[][] logico = tabuleiro.tabLogico.posicoesPecas;
		JButton[][] visual = tabuleiro.tabVisual;
		Peca[][] logicoOp = tabuleiro.tabuleiroOposto.tabLogico.posicoesPecas;
		JButton[][] visualOp = tabuleiro.tabuleiroOposto.tabVisual;
		
		for (int i = 0; i < opcoesPecas.length; i++) {
			if (e.getSource() == (opcoesPecas[i])) {
				logico[linha][coluna] = getTipoPeca(tipoDePecas.values()[i + 1], logico[linha][coluna].cor);
				visual[linha][coluna].setIcon(getIconPeca(logico[linha][coluna].cor, tipoDePecas.values()[i + 1]));
				logicoOp[linha][coluna] = getTipoPeca(tipoDePecas.values()[i + 1], logico[linha][coluna].cor);
				visualOp[linha][coluna].setIcon(getIconPeca(logico[linha][coluna].cor, tipoDePecas.values()[i + 1]));
				this.setVisible(false);
			}
		}
	}

	public int getLinha() {
		return linha;
	}

	public void setLinha(int linha) {
		this.linha = linha;
	}

	public int getColuna() {
		return coluna;
	}

	public void setColuna(int coluna) {
		this.coluna = coluna;
	}

	public TabuleiroVisual getTabuleiro() {
		return tabuleiro;
	}

	public void setTabuleiro(TabuleiroVisual tabuleiro) {
		this.tabuleiro = tabuleiro;
	}

	public JButton[] getOpcoesPecas() {
		return opcoesPecas;
	}

	public void setOpcoesPecas(JButton[] opcoesPecas) {
		this.opcoesPecas = opcoesPecas;
	}

	public ImageIcon getIconPeca(corDaPeca cor, tipoDePecas peca) {
		return new ImageIcon(new ImageIcon("icons/" + cor + "/" + peca + ".png").getImage().getScaledInstance(64, 64,java.awt.Image.SCALE_SMOOTH));
	}

	public Peca getTipoPeca(tipoDePecas peca, corDaPeca cor) {
		switch (peca) {
			case PEAO:return new Peao(cor);
			case TORRE:return new Torre(cor);
			case CAVALO:return new Cavalo(cor);
			case BISPO:return new Bispo(cor);
			case RAINHA:return new Rainha(cor);
			case REI:return new Rei(cor);
			default:return new SemPeca();
		}
	}
}