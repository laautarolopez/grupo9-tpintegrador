package SEM;

public class Sem {
	
	private static Sem sem;
	
	private Sem() {
		
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
