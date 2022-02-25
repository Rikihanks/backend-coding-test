package com.example.demo.task.specification;

import com.example.demo.task.entities.TaskEntity;
import org.springframework.data.jpa.domain.Specification;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class TaskSpecificationBuilder {

    private final List<SearchCriteria> params;

    public TaskSpecificationBuilder() {
        params = new ArrayList<SearchCriteria>();
    }

    public TaskSpecificationBuilder with(String key, String operation, Object value) {
        params.add(new SearchCriteria(key, SearchOperation.getSimpleOperation(operation.charAt(0)), value, true));
        return this;
    }

    public Specification<TaskEntity> build() {
        if (params.size() == 0) {
            return null;
        }

        List<Specification> specs = params.stream()
                .map(TaskSpecification::new)
                .collect(Collectors.toList());

        Specification result = specs.get(0);

        for (int i = 1; i < params.size(); i++) {
            result = params.get(i)
                    .isOrPredicate()
                    ? Specification.where(result)
                    .or(specs.get(i))
                    : Specification.where(result)
                    .and(specs.get(i));
        }
        return result;
    }
}
