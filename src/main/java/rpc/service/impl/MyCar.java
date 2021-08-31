package rpc.service.impl;

import rpc.service.Car;

public class MyCar implements Car {


    @Override
    public String ooxx(String id) {
        return id + " 是法拉利";
    }

    @Override
    public String xxoo(String id) {
        return null;
    }
}
