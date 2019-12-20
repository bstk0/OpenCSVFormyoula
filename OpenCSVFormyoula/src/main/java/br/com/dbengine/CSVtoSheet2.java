package br.com.dbengine;

import java.io.IOException;

class CSVtoSheet2 {
    
	final static String FILE_PATH = "E://OneDrive - Infosys Limited/Syngenta/Formyoula/Complaints/Report_Auditoria/";
	final static String EXCEL_NAME = "workbook2.xls";
	//final static String FILE_SEARCH = "Formulário de Ocorrências - Logistica";
	//final static String SHEET_NAME = "Logistica";
	//
	//final static String FILE_SEARCH = "Formulário de Ocorrências - Reclamação de Produto";
	//final static String SHEET_NAME = "Reclamacao Produto";
	//
	//final static String FILE_SEARCH = "Formulário de Ocorrências - Erros na NF";
	//final static String SHEET_NAME = "Erros na NF";
	//
	//final static String FILE_SEARCH = "Formulário de Ocorrências - Serviços Adicionais";
	//final static String SHEET_NAME = "Serviços Adicionais";
	//
	final static String FILE_SEARCH = "Formulário de Ocorrências - Acordo Comercial";
	final static String SHEET_NAME = "Acordo Comercial";
	

	public static void main(String[] args) throws IOException {
    	
    	//String fileName = "Formulário de Ocorrências - Logistica(10_01_2019 - 11_30_2019).csv"; //"src/main/resources/numbers.csv";
    	//final File file = FILE_PATH + fileName; //"/tmp/sheet.xls";
		
		CSVtoSheet2Impl csv2 = new CSVtoSheet2Impl(); 
		csv2.generateExcel(FILE_PATH, FILE_SEARCH, EXCEL_NAME,SHEET_NAME);
		
    }	
	
}