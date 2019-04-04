package com.eduardo.factory;

import com.eduardo.view.opencv.ReconhecimentoLBPH;

public class Reconhecimento {
	public static void iniciar() {
		new Thread() {
			@Override
			public void run() {
				try {
					System.out.println("treinamento em andamnento..");
					new ReconhecimentoLBPH();

				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}.start();
	}
}
