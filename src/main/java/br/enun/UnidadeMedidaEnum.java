package br.enun;

import br.util.ConverterDados;

// Foi criado para evitar está 
// alterando sempre que recebia uma nova planilha

public enum UnidadeMedidaEnum {

	
	ARR(1,"ARR"),
	CB(2,"CB"),
	
	CX_360Un(3,"CX(360UN)"),

	
	DZ(4,"DZ"),
	
	FD_10(5,"FD(10KG)"),
	FD_20(6,"FD(30KG)"),
	FD_48(7,"FD(48X1)"),
	FD_64(8,"FD(64X1)"),
	
	HL(9,"HL"),
	JOGO(10,"JOGO"),
	KG(11,"KG"),
	L(12,"L"),
	LATA(13,"LATA"),
	LITRO(14,"LITRO"),
	METRO_QUADRADO(15,"M²"),
	METRO_CUBICO(16,"M³"),
	MILHEIRO(17,"MILHEIRO"),
	
	SC_25(18,"SC(25KG)"),
	SC_50(19,"SC(50KG)"),
	SC_60(20,"SC(60KG)"),
	
	
	T(21,"T"),
	TON(22,"TON"),
	UM(23,"UM(UNIDADE)"),
	UN(24,"UNIDADE"),
	NAO_DEFINIDO(0,"NAO DEFINIDO");
	


	
	private int codigo;
	private String unidade;
	
	private UnidadeMedidaEnum(int codigo, String unidade) {
		this.codigo=codigo;
		this.unidade=unidade;
	}
	
	public static UnidadeMedidaEnum getUnidadeMedidaEnum(String valor){
		for (UnidadeMedidaEnum e : UnidadeMedidaEnum.values()) {
			if(e.unidade.equals(ConverterDados.padronizaString(valor))) return e;
		}
		
		return NAO_DEFINIDO;
	}
	
	
	
	
	public int getCodigo() {
		return codigo;
	}

	public void setCodigo(int codigo) {
		this.codigo = codigo;
	}

	public String getUnidade() {
		return unidade;
	}
	public void setUnidade(String unidade) {
		this.unidade = unidade;
	}
	
	
	
	
	

}
