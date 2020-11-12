package SEM;

import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;

public class CentroCelulares extends Observable{
	protected int valorDeHora = 40;
	
	protected HashMap<String,Integer> celularesRegistrados = new HashMap<String,Integer>();
	
	protected static CentroCelulares centro = null;
	
	protected CentroCelulares() {
		
	}
	
	public static CentroCelulares getCentroCelulares() {
		if(centro == null) {
			centro = new CentroCelulares();
			return centro;
		}else {
			return centro;
		} 
	}
	
	public void registrarCambio(String numero, int saldo) {
		this.celularesRegistrados.put(numero, saldo);
		this.notificarObservers(numero, saldo);
	}
	
	// Prec.: el numero dado debe estar registrado en el HashMap.
	public boolean tieneSaldoSuficiente(String numero) {
		int saldo = celularesRegistrados.get(numero);
		return saldo >= this.valorDeHora;
	}
	
	private void notificarObservers(String numero, int saldo) {
		Entry<String, Integer> datos = Map.entry(numero, saldo);
		this.notifyObservers(datos);
	}
	
	public int saldoDe(String numero) {
		return this.celularesRegistrados.get(numero) == null? 0 : this.celularesRegistrados.get(numero);
	}

	public void restarSaldo(String numero, int costo) {
		this.registrarCambio(numero, this.saldoDe(numero) - costo);
	}

	public void agregarSaldo(String numero, int monto) {
		this.registrarCambio(numero, this.saldoDe(numero) + monto);
	}
	
	public void validarSaldo(String numero, int valorDeHora) throws Exception {
		if(!(this.saldoDe(numero) >= valorDeHora)) {
			throw new Exception("Saldo insuficiente. Estacionamiento no permitido.");
		}
	}
}