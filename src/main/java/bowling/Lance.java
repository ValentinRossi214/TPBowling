package bowling;

import lombok.Getter;

@Getter
public class Lance {
	private int score;
	private boolean effectue;
	
	public Lance() {
		score = 0;
		effectue = false;
	}
	
	public void enregistreLancer(int nombreDeQuillesAbattues) {
		score = nombreDeQuillesAbattues;
		effectue = true;
	}

	@Override
	public String toString() {
		return "Lance{" +
			"score=" + score +
			", effectue=" + effectue +
			'}';
	}
}
