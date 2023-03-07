package servlet;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.apache.commons.lang.WordUtils;
import org.semanticweb.owlapi.model.OWLOntologyCreationException;
import org.semanticweb.owlapi.model.OWLOntologyStorageException;

import edu.stanford.smi.protege.exception.OntologyLoadException;
import model.MarineOrganism;
import model.Genus;
import model.CommonName;
import model.Family;
import model.Habitat;
import model.Location;
import service.OntologyManager;
import service.OntologyQuery;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerConfigurationException;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;


@WebServlet({ "/AddMarOrgServlet, /ApproveMarOrgServlet, RejectMarOrgServlet"})
public class AddMarOrgServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static final String taggedFolder = "C:\\Users\\eduar\\Documents\\GitHub\\NatPro\\NatPro\\Documents\\TaggedBootstrap\\";
	private static final String validationFolder = "C:\\Users\\eduar\\Documents\\GitHub\\NatPro\\NatPro\\Documents\\validation\\";
	private static final String owlPath = "C:\\Users\\eduar\\Desktop\\OntoNatPro.owl";
	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public AddMarOrgServlet() {
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
		case "/AddMarOrgServlet":
			try {
				addMarOrg(request, response);
			} catch (OWLOntologyCreationException | OWLOntologyStorageException | OntologyLoadException
					| ServletException | IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			break;
		case "/ApproveMarOrgServlet":
			removeApproved(request, response);
			try {
				addPlant(request, response);
			} catch (OWLOntologyCreationException | OWLOntologyStorageException | OntologyLoadException
					| ServletException | IOException e1) {
			}

			break;
		case "/RejectMarOrgServlet":
			removeRejected(request, response);
			request.getRequestDispatcher("/ValidationServlet").forward(request, response);
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

	private void addMarOrg(HttpServletRequest request, HttpServletResponse response) throws OntologyLoadException,
			ServletException, IOException, OWLOntologyCreationException, OWLOntologyStorageException {
		OntologyManager m = new OntologyManager(owlPath);

		if (!request.getParameter("commonName").equals("")) { 
			String commonNameIndiv = request.getParameter("commonName").trim().toLowerCase().replaceAll(" ",
					"_");
			String commonName = request.getParameter("commonName").trim();
			m.addIndiv_CommName(commonNameIndiv);
			m.addDataPropCommName(commonName);

			if (!request.getParameter("marineOrganism").equals("")) { 
				String marOrgIndiv = request.getParameter("marineOrganism").trim().toLowerCase().replaceAll(" ",
						"_");
				m.addIndiv_MarineOrganism(marOrgIndiv);
				m.addDataPropMarineOrganism(request.getParameter("marineOrganism").trim());
				m.addObjectHasCommonName(commonNameIndiv, marOrgIndiv);

				
				if (!request.getParameter("genus").equals("")) { 
					String genusNameIndiv = request.getParameter("genus").trim().toLowerCase().replaceAll(" ", "_");
					m.addIndiv_Genus(genusNameIndiv);
					m.addDataPropGenus(request.getParameter("genus").trim());
					m.addObjectBelongsToGenus(marOrgIndiv, genusNameIndiv);

					if (!request.getParameter("family").equals("")) { 
						String familyNameIndiv = request.getParameter("family").trim().toLowerCase().replaceAll(" ",
								"_");
						m.addIndiv_Family(familyNameIndiv);
						m.addDataPropFamily(request.getParameter("family").trim());
						m.addObjectBelongsToFamily(genusNameIndiv, familyNameIndiv);

						if (!request.getParameter("habitat").equals("")) { 
							String habitatIndiv = request.getParameter("habitat").trim().toLowerCase().replaceAll(" ",
									"_");
							m.addIndiv_Habitat(habitatIndiv);
							m.addDataPropHabitat(request.getParameter("habitat").trim());
							m.addObjectLivesIn(marOrgIndiv, habitatIndiv);
						}
					}
				}
			}

			String[] locationNames;
			locationNames = request.getParameterValues("location");
			for (int i = 0; i < locationNames.length; i++) {
				if (!locationNames[i].equals("")) {
					String locationNameIndiv = locationNames[i].trim().toLowerCase().replaceAll(", ", " ")
							.replaceAll(",", " ").replaceAll(" ", "_");
					m.addIndiv_Location(locationNameIndiv);
					m.addDataPropLocation(locationNames[i].trim());

					m.addObjectIsLocatedIn(habitatIndiv, locationNameIndiv);
				}
			}
		}
		request.getRequestDispatcher("3aadded.jsp").forward(request, response);
	}

	private void removeApproved(HttpServletRequest request, HttpServletResponse response) {
		String fileName = request.getParameter("fileNameApprove").replaceAll(".pdf", "");
		String marOrg = request.getParameter("marOrgApprove").toLowerCase();
		removeValidationFile(fileName, marOrg);
	}

	private void removeRejected(HttpServletRequest request, HttpServletResponse response) {
		String fileName = request.getParameter("fileNameReject").replaceAll(".pdf", "");
		String marOrg = request.getParameter("marOrgReject").toLowerCase();
		removeValidationFile(fileName, marOrg);
	}

	private void removeValidationFile(String fileName, String marOrgName) {
		File Folder = new File(validationFolder);
		File[] listFiles = Folder.listFiles();
		List<File> deleteFiles = new ArrayList<>();
		for (File xmlFile : listFiles) {
			String deleteFile = fileName + "-" + marOrgName;
			Pattern p = Pattern.compile("^(.)*(" + deleteFile + ")(.)*");
			Matcher m = p.matcher(xmlFile.toString());
			boolean b = m.matches();
			System.out.println(b);
			System.out.println(deleteFile);
			System.out.println(xmlFile);
			if (b) {
				deleteFiles.add(xmlFile);
			}

			Pattern p1 = Pattern.compile("^(.)*(" + fileName + ")(.)*");
			Matcher m1 = p1.matcher(xmlFile.toString());
			boolean b1 = m1.matches();
			if (b1) {
				DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
				boolean delete = false;
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

							if (tag1.contains("CommonName")) {
								if (tag2.contains("MarineOrganism")) {
									if (nameElementTag2.item(0).getTextContent().equalsIgnoreCase(specieName)) {
										delete = true;
									}
								}
							}
						}
					}
				} catch (ParserConfigurationException | IOException | org.xml.sax.SAXException e) {
					e.printStackTrace();
				}

				if (delete) {
					deleteFiles.add(xmlFile);
				}
			}

		}
		System.out.println(deleteFiles);
		System.gc();
		for (File deleteXml : deleteFiles) {
			if (deleteXml.delete()) {
				System.out.println("Deleted the file");
			} else {
				System.err.println("Failed to delete the file.");
			}
		}
		System.gc();

	}

}
