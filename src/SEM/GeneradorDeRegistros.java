package SEM;

public interface GeneradorDeRegistros {
	public int valorDeHora = 40;
	public void iniciarEstacionamiento(String patente, int horas) throws Exception;
}
