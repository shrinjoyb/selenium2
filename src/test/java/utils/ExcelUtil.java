package utils;

import org.apache.poi.ss.usermodel.*;
import java.io.FileInputStream;

public class ExcelUtil {
    public static Object[][] getData(String path, String sheet) {
        try (Workbook wb = WorkbookFactory.create(new FileInputStream(path))) {
            Sheet s = wb.getSheet(sheet);
            int rows = s.getLastRowNum();
            int cols = s.getRow(0).getLastCellNum();
            DataFormatter fmt = new DataFormatter();

            Object[][] data = new Object[rows][cols];
            for (int i = 1; i <= rows; i++) {
                Row r = s.getRow(i);
                for (int j = 0; j < cols; j++) {
                    Cell c = (r == null) ? null : r.getCell(j);
                    data[i - 1][j] = (c == null) ? "" : fmt.formatCellValue(c).trim();
                }
            }
            return data;
        } catch (Exception e) {
            return new Object[0][0];
        }
    }
}
