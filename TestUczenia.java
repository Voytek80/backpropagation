
public class TestUczenia {
	private static Siec siec;
	private static ElementUczacy [] ciagUczacy;
	private static ElementUczacy [] ciagTestowy;
	private static AlgorytmWstecznejPropagacjiBledow algorytm;
	
	public static void main(String[] args) {
		int [] tab = {3, 2, 1};
		siec = new Siec(2, 3, tab);

		double[][] daneUczace = {
			{ 0.0,  1.0},
			{ 1.0,  1.0},
			{-1.0,  2.0},
			{ 1.0, -0.5},
			{ 3.0,  3.0},	
			{-1.0,  0.0},
			{ 0.0, -1.0},
			{ 1.0, -2.0},
			{-2.0, -0.5},
			{-3.0, -3.0}
		};
		double[][] pozadaneUczace = {{1.0}, {1.0}, {1.0}, {1.0}, {1.0}, {0.0},	{0.0}, {0.0}, {0.0}, {0.0},};

		ciagUczacy = new ElementUczacy [] {
				new ElementUczacy(daneUczace[0], pozadaneUczace[0]),
				new ElementUczacy(daneUczace[1], pozadaneUczace[1]),
				new ElementUczacy(daneUczace[2], pozadaneUczace[2]),
				new ElementUczacy(daneUczace[3], pozadaneUczace[3]),
				new ElementUczacy(daneUczace[4], pozadaneUczace[4]),
				new ElementUczacy(daneUczace[5], pozadaneUczace[5]),
				new ElementUczacy(daneUczace[6], pozadaneUczace[6]),
				new ElementUczacy(daneUczace[7], pozadaneUczace[7]),
				new ElementUczacy(daneUczace[8], pozadaneUczace[8]),
				new ElementUczacy(daneUczace[9], pozadaneUczace[9]),
		};

		double[][] daneTestowe = {
			{ 0.5,  0.5},
			{ 0.7,  0.7},
			{-1.0,  3.0},
			{ 1.0, -0.7},
			{ 3.0,  8.0},	
			{-1.0,  0.9},
			{ 0.0, -0.1},
			{ 0.2, -0.4},
			{-1.5,  1.2},
			{-3.0, -9.0}
		};
		double[][] pozadaneTestowe = {{1.0}, {1.0}, {1.0}, {1.0}, {1.0}, {0.0},	{0.0}, {0.0}, {0.0}, {0.0},};

		ciagTestowy = new ElementUczacy [] {
			new ElementUczacy(daneTestowe[0], pozadaneTestowe[0]),
			new ElementUczacy(daneTestowe[1], pozadaneTestowe[1]),
			new ElementUczacy(daneTestowe[2], pozadaneTestowe[2]),
			new ElementUczacy(daneTestowe[3], pozadaneTestowe[3]),
			new ElementUczacy(daneTestowe[4], pozadaneTestowe[4]),
			new ElementUczacy(daneTestowe[5], pozadaneTestowe[5]),
			new ElementUczacy(daneTestowe[6], pozadaneTestowe[6]),
			new ElementUczacy(daneTestowe[7], pozadaneTestowe[7]),
			new ElementUczacy(daneTestowe[8], pozadaneTestowe[8]),
			new ElementUczacy(daneTestowe[9], pozadaneTestowe[9]),
		};

		algorytm = new AlgorytmWstecznejPropagacjiBledow(siec, ciagUczacy);
		algorytm.naucz(10000);

		int ok = 0;
		double [] rezultat;
		for(int i = 0; i < ciagTestowy.length; i++) {
			rezultat = siec.oblicz_wyjscie(ciagTestowy[i].pobierzDane());
			for(int j = 0; j < rezultat.length; j++) {
				if(rezultat[j] >= 0.5) {
					if(ciagTestowy[i].pobierzWartoscPozadana()[j] > 0.5)
						ok += 1;
				} else {
					if(ciagTestowy[i].pobierzWartoscPozadana()[j] < 0.5)
						ok += 1;
				}
			}
		}
		System.out.println("Skuteczność: " + ok);
	}
}
