package ru.peshekhonov;

import ru.peshekhonov.products.Product;
import ru.peshekhonov.products.ProductRepository;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet(urlPatterns = "/products")
public class ProductServlet extends HttpServlet {

    private ProductRepository productRepository;

    @Override
    public void init() throws ServletException {
        productRepository = (ProductRepository) getServletContext().getAttribute("productRepository");
        if (productRepository == null) {
            throw new ServletException("Repository of Products does not exist");
        }
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        PrintWriter writer = resp.getWriter();
        writer.println("<table style=\"font-size: 1.5rem;\">");
        writer.println("<caption style=\"color:red\"><i>Список продуктов</i></caption>");
        writer.println("<thead><tr><th>id</th><th>название продукта</th><th>цена</th></tr></thead>");

        for (Product product : productRepository.findAll()) {
            writeProductRow(writer, product);
        }

        writer.println("</table>");
    }

    private void writeProductRow(PrintWriter writer, Product product) {
        String link = getServletContext().getContextPath() + "/product/" + product.getId();
        String format = "<td><a href=\"%s\">%s</a></td>%n";
        writer.println("<tr style=\"font-size: 1.2rem;\">");
        writer.printf(format, link, product.getId());
        writer.printf(format, link, product.getTitle());
        writer.println("<td>" + product.getCost() + "</td>");
        writer.println("</tr>");
    }
}
