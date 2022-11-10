import java.awt.Color;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.Graphics;
import java.awt.Toolkit;
import java.io.InterruptedIOException;
import java.util.Random;
import java.util.concurrent.atomic.DoubleAccumulator;
import java.util.function.IntToDoubleFunction;

import javax.print.CancelablePrintJob;
import javax.swing.*;
import javax.swing.text.StyledEditorKit.ForegroundAction;


public class Test{

	
	Siec siec;
	Neuron neuron;
	Warstwa warstwa;
	
	
	
	
	public Test() {
		
		double [] wej=new double[1];
		double [] wynik;
		double [] wartosc_oczekiwana = new double[2];
		double blad_calkowity = 0;
		int neurony_warstwy_1 = 2;
		int neurony_warstwy_2 = 2;
		int neurony_warstwy_3 = 2;
		int neurony_warstwy_4 = 2;
		int ilosc_warstw = 4;
		double [][] tablica_y = new double[ilosc_warstw][neurony_warstwy_1] ;
		double [][] tablica_bledow = new double [ilosc_warstw][neurony_warstwy_1]; 
		double [][][] tablica_wag = new double [ilosc_warstw][neurony_warstwy_1][neurony_warstwy_1+1]; 
		wartosc_oczekiwana[0]=1;
		wartosc_oczekiwana[1]=0;
		double wspolczynnik_uczenia = 0.01;
		int liczba_epok = 100;
		
		
		
		
		
		wej[0] = 5;
		//wej[1] = 10;
		int [] tab=new int [4];
		tab[0]=neurony_warstwy_1; tab[1]=neurony_warstwy_2; tab[2]=neurony_warstwy_3; tab[3]=neurony_warstwy_4;
		siec=new Siec(1,4,tab);
		
		
		for (int e = 0; e < liczba_epok; e++) {
			tablica_wag = new double [ilosc_warstw][neurony_warstwy_1][neurony_warstwy_1+1];
			
				
				
				
			
			
			
				wynik=siec.oblicz_wyjscie(wej);
				System.out.println(wynik[0]);
				System.out.println(wynik[1]);
				
				
				/*
				for (int i = 0; i < tab[3];i++) {
					blad_calkowity += wartosc_oczekiwana[i] - wynik[i];    // do poprawy
				}
				blad_calkowity = wartosc_oczekiwana[1] - wynik[1];
				System.out.println("Błąd całkowity: " + blad_calkowity);
				//System.out.println("Błąd kwadratowy: " + blad_calkowity*blad_calkowity);
				*/
				
				
				
				// Tablica wyjsc y  (wyjscia dla kazdego neuronu)
				for (int j = 0; j<tab[0];j++) {
					tablica_y[0][j] = siec.warstwy[0].neurony[j].oblicz_wyjscie(wej);
					
				}
				
				for (int i=1;i<ilosc_warstw;i++) {
					for (int j=0;j<tab[i];j++) {
						tablica_y[i][j] = siec.warstwy[i].neurony[j].oblicz_wyjscie(tablica_y[i-1]);	
						
					}
				}
				
				// tablica bledow sigma  (blad dla kazdego neurony obliczanyn od ostatniej warstwy
				tablica_bledow = new double [ilosc_warstw][neurony_warstwy_1];
				for (int x = 0; x<neurony_warstwy_4;x++) {
					tablica_bledow[ilosc_warstw-1][x] = (wartosc_oczekiwana[x] - wynik[x]);

				}
				
				for (int i = ilosc_warstw-2; i >=0; i--) {
					for (int j = 0; j < tab[i];j++ ) {
						for (int k = 0; k < tab[i+1];k++) {
							
							tablica_bledow[i][j] += tablica_bledow[i+1][k]*siec.warstwy[i+1].neurony[k].wagi[j+1];
							
						}
					}
				}
				
				
				// TABLICA NOWYCH WAG				wnew =wold +η * blad * y(1−y)x
				// nowe bias
				
				
				for (int i = 0; i< ilosc_warstw;i++) {
					for (int j = 0; j< tab[i];j++) {
						
						siec.warstwy[i].neurony[j].wagi[0] = siec.warstwy[i].neurony[j].wagi[0] + wspolczynnik_uczenia*tablica_bledow[i][j]*tablica_y[i][j];	
						
					}
				}
				// nowe wagi (warstwa 0)
				for (int j = 0; j< tab[0];j++) {
					for (int k = 1; k <  siec.warstwy[0].neurony[j].wagi.length; k++) {
						siec.warstwy[0].neurony[j].wagi[k] = siec.warstwy[0].neurony[j].wagi[k] + wspolczynnik_uczenia*tablica_bledow[0][j]*tablica_y[0][j]*(1-tablica_y[0][j])*wej[0];
						
						}
						}
				
				// nowe wagi (pozozstale warstwy)
				for (int i = 1; i < ilosc_warstw; i++) {
					for (int j = 0; j<tab[i];j++ ) {
						for (int k = 1; k< siec.warstwy[i].neurony[j].wagi.length; k++) {
							siec.warstwy[i].neurony[j].wagi[k] = siec.warstwy[i].neurony[j].wagi[k] + wspolczynnik_uczenia*tablica_bledow[i][j]*tablica_y[i-1][j]*(1-tablica_y[i-1][j]);
							
						}
					}
				}
				
				
			
			/*
			// Nadanie sumy nowych wag
			for (int i = 0; i < ilosc_warstw; i++) {
				for (int j = 0; j < tab[i]; j++) {
					for (int k = 0; k < siec.warstwy[i].neurony[j].wagi.length; k++) {
						siec.warstwy[i].neurony[j].wagi[k] = tablica_wag[i][j][k];
					}
				}
			}*/
		}
		
		
		
		
	}

	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			
			@Override
			public void run() {
				new Test();
			}
		});
	}

}
