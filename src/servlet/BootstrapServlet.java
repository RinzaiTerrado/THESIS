package servlet;

import com.google.common.collect.HashMultimap;
import com.google.common.collect.Multimap;
import edu.stanford.nlp.tagger.maxent.MaxentTagger;
import net.didion.jwnl.JWNL;
import net.didion.jwnl.JWNLException;
import net.didion.jwnl.data.IndexWord;
import net.didion.jwnl.data.POS;
import net.didion.jwnl.data.Synset;
import net.didion.jwnl.data.Word;
import net.didion.jwnl.dictionary.Dictionary;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import java.io.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.TreeSet;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Servlet implementation class BootstrapServlet
 */
@WebServlet("/BootstrapServlet")
public class BootstrapServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	private static final String taggedFolder = "\\Documents\\TaggedBootstrap\\"; //missing folder
	private static final String seedsPossibleFolder = "\\Documents\\SeedsPossible\\";//missing folder
	private static final String seedOutputFolder = "\\Documents\\SeedOutput\\";
	private static final String validationFolder = "\\Documents\\Validation\\";
	private static final String posLogsFolder = "\\Documents\\POSLogs\\";//missing folder
	private static final String processingTxtFile = "\\Documents\\processing.txt";//missing file
    private static final String englishTaggerFile = "\\Resources\\english-left3words-distsim.tagger";
    private static final String filePropertiesXml = "\\file_properties.xml";



    /**
	 * @see HttpServlet#HttpServlet()
	 */
	public BootstrapServlet() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
		switch (request.getServletPath()) {
		case "/BootstrapServlet":

			request.getRequestDispatcher("uploadprogress.jsp").forward(request, response);

			List<Thread> threads = (List<Thread>) request.getAttribute("threads");
			for (Thread t : threads) {
				while (t.isAlive()) {
				}
			}

			bootstrap(request, response);
			break;
		default:
			System.out.println("URL pattern doesn't match existing patterns.");
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

	private void bootstrap(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException { //bootstrapping happens here
		File Folder = new File(taggedFolder);
        File[] listFiles = Folder.listFiles();
        Reader fileReader = null;

        MaxentTagger tagger =  new MaxentTagger(englishTaggerFile); //speech tagger
        try {
            JWNL.initialize(new FileInputStream(filePropertiesXml));
        } catch (JWNLException e) {
            e.printStackTrace();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } 
        //tags
        //V = Verb, Particule, Adverb
        //W = Noun, Adjective,Adverb, Pronoun, Determiner
        //P = Prepostion(ADP), Particle
        String V = "(.*)_[VTN][BON][PNDZG]*(.*)"; //Verb --V
        String V2 = "(.*)_[V]([BP][DNGPZ]*)*(.*)"; //Verb --V
        String AV = "(.*)_[WRV][RBP][RSBG]*(.*)"; //Adverb --VW
        String W = "(.*)_[NSV][BYNP][GMPS]*(.*)"; //Noun --W
        //String W2 = "(.*)_(SYM)*(VBG)*(.*)"; //Noun --W
        String P = "(.*)_[PRT][ORP][ST]*(.*)"; //Particule --VP
        //String P2 = "(.*)_(POS)*(PRT)*(TO)*(.*)"; //Particule --VP
        String ADJ = "(.*)_[JRV][BJB][RSG]*(JR)*(.*)"; //Adjective --W
        String PN = "(.*)_[WPV][BPR][$P]*(.*)"; //Pronoun --W
        //String PN2 = "(.*)_(VBP)*(.*)"; //Pronoun --W
        String DET = "(.*)_[DEWP][TXD][T]*(.*)"; //Determiner --W
        String ADP = "(.*)_[IR][NP](.*)"; //ADPronoun --P



        long BStart = System.nanoTime();


        
        for(File xmlFile : listFiles) { //goes through listfiles, taggedfolder(tagged documents)

            TreeSet<String> POSText = new TreeSet<String>(); //POS part of speech

            File seedFolder = new File(seedsPossibleFolder); //generated seeds
            File[] seedList = seedFolder.listFiles();
            for(File seeds: seedList) { //iterates through generated seeds

                String hashxml = xmlFile.getName().replaceAll(".xml",""); //xml file name

                TreeSet<String> ValidationRelation = new TreeSet<String>();
                TreeSet<String> Verbmatches = new TreeSet<String>();
                TreeSet<String> Pronounmatches = new TreeSet<String>();
                TreeSet<String> Wordmatches = new TreeSet<String>();
                TreeSet<String> validation = new TreeSet<String>();



                String e1=null; String e2=null; //tuple values
                TreeSet<String> relation = new TreeSet<String>();
                TreeSet<String> e1Name = new TreeSet<String>();
                TreeSet<String> e2Name = new TreeSet<String>();
                TreeSet<String> list = new TreeSet<String>();

                String seedData = readFile(seeds, fileReader).toString(); //loads seedData
                String[] seedLines = seedData.split("\\r?\\n");
                ArrayList<String> seedEntity = new ArrayList<String>();
                ArrayList<String> lines = new ArrayList<String>();
                for(String sling: seedLines){
                    seedEntity.add(sling); //seedEntity contains seeds from seedData
                }

                e1 = getTag(seedEntity.get(0)); //gets entity 1 of tuple
                e2 = getTag(seedEntity.get(1)); //gets entity 2 of tuple
                File seedExists = new File(seedOutputFolder+e1+"-"+e2+".xml");
                if(seedExists.exists()){ //if seed exists in outputfolder
                    readXML(e1,e2,list);
                }


                System.out.println("Seeds retrieved: "+e1+" "+e2);

                Multimap<String,String> seedMap = HashMultimap.create(); //not sure what hashing is for
                Multimap<String,String> ValidationMap = HashMultimap.create();
                for(int i=3; i<seedEntity.size(); i++){
                    addMap(seedMap,seedEntity,i);
                }



                String xml2String = readFile(xmlFile, fileReader).toString();
                String[] line = xml2String.split("\\r?\\n");
                Collections.addAll(lines,line); //adds values of the seed from xml file to collections? not sure what for



                long startTime = System.nanoTime();
                for (String pLine : lines) {
                    pLine = pLine.toLowerCase();
                    addPreprocessing(relation,pLine,e1,e2,lines,seedMap,ValidationMap,list); //Preprocess the Tagged Documents (from documentation)

                }
                long endTime = System.nanoTime();
                long time = endTime-startTime;
                System.out.println("Time Elapsed: "+ time); //time elapsed of preprocessing 

                for(String key : seedMap.keySet()){
                    String class1 = key;
                    for(String class2 : seedMap.get(key)){
                        class1 = class1.toLowerCase();
                        class2 = class2.toLowerCase();
                        System.out.println(e1+": "+class1+";"+e2+": "+class2);
                        for (String pLine : lines) {
                            pLine = pLine.toLowerCase();
                            addRelation(relation,pLine,class1,class2,e1,e2,ValidationMap,validation);//Stores the relations found between entities to a TreeSet

                        }
                    }

                }
                //for storing seed patterns found
                TreeSet<String> seedPatternV = new TreeSet<String>();
                TreeSet<String> seedPatternW = new TreeSet<String>();
                TreeSet<String> seedPatternP = new TreeSet<String>();
                
                // POS TAGGER
                //tags
                //V = Verb, Particule, Adverb
                //W = Noun, Adjective,Adverb, Pronoun, Determiner
                //P = Prepostion(ADP), Particle
                String EntityCheck = e1+"+"+e2;
                EntityCheck = EntityCheck.toLowerCase();
                for(String rLine: relation){
                    if(EntityCheck.contains("habitat") || EntityCheck.contains("marineOrganism+location")){
                        POSTagger(seedPatternV,seedPatternW,seedPatternP,rLine,V2,P,W,tagger,AV,ADJ,PN,DET,ADP,validation,POSText);
                    }
                    else{
                        POSTagger(seedPatternV,seedPatternW,seedPatternP,rLine,V,P,W,tagger,AV,ADJ,PN,DET,ADP,validation,POSText);
                    }

                }


                TreeSet<String> Vmatches = new TreeSet<String>();

                TreeSet<String> Pmatches = new TreeSet<String>();

                TreeSet<String> Wmatches = new TreeSet<String>();


                for(String seedWordV: seedPatternV){
                    try {
                        String tagged = tagger.tagString(seedWordV);
                        String temp = tagged.substring(tagged.indexOf("_")+1);

                        IndexWord word = null;
                        if(tagged.matches(V)){
                            word = Dictionary.getInstance().lookupIndexWord(POS.VERB, seedWordV);
                            Vmatches.add(seedWordV);
                        }
                        if(word != null) {
                            Synset synset[] = word.getSenses();

                            Vmatches.add(synset[0].getWord(0).getLemma());

                        }
                    } catch (Exception ex) {
                        ex.printStackTrace();
                    }
                }
                for(String seedWordW: seedPatternW){
                    try {
                        String tagged = tagger.tagString(seedWordW);
                        String temp = tagged.substring(tagged.indexOf("_")+1);
                        IndexWord word = null;
                        if(tagged.matches(W) || tagged.matches(PN)){
                            word = Dictionary.getInstance().lookupIndexWord(POS.NOUN, seedWordW );
                            Wmatches.add(seedWordW);
                        }
                        if(word != null){
                            Synset synset[] = word.getSenses();

                            for(int i=0;i<synset.length;i++){
                                for(Word synonym : synset[i].getWords())
                                {
                                    if( tagged.matches(ADJ)  || tagged.matches(DET)){
                                        Wmatches.add(synonym.getLemma());
                                    }else if(tagged.matches(W) || tagged.matches(PN)){
                                        Wmatches.add(synonym.getLemma());
                                    }
                                }
                                if(i == 0)
                                    break;
                            }
                        }


                    } catch (Exception ex) {
                        ex.printStackTrace();
                    }
                }
                for(String seedWordP: seedPatternP){
                    try {
                        String tagged = tagger.tagString(seedWordP);
                        String temp = tagged.substring(tagged.indexOf("_")+1);
                        IndexWord word = null;
                        if(tagged.matches(P) || tagged.matches(ADP) || tagged.matches(AV)){
                            word = Dictionary.getInstance().lookupIndexWord(POS.ADVERB, seedWordP);
                            Pmatches.add(seedWordP);
                        }
                        if(word != null){
                            Synset synset[] = word.getSenses();
                            for(int i=0;i<synset.length;i++){
                                for(Word synonym : synset[i].getWords())
                                {
                                    if(tagged.matches(P) || tagged.matches(ADP) || tagged.matches(AV)){
                                        Pmatches.add(synonym.getLemma());
                                    }
                                }
                                if(i == 0)
                                    break;
                            }
                        }
                    } catch (Exception ex) {
                        ex.printStackTrace();
                    }
                }

                //finding occurences and generated seed patterns
                TreeSet<String> matches = new TreeSet<String>();
              


                matches.addAll(list);

                for(String key : ValidationMap.keySet()) {
                    String class1 = key;
                    File validationOutput; 
                    validationOutput = new File(validationFolder+hashxml+"-"+class1+"-"+e2+".xml");
                   
                    if(validationOutput.exists()){ //validated outputs
                        DocumentBuilderFactory Vfactory=  DocumentBuilderFactory.newInstance();
                        try {
                            DocumentBuilder builder =  Vfactory.newDocumentBuilder();
                            Document doc;
                            doc = builder.parse(validationFolder+hashxml+"-"+class1+"-"+e2+".xml");
                            doc.getDocumentElement().normalize();
                            NodeList nodeList = doc.getElementsByTagName("Seed"); 
                            for(int i = 0; i< nodeList.getLength();i++) {//loops through all the seeds
                                Node nNode = nodeList.item(i);
                                if (nNode.getNodeType() == Node.ELEMENT_NODE) {
                                    Element eElement = (Element) nNode;
                                    int eCount= eElement.getElementsByTagName("Pattern").getLength();
                                    int nameCount = eElement.getElementsByTagName("Name").getLength();
                                    for(int k=0; k<nameCount; k++){
                                        if((eElement.getElementsByTagName("Name").item(k).getParentNode().getNodeName().equals("Tag1"))){

                                            e1Name.add(eElement.getElementsByTagName("Name").item(k).getTextContent());
                                        }
                                        else{

                                            e2Name.add(eElement.getElementsByTagName("Name").item(k).getTextContent());
                                        }

                                    }
                                    for(String e1Value : e1Name){
                                        for(String e2Value : e2Name){
                                            ValidationMap.put(e1Value,e2Value);
                                        }
                                    }
                                    for(int j=0; j<eCount;j++){
                                        validation.add(eElement.getElementsByTagName("Pattern").item(j).getTextContent());
                                    }

                                }
                            }
                            doc.getDocumentElement().setTextContent("");
                        } catch (ParserConfigurationException | IOException e) {
                            e.printStackTrace();
                        } catch (org.xml.sax.SAXException e) {
                            e.printStackTrace();
                        }
                    }
                }

                for(String p: Vmatches){
                    p = p.replaceAll("_"," ");
                    p = p.replaceAll("(.*)-[A-Z]*-(.*)","");
                    matches.add(p);
                }
                if(!Pmatches.isEmpty()){
                    for(String p: Vmatches){
                        for(String j: Pmatches){
                            p = p.replaceAll("_"," ");
                            p = p.replaceAll("(.*)-[A-Z]*-(.*)","");
                            j = j.replaceAll("_"," ");
                            j = j.replaceAll("(.*)-[A-Z]*-(.*)","");
                            matches.add(p+" "+j);
                        }
                    }
                }
                if(!Wmatches.isEmpty()){
                    for(String i: Vmatches){
                        for(String j: Wmatches) {
                            for (String k : Pmatches) {
                                i = i.replaceAll("_"," ");
                                i = i.replaceAll("(.*)-[A-Z]*-(.*)","");
                                j = j.replaceAll("_"," ");
                                j = j.replaceAll("(.*)-[A-Z]*-(.*)","");
                                k = k.replaceAll("_"," ");
                                k = k.replaceAll("(.*)-[A-Z]*-(.*)","");
                                matches.add(i + " " + j+ " "+ k);
                            }
                        }
                    }
                }

                for(String matchesLog : matches){
                    System.out.println("generated seed pattern: " + matchesLog);
                }

                if(!matches.isEmpty()){
                    DocumentBuilderFactory documentBuilderFactory = DocumentBuilderFactory.newInstance();
                    try {
                        DocumentBuilder documentBuilder = documentBuilderFactory.newDocumentBuilder();
                        Document document = documentBuilder.newDocument();

                        Element element = document.createElement("Seed");
                        document.appendChild(element);

                        Element A = document.createElement("Tag1");
                        A.appendChild(document.createTextNode(e1));
                        element.appendChild(A);

                        Element B = document.createElement("Tag2");
                        B.appendChild(document.createTextNode(e2));
                        element.appendChild(B);

                        Element C = document.createElement("Relation");
                        element.appendChild(C);
                        for(String ms: matches) {
                            Element D = document.createElement("Pattern");
                            D.appendChild(document.createTextNode(ms));
                            C.appendChild(D);
                        }

                        TransformerFactory transformerFactory = TransformerFactory.newInstance();
                        Transformer transformer = transformerFactory.newTransformer();
                        transformer.setOutputProperty(OutputKeys.INDENT, "yes");
                        transformer.setOutputProperty("{http://xml.apache.org/xslt}indent-amount", "2");
                        DOMSource source = new DOMSource(document);

                        StreamResult streamResult = new StreamResult(seedOutputFolder+e1+"-"+e2+".xml");
                        transformer.transform(source,streamResult);

                    } catch (ParserConfigurationException | TransformerException e) {
                        e.printStackTrace();
                    }
                }

                if(!validation.isEmpty()){
                    for(String key : ValidationMap.keySet()) {
                        String class1 = key;
                        if(!class1.contains(".")){ 

                            DocumentBuilderFactory docBuildFactory = DocumentBuilderFactory.newInstance();
                            try {
                                DocumentBuilder documentBuilder = docBuildFactory.newDocumentBuilder();
                                Document document = documentBuilder.newDocument();


                                Element element = document.createElement("Seed");
                                document.appendChild(element);

                                Element category = document.createElement("Category");
                                addCategory(e1,e2,category,document);
                                element.appendChild(category);

                                Element A = document.createElement("Tag1");
                                A.appendChild(document.createTextNode(e1));
                                element.appendChild(A);

                                Element AValues = document.createElement("Name");
                                AValues.appendChild(document.createTextNode(class1));
                                A.appendChild(AValues);

                                Element B = document.createElement("Tag2");
                                B.appendChild(document.createTextNode(e2));
                                element.appendChild(B);


                                for(String class2 : ValidationMap.get(key)) {
                                    Element BValues = document.createElement("Name");
                                    BValues.appendChild(document.createTextNode(class2));
                                    B.appendChild(BValues);
                                }

                                Element C = document.createElement("Relation");
                                element.appendChild(C);
                                for(String ms: validation) {
                                    Element D = document.createElement("Pattern");
                                    D.appendChild(document.createTextNode(ms));
                                    C.appendChild(D);
                                }


                                TransformerFactory transformerFactory = TransformerFactory.newInstance();
                                Transformer transformer = transformerFactory.newTransformer();
                                transformer.setOutputProperty(OutputKeys.INDENT, "yes");
                                transformer.setOutputProperty("{http://xml.apache.org/xslt}indent-amount", "2");
                                DOMSource source = new DOMSource(document);
                                class1 = class1.replaceAll("<\\/?[a-z]+>", "");
                                    StreamResult streamResult = new StreamResult(validationFolder+hashxml+"-"+class1+"-"+e2+".xml");
                                    transformer.transform(source,streamResult);


                            } catch (ParserConfigurationException | TransformerException e) {
                                e.printStackTrace();
                            }
                        }

                    }
                }
                seedMap.clear();
                ValidationMap.clear();
                validation.clear();
                relation.clear();
                e1Name.clear();
                e2Name.clear();
                list.clear();
                seedLines = null;
                seedEntity.clear();
                lines.clear();






                System.out.println("Finish Reading seeds : seedOutput/"+e1+"-"+e2+".xml");

            }

            System.out.println("Finish Reading document :"+ xmlFile.getName());
            
            String title = xmlFile.getName().replaceAll(".xml","");
            File posfile = new File(posLogsFolder+title+" POSTagger Logs.txt");

            try {
                if(!posfile.exists()){
                    posfile.createNewFile();
                }

                PrintWriter pw = new PrintWriter(posfile);
                for(String TaggedPattern: POSText){
                    pw.println(TaggedPattern);
                    pw.println("");
                }
                pw.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
            POSText.clear();
        }





        long BEnd = System.nanoTime();
        long BTime = BEnd-BStart;
        System.out.println("Bootstrapping Finished Elapsed Time: " + BTime);
	}

   public static StringBuilder readFile(File xmlFile, Reader fileReader){
       try {

           fileReader = new FileReader(xmlFile);
       } catch (
               FileNotFoundException e) {
           e.printStackTrace();
       }
       BufferedReader bufReader = new BufferedReader(fileReader);
       StringBuilder sb = new StringBuilder();
       String line = null;
       try {
           line = bufReader.readLine();
       } catch (IOException e) {
           e.printStackTrace();
       }
       while (line != null) {
           sb.append(line).append("\n");
           try {
               line = bufReader.readLine();
           } catch (IOException e) {
               e.printStackTrace();
           }
       }
       return(sb);
   }

   public static String getTag(String tag){
       tag = tag.substring(tag.indexOf(":")+1);
       return(tag);
   }

   public static void addMap(Multimap<String,String> seedMap,ArrayList<String> seedEntity, int i){
       String[] classes = seedEntity.get(i).split(";", 2);
       if(classes.length >=2){
           String class1 = classes[0];
           String class2 = classes[1];
           seedMap.put(class1,class2);
           System.out.println(class1+" & "+class2+ "was added to seedmap");
       }else{
           System.out.println("ignoring line: " + i);
       }
   }

   public static void addRelation(
           TreeSet<String> relation, String pLine,
           String class1, String class2,
           String e1, String e2,Multimap<String,String> ValidationMap,TreeSet<String> validation){
       e1= e1.toLowerCase();
       e2 = e2.toLowerCase();

       String linetemp = pLine;
       if(pLine.contains("<"+e1+">"+class1+"</"+e1+">") && pLine.contains("<"+e2+">"+class2+"</"+e2+">") ){


           ValidationMap.put(class1,class2);
           System.out.println(class1 + " & "+ class2+ " was added to validationmap");


           Pattern p = Pattern.compile("<\\/["+e1+"]+>.+<["+e2+"]+>");
           Matcher m = p.matcher(pLine);
           Pattern pRev = Pattern.compile("<\\/["+e2+"]+>.+<["+e1+"]+>");
           Matcher mRev = pRev.matcher(pLine);
           while(m.find()) {
               String temp = m.group();
               temp = temp.replaceAll("<\\/["+e1+"]+>.+<["+e1+"]+>","");
               temp = temp.replaceAll("<\\/["+e2+"]+>.+<["+e2+"]+>","");
               temp = temp.replaceAll("<\\/?[a-z]+>", "");
               relation.add(temp);
               System.out.println(temp+ " was added to seedrelation");

              
           } 
           while(mRev.find()) {
               String temp = mRev.group();
               temp = temp.replaceAll("<\\/?[a-z]+>", "");
               relation.add(temp);
               System.out.println(temp+ " was added to seedrelation");

           } 
       }
   }

   public static void addPreprocessing(
           TreeSet<String> relation, String pLine,
           String e1, String e2,
           ArrayList<String> lines,Multimap<String,String> seedMap,Multimap<String,String> ValidationMap,TreeSet<String> list){
       e1 = e1.toLowerCase();
       e2 = e2.toLowerCase();
       TreeSet<String> List = new TreeSet<String>();
      
       if(!list.isEmpty())
          
           for (String l : list) {
               if (pLine.contains("<" + e1 + ">") && pLine.contains("<" + e2 + ">") && pLine.contains(l)) {
                   String class1 = null;
                   String class2 = null;
                   Pattern pc1 = Pattern.compile("<[" + e1 + "]+>[^<>]*<\\/[" + e1 + "]+>");
                   Matcher mc1 = pc1.matcher(pLine);
                   Pattern pc2 = Pattern.compile("<[" +e2 + "]+>[^<>]*<\\/[" + e2 + "]+>");
                   Matcher mc2 = pc2.matcher(pLine);
                   while (mc1.find()) {
                       class1 = mc1.group();
                       while (mc2.find()) {
                           class2 = mc2.group();
                           class1 = class1.replaceAll("<\\/?[a-z]+>", "");
                           class2 = class2.replaceAll("<\\/?[a-z]+>", "");
                           if(!class1.contains(".") || !class2.contains(".")){
                               seedMap.put(class1, class2);
                               ValidationMap.put(class1, class2);
                               System.out.println(class1 + class2 + " was added to validationmap");
                           }
                       }
                   }
                   Pattern p = Pattern.compile("<\\/[" + e1 + "]+>.+<[" + e2 + "]+>");
                   Matcher m = p.matcher(pLine);
                   while (m.find()) {
                       String temp = m.group();
                       temp = temp.replaceAll("<\\/["+e1+"]+>.+<["+e1+"]+>","");
                       temp = temp.replaceAll("<\\/["+e2+"]+>.+<["+e2+"]+>","");
                       temp = temp.replaceAll("<\\/?[a-z]+>", "");
                       relation.add(temp);
                       System.out.println(temp+" was added to relation");
                       for (String delete : lines) {
                           String store = delete.toLowerCase();
                           if (pLine.equals(store)) {
                               int index = lines.indexOf(delete);
                               lines.set(index, "");
                           }
                       }
                   } 
               }

           }
       }


   public static void readXML(String e1, String e2, TreeSet<String> matches){
       DocumentBuilderFactory factory=  DocumentBuilderFactory.newInstance();
       try {
           DocumentBuilder builder =  factory.newDocumentBuilder();
           Document doc = builder.parse(seedOutputFolder+e1+"-"+e2+".xml");
           doc.getDocumentElement().normalize();
           NodeList nodeList = doc.getElementsByTagName("Seed");
           for(int i = 0; i< nodeList.getLength();i++) {
               Node nNode = nodeList.item(i);
               if (nNode.getNodeType() == Node.ELEMENT_NODE) {
                   Element eElement = (Element) nNode;
                   int eCount= eElement.getElementsByTagName("Pattern").getLength();
                   for(int j=0; j<eCount;j++){
                       matches.add(eElement.getElementsByTagName("Pattern").item(j).getTextContent());
                   }

               }
           }
           doc.getDocumentElement().setTextContent("");
       } catch (ParserConfigurationException | IOException e) {
           e.printStackTrace();
       } catch (org.xml.sax.SAXException e) {
           e.printStackTrace();
       }
   }

   public static void addCategory(String e1, String e2, Element category, Document document){
       e1 = e1.toLowerCase();
       e2 = e2.toLowerCase();
       String entities = e1+"+"+e2;
       switch(entities){
           case "marOrg+commonName":
               category.appendChild(document.createTextNode("hasCommonName"));
               break;
           case "marOrg+habitat":
               category.appendChild(document.createTextNode("livesIn"));
               break;
           case "marOrg+genus":
               category.appendChild(document.createTextNode("belongsToGenus"));
               break;
           case "marOrg+family":
               category.appendChild(document.createTextNode("belongsToFamily"));
               break;
           case "marOrg+location":
           case "habitat+location":
               category.appendChild(document.createTextNode("isLocatedIn"));
               break;

           case "habitat+effect":
               category.appendChild(document.createTextNode("isAffectedBy"));
               break;
           case "effect+factor":
               category.appendChild(document.createTextNode("causedBy"));
               break;
           case "effect+effectValue":
               category.appendChild(document.createTextNode("effectValueIs"));
               break;
           case "factor+factorValue":
               category.appendChild(document.createTextNode("factorValueIs"));
               break;
       }
   }

   //V = Verb, Particule, Adverb
   //W = Noun, Adjective,Adverb, Pronoun, Determiner
   //P = Prepostion(ADP), Particle
   /*String V = "(.*)_[V][BP][A-Z]*[|]*[VB]*[NT]*[O]*(.*)"; //Verb --V
   String AV = "(.*)_[V][BP][A-Z]*[|]*[VB]*[NT]*[O]*(.*)"; //Adverb --VW
   String W = "(.*)_[N][NP][PS]*[|NSYMVBG]*(.*)"; //Noun --W
   String P = "(.*)_[RTP][NPOR][ST]*(.*)"; //Particule --VP
   String ADJ = "(.*)_[J][J][|SRJVBG]*(.*)"; //Adjective --W
   String PN = "(.*)_[WP][RP][|P$]*(VBP)*(.*)"; //Pronoun --W
   String DET = "(.*)_[PDEW][DTX][T]*(.*)"; //Determiner --W
   String ADP = "(.*)_[I][N][|RP]*(.*)"; //ADPronoun --P*/
   public static void POSTagger(TreeSet<String> seedPatternV,TreeSet<String> seedPatternW,TreeSet<String> seedPatternP,
                                String rLine, String V, String P, String W, MaxentTagger tagger, String AV,String ADJ,
                                String PN, String DET, String ADP, TreeSet<String> validation, TreeSet<String> POSText){
       String tagged = tagger.tagString(rLine);

       POSText.add(tagged);

       String[] tLines= tagged.split(" ");
       for(int i=0; i<tLines.length;i++){

           if(tLines[i].matches(V)){
               String temp = tLines[i].substring(0,tLines[i].indexOf("_"));
               seedPatternV.add(temp);
               if(i+1 <tLines.length){
                   if(tLines[i+1].matches(W) || tLines[i+1].matches(ADJ) || tLines[i+1].matches(AV) || tLines[i+1].matches(PN) || tLines[i+1].matches(DET) ){
                       temp = tLines[i+1].substring(0,tLines[i+1].indexOf("_"));
                       seedPatternW.add(temp);
                       if(i+2 < tLines.length){
                           if(tLines[i+2].matches(P) || tLines[i+2].matches(ADP)){
                               temp = tLines[i+2].substring(0,tLines[i+2].indexOf("_"));
                               seedPatternP.add(temp);
                               String TempSeedPatternV = tLines[i].substring(0,tLines[i].indexOf("_"));
                               TempSeedPatternV = TempSeedPatternV.replaceAll("(.*)-[A-Z]*-(.*)","");
                               String TempSeedPatternW = tLines[i+1].substring(0,tLines[i+1].indexOf("_"));
                               TempSeedPatternW = TempSeedPatternW.replaceAll("(.*)-[A-Z]*-(.*)","");
                               String TempSeedPatternP = tLines[i+2].substring(0,tLines[i+2].indexOf("_"));
                               TempSeedPatternP = TempSeedPatternP.replaceAll("(.*)-[A-Z]*-(.*)","");
                               validation.add(TempSeedPatternV+" "+TempSeedPatternW+" "+TempSeedPatternP);
                           }
                       }
                   }
                   else if(tLines[i+1].matches(P)|| tLines[i+1].matches(ADP)){
                       temp = tLines[i+1].substring(0,tLines[i+1].indexOf("_"));
                       seedPatternP.add(temp);
                       String TempSeedPatternV = tLines[i].substring(0,tLines[i].indexOf("_"));
                       TempSeedPatternV = TempSeedPatternV.replaceAll("(.*)-[A-Z]*-(.*)","");
                       String TempSeedPatternP = tLines[i+1].substring(0,tLines[i+1].indexOf("_"));
                       TempSeedPatternP = TempSeedPatternP.replaceAll("(.*)-[A-Z]*-(.*)","");
                       validation.add(TempSeedPatternV+" "+TempSeedPatternP);
                   }else{
                       String TempSeedPatternV = tLines[i].substring(0,tLines[i].indexOf("_"));
                       TempSeedPatternV = TempSeedPatternV.replaceAll("(.*)-[A-Z]*-(.*)","");
                       validation.add(TempSeedPatternV);
                   }
               }else{
                   String TempSeedPatternV = tLines[i].substring(0,tLines[i].indexOf("_"));
                   TempSeedPatternV = TempSeedPatternV.replaceAll("(.*)-[A-Z]*-(.*)","");
                   validation.add(TempSeedPatternV);
               }
           }
       }
   }

}
