package SEM;

public interface Celular /*Puede ser temporal, despues lo resolvemos en el diseño*/{

	String getNumero();

	int getSaldoActual();

	void agregarSaldo(int i);

	void notificacionDeFin(String resumen);
	
}
