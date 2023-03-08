package servlet;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.Reader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.TreeSet;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.lang.WordUtils;
import org.json.JSONObject;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import com.google.gson.Gson;

import edu.stanford.smi.protege.exception.OntologyLoadException;
import edu.stanford.smi.protegex.owl.swrl.sqwrl.exceptions.SQWRLException;
import model.MarineOrganism;
import model.CommonName;
import model.Family;
import model.Genus;
import model.Location;
import model.Habitat;
import service.OntologyQuery;

/**
 * Servlet implementation class ValidationServlet
 */
@WebServlet({ "/ValidationServlet", "/GetMarOrgEntity" })
public class ValidationServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static final String taggedFolder = "\\Documents\\TaggedBootstrap\\";
	private static final String validationFolder = "\\Documents\\Validation\\";
	private static final String owlPath = "\\Ontology\\OntoMarine.owl";
	
	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public ValidationServlet() {
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
		switch (request.getServletPath()) {
		case "/ValidationServlet":
			try {
				getValidationXML(request, response);
			} catch (ServletException | IOException | OntologyLoadException | SQWRLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			break;
		case "/GetMarOrgEntity":
			getMarOrgEntity(request, response);
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
	}

	private void getMarOrgEntity(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String marOrgName = request.getParameter("fileName");
		String commName = request.getParameter("commName");
		File Folder = new File(validationFolder);
		File[] listFiles = Folder.listFiles();
		Reader fileReader = null;
		String pdfFileName = null;
		HashSet<Validation> validations = new HashSet<Validation>();
		for (File xmlFile : listFiles) {
			ArrayList<String> lines = new ArrayList<String>();

			pdfFileName = getPdfFileName(xmlFile) + ".pdf";
			if (pdfFileName.equalsIgnoreCase(plantFileName)) {

				Validation xmlValidation = new Validation(pdfFileName);
				xmlValidation = findIfPresent(xmlValidation, validations);

				readXML(xmlValidation, xmlFile);

				validations.add(xmlValidation);
			}
		}
		String jsonEntry = "";
		Iterator<Validation> vIt = validations.iterator();
		while (vIt.hasNext()) {
			Validation v = vIt.next();
			if (v.getPdfFileName().equalsIgnoreCase(plantFileName)) {
				Iterator<MarineOrganism> mIt = v.getMarineOrganisms().iterator();
				while (mIt.hasNext()) {
					MarineOrganism m = mIt.next();
					try {
						if (m.getMarineOrganisms().get(0).getCommonName().equalsIgnoreCase(commName) ) {
							jsonEntry = new Gson().toJson(m);
						}
						if ((!m.getMarineOrganism().equalsIgnoreCase(commName))) {
							jsonEntry = new Gson().toJson(m);
							break;
						}
						System.out.println(jsonEntry);
					} catch (Exception e) {

					}
				}
			}
		}
		PrintWriter out = response.getWriter();
		out.println(jsonEntry);

	}

	private void getValidationXML(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException, OntologyLoadException, SQWRLException {
		File Folder = new File(validationFolder);
		File[] listFiles = Folder.listFiles();
		Reader fileReader = null;
		String pdfFileName = null;
		HashSet<Validation> validations = new HashSet<Validation>();
		for (File xmlFile : listFiles) {
			ArrayList<String> lines = new ArrayList<String>();
			pdfFileName = getPdfFileName(xmlFile) + ".pdf";

			Validation xmlValidation = new Validation(pdfFileName);
			xmlValidation = findIfPresent(xmlValidation, validations);

			String xmlString = readFile(xmlFile, fileReader).toString();
			String[] xmlLine = xmlString.split("\\r?\\n");
			Collections.addAll(lines, xmlLine);

			TreeSet<String> CategoryList = new TreeSet<String>();
			readXML(xmlValidation, xmlFile);

			validations.add(xmlValidation);
		}

		request.setAttribute("Validations", validations);
		String jsonValidations = new Gson().toJson(validations);
		request.setAttribute("JsonValidations", jsonValidations);
		OntologyQuery q = new OntologyQuery(owlPath);
		List<String> commNames = q.getAllCommonNames();
		request.setAttribute("commNameList", commNames);
		List<String> marOrgs = q.getAllMarineOrg();
		request.setAttribute("marOrgList", marOrgs);

		request.getRequestDispatcher("validation.jsp").forward(request, response);

	}

	Validation findIfPresent(Validation curValidation, HashSet<Validation> validations) {
		if (validations.contains(curValidation)) {
			for (Validation v : validations) {
				if (v.equals(curValidation))
					return v;
			}
		}

		return curValidation;
	}

	public StringBuilder readFile(File xmlFile, Reader fileReader) {
		try {
			fileReader = new FileReader(xmlFile);
		} catch (FileNotFoundException e) {
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
		return (sb);
	}

	public String getPdfFileName(File xmlFile) {
		String pdfFileName = null;
		Pattern pattern = Pattern.compile("^([A-Z]*)\\w+");
		Matcher matcher = pattern.matcher(xmlFile.getName());
		if (matcher.find()) {
			pdfFileName = matcher.group();
		}
		return pdfFileName;

	}

	public void readXML(Validation validation, File xmlFile) {
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

					if (tag1.contains("MarineOrganism")) {
						MarineOrganism marOrg = new MarineOrganism(nameElementTag1.item(0).getTextContent());
						if (validation.getMarineOrganisms().contains(marOrg)) {
							for (MarineOrganism m : validation.getMarineOrganisms()) {
								if (m.equals(marOrg))
									marOrg = m;
							}
						}


						validation.addMedicinalPlants(medPlant);
					}

					if (tag1.contains("CommonName")) {
						CommonName commName = new CommonName(nameElementTag1.item(0).getTextContent());
						if (validation.getAllCommonNames().contains(commName)) {
							for (CommonName s : validation.getAllCommonNames()) {
								if (s.equals(commName))
									commName = s;
							}
						}

						if (tag2.contains("Location")) {
							ArrayList<String> locations = new ArrayList<String>();

							for (int j = 0; j < nameElementTag2.getLength(); j++) {
								locations.add(WordUtils.capitalizeFully(nameElementTag2.item(j).getTextContent()));
							}

							marOrg.setLocations(locations);

						}

						if (tag2.contains("Family")) {
							commName.setFamily(WordUtils.capitalizeFully(nameElementTag2.item(0).getTextContent()));
						}

						if (tag2.contains("Genus")) {
							commName.setGenus(WordUtils.capitalizeFully(nameElementTag2.item(0).getTextContent()));
						}

						validation.addCommonName(commName);
						validation.addMarOrg(marOrg);
					}

					if (validation.getMarineOrganisms().size() > 0) {
						Iterator<MarineOrganism> mIt = validation.getMarineOrganism().iterator();
						while (mIt.hasNext()) {
							MarineOrganism marOrg = mIt.next();
							Iterator<MarineOrganism> mIt2 = validation.getMarineOrganisms().iterator();
							while (mIt2.hasNext()) {
								MarineOrganism marOrg2 = mIt2.next();
								try {
									if (marOrg.getMarineOrganism()
											.equalsIgnoreCase(marOrg2.getCommonName().get(0))) {
										marOrg2.setLocations(marOrg.getLocations());
									}
								} catch (Exception e) {
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

}
