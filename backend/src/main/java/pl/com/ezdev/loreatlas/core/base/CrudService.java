package pl.com.ezdev.loreatlas.core.base;

import java.util.List;

public interface CrudService<T extends BaseEntity, ID, Request, Response, FindAllRequest> {
    Response add(Request request);
    Response get(ID id);
    Response update(ID id, Request request);
    void delete(ID id);
    List<Response> findAll(FindAllRequest request);
}