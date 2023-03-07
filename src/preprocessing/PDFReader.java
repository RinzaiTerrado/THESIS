package preprocessing;

import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.text.PDFTextStripper;
import org.apache.pdfbox.text.PDFTextStripperByArea;

import java.io.File;

public class PDFReader {
    private String text;

    public PDFReader(String filename){
        System.out.println("Converting PDF to TEXT...");
        long startTime, endTime;
        startTime = System.nanoTime ();

        text = "";

        try{
            PDDocument document = null;
            document = PDDocument.load(new File(filename));
            document.getClass();
            if(!document.isEncrypted()){
                PDFTextStripperByArea stripper = new PDFTextStripperByArea();
                stripper.setSortByPosition(true);
                PDFTextStripper Tstripper = new PDFTextStripper();
                text = Tstripper.getText(document);
            }
            document.close();
        }catch(Exception e){
            e.printStackTrace();
        }

        endTime = System.nanoTime ();
        System.err.println("[PDF to TXT Converter] Duration: "+ ((double)(endTime - startTime)) / 1000000 + " ms");
    }

    public String getText(){
        return text;
    }
}
