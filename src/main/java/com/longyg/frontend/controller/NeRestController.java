package com.longyg.frontend.controller;

import com.longyg.frontend.model.ne.NeRelease;
import com.longyg.frontend.model.ne.NeType;
import com.longyg.frontend.service.NeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.logging.Logger;

@RestController
public class NeRestController {
    @Autowired
    private NeService neService;

    //////////////////////////////////////////////////////////////
    // NE Type
    @GetMapping("/api/netype")
    public List<NeType> getAllNeTypes() {
        return neService.findAllNeTypes();
    }

    @PostMapping("/api/netype")
    public NeType addNeType(@RequestBody NeType neType) {
        return neService.saveNeType(neType);
    }

    @GetMapping(value = "/api/netype/{id}")
    public ResponseEntity<NeType> getNeType(@PathVariable("id") String id) {
        NeType neType = neService.findNeType(id);
        if (null != neType) {
            return new ResponseEntity<>(neType, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PutMapping(value = "/api/netype/{id}")
    public ResponseEntity<NeType> updateNeType(@PathVariable("id") String id, @RequestBody NeType neType) {
        NeType neTypeData = neService.findNeType(id);
        if (null == neTypeData) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        neTypeData.setName(neType.getName());
        neTypeData.setPresentation(neType.getPresentation());
        neTypeData.setAgentClass(neType.getAgentClass());
        neTypeData.setDescription(neType.getDescription());

        NeType updatedNeType = neService.saveNeType(neTypeData);

        return new ResponseEntity<>(updatedNeType, HttpStatus.OK);
    }

    @DeleteMapping(value = "/api/netype/{id}")
    public void removeNeType(@PathVariable("id") String id) {
        neService.deleteNeType(id);
    }

    @PostMapping(value = "/api/netype/delete")
    public void removeNeTypes(@RequestBody List<String> ids) {
        neService.deleteNeTypes(ids);
    }

    //////////////////////////////////////////////////////////////
    // NE Release
    @GetMapping("/api/nerel")
    public List<NeRelease> getAllNeReleases() {
        return neService.findAllReleases();
    }

    @PostMapping("/api/nerel")
    public NeRelease addNeRelease(@RequestBody NeRelease entity) {
        return neService.saveRelease(entity);
    }

    @GetMapping(value = "/api/nerel/{id}")
    public ResponseEntity<NeRelease> getNeRelease(@PathVariable("id") String id) {
        NeRelease entity = neService.findRelease(id);
        if (null != entity) {
            return new ResponseEntity<>(entity, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PutMapping(value = "/api/nerel/{id}")
    public ResponseEntity<NeRelease> updateNeRelease(@PathVariable("id") String id, @RequestBody NeRelease entity) {
        NeRelease entityData = neService.findRelease(id);
        if (null == entityData) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        entityData.setType(entity.getType());
        entityData.setVersion(entity.getVersion());
        entityData.setRemarks(entity.getRemarks());

        NeRelease updatedEntity = neService.saveRelease(entityData);

        return new ResponseEntity<>(updatedEntity, HttpStatus.OK);
    }

    @DeleteMapping(value = "/api/nerel/{id}")
    public void removeNeRelease(@PathVariable("id") String id) {
        neService.deleteRelease(id);
    }

    @PostMapping(value = "/api/nerel/delete")
    public void removeNeReleases(@RequestBody List<String> ids) {
        neService.deleteReleases(ids);
    }
}
