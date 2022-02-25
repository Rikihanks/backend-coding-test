package com.example.demo.task.specification;

import com.example.demo.task.entities.TaskEntity;
import com.example.demo.task.entities.TaskOrderableFields;
import com.example.demo.task.entities.TaskPriority;
import org.springframework.data.jpa.domain.Specification;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

public class TaskSpecification implements Specification<TaskEntity> {

    private SearchCriteria criteria;

    public TaskSpecification(final SearchCriteria criteria) {
        super();
        this.criteria = criteria;
    }

    public SearchCriteria getCriteria() {
        return criteria;
    }

    @Override
    public Predicate toPredicate(Root<TaskEntity> root, CriteriaQuery<?> criteriaQuery, CriteriaBuilder criteriaBuilder) {
        if (criteria.getOperation().equals(SearchOperation.getSimpleOperation('>'))) {
            return criteriaBuilder.greaterThan(
                    root.<String> get(criteria.getKey()), criteria.getValue().toString());
        }
        else if (criteria.getOperation().equals(SearchOperation.getSimpleOperation('<'))) {
            return criteriaBuilder.lessThan(
                    root.<String> get(criteria.getKey()), criteria.getValue().toString());
        }
        else if (criteria.getOperation().equals(SearchOperation.getSimpleOperation(':'))) {
            var key = criteria.getKey();
            switch (TaskOrderableFields.valueOf(key.toUpperCase())) {
                case PRIORITY:
                    return criteriaBuilder.equal(root.get(criteria.getKey()), TaskPriority.valueOf(criteria.getValue().toString().toUpperCase()));
                case COMPLETED:
                    return criteriaBuilder.equal(root.get(criteria.getKey()), Boolean.valueOf(criteria.getValue().toString()));
                case ID:
                    return criteriaBuilder.equal(root.get(criteria.getKey()), criteria.getValue());
                default:
                    return criteriaBuilder.like(root.<String>get(criteria.getKey()), "%" + criteria.getValue() + "%");
            }
        }
        return null;
    }

}
