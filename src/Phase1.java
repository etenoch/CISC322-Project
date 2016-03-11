import com.opencsv.CSVReader;
import com.opencsv.CSVWriter;

import java.io.*;

public class Phase1 {

    public static void main(String [] args){

        try{
            reverseCSV(args[0],args[1]);
        }catch (IOException e){
            System.out.println("Error: file IO error occured");
        }

    }

    public static void reverseCSV(String fromFile, String toFile) throws IOException{
        InputStream inputStream = new FileInputStream(fromFile);
        CSVReader reader = new CSVReader(new InputStreamReader(inputStream));

        OutputStream outputStream = new FileOutputStream(toFile);
        CSVWriter writer = new CSVWriter(new OutputStreamWriter(outputStream),',', CSVWriter.NO_QUOTE_CHARACTER);

        String [] line;
        while ((line = reader.readNext()) != null) {
            reverseArray(line);
            writer.writeNext(line);
        }
        writer.close();
    }


    public static void reverseArray(String[] array){
        for (int i = 0; i < array.length / 2; i++) {
            String temp = array[i];
            array[i] = array[array.length - 1 - i];
            array[array.length - 1 - i] = temp;
        }
    }
}
