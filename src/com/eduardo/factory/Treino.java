package com.eduardo.factory;

import com.eduardo.view.opencv.ReconhecimentoFisher;
import com.eduardo.view.opencv.ReconhecimentoLBPH;
import com.eduardo.view.opencv.Treinamento;

public class Treino {
		public static void iniciar(){
			  new Thread() {
			    @Override
			    public void run() {
			       try {
			    	   System.out.println("treinamento em andamnento..");
			    	   Treinamento.iniciar();
			    	   Thread.sleep(10);
			    	  
			    	   ReconhecimentoLBPH.iniciar();
			    	   
			    	  
					} catch (Exception e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
			    }
			  }.start();
			 
		}

}
