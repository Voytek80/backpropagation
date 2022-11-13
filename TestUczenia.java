
public class TestUczenia {
	private static Siec siec;
	private static ElementUczacy [] ciagUczacy;
	private static ElementUczacy [] ciagTestowy;
	private static AlgorytmWstecznejPropagacjiBledow algorytm;
	
	public static void main(String[] args) {
		int [] tab = {3, 2, 1};
		siec = new Siec(2, 3, tab);
		/*double[][] daneUczace = {
			{ 0.0,  1.0},
			{ 1.0,  1.0},
			{-1.0,  0.0},
			{ 0.0, -1.0}
		};

		ciagUczacy = new ElementUczacy [] {
			new ElementUczacy(daneUczace[0], 1.0),
			new ElementUczacy(daneUczace[1], 1.0),
			new ElementUczacy(daneUczace[2], 0.0),
			new ElementUczacy(daneUczace[3], 0.0),
		};*/

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

		ciagUczacy = new ElementUczacy [] {
			new ElementUczacy(daneUczace[0], 1.0),
			new ElementUczacy(daneUczace[1], 1.0),
			new ElementUczacy(daneUczace[2], 1.0),
			new ElementUczacy(daneUczace[3], 1.0),
			new ElementUczacy(daneUczace[4], 1.0),
			new ElementUczacy(daneUczace[5], 0.0),
			new ElementUczacy(daneUczace[6], 0.0),
			new ElementUczacy(daneUczace[7], 0.0),
			new ElementUczacy(daneUczace[8], 0.0),
			new ElementUczacy(daneUczace[9], 0.0),
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

		ciagTestowy = new ElementUczacy [] {
			new ElementUczacy(daneTestowe[0], 1.0),
			new ElementUczacy(daneTestowe[1], 1.0),
			new ElementUczacy(daneTestowe[2], 1.0),
			new ElementUczacy(daneTestowe[3], 1.0),
			new ElementUczacy(daneTestowe[4], 1.0),
			new ElementUczacy(daneTestowe[5], 0.0),
			new ElementUczacy(daneTestowe[6], 0.0),
			new ElementUczacy(daneTestowe[7], 0.0),
			new ElementUczacy(daneTestowe[8], 0.0),
			new ElementUczacy(daneTestowe[9], 0.0),
		};

		algorytm = new AlgorytmWstecznejPropagacjiBledow(siec, ciagUczacy);
		algorytm.naucz(10000);

		int ok = 0;
		double rezultat;
		for(int i = 0; i < ciagTestowy.length; i++) {
			rezultat = siec.oblicz_wyjscie(ciagTestowy[i].pobierzDane())[0];
			if(rezultat >= 0.5) {
				if(ciagTestowy[i].pobierzWartoscPozadana() > 0.5)
					ok += 1;
			} else {
				if(ciagTestowy[i].pobierzWartoscPozadana() < 0.5)
					ok += 1;
			}
		}
		System.out.println("Skuteczność: " + ok);
	}
}
