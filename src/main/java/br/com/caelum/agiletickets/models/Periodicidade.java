package br.com.caelum.agiletickets.models;

public enum Periodicidade {

	DIARIA(1, "Diaria"), SEMANAL(2, "Semanal");

	private Periodicidade(int codigo, String desc) {
		this.codigo = codigo;
		this.descricao = desc;
	}

	private int codigo;
	private String descricao;

	public int getCodigo() {
		return codigo;
	}

	public void setCodigo(int codigo) {
		this.codigo = codigo;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

}
