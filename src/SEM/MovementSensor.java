package SEM;

public interface MovementSensor {
	
	//Indica que el desplazamiento se realiza a bordo de un veh�culo.
	public void driving();
	
	//Indica que el desplazamiento se realiza caminando.
	public void walking();

	//No deber�a de ir este m�todo.
	public void addApp(AplicacionCliente aplicacionCliente);
}