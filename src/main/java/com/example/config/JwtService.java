package com.example.config;

import com.example.model.Student;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import org.springframework.stereotype.Service;

import javax.crypto.SecretKey;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@Service
public class JwtService {
    private final static String SECRET_KEY = "kSeTiOKxaLlfVcOB/qdq7IqUnYm4PT+R3kxQ9+5xuXKALc7dbLZTzvzEBMvoaBXV";
    private final static long JWT_EXPIRATION_MS = 86400000; // 1 ngày

    private SecretKey secretKey;

    public JwtService() {
        this.secretKey = Keys.hmacShaKeyFor(Decoders.BASE64.decode(SECRET_KEY));
    }

    // Phương thức này tạo JWT từ thông tin của sinh viên
    public String generateToken(Student student) {
        Map<String, Object> claims = new HashMap<>();
        claims.put("name", student.getName());
        claims.put("password", student.getPassword());
        return createToken(claims);
    }

    // Phương thức này tạo token từ các thông tin cần thiết
    private String createToken(Map<String, Object> claims) {
        return Jwts.builder()
                .setClaims(claims)
                .setIssuedAt(new Date())
                .setExpiration(new Date(System.currentTimeMillis() + JWT_EXPIRATION_MS))
                .signWith(secretKey, SignatureAlgorithm.HS256)
                .compact();
    }

    // Phương thức này kiểm tra tính hợp lệ của token dựa trên thông tin của sinh viên
    public boolean isTokenValid(String token, Student student) {
        final String username = extractUsername(token);
        return (username.equals(student.getName())) && !isTokenExpired(token);
    }

    // Phương thức này trích xuất tên người dùng từ token
    private String extractUsername(String token) {
        return extractAllClaims(token).getSubject();
    }

    // Phương thức này kiểm tra xem token có hết hạn không
    private boolean isTokenExpired(String token) {
        return extractExpiration(token).before(new Date());
    }

    // Phương thức này trích xuất các thông tin từ token
    private Claims extractAllClaims(String token) {
        return Jwts.parserBuilder().setSigningKey(secretKey).build().parseClaimsJws(token).getBody();
    }

    // Phương thức này trích xuất thời gian hết hạn của token
    private Date extractExpiration(String token) {
        return extractAllClaims(token).getExpiration();
    }
}
