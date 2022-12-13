package br.com.ecoffee.exception;

public class UniqueException extends RuntimeException {

	private static final long serialVersionUID = 1L;

	private String campo;

	public UniqueException(String string) {
		super(string);
	}

	public UniqueException(String mensagem, String campo) {
		super(mensagem);
		this.campo = campo;
	}

	public String getCampo() {
		return campo;
	}

}
