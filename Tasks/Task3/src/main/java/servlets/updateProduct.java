package servlets;

import org.example.Product;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

import static servlets.addProduct.store;

@WebServlet("/update")
public class updateProduct extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String name = req.getParameter("name");
        String newName = req.getParameter("newname");
        double newPrice = Double.parseDouble(req.getParameter("newprice"));
        resp.setContentType("text/html");
        resp.getWriter().println(name + "--" + newName + "--" + newPrice);
        try {
            Product p = store.updateProduct(name, newName, newPrice);
            resp.getWriter().println("<h1>Updated Product</h1>");
            resp.getWriter().println(p + "<br>");
        } catch (Exception exception) {
            resp.getWriter().println("<h1>Error</h1>");
            resp.getWriter().println("<p>" + exception.getMessage() + "  kk" + "</p>");
        }
        resp.getWriter().println("<a href=\"index.html\">Back to Home</a>");
    }
}
