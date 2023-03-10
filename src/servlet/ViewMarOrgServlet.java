package servlet;

import edu.stanford.smi.protege.exception.OntologyLoadException;
import edu.stanford.smi.protegex.owl.swrl.sqwrl.exceptions.SQWRLException;
import model.CommonName;
import model.MarineOrganism;
import service.OntologyQuery;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * Servlet implementation class ViewPlantServlet
 */
@WebServlet({ "/ViewMarOrgServlet", "/ViewCommNameServlet" })
public class ViewMarOrgServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static final String owlPath = "C:\\OntoMarine.owl";
	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public ViewMarOrgServlet() {
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
		case "/ViewMarOrgServlet":
			try {
				viewMarOrg(request, response);
			} catch (SQWRLException | OntologyLoadException | ServletException | IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			break;
		case "/ViewCommNameServlet":
			try {
				viewCN(request, response);
			} catch (OntologyLoadException | ServletException | IOException | SQWRLException e) {
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

	private void viewMarOrg(HttpServletRequest request, HttpServletResponse response)
			throws OntologyLoadException, ServletException, IOException, SQWRLException {
		// TODO Auto-generated method stub
		String searchKey = request.getParameter("marOrg");
		OntologyQuery q = new OntologyQuery(owlPath);
		try {
			List<MarineOrganism> marOrgs = q.searchMarineOrganism(searchKey);
			List<MarineOrganism> marOrgs2 = new ArrayList<>();
			for (MarineOrganism m : marOrgs) {
				if (m.getMarineOrg().equalsIgnoreCase(searchKey))
					marOrgs2.add(m);
			}

			request.setAttribute("marOrgsList", marOrgs2);
		} catch (Exception e) {
		}

		List<String> family = q.getAllFamily();
		request.setAttribute("familyList", family);

		List<String> genus = q.getAllGenus();
		request.setAttribute("genusList", genus);

		List<String> habitats = q.getAllHabitats();
		request.setAttribute("habitatList", habitats);

		List<String> locs = q.getAllLocations();
		request.setAttribute("locList", locs);

		request.getRequestDispatcher("6dentry.jsp").forward(request, response);
	}

	private void viewCN(HttpServletRequest request, HttpServletResponse response)
			throws OntologyLoadException, ServletException, IOException, SQWRLException {
		// TODO Auto-generated method stub

		String searchKey = request.getParameter("commName");
		OntologyQuery q = new OntologyQuery(owlPath);
		List<CommonName> commNames = q.searchCommonName(searchKey);
		request.setAttribute("commNameList", commNames);
		request.getRequestDispatcher("6dentry-sci.jsp").forward(request, response);
	}
}
