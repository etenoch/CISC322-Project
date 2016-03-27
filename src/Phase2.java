
import com.opencsv.CSVReader;

import javax.swing.*;
import java.awt.*;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.List;


public class Phase2 {


    public static void main(String[] args) {
        List<String[]> rawData = null;
        try {

            InputStream inputStream = new FileInputStream("Test_In.csv");
            CSVReader reader = new CSVReader(new InputStreamReader(inputStream));
            rawData = reader.readAll();

        } catch (IOException e) {

        }

        if (rawData!=null){

            String[] header = {};
            String[][] data = new String[rawData.size()-1][];
            int i = 0;
            for(String[] line : rawData){
                if (i==0) header = line;
                else data[i-1]=line;
                i++;
            }

            JFrame frame = new JFrame();


            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

            JTable table = new JTable(data, header);

            JScrollPane scrollPane = new JScrollPane(table);
            frame.add(scrollPane, BorderLayout.CENTER);
            frame.setSize(300, 150);
            frame.setVisible(true);

        }

    }
}
