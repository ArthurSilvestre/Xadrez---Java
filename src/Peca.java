import javax.swing.JButton;

public abstract class Peca {
	corDaPeca cor;
	tipoDePecas tipo;
	
	public Peca(){}
	public Peca(corDaPeca cor,tipoDePecas tipo){
		this.cor = cor; 
		this.tipo = tipo;
	}
	
	public corDaPeca getCor() {
		return cor;
	}

	public void setCor(corDaPeca cor) {
		this.cor = cor;
	}
	
	public abstract void pintarOpcoes(JButton[][] tabVisual,Peca[][] tabLogico,int linha,int coluna);
	public abstract void cancelarOpcoes(JButton[][] tabVisual,Peca[][] tabLogico,int linha,int coluna);
	public abstract boolean trocarPeca(TabuleiroVisual tabuleiro, JButton[][] tabVisual,Peca[][] tabLogico,int linha,int coluna);
}