/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.eduardo.view.opencv;

import static org.bytedeco.javacpp.opencv_imgcodecs.imwrite;
import static org.bytedeco.javacpp.opencv_imgproc.COLOR_BGRA2GRAY;
import static org.bytedeco.javacpp.opencv_imgproc.cvtColor;
import static org.bytedeco.javacpp.opencv_imgproc.rectangle;
import static org.bytedeco.javacpp.opencv_imgproc.resize;

import java.awt.Toolkit;

import javax.swing.JFrame;

import org.bytedeco.javacpp.opencv_core.Mat;
import org.bytedeco.javacpp.opencv_core.Rect;
import org.bytedeco.javacpp.opencv_core.RectVector;
import org.bytedeco.javacpp.opencv_core.Scalar;
import org.bytedeco.javacpp.opencv_core.Size;
import org.bytedeco.javacpp.opencv_objdetect.CascadeClassifier;
import org.bytedeco.javacv.CanvasFrame;
import org.bytedeco.javacv.OpenCVFrameConverter;
import org.bytedeco.javacv.OpenCVFrameGrabber;

import com.eduardo.control.PessoasControl;
import com.eduardo.dao.FotosDao;
import com.eduardo.model.Fotos;
import com.eduardo.view.TelaPrincipalAluno;

/**
 *
 * @author Pichau
 */
public class Captura {

	public static boolean tecla = false;
	private static FotosDao fotosdao = new FotosDao();
	private static Fotos fotos = null;
	private static PessoasControl pessoacontrol = new PessoasControl(null, null);

	public Captura() {
		try {
			iniciar(tecla);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public static void iniciar(boolean tecla) throws Exception, InterruptedException {

		
		
		OpenCVFrameConverter.ToMat converteMat = new OpenCVFrameConverter.ToMat();
		OpenCVFrameGrabber camera = new OpenCVFrameGrabber(0);

		camera.start();

		CascadeClassifier detectorFace = new CascadeClassifier("src\\recursos\\haarcascade-frontalface-alt.xml");
		CanvasFrame cFrame = new CanvasFrame("Captura", CanvasFrame.getDefaultGamma() / camera.getGamma());
		cFrame.setIconImage(Toolkit.getDefaultToolkit().getImage(TelaPrincipalAluno.class.getResource("/resources/icons8-webcam-26.png")));
		cFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		org.bytedeco.javacv.Frame frameCapturado = null;
		Mat imagemColorida = new Mat();
		int numeroAmostras = 50;
		int amostra = 1;

		int cadastro = pessoacontrol.pegarIdAction();
		int idPessoa = cadastro;
		System.err.println(cadastro);
		while ((frameCapturado = camera.grab()) != null) {
			imagemColorida = converteMat.convert(frameCapturado);
			Mat imagemCinza = new Mat();
			cvtColor(imagemColorida, imagemCinza, COLOR_BGRA2GRAY);
			RectVector facesDetectadas = new RectVector();
			detectorFace.detectMultiScale(imagemCinza, facesDetectadas, 1.1, 1, 0, new Size(150, 150),
					new Size(1500, 1500));

			for (int i = 0; i < facesDetectadas.size(); i++) {
				Rect dadosFace = facesDetectadas.get(0);
				rectangle(imagemColorida, dadosFace, new Scalar(0, 0, 255, 0));
				Mat faceCapturada = new Mat(imagemCinza, dadosFace);
				resize(faceCapturada, faceCapturada, new Size(160, 160));
				if (amostra <= numeroAmostras) {
					imwrite("src\\testeFotos\\pessoa-" + idPessoa + "-" + amostra + ".jpg", faceCapturada);
					System.out.println("Foto " + amostra + " capturada\n");
					fotos = new Fotos();
					fotos.setCaminho("src\\testeFotos\\pessoa-" + idPessoa + "-" + amostra + ".jpg");
					fotos.getPessoas().setId(cadastro);
					fotosdao.cadastrar(fotos);
					System.out.println("Foto "+amostra+ "Cadastrada");
					amostra++;

				}

			}
			if (cFrame.isVisible()) {
				cFrame.showImage(frameCapturado);
			}
			if (amostra > numeroAmostras) {
				break;
			}
		}

		cFrame.dispose();
		camera.stop();

	}

}
