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

@WebServlet(urlPatterns = "/product/*")
public class IdServlet extends HttpServlet {

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
        try {
            String pathInfo = req.getPathInfo().substring(1);
            Product product = productRepository.findById(Long.parseLong(pathInfo));
            if (product == null) {
                writer.println("<p style=\"font-size: 2em;\">В магазине нет такого товара</p>");
                resp.setStatus(404);
                return;
            }
            writer.printf("<h2 style=\"color: green;font-size: 4em;\">%s</h2>%n", product.getTitle());
            writer.printf("<p style=\"font-size: 3em;\">цена: %s</p>%n", product.getCost());
            writer.printf("<p style=\"font-size: 3em;\">идентификатор: %s</p>%n", product.getId());
        } catch (NumberFormatException | NullPointerException e) {
            writer.println("<p style=\"font-size: 2em;\">Плохой запрос</p>");
            resp.setStatus(400);
        }
    }
}
