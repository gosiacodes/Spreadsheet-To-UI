package appPackage;

import java.awt.Container;
import java.awt.Dimension;
import java.util.ArrayList;

import javax.swing.GroupLayout;
import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.WindowConstants;
import javax.swing.table.DefaultTableModel;

public class UserInterface implements Runnable {
		
	private JFrame jFrame;
	private JScrollPane jScrollPane;
	private JTable jTable;
	private DefaultTableModel csv_data;
	
	static ArrayList<ArrayList<String>> currentSheet = readCSV.getWholeSheet();
	static String[][] dataArray;

	// Method updating values for table
	public static void updateValues() {

		int column = (currentSheet.get(0).size());

		dataArray = new String[currentSheet.toArray().length - 1][column];

		for (int i = 1; i < currentSheet.toArray().length; i++) {
			ArrayList<String> row = new ArrayList<>();
			row = currentSheet.get(i);
			dataArray[i - 1] = row.toArray(new String[column]);
		}
	}	

	@Override
	public void run() {
		jFrame = new JFrame("Spreedsheet to UI");
		jFrame.setPreferredSize(new Dimension(1000, 500));
		
		createComponents(jFrame.getContentPane());
		
		jFrame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
		jFrame.pack();
		jFrame.setVisible(true);
	}

	// Method creating components - design of interface with data for table
	private void createComponents(Container container) {
		
		jScrollPane = new JScrollPane();
		jTable = new JTable ();		
		
		updateValues();
		
		csv_data = new DefaultTableModel(dataArray, currentSheet.get(0).toArray());	
		
		jTable.setModel(csv_data);
	    jScrollPane.setViewportView(jTable);
	    jTable.setAutoCreateRowSorter(true);
	    
	    // Creating layout and its components
	    GroupLayout layout = new GroupLayout(container);
	    container.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane, GroupLayout.DEFAULT_SIZE, 800, Short.MAX_VALUE)
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane, GroupLayout.PREFERRED_SIZE, 400, GroupLayout.PREFERRED_SIZE)
                .addContainerGap(14, Short.MAX_VALUE))
        );		
        
	}
	
	public JFrame getFrame() {
		return jFrame;
	}
}
