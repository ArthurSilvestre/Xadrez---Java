import java.awt.Color;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import javax.swing.ImageIcon;
import javax.swing.JButton;

public class Movimento {

	boolean pintarBase = true;
	boolean movimentoFinalizado = false;

	public Movimento(Replay tabuleiro, int iniL, int iniC,int fimL,int fimC, tipoDePecas peca) {
		tabuleiro.tabVisual[fimL][fimC].setIcon(getIconPeca(tabuleiro.tabLogico.posicoesPecas[iniL][iniC].cor, peca));
		tabuleiro.tabVisual[iniL][iniC].setIcon(null);
		
		tabuleiro.tabLogico.posicoesPecas[fimL][fimC] = tabuleiro.tabLogico.posicoesPecas[iniL][iniC];
		tabuleiro.tabLogico.posicoesPecas[iniL][iniC] = new SemPeca();
		
		tabuleiro.tabVisual[fimL][fimC].setEnabled(true);
		tabuleiro.tabVisual[iniL][iniC].setEnabled(false);
	}
	
	public Movimento(TabuleiroVisual tabuleiro, int linha, int coluna, corDaPeca pecaDaVez,File replayFile) throws IOException{
		if (tabuleiro.tabVisual[linha][coluna].getBackground() == Color.BLUE){
			tabuleiro.tabLogico.posicoesPecas[linha][coluna].cancelarOpcoes(tabuleiro.tabVisual, tabuleiro.tabLogico.posicoesPecas, linha, coluna);
		} else if (tabuleiro.tabVisual[linha][coluna].getBackground() == Color.GREEN || tabuleiro.tabVisual[linha][coluna].getBackground() == Color.RED) {
			for (int i = 0; i < tabuleiro.tabVisual.length; i++) {
				for (int j = 0; j < tabuleiro.tabVisual.length; j++) {
					if (tabuleiro.tabVisual[i][j].getBackground() == Color.BLUE) {
						
						if (tabuleiro.tabLogico.posicoesPecas[linha][coluna].tipo == tipoDePecas.REI){
							tabuleiro.vitoria();
							tabuleiro.tabuleiroOposto.derrota();
						}						
						
						tabuleiro.tabLogico.posicoesPecas[i][j].cancelarOpcoes(tabuleiro.tabVisual, tabuleiro.tabLogico.posicoesPecas, i, j);
						
						tabuleiro.tabVisual[linha][coluna].setIcon(tabuleiro.tabVisual[i][j].getIcon());
						tabuleiro.tabVisual[i][j].setIcon(null);
						
						tabuleiro.tabLogico.posicoesPecas[linha][coluna] = tabuleiro.tabLogico.posicoesPecas[i][j];
						tabuleiro.tabLogico.posicoesPecas[i][j] = new SemPeca();
						
						tabuleiro.tabVisual[linha][coluna].setEnabled(true);
						tabuleiro.tabVisual[i][j].setEnabled(false);
						
						movimentoFinalizado = true;
						
						tabuleiro.tabLogico.posicoesPecas[linha][coluna].trocarPeca(tabuleiro,tabuleiro.tabVisual,tabuleiro.tabLogico.posicoesPecas,linha,coluna);
						
						//VER COM RAPHAEL QUAL A MELHOR FORMA DE AJEITAR ISTO//EM RELAÇÃO AO REPLAY//
						
						FileWriter FW = new FileWriter(replayFile,true);
						BufferedWriter BW = new BufferedWriter(FW);
						BW.write("PI{"+i+","+j+"}PF{"+linha+","+coluna+"}P{"+tabuleiro.tabLogico.posicoesPecas[linha][coluna].tipo+"};");
						
						BW.close();
						FW.close();
						
						tabuleiro.tabuleiroOposto.atualizar();
					}
				}
			}
		} else {
			for (int i = 0; i < tabuleiro.tabVisual.length; i++) {
				for (int j = 0; j < tabuleiro.tabVisual.length; j++) {
					if (tabuleiro.tabVisual[i][j].getBackground() == Color.BLUE) {
						tabuleiro.tabLogico.posicoesPecas[i][j].cancelarOpcoes(tabuleiro.tabVisual, tabuleiro.tabLogico.posicoesPecas, i, j);
					}
				}
			}
			PintarBase(tabuleiro.tabVisual);
			if (pecaDaVez == tabuleiro.tabLogico.posicoesPecas[linha][coluna].cor) {
				tabuleiro.tabLogico.posicoesPecas[linha][coluna].pintarOpcoes(tabuleiro.tabVisual, tabuleiro.tabLogico.posicoesPecas, linha, coluna);
				pintarBase = false;
			}
		}
	}

	public boolean isPintarBase() {
		return pintarBase;
	}

	public void setPintarBase(boolean pintarBase) {
		this.pintarBase = pintarBase;
	}

	public boolean isMovimentoFinalizado() {
		return movimentoFinalizado;
	}

	public void setMovimentoFinalizado(boolean movimentoFinalizado) {
		this.movimentoFinalizado = movimentoFinalizado;
	}

	public void PintarBase(JButton[][] tabVisual) {
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
	
	public ImageIcon getIconPeca(corDaPeca cor, tipoDePecas peca) {
		return new ImageIcon(new ImageIcon("icons/" + cor + "/" + peca + ".png").getImage().getScaledInstance(64, 64,java.awt.Image.SCALE_SMOOTH));
	}
	
}