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

@WebServlet("/EditTranslationsServlet")
public class EditTranslationsServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int wordId = Integer.parseInt(request.getParameter("wordId"));
        int languageId = Integer.parseInt(request.getParameter("languageId"));
        String translationText = request.getParameter("translation");
        if (translationText != null && !translationText.trim().isEmpty()) {
            ServletContext context = getServletContext();
            List<Translation> translations = DataStorage.getTranslations(context);
            int newId = translations.size() + 1;
            translations.add(new Translation(newId, wordId, languageId, translationText));
            DataStorage.saveData(context);
        }
        response.sendRedirect("editData.jsp");
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getParameter("action");
        if ("delete".equals(action)) {
            int id = Integer.parseInt(request.getParameter("id"));
            ServletContext context = getServletContext();
            List<Translation> translations = DataStorage.getTranslations(context);
            translations.removeIf(translation -> translation.getId() == id);
            DataStorage.saveData(context);
        }
        response.sendRedirect("editData.jsp");
    }
}