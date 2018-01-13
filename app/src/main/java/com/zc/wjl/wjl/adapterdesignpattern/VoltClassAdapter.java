package com.zc.wjl.wjl.adapterdesignpattern;

/**
 * 类适配器模式
 *
 * @author Wjl.
 * @date 2018\1\13 0013
 */

public class VoltClassAdapter extends Output220Volt implements Output5Volt {
    @Override
    public int output5V() {
        int output220V = this.output220V();
        return output220V % 5;
    }
}
