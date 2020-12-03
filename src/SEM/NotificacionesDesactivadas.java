package SEM;

public class NotificacionesDesactivadas implements Notificador{

	@Override
	public void informarInicio(Celular celular, RegistroDeEstacionamiento registro) {
		
		
	}

	

	@Override
	public void informarFinal(Celular celular, RegistroDeEstacionamiento registro) {
		
	}



	@Override
	public void aconsejarInicio(Celular celular, AplicacionCliente app) {
		
	}



	@Override
	public void aconsejarFinal(Celular celular, AplicacionCliente app) {
		
	}



	@Override
	public void cambiarModo(AplicacionCliente app) {
		app.setNotificador(new NotificacionesActivadas());
		
	}

}
