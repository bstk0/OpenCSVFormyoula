package br.com.dbengine;

import com.opencsv.CSVReader;

import java.io.FileReader;
import java.io.IOException;

public class CSVReaderExample {
	
    //public static void main(String[] args) {
	public static void main(String[] args) throws IOException {

        //String csvFile = "/Users/mkyong/csv/country3.csv";
    	//String csvFile = "E:/OneDrive - Infosys Limited/Syngenta/Formyoula/Complaints/Formulário de Ocorrências - Logistica(01_01_2019 - 01_31_2019).csv";
		  String csvFile = "E:/OneDrive - Infosys Limited/Syngenta/Formyoula/Complaints/Report_Auditoria/Formulário de Ocorrências - Reclamação de Produto(01_01_2019 - 02_28_2019).csv";
		
        CSVReader reader = null;
        try {
            reader = new CSVReader(new FileReader(csvFile));
            String[] line;
            while ((line = reader.readNext()) != null) {
                //System.out.println("LINHA [id= " + line[0] + ", code= " + line[1] + " , name=" + line[2] + "]");
            	System.out.println("LINHA [Número da Ocorrência="+ line[9] +
            	",Número da Nota fiscal="+ line[55] + 
            	",Cliente="+line[11] +
            	",RTV="+line[27] +
            	",Tipo da Ocorrência="+line[17] +
            	",Valor do Pedido="+line[57] + "]");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }


    }

}