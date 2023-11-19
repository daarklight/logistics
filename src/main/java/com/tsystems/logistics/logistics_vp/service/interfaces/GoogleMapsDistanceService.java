package com.tsystems.logistics.logistics_vp.service.interfaces;

import java.util.List;

public interface GoogleMapsDistanceService {
    List<Integer> calculateRideDurationAndDistance(String startPoint, String endPoint) throws InterruptedException;
}
