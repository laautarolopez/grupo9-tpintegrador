package SEM;

public interface Notificador {

	void informarInicio(Celular celular, RegistroDeEstacionamiento registro);

	void informarFinal(Celular celular, RegistroDeEstacionamiento registro);

	void aconsejarInicio(AplicacionCliente app);

	void aconsejarFinal(AplicacionCliente app);
	
	void cambiarModo(AplicacionCliente app);
}
