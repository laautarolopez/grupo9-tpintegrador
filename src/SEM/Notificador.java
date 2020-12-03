package SEM;

public interface Notificador {

	void informarInicio(Celular celular, RegistroDeEstacionamiento registro);

	void informarFinal(Celular celular, RegistroDeEstacionamiento registro);

	void aconsejarInicio(Celular celular, AplicacionCliente app);

	void aconsejarFinal(Celular celular, AplicacionCliente app);
	
	void cambiarModo(AplicacionCliente app);
}
