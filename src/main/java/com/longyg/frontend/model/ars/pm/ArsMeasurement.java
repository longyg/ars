package com.longyg.frontend.model.ars.pm;

import java.util.ArrayList;
import java.util.List;

public class ArsMeasurement {
    private String name;
    private String nameInOmes;
    private String measuredObject;
    private boolean isSupported;
    private List<String> supportedPreviousVersions = new ArrayList<>();
    private String supportedOtherReleases;
    private List<String> dimensions = new ArrayList<>();
    private String dimension;
    private int avgPerNet;
    private int maxPerNet;
    private int maxPerNe;
    private int counterNumber;
    private int counterNumberOfLastVersion;
    private int delta;
    private String aggObject;
    private String timeAgg = "15min, 30min, Hour,Day,Week,Month";
    private String bh = "";
    private int active = 1;
    private int defaultInterval = 15;
    private int minimalInterval = 1;
    private int storageDays = 14;
    private int bytesPerCounter = 4;
    private int mphPerNE;
    private int cphPerNE;
    private int chaPerNE;
    private int dhaPerNe;
    private int maxMph;
    private int maxCph;
    private String measGroup;
    private int dbRrPerNe;
    private int dbRcPerNe;
    private int msPerNe;
    private int dbMaxRows;
    private int dbMaxCtrs;
    private int maxMs;
    private int totalBytesPerInterval;
    private int totalSizePerHour;
    private int tableSizePerDay;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getNameInOmes() {
        return nameInOmes;
    }

    public void setNameInOmes(String nameInOmes) {
        this.nameInOmes = nameInOmes;
    }

    public String getMeasuredObject() {
        return measuredObject;
    }

    public void setMeasuredObject(String measuredObject) {
        this.measuredObject = measuredObject;
    }

    public boolean isSupported() {
        return isSupported;
    }

    public void setSupported(boolean supported) {
        isSupported = supported;
    }

    public List<String> getSupportedPreviousVersions() {
        return supportedPreviousVersions;
    }

    public void setSupportedPreviousVersions(List<String> supportedPreviousVersions) {
        this.supportedPreviousVersions = supportedPreviousVersions;
    }

    public String getSupportedOtherReleases() {
        if (null == supportedPreviousVersions || supportedPreviousVersions.size() < 1) {
            return "";
        }
        StringBuilder sb = new StringBuilder();
        for (String version : supportedPreviousVersions) {
            sb.append(version).append(", ");
        }
        sb.deleteCharAt(sb.length() - 1);
        sb.deleteCharAt(sb.length() - 1);
        this.supportedOtherReleases = sb.toString();
        return supportedOtherReleases;
    }

    public List<String> getDimensions() {
        return dimensions;
    }

    public void setDimensions(List<String> dimensions) {
        this.dimensions = dimensions;
    }

    public String getDimension() {
        if (null == dimensions || dimensions.size() < 1) {
            return "";
        }
        StringBuilder sb = new StringBuilder();
        for (String version : dimensions) {
            sb.append(version).append(", ");
        }
        sb.deleteCharAt(sb.length() - 1);
        sb.deleteCharAt(sb.length() - 1);
        this.dimension = sb.toString();
        return dimension;
    }

    public int getAvgPerNet() {
        return avgPerNet;
    }

    public void setAvgPerNet(int avgPerNet) {
        this.avgPerNet = avgPerNet;
    }

    public int getMaxPerNet() {
        return maxPerNet;
    }

    public void setMaxPerNet(int maxPerNet) {
        this.maxPerNet = maxPerNet;
    }

    public int getMaxPerNe() {
        return maxPerNe;
    }

    public void setMaxPerNe(int maxPerNe) {
        this.maxPerNe = maxPerNe;
    }

    public int getCounterNumber() {
        return counterNumber;
    }

    public void setCounterNumber(int counterNumber) {
        this.counterNumber = counterNumber;
    }

    public int getCounterNumberOfLastVersion() {
        return counterNumberOfLastVersion;
    }

    public void setCounterNumberOfLastVersion(int counterNumberOfLastVersion) {
        this.counterNumberOfLastVersion = counterNumberOfLastVersion;
    }

    public int getDelta() {
        return delta;
    }

    public void setDelta(int delta) {
        this.delta = delta;
    }

    public String getAggObject() {
        return aggObject;
    }

