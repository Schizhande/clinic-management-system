package com.myclinic.part2project.reports;

import java.net.URL;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.jfree.chart.title.Title;

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
import com.myclinic.part2project.model.Patient;
import com.myclinic.part2project.model.Staff;

public class StaffReportBuilder extends AbstractITextPdfView {

	@Override
	protected void buildPdfDocument(Map<String, Object> model, Document document, PdfWriter writer,
			HttpServletRequest request, HttpServletResponse response) throws Exception {
		List<Staff> listStaffs = (List<Staff>) model.get("listStaffs");
		URL imageUrl = getClass().getResource("/image/Taku.png");
		Image logo = Image.getInstance(imageUrl);
		logo.scaleAbsolute(100f, 100f);
		
		document.add(logo);
		document.add(new Paragraph(
				new Chunk("STAFF REPORT", FontFactory.getFont(FontFactory.HELVETICA, 16))));
		document.add(new Paragraph("Staff reistered in the system"));
		PdfPTable table = new PdfPTable(11);
		table.setWidthPercentage(100.0f);
		table.setWidths(new float[] { 1.2f, 2.0f, 2.0f, 1.5f, 1.5f, 2.5f, 2.0f, 2.0f, 2.0f, 2.0f, 2.0f ,2.0f});
		table.setSpacingBefore(5);
		// define font for table header row
		Font font = FontFactory.getFont(FontFactory.TIMES_ROMAN);
		font.setColor(BaseColor.WHITE);
		font.setSize(6);

		// define table header cell
		PdfPCell cell = new PdfPCell();
		cell.setBackgroundColor(BaseColor.BLUE);

		// write table header
		cell.setPhrase(new Phrase("STAFF ID", font));
		table.addCell(cell);

		cell.setPhrase(new Phrase("FIRST NAME", font));
		table.addCell(cell);

		cell.setPhrase(new Phrase("LAST NAME", font));
		table.addCell(cell);

		cell.setPhrase(new Phrase("GENDER", font));
		table.addCell(cell);

		cell.setPhrase(new Phrase("MARITAL STATUS", font));
		table.addCell(cell);

		cell.setPhrase(new Phrase("EMAIL", font));
		table.addCell(cell);

		cell.setPhrase(new Phrase("MOBILE", font));
		table.addCell(cell);

		cell.setPhrase(new Phrase("DATE OF BIRTH", font));
		table.addCell(cell);

		cell.setPhrase(new Phrase("JOINED DATE", font));
		table.addCell(cell);

		cell.setPhrase(new Phrase("NATIONAL ID", font));
		table.addCell(cell);

		cell.setPhrase(new Phrase("ADDRESS", font));
		table.addCell(cell);

		// write table row data
		for (Staff staff : listStaffs) {
			table.addCell(
					new Phrase(String.valueOf(staff.getStaffID()), FontFactory.getFont(FontFactory.HELVETICA, 8)));
			table.addCell(new Phrase(staff.getLastName(), FontFactory.getFont(FontFactory.HELVETICA, 8)));
			table.addCell(new Phrase(staff.getFirstName(), FontFactory.getFont(FontFactory.HELVETICA, 8)));
			table.addCell(
					new Phrase(staff.getGender().getGenderType(), FontFactory.getFont(FontFactory.HELVETICA, 8)));
			table.addCell(
					new Phrase(staff.getMaritalStatus().getMarital(), FontFactory.getFont(FontFactory.HELVETICA, 8)));
			table.addCell(new Phrase(staff.getEmail(), FontFactory.getFont(FontFactory.HELVETICA, 8)));
			table.addCell(new Phrase(staff.getMobile(), FontFactory.getFont(FontFactory.HELVETICA, 8)));
			table.addCell(new Phrase(staff.getJobTitle().getTitle(), FontFactory.getFont(FontFactory.HELVETICA, 8)));
			table.addCell(new Phrase(staff.getDob().toString(), FontFactory.getFont(FontFactory.HELVETICA, 8)));
			table.addCell(
					new Phrase(staff.getJoinedDate().toString(), FontFactory.getFont(FontFactory.HELVETICA, 8)));
			table.addCell(new Phrase(staff.getNationalID(), FontFactory.getFont(FontFactory.HELVETICA, 8)));
			table.addCell(new Phrase(staff.getAddress(), FontFactory.getFont(FontFactory.HELVETICA, 8)));

		}

		document.add(table);

	}

}
