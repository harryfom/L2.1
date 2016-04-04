package servlets;

import accounts.dataSets.UserProfile;
import accounts.executor.AccountService;
import com.google.gson.Gson;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

/**
 * Created by FomeIV on 04.04.2016.
 */
public class UserListServlet extends HttpServlet{
    private final AccountService accountService;

    public UserListServlet(AccountService accountService) {
        this.accountService = accountService;
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        List<UserProfile> userProfiles = accountService.getAllUsers();
        Gson gson = new Gson();
        String json = gson.toJson(userProfiles);
        resp.setContentType("text/html;charset=utf-8");
        resp.getWriter().println(json);
        resp.setStatus(HttpServletResponse.SC_OK);
    }
}
