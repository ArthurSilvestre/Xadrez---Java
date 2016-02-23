import java.awt.Color;
import javax.swing.JButton;

public class Bispo extends Peca {

	public Bispo(corDaPeca cor) {
		super(cor,tipoDePecas.BISPO);
	}

	@Override
	public void pintarOpcoes(JButton[][] tabVisual, Peca[][] tabLogico, int linha, int coluna) {

		tabVisual[linha][coluna].setBackground(Color.BLUE);

		for (int i = 0; i < 4; i++) {
			int[] dx = { 1, 1, -1, -1 };
			int[] dy = { -1, 1, -1, 1 };

			for (int j = 0; j < 8; j++) {
				int xx = linha + (j * dx[i]);
				int yy = coluna + (j * dy[i]);

				if ((xx >= 0) && (xx <= 7) && ((yy >= 0) && (yy <= 7))) {
					if (tabLogico[linha][coluna].cor != tabLogico[xx][yy].cor) {
						if (tabVisual[xx][yy].isEnabled()) {
							tabVisual[xx][yy].setBackground(Color.RED);
							break;
						} else
							tabVisual[xx][yy].setBackground(Color.GREEN);

						tabVisual[xx][yy].setEnabled(true);
					} else if (j > 0)
						if (tabVisual[xx][yy].isEnabled())
							break;
				}
			}
		}
	}

	@Override
	public void cancelarOpcoes(JButton[][] tabVisual, Peca[][] tabLogico, int linha, int coluna) {
		for (int i = 0; i < 4; i++) {
			int[] dx = { 1, 1, -1, -1 };
			int[] dy = { -1, 1, -1, 1 };

			for (int j = 0; j < 8; j++) {
				int xx = linha + (j * dx[i]);
				int yy = coluna + (j * dy[i]);

				if ((xx >= 0) && (xx <= 7) && ((yy >= 0) && (yy <= 7))) {
					if (tabVisual[xx][yy].getBackground() == Color.GREEN) {
						tabVisual[xx][yy].setEnabled(false);
					}
				}
			}
		}
	}

	@Override
	public boolean trocarPeca(TabuleiroVisual tabuleiro, JButton[][] tabVisual, Peca[][] tabLogico, int linha, int coluna) {
		return false;
	}
}