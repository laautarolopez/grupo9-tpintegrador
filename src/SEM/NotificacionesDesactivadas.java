package SEM;

public class NotificacionesDesactivadas implements Notificador{

	@Override
	public void informarInicio(Celular celular, RegistroDeEstacionamiento registro) {
		
		
	}

	

	@Override
	public void informarFinal(Celular celular, RegistroDeEstacionamiento registro) {
		
	}



	@Override
	public void aconsejarInicio(AplicacionCliente app) {
		
	}



	@Override
	public void aconsejarFinal(AplicacionCliente app) {
		
	}



	@Override
	public void cambiarModo(AplicacionCliente app) {
		app.setNotificador(new NotificacionesActivadas());
		
	}
	
	@Override 
	public boolean equals(Object o) {
		if (o == this) { 
            return true; 
        }

		return !(o instanceof NotificacionesDesactivadas);
	}
}
