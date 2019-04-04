/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.eduardo.view.opencv;

import static org.bytedeco.javacpp.opencv_core.CV_32SC1;
import static org.bytedeco.javacpp.opencv_imgcodecs.CV_LOAD_IMAGE_GRAYSCALE;
import static org.bytedeco.javacpp.opencv_imgcodecs.imread;
import static org.bytedeco.javacpp.opencv_imgproc.resize;

import java.io.File;
import java.io.FilenameFilter;
import java.nio.IntBuffer;

import org.bytedeco.javacpp.opencv_core.Mat;
import org.bytedeco.javacpp.opencv_core.MatVector;
import org.bytedeco.javacpp.opencv_core.Size;
import org.bytedeco.javacpp.opencv_face.EigenFaceRecognizer;
import org.bytedeco.javacpp.opencv_face.FaceRecognizer;
import org.bytedeco.javacpp.opencv_face.FisherFaceRecognizer;
import org.bytedeco.javacpp.opencv_face.LBPHFaceRecognizer;

/**
 *
 * @author Pichau
 */
public class Treinamento {

	public static void iniciar() {
		File diretorio = new File("src\\testeFotos");
		FilenameFilter filtroImagem = new FilenameFilter() {
			@Override
			public boolean accept(File dirm, String nome) {
				return nome.endsWith(".jpg") || nome.endsWith(".gif") || nome.endsWith(".png");
			}

		};
		File[] arquivos = diretorio.listFiles(filtroImagem);
		MatVector fotos = new MatVector(arquivos.length);
		Mat rotulos = new Mat(arquivos.length, 1, CV_32SC1);
		IntBuffer rotulosBuffer = rotulos.createBuffer();
		int contador = 0;
		for (File imagem : arquivos) {
			Mat foto = imread(imagem.getAbsolutePath(), CV_LOAD_IMAGE_GRAYSCALE);
			int classe = Integer.parseInt(imagem.getName().split("\\-")[1]);
			// System.out.println(classe);
			resize(foto, foto, new Size(160, 160));
			fotos.put(contador, foto);
			rotulosBuffer.put(contador, classe);
			contador++;

		}
		// FaceRecognizer eigenfaces = EigenFaceRecognizer.create(10,0);
		// FaceRecognizer fisherfaces = FisherFaceRecognizer.create();
		FaceRecognizer lbph = LBPHFaceRecognizer.create(12, 10, 10, 10, 0);

		// eigenfaces.train(fotos, rotulos);
		// eigenfaces.save("src\\recursos\\classificadorEigenFaces.yml");
		// fisherfaces.train(fotos, rotulos);
		// fisherfaces.save("src\\recursos\\classificadorFisherFaces.yml");
		lbph.train(fotos, rotulos);
		lbph.save("src\\recursos\\classificadorLBPH.yml");
		System.out.println("Treinamento concluido");

	}

}
