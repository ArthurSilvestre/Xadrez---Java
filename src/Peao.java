import java.awt.Color;
import javax.swing.JButton;

@SuppressWarnings("unused")
public class Peao extends Peca{
	
	public Peao(corDaPeca cor) {
		super(cor,tipoDePecas.PEAO);
	}

	@Override
	public void pintarOpcoes(JButton[][] tabVisual,Peca[][] tabLogico,int linha,int coluna) {
		tabVisual[linha][coluna].setBackground(Color.BLUE);

		int N = 1;
		int[] Ataque = {-1,1};

		if (tabLogico[linha][coluna].cor == corDaPeca.BRANCA)
			N = -1;
			
		
		for (int i = 0; i < Ataque.length; i++) {
			int xx = linha+N;
			int yy = coluna+Ataque[i];
			
			if ((xx >= 0) && (xx <= 7) && ((yy >= 0) && (yy <= 7))) {
				if (tabLogico[linha][coluna].cor != tabLogico[xx][yy].cor){
					if (tabVisual[xx][yy].isEnabled()){
						tabVisual[xx][yy].setBackground(Color.RED);
						tabVisual[xx][yy].setEnabled(true);
					}
				}
			}
		}

		if ((linha == 6 || linha == 1) && (!tabVisual[linha+(1*N)][coluna].isEnabled()))
			if (((linha+(2*N)) >= 0) && ((linha+(2*N)) <= 7 ))
				if (!tabVisual[linha+(2*N)][coluna].isEnabled()) {
					tabVisual[linha+(2*N)][coluna].setBackground(Color.GREEN);
					tabVisual[linha+(2*N)][coluna].setEnabled(true);
				}

		if (!tabVisual[linha+(1*N)][coluna].isEnabled()) {
			tabVisual[linha+(1*N)][coluna].setBackground(Color.GREEN);
			tabVisual[linha+(1*N)][coluna].setEnabled(true);
		}
	}

	@Override
	public void cancelarOpcoes(JButton[][] tabVisual, Peca[][] tabLogico, int linha, int coluna) {

		int N = 1;
		int[] Movime = {1,2};
		
		if (tabLogico[linha][coluna].cor == corDaPeca.BRANCA)
			N = -1;
		
		for (int i = 0; i < Movime.length; i++) {
			if ((linha+(Movime[i]*N) >= 0) && (linha+(Movime[i]*N) <= 7 ))
				if (tabVisual[linha+(Movime[i]*N)][coluna].getBackground() == Color.GREEN){
					tabVisual[linha+(Movime[i]*N)][coluna].setEnabled(false);
				}
		}
	}
	
	@Override
	public boolean trocarPeca(TabuleiroVisual tabuleiro, JButton[][] tabVisual, Peca[][] tabLogico, int linha, int coluna) {
		if (((linha == 0) && (tabLogico[linha][coluna].cor == corDaPeca.BRANCA)) || ((linha == 7) && (tabLogico[linha][coluna].cor == corDaPeca.PRETA))) {
			opcoesTroca opcoesTroca = new opcoesTroca(tabuleiro, tabLogico[linha][coluna].cor, linha, coluna);
			return true;
		}
		return false;
	}

}