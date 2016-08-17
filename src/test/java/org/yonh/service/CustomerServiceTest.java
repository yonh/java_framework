package org.yonh.service;


import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.yonh.helper.DatabaseHelper;
import org.yonh.model.Customer;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by yonh on 8/15/16Monday.
 */
public class CustomerServiceTest {
    private final CustomerService customerService;

    public CustomerServiceTest() {
        customerService = new CustomerService();
    }

    @Before
    public void init() throws Exception {
        String file = "sql/customer_init.sql";
        DatabaseHelper.executeSqlFile(file);
    }

    @Test
    public void getCustomerListTest() throws Exception {
        List<Customer> customerList  = customerService.getCustomerList("");
        Assert.assertEquals(2, customerList.size());
    }

    public void createCreateCustomerTest() throws Exception {
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("name", "vincent");
        map.put("contact", "vincent");
        map.put("mobile", "110");
        boolean result = customerService.createCustomer(map);

        Assert.assertTrue(result);
    }

    public void getCustomerTest() throws Exception {
        int id = 1;
        Customer customer = customerService.getCustomer(id);
        Assert.assertNotNull(customer);
    }

    public void updateCustomerTest() throws Exception {
        int id = 1;
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("name", "hello world");
        boolean result = customerService.updateCustomer(id, map);

        Assert.assertTrue(result);
    }

    public void deleteCustomerTest() throws Exception {
        int id = 1;
        boolean result = customerService.deleteCustomer(id);

        Assert.assertTrue(result);
    }

}
