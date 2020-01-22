package com.myclinic.part2project.reports;

import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.itextpdf.text.BaseColor;
import com.itextpdf.text.Chunk;
import com.itextpdf.text.Document;
import com.itextpdf.text.Font;
import com.itextpdf.text.FontFactory;
import com.itextpdf.text.Image;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import com.myclinic.part2project.model.Drug;
import com.myclinic.part2project.model.Prescription;
import com.myclinic.part2project.model.Record;

/**
 * This view class generates a PDF document 'on the fly' based on the data
 * contained in the model.
 * 
 * @author www.codejava.net
 *
 */
public class PrescriptionBuilder extends AbstractITextPdfView {

	@Override
	protected void buildPdfDocument(Map<String, Object> model, Document doc, PdfWriter writer,
			HttpServletRequest request, HttpServletResponse response) throws Exception {
		// get data model which is passed by the Spring container
		// List<Drug> drugs = (List<Drug>) model.get("prescription");
		Record record = (Record) model.get("record");
		URL imageUrl = getClass().getResource("/image/Taku.png");
		Image logo = Image.getInstance(imageUrl);
		
		logo.scaleAbsolute(100f, 100f);
		doc.add(logo);
		doc.add(new Paragraph(new Chunk("PATIENT PRESCRIPTION", FontFactory.getFont(FontFactory.HELVETICA, 16))));
		doc.add(new Paragraph("PATIENT NAME:  " + record.getPatient().getFirstName()));
		doc.add(new Paragraph("PATIENT SURNAME:  " + record.getPatient().getLastName()));
		doc.add(new Paragraph("DATE OF BIRTH:  " + record.getPatient().getDob().toString()));
		List<Drug> drugs = new ArrayList<>();
		List<Prescription> pres = record.getPrescription();
		for (Prescription pr : record.getPrescription()) {
			drugs.add(pr.getDrug());
		}

		PdfPTable table = new PdfPTable(5);
		table.setWidthPercentage(100.0f);
		table.setWidths(new float[] { 2.0f, 2.0f, 2.0f, 2.0f, 2.0f });
		table.setSpacingBefore(10);

		// define font for table header row
		Font font = FontFactory.getFont(FontFactory.HELVETICA);
		font.setColor(BaseColor.WHITE);

		// define table header cell
		PdfPCell cell = new PdfPCell();
		cell.setBackgroundColor(BaseColor.BLUE);
		cell.setPadding(5);

		// write table header
		cell.setPhrase(new Phrase("Drug Name", font));
		table.addCell(cell);

		cell.setPhrase(new Phrase("Dosage", font));
		table.addCell(cell);

		cell.setPhrase(new Phrase("Frequency", font));
		table.addCell(cell);

		cell.setPhrase(new Phrase("Druration", font));
		table.addCell(cell);

		cell.setPhrase(new Phrase("Quantity", font));
		table.addCell(cell);

		// write table row data
		for (Drug drug : drugs) {
			table.addCell(drug.getName());
			table.addCell(drug.getDosage());
			table.addCell(drug.getFrequency());
			table.addCell(String.valueOf(drug.getDuration()));
			table.addCell(String.valueOf(drug.getQuantity()));
		}

		doc.add(table);

	}

}