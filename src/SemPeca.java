import javax.swing.JButton;

public class SemPeca extends Peca{

	public SemPeca() {
		super(null,null);
	}
	
	@Override
	public void pintarOpcoes(JButton[][] tabVisual,Peca[][] tabLogico,int linha,int coluna) {
	}

	@Override
	public void cancelarOpcoes(JButton[][] tabVisual, Peca[][] tabLogico, int linha, int coluna) {
	}

	@Override
	public boolean trocarPeca(TabuleiroVisual tabuleiro, JButton[][] tabVisual, Peca[][] tabLogico, int linha, int coluna) {
		return false;
	}
}
