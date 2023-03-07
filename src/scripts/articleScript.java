package scripts;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.HashSet;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import com.google.common.collect.HashMultimap;

public class articleScript {
	public static HashSet<String> gold_rel_set;
	private static HashMap<String, String> gold_entityType_map;
	private static HashMap<String, String> gol_relType_map;
	
	private static HashMap<String, Integer> gold_rel_map;
	
	public static HashSet<String> res_rel_tp_set;
	public static HashSet<String> res_rel_set;
	private static HashMap<String, String> res_entityType_map;
	private static HashMap<String, String> res_relType_map;
	
	private static HashMap<String, Integer> res_rel_tp_map;
	private static HashMap<String, Integer> res_rel_map;
	
	private static int ctr = 0;
	
	public static void main(String[] args) throws IOException {
		System.out.println("Main...");
        long startTime, endTime;
        startTime = System.nanoTime();
        
        gold_rel_map = new HashMap<String, Integer>();
		initMap0(gold_rel_map);
		
		res_rel_tp_map = new HashMap<String, Integer>();
		initMap0(res_rel_tp_map);
		
		res_rel_map = new HashMap<String, Integer>();
		initMap0(res_rel_map);
		
		ReadGoldAnn();
		ReadXMLFiles();
		
		printResult2();
		
		endTime = System.nanoTime();
        System.err.println("Main Duration: "+ ((double)(endTime - startTime)) / 1000000 + " ms");
	}
	
	public static void Evaluate() {
		int ctr = 0;
		for(String goldPair : gold_rel_set) {
			if(res_rel_set.contains(goldPair)) {
				ctr++;
				res_rel_tp_set.add(goldPair);
			}
		}
		System.out.println(ctr);
	}

	public static String getScore_Accuracy(String relType) {
		return String.format("%.2f", (double) res_rel_tp_map.get(relType)/(res_rel_tp_map.get(relType)+(res_rel_map.get(relType)-res_rel_tp_map.get(relType))+(gold_rel_map.get(relType)-res_rel_tp_map.get(relType)))*100);
	}
	
	public static String getScore_Precision(String relType) {
		return String.format("%.2f", (double) res_rel_tp_map.get(relType)/(res_rel_tp_map.get(relType)+(res_rel_map.get(relType)-res_rel_tp_map.get(relType)))*100);
	}
	
	public static String getScore_Recall(String relType) {
		return String.format("%.2f", (double) res_rel_tp_map.get(relType)/(res_rel_tp_map.get(relType)+(gold_rel_map.get(relType)-res_rel_tp_map.get(relType)))*100);
	}
	
	public static String getScore_Fmeasure(String relType) {
		Double prec = Double.parseDouble(getScore_Precision(relType));
		Double rec = Double.parseDouble(getScore_Recall(relType));
		return String.format("%.2f", (double) (2*prec*rec)/(prec+rec));
	}
	
	public static int getTP(String relType) {
		return res_rel_tp_map.get(relType);
	}
	
	public static int getFP(String relType) {
		return res_rel_map.get(relType)-res_rel_tp_map.get(relType);
	}
	
	public static int getFN(String relType) {
		return gold_rel_map.get(relType)-res_rel_tp_map.get(relType);
	}
	
	public static void ReadGoldAnn() throws IOException {
		System.out.println("ReadGoldAnn...");
        long startTime, endTime;
        startTime = System.nanoTime();
		
        gold_rel_set = new HashSet<String>();
        gold_entityType_map = new HashMap<String, String>();
        gol_relType_map = new HashMap<String, String>();
        
		File Folder = new File(annFolder);
		File[] listFiles = Folder.listFiles();
		
		for (File file : listFiles) {
			if(file.getName().matches("(.*)\\.ann")) {
				HashMap<String, String> Ts = new HashMap<String, String>();
				
				String text = "";
				
				BufferedReader reader;
		        reader = new BufferedReader(new FileReader(file));
		        String line = reader.readLine();
		        while (line != null) {
		        	text += line + "\n";
		            line = reader.readLine();
		        }
		        
		        text = text.replaceAll("\\(", "\\\\(").replaceAll("\\)", "\\\\)");
		        text = text.replaceAll("\\[", "\\\\[").replaceAll("\\]", "\\\\]");
		        
		        Pattern pattern1 = Pattern.compile("(T\\d+)\\t(\\w+)\\s\\d+\\s\\d+\\t(.*)");
		        Matcher matcher1 = pattern1.matcher(text);
		        
		        while(matcher1.find()) {
		        	Ts.put(matcher1.group(1), matcher1.group(3).toLowerCase());
		        	gold_entityType_map.put(matcher1.group(3).toLowerCase(), matcher1.group(2));
		        }
		        
		        Pattern pattern2 = Pattern.compile("(R\\d+)\\t(\\w+)\\sArg1:(T\\d+)\\sArg2:(T\\d+)");
		        Matcher matcher2 = pattern2.matcher(text);
		        
		        while(matcher2.find()) {
		        	String ent1 = Ts.get(matcher2.group(3));
		        	String ent2 = Ts.get(matcher2.group(4));
		        	
		        	if(!gold_rel_set.contains(ent1 + " : " + ent2)) {
		        		gold_rel_set.add(ent1 + " : " + ent2);
		        		gol_relType_map.put(ent1 + " : " + ent2, matcher2.group(2));
		        		eval(gold_rel_map, matcher2.group(2), gold_entityType_map.get(ent1));
		        	} else {
		        	}
		        }
		        		   
		        reader.close();
			}
		}
		
		endTime = System.nanoTime();
        System.err.println("ReadGoldAnn Duration: "+ ((double)(endTime - startTime)) / 1000000 + " ms");
	}
	
