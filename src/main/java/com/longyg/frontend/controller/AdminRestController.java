package com.longyg.frontend.controller;

import com.longyg.frontend.model.config.InterfaceObject;
import com.longyg.frontend.model.ne.NeType;
import com.longyg.frontend.service.ConfigService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.logging.Logger;

@RestController
public class AdminRestController {
    @Autowired
    private ConfigService configService;

    //////////////////////////////////////////////////////////////
    // NE Type
    @GetMapping("/api/ifo")
    public List<InterfaceObject> getAllInterfaces() {
        return configService.findInterfaces();
    }

    @PostMapping("/api/ifo")
    public InterfaceObject addInterface(@RequestBody InterfaceObject ifo) {
        return configService.saveInterface(ifo);
    }

    @GetMapping(value = "/api/ifo/{id}")
    public ResponseEntity<InterfaceObject> getIfo(@PathVariable("id") String id) {
        InterfaceObject entity = configService.findInterface(id);
        if (null != entity) {
            return new ResponseEntity<>(entity, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PutMapping(value = "/api/ifo/{id}")
    public ResponseEntity<InterfaceObject> updateInterface(@PathVariable("id") String id, @RequestBody InterfaceObject entity) {
        InterfaceObject entityData = configService.findInterface(id);
        if (null == entityData) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        entityData.setName(entity.getName());
        entityData.setPresentation(entity.getPresentation());
        entityData.setDescription(entity.getDescription());

        InterfaceObject updatedEntity = configService.saveInterface(entityData);

        return new ResponseEntity<>(updatedEntity, HttpStatus.OK);
    }

    @DeleteMapping(value = "/api/ifo/{id}")
    public void removeInterface(@PathVariable("id") String id) {
        configService.deleteInterface(id);
    }

    @PostMapping(value = "/api/ifo/delete")
    public void removeInterfaces(@RequestBody List<String> ids) {
        configService.deleteInterfaces(ids);
    }
}
