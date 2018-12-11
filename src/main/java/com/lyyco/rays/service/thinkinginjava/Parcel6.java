package com.lyyco.rays.service.thinkinginjava;

/**
 * 任意的作用域内嵌入一个内部类
 * Author liyangyang
 * 2018/12/11
 */
public class Parcel6 {
    private void internalTracking(boolean b){
        if(b){
            class TrackingSlip{
                private String id;
                TrackingSlip(String s){
                    id = s;
                }
                String getSlip(){
                    return id;
                }
            }
            TrackingSlip ts = new TrackingSlip("slip");
            String s = ts.getSlip();
        }

    }
    public void track(){
        internalTracking(true);
    }

    public static void main(String[] args) {
        Parcel6 p = new Parcel6();
        p.track();
    }
}
