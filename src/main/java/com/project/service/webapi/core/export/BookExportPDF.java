package com.project.service.webapi.core.export;

import java.awt.Color;
import java.io.IOException;
import java.util.List;

import com.lowagie.text.*;
import com.lowagie.text.pdf.*;

import jakarta.servlet.http.HttpServletResponse;

import com.project.service.webapi.domain.entities.Book;

public class BookExportPDF {
    private List<Book> listBooks;

    public BookExportPDF(List<Book> listBooks) {
        this.listBooks = listBooks;
    }

    private void writeTableHeader(PdfPTable table) {
        PdfPCell cell = new PdfPCell();
        cell.setBackgroundColor(Color.WHITE);
        cell.setPadding(5);

        Font font = FontFactory.getFont(FontFactory.HELVETICA);
        font.setColor(Color.WHITE);

        cell.setPhrase(new Phrase("ID", font));
        table.addCell(cell);

        cell.setPhrase(new Phrase("Title", font));
        table.addCell(cell);

        cell.setPhrase(new Phrase("Chapters", font));
        table.addCell(cell);

        cell.setPhrase(new Phrase("Pages", font));
        table.addCell(cell);
    }

    private void writeTableData(PdfPTable table) {
        for (Book book : listBooks) {
            table.addCell(String.valueOf(book.getBookId()));
            table.addCell(book.getTitle());
            table.addCell(String.valueOf(book.getChapters().size()));
            table.addCell(String.valueOf(
                book.getChapters().stream().mapToInt(
                    item -> item.getPages().size()
                ).sum()
            ));
        }
    }

    public void export(HttpServletResponse response) throws DocumentException, IOException {
        Document document = new Document(PageSize.A4);
        PdfWriter.getInstance(document, response.getOutputStream());

        document.open();
        Font font = FontFactory.getFont(FontFactory.HELVETICA_BOLD);
        font.setSize(18);
        font.setColor(Color.DARK_GRAY);

        Paragraph p = new Paragraph("List of Books", font);
        p.setAlignment(Paragraph.ALIGN_CENTER);

        document.add(p);

        PdfPTable table = new PdfPTable(5);
        table.setWidthPercentage(100f);
        table.setWidths(new float[] {1.5f, 3.5f, 3.0f, 3.0f, 1.5f});
        table.setSpacingBefore(10);

        writeTableHeader(table);
        writeTableData(table);

        document.add(table);

        document.close();
    }
}