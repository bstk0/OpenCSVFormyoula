package br.com.dbengine;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.CreationHelper;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;

import com.opencsv.CSVReader;

class CSVtoSheet2 {
    
	final static String FILE_SEARCH = "Formulário de Ocorrências - Logistica";
	final static String EXCEL_NAME = "workbook2.xls";
	final static String SHEET_NAME = "Logistica";
	final static String FILE_PATH = "E://OneDrive - Infosys Limited/Syngenta/Formyoula/Complaints/Report_Auditoria/";
	

	public static void main(String[] args) throws IOException {
    	
    	//String fileName = "Formulário de Ocorrências - Logistica(10_01_2019 - 11_30_2019).csv"; //"src/main/resources/numbers.csv";
    	//final File file = FILE_PATH + fileName; //"/tmp/sheet.xls";
		
		CSVtoSheet2Impl csv2 = new CSVtoSheet2Impl(); 
		
    	File file = new File(FILE_PATH + EXCEL_NAME);
    	
    	final HSSFWorkbook wb; //workbook;
    	if (file.exists() == false) {
    	  System.out.println("Creating a new workbook '" + file + "'");
    	  wb = new HSSFWorkbook();
    	  csv2.setWriteReader(false);
    	  
    	} else {
    	  System.out.println("Appending to existing workbook '" + file + "'");
    	  final InputStream is = new FileInputStream(file);
    	  try {
    		  wb = new HSSFWorkbook(is);
    	  } finally {
    	    is.close();
    	  }
    	}
    	
    	//listing Files
		final File folder = new File(FILE_PATH);
    	
    	List<String> result = new ArrayList<>();
    	
    	ListFiles.search(FILE_SEARCH+".*\\.csv", folder, result);
    	
    	HSSFSheet sheet = getSheet(wb);

		for (String s : result) {
			System.out.println(s);
			loadWriteSheet(FILE_PATH, SHEET_NAME, s, sheet);
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
        FileOutputStream fileOut = new FileOutputStream(FILE_PATH + EXCEL_NAME);
        wb.write(fileOut);
        fileOut.close();
    }

	private static void loadWriteSheet(final String FILE_PATH, final String SHEET_NAME,String fileName, HSSFSheet sheet)
			throws FileNotFoundException, IOException {
		//Workbook wb = new HSSFWorkbook();
        CreationHelper helper = sheet.getWorkbook().getCreationHelper();

        
        /*
        System.out.println("wb.getNumberOfSheets():" + wb.getNumberOfSheets());
		if (wb.getNumberOfSheets() != 0) {
	        for (int i = 0; i < wb.getNumberOfSheets(); i++) {
	          System.out.println("wb.getSheetName(i)" + wb.getSheetName(i));	
	           if (wb.getSheetName(i).equals(SHEET_NAME)) {
	        	    System.out.println("Achou");
	                sheet = wb.getSheet(SHEET_NAME);
	                sheet.shiftRows(1, sheet.getLastRowNum()+1, 1, true,true);
	            } else {
	            	System.out.println("Não Achou");
	            	sheet = wb.createSheet(SHEET_NAME);
	            }
	        }
	    }
	    else {
	        // Create new sheet to the workbook if empty
	        sheet = wb.createSheet(SHEET_NAME);
	    }
		*/
		int rowCount = sheet.getLastRowNum()+1;

        @SuppressWarnings("resource")
		CSVReader reader = new CSVReader(new FileReader(FILE_PATH + fileName));
        String[] line;
        //int r = 0;
        while ((line = reader.readNext()) != null) {
            Row row = sheet.createRow((short) rowCount++);
			//System.out.println("sheet.getLastRowNum():" + sheet.getLastRowNum());
			//sheet.shiftRows(1, sheet.getLastRowNum()+1, 1, true,true);
            
            //TO DO: HEADER SÓ PARA O PRIMEIRO ARQUIVO ...
            for (int i = 0; i < line.length; i++)
                row.createCell(i)
                   .setCellValue(helper.createRichTextString(line[i]));
        }
        //reader.close();
	}
	
	
	private static HSSFSheet getSheet(final HSSFWorkbook wb) {
       
        //Sheet sheet = null;
        HSSFSheet sheet = wb.getSheet(SHEET_NAME);
		if(sheet == null){
			//throw new Exception("Sheet doesnot exist...");
			System.out.println("Sheet doesnot exist...");
			sheet = wb.createSheet(SHEET_NAME);
		}
		else {
			System.out.println("sheet.getLastRowNum():" + sheet.getLastRowNum());
			//sheet.shiftRows(1, sheet.getLastRowNum()+1, 1, true,true);
		}
		return sheet;
	}
	
	
}