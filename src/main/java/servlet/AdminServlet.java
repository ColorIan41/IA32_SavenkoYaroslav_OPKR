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

@WebServlet("/AdminServlet")
public class AdminServlet extends HttpServlet {
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        ServletContext context = getServletContext();
        List<Language> languages = DataStorage.getLanguages(context);
        List<Word> words = DataStorage.getWords(context);
        List<Translation> translations = DataStorage.getTranslations(context);

        request.setAttribute("languages", languages);
        request.setAttribute("words", words);
        request.setAttribute("translations", translations);

        request.getRequestDispatcher("admin.jsp").forward(request, response);
    }
}