	public static void runConnector(HashMap<String, String> map, String txt, String relType, String entType) {
		Pattern pattern2 = Pattern.compile("(R\\d+)\\t(hasSynonymPlantPart)\\sArg1:(T\\d+)\\sArg2:(T\\d+)");
        Matcher matcher2 = pattern2.matcher(txt);
        
        while(matcher2.find()) {
        	String synonym = map.get(matcher2.group(3));
        	String partT = matcher2.group(4);
        	
        	
        	Pattern pattern3 = Pattern.compile("(R\\d+)\\t(hasCompound)\\sArg1:("+partT+")\\sArg2:(T\\d+)");
            Matcher matcher3 = pattern3.matcher(txt);
            
            while(matcher3.find()) {
            	String compound = map.get(matcher3.group(4));
            	
            	
            	if(!gold_rel_set.contains(synonym + " : " + compound)) {
	        		gold_rel_set.add(synonym + " : " + compound);
	        		gol_relType_map.put(synonym + " : " + compound, matcher2.group(2));
	        		eval(gold_rel_map, "hasCompound", gold_entityType_map.get(synonym));
	        	}
            }
            
        }
	}
	
	public static void runConnector2(HashMap<String, String> map, String txt, String relType, String entType) {
		Pattern pattern2 = Pattern.compile("(R\\d+)\\t("+relType+")\\sArg1:(T\\d+)\\sArg2:(T\\d+)");
        Matcher matcher2 = pattern2.matcher(txt);
        
        while(matcher2.find()) {
        	String synonym = map.get(matcher2.group(3));
        	String partT = matcher2.group(4);
        	        	
        	Pattern pattern3 = Pattern.compile("(R\\d+)\\t("+entType+")\\sArg1:("+partT+")\\sArg2:(T\\d+)");
            Matcher matcher3 = pattern3.matcher(txt);
            
            while(matcher3.find()) {
            	String compound = map.get(matcher3.group(4));
            	
            	
            	if(!gold_rel_set.contains(synonym + " : " + compound)) {
	        		gold_rel_set.add(synonym + " : " + compound);
	        		gol_relType_map.put(synonym + " : " + compound, matcher2.group(2));
	        		eval(gold_rel_map, relType, gold_entityType_map.get(synonym));
	        	}
            }
            
        }
	}
	
