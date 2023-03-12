package servlet;

import org.apache.commons.io.FileUtils;
import preprocessing.Tagger;
import service.GenDocID;
import service.SaveFile;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;
import java.io.*;
import java.nio.file.Paths;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.TreeSet;
import java.util.stream.Collectors;

@WebServlet({ "/UploadServlet" })
@MultipartConfig(maxFileSize = 16177215L)
public class UploadServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	private static final String taggedFolder = "\\Documents\\TaggedBootstrap\\";
	private	String uploadedDocumentsFolderPath = "\\Documents\\UploadedDocuments\\";
	private String taggedDocumentsFolderPath = "\\Documents\\Tagged\\";
	private String processingTxtFile = "\\Documents\\processing.txt";

	protected void doGet(final HttpServletRequest request, final HttpServletResponse response)
			throws ServletException, IOException {
		response.sendRedirect("2upload.html");
	}

	protected void doPost(final HttpServletRequest request, final HttpServletResponse response)
			throws ServletException, IOException {
		removeFinishedFiles();
		List<Part> fileParts = request.getParts().stream()
				.filter(part -> "file-upload".equals(part.getName()) && part.getSize() > 0)
				.collect(Collectors.toList()); 

		if (fileParts.size() > 1) {
			request.setAttribute("numdocsplural", "s");
		} else {
			request.setAttribute("numdocsplural", "");
		}

		List<String> fileNameList = new ArrayList<String>();
		for (Part filePart : fileParts) {
			String fileName = Paths.get(getSubmittedFileName(filePart)).getFileName().toString();

			InputStream fileContent = filePart.getInputStream();
			InputStream fileContent1 = filePart.getInputStream();

			try {
				String uniqueID = new GenDocID(fileContent).getId();
				if (checkDocument(uniqueID)) {
					System.out.println(fileName);
					if (fileName.endsWith(".pdf")) {
						new SaveFile(fileContent1, new File(uploadedDocumentsFolderPath + uniqueID + ".pdf"));
					} else if (fileName.endsWith(".txt")) {
						new SaveFile(fileContent1, new File(uploadedDocumentsFolderPath + uniqueID + ".txt"));
					}
				} else {
					System.out.println("file duplicate: " + fileName);
				}
			} catch (NoSuchAlgorithmException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			}
			fileNameList.add(fileName);
		}
		request.setAttribute("filenamelist", fileNameList);

		File folder = new File(uploadedDocumentsFolderPath);
		File[] listOfFiles = folder.listFiles();

		List<Thread> threads = new ArrayList<Thread>();
		for (int i = 0; i < listOfFiles.length; i++) {
			if (listOfFiles[i].isFile()) {
				String name = listOfFiles[i].getName().replaceAll(".pdf", "").replaceAll(".txt", "");
				deleteProcessUntagged();
				if (checkIfProcessing(name)) {
					if (!getTaggedDocumentsNames().contains(name)) {
						int num = i;
						System.err.println("bef:>>>>>>>>>>>>>>>>>>>>>>>>>>> " + Thread.activeCount());
						Thread upThread = new Thread(() -> {
							try {
								writeUniqueID(name);
								new Tagger(folder + "\\" + listOfFiles[num].getName(), name);

								System.err.println("aft:>>>>>>>>>>>>>>>>>>>>>>>>>>> " + Thread.activeCount());
							} catch (NoSuchAlgorithmException | ClassCastException | ClassNotFoundException
									| IOException e) {
								e.printStackTrace();
								try {
									deleteUniqueID(name);
								} catch (IOException e1) {
									e1.printStackTrace();
								}
							} finally {
								try {
									deleteUniqueID(name);
								} catch (IOException e) {
									e.printStackTrace();
								}
							}
						});

						upThread.start();
						threads.add(upThread);

					} else {
						System.out.println(name + " already tagged");
					}
				} else {

					System.out.println(name + " preprocessing ongoing");
				}
			} else if (listOfFiles[i].isDirectory()) {
				System.out.println("Directory " + listOfFiles[i].getName());
			}
		}

		request.setAttribute("threads", threads);
		request.getRequestDispatcher("/BootstrapServlet").forward(request, response);

	}
	
	public void removeFinishedFiles() {
		File Folder = new File(taggedFolder);
		File[] listFiles = Folder.listFiles();
		for (File xmlFile : listFiles) {
			if (xmlFile.delete()) {
				System.out.println("Deleted the file: " + xmlFile.getName());
			} else {
				System.out.println("Failed to delete the file.");
			}
		}
	}

	public Set<String> getTaggedDocumentsNames() throws IOException {
		Set<String> tagged = new TreeSet<String>();
		File dir = new File(taggedDocumentsFolderPath);
		File[] files = dir.listFiles((dir1, name) -> name.endsWith(".xml"));
		for (File file : files) {
			tagged.add(file.getName().replaceAll(".xml", ""));
		}
		return tagged;
	}

	public void deleteProcessUntagged() throws IOException {
		Set<String> tagged = getTaggedDocumentsNames();
		String content = FileUtils.readFileToString(new File(processingTxtFile), "UTF-8");

		for (String s : tagged) {
			content = content.replaceAll(s, "");
		}

		File tempFile = new File(processingTxtFile);
		FileUtils.writeStringToFile(tempFile, content, "UTF-8");
	}

	public void deleteUniqueID(String uniqueID) throws IOException {
		String content = FileUtils.readFileToString(new File(processingTxtFile), "UTF-8");
		content = content.replaceAll(uniqueID, "");
		File tempFile = new File(processingTxtFile);
		FileUtils.writeStringToFile(tempFile, content, "UTF-8");
	}

	public void writeUniqueID(String uniqueID) throws IOException {
		String filename = processingTxtFile;
		FileWriter fw = new FileWriter(filename, true); 
		fw.write("\n" + uniqueID);
		fw.close();
	}

	private static String getSubmittedFileName(Part part) {
		for (String cd : part.getHeader("content-disposition").split(";")) {
			if (cd.trim().startsWith("filename")) {
				String fileName = cd.substring(cd.indexOf('=') + 1).trim().replace("\"", "");
				return fileName.substring(fileName.lastIndexOf('/') + 1).substring(fileName.lastIndexOf('\\') + 1); // MSIE fix.
			}
		}
		return null;
	}

	public boolean checkIfProcessing(String uniqueID) throws IOException {
		BufferedReader reader = new BufferedReader(new FileReader(processingTxtFile));
		String line = reader.readLine();
		while (line != null) {
			if (!line.equals("")) {
				if (line.trim().equals(uniqueID)) {
					return false;
				}
			}

			line = reader.readLine();
		}

		reader.close();

		return true;
	}

	public boolean checkDocument(String uniqueID) throws IOException {
		File dir = new File(uploadedDocumentsFolderPath);
		File[] files = dir.listFiles(
				(dir1, name) -> name.startsWith(uniqueID) && (name.endsWith(".pdf") || name.endsWith(".txt")));
		if (files.length == 0) {
			return true;
		}
		return false;
	}
}