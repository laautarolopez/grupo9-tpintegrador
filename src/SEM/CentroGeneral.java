package SEM;

import java.util.ArrayList;
import java.util.HashSet;

public final class Sem {
	
	private static Sem sem;
	
	private ArrayList<Registro> registros= new ArrayList<Registro>() ;
	
	private Sem() {
		
	}
	
	public boolean esValido(String patente) {
		for(Registro i : registros) {
			if(i.patente() == patente) {
				return i.estaVigente();
			}
		}
		return false;
	}
	
	public static Sem getSem() {
		if(sem == null) {
			sem = new Sem();
			return sem;
		}else {
			return sem;
		}
		
		
	}

	

}
