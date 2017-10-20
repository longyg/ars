package com.longyg.backend.adaptation.topology;

import com.longyg.backend.adaptation.main.AdaptationRepository;
import com.longyg.backend.adaptation.pm.*;
import com.longyg.frontend.model.ars.ArsConfig;
import com.longyg.frontend.model.config.GlobalObject;
import org.apache.log4j.Logger;

import java.util.*;

public class PmbObjectRepository {
    private static final Logger LOG = Logger.getLogger(PmbObjectRepository.class);

    // key: adaptation Id
    // value: all releases' object of specific adaptation id
    private Map<String, List<PmbObject>> allReleaseObjects = new HashMap<>();

    private AdaptationRepository adaptationRepository;

    private ArsConfig config;

    private List<GlobalObject> globalObjects;

    public Map<String, List<PmbObject>> getAllReleaseObjects() {
        return allReleaseObjects;
    }

    public void setAllReleaseObjects(Map<String, List<PmbObject>> allReleaseObjects) {
        this.allReleaseObjects = allReleaseObjects;
    }

    public PmbObjectRepository(AdaptationRepository adaptationRepository, ArsConfig config, List<GlobalObject> globalObjects) {
        this.adaptationRepository = adaptationRepository;
        this.config = config;
        this.globalObjects = globalObjects;
    }

    public void init() throws Exception {
        createPmbObjectFromPmb();
        addParentObject();
        setObjectLoad();
    }

    private void setObjectLoad() {
        // TODO

    }

    private void addParentObject() throws Exception {
        Map<String, List<PmbObject>> rootObjectMap = getRootObjects();
        for (Map.Entry<String, List<PmbObject>> entry : allReleaseObjects.entrySet()) {
            String adaptationId = entry.getKey();
            String adapId = adaptationId.replaceAll("\\.", "_");
            if (config.getParents().containsKey(adapId)) {
                String parent =  config.getParents().get(adapId);
                if (null != parent) {
                    List<PmbObject> rootObjects = rootObjectMap.get(adaptationId);

                    String lastClass = getLastClass(parent);
                    GlobalObject globalObject = findGlobalObjectByName(lastClass);
                    if (null == globalObject) {
                        throw new Exception("Parent Object class '" + lastClass + "' is not defined.");
                    }
                    PmbObject pmbObject = new PmbObject();
                    pmbObject.setName(lastClass);
                    pmbObject.setNameInOmes(globalObject.getNameInOMeS());
                    pmbObject.setPresentation(globalObject.getPresentation());
                    pmbObject.setTransient(globalObject.isTransient());

                    addPmbObjectToList(adaptationId, pmbObject);

                    String parentHierarchy = getParentHierarchy(parent);

                    for (PmbObject rootObject : rootObjects) {
                        rootObject.addParentObject(pmbObject);
                        pmbObject.addChildObject(rootObject);

                        createPmbObjectsFromParentHierarchy(adaptationId, pmbObject, parentHierarchy);
                    }
                }
            }
        }
    }

    private GlobalObject findGlobalObjectByName(String name) {
        for (GlobalObject globalObject : globalObjects) {
            if (globalObject.getName().equals(name)) {
                return globalObject;
            }
        }
        return null;
    }

    private void addPmbObjectToList(String adaptationId, PmbObject pmbObject) {
        if (allReleaseObjects.containsKey(adaptationId)) {
            List<PmbObject> pmbObjects = allReleaseObjects.get(adaptationId);
            if (null != pmbObjects) {
                if (!pmbObjects.contains(pmbObject)) {
                    pmbObjects.add(pmbObject);
                }
            }
        }
    }

    private void createPmbObjectsFromParentHierarchy(String adaptationId,
                                                     PmbObject childObject,
                                                     String hierarchy) throws Exception{
        if (null == hierarchy || "".equals(hierarchy)) {
            return;
        }
        String lastClass = getLastClass(hierarchy);

        GlobalObject globalObject = findGlobalObjectByName(lastClass);
        if (null == globalObject) {
            throw new Exception("Parent Object class '" + lastClass + "' is not defined.");
        }

        PmbObject pmbObject = new PmbObject();
        pmbObject.setName(lastClass);
        pmbObject.setNameInOmes(globalObject.getNameInOMeS());
        pmbObject.setPresentation(globalObject.getPresentation());
        pmbObject.setTransient(globalObject.isTransient());

        if (null != childObject) {
            pmbObject.addChildObject(childObject);
            childObject.addParentObject(pmbObject);
        }
        addPmbObjectToList(adaptationId, pmbObject);

        String parentHierarchy = getParentHierarchy(hierarchy);
        createPmbObjectsFromParentHierarchy(adaptationId, pmbObject, parentHierarchy);
    }

    public Map<String, List<PmbObject>> getRootObjects() {
        Map<String, List<PmbObject>> rootObjectMap = new HashMap<String, List<PmbObject>>();
        for (Map.Entry<String, List<PmbObject>> entry : allReleaseObjects.entrySet()) {
            String adaptationId = entry.getKey();
            List<PmbObject> rootObjects = new ArrayList<>();
            for (PmbObject pmbObject : entry.getValue()) {
                if (pmbObject.getParentObjects().size() < 1) {
                    rootObjects.add(pmbObject);
                }
            }
            rootObjectMap.put(adaptationId, rootObjects);
        }
        return rootObjectMap;
    }

