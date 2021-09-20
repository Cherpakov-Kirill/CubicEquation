import javax.swing.*;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.data.xy.XYSeries;
import org.jfree.data.xy.XYSeriesCollection;


public class ChartBuilder {
    ChartBuilder(double aParam, double bParam, double cParam, AnswerFinder solution) {
        int start = (int) (Math.floor(solution.firstAnswer) - 1);
        int finish = (int) (Math.ceil(solution.thirdAnswer) + 1);
        XYSeries seriesCubic = new XYSeries("x^3+" + aParam + "x^2+" + bParam + "x+" + cParam);
//        for (float i = start; i <= finish; i += 0.1) {
//            seriesCubic.add(i, i * i * i + aParam * i * i + bParam * i + cParam);
//        }
        for (float i = -(int)aParam; i <= aParam; i += 0.1) {
            seriesCubic.add(i, i * i * i + aParam * i * i + bParam * i + cParam);
        }
        XYSeriesCollection xyDataset = new XYSeriesCollection();
        xyDataset.addSeries(seriesCubic);

        JFreeChart chart = ChartFactory
                .createXYLineChart("x^3+" + aParam + "x^2+" + bParam + "x+" + cParam, "x", "y",
                        xyDataset,
                        PlotOrientation.VERTICAL,
                        true, true, true);
        JFrame frame = new JFrame("Chart");
        frame.setSize(1000, 800);

        JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
        if (solution.countOfAnswers == 1 || solution.countOfAnswers == 2 || solution.countOfAnswers == 3) {
            JLabel label1 = new JLabel("Answer 1: " + solution.firstAnswer);
            panel.add(label1);
            if (solution.countOfAnswers == 2 || solution.countOfAnswers == 3) {
                JLabel label2 = new JLabel("Answer 2: " + solution.secondAnswer);
                panel.add(label2);
                if (solution.countOfAnswers == 3) {
                    JLabel label3 = new JLabel("Answer 3: " + solution.thirdAnswer);
                    panel.add(label3);
                }
            }
        } else {
            JLabel label = new JLabel("Have not answers!");
            panel.add(label);
        }
        panel.add(new ChartPanel(chart));
        frame.setContentPane(panel);
        frame.setVisible(true);
    }
}
