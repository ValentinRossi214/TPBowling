package bowling;

public class Main {
	public static void main(String[] args) {
		PartieMonoJoueur partie = new PartieMonoJoueur();
		
		partie.enregistreLancer(5);
		partie.enregistreLancer(5);
		
		partie.enregistreLancer(3);
		
		System.out.println(partie);
	}
}
