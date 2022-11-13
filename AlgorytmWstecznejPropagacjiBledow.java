
public class AlgorytmWstecznejPropagacjiBledow {
	private Siec siec;
	private ElementUczacy [] ciagUczacy;
	
	public AlgorytmWstecznejPropagacjiBledow(Siec siec, ElementUczacy [] ciagUczacy) {
		this.siec = siec;
		this.ciagUczacy = ciagUczacy;
	}
	
	public void naucz(int liczbaEpok) {
		double eta = 0.1;
		double sigma = 0.0;
		for(int i = 0; i < liczbaEpok; i++) {
			sigma = uruchomEpoke(eta);
			siec.wprowadzKorekty();
			System.out.println("Błąd: " + Double.toString(sigma));
		}
	}
	
	public double uruchomEpoke(double eta) {
		double sigma;
		double bladSrKwadratowy = 0.0;
		for(int i = 0; i < ciagUczacy.length; i++) {
			// TODO: stworzyć tablicę sigm dla wszystkich wyjsc
			double y = siec.oblicz_wyjscie(ciagUczacy[i].pobierzDane())[0];
			sigma = ciagUczacy[i].pobierzWartoscPozadana() - y;
			bladSrKwadratowy += Math.pow(sigma, 2.0);
			siec.propagacjaBledowWstecz(sigma);
			siec.obliczKorekty(eta);
		}
		return bladSrKwadratowy / ciagUczacy.length;
	}
}
