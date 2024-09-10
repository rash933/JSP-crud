package com.is.invoicingsystem.controller;

import com.is.invoicingsystem.service.ItemService;
import com.is.invoicingsystem.service.impl.ItemServiceImpl;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;

import java.io.IOException;
import java.util.Objects;

@WebServlet(name = "items", value = "/items")
public class ItemController extends HttpServlet {
    ItemService itemService = new ItemServiceImpl();

    public void init() {
    }

//    @Override
//    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
//        itemService.getItems(request, response);
//    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            String action = request.getParameter("action");
            if (Objects.nonNull(action)) {
                switch (action) {
                    case "add":
                        itemService.addItem(request, response);
                        break;
                    case "edit":
                        itemService.editItem(request, response);
                        break;
                    case "delete":
                        itemService.deleteItem(request, response);
                        break;
                }
            } else {
                itemService.getItems(request, response);
            }

        } catch (Exception e) {
            e.printStackTrace();
            response.sendError(HttpServletResponse.SC_BAD_REQUEST, "Error adding item: " + e.getMessage());
        }
    }

//    @Override
//    protected void doPut(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
//        try {
//            itemService.editItem(request, response);
//        } catch (Exception e) {
//            response.sendError(HttpServletResponse.SC_BAD_REQUEST, "Error updating item: " + e.getMessage());
//        }
//    }
//
//    @Override
//    protected void doDelete(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
//        try {
//            itemService.deleteItem(request, response);
//        } catch (NumberFormatException e) {
//            response.sendError(HttpServletResponse.SC_BAD_REQUEST, "Invalid 'id' parameter: must be a valid number.");
//        } catch (Exception e) {
//            response.sendError(HttpServletResponse.SC_BAD_REQUEST, "Error deleting item: " + e.getMessage());
//        }
//    }
}
