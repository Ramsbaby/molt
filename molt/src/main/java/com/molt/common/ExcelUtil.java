package com.molt.common;

import java.awt.Color;

import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFDataFormat;
import org.apache.poi.hssf.usermodel.HSSFFont;
import org.apache.poi.hssf.usermodel.HSSFPalette;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.hssf.util.HSSFColor;
import org.apache.poi.ss.usermodel.CellStyle;

/**
 * 교차접속 스크립트 공격 취약성 방지(파라미터 문자열 교체)
 *
 * <pre>
 * << 개정이력(Modification Information) >>
 *
 *   수정일      수정자           수정내용
 *  -------    	--------    ---------------------------
 *   2011.10.10  한성곤          최초 생성
 *
 * </pre>
 */

public class ExcelUtil {

	private HSSFWorkbook wb;
	private HSSFFont hSSFFont_normal;
	private HSSFFont hSSFFont_10_normal;
	private HSSFFont hSSFFont_10_red;
	private HSSFFont hSSFFont_10_bold;
	private HSSFFont hSSFFont_18_bold;
	
	public ExcelUtil(){
		if(wb == null) wb = new HSSFWorkbook();
		
		if(hSSFFont_normal == null) {
			hSSFFont_normal = getHssfFont();
//			hSSFFont_normal.setBoldweight(HSSFFont.BOLDWEIGHT_NORMAL);
		}
		
		if(hSSFFont_10_normal == null) {
			hSSFFont_10_normal = getHssfFont();
			hSSFFont_10_normal.setFontHeightInPoints((short) 10);
//			hSSFFont_10_normal.setBoldweight(HSSFFont.BOLDWEIGHT_NORMAL);
		}
		
		if(hSSFFont_10_red == null) {
			hSSFFont_10_red = getHssfFont();
			hSSFFont_10_red.setFontHeightInPoints((short) 10);
			hSSFFont_10_red.setColor(HSSFFont.COLOR_RED);
		}
		
		if(hSSFFont_10_bold == null) {
			hSSFFont_10_bold = getHssfFont();
			hSSFFont_10_bold.setFontHeightInPoints((short) 10);
			// hSSFFont_10_bold.setBoldweight(HSSFFont.BOLDWEIGHT_BOLD);
		}
		
		if(hSSFFont_18_bold == null) {
			hSSFFont_18_bold = getHssfFont();
			hSSFFont_18_bold.setFontHeightInPoints((short) 18);
			hSSFFont_18_bold.setBoldweight(HSSFFont.BOLDWEIGHT_BOLD);
		}
	}
	
	public HSSFFont getHssfFont(){
		HSSFFont hSSFFont = wb.createFont();
		hSSFFont.setFontName("맑은 고딕");                 
		return hSSFFont;
	}
	
	HSSFCellStyle style_normal;
	public HSSFCellStyle getHSSFCellStyle(){
		HSSFCellStyle style_normal = (HSSFCellStyle) wb.createCellStyle();
		style_normal.setVerticalAlignment(CellStyle.VERTICAL_CENTER);
		style_normal.setFillForegroundColor(HSSFColor.WHITE.index);
		style_normal.setFillPattern(HSSFCellStyle.SOLID_FOREGROUND);
		
		style_normal.setBorderBottom(HSSFCellStyle.BORDER_THIN);
		style_normal.setBorderLeft(HSSFCellStyle.BORDER_THIN);
		style_normal.setBorderRight(HSSFCellStyle.BORDER_THIN);
		style_normal.setBorderTop(HSSFCellStyle.BORDER_THIN);
		style_normal.setFont(hSSFFont_10_normal);
		
		style_normal.setRightBorderColor(HSSFColor.GREY_50_PERCENT.index);
		style_normal.setLeftBorderColor(HSSFColor.GREY_50_PERCENT.index);
		style_normal.setTopBorderColor(HSSFColor.GREY_50_PERCENT.index);
		style_normal.setBottomBorderColor(HSSFColor.GREY_50_PERCENT.index);
		
		return style_normal;
	}
	
