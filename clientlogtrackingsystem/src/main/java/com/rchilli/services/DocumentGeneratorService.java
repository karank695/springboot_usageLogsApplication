package com.rchilli.services;

import java.io.FileOutputStream;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import com.itextpdf.html2pdf.HtmlConverter;
import com.itextpdf.io.source.ByteArrayOutputStream;
import com.itextpdf.kernel.pdf.PdfWriter;

@Service
public class DocumentGeneratorService {

	private static final Logger logger = LoggerFactory.getLogger(DocumentGeneratorService.class);

	public String htmlToPdf(String processedHtml, String nameOfFile) {
		ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
		try {
			PdfWriter pdfWriter = new PdfWriter(byteArrayOutputStream);

			// Convert processed HTML to PDF using iText
			HtmlConverter.convertToPdf(processedHtml, pdfWriter, null);

			// Write the PDF content to a file
			FileOutputStream fout = new FileOutputStream(
					"C:\\Users\\karan\\Pictures\\pdfSpringBoot\\" + nameOfFile + ".pdf");
			byteArrayOutputStream.writeTo(fout);

			// Close and flush the output streams
			byteArrayOutputStream.close();
			byteArrayOutputStream.flush();
			fout.close();

			// Logging success message
			logger.info("HTML converted to PDF successfully");

			return null;
		} catch (Exception e) {
			// Logging error message
			logger.error("Error converting HTML to PDF", e);
		}

		return null;
	}
}
