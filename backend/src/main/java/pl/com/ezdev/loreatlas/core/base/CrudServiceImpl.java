package pl.com.ezdev.loreatlas.core.base;

import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import pl.com.ezdev.loreatlas.core.exception.EntityNotFoundException;
import pl.com.ezdev.loreatlas.modules.user.domain.User;

import java.util.List;

public abstract class CrudServiceImpl<T extends BaseEntity, ID, Request, Response, FindAllRequest>
        implements CrudService<T, ID, Request, Response, FindAllRequest> {

    protected abstract JpaRepository<T, ID> getRepository();

    protected abstract T mapRequestToEntity(Request request);

    protected abstract void updateEntityFromRequest(Request request, T entity);

    protected abstract Response mapEntityToResponse(T entity);

    protected abstract Specification<T> buildSpecification(FindAllRequest request);

    protected abstract Class<T> getEntityClass();

    @Override
    public Response add(Request request) {
        T entity = mapRequestToEntity(request);
        entity.setCreatedBy(getCurrentUser());
        entity.setModifiedBy(getCurrentUser());
        return mapEntityToResponse(getRepository().save(entity));
    }

    @Override
    public Response get(ID id) {
        T entity = getRepository().findById(id)
                .orElseThrow(() -> new EntityNotFoundException(getEntityClass(), "id", id));
        return mapEntityToResponse(entity);
    }

    @Override
    public Response update(ID id, Request request) {
        T entity = getRepository().findById(id)
                .orElseThrow(() -> new EntityNotFoundException(getEntityClass(), "id", id));
        updateEntityFromRequest(request, entity);
        entity.setModifiedBy(getCurrentUser());
        return mapEntityToResponse(getRepository().save(entity));
    }

    @Override
    public void delete(ID id) {
        getRepository().deleteById(id);
    }

    @Override
    public List<Response> findAll(FindAllRequest request) {
        return ((JpaSpecificationExecutor<T>) getRepository())
                .findAll(buildSpecification(request)).stream()
                .map(this::mapEntityToResponse)
                .toList();
    }

    private User getCurrentUser() {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        return auth != null && auth.getPrincipal() instanceof User user ? user : null;
    }
}
