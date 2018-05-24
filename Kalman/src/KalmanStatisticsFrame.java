
import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import javax.swing.*;


public class KalmanStatisticsFrame extends JFrame{
    
    private final JList stateError; // orates se oles tis  methodous tis klasis
    private final JList kalmanGain;
    private ArrayList<Double> arPplus;
    private ArrayList<Double> arK;
    
    private final JTextField leftTitle;
    private final JTextField rightTitle;
    private final JButton closeButton;
    
    
    public KalmanStatisticsFrame() {
        //Οι τιμές των Pplus(θόρυβος κατάστασης) και Κ(κέρδος Κάλμαν) διαβάζονται απτην κλάση readFile
        ArrayList<Double> arPplus = readFile.get_arPplus();        
        ArrayList<Double> arK = readFile.get_arK();
        
        //Τίτλος των στηλών του παραθύρου στατιστικών
        leftTitle = new JTextField();
        rightTitle = new JTextField();
        leftTitle.setText("State error");
        rightTitle.setText("Kalman gain");
        
        //close button listener
        ButtonListener listener = new ButtonListener();
        closeButton = new JButton("Close");
        closeButton.addActionListener(listener);
        
        //Πέρασμα των τιμών του Ρ στη JList
        stateError =  new JList();
        DefaultListModel listModelStateError = new DefaultListModel();
        for(Double i: arPplus){ //παίρνει τα δεδομένα του arPplus
            listModelStateError.addElement(i);
        }
        stateError.setModel(listModelStateError);
       
        //Πέρασμα των τιμών του Κ στη JList
        kalmanGain = new JList();
        DefaultListModel listModelKalmanGain = new DefaultListModel();
        for(Double i: arK){
            listModelKalmanGain.addElement(i);
        }
        kalmanGain.setModel(listModelKalmanGain);
        
        //15 ορατές τιμές μέσα στο παράθυρο
        stateError.setVisibleRowCount(15);
        kalmanGain.setVisibleRowCount(15);
       
        //Scroll pane
        JScrollPane scrollPaneStateError = new JScrollPane();
        JScrollPane scrollPaneKalmanGain = new JScrollPane();
        
        //Οι τιμές που εμφανίζονται
        scrollPaneStateError.setViewportView(stateError);        
        scrollPaneKalmanGain.setViewportView(kalmanGain);
        
        //borders
        leftTitle.setBorder(null);
        rightTitle.setBorder(null);
        
        //Αριστερό πλαίσιο
        JPanel leftLabelPanel = new JPanel(new BorderLayout() );       
        leftLabelPanel.add(leftTitle, BorderLayout.NORTH);
        leftLabelPanel.add(scrollPaneStateError, BorderLayout.SOUTH);
        
        //Δεξί πλαίσιο
        JPanel rightLabelPanel = new JPanel(new BorderLayout());
        rightLabelPanel.add(rightTitle, BorderLayout.NORTH);
        rightLabelPanel.add(scrollPaneKalmanGain, BorderLayout.SOUTH);
       
        //Κυριώς πλαίσιο
        JPanel mainPanel = new JPanel(new BorderLayout());
        mainPanel.add(leftLabelPanel, BorderLayout.WEST);        
        mainPanel.add(rightLabelPanel,BorderLayout.EAST);
        JPanel CloseButtonPanel = new JPanel();
        CloseButtonPanel.add(closeButton);
        mainPanel.add(CloseButtonPanel, BorderLayout.SOUTH);
        
        
        //Ρυθμίσεις παραθύρου
        this.setTitle("Kalman Filter Statistics");
        this.setSize(400,400); // megethos
        this.setVisible(true);
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.setContentPane(mainPanel); 
    
    }
    
    //Λειτουργία close button
    public class ButtonListener implements ActionListener{
        @Override
        public void actionPerformed(ActionEvent e) {
            Object src = e.getSource();
            if(src instanceof JButton)
            CloseFrame();
        }
        
    }
    
    public void CloseFrame(){
        super.dispose();
    }
         
}

