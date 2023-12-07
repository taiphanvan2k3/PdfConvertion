package model.BO;

import java.util.ArrayList;

import com.spire.doc.Document;
import com.spire.doc.FileFormat;
import com.spire.doc.Section;

public class CombineDocx {
	public static void main(String[] args) {
		// Create word document and the file form disk
		Document document = new Document();
		document.loadFromFile("./output/Math4_Optimizations_4_part_1.docx", FileFormat.Docx);

		// Create word document and the file form disk
		Document documentMerge = new Document();
		documentMerge.loadFromFile("./output/Math4_Optimizations_4_part_2.docx", FileFormat.Docx);

		// Merge files
		for (Object sectionObj : documentMerge.getSections()) {
			Section section = (Section) sectionObj;
			document.getSections().add(section.deepClone());
		}

		// Save as docx file.
		document.saveToFile("output/merge.docx", FileFormat.Docx_2013);
	}

	public static void combineFiles(ArrayList<String> docFilePaths) {
		Document firstDocument = new Document();
		firstDocument.loadFromFile(docFilePaths.get(0), FileFormat.Docx);

		for (int i = 1; i < docFilePaths.size(); i++) {
			// Create word document and the file form disk
			Document documentMerge = new Document();
			documentMerge.loadFromFile(docFilePaths.get(i), FileFormat.Docx);

			// Merge files
			for (Object sectionObj : documentMerge.getSections()) {
				Section section = (Section) sectionObj;
				firstDocument.getSections().add(section.deepClone());
			}
		}

		// Save as docx file.
		firstDocument.saveToFile("output/merge.docx", FileFormat.Docx);
	}
}
