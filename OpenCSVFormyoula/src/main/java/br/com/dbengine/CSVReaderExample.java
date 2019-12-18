package br.com.dbengine;

import com.opencsv.CSVReader;

import java.io.FileReader;
import java.io.IOException;

public class CSVReaderExample {
	
    //public static void main(String[] args) {
	public static void main(String[] args) throws IOException {

        //String csvFile = "/Users/mkyong/csv/country3.csv";
    	String csvFile = "E:/OneDrive - Infosys Limited/Syngenta/Formyoula/Complaints/Formulário de Ocorrências - Logistica(01_01_2019 - 01_31_2019).csv";

        CSVReader reader = null;
        try {
            reader = new CSVReader(new FileReader(csvFile));
            String[] line;
            while ((line = reader.readNext()) != null) {
                System.out.println("LINHA [id= " + line[0] + ", code= " + line[1] + " , name=" + line[2] + "]");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }


    }

}