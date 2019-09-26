package quicksilver.commons.data;

import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.FileOutputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.time.Instant;
import java.time.LocalDate;
import java.util.*;

public class ExcelExporter {

    /*
        Apache POI Examples :
        - http://svn.apache.org/repos/asf/poi/trunk/src/examples/src/org/apache/poi/ss/examples/
        - http://svn.apache.org/repos/asf/poi/trunk/src/examples/src/org/apache/poi/ss/examples/TimesheetDemo.java
        - http://svn.apache.org/repos/asf/poi/trunk/src/examples/src/org/apache/poi/ss/examples/LoanCalculator.java

     */

    private String filePath;

    private TimeZone timeZone;
    private SimpleDateFormat simpleDateFormat;
    private SimpleDateFormat simpleDateTimeFormat;

    private ArrayList<HashMap.SimpleEntry<DataSet, String>> dataSetList = new ArrayList<HashMap.SimpleEntry<DataSet, String>>();

    public ExcelExporter(String filePath) {
        this(filePath, TimeZone.getTimeZone("UTC"));
    }

    public ExcelExporter(String filePath, TimeZone timeZone) {
        this.filePath = filePath;
        this.timeZone = timeZone;

        this.simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
        simpleDateFormat.setTimeZone(timeZone);

        this.simpleDateTimeFormat = new SimpleDateFormat("yyyy-MM-dd'T'hh:mm:ssZ");
        simpleDateTimeFormat.setTimeZone(timeZone);
    }

    public void addDataSet(DataSet dataset, String sheetName) {
        dataSetList.add(new HashMap.SimpleEntry<DataSet, String>(dataset, sheetName));
    }

    public void export() throws IOException {

        // Create new workbook
        Workbook workbook = new XSSFWorkbook();

        for ( int i = 0; i < dataSetList.size(); i++ ) {
            HashMap.SimpleEntry<DataSet, String> entry = dataSetList.get(i);
            DataSet dataSet = entry.getKey();
            String sheetName = entry.getValue();

            // Create or get a Sheet
            Sheet sheet = workbook.getSheet(sheetName);
            if (sheet == null) {
                sheet = workbook.createSheet(sheetName);
            }

            // Write dataSet out to Sheet
            writeSheet(sheet, dataSet);
        }

        // Write workbook to the file
        try {
            workbook.write(new FileOutputStream(filePath));
        } finally {
            workbook.close();
        }

    }

    protected void writeSheet(Sheet sheet, DataSet dataSet) {

        int currentRow = 0;
        int currentColumn = 0;

        // Write out the headers of the dataset into the first row
        Row row = sheet.createRow(currentRow);

        for ( int j = 0; j < dataSet.getColumnCount(); j++ ) {
            Cell cell = row.createCell(j);
            cell.getCellStyle().setFillBackgroundColor(IndexedColors.GREY_50_PERCENT.getIndex());
            setCellValue(cell, dataSet.getColumnName(j));
            currentColumn++;
        }
        currentRow++;

        currentColumn = 0;

        // Write out the dataset rows into the following rows
        for ( int i = 0; i < dataSet.getRowCount(); i++ ) {

            row = sheet.createRow(currentRow);

            for ( int j = 0; j < dataSet.getColumnCount(); j++ ) {
                Cell cell = row.createCell(j);
                setCellValue(cell, dataSet.getValue(j, i));
                currentColumn++;
            }
            currentRow++;
        }

    }

    protected void setCellValue(Cell cell, Object value) {
        if (value instanceof Boolean) {
            cell.setCellValue((Boolean)(value));
        } else if (value instanceof LocalDate) {
            Instant instant = ((LocalDate)value).atStartOfDay(timeZone.toZoneId()).toInstant();
            cell.setCellValue(simpleDateFormat.format(Date.from(instant)));
        } else if (value instanceof Instant) {
            cell.setCellValue(simpleDateTimeFormat.format(Date.from((Instant) value)));
        } else if (value instanceof Double) {
            cell.setCellValue((Double)(value));
        } else if (value instanceof Integer) {
            cell.setCellValue((Integer)(value));
        } else if (value instanceof Long) {
            cell.setCellValue((Long)(value));
        } else if (value instanceof String) {
            cell.setCellValue(value.toString());
        } else if (value == null) {
            // write nothing
        } else {
            cell.setCellValue(value.toString());
        }
    }

}
