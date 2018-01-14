package com.longyg.frontend.controller;

import com.longyg.frontend.model.config.AlarmObject;
import com.longyg.frontend.model.config.InterfaceObject;
import com.longyg.frontend.model.config.ParentObject;
import com.longyg.frontend.service.ConfigService;
import javafx.scene.Parent;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class AdminRestController {
    @Autowired
    private ConfigService configService;

    //////////////////////////////////////////////////////////////
    // Interface Object Rest API
    @GetMapping("/api/ifo")
    public List<InterfaceObject> getAllInterfaces() {
        return configService.findInterfaces();
    }

    @PostMapping("/api/ifo")
    public InterfaceObject addInterface(@RequestBody InterfaceObject entity) {
        return configService.saveInterface(entity);
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

    //////////////////////////////////////////////////////////////
    // Parent Object Rest API
    @GetMapping("/api/pto")
    public List<ParentObject> getAllParentObjects() {
        return configService.findParentObjects();
    }

    @PostMapping("/api/pto")
    public ParentObject addParentObject(@RequestBody ParentObject entity) {
        return configService.saveParentObject(entity);
    }

    @GetMapping(value = "/api/pto/{id}")
    public ResponseEntity<ParentObject> getPto(@PathVariable("id") String id) {
        ParentObject entity = configService.findParentObject(id);
        if (null != entity) {
            return new ResponseEntity<>(entity, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PutMapping(value = "/api/pto/{id}")
    public ResponseEntity<ParentObject> updateParentObject(@PathVariable("id") String id, @RequestBody ParentObject entity) {
        ParentObject entityData = configService.findParentObject(id);
        if (null == entityData) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        entityData.setName(entity.getName());
        entityData.setPresentation(entity.getPresentation());
        entityData.setNameInOMeS(entity.getNameInOMeS());
        entityData.setIsTransient(entity.getIsTransient());

        ParentObject updatedEntity = configService.saveParentObject(entityData);

        return new ResponseEntity<>(updatedEntity, HttpStatus.OK);
    }

    @DeleteMapping(value = "/api/pto/{id}")
    public void removeParentObject(@PathVariable("id") String id) {
        configService.deleteParentObject(id);
    }

    @PostMapping(value = "/api/pto/delete")
    public void removeParentObjects(@RequestBody List<String> ids) {
        configService.deleteParentObjects(ids);
    }


    //////////////////////////////////////////////////////////////
    // Alarm Object Rest API
    @GetMapping("/api/ao")
    public List<AlarmObject> getAllAlarmObjects() {
        return configService.findAlarmObjects();
    }

    @PostMapping("/api/ao")
    public AlarmObject addAlarmObject(@RequestBody AlarmObject entity) {
        return configService.saveAlarmObject(entity);
    }

    @GetMapping(value = "/api/ao/{id}")
    public ResponseEntity<AlarmObject> getAlarmObject(@PathVariable("id") String id) {
        AlarmObject entity = configService.findAlarmObject(id);
        if (null != entity) {
            return new ResponseEntity<>(entity, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PutMapping(value = "/api/ao/{id}")
    public ResponseEntity<AlarmObject> updateAlarmObject(@PathVariable("id") String id, @RequestBody AlarmObject entity) {
        AlarmObject entityData = configService.findAlarmObject(id);
        if (null == entityData) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        entityData.setName(entity.getName());
        entityData.setPresentation(entity.getPresentation());
        entityData.setDescription(entity.getDescription());

        AlarmObject updatedEntity = configService.saveAlarmObject(entityData);

        return new ResponseEntity<>(updatedEntity, HttpStatus.OK);
    }

    @DeleteMapping(value = "/api/ao/{id}")
    public void removeAlarmObject(@PathVariable("id") String id) {
        configService.deleteAlarmObject(id);
    }

    @PostMapping(value = "/api/ao/delete")
    public void removeAlarmObjects(@RequestBody List<String> ids) {
        configService.deleteAlarmObjects(ids);
    }
}
