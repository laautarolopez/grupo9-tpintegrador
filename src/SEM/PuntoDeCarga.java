package SEM;

public class PuntoDeCarga {
	protected CentroCelulares centroCelulares = CentroCelulares.getCentroCelulares();
	
	public void recargarCelular(Celular celular, int monto) {
		celular.agregarSaldo(monto);
		this.centroCelulares.registrarCarga(celular.getNumero(),celular.getSaldoActual());
	}
	
}
