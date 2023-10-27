package com.file.Service;

//
import com.lowagie.text.*;
import com.lowagie.text.pdf.PdfWriter;
//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;
//import org.springframework.stereotype.Service;
//
//import java.io.ByteArrayInputStream;
//import java.io.ByteArrayOutputStream;
//@Service
//public class PdfService {
//
//    private Logger logger= LoggerFactory.getLogger(PdfService.class);
//    public  ByteArrayInputStream  createPdf() {
//        logger.info("Create PDF Started");
//        String title = "Welcome to Learning Java Programming";
//        String content = "Java is basically used for as Backend Development. We can build Production ready applications with Java.";
//
//        ByteArrayOutputStream out = new ByteArrayOutputStream();
//        Document document = new Document();
//        PdfWriter.getInstance(document, out);
//        document.open();
//        Font titleFont= FontFactory.getFont(FontFactory.HELVETICA_BOLD,25);
//
//        Paragraph paragraph= new Paragraph(title,titleFont);
//        paragraph.setAlignment(Element.ALIGN_CENTER);
//        document.add(paragraph);
//
//        Font paraFont=FontFactory.getFont(FontFactory.HELVETICA,18);
//        Paragraph paragraph1=new Paragraph(content);
//        paragraph1.add(new Chunk("This line is added after creating paragraph"));
//        document.add(paragraph1);
//
//        document.close();
//
//        return  new ByteArrayInputStream(out.toByteArray());
//    }
//
//}
//

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.xhtmlrenderer.pdf.ITextRenderer;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;

@Service
public class PdfService {

	private Logger logger = LoggerFactory.getLogger(PdfService.class);

	public ByteArrayInputStream generateResume() throws IOException, com.lowagie.text.DocumentException {
		logger.info("Generate Resume Started");

		ByteArrayOutputStream out = new ByteArrayOutputStream();

		// Load your resume content from an HTML file or string
		String resumeHtml = "<html><head></head>" +
		        "<body bgcolor='orange'>\n" +
		        "    <table width='100%'>\n" +
		        "        <tr>\n" +
		        "            <td align='center'>\n" +
		        "                <img src='src/main/resources/static/profile-removebg-preview.png' width='130' height='130' />\n" +
		        "            </td>\n" +
		        "        </tr>\n" +
		        "    </table>" +
		        "    <table width='100%'>\n" +
		        "        <tr>\n" +
		        "            <td align='center'>\n" +
		        "    <h1 align='center'>John Doe</h1>" +
		        "    <p>Email: john.doe@example.com | Phone: (123) 456-7890</p>\n" +
		        "            </td>\n" +
		        "        </tr>\n" +
		        "    </table>\n" +
		        "    <!-- Add Personal Information -->\n" +
		        "    <table width='100%'>\n" +
		        "        <tr>\n" +
		        "            <td><h2>Personal Information</h2></td>\n" +
		        "        </tr>\n" +
		        "    </table>\n" +
		        "    <table width='100%'>\n" +
		        "        <tr>\n" +
		        "            <td>\n" +
		        "                <p>Date of Birth: January 1, 19XX<br/>\n" +
		        "                Address: 123 Main Street, City, State, Zip Code<br/>\n" +
		        "                LinkedIn: <a href=\"https://www.linkedin.com/in/johndoe\">https://www.linkedin.com/in/johndoe</a></p>\n" +
		        "            </td>\n" +
		        "        </tr>\n" +
		        "    </table>\n" +
		        "    <!-- Add Skills -->\n" +
		        "    <table width='100%'>\n" +
		        "        <tr>\n" +
		        "            <td><h2>Skills</h2></td>\n" +
		        "        </tr>\n" +
		        "    </table>\n" +
		        "    <table width='100%'>\n" +
		        "        <tr>\n" +
		        "            <td>\n" +
		        "                <p> •  Java Programming<br/>\n" +
		        "                •   Web Development<br/>\n" +
		        "                •   Problem Solving<br/>\n" +
		        "                 •  Team Collaboration</p>\n" +
		        "            </td>\n" +
		        "        </tr>\n" +
		        "    </table>\n" +
		        "    <table width='100%'>\n" +
		        "        <tr>\n" +
		        "            <td><h2>Education</h2></td>\n" +
		        "        </tr>\n" +
		        "    </table>\n" +
		        "    <table width='100%'>\n" +
		        "        <tr>\n" +
		        "            <td>\n" +
		        "                <p> •  Bachelor of Science in Computer Science<br/>\n" +
		        "                •  University of Example<br/>\n" +
		        "                •  Graduated: May 20XX</p>\n" +
		        "            </td>\n" +
		        "        </tr>\n" +
		        "    </table>\n" +
		        "\n" +
		        "    <table width='100%'>\n" +
		        "        <tr>\n" +
		        "            <td><h2>Work Experience</h2></td>\n" +
		        "        </tr>\n" +
		        "    </table>\n" +
		        "    <table width='100%'>\n" +
		        "        <tr>\n" +
		        "            <td>\n" +
		        "                <p>Software Developer<br/>\n" +
		        "                Example Company<br/>\n" +
		        "                May 20XX - Present<br/>\n" +
		        "                • Developed and maintained web applications.<br/>\n" +
		        "                • Collaborated with cross-functional teams on various projects.</p>\n" +
		        "            </td>\n" +
		        "        </tr>\n" +
		        "    </table>\n" +
		        "\n" +
		        "</body>\n" +
		        "</html>";



		// Create an ITextRenderer instance
		ITextRenderer renderer = new ITextRenderer();
		renderer.setDocumentFromString(resumeHtml);
		renderer.layout();

		// Render the PDF
		renderer.createPDF(out);

		return new ByteArrayInputStream(out.toByteArray());
	}

	public static void main(String[] args) {
		PdfService resumeGenerator = new PdfService();
		try {
			ByteArrayInputStream inputStream = resumeGenerator.generateResume();
			// Now, you can do something with the inputStream, like save it to a file or
			// send it as a response in a web application.
		} catch (IOException | com.lowagie.text.DocumentException e) {
			e.printStackTrace();
		}
	}
}
