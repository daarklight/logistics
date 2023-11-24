package com.tsystems.logistics.logistics_vp.service.interfaces;

import java.io.IOException;
import java.util.List;

public interface GoogleMapsDistanceService {
    List<Integer> calculateRideDurationAndDistance(String startPoint, String endPoint) throws InterruptedException;
    List<List<Integer>> getRouteMatrixResults(String firstOriginAddress, String firstFinalAddress,
                                              String secondFinalAddress) throws IOException;
}
