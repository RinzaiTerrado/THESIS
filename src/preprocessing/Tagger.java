package preprocessing;

import java.io.File;
import java.io.IOException;
import java.security.NoSuchAlgorithmException;

public class Tagger {
    public Tagger(String filename, String uniqueID) throws IOException, NoSuchAlgorithmException, ClassCastException, ClassNotFoundException{
        //Folder paths
        String preprocessedPath = "C:\\Users\\John Gabriel Legaspi\\IdeaProjects\\ontology\\Documents\\Preprocessed";
        String taggedPath = "C:\\Users\\John Gabriel Legaspi\\IdeaProjects\\ontology\\Documents\\Tagged";
        String taggedBSPath = "C:\\Users\\John Gabriel Legaspi\\IdeaProjects\\ontology\\Documents\\TaggedBootstrap";

        //Resource paths
        String genusTxtFile = "C:\\Users\\John Gabriel Legaspi\\IdeaProjects\\ontology\\Resources\\genus.txt";
        String familyTxtFile = "C:\\Users\\John Gabriel Legaspi\\IdeaProjects\\ontology\\Resources\\family.txt";

        String twentyKTxtFile = "C:\\Users\\John Gabriel Legaspi\\IdeaProjects\\ontology\\Resources\\google-20k.txt";

        String englishTaggerFile = "C:\\Users\\John Gabriel Legaspi\\IdeaProjects\\ontology\\Resources\\english-left3words-distsim.tagger";
        String serializedClassifier = "C:\\Users\\John Gabriel Legaspi\\IdeaProjects\\ontology\\Resources\\english.all.3class.distsim.crf.ser.gz";

        String text = new PDFReader(filename).getText();
        String cleanTxt = new TextCleaner(text).cleanText().getCleanText();
        String splitTxt = new SentenceSplitter(cleanTxt).getSplitText();

        java.io.FileWriter fw = new java.io.FileWriter(preprocessedPath + uniqueID + " txt.txt");
        fw.write(text);
        fw.close();

        java.io.FileWriter fw1 = new java.io.FileWriter(preprocessedPath + uniqueID + " clean.txt");
        fw1.write(cleanTxt);
        fw1.close();

        java.io.FileWriter fw2 = new java.io.FileWriter(preprocessedPath + uniqueID + " ssplit.txt");
        fw2.write(splitTxt);
        fw2.close();

        splitTxt = new SpeciesTagger("Synonym", splitTxt, genusTxtFile, twentyKTxtFile).run();

        splitTxt = new CommonNameTagger("MarineOrganism", splitTxt, englishTaggerFile).run();
        splitTxt = new LocationTagger("Location", splitTxt, serializedClassifier).run();

        splitTxt = new Coref(splitTxt).run();

        String notag = splitTxt.replaceAll("<\\/?\\w+>", "");

        java.io.FileWriter fw3 = new java.io.FileWriter(preprocessedPath + uniqueID + " notag.txt");
        fw3.write(notag);
        fw3.close();

        java.io.FileWriter fw4 = new java.io.FileWriter(taggedPath + uniqueID + ".xml");
        fw4.write(splitTxt);
        fw4.close();

        java.io.FileWriter fw5 = new java.io.FileWriter(taggedBSPath + uniqueID + ".xml");
        fw5.write(splitTxt);
        fw5.close();
    }
}
