package org.example.ecommerce.domain.users.repositories.specification;

import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.criteria.Root;
import org.example.ecommerce.domain.users.entities.User;
import org.springframework.data.jpa.domain.Specification;

public class UserSpecification implements Specification<User> {
    private final SpecSearchCriteria criteria;

    public UserSpecification(SpecSearchCriteria specSearchCriteria) {this.criteria = specSearchCriteria;}

    @Override
    public Predicate toPredicate(Root<User> root, CriteriaQuery<?> query, CriteriaBuilder criteriaBuilder) {
        return switch (criteria.getOperation()) {
            case EQUALITY -> criteriaBuilder.equal(root.get(criteria.getKey()), criteria.getValue());
            case NEGATION -> criteriaBuilder.notEqual(root.get(criteria.getKey()), criteria.getValue());
            case GREATER_THAN -> criteriaBuilder.greaterThan(
                    root.get(criteria.getKey()),
                    criteria.getValue()
                            .toString()
            );
            case LESS_THAN -> criteriaBuilder.lessThan(
                    root.get(criteria.getKey()),
                    criteria.getValue()
                            .toString()
            );
            case LIKE -> criteriaBuilder.like(
                    root.get(criteria.getKey()),
                    "%" + criteria.getValue()
                                  .toString() + "%"
            );
            case STARTS_WITH -> criteriaBuilder.like(root.get(criteria.getKey()), criteria.getValue() + "%");
            case ENDS_WITH -> criteriaBuilder.like(root.get(criteria.getKey()), "%" + criteria.getValue());
            case CONTAINS -> criteriaBuilder.like(root.get(criteria.getKey()), "%" + criteria.getValue() + "%");
            default -> throw new IllegalArgumentException("Invalid operation");
        };
    }
}
