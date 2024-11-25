package bowling;

import lombok.*;

@Getter
public class Tour {
	private Lance lance1;
	private Lance lance2;
	private boolean strike;
	private boolean spare;
	@Setter
	private int scoreFinal;
	
	public Tour() {
		lance1 = new Lance();
		lance2 = new Lance();
		strike = false;
		spare = false;
		scoreFinal = 0;
	}
	
	public void enregistreLancer(int nombreDeQuillesAbattues) {
		if(!lance1.isEffectue()) {
			if (nombreDeQuillesAbattues == 10) {
				lance1.enregistreLancer(10);
				strike = true;
			} else {
				lance1.enregistreLancer(nombreDeQuillesAbattues);
			}
		} else {
			if (nombreDeQuillesAbattues + lance1.getScore() == 10) {
				lance2.enregistreLancer(nombreDeQuillesAbattues);
				spare = true;
			} else {
				lance2.enregistreLancer(nombreDeQuillesAbattues);
			}
		}
	}
	
	public boolean isLance1effectue() {
		return lance1.isEffectue();
	}
	
	public boolean isLance2effectue() {
		return lance2.isEffectue();
	}
	
	public int getScoreLance1() {
		return lance1.getScore();
	}
	
	public int getScoreLance2() {
		return lance2.getScore();
	}

	@Override
	public String toString() {
		return "Tour{" +
			"\nlance1=" + lance1 +
			"\n, lance2=" + lance2 +
			"\n, strike=" + strike +
			"\n, spare=" + spare +
			"\n, scoreFinal=" + scoreFinal +
			'}';
	}
}
