package pl.com.ezdev.loreatlas.core.specification;

import org.springframework.data.jpa.domain.Specification;


public class SpecificationBuilder<T> {

    private Specification<T> specification = Specification.allOf();

    public SpecificationBuilder<T> addFilter(String column, Object filterValue) {
        if (filterValue != null) {
            Specification<T> newFilter;

            if (filterValue instanceof Boolean boolValue) {
                newFilter = (root, query, builder) ->
                        builder.equal(root.get(column), boolValue);
            } else if (filterValue instanceof Long longValue) {
                newFilter = (root, query, builder) ->
                        builder.equal(root.get(column), longValue);
            } else if (filterValue instanceof String strValue) {
                newFilter = (root, query, builder) ->
                        builder.like(root.get(column), "%" + strValue + "%");
            } else {
                throw new IllegalArgumentException("Unsupported filter type: " + filterValue.getClass());
            }

            specification = specification.and(newFilter);
        }
        return this;
    }

    public Specification<T> build() {
        return specification;
    }
}
