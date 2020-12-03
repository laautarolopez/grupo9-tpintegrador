package SEM;

import java.util.HashMap;
import java.util.Map;

public class CentroCelulares extends Observable {
	private Map<String,Integer> celularesRegistrados = new HashMap<String,Integer>();
	
	public int saldoDe(String numero) {
		return this.celularesRegistrados.get(numero) == null? 0 : this.celularesRegistrados.get(numero);
	}
	
	public void agregarSaldo(String numero, int monto) {
		this.registrarCambio(numero, this.saldoDe(numero) + monto);
		this.notificarObservers(numero, monto);
	}

	public void restarSaldo(String numero, int costo) {
		this.registrarCambio(numero, this.saldoDe(numero) - costo);
	}

	private void registrarCambio(String numero, int saldo) {
		this.celularesRegistrados.put(numero, saldo);
	}
	
	private void notificarObservers(String numero, int monto) {
		String resultado = "Se recargó $" + monto + "al número " + numero + "\n" +
				"El crédito actual es de: $" + this.saldoDe(numero) + "\n";
		this.notifyObservers(resultado);
	}
}