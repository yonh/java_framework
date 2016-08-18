package org.yonh.controller;

import org.yonh.model.Customer;
import org.yonh.service.CustomerService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

/**
 * Created by yonh on 8/15/16Monday.
 */

@WebServlet("/customer")
public class CustomerServlet extends HttpServlet {
    private CustomerService service = new CustomerService();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        List<Customer> list = service.getCustomerList("");
        req.setAttribute("list", list);
        req.getRequestDispatcher("/WEB-INF/view/customer.jsp").forward(req, resp);
    }
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

    }
}
