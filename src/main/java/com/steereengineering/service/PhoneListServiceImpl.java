package com.steereengineering.service;

import java.util.LinkedHashSet;
import java.util.Optional;
import java.util.Set;
import java.util.List;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import lombok.extern.slf4j.Slf4j;

import com.steereengineering.command.PhoneListCommand;
import com.steereengineering.converter.PhoneListCommandToPhoneList;
import com.steereengineering.converter.PhoneListToPhoneListCommand;
import com.steereengineering.model.*;
import com.steereengineering.repository.PhoneListRepository;

import java.io.FileOutputStream;
import java.io.IOException;
import java.math.BigInteger;

import org.apache.poi.xwpf.usermodel.*;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.*;



@Slf4j
@Service
public class PhoneListServiceImpl implements PhoneListService {
	private final PhoneListRepository phoneListRepository;
	private final PhoneListCommandToPhoneList  phoneListCommandToPhoneList;
	private final PhoneListToPhoneListCommand  phoneListToPhoneListCommand; 



	public PhoneListServiceImpl(PhoneListRepository phoneListRepository,PhoneListCommandToPhoneList  phoneListCommandToPhoneList, PhoneListToPhoneListCommand  phoneListToPhoneListCommand) {
		this.phoneListRepository = phoneListRepository;
		this.phoneListCommandToPhoneList = phoneListCommandToPhoneList;
		this.phoneListToPhoneListCommand = phoneListToPhoneListCommand;
	}

	@Override
	public Set<PhoneList> getPhoneList() {
		log.debug("I'm in the service");

		Set<PhoneList> phoneListSet = new LinkedHashSet<PhoneList>();

		phoneListRepository.getOrderByFirstname().iterator().forEachRemaining(phoneListSet::add);
		for(PhoneList phone : phoneListSet) {
			System.out.println(phone.toString());
		}

		return phoneListSet;
	}

	@Override
	public PhoneList findById(Long l) {

		Optional<PhoneList> phoneListOptional = phoneListRepository.findById(l);

		if (!phoneListOptional.isPresent()) {
			throw new RuntimeException("Name Not Found!");
		}

		return phoneListOptional.get();
	}

	@Override
	@Transactional
	public PhoneListCommand savePhoneListCommand(PhoneListCommand command) {
		PhoneList detachedPhoneList = phoneListCommandToPhoneList.convert(command);

		PhoneList savePhoneList = phoneListRepository.save(detachedPhoneList);
		log.debug("Saved RecipeID:" + savePhoneList.getId());
		return phoneListToPhoneListCommand.convert(savePhoneList);
	}

	@Override
	@Transactional
	public PhoneListCommand findCommandById(Long l) {
		return phoneListToPhoneListCommand.convert(findById(l));
	}

	@Override
	public void deleteById(Long idToDelete) {
		phoneListRepository.deleteById(idToDelete);
	}

	/* (non-Javadoc)
	 * @see com.steereengineering.service.PhoneListService#BuildAsposePhonelistWordDocument()
	 */
	@Override
	public String BuildPOIPhonelistWordDocument() {
		String dataPath = "./src/main/resources/";
		List<PhoneList> phonelist = phoneListRepository.getOrderByFirstname(); 
		//Write the Document in file system
		FileOutputStream out;
		try {
			// Create a new document from scratch
			XWPFDocument document= new XWPFDocument();
			//Create a blank spreadsheet
			XWPFParagraph paragraph = document.createParagraph();
			XWPFRun run = paragraph.createRun();
			run.setText("At tutorialspoint.com, we strive hard to " +
					"provide quality tutorials for self-learning " +
					"purpose in the domains of Academics, Information " +
					"Technology, Management and Computer Programming Languages.");

			// -- OR --
			// open an existing empty document with styles already defined
			//XWPFDocument document= new XWPFDocument(new FileInputStream("base_document.docx"));
			// Create a new table with 6 rows and 3 columns
			int nRows = phonelist.size() + 1;
			int nCols = 7;
			XWPFTable table = document.createTable(nRows, nCols);
			// Set the table style. If the style is not defined, the table style
			// will become "Normal".
			CTTblPr tblPr = table.getCTTbl().getTblPr();
			CTString styleStr = tblPr.addNewTblStyle();
			styleStr.setVal("StyledTable");
			// Get a list of the rows in the table
			List<XWPFTableRow> rows = table.getRows();
			int rowCt = 0;
			int colCt = 0;
			for (XWPFTableRow row : rows) {
				// get table row properties (trPr)
				CTTrPr trPr = row.getCtRow().addNewTrPr();
				// set row height; units = twentieth of a point, 360 = 0.25"
				CTHeight ht = trPr.addNewTrHeight();
				ht.setVal(BigInteger.valueOf(360));
				// get the cells in this row
				List<XWPFTableCell> cells = row.getTableCells();
				// add content to each cell
				for (XWPFTableCell cell : cells) {
					// get a table cell properties element (tcPr)
					CTTcPr tcpr = cell.getCTTc().addNewTcPr();
					// set vertical alignment to "center"
					CTVerticalJc va = tcpr.addNewVAlign();
					va.setVal(STVerticalJc.CENTER);
					// create cell color element
					CTShd ctshd = tcpr.addNewShd();
					ctshd.setColor("auto");
					ctshd.setVal(STShd.CLEAR);
					if (rowCt == 0) {
						// header row
						ctshd.setFill("A7BFDE");
					} else if (rowCt % 2 == 0) {
						// even row
						ctshd.setFill("D3DFEE");
					} else {
						// odd row
						ctshd.setFill("EDF2F8");
					}
					// get 1st paragraph in cell's paragraph list
					XWPFParagraph para = cell.getParagraphs().get(0);
					// create a run to contain the content
					XWPFRun rh = para.createRun();
					// style cell as desired
					if (colCt == nCols - 1) {
						// last column is 10pt Courier
						rh.setFontSize(10);
						rh.setFontFamily("Courier");
					}
					if (rowCt == 0) {
						// header row
						rh.setText("header row, col " + colCt);
						rh.setBold(true);
						para.setAlignment(ParagraphAlignment.CENTER);
					} else if (rowCt % 2 == 0) {
						// even row
						rh.setText("row " + rowCt + ", col " + colCt);
						para.setAlignment(ParagraphAlignment.LEFT);
					} else {
						// odd row
						rh.setText("row " + rowCt + ", col " + colCt);
						para.setAlignment(ParagraphAlignment.LEFT);
					}
					colCt++;
				}
				// for cell  
				colCt = 0;
				rowCt++;
			}
			// for row
			// write the file
			out = new FileOutputStream(dataPath +"Apache_styledTable.docx");
			document.write(out);
			document.close();
			out.close();
			System.out.println("Process Completed Successfully");
		} catch (IOException e) {
			e.getMessage();
			e.getMessage();
		}

		return  new String(dataPath +"Apache_styledTable.docx");
	}
}  	
