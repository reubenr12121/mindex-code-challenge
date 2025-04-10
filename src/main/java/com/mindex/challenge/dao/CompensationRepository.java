package com.mindex.challenge.dao;

import com.mindex.challenge.data.Compensation;
import org.springframework.stereotype.Repository;
import org.springframework.data.mongodb.repository.MongoRepository;

/**
 * Repository for Compensation objects
 */
@Repository
public interface CompensationRepository extends MongoRepository<Compensation, String> {
    Compensation findByCompensationId(String compensationId);
    void deleteByCompensationId(String compensationId);
}
