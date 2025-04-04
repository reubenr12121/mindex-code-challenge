package com.mindex.challenge.controller;

import com.mindex.challenge.data.Compensation;
import com.mindex.challenge.service.CompensationService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

/**
 * Controller for managing Compensations
 * <p>Creates endpoints for creating, reading, and updating Compensations</p>
 */
@RestController
public class CompensationController {
    private static final Logger LOG = LoggerFactory.getLogger(CompensationController.class);

    @Autowired
    private CompensationService compensationService;

    /**
     * Creates a Compensation
     * @param compensation the Compensation
     * @return a ResponseEntity containing the compensation and an OK code or
     * an error code
     */
    @PostMapping("/compensation")
    public ResponseEntity<Compensation> create(@RequestBody Compensation compensation) {
        LOG.debug("Received compensation create request for [{}]", compensation);
        Compensation compensationSaved = compensationService.create(compensation);
        if(compensationSaved != null) {
            return new ResponseEntity<>(compensationSaved, HttpStatus.CREATED);
        }
        else {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
        }

    }

    /**
     * Reads a Compensation object
     * @param id the Compensation's ID
     * @return ResponseEntity containing the read Compensation object with an
     * OK code or a NOT_FOUND code
     */
    @GetMapping("/compensation/{id}")
    public ResponseEntity<Compensation> read(@PathVariable String id) {
        LOG.debug("Received compensation read request for id [{}]", id);
        try {
            Compensation compensation = compensationService.read(id);
            return new ResponseEntity<>(compensation, HttpStatus.OK);
        } catch(RuntimeException e) {
            LOG.error("Compensation not found for id [{}]", id);
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }
    }

    /**
     * Updates a Compensation object
     * @param id the Compensation's ID
     * @return a call to compensationService to update the compensation
     */
    @PutMapping("/compensation/{id}")
    public ResponseEntity<Compensation> update(@PathVariable String id, @RequestBody Compensation compensation) {
        LOG.debug("Received compensation update request for id [{}] and compensation [{}]", id, compensation);
        compensation.setCompensationId(id);
        Compensation updatedCompensation = compensationService.update(compensation);
        if(updatedCompensation != null) {
            return new ResponseEntity<>(updatedCompensation, HttpStatus.OK);
        }
        else {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
        }
    }
}
