package com.example.bibliotecaApp.repository;

import com.example.bibliotecaApp.entity.Lending;
import com.example.bibliotecaApp.entity.LendingDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class LendingRepositoryImpl {

    @PersistenceContext
    private EntityManager entityManager;

    public Page<LendingDTO> getLending (HashMap<String, Object> conditions) {
        CriteriaBuilder cb = entityManager.getCriteriaBuilder();
        CriteriaQuery<LendingDTO> query = cb.createQuery(LendingDTO.class);
        Root<Lending> root = query.from(Lending.class);
        List<Predicate> predicates = new ArrayList<>();

        for ( Map.Entry<String,Object> field :conditions.entrySet())  {
            switch (field.getKey()) {
                case "pending":
                    if ((Boolean) field.getValue()){
                        predicates.add(cb.isNull(root.get("dateReturn")));
                    }
                    break;
                case "dni":
                    predicates.add(cb.like(root.get("userApp").get("dni"),field.getValue().toString()));
                    break;

            }
        }
        query.select(cb.construct(LendingDTO.class,
                root.get("id"),
                root.get("userApp").get("id"),
                root.get("book").get("id"),
                root.get("dateOut"),
                root.get("dateReturn"),
                root.get("returnEstimateDate"),
                cb.nullLiteral(Integer.class),
                cb.nullLiteral(Integer.class)
        )).where(predicates.toArray(new Predicate[0]));

        query.orderBy(cb.asc(root.get("dateOut")));

        TypedQuery<LendingDTO> query1 = entityManager.createQuery(query);
        Long total = getCountCategories(conditions);
        if (conditions.containsKey("pageOffset") && conditions.containsKey("pageSize")) {
            query1.setFirstResult((Integer) conditions.get("pageOffset"));
            query1.setMaxResults((Integer) conditions.get("pageSize"));
        }
        return new PageImpl<>(query1.getResultList(), Pageable.unpaged(),total);
    }

    public Long getCountCategories (HashMap<String, Object> conditions) {
        CriteriaBuilder cb = entityManager.getCriteriaBuilder();
        CriteriaQuery<Long> query = cb.createQuery(Long.class);
        Root<Lending> root = query.from(Lending.class);
        List<Predicate> predicates = new ArrayList<>();

        for ( Map.Entry<String,Object> field :conditions.entrySet())  {
            switch (field.getKey()) {
                case "pending":
                    predicates.add(cb.isNull(root.get("dateReturn")));
                    break;
                case "dni":
                    predicates.add(cb.like(root.get("userApp").get("dni"),field.getValue().toString()));
                    break;

            }
        }
        query.select(cb.countDistinct(root)).where(predicates.toArray(new Predicate[0]));
        return entityManager.createQuery(query).getSingleResult();
    }
}
