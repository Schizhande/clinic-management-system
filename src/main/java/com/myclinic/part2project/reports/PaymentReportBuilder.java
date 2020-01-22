package com.myclinic.part2project.reports;

import java.net.URL;
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
import com.myclinic.part2project.model.Payment;

public class PaymentReportBuilder extends AbstractITextPdfView {

	@Override
	protected void buildPdfDocument(Map<String, Object> model, Document document, PdfWriter writer,
			HttpServletRequest request, HttpServletResponse response) throws Exception {
		// get data model which is passed by the Spring container
		List<Payment> listPayments = (List<Payment>) model.get("listPayments");
		
		URL imageUrl = getClass().getResource("/image/Taku.png");
		Image logo = Image.getInstance(imageUrl);
		logo.scaleAbsolute(100f, 100f);
		
		document.add(logo);

		document.add(new Paragraph(new Chunk("PAYMENT REPORT", FontFactory.getFont(FontFactory.HELVETICA, 16))));
		document.add(new Paragraph("Payments made by patients"));
		PdfPTable table = new PdfPTable(5);
		table.setWidthPercentage(100.0f);
		table.setWidths(new float[] { 2.0f, 2.0f, 2.0f, 2.0f, 2.0f });
		table.setSpacingBefore(5);
		// define font for table header row
		Font font = FontFactory.getFont(FontFactory.TIMES_ROMAN);
		font.setColor(BaseColor.WHITE);
		font.setSize(6);

		// define table header cell
		PdfPCell cell = new PdfPCell();
		cell.setBackgroundColor(BaseColor.BLUE);

		// write table header
		cell.setPhrase(new Phrase("PAYMENT ID", font));
		table.addCell(cell);

		cell.setPhrase(new Phrase("AMOUNT", font));
		table.addCell(cell);

		cell.setPhrase(new Phrase("PAYMENT DATE", font));
		table.addCell(cell);

		cell.setPhrase(new Phrase("PAYMENT METHODS", font));
		table.addCell(cell);

		cell.setPhrase(new Phrase("PAYMENT PURPOSE", font));
		table.addCell(cell);

		// write table row data
		for (Payment payment : listPayments) {
			table.addCell(
					new Phrase(String.valueOf(payment.getPaymentID()), FontFactory.getFont(FontFactory.HELVETICA, 8)));
			table.addCell(
					new Phrase(String.valueOf(payment.getAmount()), FontFactory.getFont(FontFactory.HELVETICA, 8)));
			table.addCell(
					new Phrase(payment.getPaymentDate().toString(), FontFactory.getFont(FontFactory.HELVETICA, 8)));
			table.addCell(
					new Phrase(payment.getPaymentMethod().getMethod(), FontFactory.getFont(FontFactory.HELVETICA, 8)));
			table.addCell(new Phrase(payment.getPaymentPurpose().getPurpose(),
					FontFactory.getFont(FontFactory.HELVETICA, 8)));
		}

		document.add(table);

	}

}
