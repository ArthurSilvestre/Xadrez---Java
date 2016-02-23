import java.awt.Color;
import javax.swing.JButton;

public class Rainha extends Peca {

	public Rainha(corDaPeca cor) {
		super(cor,tipoDePecas.RAINHA);
	}

	@Override
	public void pintarOpcoes(JButton[][] tabVisual, Peca[][] tabLogico, int linha, int coluna) {

		tabVisual[linha][coluna].setBackground(Color.BLUE);

		for (int i = 0; i < 8; i++) {
			int[] dx = { 0, 0, -1, 1, 1, 1, -1, -1 };
			int[] dy = { -1, 1, 0, 0, -1, 1, -1, 1 };

			for (int j = 0; j <= (dx.length + 1); j++) {
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
		for (int i = 0; i < 8; i++) {
			int[] dx = { 0, 0, -1, 1, 1, 1, -1, -1 };
			int[] dy = { -1, 1, 0, 0, -1, 1, -1, 1 };

			for (int j = 0; j <= dx.length; j++) {
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