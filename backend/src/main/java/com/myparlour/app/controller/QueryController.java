package com.myparlour.app.controller;
import com.myparlour.app.entity.CustomerQuery;
import com.myparlour.app.repository.CustomerQueryRepository;
import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;
import java.util.*;
@RestController @RequestMapping("/api")
public class QueryController {
 private final CustomerQueryRepository repo; public QueryController(CustomerQueryRepository repo){this.repo=repo;}
 @PostMapping("/queries") public ResponseEntity<?> create(@Valid @RequestBody CustomerQuery q){ q.setMobileNumber(q.getMobileNumber().replaceAll("\\s+","")); return ResponseEntity.status(HttpStatus.CREATED).body(repo.save(q)); }
 @GetMapping("/admin/queries") public ResponseEntity<?> all(HttpSession s){ if(!Boolean.TRUE.equals(s.getAttribute("admin"))) return ResponseEntity.status(401).build(); List<CustomerQuery> list=repo.findAll(); list.sort(Comparator.comparing(CustomerQuery::getCreatedAt, Comparator.nullsLast(Comparator.reverseOrder()))); return ResponseEntity.ok(list); }
 @DeleteMapping("/admin/queries/{id}") public ResponseEntity<?> delete(@PathVariable Long id,HttpSession s){ if(!Boolean.TRUE.equals(s.getAttribute("admin"))) return ResponseEntity.status(401).build(); repo.deleteById(id); return ResponseEntity.noContent().build(); }
}
