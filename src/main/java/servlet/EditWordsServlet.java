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

@WebServlet("/EditWordsServlet")
public class EditWordsServlet extends HttpServlet {

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String newWord = request.getParameter("newWord");
        int languageId = Integer.parseInt(request.getParameter("languageId"));
        if (newWord != null && !newWord.trim().isEmpty()) {
            ServletContext context = getServletContext();
            List<Word> words = DataStorage.getWords(context);
            int newId = words.size() + 1;
            words.add(new Word(newId, newWord, languageId));
            DataStorage.saveData(context);
        }
        response.sendRedirect("editData.jsp");
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getParameter("action");
        if ("delete".equals(action)) {
            int id = Integer.parseInt(request.getParameter("id"));
            ServletContext context = getServletContext();
            List<Word> words = DataStorage.getWords(context);
            words.removeIf(word -> word.getId() == id);
            DataStorage.saveData(context);
        }
        response.sendRedirect("editData.jsp");
    }
}
