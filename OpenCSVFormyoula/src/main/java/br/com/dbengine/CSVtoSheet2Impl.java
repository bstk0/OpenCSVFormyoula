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

import com.opencsv.CSVReader;

public class CSVtoSheet2Impl {

	boolean jumpHeader;
	
	public void generateExcel(String FILE_PATH, String FILE_SEARCH, String EXCEL_NAME,String SHEET_NAME) {
		HSSFWorkbook wb = null; //workbook;
		File file = new File(FILE_PATH + EXCEL_NAME);

		if (file.exists() == false) {
			System.out.println("Creating a new workbook '" + file + "'");
			wb = new HSSFWorkbook();
		} else {
			try {
				System.out.println("Appending to existing workbook '" + file + "'");
				final InputStream is = new FileInputStream(file);
				wb = new HSSFWorkbook(is);
			} catch (FileNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} finally {
				// is.close();
			}
		}
    	
    	//listing Files
		final File folder = new File(FILE_PATH);
    	
    	List<String> result = new ArrayList<>();
    	
    	ListFiles.search(FILE_SEARCH+".*\\.csv", folder, result);
    	
    	HSSFSheet sheet = getSheet(wb,SHEET_NAME);

		for (String s : result) {
			System.out.println(s);
			try {
				//loadWriteSheet(FILE_PATH, SHEET_NAME, s, sheet);
				loadWriteSheetLogistica(FILE_PATH, SHEET_NAME, s, sheet);
			} catch (FileNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
      
        // Write the output to a file
        FileOutputStream fileOut;
		try {
			fileOut = new FileOutputStream(FILE_PATH + EXCEL_NAME);
			wb.write(fileOut);
	        fileOut.close();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	        
		
	}
	
	private void loadWriteSheet(final String FILE_PATH, final String SHEET_NAME,String fileName, HSSFSheet sheet)
			throws FileNotFoundException, IOException {
		//Workbook wb = new HSSFWorkbook();
        CreationHelper helper = sheet.getWorkbook().getCreationHelper();
        int rowCount = 0;
        if (sheet.getLastRowNum() == 0) {
        	setJumpHeader(false);
        } else {
        	setJumpHeader(true);
        	rowCount = sheet.getLastRowNum()+1;
        }

        @SuppressWarnings("resource")
		CSVReader reader = new CSVReader(new FileReader(FILE_PATH + fileName));
        String[] line;
        //int r = 0;
        while ((line = reader.readNext()) != null) {
            if (isJumpHeader()) {
            	setJumpHeader(false);
            	continue;
            }
        	//line - rows
            Row row = sheet.createRow((short) rowCount++);
            //colunas
            for (int i = 0; i < line.length; i++) {
                row.createCell(i)
                .setCellValue(helper.createRichTextString(line[i]));
            }
        }
        //reader.close();
	}
	
	private void loadWriteSheetLogistica(final String FILE_PATH, final String SHEET_NAME,String fileName, HSSFSheet sheet)
			throws FileNotFoundException, IOException {
		//Workbook wb = new HSSFWorkbook();
        CreationHelper helper = sheet.getWorkbook().getCreationHelper();
        int rowCount = 0;
        if (sheet.getLastRowNum() == 0) {
        	setJumpHeader(false);
        } else {
        	setJumpHeader(true);
        	rowCount = sheet.getLastRowNum()+1;
        }

        @SuppressWarnings("resource")
		CSVReader reader = new CSVReader(new FileReader(FILE_PATH + fileName));
        String[] line;
        //int r = 0;
        while ((line = reader.readNext()) != null) {
            if (isJumpHeader()) {
            	setJumpHeader(false);
            	continue;
            }
        	//line - rows
            Row row = sheet.createRow((short) rowCount++);
            //colunas
            row.createCell(0).setCellValue(helper.createRichTextString(line[9]));
            row.createCell(1).setCellValue(helper.createRichTextString(line[11]));
            row.createCell(2).setCellValue(helper.createRichTextString(line[54]));
            row.createCell(3).setCellValue(helper.createRichTextString(line[27]));
            row.createCell(4).setCellValue(helper.createRichTextString(line[17]));
            row.createCell(5).setCellValue(helper.createRichTextString(line[57]));
            
        }
        //reader.close();
	}

	
	private HSSFSheet getSheet(final HSSFWorkbook wb, String SHEET_NAME) {
	       
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
	
	public boolean isJumpHeader() {
		return jumpHeader;
	}

	public void setJumpHeader(boolean jumpHeader) {
		this.jumpHeader = jumpHeader;
	}


}
