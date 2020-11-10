package SEM;

public class PuntoDeCarga {
	protected CentroCelulares centroCelulares = CentroCelulares.getCentroCelulares();
	
	public void recargarCelular(Celular celular, int monto) {
		this.centroCelulares.agregarSaldo(celular.getNumero(),monto);
	}
	
}