	HSSFCellStyle style_no_border;
	public HSSFCellStyle getHSSFCellStyleNoBorder(){
		HSSFCellStyle style = (HSSFCellStyle) wb.createCellStyle();
		style.setVerticalAlignment(CellStyle.VERTICAL_CENTER);
		style.setFillForegroundColor(HSSFColor.WHITE.index);
		style.setFillPattern(HSSFCellStyle.SOLID_FOREGROUND);
		
		return style;
	}
	
	public HSSFWorkbook getWb() {
		return wb;
	}

	HSSFCellStyle style_title;
	public HSSFCellStyle getCellTitleStyle() {
		if(style_title == null) style_title = getHSSFCellStyleNoBorder();
		style_title.setAlignment(CellStyle.ALIGN_CENTER);
		style_title.setFont(hSSFFont_18_bold);
		
		return style_title;
	}
	
	HSSFCellStyle style_sub_title;
	public HSSFCellStyle getCellSubTitleStyle(){
		return getCellSubTitleStyle(CellStyle.ALIGN_LEFT);
	}
	public HSSFCellStyle getCellSubTitleStyle(short alignType) {
		if(style_sub_title == null) style_sub_title = getHSSFCellStyleNoBorder();
		style_sub_title.setAlignment(alignType);
		style_sub_title.setFont(hSSFFont_10_bold);
		
		return style_sub_title;
	}
	
	HSSFCellStyle style_sub_title_right;
	public HSSFCellStyle getCellSubTitleRightStyle(){
		if(style_sub_title_right == null) style_sub_title_right = getHSSFCellStyleNoBorder();
		style_sub_title_right.setAlignment(CellStyle.ALIGN_RIGHT);
		style_sub_title_right.setFont(hSSFFont_10_bold);
		
		return style_sub_title_right;
	}
	
	/**
	 * 일반문자, 가운데 정렬, 테두리 없음
	 * @return
	 */
	HSSFCellStyle style_center_no_border;
	public HSSFCellStyle getCellContentCenterNoBorderStyle() {
		return getCellContentCenterNoBorderStyle((short)12);
	}
	public HSSFCellStyle getCellContentCenterNoBorderStyle(short fontSize) {
		if(style_center_no_border == null) style_center_no_border = getHSSFCellStyleNoBorder();
		style_center_no_border.setAlignment(CellStyle.ALIGN_CENTER);
		hSSFFont_normal.setFontHeightInPoints(fontSize);
		style_center_no_border.setFont(hSSFFont_normal);
	
		return style_center_no_border;
	}
	
	/**
	 * 일반문자, 왼쪽 정렬, 테두리 없음
	 * @return
	 */
	HSSFCellStyle style_left_no_border;
	public HSSFCellStyle getCellContentLeftNoBorderStyle() {
		return getCellContentLeftNoBorderStyle((short)12);
	}
	
	public HSSFCellStyle getCellContentLeftNoBorderStyle(short fontSize) {
		if(style_left_no_border == null) style_left_no_border = getHSSFCellStyleNoBorder();
		style_left_no_border.setAlignment(CellStyle.ALIGN_LEFT);
			
		hSSFFont_normal.setFontHeightInPoints(fontSize);
		style_left_no_border.setFont(hSSFFont_normal);
		
		return style_left_no_border;
		
	}
	