	public static void readXML(File xmlFile) {
		
		DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
		try {
			DocumentBuilder builder = factory.newDocumentBuilder();
			Document doc = builder.parse(xmlFile.getAbsoluteFile());
			doc.getDocumentElement().normalize();
			NodeList nodeList = doc.getElementsByTagName("Seed");
			for (int i = 0; i < nodeList.getLength(); i++) {
				Node nNode = nodeList.item(i);
				if (nNode.getNodeType() == Node.ELEMENT_NODE) {
					Element eElement = (Element) nNode;

					String tag1 = eElement.getElementsByTagName("Tag1").item(0).getTextContent();
					String tag2 = eElement.getElementsByTagName("Tag2").item(0).getTextContent();

					Element elementTag1 = (Element) eElement.getElementsByTagName("Tag1").item(0);
					Element elementTag2 = (Element) eElement.getElementsByTagName("Tag2").item(0);

					NodeList nameElementTag1 = elementTag1.getElementsByTagName("Name");
					NodeList nameElementTag2 = elementTag2.getElementsByTagName("Name");
					
					String relType = eElement.getElementsByTagName("Category").item(0).getTextContent();
					
					String entType1 = tag1.replace(nameElementTag1.item(0).getTextContent(), "").trim();
					String entType2 = tag2.replaceAll("\\(", "\\\\(").replaceAll("\\)", "\\\\)");
					entType2 = entType2.replaceAll("\\[", "\\\\[").replaceAll("\\]", "\\\\]");
					entType2 = entType2.replaceAll("\\s+", "").replaceAll("\n", "");
							
					String txt = "";
					for(int j = 0; j < nameElementTag2.getLength(); j++) {
						txt += nameElementTag2.item(j).getTextContent() + "";
					}
					
					txt = txt.replaceAll("\\(", "\\\\(").replaceAll("\\)", "\\\\)");
					txt = txt.replaceAll("\\[", "\\\\[").replaceAll("\\]", "\\\\]");
					txt = txt.replaceAll("\\s+", "").replaceAll("\n", "");
										
					entType2 = entType2.replace(txt, "");

					for(int j = 0; j < nameElementTag2.getLength(); j++) {
						String ent1 = nameElementTag1.item(0).getTextContent().toLowerCase();
			        	String ent2 = nameElementTag2.item(j).getTextContent().toLowerCase();
			        	
						if(gold_rel_set.contains(ent1 + " : " + ent2)) {
							if(!res_rel_tp_set.contains(ent1 + " : " + ent2)) {
								res_rel_tp_set.add(ent1 + " : " + ent2);
								if(res_rel_tp_set.contains(ent1 + " : " + ent2)) {
									eval(res_rel_tp_map, gol_relType_map.get(ent1 + " : " + ent2), gold_entityType_map.get(ent1));
								}
							}
							System.out.println(ent1 + " : " + ent2 + " " + relType);
						} 
						
						if(!res_rel_set.contains(ent1 + " : " + ent2)) {
							res_rel_set.add(ent1 + " : " + ent2);
							res_relType_map.put(ent1 + " : " + ent2, relType);
							
							if(res_rel_set.contains(ent1 + " : " + ent2) || res_rel_tp_set.contains(ent1 + " : " + ent2)) {
								if(res_rel_tp_set.contains(ent1 + " : " + ent2)) {
									eval(res_rel_map, gol_relType_map.get(ent1 + " : " + ent2), gold_entityType_map.get(ent1));
								} else {
									eval(res_rel_map, relType, entType1);
									System.err.println(ent1 + " : " + ent2 + " (" + relType + ") == (" + xmlFile.getName().replaceAll("-\\w+\\.xml", "").replaceAll("-[^-]+", "") + ")");
								}
							}
						}
					}
				}
			}
		} catch (ParserConfigurationException | IOException e) {
			e.printStackTrace();
		} catch (org.xml.sax.SAXException e) {
			e.printStackTrace();
		}
	}
	
	public static void ReadXMLFiles() {
		
		System.out.println("ReadXMLFiles...");
        long startTime, endTime;
        startTime = System.nanoTime();
        
        res_rel_tp_set = new HashSet<String>();
        res_rel_set = new HashSet<String>();
    	res_entityType_map = new HashMap<String, String>();
    	res_relType_map = new HashMap<String, String>();
		
		File Folder = new File(validationFolder);
		File[] listFiles = Folder.listFiles();
		for (File xmlFile : listFiles) {
			readXML(xmlFile);
		}
		
		endTime = System.nanoTime();
        System.err.println("ReadXMLFiles Duration: "+ ((double)(endTime - startTime)) / 1000000 + " ms");
	}
	
	
	public static int getGold(String relType) {
		return gold_rel_map.get(relType);
	}
	
	public static int getRet(String relType) {
		return res_rel_map.get(relType);
	}
	
	public static void printResult() {
		System.out.println("Gold Pair Size: " + gold_rel_set.size());
		System.out.println("Result Pair Size: " + res_rel_set.size());
		System.out.println("True Positive: " + res_rel_tp_set.size());
		System.out.println("False Positive: " + (res_rel_set.size()-res_rel_tp_set.size()));
		System.out.println("False Negative: " + (gold_rel_set.size()-res_rel_tp_set.size()));
		int tp = res_rel_tp_set.size();
		int fp = res_rel_set.size()-res_rel_tp_set.size();
		int fn = gold_rel_set.size()-res_rel_tp_set.size();
		
		double acc = (double) tp/(tp+fp+fn)*100;
		double prec = (double) tp/(tp+fp)*100;
		double rec = (double) tp/(tp+fn)*100;
		double fm = (double) (2*prec*rec)/(prec+rec);
	}
}
