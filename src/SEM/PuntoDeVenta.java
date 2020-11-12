package SEM;

public class PuntoDeVenta extends PuntoDeCarga implements GeneradorDeRegistros {
	protected String zona;
	protected CentroRegistros centroRegistros = CentroRegistros.getCentro();
	protected int monto;
	
	public PuntoDeVenta(String zona) {
		this.zona = zona;
		this.monto = 0;
	}
	
	public void iniciarEstacionamiento(String patente, int horas) {
		if (horas > 0) {
			this.monto += horas * valorDeHora;
			RegistroPuntoDeVenta registro = new RegistroPuntoDeVenta(patente,this.zona, horas);
			centroRegistros.registrarInicio(registro);
		}
	}
}
