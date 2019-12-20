package br.com.dbengine.test;

import java.io.File;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.CreationHelper;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;

import com.opencsv.CSVReader;

class CSVtoSheet {
    
	//SE O ARQUIVO EXISTE NÃO DÁ ERRO, MAS DEIXA SOMENTE UM SHEET
	
	public static void main(String[] args) throws IOException {
		
		final String FILE_PATH = "E://OneDrive - Infosys Limited/Syngenta/Formyoula/Complaints/Report_Auditoria/";
    	
    	String fileName = "Formulário de Ocorrências - Logistica(10_01_2019 - 11_30_2019).csv"; //"src/main/resources/numbers.csv";
    	
        Workbook wb = new HSSFWorkbook();
        CreationHelper helper = wb.getCreationHelper();
        Sheet sheet = wb.createSheet("new sheet 2");

        CSVReader reader = new CSVReader(new FileReader(FILE_PATH + fileName));
        String[] line;
        int r = 0;
        while ((line = reader.readNext()) != null) {
            Row row = sheet.createRow((short) r++);

            for (int i = 0; i < line.length; i++)
                row.createCell(i)
                   .setCellValue(helper.createRichTextString(line[i]));
        }

        /*
        // test to see if a file exists
        File file = new File(FILE_PATH + "workbook.xls");
        //exists = file.exists();
        if (file.exists() && file.isFile())
        {
          System.out.println("file exists, and it is a file");
        }
        */
        
        // Write the output to a file
        FileOutputStream fileOut = new FileOutputStream(FILE_PATH + "workbook.xls");
        wb.write(fileOut);
        fileOut.close();
    }
}