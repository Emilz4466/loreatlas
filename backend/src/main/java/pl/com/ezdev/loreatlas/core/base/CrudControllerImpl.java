package pl.com.ezdev.loreatlas.core.base;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;

import java.util.List;

@RequiredArgsConstructor
public abstract class CrudControllerImpl<
        T extends BaseEntity,
        ID,
        Request,
        Response,
        FindRequest>
        implements CrudController<ID, Request, Response, FindRequest> {

    private final CrudService<T, ID, Request, Response, FindRequest> service;

    @Override
    public ResponseEntity<Response> get(ID id) {
        return ResponseEntity.ok(service.get(id));
    }

    @Override
    public ResponseEntity<Response> add(Request request) {
        return ResponseEntity.ok(service.add(request));
    }

    @Override
    public ResponseEntity<Response> update(ID id, Request request) {
        return ResponseEntity.ok(service.update(id, request));
    }

    @Override
    public ResponseEntity<Void> delete(ID id) {
        service.delete(id);
        return ResponseEntity.noContent().build();
    }

    @Override
    public List<Response> findAll(FindRequest request) {
        return service.findAll(request);
    }
}
