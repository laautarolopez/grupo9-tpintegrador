package SEM;

public class Driving extends Estado {
	
	@Override
	public void aconsejarInicio(AplicacionCliente aplicacion) {
		aplicacion.aconsejarInicio();
	}
	
	@Override
	public void aconsejarFinal(AplicacionCliente aplicacion) {
		aplicacion.aconsejarFinal();
	}
}