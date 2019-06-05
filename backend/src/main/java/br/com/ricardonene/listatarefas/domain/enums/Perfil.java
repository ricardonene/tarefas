package br.com.ricardonene.listatarefas.domain.enums;

public enum Perfil {
	USUARIO(1, "ROLE_USUARIO"),
	ADMIN(2, "ROLE_ADMIN"),
	PROFESSOR(3, "ROLE_PROFESSOR");

	private int codigo;
	private String descricao;

	private Perfil(int codigo, String descricao) {
		this.codigo = codigo;
		this.descricao = descricao;
	}

	public int getCodigo() {
		return codigo;
	}

	public String getDescricao() {
		return descricao;
	}

	public static Perfil toEnum(Integer codigo) {
		if (codigo == null) return null;
		
		for (Perfil tipo : Perfil.values()) {
			if (codigo.equals(tipo.getCodigo())) {
				return tipo;
			}
		}
		
		throw new IllegalArgumentException("Id Inválido: " + codigo);
	}
}
