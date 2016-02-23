import java.awt.Color;

import javax.swing.JButton;

public class Rei extends Peca {

	public Rei(corDaPeca cor) {
		super(cor,tipoDePecas.REI);
	}

	@Override
	public void pintarOpcoes(JButton[][] tabVisual, Peca[][] tabLogico, int linha, int coluna) {
		tabVisual[linha][coluna].setBackground(Color.BLUE);

		int[] dx = { 0, 0, -1, 1, 1, 1, -1, -1 };
		int[] dy = { -1, 1, 0, 0, -1, 1, -1, 1 };

		for (int i = 0; i < dx.length; i++) {
			int xx = linha + dx[i];
			int yy = coluna + dy[i];

			if ((xx >= 0) && (xx <= 7) && ((yy >= 0) && (yy <= 7))) {
				if (tabLogico[linha][coluna].cor != tabLogico[xx][yy].cor) {
					if (tabVisual[xx][yy].isEnabled()) {
						tabVisual[xx][yy].setBackground(Color.RED);
					} else
						tabVisual[xx][yy].setBackground(Color.GREEN);

					tabVisual[xx][yy].setEnabled(true);
				}
			}
		}
	}

	@Override
	public void cancelarOpcoes(JButton[][] tabVisual, Peca[][] tabLogico, int linha, int coluna) {

		int[] dx = { 0, 0, -1, 1, 1, 1, -1, -1 };
		int[] dy = { -1, 1, 0, 0, -1, 1, -1, 1 };

		for (int i = 0; i < dx.length; i++) {
			int xx = linha + (dx[i]);
			int yy = coluna + (dy[i]);

			if ((xx >= 0) && (xx <= 7) && ((yy >= 0) && (yy <= 7))) {
				if (tabVisual[xx][yy].getBackground() == Color.GREEN) {
					tabVisual[xx][yy].setEnabled(false);
				}
			}
		}
	}
	@Override
	public boolean trocarPeca(TabuleiroVisual tabuleiro, JButton[][] tabVisual, Peca[][] tabLogico, int linha, int coluna) {
		return false;
	}
}