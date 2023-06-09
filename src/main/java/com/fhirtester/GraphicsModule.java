package com.fhirtester;

import java.awt.*;
import java.util.Arrays;

public class GraphicsModule {
    private final int canvas_x_size = 1000;
    private final int canvas_y_size = 1000;
    private final int lead_y_size = canvas_y_size / 3;

    public GraphicsModule() {
        StdDraw.setCanvasSize(canvas_x_size, canvas_y_size);
        StdDraw.setXscale(0, canvas_x_size);
        StdDraw.setYscale(0, canvas_y_size);
    }

    public void drawECG(String[] comps) {
        StdDraw.clear(Color.WHITE);
        StdDraw.setPenColor(Color.black);
        StdDraw.setPenRadius(0.005);
        StdDraw.line(0, lead_y_size, canvas_x_size, lead_y_size);
        StdDraw.line(0, 2*lead_y_size, canvas_x_size, 2*lead_y_size);

        Font font = new Font("Arial", Font.BOLD, 14);
        StdDraw.setFont(font);
        StdDraw.textLeft(10, lead_y_size - 15, comps[4]);
        StdDraw.textLeft(10, (2*lead_y_size) - 15, comps[2]);
        StdDraw.textLeft(10, (3*lead_y_size) - 15, comps[0]);

        int[] dat1 = Arrays.stream(comps[1].split(" ")).mapToInt(Integer::parseInt).toArray();
        int[] dat2 = Arrays.stream(comps[3].split(" ")).mapToInt(Integer::parseInt).toArray();
        int[] dat3 = Arrays.stream(comps[5].split(" ")).mapToInt(Integer::parseInt).toArray();

        int datapoints = dat1.length;
        double datapoint_x_dist = ((double) canvas_x_size - 10) / datapoints;

        StdDraw.setPenColor(Color.red);
        StdDraw.setPenRadius(0.005);

        double prev_d1_y = 2.5*lead_y_size;
        double prev_d2_y = 1.5*lead_y_size;
        double prev_d3_y = 0.5*lead_y_size;

        for(int i = 0; i < datapoints; i++) {
            double d1_y = (2.5*lead_y_size) + ((dat1[i] - 2000)/2.0);
            double d2_y = (1.5*lead_y_size) + ((dat2[i] - 2000)/2.0);
            double d3_y = (0.5*lead_y_size) + ((dat3[i] - 2000)/2.0);

            StdDraw.line(i*datapoint_x_dist, prev_d1_y, (i+1)*datapoint_x_dist, d1_y);
            StdDraw.line(i*datapoint_x_dist, prev_d2_y, (i+1)*datapoint_x_dist, d2_y);
            StdDraw.line(i*datapoint_x_dist, prev_d3_y, (i+1)*datapoint_x_dist, d3_y);

            prev_d1_y = d1_y;
            prev_d2_y = d2_y;
            prev_d3_y = d3_y;
        }

        StdDraw.show();
    }
}
