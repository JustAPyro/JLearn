package com.pyredevelopment.examples.graphical;

import com.pyredevelopment.window.Plot;

public class PlotExample {


    public static void main(String[] args) {

        /* MatPlotLib PyPlot Example Usage 1.
        Plot plt = new Plot();
        plt.plot(new int[]{1, 2, 3, 4});
        plt.setYLabel("some numbers");
        plt.show();
        */


        /* MatPlotLib PyPlot Example Usage 2.
        Plot plt = new Plot();
        plt.plot(new int[]{1, 2, 3, 4}, new int[]{1, 4, 9, 16});
        plt.axis(1, 4, 0, 16);
        plt.show();
        */

        /* MatPlotLib PyPlot Example Usage 3. */
        Plot plt = new Plot();
        plt.plot(new int[]{1, 2, 3, 4}, new int[]{1, 4, 9, 16}, "ro");
        plt.axis(0, 6, 0, 20);
        plt.show();







    }

}
