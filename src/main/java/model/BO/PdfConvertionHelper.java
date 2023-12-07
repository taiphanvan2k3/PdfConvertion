package model.BO;

import java.io.File;
import java.util.ArrayList;
import java.util.Collections;

import org.apache.pdfbox.pdmodel.PDDocument;

import com.spire.pdf.FileFormat;
import com.spire.pdf.PdfDocument;

public class PdfConvertionHelper {
	private static final int MAX_PAGES_PER_FILE = 10;

	private void splitPdf(String filePath) {
		try {
			String fileNameDontHaveExtension = filePath.replace(".pdf", "").replaceAll(" ", "");
			String outputFolder = "output";
			PDDocument document = PDDocument.load(new File(filePath));
			int totalPages = document.getNumberOfPages();
			int fileIndex = 1;

			ArrayList<String> pathOfChunkFiles = new ArrayList<>();
			for (int start = 0; start < totalPages; start += MAX_PAGES_PER_FILE) {
				int end = Math.min(fileIndex * MAX_PAGES_PER_FILE, totalPages);

				PDDocument chunkDocument = new PDDocument();
				for (int page = start; page < end; page++) {
					chunkDocument.addPage(document.getPage(page));
				}

				String outputPdf = outputFolder + "/" + fileNameDontHaveExtension + "_part_" + fileIndex + ".pdf";
				pathOfChunkFiles.add(outputPdf);
				chunkDocument.save(outputPdf);
				chunkDocument.close();
				fileIndex++;
			}
			document.close();
			this.combineChunkFiles(pathOfChunkFiles);
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
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

	private void combineChunkFiles(ArrayList<String> chunkFiles) {
		ArrayList<String> fileDocxPaths = this.convertChunkPdfToDocx(chunkFiles);
		Collections.sort(fileDocxPaths);
		CombineDocx.combineFiles(fileDocxPaths);
	}

	public static void main(String[] args) {
		(new PdfConvertionHelper()).splitPdf("GK_DTDM.pdf");
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
