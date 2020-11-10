package SEM;

public class PuntoDeVenta extends PuntoDeCarga implements GeneradorDeRegistros {
	protected Zona zona;
	protected CentroRegistros centroRegistros = CentroRegistros.getCentro();
	protected int monto;
	
	public PuntoDeVenta(Zona zona) {
		this.zona = zona;
		this.monto = 0;
	}
	
	public void iniciarEstacionamiento(String patente, int horas) {
		if (horas > 0) {
			this.monto += horas * 40;
			RegistroPuntoDeVenta registro = new RegistroPuntoDeVenta(patente,this.zona, horas);
			centroRegistros.registrarInicio(registro);
		}
	}
}
