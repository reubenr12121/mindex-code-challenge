package com.mindex.challenge.service.impl;

import com.mindex.challenge.dao.CompensationRepository;
import com.mindex.challenge.data.Compensation;
import com.mindex.challenge.service.CompensationService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.UUID;

/**
 * Service tier for compensation
 */
@Service
public class CompensationServiceImpl implements CompensationService {

    private static final Logger LOG = LoggerFactory.getLogger(CompensationServiceImpl.class);

    @Autowired
    private CompensationRepository compensationRepository;

    /**
     * create method calls repository to create insert created compensation
     * @param compensation the compensation being inserted
     * @return the compensation
     */
    @Override
    public Compensation create(Compensation compensation) {
        LOG.debug("Creating compensation [{}]", compensation);
        compensation.setCompensationId(UUID.randomUUID().toString());
        compensationRepository.insert(compensation);
        return compensation;
    }

    /**
     * read method calls repository to grab compensation from id
     * @param id
     * @return the compensation
     */
    @Override
    public Compensation read(String id) {
        LOG.debug("Reading compensation with id [{}]", id);
        
        Compensation compensation = compensationRepository.findByCompensationId(id);

        if (compensation == null) {
            throw new RuntimeException("Invalid compensationID: " + id);
        }

        return compensation;
    }

    /**
     * updates a compensation by calling the repository with a new compensation
     * @param compensation the new compensation
     * @return the compensation
     */
    @Override
    public Compensation update(Compensation compensation) {
        compensationRepository.deleteByCompensationId(compensation.getCompensationId());
        LOG.debug("Updating compensation [{}]", compensation);
        compensationRepository.insert(compensation);
        return compensation;
    }
}
