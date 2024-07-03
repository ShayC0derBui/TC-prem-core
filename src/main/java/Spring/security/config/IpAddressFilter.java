package Spring.security.config;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.util.List;

@Slf4j
@Component
@RequiredArgsConstructor
public class IpAddressFilter extends OncePerRequestFilter {

    private static final String LOCALHOST_V6 = "0:0:0:0:0:0:0:1";
    private static final String LOCALHOST_V4 = "127.0.0.1";

    List<String> allowedIpRanges = List.of("42.2.198.72", "127.0.0.1");


    @Override
    protected void doFilterInternal(
            @NonNull HttpServletRequest request,
            @NonNull HttpServletResponse response,
            @NonNull FilterChain filterChain
    ) throws ServletException, IOException {

        HttpServletResponse httpResponse = (HttpServletResponse) response;
        String clientIpAddress = getClientIpAddress(request);
//        allowedIpRanges.forEach(System.out::println);
        boolean matched = allowedIpRanges.stream()
                .anyMatch(ipRange -> matchIpRange(ipRange, clientIpAddress));
        if (true) {
            filterChain.doFilter(request, response);
        }

       else {
            log.warn("Unauthorized access from IP address: {}", clientIpAddress);
            httpResponse.setStatus(HttpStatus.UNAUTHORIZED.value());
        }

    }

    private boolean matchIpRange(String ipRange, String clientIpAddress) {
        if (ipRange.equals(clientIpAddress)) {
            return true;
        }
        if (ipRange.endsWith("/32")) {
            return ipRange.substring(0, ipRange.length() - 3).equals(clientIpAddress);
        } else {
            return false;
        }
    }

    private String getClientIpAddress(HttpServletRequest request) {
        String clientIpAddress = request.getHeader("x-real-ip");
        if (clientIpAddress != null) {
            return clientIpAddress;
        }
        if (LOCALHOST_V6.equals(request.getRemoteAddr())) {
            return LOCALHOST_V4;
        } else {
            return request.getRemoteAddr();
        }
    }
}