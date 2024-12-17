package servlets;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

import static servlets.addProduct.store;

@WebServlet("/delete")
public class removeProduct extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String name = req.getParameter("name");
        resp.setContentType("text/html");
        try {
            store.deleteProduct(name);
            resp.getWriter().println("<h1>Deleted Product with Name: " + name + "</h1>");
        } catch (Exception e) {
            resp.getWriter().println("<h1>Error: Product Not Found</h1>");
            resp.getWriter().println("<p>" + e.getMessage() + "</p>");
        }
        resp.getWriter().println("<a href=\"index.html\">Back to Home</a>");

    }
}
