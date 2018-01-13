package com.zc.wjl.wjl.adapterdesignpattern;

/**
 * 对象适配器模式
 *
 * @author Wjl.
 * @date 2018\1\13 0013
 */

public class VoltObjAdapter implements Output5Volt {
    private Output220Volt mOutput220Volt;

    public VoltObjAdapter(Output220Volt output220Volt) {
        mOutput220Volt = output220Volt;
    }

    @Override
    public int output5V() {
        //接口方法中获取220V的结果进行操作并输出需要的Volt
        int output220V = mOutput220Volt.output220V();
        return output220V % 5;
    }
}
