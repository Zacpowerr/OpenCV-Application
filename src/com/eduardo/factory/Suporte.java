package com.eduardo.factory;

import com.eduardo.view.opencv.Captura;

public class Suporte {

	public static void iniciar() {
		new Thread() {
			@Override
			public void run() {
				try {
					new Captura();
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}.start();

	}

}
