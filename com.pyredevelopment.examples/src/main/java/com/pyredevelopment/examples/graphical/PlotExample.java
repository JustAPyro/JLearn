package com.pyredevelopment.examples.graphical;

import com.pyredevelopment.window.Plot;

public class PlotExample {


    public static void main(String[] args) {

        Plot plt = new Plot();
        plt.plot(new int[]{1, 2, 3, 4});
        plt.ylabel("some numbers");
        plt.show();

    }

}
