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

@WebServlet("/TranslateServlet")
public class TranslateServlet extends HttpServlet {
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String sourceLanguageIdStr = request.getParameter("sourceLanguage");
        String targetLanguageIdStr = request.getParameter("targetLanguage");
        String wordText = request.getParameter("word");

        if (sourceLanguageIdStr != null && targetLanguageIdStr != null && wordText != null && !wordText.trim().isEmpty()) {
            int sourceLanguageId = Integer.parseInt(sourceLanguageIdStr);
            int targetLanguageId = Integer.parseInt(targetLanguageIdStr);

            ServletContext context = getServletContext();
            List<Translation> translations = DataStorage.getTranslations(context);
            List<Word> words = DataStorage.getWords(context);

            String translationResult = "Переклад не знайдено";

            for (Word word : words) {
                if (word.getLanguageId() == sourceLanguageId && word.getWord().equalsIgnoreCase(wordText)) {
                    for (Translation translation : translations) {
                        if (translation.getWordId() == word.getId() && translation.getLanguageId() == targetLanguageId) {
                            translationResult = translation.getTranslation();
                            break;
                        }
                    }
                }
            }

            request.setAttribute("translationResult", translationResult);
        } else {
            request.setAttribute("translationResult", "Будь ласка, виберіть мови та введіть слово.");
        }

        request.getRequestDispatcher("translate.jsp").forward(request, response);
    }
}