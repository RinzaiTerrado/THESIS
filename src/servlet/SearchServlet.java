package servlet;

import edu.stanford.smi.protege.exception.OntologyLoadException;
import edu.stanford.smi.protegex.owl.swrl.sqwrl.exceptions.SQWRLException;
import model.*;
import service.OntologyQuery;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

/**
 * Servlet implementation class SearchServlet
 */
@WebServlet({ "/SearchServlet", "/BrowseServlet" })
public class SearchServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static final String owlPath = "C:\\OntoMarine.owl";

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public SearchServlet() {
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
		case "/BrowseServlet":
			try {
				browse(request, response);
			} catch (OntologyLoadException | ServletException | IOException | SQWRLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

			break;
		default:
			System.out.println("aERROR(Inside userServlet *doPost*): url pattern doesn't match existing patterns.");
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		switch (request.getServletPath()) {
		case "/SearchServlet":
			try {
				performSearch(request, response);
			} catch (OntologyLoadException | SQWRLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			break;
		case "/BrowseServlet":
			try {
				browse(request, response);
			} catch (OntologyLoadException | ServletException | IOException | SQWRLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

			break;
		default:
			System.out.println("ERROR(Inside userServlet *doPost*): url pattern doesn't match existing patterns.");
		}
		doGet(request, response);
	}

	private void performSearch(HttpServletRequest request, HttpServletResponse response)
			throws OntologyLoadException, ServletException, IOException, SQWRLException {
		// TODO Auto-generated method stub
		String searchKey = request.getParameter("searchKey").trim();
		OntologyQuery q = new OntologyQuery(owlPath);
		if (request.getParameter("searchCategory").equals("1")) {
			List<MarineOrganism> marOrgs = q.searchMarineOrganism(searchKey);
			request.setAttribute("marOrgList", marOrgs);
		} else if (request.getParameter("searchCategory").equals("2")) {
			List<CommonName> commNames = q.searchCommonName(searchKey);
			request.setAttribute("commNameList", commNames);
		} else if (request.getParameter("searchCategory").equals("3")) {
			List<Genus> genus = q.searchGenus(searchKey);
			request.setAttribute("genusList", genus);
		} else if (request.getParameter("searchCategory").equals("4")) {
			List<Family> family = q.searchFamily(searchKey);
			request.setAttribute("familyList", family);
		} else if (request.getParameter("searchCategory").equals("5")) {
			List<String> locList = q.searchLocations(searchKey);
			request.setAttribute("locList", locList);
		} else if (request.getParameter("searchCategory").equals("6")) {
			List<Habitat> habitatList = q.searchHabitat(searchKey);
			request.setAttribute("habitatList", habitatList);
		}

		request.setAttribute("searchCategory", request.getParameter("searchCategory"));

		request.setAttribute("searchKey", searchKey);

		request.getRequestDispatcher("searchresults.jsp").forward(request, response);
	}

	private void browse(HttpServletRequest request, HttpServletResponse response)
			throws OntologyLoadException, ServletException, IOException, SQWRLException {
		String searchKey = "";
		OntologyQuery q = new OntologyQuery(owlPath);
		if (request.getParameter("searchCategory").equals("1")) {
			List<MarineOrganism> marOrgs = q.searchMarineOrganism(searchKey);
			request.setAttribute("marOrgList", marOrgs);
		} else if (request.getParameter("searchCategory").equals("2")) {
			List<CommonName> commNames = q.searchCommonName(searchKey);
			request.setAttribute("commNameList", commNames);
		} else if (request.getParameter("searchCategory").equals("3")) {
			List<Genus> genus = q.searchGenus(searchKey);
			request.setAttribute("genusList", genus);
		} else if (request.getParameter("searchCategory").equals("4")) {
			List<Family> family = q.searchFamily(searchKey);
			request.setAttribute("familyList", family);
		} else if (request.getParameter("searchCategory").equals("5")) {
			List<String> locList = q.searchLocations(searchKey);
			request.setAttribute("locList", locList);
		} else if (request.getParameter("searchCategory").equals("6")) {
			List<Habitat> habitatList = q.searchHabitat(searchKey);
			request.setAttribute("habitatList", habitatList);
		}

		request.setAttribute("searchCategory", request.getParameter("searchCategory"));

		request.getRequestDispatcher("searchresults.jsp").forward(request, response);

	}

}
