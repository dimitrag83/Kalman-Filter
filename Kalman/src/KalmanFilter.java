import org.jfree.ui.RefineryUtilities;

//import org.jfree.ui.RefineryUtilities;

class KalmanFilter {
    public static void main(String[] args) {
        
       readFile test = new readFile();  
       KalmanStatisticsFrame test1 = new KalmanStatisticsFrame();
       
      final KalmanVisualisationFrame demo = new KalmanVisualisationFrame("Kalman Filter Visualisation");
      demo.pack();
      RefineryUtilities.centerFrameOnScreen(demo);
      demo.setVisible(true); 
       
        
        
    }  
}