	/**
	 * 일반문자, 왼쪽 정렬, border-bottom, dot
	 * @return
	 */
	HSSFCellStyle style_center_border_bottom_dot;
	public HSSFCellStyle getCellContentBorderBottomDotStyle() {
		return getCellContentBorderBottomDotStyle(CellStyle.ALIGN_LEFT, (short)12, HSSFFont.BOLDWEIGHT_BOLD);
	}
	public HSSFCellStyle getCellContentBorderBottomDotStyle(short alignType, short fontSize, short fontWeight) {
		if(style_center_border_bottom_dot == null) style_center_border_bottom_dot = getHSSFCellStyleNoBorder();
		style_center_border_bottom_dot.setAlignment(alignType);
			
		style_center_border_bottom_dot.setBorderBottom(HSSFCellStyle.BORDER_DOTTED);
			
		hSSFFont_normal.setFontHeightInPoints(fontSize);
//		hSSFFont_normal.setBoldweight(fontWeight);
		style_center_border_bottom_dot.setFont(hSSFFont_normal);
		
		return style_center_border_bottom_dot;
		
	}
	
	
	/**
	 * 금액, 왼쪽정렬, no-border, normal
	 * @param fontSize
	 * @return
	 */
	public HSSFCellStyle getCellDataNumberStyle() {
		return getCellDataNumberStyle(CellStyle.ALIGN_RIGHT, (short)10);
	}
	
	HSSFCellStyle style_number;
	public HSSFCellStyle getCellDataNumberStyle(short alignType, short fontSize) {
		if(style_number == null) style_number = getHSSFCellStyle();
		style_number.setAlignment(alignType);
		
		HSSFDataFormat format = getWb().createDataFormat();
		style_number.setDataFormat(format.getFormat("#,##0"));
		
		hSSFFont_normal.setFontHeightInPoints(fontSize);
		style_number.setFont(hSSFFont_normal);
		
		return style_number;
	}
	
	public HSSFCellStyle getCellDataNumberStyle(short alignType, short fontSize, short borderBottom) {
		if(style_number == null) style_number = getHSSFCellStyle();
		style_number.setAlignment(alignType);
		
		HSSFDataFormat format = getWb().createDataFormat();
		style_number.setDataFormat(format.getFormat("#,##0"));
		
		hSSFFont_normal.setFontHeightInPoints(fontSize);
		style_number.setFont(hSSFFont_normal);
		style_number.setBorderBottom(borderBottom);
		
		return style_number;
	}
	
	/**
	 * 금액, 왼쪽정렬, no-border, normal, 빨간색
	 * @param fontSize
	 * @return
	 */
	HSSFCellStyle style_number_red;
	public HSSFCellStyle getCellDataNumberRedStyle() {
		return getCellDataNumberRedStyle(CellStyle.ALIGN_RIGHT, (short)10);
	}
	
	public HSSFCellStyle getCellDataNumberRedStyle(short alignType, short fontSize) {
		return getCellDataNumberRedStyle(alignType, fontSize, (short)0);
	}
	
	public HSSFCellStyle getCellDataNumberRedStyle(short alignType, short fontSize, short borderBottom) {
		if(style_number_red == null) style_number_red = getHSSFCellStyle();

		hSSFFont_10_red.setFontHeightInPoints(fontSize);
		style_number_red.setFont(hSSFFont_10_red);
		
		style_number_red.setAlignment(alignType);
		
		HSSFDataFormat format = getWb().createDataFormat();
		style_number_red.setDataFormat(format.getFormat("#,##0"));
		
		if(borderBottom != 0){
			style_number_red.setBorderBottom(borderBottom);
		}
		
		return style_number_red;
	}
	
	HSSFCellStyle style_number_border_bottom;
	public HSSFCellStyle getCellDataNumberBorderBottomStyle(short alignType, short fontSize, short borderBottom) {
		if(style_number_border_bottom == null) style_number_border_bottom = getHSSFCellStyleNoBorder();
		style_number_border_bottom.setAlignment(alignType);
		
		HSSFDataFormat format = getWb().createDataFormat();
		style_number_border_bottom.setDataFormat(format.getFormat("#,##0"));
		
		hSSFFont_normal.setFontHeightInPoints(fontSize);
		style_number_border_bottom.setFont(hSSFFont_normal);
		
		style_number_border_bottom.setBorderBottom(borderBottom);
		
		return style_number_border_bottom;
	}
	
