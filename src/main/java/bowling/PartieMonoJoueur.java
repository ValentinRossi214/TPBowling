package bowling;

import java.util.ArrayList;
import java.util.List;

/**
 * Cette classe a pour but d'enregistrer le nombre de quilles abattues lors des
 * lancers successifs d'<b>un seul et même</b> joueur, et de calculer le score
 * final de ce joueur
 */
public class PartieMonoJoueur {

	private int score;
	private Tour tourCourant;
	private int prochainLancer;
	private ArrayList<Tour> tours;
	
	/**
	 * Constructeur
	 */
	public PartieMonoJoueur() {
		score = 0;
		tourCourant = new Tour();
		prochainLancer = 1;
		tours = new ArrayList<Tour>();
	}

	/**
	 * Cette méthode doit être appelée à chaque lancer de boule
	 *
	 * @param nombreDeQuillesAbattues le nombre de quilles abattues lors de ce lancer
	 * @throws IllegalStateException si la partie est terminée
	 * @return vrai si le joueur doit lancer à nouveau pour continuer son tour, faux sinon	
	 */
	public boolean enregistreLancer(int nombreDeQuillesAbattues) {
		if (estTerminee()) {
			throw new IllegalStateException("La partie est terminée");
		}
		
		if((tourCourant.isLance2effectue() && tours.size() <= 9) || tours.isEmpty()) {
			tourCourant = new Tour();
			tours.add(tourCourant);
		}
		
		if (!tourCourant.isLance1effectue()) {
			tourCourant.enregistreLancer(nombreDeQuillesAbattues);
			if(tourCourant.isStrike()) {
				return false;
			} else {
				prochainLancer = 2;
				return true;
			}
		} else {
			tourCourant.enregistreLancer(nombreDeQuillesAbattues);
			prochainLancer = 1;
			return false;
		}
	}

	/**
	 * Cette méthode donne le score du joueur.
	 * Si la partie n'est pas terminée, on considère que les lancers restants
	 * abattent 0 quille.
	 * @return Le score du joueur
	 */
	public int score() {
		if(!estTerminee()) {;
			for(int i = tours.size() - 1 ; i >= 0 ; i--) {
				if(tours.get(i).isStrike()) {
					if(i == tours.size() - 1) {
						tours.get(i).setScoreFinal(10);
					} else if (i == tours.size() - 2) {
						tours.get(i).setScoreFinal(10 + tours.get(i + 1).getScoreFinal());
					} else {
						tours.get(i).setScoreFinal(10 + tours.get(i + 1).getScoreFinal() + tours.get(i + 2).getScoreFinal());
					}
				} else if (tours.get(i).isSpare()) {
					if(i == tours.size() - 1) {
						tours.get(i).setScoreFinal(10);
					} else {
						tours.get(i).setScoreFinal(10 + tours.get(i + 1).getScoreLance1());
					}
				} else {
					tours.get(i).setScoreFinal(tours.get(i).getScoreLance1() + tours.get(i).getScoreLance2());
				}
			}
		} else {
			throw new UnsupportedOperationException("Pas encore implémenté");
		}
		
		for (Tour tour : tours) {
			score += tour.getScoreFinal();
		}
		
		return score;
	}

	/**
	 * @return vrai si la partie est terminée pour ce joueur, faux sinon
	 */
	public boolean estTerminee() {
		return tours.size() == 10 && tourCourant.isLance2effectue();
	}


	/**
	 * @return Le numéro du tour courant [1..10], ou 0 si le jeu est fini
	 */
	public int numeroTourCourant() {
		if(estTerminee())
			return 0;
		else
			return tours.size() + 1;
	}

	/**
	 * @return Le numéro du prochain lancer pour tour courant [1..3], ou 0 si le jeu
	 *         est fini
	 */
	public int numeroProchainLancer() {
		if (estTerminee())
			return 0;
		else
			return prochainLancer;
	}

	@Override
	public String toString() {
		return "PartieMonoJoueur{" +
			"tours=" + tours +
			'}';
	}
}