    public void setAggObject(String aggObject) {
        this.aggObject = aggObject;
    }

    public String getTimeAgg() {
        return timeAgg;
    }

    public void setTimeAgg(String timeAgg) {
        this.timeAgg = timeAgg;
    }

    public String getBh() {
        return bh;
    }

    public void setBh(String bh) {
        this.bh = bh;
    }

    public int getActive() {
        return active;
    }

    public void setActive(int active) {
        this.active = active;
    }

    public int getDefaultInterval() {
        return defaultInterval;
    }

    public void setDefaultInterval(int defaultInterval) {
        this.defaultInterval = defaultInterval;
    }

    public int getMinimalInterval() {
        return minimalInterval;
    }

    public void setMinimalInterval(int minimalInterval) {
        this.minimalInterval = minimalInterval;
    }

    public int getStorageDays() {
        return storageDays;
    }

    public void setStorageDays(int storageDays) {
        this.storageDays = storageDays;
    }

    public int getBytesPerCounter() {
        return bytesPerCounter;
    }

    public void setBytesPerCounter(int bytesPerCounter) {
        this.bytesPerCounter = bytesPerCounter;
    }

    public int getMphPerNE() {
        return mphPerNE;
    }

    public void setMphPerNE(int mphPerNE) {
        this.mphPerNE = mphPerNE;
    }

    public int getCphPerNE() {
        return cphPerNE;
    }

    public void setCphPerNE(int cphPerNE) {
        this.cphPerNE = cphPerNE;
    }

    public int getChaPerNE() {
        return chaPerNE;
    }

    public void setChaPerNE(int chaPerNE) {
        this.chaPerNE = chaPerNE;
    }

    public int getDhaPerNe() {
        return dhaPerNe;
    }

    public void setDhaPerNe(int dhaPerNe) {
        this.dhaPerNe = dhaPerNe;
    }

    public int getMaxMph() {
        return maxMph;
    }

    public void setMaxMph(int maxMph) {
        this.maxMph = maxMph;
    }

    public int getMaxCph() {
        return maxCph;
    }

    public void setMaxCph(int maxCph) {
        this.maxCph = maxCph;
    }

    public String getMeasGroup() {
        return measGroup;
    }

    public void setMeasGroup(String measGroup) {
        this.measGroup = measGroup;
    }

    public int getDbRrPerNe() {
        return dbRrPerNe;
    }

    public void setDbRrPerNe(int dbRrPerNe) {
        this.dbRrPerNe = dbRrPerNe;
    }

    public int getDbRcPerNe() {
        return dbRcPerNe;
    }

    public void setDbRcPerNe(int dbRcPerNe) {
        this.dbRcPerNe = dbRcPerNe;
    }

    public int getMsPerNe() {
        return msPerNe;
    }

    public void setMsPerNe(int msPerNe) {
        this.msPerNe = msPerNe;
    }

    public int getDbMaxRows() {
        return dbMaxRows;
    }

    public void setDbMaxRows(int dbMaxRows) {
        this.dbMaxRows = dbMaxRows;
    }

    public int getDbMaxCtrs() {
        return dbMaxCtrs;
    }

    public void setDbMaxCtrs(int dbMaxCtrs) {
        this.dbMaxCtrs = dbMaxCtrs;
    }

    public int getMaxMs() {
        return maxMs;
    }

    public void setMaxMs(int maxMs) {
        this.maxMs = maxMs;
    }

    public int getTotalBytesPerInterval() {
        return totalBytesPerInterval;
    }

    public void setTotalBytesPerInterval(int totalBytesPerInterval) {
        this.totalBytesPerInterval = totalBytesPerInterval;
    }

    public int getTotalSizePerHour() {
        return totalSizePerHour;
    }

    public void setTotalSizePerHour(int totalSizePerHour) {
        this.totalSizePerHour = totalSizePerHour;
    }

    public int getTableSizePerDay() {
        return tableSizePerDay;
    }

    public void setTableSizePerDay(int tableSizePerDay) {
        this.tableSizePerDay = tableSizePerDay;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        ArsMeasurement that = (ArsMeasurement) o;

        return name != null ? name.equals(that.name) : that.name == null;
    }

    @Override
    public int hashCode() {
        return name != null ? name.hashCode() : 0;
    }
}
