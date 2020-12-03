package SEM;

public class PuntoDeCarga {
	Sistema sistema;
	
	public PuntoDeCarga(Sistema sistema) {
		this.sistema = sistema;
	}
	
	public void recargarCelular(Celular celular, int monto) {
		sistema.agregarSaldo(celular.getNumero(),monto);
	}
	
}
