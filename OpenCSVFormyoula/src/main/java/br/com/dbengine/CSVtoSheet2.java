package br.com.dbengine;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.CreationHelper;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;

import com.opencsv.CSVReader;

class CSVtoSheet2 {
    
	//SE O ARQUIVO EXISTE NÃO DÁ ERRO, MAS DEIXA SOMENTE UM SHEET
	
	public static void main(String[] args) throws IOException {
		
		final String FILE_PATH = "E://OneDrive - Infosys Limited/Syngenta/Formyoula/Complaints/Report_Auditoria/";
    	
    	String fileName = "Formulário de Ocorrências - Logistica(10_01_2019 - 11_30_2019).csv"; //"src/main/resources/numbers.csv";
    	
    	//final File file = FILE_PATH + fileName; //"/tmp/sheet.xls";
    	File file = new File(FILE_PATH + "workbook.xls");
    	
    	final HSSFWorkbook wb; //workbook;
    	if (file.exists() == false) {
    	  System.out.println("Creating a new workbook '" + file + "'");
    	  wb = new HSSFWorkbook();
    	} else {
    	  System.out.println("Appending to existing workbook '" + file + "'");
    	  final InputStream is = new FileInputStream(file);
    	  try {
    		  wb = new HSSFWorkbook(is);
    	  } finally {
    	    is.close();
    	  }
    	}
    	
        //Workbook wb = new HSSFWorkbook();
        CreationHelper helper = wb.getCreationHelper();
        Sheet sheet = wb.createSheet("_new sheet 3");

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