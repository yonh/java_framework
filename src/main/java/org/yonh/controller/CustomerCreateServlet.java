package org.yonh.controller;

import org.yonh.model.Customer;
import org.yonh.service.CustomerService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by yonh on 8/15/16Monday.
 */
@WebServlet("/customer_create")
public class CustomerCreateServlet extends HttpServlet {
    private CustomerService service = new CustomerService();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getRequestDispatcher("/WEB-INF/view/customer_create.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getParameter("name");


        Map<String, Object> map = new HashMap<String, Object>();
        map.put("name", req.getParameter("name"));
        map.put("contact", req.getParameter("contact"));
        map.put("mobile", req.getParameter("mobile"));
        map.put("email", req.getParameter("email"));
        map.put("remark", req.getParameter("remark"));

        if (service.createCustomer(map)) {
            req.setAttribute("message", "创建成功");
        }

        req.getRequestDispatcher("/WEB-INF/view/customer_create.jsp").forward(req, resp);
    }
}
