package class4.demoday.domain.auth.service;

import jakarta.servlet.http.HttpServletRequest;

public interface SignoutService {
    void signout(HttpServletRequest request);
}
