package com.vgvoleg.heuristic.util;

import com.vgvoleg.heuristic.problems.base.OptimizationDetailedResult;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

public class JSONSaver {

    public static void saveAsJSON(OptimizationDetailedResult result, String pathToFile) {
        JSONObject object = new JSONObject();
        int populationsCount = result.getPopulations().size();
        List<double[][]> listOfPopulations = result.getPopulations();
        object.put("populationsCount", populationsCount);
        object.put("pointsInPopulation", listOfPopulations.get(0).length);
        object.put("pointSize", listOfPopulations.get(0)[0].length); // TODO: make this shit beauty

        JSONArray points;
        JSONArray point;

        double[][] p;
        for (int i = 0; i < populationsCount; i++) {
            points = new JSONArray();
            p = listOfPopulations.get(i);
            for (int j = 0; j < p.length; j++) {
                point = new JSONArray();
                for (int k = 0; k < p[0].length; k++) {
                    point.add(p[j][k]);
                }
                points.add(j, point);
            }
            object.put(i, points);
        }

        try (FileWriter file = new FileWriter(pathToFile)) {
            file.write(object.toJSONString());
            file.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private JSONSaver() {
    }
}
