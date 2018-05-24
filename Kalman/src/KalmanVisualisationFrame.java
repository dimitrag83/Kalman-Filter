/*
Για την γραφική αναπαράσταση των τιμών χρησιμοποιήθηκε η βιβλιοθήκη jfreeChart
http://sourceforge.net/projects/jfreechart/files/
*/

import java.awt.Color;
import java.util.ArrayList;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.chart.plot.XYPlot;
import org.jfree.chart.renderer.xy.XYLineAndShapeRenderer;
import org.jfree.data.xy.XYDataset;
import org.jfree.data.xy.XYSeries;
import org.jfree.data.xy.XYSeriesCollection;
import org.jfree.ui.ApplicationFrame;


public class KalmanVisualisationFrame extends ApplicationFrame {

    //Οι τιμές που θέλω να παραραστήσω γραφικά
    private ArrayList<Double> realValues;
    private ArrayList<Double> measuredValues;
    private ArrayList<Double> arXplus;
        
   
    public KalmanVisualisationFrame(final String title) {
        
        super(title);

        final XYDataset kalmanFilterDataset = createKalmanFilterDataset();
        final JFreeChart kalmanChart = createChart(kalmanFilterDataset);
        final ChartPanel kalmanChartPanel = new ChartPanel(kalmanChart);
        kalmanChartPanel.setPreferredSize(new java.awt.Dimension(700, 500));
        setContentPane(kalmanChartPanel);

    }
    
    
    private XYDataset createKalmanFilterDataset() {
        
        ArrayList<Double> realValues = readFile.get_realValues();
        ArrayList<Double> measuredValues = readFile.get_measuredValues();
        ArrayList<Double> arXplus = readFile.get_arXplus();
        
        final XYSeries realValuesSeries = new XYSeries("Real values");
        final XYSeries measuredValuesSeries = new XYSeries("Measured values");
        final XYSeries kalmanFilterValuesSeries = new XYSeries("Kalman filter values");
        
        double x = 0.0;
        
        //Προσθήκη των σημείων στο dataset
        for (int i=0;i<200;i++){      
            
            realValuesSeries.add(x+i, realValues.get(i));
            measuredValuesSeries.add(x+i, measuredValues.get(i));        
            kalmanFilterValuesSeries.add(x+i, arXplus.get(i));
        }
        
        //Αναπαράσταση των σημείων του dataset
        final XYSeriesCollection kalmanVisualisationDataset = new XYSeriesCollection();
        kalmanVisualisationDataset.addSeries(realValuesSeries);
        kalmanVisualisationDataset.addSeries(measuredValuesSeries);
        kalmanVisualisationDataset.addSeries(kalmanFilterValuesSeries);
                
        return kalmanVisualisationDataset;
        
    }
    
    //Εμφανίζει το γράφημα
    private JFreeChart createChart(final XYDataset kalmanVisualisationDataset) {
        
        // Δημιουργία γραφήματος
        final JFreeChart chart = ChartFactory.createXYLineChart(
            "Kalman Filter Visualisation",      // τίτλος γραφήματος
            "X",                      // τίτλος x άξονα
            "Y",                      // τίτλος y άξονα
            kalmanVisualisationDataset,                  // δεδομένα
            PlotOrientation.VERTICAL,
            true,                     // include legend
            true,                     // tooltips
            false                     // urls
        );

        final XYPlot plot = chart.getXYPlot();
        plot.setBackgroundPaint(Color.lightGray);
        plot.setDomainGridlinePaint(Color.black);
        plot.setRangeGridlinePaint(Color.black);
        
        //εμφανίζει τα σημεία
        final XYLineAndShapeRenderer renderer = new XYLineAndShapeRenderer();        
        plot.setRenderer(renderer);      
        
        return chart;
        
    }

  

}