package com.mindex.challenge.controller;

import com.mindex.challenge.data.Compensation;
import com.mindex.challenge.data.ReportingStructure;
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

@RestController
public class CompensationController {
    private static final Logger LOG = LoggerFactory.getLogger(CompensationController.class);

    @Autowired
    private CompensationService compensationService;

    @PostMapping("/compensation")
    public Compensation create(@RequestBody Compensation compensation) {
        LOG.debug("Received compensation create request for [{}]", compensation);

        return compensationService.create(compensation);
    }

    @GetMapping("/compensation/{id}")
    public Compensation read(@PathVariable String id) {
        LOG.debug("Received compensation read request for id [{}]", id);

        return compensationService.read(id);
    }

    @PutMapping("/compensation/{id}")
    public Compensation update(@PathVariable String id, @RequestBody Compensation compensation) {
        LOG.debug("Received compensation update request for id [{}] and compensation [{}]", id, compensation);

        compensation.setCompensationID(id);
        return compensationService.update(compensation);
    }

    /**
     * Generates a ReportingStructure using a provided compensation ID
     * @param id the compensation's ID for the desired ReportingStructure
     * @return a ResponseEntity containing the ReportingStructure if found or
     * an error code
     */
    @GetMapping("/compensation/{id}/reporting-structure")
    public ResponseEntity<ReportingStructure> reportingStructure(@PathVariable String id) {
        // log the request
        LOG.debug("Received compensation reporting structure request for id [{}]", id);
        // decided to implement this controller method with ResponseEntities
        // instead of just returning the ReportingStructure
        try{
            // grab compensation using compensationService
            // attempt to create a ReportingStructure
            Compensation compensation = compensationService.read(id);
            // count the DirectReports recursively
            int directCompensationCount = compensationService.countDirectReports(compensation);
            LOG.debug("compensation count: [{}]", directCompensationCount);
            //int directCompensationCount = compensation.getDirectReports().size();
            ReportingStructure responseEntity = new ReportingStructure(
                    compensation.getCompensationId(),
                    directCompensationCount);
            // able to make the new ReportingStructure
            return new ResponseEntity<>(responseEntity, HttpStatus.OK);
        } catch(RuntimeException e) {
            // if compensation not found handle
            LOG.error("Compensation not found for id [{}]", id);
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }
    }
}
