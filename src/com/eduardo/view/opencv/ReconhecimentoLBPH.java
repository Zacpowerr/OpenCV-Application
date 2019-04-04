package com.eduardo.view.opencv;

import static org.bytedeco.javacpp.opencv_core.FONT_HERSHEY_PLAIN;
import static org.bytedeco.javacpp.opencv_imgproc.COLOR_BGRA2GRAY;
import static org.bytedeco.javacpp.opencv_imgproc.cvtColor;
import static org.bytedeco.javacpp.opencv_imgproc.putText;
import static org.bytedeco.javacpp.opencv_imgproc.rectangle;
import static org.bytedeco.javacpp.opencv_imgproc.resize;

import java.util.ArrayList;
import java.util.List;

import javax.swing.JFrame;
import javax.swing.JOptionPane;

import org.bytedeco.javacpp.DoublePointer;
import org.bytedeco.javacpp.IntPointer;
import org.bytedeco.javacpp.opencv_core;
import org.bytedeco.javacpp.opencv_core.Mat;
import org.bytedeco.javacpp.opencv_core.Point;
import org.bytedeco.javacpp.opencv_core.Rect;
import org.bytedeco.javacpp.opencv_core.RectVector;
import org.bytedeco.javacpp.opencv_core.Scalar;
import org.bytedeco.javacpp.opencv_core.Size;
import org.bytedeco.javacpp.opencv_face.FaceRecognizer;
import org.bytedeco.javacpp.opencv_face.FisherFaceRecognizer;
import org.bytedeco.javacpp.opencv_face.LBPHFaceRecognizer;
import org.bytedeco.javacpp.opencv_objdetect.CascadeClassifier;
import org.bytedeco.javacv.CanvasFrame;
import org.bytedeco.javacv.Frame;
import org.bytedeco.javacv.FrameGrabber;
import org.bytedeco.javacv.OpenCVFrameConverter;
import org.bytedeco.javacv.OpenCVFrameGrabber;

import com.eduardo.dao.PessoasDao;
import com.eduardo.model.Pessoas;

public class ReconhecimentoLBPH {
	private static List<Pessoas> listPessoas;
	private static PessoasDao pdao = new PessoasDao();
	static int i = 1;

	public static void iniciar() throws FrameGrabber.Exception, InterruptedException {
		JOptionPane.showMessageDialog(null, "Renhonecimento LBPH aberto");
		OpenCVFrameConverter.ToMat converteMat = new OpenCVFrameConverter.ToMat();
		OpenCVFrameGrabber camera = new OpenCVFrameGrabber(0);
		ArrayList<String> pessoas = new ArrayList<>();
		listPessoas = pdao.listar();
		pessoas.add("");
		for (Pessoas p : listPessoas) {

			pessoas.add(p.getNome());
		}

		camera.start();

		CascadeClassifier detectorFace = new CascadeClassifier("src\\recursos\\haarcascade-frontalface-alt.xml");
		FaceRecognizer reconhecedor = LBPHFaceRecognizer.create();
		reconhecedor.read("src\\recursos\\classificadorLBPH.yml");

		reconhecedor.setThreshold(7000);
		CanvasFrame cFrame = new CanvasFrame("Reconhecimento", CanvasFrame.getDefaultGamma() / camera.getGamma());
		cFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		Frame frameCapturado = null;
		opencv_core.Mat imagemColorida = new Mat();

		while ((frameCapturado = camera.grab()) != null) {
			imagemColorida = converteMat.convert(frameCapturado);
			Mat imagemCinza = new Mat();
			cvtColor(imagemColorida, imagemCinza, COLOR_BGRA2GRAY);
			RectVector facesDetectadas = new RectVector();
			detectorFace.detectMultiScale(imagemCinza, facesDetectadas, 1.1, 6, 0, new Size(150, 150),
					new Size(500, 500));

			for (int i = 0; i < facesDetectadas.size(); i++) {
				Rect dadosFace = facesDetectadas.get(0);
				rectangle(imagemColorida, dadosFace, new Scalar(0, 0, 255, 0));
				Mat faceCapturada = new Mat(imagemCinza, dadosFace);
				resize(faceCapturada, faceCapturada, new Size(160, 160));
				IntPointer rotulo = new IntPointer(1);
				DoublePointer confianca = new DoublePointer(1);
				reconhecedor.predict(faceCapturada, rotulo, confianca);
				int predicao = rotulo.get(0);
				String nome;
				if (predicao == -1) {
					nome = "Desconhecido";
				} else {
					nome = pessoas.get(predicao) + " - " + confianca.get(0);
				}
				int x = Math.max(dadosFace.tl().x() - 10, 0);
				int y = Math.max(dadosFace.tl().y() - 10, 0);
				putText(imagemColorida, nome, new Point(x, y), FONT_HERSHEY_PLAIN, 1.4, new Scalar(0, 255, 0, 0));
			}
			if (cFrame.isVisible()) {
				cFrame.showImage(frameCapturado);
			}

		}

		cFrame.dispose();
		camera.stop();
	}
}
