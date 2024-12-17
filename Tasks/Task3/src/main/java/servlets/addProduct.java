package servlets;

import org.example.Product;
import org.example.Store;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/add")
public class addProduct extends HttpServlet {
    static Store store = new Store();

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        String name = req.getParameter("name");
        double price = Double.parseDouble(req.getParameter("price"));
        resp.setContentType("text/html");
        try {
            Product product = store.addProduct(name, price);
            resp.getWriter().println("<h1>Added Product</h1>");
            resp.getWriter().println(product + "<br>");
        } catch (Exception exception) {
            resp.getWriter().println("<h1>Error</h1>");
            resp.getWriter().println("<p>" + exception.getMessage() + "</p>");
        }
        resp.getWriter().println("<a href=\"index.html\">Back to Home</a>");
    }
}
