package com.myparlour.app.controller;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;
import java.util.Map;
@RestController @RequestMapping("/api/admin")
public class AdminController {
 @Value("${admin.username}") String username; @Value("${admin.password}") String password;
 @PostMapping("/login") public ResponseEntity<?> login(@RequestBody Map<String,String> body,HttpSession session){
  if(username.equals(body.get("username")) && password.equals(body.get("password"))){session.setAttribute("admin",true); return ResponseEntity.ok(Map.of("loggedIn",true));}
  return ResponseEntity.status(401).body(Map.of("message","Invalid username or password"));
 }
 @GetMapping("/session") public Map<String,Boolean> session(HttpSession s){return Map.of("loggedIn",Boolean.TRUE.equals(s.getAttribute("admin")));}
 @PostMapping("/logout") public Map<String,Boolean> logout(HttpSession s){s.invalidate(); return Map.of("loggedOut",true);}
}

