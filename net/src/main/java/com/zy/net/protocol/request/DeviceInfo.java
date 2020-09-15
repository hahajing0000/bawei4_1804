package com.zy.net.protocol.request;

/**
 * @author:zhangyue
 * @date:2020/9/11
 */
public class DeviceInfo {
    /**
     * 当前app的VersionCode
     */
    private int appversioncode;
    /**
     * 当前app的VersionName
     */
    private String appversionname;
    /**
     * MD5值
     */
    private String authcode;
    /**
     * 手机品牌
     */
    private String devicebrand;
    /**
     * 手机厂商
     */
    private String devicemanufacturer;
    /**
     * 手机机型
     */
    private String devicemodel;
    /**
     * 设备系统的编译类型  ENG USERDEBUG USER
     */
    private String devicetype;
    /**
     * 显示参数  分辨率
     */
    private String displayparam;
    /**
     * 设备表的主键
     */
    private int id;
    /**
     * IMEI/MEID  设备串号 *#06#
     */
    private String imei;
    /**
     * 最新的位置
     */
    private String lastlocation;
    /**
     * Android SDK INT
     */
    private String sdkversion;
    /**
     * 设备使用者id
     */
    private int userid;

    public DeviceInfo() {
    }

    public DeviceInfo(int appversioncode, String appversionname, String authcode, String devicebrand, String devicemanufacturer, String devicemodel, String devicetype, String displayparam, int id, String imei, String lastlocation, String sdkversion, int userid) {
        this.appversioncode = appversioncode;
        this.appversionname = appversionname;
        this.authcode = authcode;
        this.devicebrand = devicebrand;
        this.devicemanufacturer = devicemanufacturer;
        this.devicemodel = devicemodel;
        this.devicetype = devicetype;
        this.displayparam = displayparam;
        this.id = id;
        this.imei = imei;
        this.lastlocation = lastlocation;
        this.sdkversion = sdkversion;
        this.userid = userid;
    }

    public int getAppversioncode() {
        return appversioncode;
    }

    public void setAppversioncode(int appversioncode) {
        this.appversioncode = appversioncode;
    }

    public String getAppversionname() {
        return appversionname;
    }

    public void setAppversionname(String appversionname) {
        this.appversionname = appversionname;
    }

    public String getAuthcode() {
        return authcode;
    }

    public void setAuthcode(String authcode) {
        this.authcode = authcode;
    }

    public String getDevicebrand() {
        return devicebrand;
    }

    public void setDevicebrand(String devicebrand) {
        this.devicebrand = devicebrand;
    }

    public String getDevicemanufacturer() {
        return devicemanufacturer;
    }

    public void setDevicemanufacturer(String devicemanufacturer) {
        this.devicemanufacturer = devicemanufacturer;
    }

    public String getDevicemodel() {
        return devicemodel;
    }

    public void setDevicemodel(String devicemodel) {
        this.devicemodel = devicemodel;
    }

    public String getDevicetype() {
        return devicetype;
    }

    public void setDevicetype(String devicetype) {
        this.devicetype = devicetype;
    }

    public String getDisplayparam() {
        return displayparam;
    }

    public void setDisplayparam(String displayparam) {
        this.displayparam = displayparam;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getImei() {
        return imei;
    }

    public void setImei(String imei) {
        this.imei = imei;
    }

    public String getLastlocation() {
        return lastlocation;
    }

    public void setLastlocation(String lastlocation) {
        this.lastlocation = lastlocation;
    }

    public String getSdkversion() {
        return sdkversion;
    }

    public void setSdkversion(String sdkversion) {
        this.sdkversion = sdkversion;
    }

    public int getUserid() {
        return userid;
    }

    public void setUserid(int userid) {
        this.userid = userid;
    }
}
