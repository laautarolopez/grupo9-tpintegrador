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
	
	public void registrarCarga(String numero, int saldo) {
		this.celularesRegistrados.put(numero, saldo);
		Entry<String, Integer> datos = Map.entry(numero, saldo);
		this.notifyObservers(datos);
	}
	
	// Devuelve true si descontó el saldo suficiente del valor de una hora.
	public boolean registrarHora(String numero) {
		if(celularesRegistrados.containsKey(numero)) {
			int saldoActual = celularesRegistrados.get(numero);
			if(saldoActual >= this.valorDeHora) {
				saldoActual -= this.valorDeHora;
				celularesRegistrados.put(numero, saldoActual);
				Entry<String, Integer> datos = Map.entry(numero, saldoActual);
				this.notifyObservers(datos);
				return true;
			}
		}
		return false;
	}
}
