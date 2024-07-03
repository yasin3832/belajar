package car_rent.utils.specification;

import car_rent.model.Car;
import jakarta.persistence.criteria.Predicate;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

import java.util.ArrayList;
import java.util.List;

public class CarSpecification {
    public static Specification<Car> hasName(String name){
        return (root, query, criteriaBuilder) ->
                criteriaBuilder.like(root.get("name"), "%" + name + "%");
    }
    public static Specification<Car> hasAvailable(Boolean available){
        return (root, query, criteriaBuilder) ->
                criteriaBuilder.equal(root.get("available"), available);
    }

    public static Specification<Car> getCarSpecification(String name, Boolean available){
        return (root, query, criteriaBuilder) -> {
            List<Predicate> predicates = new ArrayList<>();

            if (name != null && !name.isEmpty()){
                predicates.add(criteriaBuilder.like(root.get("name"), "%" + name + "%"));
            }


            if (available != null){
                predicates.add(criteriaBuilder.equal(root.get("available"), available));
            }

            return criteriaBuilder.and(predicates.toArray(new Predicate[0]));
        };
    }
}
