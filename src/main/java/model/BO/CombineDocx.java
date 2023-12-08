package model.BO;

import java.util.ArrayList;

import com.spire.doc.Document;
import com.spire.doc.FileFormat;
import com.spire.doc.Section;

public class CombineDocx {

	public static void combineFiles(ArrayList<String> docFilePaths, String output) {
		Document firstDocument = new Document();
		firstDocument.loadFromFile(docFilePaths.get(0), FileFormat.Docx);

		for (int i = 1; i < docFilePaths.size(); i++) {
			Document documentMerge = new Document();
			documentMerge.loadFromFile(docFilePaths.get(i), FileFormat.Docx);

			// Merge files
			for (Object sectionObj : documentMerge.getSections()) {
				Section section = (Section) sectionObj;
				firstDocument.getSections().add(section.deepClone());
			}
		}

		firstDocument.saveToFile(output, FileFormat.Docx);
	}
}
