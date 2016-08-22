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
@WebServlet("/customer_edit")
public class CustomerEditServlet extends HttpServlet {
    private CustomerService service = new CustomerService();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        int id = Integer.parseInt(req.getParameter("id"));
        Customer customer = this.service.getCustomer(id);
        req.setAttribute("customer", customer);

        System.out.println(customer);

        req.getRequestDispatcher("WEB-INF/view/customer_edit.jsp").forward(req, resp);

    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Map<String, Object> map = new HashMap<String, Object>();
        int id = Integer.parseInt(req.getParameter("id"));
        map.put("name", req.getParameter("name"));
        map.put("contact", req.getParameter("contact"));
        map.put("mobile", req.getParameter("mobile"));
        map.put("email", req.getParameter("email"));
        map.put("remark", req.getParameter("remark"));

        if (service.updateCustomer(id, map)) {
            req.setAttribute("message", "编辑成功");
            Customer customer = service.getCustomer(id);
            req.setAttribute("customer", customer);
        }

        req.getRequestDispatcher("/WEB-INF/view/customer_edit.jsp").forward(req, resp);
    }
}
