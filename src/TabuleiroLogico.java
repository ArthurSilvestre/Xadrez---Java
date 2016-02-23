public class TabuleiroLogico {

	Peca[][] posicoesPecas = new Peca[8][8];
	corDaPeca playerAtu = corDaPeca.BRANCA;
	final tipoDePecas[] tipos = {tipoDePecas.TORRE,tipoDePecas.CAVALO,tipoDePecas.BISPO,tipoDePecas.RAINHA,tipoDePecas.REI,tipoDePecas.BISPO,tipoDePecas.CAVALO,tipoDePecas.TORRE};
	
	public TabuleiroLogico() {
		for (int i = 0; i < posicoesPecas.length; i++) {
			for (int j = 0; j < posicoesPecas.length; j++) {
				posicoesPecas[i][j] = new SemPeca(); 
				for (int k = 0; k < corDaPeca.values().length ; k++) {
					int posPecas = k == 0 ? 0 : 7;
					int posPeoes = k == 0 ? 1 : 6;
					corDaPeca cor = corDaPeca.values()[k];
					tipoDePecas peca = i == posPeoes ? tipoDePecas.values()[0] : tipos[j];   

					if ((i == posPeoes) || (i == posPecas))
						posicoesPecas[i][j] = getTipoPeca(peca,cor);
				}
			}
		}
	}

	public Peca getTipoPeca(tipoDePecas peca,corDaPeca cor){
		switch (peca) {
			case PEAO:	return new Peao(cor);
			case TORRE: return new Torre(cor);
			case CAVALO: return new Cavalo(cor);
			case BISPO: return new Bispo(cor);
			case RAINHA: return new Rainha(cor);
			case REI: return new Rei(cor);
			default: return new SemPeca();
		}
	}

	public Peca[][] getPosicoesPecas() {
		return posicoesPecas;
	}

	public void setPosicoesPecas(Peca[][] posicoesPecas) {
		this.posicoesPecas = posicoesPecas;
	}

	public corDaPeca getPlayerAtu() {
		return playerAtu;
	}

	public void setPlayerAtu(corDaPeca playerAtu) {
		this.playerAtu = playerAtu;
	}

	public tipoDePecas[] getTipos() {
		return tipos;
	}
	
}
