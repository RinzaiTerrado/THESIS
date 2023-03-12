package servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.WordUtils;
import org.semanticweb.owlapi.model.OWLOntologyCreationException;
import org.semanticweb.owlapi.model.OWLOntologyStorageException;

import edu.stanford.smi.protege.exception.OntologyLoadException;
import edu.stanford.smi.protegex.owl.model.OWLIndividual;
import edu.stanford.smi.protegex.owl.model.OWLNamedClass;
import edu.stanford.smi.protegex.owl.swrl.sqwrl.exceptions.SQWRLException;
import service.OntologyManager;
import service.OntologyQuery;

/**
 * Servlet implementation class EditServlet
 */
@WebServlet({ "/EditServlet", "/EditMarOrg", "/DeleteMarOrg", "/EditFamilyName", "/EditFamily", "/EditGenusName",
		"/EditGenus", "/AddGenus", "/RemoveGenus", "/EditCommName", "/AddLoc", "/RemoveLoc", "/EditHabitat",
		"/AddHabitat"})
public class EditServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static final String owlPath = "C:\\OntoMarine.owl";

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public EditServlet() {
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
//		response.getWriter().append("Served at: ").append(request.getContextPath());
		switch (request.getServletPath()) {
		case "/EditMarOrg":
			try {
				editMarOrg(request, response);
			} catch (OWLOntologyCreationException | OWLOntologyStorageException | ServletException | IOException
					| SQWRLException | OntologyLoadException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			break;
		case "/DeleteMarOrg":
			try {
				deleteMarOrg(request, response);
			} catch (OWLOntologyCreationException | OWLOntologyStorageException | ServletException | IOException
					| SQWRLException | OntologyLoadException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			break;
		case "/EditFamilyName":
			try {
				editFamilyName(request, response);
			} catch (SQWRLException | OWLOntologyCreationException | OWLOntologyStorageException | ServletException
					| IOException | OntologyLoadException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			break;
		case "/EditFamily":
			try {
				editFamily(request, response);
			} catch (SQWRLException | OWLOntologyCreationException | OWLOntologyStorageException | ServletException
					| IOException | OntologyLoadException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			break;
		case "/EditGenusName":
			try {
				editGenusName(request, response);
			} catch (SQWRLException | OWLOntologyCreationException | OWLOntologyStorageException | ServletException
					| IOException | OntologyLoadException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			break;
		case "/EditGenus":
			try {
				editGenus(request, response);
			} catch (SQWRLException | OWLOntologyCreationException | OWLOntologyStorageException | ServletException
					| IOException | OntologyLoadException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			break;
		case "/AddGenus":
			try {
				addGenus(request, response);
			} catch (SQWRLException | OWLOntologyCreationException | OWLOntologyStorageException | ServletException
					| IOException | OntologyLoadException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			break;
		case "/RemoveGenus":
			try {
				removeGenus(request, response);
			} catch (SQWRLException | OWLOntologyCreationException | OWLOntologyStorageException | ServletException
					| IOException | OntologyLoadException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			break;
		case "/EditCommName":
			try {
				editCommName(request, response);
			} catch (SQWRLException | OWLOntologyCreationException | OWLOntologyStorageException | ServletException
					| IOException | OntologyLoadException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			break;
		case "/AddLoc":
			try {
				addLoc(request, response);
			} catch (SQWRLException | OWLOntologyCreationException | OWLOntologyStorageException | ServletException
					| IOException | OntologyLoadException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			break;
		case "/RemoveLoc":
			try {
				removeLoc(request, response);
			} catch (SQWRLException | OWLOntologyCreationException | OWLOntologyStorageException | ServletException
					| IOException | OntologyLoadException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			break;
		case "/EditHabitat":
			try {
				editHabitat(request, response);
			} catch (SQWRLException | OWLOntologyCreationException | OWLOntologyStorageException | ServletException
					| IOException | OntologyLoadException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			break;
		case "/AddHabitat":
			try {
				addHabitat(request, response);
			} catch (SQWRLException | OWLOntologyCreationException | OWLOntologyStorageException | ServletException
					| IOException | OntologyLoadException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
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

	private void editMarOrg(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException, OWLOntologyCreationException, OWLOntologyStorageException,
			OntologyLoadException, SQWRLException {
		String oldMarOrg = request.getParameter("oldMarOrg");
		String newMarOrg = request.getParameter("newMarOrg");

		OntologyQuery q = new OntologyQuery(owlPath);
		String oldMarOrgIndiv = q.getMarOrgIndivName(oldMarOrg);

		String checkIfIndivNameExists = q.getMarOrgIndivName(newMarOrg);
		if (checkIfIndivNameExists == null) {
			OntologyManager m = new OntologyManager(owlPath);
			m.setMarOrgIndiv(oldMarOrgIndiv);
			m.addDataPropMarineOrganism(newMarOrg);
			m.removeDataPropertyValue(oldMarOrgIndiv, "datatypeProperty_MarineOrganism", oldMarOrg);
			m.changeNameIndividual(oldMarOrgIndiv, newMarOrg);

			PrintWriter out = response.getWriter();
			String message = "Marine Organism Successfully Edited";
			out.println(message);
		} else {
			PrintWriter out = response.getWriter();
			String message = "Edit Unsuccessful, Marine Organism Already Exists!";
			out.println(message);
		}
	}

	private void deleteMarOrg(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException, OWLOntologyCreationException, OWLOntologyStorageException,
			OntologyLoadException, SQWRLException {
		String marOrg = request.getParameter("orgVal");

		OntologyQuery q = new OntologyQuery(owlPath);
		String MarineOrgIndiv = q.getMarOrgIndivName(marOrg);

		try {
			OntologyManager m = new OntologyManager(owlPath);
			m.deleteMarOrg(MarineOrgIndiv);
			PrintWriter out = response.getWriter();
			String message = "Marine Organism Successfully Removed";
			out.println(message);

		} catch (Exception e) {
			PrintWriter out = response.getWriter();
			String message = "Remove Marine Organism Unsuccessful";
			out.println(message);
		}

	}

	private void editFamily(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException, OWLOntologyCreationException, OWLOntologyStorageException,
			OntologyLoadException, SQWRLException {
		String oldFamily = request.getParameter("oldFamilyVal");
		String newFamily = request.getParameter("newFamilyVal");

		OntologyQuery q = new OntologyQuery(owlPath);
		String oldFamilyIndiv = q.getFamilyIndivName(oldFamily);
		List<String> genus = q.getAllGenus();
		List<String> families = q.getAllFamily();

		String checkIfFamilyIndivExists = q.getFamilyIndivName(newFamily);

		OntologyManager m = new OntologyManager(owlPath);
		if (checkIfFamilyIndivExists == null) {
			m.addIndiv_Family(m.cleanString(newFamily));
			m.addDataPropFamily(newFamily);

		} else {
			String FamilyIndiv = q.getFamilyIndivName(newFamily);
			m.setFamilyIndiv(FamilyIndiv);
		}

		if (oldFamily.isEmpty()) {
			System.out.println("empty");
		} else {
			System.out.println(oldFamily);
		}

		for (int i = 0; i < genus.size(); i++) {
			String genusIndiv = q.getGenusIndivName(genus.get(i));
			List<String> genusToFamily = q.getGenusFamily(genus.get(i));
			if (genusToFamily.contains(oldFamily)) {
				m.removeObjectPropertyValue(genusIndiv, "belongsToFamily", oldFamilyIndiv);
				m.addObjectBelongsToFamily(genusIndiv, m.cleanString(newFamily));
			}
		}

		PrintWriter out = response.getWriter();
		String message = "Family Successfully Edited";
		out.println(message);
	}

	private void editFamilyName(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException, OWLOntologyCreationException, OWLOntologyStorageException,
			OntologyLoadException, SQWRLException {
		String oldFamilyName = request.getParameter("oldFamilyName");
		String newFamilyName = request.getParameter("newFamilyName");

		OntologyQuery q = new OntologyQuery(owlPath);
		String oldFamilyNameIndiv = q.getFamilyIndivName(oldFamilyName);

		String checkIfIndivNameExists = q.getFamilyIndivName(newFamilyName);
		if (checkIfIndivNameExists == null) {
			OntologyManager m = new OntologyManager(owlPath);
			m.setFamilyIndiv(oldFamilyNameIndiv);
			m.addDataPropFamily(newFamilyName);
			m.removeDataPropertyValue(oldFamilyNameIndiv, "datatypeProperty_Family", oldFamilyName);
			m.changeNameIndividual(oldFamilyNameIndiv, newFamilyName);

			PrintWriter out = response.getWriter();
			String message = "Family Name Successfully Edited";
			out.println(message);
		} else {
			PrintWriter out = response.getWriter();
			String message = "Edit Unsuccessful, Family Name Already Exists!";
			out.println(message);
		}
	}

	private void editGenus(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException, OWLOntologyCreationException, OWLOntologyStorageException,
			OntologyLoadException, SQWRLException {
		PrintWriter out = response.getWriter();
		String message = "Edit Genus Failed. Does not work";
		out.println(message);
	}

	private void editGenusName(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException, OntologyLoadException, OWLOntologyCreationException,
			OWLOntologyStorageException, SQWRLException {
		String oldGenusName = request.getParameter("oldGenusName");
		String newGenusName = request.getParameter("newGenusName");

		OntologyQuery q = new OntologyQuery(owlPath);
		String oldGenusNameIndiv = q.getGenusIndivName(oldGenusName);

		String checkIfIndivNameExists = q.getGenusIndivName(newGenusName);
		if (checkIfIndivNameExists == null) {
			OntologyManager m = new OntologyManager(owlPath);
			m.setGenusIndiv(oldGenusNameIndiv);
			m.addDataPropGenus(newGenusName);
			m.removeDataPropertyValue(oldGenusNameIndiv, "datatypeProperty_Genus", oldGenusName);
			m.changeNameIndividual(oldGenusNameIndiv, newGenusName);

			PrintWriter out = response.getWriter();
			String message = "Genus Name Successfully Edited";
			out.println(message);
		} else {
			PrintWriter out = response.getWriter();
			String message = "Edit Unsuccessful, Genus Name Already Exists!";
			out.println(message);
		}
	}

	private void addGenus(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException, OntologyLoadException, OWLOntologyCreationException,
			OWLOntologyStorageException, SQWRLException {
		String genus = request.getParameter("genusVal");
		String familyName = request.getParameter("familyName");

		try {
			OntologyQuery q = new OntologyQuery(owlPath);
			String familyIndiv = q.getFamilyIndivName(familyName);

			String checkIfGenusIndivExists = q.getGenusIndivName(genus);
			OntologyManager m = new OntologyManager(owlPath);
			if (checkIfGenusIndivExists == null) {
				m.addIndiv_Genus(m.cleanString(genus));
				m.addDataPropGenus(genus);
				m.addObjectBelongsToFamily(m.cleanString(genus), familyIndiv);

			} else {
				String oldFamily;
				try {
					oldFamily = q.getGenusFamily(checkIfGenusIndivExists).get(0);
				} catch (Exception e) {
					oldFamily = null;
				}
				if (oldFamily == null) {
					m.setGenusIndiv(checkIfGenusIndivExists);
					m.addObjectBelongsToFamily(checkIfGenusIndivExists, familyIndiv);
				} else {
					String oldFamilyIndiv = q.getFamilyIndivName(oldFamily);
					m.removeObjectPropertyValue(checkIfGenusIndivExists, "belongsToFamily", oldFamilyIndiv);
					m.setGenusIndiv(checkIfGenusIndivExists);
					m.addObjectBelongsToFamily(checkIfGenusIndivExists, familyIndiv);
				}
			}

			PrintWriter out = response.getWriter();
			String message = "Genus Added Successfully";
			out.println(message);
		} catch (Exception e) {
			System.err.println(e);
			PrintWriter out = response.getWriter();
			String message = "Genus Failed to Add";
			out.println(message);
		}

	}

	private void removeGenus(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException, OntologyLoadException, OWLOntologyCreationException,
			OWLOntologyStorageException, SQWRLException {
		String genus = request.getParameter("genusVal");
		String familyName = request.getParameter("familyName");

		try {
			OntologyQuery q = new OntologyQuery(owlPath);
			String checkIfGenusIndivExists = q.getGenusIndivName(genus);
			String familyIndiv = q.getFamilyIndivName(familyName);

			OntologyManager m = new OntologyManager(owlPath);
			m.removeObjectPropertyValue(checkIfGenusIndivExists, "belongsToFamily", familyIndiv);

			PrintWriter out = response.getWriter();
			String message = "Genus Removed";
			out.println(message);
		} catch (Exception e) {
			PrintWriter out = response.getWriter();
			String message = "Remove Genus Failed";
			out.println(message);
		}

	}

	private void editCommName(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException, OntologyLoadException, OWLOntologyCreationException,
			OWLOntologyStorageException, SQWRLException {
		String oldCommName = request.getParameter("oldCommName");
		String newCommName = request.getParameter("newCommName");

		OntologyQuery q = new OntologyQuery(owlPath);
		String oldCommNameIndiv = q.getCommNameIndivName(oldCommName);

		String checkIfIndivNameExists = q.getCommNameIndivName(newCommName);
		if (checkIfIndivNameExists == null) {
			OntologyManager m = new OntologyManager(owlPath);
			m.setCommNameIndiv(oldCommNameIndiv);
			m.addDataPropCommName(newCommName);
			m.removeDataPropertyValue(oldCommNameIndiv, "datatypeProperty_CommonName", oldCommName);
			m.changeNameIndividual(oldCommNameIndiv, newCommName);

			PrintWriter out = response.getWriter();
			String message = "Common Name Successfully Edited";
			out.println(message);
		} else {
			PrintWriter out = response.getWriter();
			String message = "Edit Unsuccessful, Common Name Already Exists!";
			out.println(message);
		}

	}

	private void addHabitat(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException,
			OntologyLoadException, OWLOntologyCreationException, OWLOntologyStorageException, SQWRLException {
		String habitat = request.getParameter("habitatVal");
		String marOrg = request.getParameter("marOrg");

		OntologyQuery q = new OntologyQuery(owlPath);
		String marOrgIndiv = q.getMarOrgIndivName(marOrg);

		String checkIfHabitatIndivExists = q.getHabitatIndivName(habitat);
		OntologyManager m = new OntologyManager(owlPath);
		if (checkIfHabitatIndivExists == null) {
			m.addIndiv_Habitat(m.cleanString(habitat));
			m.addDataPropHabitat(habitat);
			m.addObjectLivesIn(marOrgIndiv, m.cleanString(habitat));
		} else {
			String habitatIndiv = q.getHabitatIndivName(habitat);
			m.setHabitatIndiv(habitatIndiv);
			m.addObjectLivesIn(marOrgIndiv, habitatIndiv);
		}

		PrintWriter out = response.getWriter();
		String message = "Habitat Added Successfully";
		out.println(message);

	}

	private void editHabitat(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException, OntologyLoadException, OWLOntologyCreationException,
			OWLOntologyStorageException, SQWRLException {
		String oldHabitat = request.getParameter("oldHabitat");
		String newHabitat = request.getParameter("newHabitat");

		OntologyQuery q = new OntologyQuery(owlPath);
		String oldHabitatIndiv = q.getHabitatIndivName(oldHabitat);

		String checkIfIndivNameExists = q.getHabitatIndivName(newHabitat);
		if (checkIfIndivNameExists == null) {
			OntologyManager m = new OntologyManager(owlPath);
			m.setHabitatIndiv(oldHabitatIndiv);
			m.addDataPropHabitat(newHabitat);
			m.removeDataPropertyValue(oldHabitatIndiv, "datatypeProperty_Habitat", oldHabitat);
			m.changeNameIndividual(oldHabitatIndiv, newHabitat);

			PrintWriter out = response.getWriter();
			String message = "Habitat Successfully Edited";
			out.println(message);
		} else {
			PrintWriter out = response.getWriter();
			String message = "Edit Unsuccessful, Habitat Already Exists!";
			out.println(message);
		}

	}

	private void addLoc(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException,
			OntologyLoadException, OWLOntologyCreationException, OWLOntologyStorageException, SQWRLException {
		String location = request.getParameter("locVal");
		String marOrg = request.getParameter("marOrg");

		OntologyQuery q = new OntologyQuery(owlPath);
		String marOrgIndiv = q.getMarOrgIndivName(marOrg);

		String checkIfLocIndivExists = q.getLocIndivName(location);
		OntologyManager m = new OntologyManager(owlPath);
		if (checkIfLocIndivExists == null) {
			m.addIndiv_Location(m.cleanString(location));
			m.addDataPropLocation(location);
			m.addObjectIsLocatedIn(marOrgIndiv, m.cleanString(location));

		} else {
			String locIndiv = q.getLocIndivName(location);
			m.setLocationIndiv(locIndiv);
			m.addObjectIsLocatedIn(marOrgIndiv, locIndiv);
		}

		PrintWriter out = response.getWriter();
		String message = "Location Added Successfully";
		out.println(message);

	}

	private void removeLoc(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException, OntologyLoadException, OWLOntologyCreationException,
			OWLOntologyStorageException, SQWRLException {
		String location = request.getParameter("locVal");
		String marOrg = request.getParameter("marOrg");

		OntologyQuery q = new OntologyQuery(owlPath);
		String marOrgIndiv = q.getMarOrgIndivName(marOrg);
		String locIndiv = q.getLocIndivName(location);
		OntologyManager m = new OntologyManager(owlPath);
		m.removeObjectPropertyValue(marOrgIndiv, "isLocatedIn", locIndiv);

		PrintWriter out = response.getWriter();
		String message = "Location Removed";
		out.println(message);

	}
}
