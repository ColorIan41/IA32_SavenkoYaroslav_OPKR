
package servlet;

import jakarta.servlet.ServletContext;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;
import model.*;
import service.*;

@WebServlet("/EditLanguagesServlet")
public class EditLanguagesServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String newLanguage = request.getParameter("newLanguage");
        if (newLanguage != null && !newLanguage.trim().isEmpty()) {
            ServletContext context = getServletContext();
            List<Language> languages = DataStorage.getLanguages(context);
            int newId = languages.size() + 1;
            languages.add(new Language(newId, newLanguage));
            DataStorage.saveData(context);
        }
        response.sendRedirect("editData.jsp");
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getParameter("action");
        if ("delete".equals(action)) {
            int id = Integer.parseInt(request.getParameter("id"));
            ServletContext context = getServletContext();
            List<Language> languages = DataStorage.getLanguages(context);
            languages.removeIf(language -> language.getId() == id);
            DataStorage.saveData(context);
        }
        response.sendRedirect("editData.jsp");
    }
}