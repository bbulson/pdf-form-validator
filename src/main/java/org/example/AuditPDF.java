package org.example;

import org.apache.pdfbox.Loader;
import org.apache.pdfbox.cos.COSDictionary;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.PDPage;
import org.apache.pdfbox.pdmodel.PDPageContentStream;
import org.apache.pdfbox.pdmodel.common.PDRectangle;
import org.apache.pdfbox.pdmodel.font.PDType1Font;
import org.apache.pdfbox.pdmodel.font.Standard14Fonts;
import org.apache.pdfbox.pdmodel.interactive.annotation.PDAnnotationText;
import org.apache.pdfbox.pdmodel.interactive.annotation.PDAnnotationWidget;
import org.apache.pdfbox.pdmodel.interactive.form.*;

import java.io.File;
import java.io.IOException;
import java.util.List;

public class AuditPDF {
    public static void main(String[] args) throws IOException {
        // 1. Check if a path was actually provided in the command line
        if (args.length == 0) {
            System.out.println("Error: No input file path provided.");
            System.out.println("Usage: java -jar app.jar <path-to-pdf>");
            return;
        }

        // 2. Capture the path from the first argument (args[0])
        String inputPath = args[0];
        File input = new File(inputPath);

        // 3. Validate that the file exists before processing
        if (!input.exists()) {
            System.out.println("Error: File not found at " + input.getAbsolutePath());
            return;
        }

        System.out.println("Processing file: " + input.getName());
        String outputName = "demo-files/audit-" + input.getName();
        File output = new File(outputName);
        PDDocument doc = Loader.loadPDF(input);
        PDAcroForm form = doc.getDocumentCatalog().getAcroForm();

        if (form == null) {
            System.out.println("No AcroForm found.");
            doc.close();
            return;
        }

        form.setNeedAppearances(true);

        /* --------------------------------------------------
           STEP 1 — Insert test values
        -------------------------------------------------- */
        int index = 0;
        for (PDField field : form.getFieldTree()) {
            index+=1;
            form.setNeedAppearances(true);
            try {

                if (field instanceof PDTextField) {
                    field.setValue("TEST"+index);
                }

                else if (field instanceof PDCheckBox) {
                    PDCheckBox cb = (PDCheckBox) field;
                    cb.getCOSObject().setName("V", "Yes");
                }

                else if (field instanceof PDRadioButton) {
                    PDRadioButton radio = (PDRadioButton) field;
                    List<String> values = radio.getExportValues();
                    if (!values.isEmpty()) {
                        String value = values.get(0);
                        // Set field value
                        radio.setValue(value);
                        // Force widget appearance
                        for (PDAnnotationWidget widget : radio.getWidgets()) {
                            COSDictionary dict = widget.getCOSObject();
                            // selected widget
                            if (widget == radio.getWidgets().get(0)) {
                                dict.setName("AS", value);
                            }
                            else {
                                dict.setName("AS", "Off");
                            }
                        }
                    }
                    // Select the first button
                    String name = radio.getFullyQualifiedName();
                    radio.setValue(radio.getValue());
                }

            } catch (Exception ignored) {
                System.out.println(ignored);
            }
            if (field instanceof PDCheckBox) {
                System.out.println(field.getFullyQualifiedName() + " ON value = " + ((PDCheckBox) field).getValue());
            }

            if (field instanceof PDRadioButton) {
                System.out.println(field.getFullyQualifiedName() + " values = " + ((PDRadioButton) field).getExportValues());
            }
        }
        /* --------------------------------------------------
           STEP 2 — Detect duplicate widgets
        -------------------------------------------------- */

        for (PDField field : form.getFieldTree()) {

            String fieldName = field.getFullyQualifiedName();

            if ("rating".equals(fieldName))
                continue;

            List<PDAnnotationWidget> widgets = field.getWidgets();

            if (widgets == null || widgets.size() <= 1)
                continue;

            PDAnnotationWidget firstWidget = widgets.get(0);
            PDPage page = firstWidget.getPage();

            PDRectangle firstRect = firstWidget.getRectangle();

            float labelX = firstRect.getUpperRightX() + 35;
            float labelY = firstRect.getUpperRightY() + 2;

            PDPageContentStream cs =
                    new PDPageContentStream(doc, page,
                            PDPageContentStream.AppendMode.APPEND,
                            true);

            /* draw label text */

            cs.beginText();
            PDType1Font font = new PDType1Font(Standard14Fonts.FontName.HELVETICA_BOLD);
            cs.setFont(font, 8);
            cs.newLineAtOffset(labelX, labelY);
            cs.showText("Duplicate Field: " + fieldName);
            cs.endText();
            PDAnnotationText comment = new PDAnnotationText();
            comment.setContents("Duplicate Field: " + fieldName);
            comment.setTitlePopup("Duplicate Audit");

            PDRectangle commentRect = new PDRectangle();
            commentRect.setLowerLeftX(labelX-20);
            commentRect.setLowerLeftY(labelY);
            commentRect.setUpperRightX(labelX + 0);
            commentRect.setUpperRightY(labelY + 20);

            comment.setRectangle(commentRect);
            comment.setName(PDAnnotationText.NAME_COMMENT);

            page.getAnnotations().add(comment);

            float startX = labelX;
            float startY = labelY;

            for (PDAnnotationWidget widget : widgets) {

                PDRectangle rect = widget.getRectangle();

                float centerX = rect.getLowerLeftX() + rect.getWidth() / 2;
                float centerY = rect.getLowerLeftY() + rect.getHeight() / 2;

                /* draw highlight box */

                cs.addRect(
                        rect.getLowerLeftX(),
                        rect.getLowerLeftY(),
                        rect.getWidth(),
                        rect.getHeight()
                );

                cs.stroke();

                /* draw line from label to widget */

                cs.moveTo(startX, startY);
                cs.lineTo(centerX, centerY);
                cs.stroke();
            }

            cs.close();
        }

        doc.save(output);
        doc.close();

        System.out.println("Audit PDF complete → " + output.getAbsolutePath());
    }

}