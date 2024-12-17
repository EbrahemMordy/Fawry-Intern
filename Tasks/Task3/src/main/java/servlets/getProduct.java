package servlets;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

import static servlets.addProduct.store;

@WebServlet("/get")
public class getProduct extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String name = req.getParameter("name");
        resp.setContentType("text/html");
        try {
            String p = store.getProduct(name).toString();
            resp.getWriter().println("<h1>" + p + "</h1>");
        } catch (Exception e) {
            resp.getWriter().println("<h1>Error: Product Not Found</h1>");
            resp.getWriter().println("<p>" + e.getMessage() + "</p>");
        }
        resp.getWriter().println("<a href=\"index.html\">Back to Home</a>");

    }
}
