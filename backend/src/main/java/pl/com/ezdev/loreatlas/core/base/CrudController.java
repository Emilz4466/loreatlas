package pl.com.ezdev.loreatlas.core.base;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

public interface CrudController<ID, Request, Response, FindRequest> {

    @GetMapping("/{id}")
    ResponseEntity<Response> get(@PathVariable ID id);

    @PostMapping
    ResponseEntity<Response> add(@RequestBody Request request);

    @PutMapping("/{id}")
    ResponseEntity<Response> update(@PathVariable ID id, @RequestBody Request request);

    @DeleteMapping("/{id}")
    ResponseEntity<Void> delete(@PathVariable ID id);

    @GetMapping
    List<Response> findAll(@ModelAttribute FindRequest request);
}