    private void createPmbObjectFromPmb() throws Exception {
        Map<String, List<PmAdaptation>> pmAdaptationsMap = adaptationRepository.getPmAdaptations();

        for (Map.Entry<String, List<PmAdaptation>> entry : pmAdaptationsMap.entrySet()) {
            String adaptationId = entry.getKey();
            List<PmAdaptation> pmAdaptations = entry.getValue();

            for (PmAdaptation pmAdaptation : pmAdaptations) {
                List<ObjectClass> pmClasses = pmAdaptation.getObjectClasses();
                for (Measurement measurement : pmAdaptation.getMeasurements()) {
                    for (MeasuredTarget measuredTarget : measurement.getMeasuredTargets()) {
                        String dimension = measuredTarget.getDimension();
                        for (String hierarchy : measuredTarget.getHierarchies()) {
                            hierarchy = format(hierarchy);
                            createPmObjectsFromHierarchy(adaptationId, hierarchy, null, pmAdaptation.getAdapRelease(), dimension, pmClasses);
                        }
                    }
                }
            }
        }
    }

    private void addToList(String adaptationId, PmbObject pmbObject) {
        if (allReleaseObjects.containsKey(adaptationId)) {
            List<PmbObject> pmbObjects = allReleaseObjects.get(adaptationId);
            if (!pmbObjects.contains(pmbObject)) {
                pmbObjects.add(pmbObject);
            }
        } else {
            List<PmbObject> pmbObjects = new ArrayList<>();
            pmbObjects.add(pmbObject);
            allReleaseObjects.put(adaptationId, pmbObjects);
        }
    }

    private String getLastClass(String hierarchy) {
        String lastClass;
        if (hierarchy.lastIndexOf("/") == -1) {
            lastClass = hierarchy;
        } else {
            lastClass = hierarchy.substring(hierarchy.lastIndexOf("/") + 1, hierarchy.length());
        }
        return lastClass;
    }

    private void createPmObjectsFromHierarchy(String adaptationId,
                                              String hierarchy,
                                              PmbObject parentObj,
                                              String version,
                                              String dimension,
                                              List<ObjectClass> pmClasses) throws Exception {
        if (null == hierarchy || "".equals(hierarchy)) {
            return;
        }
        String firstClass = getFirstClass(hierarchy);
        String childHierarchy = getChildHierarchy(hierarchy);

        PmbObject pmbObject = findOrCreatePmObject(adaptationId, firstClass, pmClasses, version, dimension);
        addToList(adaptationId, pmbObject);
        if (null != parentObj) {
            parentObj.addChildObject(pmbObject);
            pmbObject.addParentObject(parentObj);
        }
        if (null == childHierarchy || "".equals(childHierarchy)) {
            pmbObject.setMeasuredObject(true);
        }
        createPmObjectsFromHierarchy(adaptationId, childHierarchy, pmbObject, version, dimension, pmClasses);
    }

    private static String getFirstClass(String hierarchy) {
        hierarchy = format(hierarchy);
        String firstClass;
        if (hierarchy.indexOf("/") == -1) {
            firstClass = hierarchy;
        } else {
            firstClass = hierarchy.substring(0, hierarchy.indexOf("/"));
        }
        return firstClass;
    }

    private static String getChildHierarchy(String hierarchy) {
        hierarchy = format(hierarchy);
        String childHierarchy = null;
        if (hierarchy.indexOf("/") > -1) {
            childHierarchy = hierarchy.substring(hierarchy.indexOf("/"), hierarchy.length());
        }
        return childHierarchy;
    }

    private static String getParentHierarchy(String hierarchy) {
        hierarchy = format(hierarchy);
        String parentHierarchy = null;
        if (hierarchy.lastIndexOf("/") > -1) {
            parentHierarchy = hierarchy.substring(0, hierarchy.lastIndexOf("/"));
        }
        return parentHierarchy;
    }

    private PmbObject findOrCreatePmObject(String adaptationId,
                                           String clazz,
                                           List<ObjectClass> objectClasses,
                                           String version,
                                           String dimension) throws Exception {
        PmbObject pmbObject = findPmObjectByName(adaptationId, clazz);
        if (pmbObject == null) {
            pmbObject = new PmbObject();
            ObjectClass classDef = findPmClassDefinition(clazz, objectClasses);
            if (null == classDef) {
                LOG.error("Can't find PM class: " + clazz);
                throw new Exception("Can't find PM class: " + clazz + ". Please check your adaptation");
            }
            pmbObject.setName(classDef.getName());
            pmbObject.setNameInOmes(classDef.getNameInOmes());
            pmbObject.setPresentation(classDef.getPresentation());
            pmbObject.setTransient(classDef.isTransient());
        }
        pmbObject.addSupportedVersion(version);
        pmbObject.addDimension(dimension);
        return pmbObject;
    }



    private ObjectClass findPmClassDefinition(String clazz, List<ObjectClass> pmClasses) {
        for (ObjectClass objectClass : pmClasses) {
            if (clazz.equals(objectClass.getName())) {
                return objectClass;
            }
        }
        return null;
    }

    private PmbObject findPmObjectByName(String adaptationId, String className) {
        if (allReleaseObjects.containsKey(adaptationId)) {
            List<PmbObject> pmbObjects = allReleaseObjects.get(adaptationId);
            for (PmbObject pmbObject : pmbObjects) {
                if (pmbObject.getName().equals(className)) {
                    return pmbObject;
                }
            }
        }
        return null;
    }

    private static String format(String hierarchy) {
        if (hierarchy.lastIndexOf("/") == hierarchy.length() - 1) {
            hierarchy = hierarchy.substring(0, hierarchy.lastIndexOf("/"));
        }
        if (hierarchy.indexOf("/") == 0) {
            hierarchy = hierarchy.substring(1, hierarchy.length());
        }
        return hierarchy;
    }
}
