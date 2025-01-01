package com.muates.inventorymanagementsystem.session;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

public class SessionManager {

    public static void createSession(HttpServletRequest req, Integer userId, boolean isSupplier) {
        HttpSession session = req.getSession();
        session.setAttribute("userId", userId);
        session.setAttribute("isSupplier", isSupplier);
    }

    public static void invalidateSession(HttpServletRequest req) {
        HttpSession session = req.getSession(false);
        if (session != null) {
            session.invalidate();
        }
    }

    public static Integer getUserId(HttpServletRequest req) {
        HttpSession session = req.getSession(false);
        if (session != null) {
            return (Integer) session.getAttribute("userId");
        }
        return null;
    }

    public static Boolean isSupplier(HttpServletRequest req) {
        HttpSession session = req.getSession(false);
        if (session != null) {
            return (Boolean) session.getAttribute("isSupplier");
        }
        return null;
    }
}

