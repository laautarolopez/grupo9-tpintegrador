package SEM;

public interface Celular /*Puede ser temporal, despues lo resolvemos en el dise�o*/{

	String getNumero();

	int getSaldoActual();

	void agregarSaldo(int i);
	
	Zona getZona();
	
}