	HSSFCellStyle style_header;
	public HSSFCellStyle getCellHeaderStyle() {
		if(style_header == null) style_header = getHSSFCellStyle();
		style_header.setFillForegroundColor(HSSFColor.LIGHT_GREEN.index);
		style_header.setAlignment(CellStyle.ALIGN_CENTER);
		
		return style_header;
	}
	
	
	HSSFCellStyle style_data_center;
	public HSSFCellStyle getCellDataCenterStyle() {
		if(style_data_center == null) style_data_center = getHSSFCellStyle();
		style_data_center.setAlignment(CellStyle.ALIGN_CENTER);
		
		return style_data_center;
	}
	
	HSSFCellStyle style_data_left;
	public HSSFCellStyle getCellDataLeftStyle() {
		if(style_data_left == null) style_data_left = getHSSFCellStyle();
		style_data_left.setAlignment(CellStyle.ALIGN_LEFT);
		
		return style_data_left;
	}
	
	HSSFCellStyle style_data_right;
	public HSSFCellStyle getCellDataRightStyle() {
		if(style_data_right == null) style_data_right = getHSSFCellStyle();
		style_data_right.setAlignment(CellStyle.ALIGN_RIGHT);
		
		return style_data_right;
	}

	HSSFCellStyle style_rate;
	public HSSFCellStyle getCellDataRateStyle() {
		return getCellDataRateStyle(CellStyle.ALIGN_RIGHT);
	}
	public HSSFCellStyle getCellDataRateStyle(short alignType) {
		if(style_rate == null) style_rate = getHSSFCellStyle();
		HSSFDataFormat format = getWb().createDataFormat();
		style_rate.setDataFormat(format.getFormat("#,###,##0.00"));
		
		style_rate.setAlignment(alignType);
		return style_rate;
	}
	
	HSSFCellStyle style_rate_red;
	public HSSFCellStyle getCellDataRateRedStyle() {
		return getCellDataRateRedStyle(CellStyle.ALIGN_RIGHT);
	}
	public HSSFCellStyle getCellDataRateRedStyle(short alignType) {
		if(style_rate_red == null) style_rate_red = getHSSFCellStyle();
		HSSFDataFormat format = getWb().createDataFormat();
		style_rate_red.setDataFormat(format.getFormat("#,###,##0.00"));
		
		HSSFPalette palette = getWb().getCustomPalette(); 
		short colorIndex = 45; 
		palette.setColorAtIndex(colorIndex, (byte)255, (byte)218, (byte)218); 
 
		style_rate_red.setFillForegroundColor(colorIndex); 

		style_rate_red.setAlignment(alignType);
		return style_rate_red;
	}
	
	HSSFCellStyle style_rate4;
	public HSSFCellStyle getCellDataRate4Style() {
		return getCellDataRate4Style(CellStyle.ALIGN_RIGHT);
	}
	public HSSFCellStyle getCellDataRate4Style(short alignType) {
		if(style_rate4 == null) style_rate4 = getHSSFCellStyle();
		HSSFDataFormat format = getWb().createDataFormat();
		style_rate4.setDataFormat(format.getFormat("#,###,##0.0000"));
		
		style_rate4.setAlignment(alignType);
		return style_rate4;
	}
	
	HSSFCellStyle style_date;
	public HSSFCellStyle getCellDataDateStyle() {
		if(style_date == null) style_date = getHSSFCellStyle();
		style_date.setAlignment(CellStyle.ALIGN_CENTER);
		HSSFDataFormat format = getWb().createDataFormat();
		style_date.setDataFormat(format.getFormat("yyyy-MM-dd"));
		
		return style_date;
	}
	
	HSSFCellStyle style_sub_title_date;
	public HSSFCellStyle getCellSubTitleDateStyle() {
		if(style_sub_title_date == null) style_sub_title_date = getHSSFCellStyle();
		style_sub_title_date.setAlignment(CellStyle.ALIGN_LEFT);
		HSSFDataFormat format = getWb().createDataFormat();
		style_sub_title_date.setDataFormat(format.getFormat("yyyy-MM-dd"));
		
		return style_sub_title_date;
	}
}