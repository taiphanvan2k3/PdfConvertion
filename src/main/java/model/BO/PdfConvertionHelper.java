package model.BO;

import java.io.File;
import java.util.ArrayList;
import java.util.Collections;

import org.apache.pdfbox.pdmodel.PDDocument;

import com.spire.pdf.FileFormat;
import com.spire.pdf.PdfDocument;

public class PdfConvertionHelper {
	private static final String OUTPUT_FOLDER = "output";
	private static final int MAX_PAGES_PER_FILE = 10;

	private void convertPdfToDoc(String fileInput, String fileOutput) {
		Thread t = new Thread(() -> {
			ArrayList<String> pathOfChunkFiles = this.splitPdf(fileInput);
			ArrayList<String> fileDocxPaths = this.convertChunkPdfToDocx(pathOfChunkFiles);
			Collections.sort(fileDocxPaths);
			CombineDocx.combineFiles(fileDocxPaths, OUTPUT_FOLDER + "/" + fileOutput);
		});
		t.start();
	}

	/**
	 * Convert file pdf thành các file pdf nhỏ hơn mà mỗi file chứa tối đa 10 trang
	 * 
	 * @param filePath Đường dẫn file đầu vào
	 * @return ArrayList<String>: đường dẫn của các file pdf được chunk ra
	 */
	private ArrayList<String> splitPdf(String filePath) {
		ArrayList<String> pathOfChunkFiles = new ArrayList<>();
		try {
			String fileNameDontHaveExtension = filePath.replace(".pdf", "").replaceAll(" ", "");
			PDDocument document = PDDocument.load(new File(filePath));
			int totalPages = document.getNumberOfPages();
			int fileIndex = 1;

			for (int start = 0; start < totalPages; start += MAX_PAGES_PER_FILE) {
				int end = Math.min(fileIndex * MAX_PAGES_PER_FILE, totalPages);

				PDDocument chunkDocument = new PDDocument();
				for (int page = start; page < end; page++) {
					chunkDocument.addPage(document.getPage(page));
				}

				String outputPdf = OUTPUT_FOLDER + "/" + fileNameDontHaveExtension + "_part_" + fileIndex + ".pdf";
				pathOfChunkFiles.add(outputPdf);
				chunkDocument.save(outputPdf);
				chunkDocument.close();
				fileIndex++;
			}
			document.close();
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
		return pathOfChunkFiles;
	}

	private ArrayList<String> convertChunkPdfToDocx(ArrayList<String> chunkFiles) {
		ArrayList<String> docFilePaths = new ArrayList<>();
		try {
			ArrayList<ConvertDocxThread> threads = new ArrayList<>();

			for (String filePath : chunkFiles) {
				ConvertDocxThread thread = new ConvertDocxThread(docFilePaths, filePath);
				threads.add(thread);
				thread.start();
			}

			for (ConvertDocxThread thread : threads) {
				thread.join();
			}
			System.out.println("Done");
		} catch (Exception e) {
		}
		return docFilePaths;
	}

	public static void main(String[] args) {
		PdfConvertionHelper helper = new PdfConvertionHelper();
		helper.convertPdfToDoc("GK_DTDM.pdf", "GK_DTDM.docx");
		helper.convertPdfToDoc("Math 4 _Optimizations_4.pdf", "Math 4 _Optimizations_4.docx");
	}
}

class ConvertDocxThread extends Thread {
	private ArrayList<String> docFilePaths;
	private String filePath;

	public ConvertDocxThread(ArrayList<String> docFilePaths, String filePath) {
		this.filePath = filePath;
		this.docFilePaths = docFilePaths;
	}

	private void convert(String filePath) {
		try {
			System.out.println(filePath + " start");
			PdfDocument document = new PdfDocument();
			document.loadFromFile(filePath);
			String docFilePath = filePath.replace(".pdf", ".docx");
			document.saveToFile(docFilePath, FileFormat.DOCX);
			document.close();
			this.docFilePaths.add(docFilePath);
			System.out.println(filePath + " end");
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
	}

	@Override
	public void run() {
		this.convert(filePath);
	}
}
