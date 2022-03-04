package com.svi.advancedjavatraining.utils;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.LinkedList;
import java.util.List;

import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import com.svi.advancedjavatraining.object.PopulationData;

public class PopulationFileWriter {
	private static final String OUTPUTFILE = "PopulationData.xlsx";
	private static final String OUTPUTFOLDER = "Output";

	Workbook workbook;

	private List<PopulationData> populationData = new LinkedList<>();

	public PopulationFileWriter() {
		workbook = new XSSFWorkbook();
	}

	public void addPopulationRecord(PopulationData populationData) {
		this.populationData.add(populationData);
	}

	public void writeToFile() throws IOException {
		Sheet populationSheet = workbook.createSheet("PopulationData");
		Row headerRow = populationSheet.createRow(0);
		headerRow.createCell(0).setCellValue("Province");
		headerRow.createCell(1).setCellValue("City");
		headerRow.createCell(2).setCellValue("Population");

		for (int i = 0; i < populationData.size(); i++) {
			PopulationData data = populationData.get(i);

			Row row = populationSheet.createRow(i + 1);
			row.createCell(0).setCellValue(data.getProvince());
			row.createCell(1).setCellValue(data.getCity());
			row.createCell(2).setCellValue(data.getPopulation());
		}
		FileOutputStream fileOut = new FileOutputStream(OUTPUTFOLDER + File.separator + OUTPUTFILE);
		workbook.write(fileOut);
		fileOut.close();
	}

}
