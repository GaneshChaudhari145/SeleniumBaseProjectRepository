package com.aqm.staf.framework.core;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Properties;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import org.apache.commons.io.FileUtils;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.hibernate.service.ServiceRegistry;
import org.testng.TestNG;
import org.testng.xml.XmlClass;
import org.testng.xml.XmlSuite;
import org.testng.xml.XmlTest;

import com.aqm.staf.framework.core.entity.Application;
import com.aqm.staf.framework.core.entity.ExecutionMasterTestSuites;
import com.aqm.staf.framework.core.entity.ExecutionTestScenarios;
import com.aqm.staf.framework.core.entity.ExecutionTestSteps;
import com.aqm.staf.framework.core.entity.ExecutionTestSuites;
import com.aqm.staf.framework.core.entity.MasterAutomationScript;
import com.aqm.staf.framework.core.entity.MasterAutomationScriptStep;
import com.aqm.staf.framework.core.entity.MasterTestSuite;
import com.aqm.staf.framework.core.entity.Module;
import com.aqm.staf.framework.core.entity.Project;
import com.aqm.staf.framework.core.entity.RuntimeConfigParameter;
import com.aqm.staf.framework.core.entity.TestPlan;
import com.aqm.staf.framework.core.entity.TestScenario;
import com.aqm.staf.framework.core.entity.TestSuite;
import com.aqm.staf.framework.core.exceptions.DataUploadException;
import com.aqm.staf.framework.core.hibernate.TestDataHibernateUtil;
import com.aqm.staf.framework.core.hibernate.TestManagerHibernateUtil;
import org.apache.commons.io.FileUtils;

public class Uploader extends JFrame {
	private static final long serialVersionUID = 1L;

	private Properties prop;
	private InputStream input;

	private Properties tableConfigProp;
	private InputStream tableConfigInput;

	private JLabel lb_baseAutomationFile;
	private JTextField tf_baseAutomationFile;
	private JButton btn_selectAutomationFile;
	private Box bx_automationFileGroup;
	private JPanel pan_northPanel;

	private JButton btn_startUpload;
	private JButton btn_stopUpload;
	private Box bx_startStopGroup;
	private JPanel pan_southPanel;

	private Configuration configurationTestManager;
	private ServiceRegistry serviceRegistryTestManager;
	private SessionFactory factoryTestManager;
	private static Session sessionTestManager;  

	private Configuration configurationTestData;
	private ServiceRegistry serviceRegistryTestData;
	private SessionFactory factoryTestData;
	private static Session sessionTestData;

	private Connection dataDBConnectionObject;

	private boolean nonGUIMode;

	private File currentExecutionResultsFolderReference;
	private File currentScenarioExecutionResultsFolderReference;
	private File currentTestNGExecutionResultsFolderReference;
	public File currentTestAutomationSuitesAndTestDataFolderReference;

	//private String remoteMasterTestSuiteFolderForExecution;
	private String baseMasterTestSuiteFolderForExecution;
	private String baseMasterTestSuiteFileForExecution;

	private TestPlan currentExecutionTestPlan;
	private TestManagerDBInterface testManagerDBInterface;

	private boolean uploadAutomationTestDataTablesForSpecific = false;

	public String currentExecutionResultsFolder;

	public Uploader() throws IOException {
		nonGUIMode = true;
		loadProperties();

		//this.remoteMasterTestSuiteFolderForExecution = prop.getProperty("RemoteMasterTestSuiteFolderForExecution");
		this.baseMasterTestSuiteFolderForExecution = prop.getProperty("BaseMasterTestSuiteFolderForExecution");
		this.baseMasterTestSuiteFileForExecution = prop.getProperty("BaseMasterTestSuiteFileForExecution");

		//FileUtils.cleanDirectory(new File(baseMasterTestSuiteFolderForExecution));
		//FileUtils.copyDirectory(new File(remoteMasterTestSuiteFolderForExecution), new File(baseMasterTestSuiteFolderForExecution));

		createFolderStructureForResults(getExecutionResultsBaseFolder());
		generateTestManagerDBSessions();
	}

	public Uploader(String title) throws IOException {
		super(title);
		nonGUIMode = false;
		loadProperties();
		this.baseMasterTestSuiteFolderForExecution = prop.getProperty("BaseMasterTestSuiteFolderForExecution");
		this.baseMasterTestSuiteFileForExecution = prop.getProperty("BaseMasterTestSuiteFileForExecution");
		createFolderStructureForResults(getExecutionResultsBaseFolder());

		lb_baseAutomationFile = new JLabel("Select the Base Automation File");
		tf_baseAutomationFile = new JTextField(40);
		tf_baseAutomationFile.setEnabled(false);
		tf_baseAutomationFile.setText(baseMasterTestSuiteFolderForExecution + "\\" + baseMasterTestSuiteFileForExecution);
		btn_selectAutomationFile = new JButton("...");
		btn_selectAutomationFile.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				// Please write the code to set the following variables individually
				// this.baseMasterTestSuiteFolderForExecution = //Set the Folder Location
				// this.baseMasterTestSuiteFileForExecution = //Set the File Name with File Extention Only.
				// and then set the Textfield to the concatenated value
			}
		});

		bx_automationFileGroup = Box.createHorizontalBox();
		bx_automationFileGroup.add(lb_baseAutomationFile);
		bx_automationFileGroup.add(Box.createHorizontalStrut(15));
		bx_automationFileGroup.add(tf_baseAutomationFile);
		bx_automationFileGroup.add(Box.createHorizontalStrut(15));
		bx_automationFileGroup.add(btn_selectAutomationFile);
		pan_northPanel = new JPanel();
		pan_northPanel.add(bx_automationFileGroup);

		btn_startUpload = new JButton("Start Upload");		
		btn_stopUpload = new JButton("Stop Upload");

		bx_startStopGroup = Box.createHorizontalBox();
		bx_startStopGroup.add(btn_startUpload);
		bx_startStopGroup.add(Box.createHorizontalStrut(15));
		bx_startStopGroup.add(btn_stopUpload);
		pan_southPanel = new JPanel();
		pan_southPanel.add(bx_startStopGroup);

		btn_startUpload.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				System.out.println("Upload Started");
				btn_startUpload.setEnabled(false);
				try {
					uploadDataFromExcelToDatabase();
				} catch (FileNotFoundException fnfe) {
					fnfe.printStackTrace();
				} catch (IOException ioe) {
					ioe.printStackTrace();
				} catch (Exception ex) {
					ex.printStackTrace();
				}
				btn_startUpload.setEnabled(true);
				System.out.println("Upload Ended");
			}
		});

		btn_stopUpload.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

			}
		});

		getContentPane().setLayout(new BoxLayout(getContentPane(),BoxLayout.Y_AXIS));

		getContentPane().add(pan_northPanel);
		getContentPane().add(pan_southPanel);

		setSize(750, 125);

		addWindowListener(new WindowAdapter() {
			public void windowOpened(WindowEvent we){
			}
			public void windowClosing(WindowEvent we){
				System.out.println("Window Closing..");
			}
			public void windowClosed(WindowEvent we){
				System.out.println("Window Closed..");
				setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
				dispose();
				System.exit(0);
			}
		});

		generateTestManagerDBSessions();
		setVisible(true);
	}

	private void generateTestManagerDBSessions() {
		sessionTestManager = TestManagerHibernateUtil.getInstance().getCurrentSession();
		testManagerDBInterface = new TestManagerDBInterface(sessionTestManager);
	}

	private void generateTestDataDBSession(File hbmFolderPath) {
		TestDataHibernateUtil.getInstance().getConfiguration();
		TestDataHibernateUtil.getInstance().attachHBMS(hbmFolderPath);
		sessionTestData = TestDataHibernateUtil.getInstance().getCurrentSession();
	}

	public void closeDBSessions() {
		sessionTestManager.close();
		sessionTestData.close();
		String jenkinsStatus=prop.getProperty("JenkinsExecution");
		if(jenkinsStatus.equals("false")){
			try {
				FileUtils.deleteDirectory(currentTestAutomationSuitesAndTestDataFolderReference);
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}

	private void loadProperties() throws IOException {
		prop = new Properties();
		input = new FileInputStream("config.properties");
		prop.load(input);

		tableConfigProp = new Properties();
		tableConfigInput = new FileInputStream("tableconfig.properties");
		tableConfigProp.load(tableConfigInput);
	}

	public String getExecutionResultsBaseFolder() {
		return prop.getProperty("ExecutionDirectory");
	}

	public void uploadDataFromExcelToDatabase() throws Exception {
		String masterTestSuiteExcelFilePath;				
		File masterTestSuiteExcelFileReference;
		FileInputStream masterTestSuiteFileInputStreamReference;
		XSSFWorkbook masterTestSuiteWorkBookReference;
		XSSFSheet projectDataWorksheet;
		XSSFSheet applicationDataWorksheet;
		XSSFSheet moduleDataWorksheet;
		XSSFSheet masterAutomationScriptDataWorksheet;
		XSSFSheet masterAutomationScriptStepDataWorksheet;
		XSSFSheet masterTestSuiteDataWorksheet;
		XSSFSheet testSuiteDataWorksheet;
		XSSFSheet testDataUploadDataWorksheet;

		File testSuiteExcelFileReference;
		FileInputStream testSuiteFileInputStreamReference;
		XSSFWorkbook testSuiteWorkBookReference;
		XSSFSheet testScenarioDataWorksheet;

		File hbmFolderPath = new File("./hbms");

		boolean uploadTestManagementTables = Boolean.parseBoolean(prop.getProperty("UploadTestManagementTables"));

		boolean uploadAllDataTables = Boolean.parseBoolean(prop.getProperty("UploadAllDataTables"));
		boolean uploadAutomationTestDataTables = Boolean.parseBoolean(prop.getProperty("UploadAutomationTestDataTables"));

		//@rbd

		uploadAutomationTestDataTablesForSpecific = uploadAutomationTestDataTables;

		if (nonGUIMode) 
			masterTestSuiteExcelFilePath = this.baseMasterTestSuiteFolderForExecution + "\\" + this.baseMasterTestSuiteFileForExecution;
		else 
			masterTestSuiteExcelFilePath = tf_baseAutomationFile.getText().replace("\\", "/");

		masterTestSuiteExcelFileReference = new File(masterTestSuiteExcelFilePath);
		masterTestSuiteFileInputStreamReference = new FileInputStream(masterTestSuiteExcelFileReference);

		masterTestSuiteWorkBookReference = new XSSFWorkbook (masterTestSuiteFileInputStreamReference);

		if (uploadTestManagementTables) {
			//Projects Table
			projectDataWorksheet = masterTestSuiteWorkBookReference.getSheet("ProjectDetails");

			int projectReferenceColumn = Integer.parseInt(tableConfigProp.getProperty("ProjectReferenceColumn"));
			int projectDescriptionColumn = Integer.parseInt(tableConfigProp.getProperty("ProjectDescriptionColumn"));
			int projectSerialNumberColumn = Integer.parseInt(tableConfigProp.getProperty("ProjectSerialNumberColumn"));

			Iterator<Row> projectIterator = projectDataWorksheet.iterator();

			projectIterator.next();
			while (projectIterator.hasNext()) {
				Row projectRow = projectIterator.next();

				String projectReference = projectRow.getCell(projectReferenceColumn).getStringCellValue();
				String projectDescription = projectRow.getCell(projectDescriptionColumn).getStringCellValue();
				int projectSerialNumber = (int) projectRow.getCell(projectSerialNumberColumn).getNumericCellValue();

				Project p = new Project();
				p.setProjectReference(projectReference);
				p.setProjectDescription(projectDescription);
				p.setProjectSerialNumber(projectSerialNumber);

				testManagerDBInterface.addUpdateProject(p);
			}

			//Applications Table
			applicationDataWorksheet = masterTestSuiteWorkBookReference.getSheet("Applications");

			int applicationReferenceColumn = Integer.parseInt(tableConfigProp.getProperty("ApplicationReferenceColumn"));
			int applicationProjectReferenceColumn = Integer.parseInt(tableConfigProp.getProperty("ApplicationProjectReferenceColumn"));
			int applicationDescriptionColumn = Integer.parseInt(tableConfigProp.getProperty("ApplicationDescriptionColumn"));
			int applicationSerialNumberColumn = Integer.parseInt(tableConfigProp.getProperty("ApplicationSerialNumberColumn"));

			Iterator<Row> applicationIterator = applicationDataWorksheet.iterator();

			applicationIterator.next();
			while (applicationIterator.hasNext()) {
				Row applicationRow = applicationIterator.next();

				String applicationReference = applicationRow.getCell(applicationReferenceColumn).getStringCellValue();
				String applicationProjectReference = applicationRow.getCell(applicationProjectReferenceColumn).getStringCellValue();
				String applicationDescription = applicationRow.getCell(applicationDescriptionColumn).getStringCellValue();
				int applicationSerialNumber = (int) applicationRow.getCell(applicationSerialNumberColumn).getNumericCellValue();

				Project projectByReference = testManagerDBInterface.getProjectByReference(applicationProjectReference);

				if (projectByReference!=null) {
					Application app = new Application();
					app.setApplicationReference(applicationReference);
					app.setApplicationDescription(applicationDescription);
					app.setApplicationProjectID(projectByReference.getProjectID());
					app.setApplicationProjectReference(applicationProjectReference);
					app.setApplicationSerialNumber(applicationSerialNumber);

					testManagerDBInterface.addUpdateApplication(app);
				}
				else {
					System.out.println("Project not found..");
					break;
				}
			}

			//Modules Table
			moduleDataWorksheet = masterTestSuiteWorkBookReference.getSheet("Modules");

			int moduleReferenceColumn = Integer.parseInt(tableConfigProp.getProperty("ModuleReferenceColumn"));
			int moduleApplicationReferenceColumn = Integer.parseInt(tableConfigProp.getProperty("ModuleApplicationReferenceColumn"));
			int moduleDescriptionColumn = Integer.parseInt(tableConfigProp.getProperty("ModuleDescriptionColumn"));
			int moduleSerialNumberColumn = Integer.parseInt(tableConfigProp.getProperty("ModuleSerialNumberColumn"));

			Iterator<Row> moduleIterator = moduleDataWorksheet.iterator();

			moduleIterator.next();
			while (moduleIterator.hasNext()) {
				Row moduleRow = moduleIterator.next();

				String moduleReference = moduleRow.getCell(moduleReferenceColumn).getStringCellValue();
				String moduleApplicationReference = moduleRow.getCell(moduleApplicationReferenceColumn).getStringCellValue();
				String moduleDescription = moduleRow.getCell(moduleDescriptionColumn).getStringCellValue();
				int moduleSerialNumber = (int) moduleRow.getCell(moduleSerialNumberColumn).getNumericCellValue();

				Application applicationByReference = testManagerDBInterface.getApplicationByReference(moduleApplicationReference);

				if (applicationByReference!=null) {
					Module mod = new Module();
					mod.setModuleReference(moduleReference);
					mod.setModuleDescription(moduleDescription);
					mod.setModuleSerialNumber(moduleSerialNumber);
					mod.setModuleProjectID(applicationByReference.getApplicationProjectID());
					mod.setModuleProjectReference(applicationByReference.getApplicationProjectReference());
					mod.setModuleApplicationID(applicationByReference.getApplicationID());
					mod.setModuleApplicationReference(applicationByReference.getApplicationReference());

					testManagerDBInterface.addUpdateModule(mod);
				}
				else {
					System.out.println("Application not found..");
					break;
				}
			}

			//Master Automation Scripts
			masterAutomationScriptDataWorksheet = masterTestSuiteWorkBookReference.getSheet("MasterAutomationScripts");

			int masterAutomationScriptReferenceColumn = Integer.parseInt(tableConfigProp.getProperty("MasterAutomationScriptReferenceColumn"));
			int masterAutomationScriptDescriptionColumn = Integer.parseInt(tableConfigProp.getProperty("MasterAutomationScriptDescriptionColumn"));
			int masterAutomationScriptSerialNumberColumn = Integer.parseInt(tableConfigProp.getProperty("MasterAutomationScriptSerialNumberColumn"));

			Iterator<Row> masterAutomationScriptIterator = masterAutomationScriptDataWorksheet.iterator();

			masterAutomationScriptIterator.next();
			while (masterAutomationScriptIterator.hasNext()) {
				Row masterAutomationScriptRow = masterAutomationScriptIterator.next();

				String masterAutomationScriptReference = masterAutomationScriptRow.getCell(masterAutomationScriptReferenceColumn).getStringCellValue();
				String masterAutomationScriptDescription = masterAutomationScriptRow.getCell(masterAutomationScriptDescriptionColumn).getStringCellValue();
				int masterAutomationScriptSerialNumber = (int) masterAutomationScriptRow.getCell(masterAutomationScriptSerialNumberColumn).getNumericCellValue();

				MasterAutomationScript masterAutomationScript = new MasterAutomationScript();
				masterAutomationScript.setMasterAutomationScriptReference(masterAutomationScriptReference);
				masterAutomationScript.setMasterAutomationScriptDescription(masterAutomationScriptDescription);
				masterAutomationScript.setMasterAutomationScriptSerialNumber(masterAutomationScriptSerialNumber);

				testManagerDBInterface.addUpdateMasterAutomationScript(masterAutomationScript);
			}		

			//Master Automation Script Steps
			masterAutomationScriptStepDataWorksheet = masterTestSuiteWorkBookReference.getSheet("MasterAutomationScriptSteps");

			int masterAutomationScriptStepReferenceColumn = Integer.parseInt(tableConfigProp.getProperty("MasterAutomationScriptStepReferenceColumn"));
			int masterAutomationScriptStepAutomationScriptReferenceColumn = Integer.parseInt(tableConfigProp.getProperty("MasterAutomationScriptStepAutomationScriptReferenceColumn"));
			int masterAutomationScriptStepStepKeywordColumn = Integer.parseInt(tableConfigProp.getProperty("MasterAutomationScriptStepStepKeywordColumn"));
			int masterAutomationScriptStepConfigDataColumn = Integer.parseInt(tableConfigProp.getProperty("MasterAutomationScriptStepConfigDataColumn"));
			int masterAutomationScriptStepStepGroupColumn = Integer.parseInt(tableConfigProp.getProperty("MasterAutomationScriptStepStepGroupColumn"));
			int masterAutomationScriptStepSerialNumberColumn = Integer.parseInt(tableConfigProp.getProperty("MasterAutomationScriptStepSerialNumberColumn"));
			int masterAutomationScriptStepExecutionSequenceColumn = Integer.parseInt(tableConfigProp.getProperty("MasterAutomationScriptStepExecutionSequenceColumn"));
			int masterAutomationScriptStepSkipStepColumn = Integer.parseInt(tableConfigProp.getProperty("MasterAutomationScriptStepSkipStepColumn"));
			int masterAutomationScriptStepToBeExecutedByMachineColumn = Integer.parseInt(tableConfigProp.getProperty("MasterAutomationScriptStepToBeExecutedByMachineColumn"));

			Iterator<Row> masterAutomationScriptStepIterator = masterAutomationScriptStepDataWorksheet.iterator();

			masterAutomationScriptStepIterator.next();
			while (masterAutomationScriptStepIterator.hasNext()) {
				Row masterAutomationScriptStepRow = masterAutomationScriptStepIterator.next();

				String masterAutomationScriptStepReference = masterAutomationScriptStepRow.getCell(masterAutomationScriptStepReferenceColumn).getStringCellValue();
				String masterAutomationScriptStepAutomationScriptReference = masterAutomationScriptStepRow.getCell(masterAutomationScriptStepAutomationScriptReferenceColumn).getStringCellValue();
				String masterAutomationScriptStepStepKeyword = masterAutomationScriptStepRow.getCell(masterAutomationScriptStepStepKeywordColumn).getStringCellValue();
				String masterAutomationScriptStepConfigData = masterAutomationScriptStepRow.getCell(masterAutomationScriptStepConfigDataColumn).getStringCellValue();
				String masterAutomationScriptStepStepGroup = masterAutomationScriptStepRow.getCell(masterAutomationScriptStepStepGroupColumn).getStringCellValue();
				int masterAutomationScriptStepSerialNumber = (int) masterAutomationScriptStepRow.getCell(masterAutomationScriptStepSerialNumberColumn).getNumericCellValue();
				int masterAutomationScriptStepExecutionSequence = (int) masterAutomationScriptStepRow.getCell(masterAutomationScriptStepExecutionSequenceColumn).getNumericCellValue();
				String masterAutomationScriptStepSkipStep = masterAutomationScriptStepRow.getCell(masterAutomationScriptStepSkipStepColumn).getStringCellValue();
				String masterAutomationScriptStepToBeExecutedByMachine = masterAutomationScriptStepRow.getCell(masterAutomationScriptStepToBeExecutedByMachineColumn).getStringCellValue();

				MasterAutomationScript masterAutomationScript = testManagerDBInterface.getMasterAutomationScriptByReference(masterAutomationScriptStepAutomationScriptReference);

				MasterAutomationScriptStep masterAutomationScriptStep = new MasterAutomationScriptStep();
				masterAutomationScriptStep.setMasterAutomationScriptStepReference(masterAutomationScriptStepReference);
				masterAutomationScriptStep.setMasterAutomationScriptStepAutomationScriptID(masterAutomationScript.getMasterAutomationScriptID());
				masterAutomationScriptStep.setMasterAutomationScriptStepAutomationScriptReference(masterAutomationScript.getMasterAutomationScriptReference());
				masterAutomationScriptStep.setMasterAutomationScriptStepStepKeyword(masterAutomationScriptStepStepKeyword);
				masterAutomationScriptStep.setMasterAutomationScriptStepConfigData(masterAutomationScriptStepConfigData);
				masterAutomationScriptStep.setMasterAutomationScriptStepStepGroup(masterAutomationScriptStepStepGroup);
				masterAutomationScriptStep.setMasterAutomationScriptStepSerialNumber(masterAutomationScriptStepSerialNumber);
				masterAutomationScriptStep.setMasterAutomationScriptStepExecutionSequence(masterAutomationScriptStepExecutionSequence);
				masterAutomationScriptStep.setMasterAutomationScriptStepSkipStep(masterAutomationScriptStepSkipStep);
				masterAutomationScriptStep.setMasterAutomationScriptStepToBeExecutedByMachine(masterAutomationScriptStepToBeExecutedByMachine);

				testManagerDBInterface.addUpdateMasterAutomationScriptStep(masterAutomationScriptStep);
			}

			//Master Test Suite
			masterTestSuiteDataWorksheet = masterTestSuiteWorkBookReference.getSheet("MasterTestSuites");

			int masterTestSuiteReferenceColumn = Integer.parseInt(tableConfigProp.getProperty("MasterTestSuiteReferenceColumn"));
			int masterTestSuiteApplicationReferenceColumn = Integer.parseInt(tableConfigProp.getProperty("MasterTestSuiteApplicationReferenceColumn"));
			int masterTestSuiteDescriptionColumn = Integer.parseInt(tableConfigProp.getProperty("MasterTestSuiteDescriptionColumn"));
			int masterTestSuiteSerialNumberColumn = Integer.parseInt(tableConfigProp.getProperty("MasterTestSuiteSerialNumberColumn"));

			Iterator<Row> masterTestSuiteIterator = masterTestSuiteDataWorksheet.iterator();

			masterTestSuiteIterator.next();
			while (masterTestSuiteIterator.hasNext()) {
				Row masterTestSuiteRow = masterTestSuiteIterator.next();

				String masterTestSuiteReference = masterTestSuiteRow.getCell(masterTestSuiteReferenceColumn).getStringCellValue();
				String masterTestSuiteApplicationReference = masterTestSuiteRow.getCell(masterTestSuiteApplicationReferenceColumn).getStringCellValue();
				String masterTestSuiteDescription = masterTestSuiteRow.getCell(masterTestSuiteDescriptionColumn).getStringCellValue();
				int masterTestSuiteSerialNumber = (int) masterTestSuiteRow.getCell(masterTestSuiteSerialNumberColumn).getNumericCellValue();

				Application applicationByReference = testManagerDBInterface.getApplicationByReference(masterTestSuiteApplicationReference);

				MasterTestSuite mts = new MasterTestSuite();
				mts.setMasterTestSuiteReference(masterTestSuiteReference);
				mts.setMasterTestSuiteProjectID(applicationByReference.getApplicationProjectID());
				mts.setMasterTestSuiteProjectReference(applicationByReference.getApplicationProjectReference());
				mts.setMasterTestSuiteApplicationID(applicationByReference.getApplicationID());
				mts.setMasterTestSuiteApplicationReference(applicationByReference.getApplicationReference());
				mts.setMasterTestSuiteDescription(masterTestSuiteDescription);
				mts.setMasterTestSuiteSerialNumber(masterTestSuiteSerialNumber);
				mts.setMasterTestSuiteRepositoryFilePath(masterTestSuiteExcelFilePath);
				mts.setMasterTestSuiteRepositoryAbsoluteFolderPath(masterTestSuiteExcelFileReference.getParentFile().getAbsolutePath().replace("\\", "//"));

				testManagerDBInterface.addUpdateMasterTestSuite(mts);
			}		

			//Test Suite
			testSuiteDataWorksheet = masterTestSuiteWorkBookReference.getSheet("TestSuites");

			int testSuiteReferenceColumn = Integer.parseInt(tableConfigProp.getProperty("TestSuiteReferenceColumn"));
			int testSuiteMasterTestSuiteReferenceColumn = Integer.parseInt(tableConfigProp.getProperty("TestSuiteMasterTestSuiteReferenceColumn"));
			int testSuiteModuleReferenceColumn = Integer.parseInt(tableConfigProp.getProperty("TestSuiteModuleReferenceColumn"));
			int testSuiteDescriptionColumn = Integer.parseInt(tableConfigProp.getProperty("TestSuiteDescriptionColumn"));
			int testSuiteRepositoryFileColumn = Integer.parseInt(tableConfigProp.getProperty("TestSuiteRepositoryFileColumn"));
			int testSuiteExecutionPriorityColumn = Integer.parseInt(tableConfigProp.getProperty("TestSuiteExecutionPriorityColumn"));
			int testSuiteConfigurationReferenceColumn = Integer.parseInt(tableConfigProp.getProperty("TestSuiteConfigurationReferenceColumn"));
			int testSuiteExecutionModeColumn = Integer.parseInt(tableConfigProp.getProperty("TestSuiteExecutionModeColumn"));
			int testSuiteSerialNumberColumn = Integer.parseInt(tableConfigProp.getProperty("TestSuiteSerialNumberColumn"));
			int testSuiteExecuteTestSuiteColumn = Integer.parseInt(tableConfigProp.getProperty("TestSuiteExecuteTestSuiteColumn"));

			Iterator<Row> testSuiteIterator = testSuiteDataWorksheet.iterator();

			testSuiteIterator.next();
			while (testSuiteIterator.hasNext()) {
				Row testSuiteRow = testSuiteIterator.next();

				String testSuiteReference = testSuiteRow.getCell(testSuiteReferenceColumn).getStringCellValue();
				String testSuiteMasterTestSuiteReference = testSuiteRow.getCell(testSuiteMasterTestSuiteReferenceColumn).getStringCellValue();
				String testSuiteModuleReference = testSuiteRow.getCell(testSuiteModuleReferenceColumn).getStringCellValue();
				String testSuiteDescription = testSuiteRow.getCell(testSuiteDescriptionColumn).getStringCellValue();
				String testSuiteRepositoryFile = testSuiteRow.getCell(testSuiteRepositoryFileColumn).getStringCellValue();
				int testSuiteExecutionPriority = (int) testSuiteRow.getCell(testSuiteExecutionPriorityColumn).getNumericCellValue();
				String testSuiteConfigurationReference = testSuiteRow.getCell(testSuiteConfigurationReferenceColumn).getStringCellValue();
				String testSuiteExecutionMode = testSuiteRow.getCell(testSuiteExecutionModeColumn).getStringCellValue();
				int testSuiteSerialNumber = (int) testSuiteRow.getCell(testSuiteSerialNumberColumn).getNumericCellValue();
				// Added by PKP
				String testSuiteExecuteTestSuite = testSuiteRow.getCell(testSuiteExecuteTestSuiteColumn).getStringCellValue();

				if (testSuiteExecuteTestSuite.equalsIgnoreCase("Yes")){
					MasterTestSuite masterTestSuiteByReference = testManagerDBInterface.getMasterTestSuiteByReference(testSuiteMasterTestSuiteReference);
					Module moduleByReference = testManagerDBInterface.getModuleByReference(testSuiteModuleReference);

					//String testSuiteExcelFilePath = masterTestSuiteExcelFileReference.getParentFile().getAbsolutePath().replace("\\", "/") + testSuiteRepositoryFile.replace("\\", "/");
					String testSuiteExcelFilePath = testSuiteRepositoryFile.replace("\\", "\\");
					testSuiteExcelFileReference = new File(masterTestSuiteExcelFileReference.getParentFile().getAbsolutePath().replace("\\", "/") + testSuiteExcelFilePath);
					System.out.println("Uploading TestSuite File :=> "+ testSuiteExcelFilePath);
					TestSuite ts = new TestSuite();
					ts.setTestSuiteReference(testSuiteReference);
					ts.setTestSuiteProjectID(moduleByReference.getModuleProjectID());
					ts.setTestSuiteProjectReference(moduleByReference.getModuleProjectReference());
					ts.setTestSuiteApplicationID(moduleByReference.getModuleApplicationID());
					ts.setTestSuiteApplicationReference(moduleByReference.getModuleApplicationReference());
					ts.setTestSuiteModuleID(moduleByReference.getModuleID());
					ts.setTestSuiteModuleReference(moduleByReference.getModuleReference());
					ts.setTestSuiteMasterTestSuiteID(masterTestSuiteByReference.getMasterTestSuiteID());
					ts.setTestSuiteMasterTestSuiteReference(masterTestSuiteByReference.getMasterTestSuiteReference());
					ts.setTestSuiteDescription(testSuiteDescription);
					ts.setTestSuiteRepositoryFile(testSuiteExcelFilePath);
					ts.setTestSuiteRepositoryAbsoluteFolderPath(testSuiteExcelFilePath.replace("\\", "\\"));
					ts.setTestSuiteExecutionPriority(testSuiteExecutionPriority);
					//PENDING
					//ts.setTestSuiteConfigurationID(testSuiteConfigurationID);
					//ts.setTestSuiteConfigurationReference(testSuiteConfigurationReference);
					ts.setTestSuiteExecutionMode(testSuiteExecutionMode);
					ts.setTestSuiteSerialNumber(testSuiteSerialNumber);

					testManagerDBInterface.addUpdateTestSuite(ts);
					//jellyFish
					//Test Scenarios
					TestSuite currentTestSuite = testManagerDBInterface.getTestSuiteByReference(testSuiteReference);
					testSuiteFileInputStreamReference = new FileInputStream(testSuiteExcelFileReference);
					testSuiteWorkBookReference = new XSSFWorkbook (testSuiteFileInputStreamReference);
					testScenarioDataWorksheet = testSuiteWorkBookReference.getSheet("TestScenarios");

					int testScenarioReferenceColumn = Integer.parseInt(tableConfigProp.getProperty("TestScenarioReferenceColumn"));
					//int TestScenarioLOBNameColumn = Integer.parseInt(tableConfigProp.getProperty("TestScenarioLOBNameColumn"));
					//int TestScenarioProductCodeColumn = Integer.parseInt(tableConfigProp.getProperty("TestScenarioProductCodeColumn"));
					int TestScenarioAutomationScripterNameColumn = Integer.parseInt(tableConfigProp.getProperty("TestScenarioAutomationScripterNameColumn"));
					int testScenarioPrerequisiteTestSuiteReferenceColumn = Integer.parseInt(tableConfigProp.getProperty("TestScenarioPrerequisiteTestSuiteReferenceColumn"));
					int testScenarioPrerequisiteTestScenarioReferenceColumn = Integer.parseInt(tableConfigProp.getProperty("TestScenarioPrerequisiteTestScenarioReferenceColumn"));
					int testScenarioDescriptionColumn = Integer.parseInt(tableConfigProp.getProperty("TestScenarioDescriptionColumn"));
					int testScenarioAutomationScriptReferenceColumn = Integer.parseInt(tableConfigProp.getProperty("TestScenarioAutomationScriptReferenceColumn"));
					int testScenarioSerialNumberColumn = Integer.parseInt(tableConfigProp.getProperty("TestScenarioSerialNumberColumn"));

					Iterator<Row> testScenarioIterator = testScenarioDataWorksheet.iterator();

					testScenarioIterator.next();
					while (testScenarioIterator.hasNext()) {
						Row testScenarioRow = testScenarioIterator.next();

						String testScenarioReference = testScenarioRow.getCell(testScenarioReferenceColumn).getStringCellValue();
						//String testScenarioLob=testScenarioRow.getCell(TestScenarioLOBNameColumn).getStringCellValue();
						//String testScenarioProduct=testScenarioRow.getCell(TestScenarioProductCodeColumn).getStringCellValue();
						String testScenarioScripter=testScenarioRow.getCell(TestScenarioAutomationScripterNameColumn).getStringCellValue();


						String testScenarioPrerequisiteTestSuiteReference = testScenarioRow.getCell(testScenarioPrerequisiteTestSuiteReferenceColumn).getStringCellValue();
						String testScenarioPrerequisiteTestScenarioReference = testScenarioRow.getCell(testScenarioPrerequisiteTestScenarioReferenceColumn).getStringCellValue();
						String testScenarioDescription = testScenarioRow.getCell(testScenarioDescriptionColumn).getStringCellValue();
						String testScenarioAutomationScriptReference = testScenarioRow.getCell(testScenarioAutomationScriptReferenceColumn).getStringCellValue();
						int testScenarioSerialNumber = (int) testScenarioRow.getCell(testScenarioSerialNumberColumn).getNumericCellValue();

						TestSuite prerequisiteTestSuiteReference = testManagerDBInterface.getTestSuiteByReference(testScenarioPrerequisiteTestSuiteReference);
						TestScenario prerequisiteTestScenarioReference = testManagerDBInterface.getTestScenarioByReference(testScenarioPrerequisiteTestScenarioReference);
						MasterAutomationScript masterAutomationScript = testManagerDBInterface.getMasterAutomationScriptByReference(testScenarioAutomationScriptReference);

						TestScenario tsc = new TestScenario();
						tsc.setTestScenarioReference(testScenarioReference);
						//tsc.setTestScenario_Lob(testScenarioLob);
						//tsc.setTestScenario_Product(testScenarioProduct);
						tsc.setTestScenario_Scripter(testScenarioScripter);
						tsc.setTestScenarioProjectID(currentTestSuite.getTestSuiteProjectID());
						tsc.setTestScenarioProjectReference(currentTestSuite.getTestSuiteProjectReference());
						tsc.setTestScenarioApplicationID(currentTestSuite.getTestSuiteApplicationID());
						tsc.setTestScenarioApplicationReference(currentTestSuite.getTestSuiteApplicationReference());
						tsc.setTestScenarioMasterTestSuiteID(currentTestSuite.getTestSuiteMasterTestSuiteID());
						tsc.setTestScenarioMasterTestSuiteReference(currentTestSuite.getTestSuiteMasterTestSuiteReference());
						tsc.setTestScenarioTestSuiteID(currentTestSuite.getTestSuiteID());
						tsc.setTestScenarioTestSuiteReference(currentTestSuite.getTestSuiteReference());

						if (prerequisiteTestSuiteReference!=null) {
							tsc.setTestScenarioPrerequisiteTestSuiteID(prerequisiteTestSuiteReference.getTestSuiteID());
							tsc.setTestScenarioPrerequisiteTestSuiteReference(prerequisiteTestSuiteReference.getTestSuiteReference());
						}

						if (prerequisiteTestScenarioReference!=null) {
							tsc.setTestScenarioPrerequisiteTestScenarioID(prerequisiteTestScenarioReference.getTestScenarioID());
							tsc.setTestScenarioPrerequisiteTestScenarioReference(prerequisiteTestScenarioReference.getTestScenarioReference());
						}
						tsc.setTestScenarioDescription(testScenarioDescription);

						tsc.setTestScenarioMasterAutomationScriptID(masterAutomationScript.getMasterAutomationScriptID());
						tsc.setTestScenarioMasterAutomationScriptReference(masterAutomationScript.getMasterAutomationScriptReference());

						tsc.setTestScenarioSerialNumber(testScenarioSerialNumber);

						testManagerDBInterface.addUpdateTestScenario(tsc);
					}

					testSuiteWorkBookReference.close();
					testSuiteFileInputStreamReference.close();
				}
			}
			System.out.println("UploadTestManagementTables Successfull!!!!");
		}

		if (uploadAutomationTestDataTables || uploadAllDataTables) {
			//Commented by PKP on 30-09-2015
			//Dont want hbm Folder to be flushed everytime before execution
			//FileUtils.cleanDirectory(hbmFolderPath); 

			//TestDataFiles
			testDataUploadDataWorksheet = masterTestSuiteWorkBookReference.getSheet("TestDataUploads");
			boolean jenkinsExecution = Boolean.parseBoolean(prop.getProperty("JenkinsExecution"));
			String dbConnectionURL;
			if (!jenkinsExecution){
				dbConnectionURL = "jdbc:sqlserver://" + prop.getProperty("TestDataDBServer") + ";databaseName=" + prop.getProperty("TestDataDBSchemaName")+ ";integratedSecurity=true;";
			}else{
				dbConnectionURL = "jdbc:sqlserver://" + prop.getProperty("JenkinsTestDataDBServer") + ";databaseName=" + prop.getProperty("TestDataDBSchemaName")+ ";integratedSecurity=true;";
			}
			try {
				dataDBConnectionObject = DriverManager.getConnection(dbConnectionURL);
			} catch (SQLException e1) {
				e1.printStackTrace();
			}

			int testDataUploadReferenceColumn = Integer.parseInt(tableConfigProp.getProperty("TestDataUploadReferenceColumn"));
			int testDataUploadTestDataFileNameColumn = Integer.parseInt(tableConfigProp.getProperty("TestDataUploadTestDataFileNameColumn"));
			int testDataUploadSerialNumberColumn = Integer.parseInt(tableConfigProp.getProperty("TestDataUploadSerialNumberColumn"));
			int testDataUploadUploadFreshDataTableFileColumn = Integer.parseInt(tableConfigProp.getProperty("TestDataUploadUploadFreshDataTableFileColumn"));

			Iterator<Row> testDataUploadIterator = testDataUploadDataWorksheet.iterator();

			boolean testDataUploadedSuccessfully = true;

			testDataUploadIterator.next();
			while (testDataUploadIterator.hasNext()) {
				Row testDataUploadRow = testDataUploadIterator.next();

				String testDataUploadReference = testDataUploadRow.getCell(testDataUploadReferenceColumn).getStringCellValue();
				String testDataUploadTestDataFileName = testDataUploadRow.getCell(testDataUploadTestDataFileNameColumn).getStringCellValue().replace("\\", "/");
				int testDataUploadSerialNumber = (int) testDataUploadRow.getCell(testDataUploadSerialNumberColumn).getNumericCellValue();
				String testDataUploadUploadFreshDataTableFile = testDataUploadRow.getCell(testDataUploadUploadFreshDataTableFileColumn).getStringCellValue();
				// Added by PKP on 20-11-2015
				String testDataUploadTestDataFilePath = masterTestSuiteExcelFileReference.getParentFile().getAbsolutePath().replace("\\", "/") + testDataUploadTestDataFileName.replace("\\", "/");
				if (testDataUploadUploadFreshDataTableFile.equalsIgnoreCase("Yes")){
					try {
						uploadTestDataFile(testDataUploadTestDataFilePath, dataDBConnectionObject, uploadAllDataTables, testDataUploadUploadFreshDataTableFile, hbmFolderPath);
						testDataUploadedSuccessfully = testDataUploadedSuccessfully && true;
						//Commented by PKP as Uploaded Workbook is set as NO on 22-09-2015
						//testDataUploadRow.getCell(testDataUploadUploadFreshDataTableFileColumn).setCellValue("No");
					} 
					catch (Exception e) {
						testDataUploadedSuccessfully = false;
						e.printStackTrace();
						throw e;
					}
				}else{
					System.out.println("===============================================================================");
					System.out.println("Skipped Test Data File : " + testDataUploadTestDataFilePath);
					System.out.println("===============================================================================");
				}
			}

			try {
				dataDBConnectionObject.close();
			}
			catch(SQLException sqle) {
				sqle.printStackTrace();
			}
		}

		generateTestDataDBSession(hbmFolderPath);

		masterTestSuiteFileInputStreamReference.close();

		File outputFile = new File(masterTestSuiteExcelFilePath);
		FileOutputStream masterTestSuiteFileOutputStreamReference = new FileOutputStream(outputFile);

		masterTestSuiteWorkBookReference.write(masterTestSuiteFileOutputStreamReference);
		masterTestSuiteFileOutputStreamReference.close();

		masterTestSuiteWorkBookReference.close();
	}

	public void uploadTestDataFile (String testDataUploadTestDataFilePath, Connection dataDBConnectionObject, boolean uploadAllDataTables, String testDataUploadUploadFreshDataTableFile, File hbmFolderPath) throws FileNotFoundException, IOException {
		System.out.println("===============================================================================");
		System.out.println("Uploading Test Data File : " + testDataUploadTestDataFilePath);
		System.out.println("===============================================================================");

		File testDataExcelFileReference;
		FileInputStream testDataFileInputStreamReference;
		XSSFWorkbook testDataWorkBookReference;
		XSSFSheet tableUploadMasterWorksheet;
		XSSFSheet dataWorksheet;

		FileOutputStream testDataFileOutputStreamReference;

		testDataExcelFileReference = new File(testDataUploadTestDataFilePath);
		testDataFileInputStreamReference = new FileInputStream(testDataExcelFileReference);
		testDataWorkBookReference = new XSSFWorkbook (testDataFileInputStreamReference);

		tableUploadMasterWorksheet = testDataWorkBookReference.getSheet("TableUploadMaster");

		int tableUploadReferenceColumn = Integer.parseInt(tableConfigProp.getProperty("TableUploadReferenceColumn"));
		int tableUploadTableNameInDBColumn = Integer.parseInt(tableConfigProp.getProperty("TableUploadTableNameInDBColumn"));
		int tableUploadUploadSheetNameColumn = Integer.parseInt(tableConfigProp.getProperty("TableUploadUploadSheetNameColumn"));
		int tableUploadUploadOnNextRunColumn = Integer.parseInt(tableConfigProp.getProperty("TableUploadUploadOnNextRunColumn"));

		Iterator<Row> tableUploadMasterIterator = tableUploadMasterWorksheet.iterator();
		int rowCount = tableUploadMasterWorksheet.getPhysicalNumberOfRows()-1;
		tableUploadMasterIterator.next();
		while (tableUploadMasterIterator.hasNext()) {
			Row tableUploadMasterRow = tableUploadMasterIterator.next();

			String tableUploadReference = tableUploadMasterRow.getCell(tableUploadReferenceColumn).getStringCellValue();
			String tableUploadTableNameInDB = tableUploadMasterRow.getCell(tableUploadTableNameInDBColumn).getStringCellValue();
			String tableUploadUploadSheetName = tableUploadMasterRow.getCell(tableUploadUploadSheetNameColumn).getStringCellValue();
			String tableUploadUploadOnNextRun = tableUploadMasterRow.getCell(tableUploadUploadOnNextRunColumn).getStringCellValue();
			dataWorksheet = testDataWorkBookReference.getSheet(tableUploadUploadSheetName);

			//System.out.print("Uploading Sheet... " + tableUploadTableNameInDB + " "); 
			uploadDataWorksheet(tableUploadTableNameInDB, dataWorksheet, uploadAllDataTables, testDataUploadUploadFreshDataTableFile, tableUploadUploadOnNextRun, dataDBConnectionObject, hbmFolderPath);
			tableUploadMasterRow.getCell(tableUploadUploadOnNextRunColumn).setCellValue("No");
			dataWorksheet = null;

			rowCount--;
			System.out.println("Remaining sheets to be uploaded => "+ rowCount); 

		}

		testDataFileInputStreamReference.close();

		testDataFileOutputStreamReference = new FileOutputStream(testDataExcelFileReference);
		testDataWorkBookReference.write(testDataFileOutputStreamReference);
		testDataFileOutputStreamReference.close();

		testDataWorkBookReference.close();
		System.out.println("-------------------------------------------------------------------------------\n\n");
	}

	private void uploadDataWorksheet(String tableUploadTableNameInDB, XSSFSheet dataWorksheet, Boolean uploadAllDataTables, String testDataUploadUploadFreshDataTableFile, String tableUploadUploadOnNextRun, Connection dataDBConnectionObject, File hbmFolderPath) {
		//String hbmCreationFolder = prop.getProperty("HBMFilesFolder");
		try {
			String hbmCreationFolder = hbmFolderPath.getCanonicalPath().replace("\\", "//");
			String hbmFilePath = hbmCreationFolder + "\\" + tableUploadTableNameInDB + ".hbm.xml";

			if (uploadAllDataTables.booleanValue() || (testDataUploadUploadFreshDataTableFile.equalsIgnoreCase("YES") && tableUploadUploadOnNextRun.equalsIgnoreCase("YES"))) {
				System.out.print("Uploading Sheet... " + tableUploadTableNameInDB + " ");
				createTableInDB(tableUploadTableNameInDB, dataWorksheet, dataDBConnectionObject);
				createHBMFile(tableUploadTableNameInDB, dataWorksheet, hbmFilePath);

				//Added smart upload @ramanuj

				if (uploadAutomationTestDataTablesForSpecific) {
					SpecificScenarioWiseDataUpload specificScenarioWiseDataUpload = new SpecificScenarioWiseDataUpload();
					specificScenarioWiseDataUpload.specificUploadFileInDB(
							tableUploadTableNameInDB, dataWorksheet,
							dataDBConnectionObject);
					System.out.println(".. Uploaded Successfully.");
				} else if (!uploadAutomationTestDataTablesForSpecific) {
					uploadFileInDB(tableUploadTableNameInDB, dataWorksheet,
							dataDBConnectionObject);
					System.out.println(".. Uploaded Successfully.");
				}

			} 
			else {
				System.out.println(".. Skipped Uploading Sheet... " + tableUploadTableNameInDB + " "); 
				//Commented by PKP on 30-09-2015
				//Dont want hbm files to be always created 
				//createHBMFile(tableUploadTableNameInDB, dataWorksheet, hbmFilePath);
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	private void createTableInDB (String tableUploadTableNameInDB, XSSFSheet dataWorksheet, Connection dataDBConnectionObject) {

		try {
			Iterator<Row> rows = dataWorksheet.rowIterator();
			Row columnHeaderRow;
			Row columnTypesRow;

			if (rows.hasNext()) {
				columnHeaderRow = rows.next();
			}
			else
				throw new RuntimeException ("Header Row Not Found. Please check the datasheet - " + dataWorksheet.getSheetName());

			if (rows.hasNext()) {
				columnTypesRow = rows.next();
			}
			else
				throw new RuntimeException ("Column Type Row Not Found. Please check the datasheet - " + dataWorksheet.getSheetName());

			Iterator<Cell> columnTitles = columnHeaderRow.cellIterator();
			Iterator<Cell> columnTypes = columnTypesRow.cellIterator();

			String tableCreateStatementString = "Create Table " + tableUploadTableNameInDB + " ( \n";
			String tableDropStatementString = "Drop Table " + tableUploadTableNameInDB;

			for (int i = 0; i < columnHeaderRow.getPhysicalNumberOfCells(); i++){
				Cell columnTitleCell = columnHeaderRow.getCell(i);
				Cell columnTypeCell = columnTypesRow.getCell(i);

				int originalColumnTitleCellType = columnTitleCell.getCellType(); 
				int originalColumnTypeCellType = columnTitleCell.getCellType(); 

				columnTitleCell.setCellType(Cell.CELL_TYPE_STRING);
				columnTypeCell.setCellType(Cell.CELL_TYPE_STRING);

				tableCreateStatementString = tableCreateStatementString + columnTitleCell.getStringCellValue() + " " + columnTypeCell.getStringCellValue();

				columnTitleCell.setCellType(originalColumnTitleCellType);
				columnTypeCell.setCellType(originalColumnTypeCellType);

				if(i<columnHeaderRow.getPhysicalNumberOfCells()-1) {
					tableCreateStatementString = tableCreateStatementString + ", \n";
				}
			}

			tableCreateStatementString = tableCreateStatementString + ") \n";


			try {
				System.out.println(tableDropStatementString);
				Statement dropTableStatement = dataDBConnectionObject.createStatement();
				dropTableStatement.execute(tableDropStatementString);
				System.out.println("Table Dropped");
			}
			catch(SQLException sqle) {
				System.out.println("Table Dropped: " + sqle.getMessage());
				//sqle.printStackTrace();
			}

			try {
				System.out.println(tableCreateStatementString);
				Statement createTableStatement = dataDBConnectionObject.createStatement();
				createTableStatement.execute(tableCreateStatementString);
				System.out.println("Table Created");
			}
			catch(SQLException sqle) {
				sqle.printStackTrace();
			}

		} catch (Exception e) {
			System.out.println("e = " + e);
		}

	}

	private void uploadFileInDB (String tableUploadTableNameInDB, XSSFSheet dataWorksheet, Connection dataDBConnectionObject) {
		try {
			Iterator<Row> rows = dataWorksheet.rowIterator();
			Row columnHeaderRow;
			Row columnTypesRow;

			if (rows.hasNext()) {
				columnHeaderRow = rows.next();
			}
			else
				throw new RuntimeException ("Header Row Not Found. Please check the datasheet - " + dataWorksheet.getSheetName());

			if (rows.hasNext()) {
				columnTypesRow = rows.next();
			}
			else
				throw new RuntimeException ("Column Type Row Not Found. Please check the datasheet - " + dataWorksheet.getSheetName());

			Iterator<Cell> columnTitles = columnHeaderRow.cellIterator();
			Iterator<Cell> columnTypes = columnTypesRow.cellIterator();

			String columnNameString = "";
			String columnValueString = "";

			while (rows.hasNext()) {
				Row currentRow = rows.next(); 
				columnNameString = "";
				columnValueString = "";
				for (int i = 0; i < columnHeaderRow.getPhysicalNumberOfCells(); i++){
					Cell currentCell = currentRow.getCell(i);
					columnNameString = columnNameString + columnHeaderRow.getCell(i);

					if (currentCell != null) {
						if (columnTypesRow.getCell(i).getStringCellValue().equalsIgnoreCase("integer") ||
								columnTypesRow.getCell(i).getStringCellValue().equalsIgnoreCase("int") ||
								columnTypesRow.getCell(i).getStringCellValue().toLowerCase().startsWith("number") ||
								columnTypesRow.getCell(i).getStringCellValue().toLowerCase().startsWith("long") || 
								columnTypesRow.getCell(i).getStringCellValue().toLowerCase().startsWith("short") ||
								columnTypesRow.getCell(i).getStringCellValue().equalsIgnoreCase("bit")) {
							double tmpValue;
							try {
								tmpValue = currentCell.getNumericCellValue();
							}
							catch (Exception e) {
								tmpValue = 0.0;
							}
							columnValueString = columnValueString + Integer.toString((int) tmpValue);
						}
						else if (columnTypesRow.getCell(i).getStringCellValue().toLowerCase().startsWith("float") ||
								columnTypesRow.getCell(i).getStringCellValue().toLowerCase().startsWith("double")) {
							double tmpValue;
							try {
								tmpValue = currentCell.getNumericCellValue();
							}
							catch (Exception e) {
								tmpValue = 0.0;
							}
							columnValueString = columnValueString + Double.toString(tmpValue);
						}
						/*else if (columnTypesRow.getCell(i).getStringCellValue().equalsIgnoreCase("bit")) {
							int originalCellType = currentCell.getCellType(); 
							currentCell.setCellType(Cell.CELL_TYPE_STRING);
							String tmpValue = currentCell.getStringCellValue();
							if (tmpValue == null) {
								tmpValue = "FALSE";
							}
							columnValueString = columnValueString + Boolean.parseBoolean(tmpValue);
							currentCell.setCellType(originalCellType);
						}
						 */
						else if (columnTypesRow.getCell(i).getStringCellValue().toLowerCase().startsWith("varchar") || 
								columnTypesRow.getCell(i).getStringCellValue().toLowerCase().startsWith("varchar2") || 
								columnTypesRow.getCell(i).getStringCellValue().toLowerCase().startsWith("text") || 
								columnTypesRow.getCell(i).getStringCellValue().toLowerCase().startsWith("date") || 
								columnTypesRow.getCell(i).getStringCellValue().toLowerCase().startsWith("time") ||
								columnTypesRow.getCell(i).getStringCellValue().toLowerCase().startsWith("timestamp") ||
								columnTypesRow.getCell(i).getStringCellValue().toLowerCase().startsWith("char")) {
							int originalCellType = currentCell.getCellType(); 
							currentCell.setCellType(Cell.CELL_TYPE_STRING);
							String tmpValue = currentCell.getStringCellValue();
							if (tmpValue == null) {
								tmpValue = "";
							}

							columnValueString = columnValueString + "'" +  tmpValue + "'";
							currentCell.setCellType(originalCellType);
						}
					}
					else
						columnValueString = columnValueString + "null";

					if(i<columnHeaderRow.getPhysicalNumberOfCells()-1) {
						columnNameString = columnNameString + ", ";
						columnValueString = columnValueString + ", ";
					}
				}

				try {
					String insertStatement = "insert into " + tableUploadTableNameInDB + " (" + columnNameString + ") values (" + columnValueString + ")";
					//System.out.println(insertStatement);
					Statement statement = dataDBConnectionObject.createStatement();
					statement.executeUpdate(insertStatement);
				}
				catch(SQLException sqle) {
					sqle.printStackTrace();
				}
			}
		} catch (Exception e) {
			System.out.println("e = " + e);
		}
	}

	private void createHBMFile(String tableUploadTableNameInDB, XSSFSheet dataWorksheet, String hbmFilePath) {
		// Added by PKP on 24-11-2015. To avoid HBM file creation.Global sheet hbm need not be created.
		if (tableUploadTableNameInDB.equalsIgnoreCase("LoginMasterEntity")){
			return;
		}
		Iterator<Row> rows = dataWorksheet.rowIterator();
		Row columnHeaderRow;
		Row columnTypesRow;

		if (rows.hasNext()) {
			columnHeaderRow = rows.next();
		}
		else
			throw new RuntimeException ("Header Row Not Found. Please check the datasheet - " + dataWorksheet.getSheetName());

		if (rows.hasNext()) {
			columnTypesRow = rows.next();
		}
		else
			throw new RuntimeException ("Column Type Row Not Found. Please check the datasheet - " + dataWorksheet.getSheetName());

		//Create HBM file
		StringBuffer hbmFileContents = new StringBuffer();
		hbmFileContents.append("<?xml version=\"1.0\"?>\n");
		hbmFileContents.append("<!DOCTYPE hibernate-mapping PUBLIC \"-//Hibernate/Hibernate Mapping DTD 3.0//EN\" ");
		hbmFileContents.append("\"http://www.hibernate.org/dtd/hibernate-mapping-3.0.dtd\">\n");

		hbmFileContents.append("<hibernate-mapping auto-import=\"true\" default-access=\"property\" default-cascade=\"none\" default-lazy=\"true\">\n");
		hbmFileContents.append("	<class abstract=\"false\" name=\"com.aqm.staf.library.databin."+tableUploadTableNameInDB+"\" table=\"" + tableUploadTableNameInDB + "\">\n");

		hbmFileContents.append("		<id name=\"Reference\" column=\"Reference\" type=\"string\">\n");
		hbmFileContents.append("		</id>\n");
		hbmFileContents.append("		<property name=\"TestScenario\" column=\"TestScenario\" type=\"string\" />\n");
		hbmFileContents.append("		<property name=\"RowNumber\" column=\"RowNumber\" type=\"integer\" />\n");
		hbmFileContents.append("		<property name=\"Action\" column=\"Action\" type=\"string\" />\n");
		hbmFileContents.append("		<property name=\"StepGroup\" column=\"StepGroup\" type=\"string\" />\n");
		hbmFileContents.append("		<property name=\"ParentKey\" column=\"ParentKey\" type=\"string\" />\n");
		hbmFileContents.append("		<property name=\"ChildKey\" column=\"ChildKey\" type=\"string\" />\n");
		hbmFileContents.append("		<property name=\"ConfigExecute\" column=\"ConfigExecute\" type=\"string\" />\n");
		hbmFileContents.append("		<dynamic-component insert=\"true\" name=\"customProperties\" optimistic-lock=\"true\" unique=\"false\" update=\"true\">\n");

		Iterator<Cell> columnTitles = columnHeaderRow.cellIterator();
		Iterator<Cell> columnTypes = columnTypesRow.cellIterator();

		while(columnTitles.hasNext()) {
			String columnName = (String) columnTitles.next().getStringCellValue();
			String columnType = (String) columnTypes.next().getStringCellValue();

			if (!columnName.equalsIgnoreCase("REFERENCE") && !columnName.equalsIgnoreCase("TESTSCENARIO") && !columnName.equalsIgnoreCase("ROWNUMBER") 
					&& !columnName.equalsIgnoreCase("ACTION") && !columnName.equalsIgnoreCase("STEPGROUP")
					&& !columnName.equalsIgnoreCase("PARENTKEY") && !columnName.equalsIgnoreCase("CHILDKEY") && !columnName.equalsIgnoreCase("ConfigExecute")) {
				hbmFileContents.append("			<property name=\""+columnName+"\" column=\""+columnName+"\" ");
				if (columnType.toUpperCase().startsWith("INTEGER") || columnType.toUpperCase().startsWith("INT")) {
					hbmFileContents.append("type=\"integer\" ");
				}
				else if (columnType.toUpperCase().startsWith("LONG")) {
					hbmFileContents.append("type=\"long\" ");
				}
				else if (columnType.toUpperCase().startsWith("SHORT")) {
					hbmFileContents.append("type=\"short\" ");
				}
				else if (columnType.toUpperCase().startsWith("CHAR")) {
					hbmFileContents.append("type=\"character\" ");
				}
				else if (columnType.toUpperCase().startsWith("NUMBER")) {
					hbmFileContents.append("type=\"big_decimal\" ");
				}
				else if (columnType.toUpperCase().startsWith("FLOAT")) {
					hbmFileContents.append("type=\"float\" ");
				}
				else if (columnType.toUpperCase().startsWith("DOUBLE")) {
					hbmFileContents.append("type=\"double\" ");
				}
				else if (columnType.toUpperCase().startsWith("BIT")) {
					hbmFileContents.append("type=\"boolean\" ");
				}
				else if (columnType.toUpperCase().startsWith("VARCHAR") || columnType.toUpperCase().startsWith("VARCHAR2")) {
					hbmFileContents.append("type=\"string\" ");
				}
				else if (columnType.toUpperCase().startsWith("TEXT")) {
					hbmFileContents.append("type=\"text\" ");
				}
				else if (columnType.toUpperCase().startsWith("DATE")) {
					hbmFileContents.append("type=\"date\" ");
				}
				else if (columnType.toUpperCase().startsWith("TIME")) {
					hbmFileContents.append("type=\"time\" ");
				}
				else if (columnType.toUpperCase().startsWith("TIMESTAMP")) {
					hbmFileContents.append("type=\"timestamp\" ");
				}

				hbmFileContents.append("/>\n");
			}
		}

		hbmFileContents.append("		</dynamic-component>\n");
		hbmFileContents.append("	</class>\n");
		hbmFileContents.append("</hibernate-mapping>\n");

		File hbmFileObject;

		try {
			hbmFileObject = new File (hbmFilePath);
			FileWriter fileWriter = new FileWriter(hbmFileObject);
			fileWriter.write(hbmFileContents.toString());
			fileWriter.flush();
			fileWriter.close();

			hbmFileObject = null;
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public static Session getSessionTestManager() {
		return sessionTestManager;
	}

	public static Session getSessionTestData() {
		return sessionTestData;
	}

	@SuppressWarnings("resource")
	public void createExecutionRecords() throws IOException {
		//Copy all files in the execution folder from the default location
		File sourceFolder = new File(this.baseMasterTestSuiteFolderForExecution);
		try {
			FileUtils.copyDirectory(sourceFolder, getCurrentTestAutomationSuitesAndTestDataFolderReference());
		} catch (IOException e) {
			e.printStackTrace();
		}

		String currentExecutionMasterTestSuiteExcelFilePath;				
		File currentExecutionMasterTestSuiteExcelFileReference;
		FileInputStream currentExecutionMasterTestSuiteFileInputStreamReference;
		XSSFWorkbook currentExecutionMasterTestSuiteWorkBookReference;
		XSSFSheet currentExecutionMasterTestSuiteDataWorksheet;
		XSSFSheet currentExecutionTestSuiteDataWorksheet;
		XSSFSheet currentExecutionTestPlanConfigurationsDataWorksheet;

		File currentExecutionTestSuiteExcelFileReference;
		FileInputStream currentExecutionTestSuiteFileInputStreamReference;
		XSSFWorkbook currentExecutionTestSuiteWorkBookReference;
		XSSFSheet currentExecutionTestScenarioDataWorksheet;

		boolean runAllScenariosOverride = Boolean.parseBoolean(tableConfigProp.getProperty("RunAllScenariosOverride"));

		currentExecutionMasterTestSuiteExcelFilePath = getCurrentTestAutomationSuitesAndTestDataFolderReference().getAbsolutePath().replace("\\", "\\\\") + 
				"\\" + this.baseMasterTestSuiteFileForExecution;

		currentExecutionMasterTestSuiteExcelFileReference = new File(currentExecutionMasterTestSuiteExcelFilePath);
		currentExecutionMasterTestSuiteFileInputStreamReference = new FileInputStream(currentExecutionMasterTestSuiteExcelFileReference);
		currentExecutionMasterTestSuiteWorkBookReference = new XSSFWorkbook (currentExecutionMasterTestSuiteFileInputStreamReference);

		currentExecutionTestPlanConfigurationsDataWorksheet = currentExecutionMasterTestSuiteWorkBookReference.getSheet("TestPlanConfigurations");

		int currentExecutionTestPlanConfigurationsPlatformToTestOnColumn = Integer.parseInt(tableConfigProp.getProperty("TestPlanConfigurationsPlatformToTestOnColumn"));
		int currentExecutionTestPlanConfigurationsBrowserToTestOnColumn = Integer.parseInt(tableConfigProp.getProperty("TestPlanConfigurationsBrowserToTestOnColumn"));
		int currentExecutionTestPlanConfigurationsBrowserVersionToTestOnColumn = Integer.parseInt(tableConfigProp.getProperty("TestPlanConfigurationsBrowserVersionToTestOnColumn"));

		//Test Plan
		int tempExecutionMasterTestSuiteSerialNumber = 1;

		RuntimeConfigParameter nextTestPlanIDRuntimeConfigParamater = testManagerDBInterface.getRuntimeConfigParameterByReference("TestPlanNextID");
		TestPlan executionTestPlan = new TestPlan();

		String testPlanReference = "TestPlan_" + nextTestPlanIDRuntimeConfigParamater.getRuntimeConfigParametersKeyData();

		Iterator<Row> currentTestPlanConfigurationIterator = currentExecutionTestPlanConfigurationsDataWorksheet.iterator();
		currentTestPlanConfigurationIterator.next();
		Row currentTestPlanConfigurationsRow = currentTestPlanConfigurationIterator.next();

		String currentExecutionTestPlanConfigurationsPlatformToTestOn = currentTestPlanConfigurationsRow.getCell(currentExecutionTestPlanConfigurationsPlatformToTestOnColumn).getStringCellValue();
		String currentExecutionTestPlanConfigurationsBrowserToTestOn = currentTestPlanConfigurationsRow.getCell(currentExecutionTestPlanConfigurationsBrowserToTestOnColumn).getStringCellValue();
		currentTestPlanConfigurationsRow.getCell(currentExecutionTestPlanConfigurationsBrowserVersionToTestOnColumn).setCellType(Cell.CELL_TYPE_STRING);
		String currentExecutionTestPlanConfigurationsBrowserVersionToTestOn = currentTestPlanConfigurationsRow.getCell(currentExecutionTestPlanConfigurationsBrowserVersionToTestOnColumn).getStringCellValue();

		executionTestPlan.setTestPlanReference(testPlanReference);
		executionTestPlan.setTestPlanDescription(testPlanReference);
		executionTestPlan.setTestPlanCurrentExecutionResultsFolder(getCurrentExecutionResultsFolderReference().getAbsolutePath().replace("\\", "\\\\").replace("/", "\\\\"));
		executionTestPlan.setTestPlanCurrentScenarioExecutionResultsFolder(getCurrentScenarioExecutionResultsFolderReference().getAbsolutePath().replace("\\", "\\\\").replace("/", "\\\\"));
		executionTestPlan.setTestPlanCurrentTestNGExecutionResultsFolder(getCurrentTestNGExecutionResultsFolderReference().getAbsolutePath().replace("\\", "\\\\").replace("/", "\\\\"));
		executionTestPlan.setTestPlanPlatformToTestOn(currentExecutionTestPlanConfigurationsPlatformToTestOn);
		executionTestPlan.setTestPlanBrowserToTestOn(currentExecutionTestPlanConfigurationsBrowserToTestOn);
		executionTestPlan.setTestPlanBrowserVersionToTestOn(currentExecutionTestPlanConfigurationsBrowserVersionToTestOn);
		executionTestPlan.setTestPlanDeleted(false);

		testManagerDBInterface.addUpdateTestPlan(executionTestPlan);
		executionTestPlan = testManagerDBInterface.getTestPlanByReference(testPlanReference);
		this.currentExecutionTestPlan = executionTestPlan;

		//String testPlanNextID = "00000" + (Integer.parseInt(nextTestPlanIDRuntimeConfigParamater.getRuntimeConfigParametersKeyData()) + 1);
		//testPlanNextID = testPlanNextID.substring(testPlanNextID.length() - 5);
		//nextTestPlanIDRuntimeConfigParamater.setRuntimeConfigParametersKeyData(testPlanNextID);
		//testManagerDBInterface.addUpdateRuntimeConfigParameter(nextTestPlanIDRuntimeConfigParamater);

		String testPlanNextID = Long.toString((Long.parseUnsignedLong(nextTestPlanIDRuntimeConfigParamater.getRuntimeConfigParametersKeyData()) + 1));
		nextTestPlanIDRuntimeConfigParamater.setRuntimeConfigParametersKeyData(testPlanNextID);
		testManagerDBInterface.addUpdateRuntimeConfigParameter(nextTestPlanIDRuntimeConfigParamater);

		//Master Test Suite
		currentExecutionMasterTestSuiteDataWorksheet = currentExecutionMasterTestSuiteWorkBookReference.getSheet("MasterTestSuites");

		int currentExecutionMasterTestSuiteReferenceColumn = Integer.parseInt(tableConfigProp.getProperty("MasterTestSuiteReferenceColumn"));
		int currentExecutionMasterTestSuiteExecuteMasterTestSuiteColumn = Integer.parseInt(tableConfigProp.getProperty("MasterTestSuiteExecuteMasterTestSuiteColumn"));

		Iterator<Row> currentExecutionMasterTestSuiteIterator = currentExecutionMasterTestSuiteDataWorksheet.iterator();

		currentExecutionMasterTestSuiteIterator.next();
		while (currentExecutionMasterTestSuiteIterator.hasNext()) {
			Row currentExecutionMasterTestSuiteRow = currentExecutionMasterTestSuiteIterator.next();

			String currentExecutionMasterTestSuiteReference = currentExecutionMasterTestSuiteRow.getCell(currentExecutionMasterTestSuiteReferenceColumn).getStringCellValue();
			String currentExecutionMasterTestSuiteExecuteMasterTestSuite = currentExecutionMasterTestSuiteRow.getCell(currentExecutionMasterTestSuiteExecuteMasterTestSuiteColumn).getStringCellValue();

			if (currentExecutionMasterTestSuiteExecuteMasterTestSuite.equalsIgnoreCase("YES") || runAllScenariosOverride) {
				MasterTestSuite currentExecutionMasterTestSuiteByReference = testManagerDBInterface.getMasterTestSuiteByReference(currentExecutionMasterTestSuiteReference);

				RuntimeConfigParameter nextExecutionMasterTestSuiteNextIDRuntimeConfigParamater = testManagerDBInterface.getRuntimeConfigParameterByReference("ExecutionMasterTestSuiteNextID");
				String executionMasterTestSuiteReference = "ExecutionMasterTestSuite_" + nextExecutionMasterTestSuiteNextIDRuntimeConfigParamater.getRuntimeConfigParametersKeyData();

				ExecutionMasterTestSuites executionMasterTestSuite = new ExecutionMasterTestSuites();
				executionMasterTestSuite.setExecutionMasterTestSuiteReference(executionMasterTestSuiteReference);
				executionMasterTestSuite.setExecutionMasterTestSuiteDescription(executionMasterTestSuiteReference);
				executionMasterTestSuite.setExecutionMasterTestSuiteTestPlanID(executionTestPlan.getTestPlanID());
				executionMasterTestSuite.setExecutionMasterTestSuiteTestPlanReference(executionTestPlan.getTestPlanReference());
				executionMasterTestSuite.setExecutionMasterTestSuiteMasterTestSuiteID(currentExecutionMasterTestSuiteByReference.getMasterTestSuiteID());
				executionMasterTestSuite.setExecutionMasterTestSuiteMasterTestSuiteReference(currentExecutionMasterTestSuiteByReference.getMasterTestSuiteReference());

				executionMasterTestSuite.setExecutionMasterTestSuiteSerialNumber(tempExecutionMasterTestSuiteSerialNumber);
				executionMasterTestSuite.setExecutionMasterTestSuiteExecutionStatus(0);

				executionMasterTestSuite.setExecutionMasterTestSuiteDeleted(false);

				testManagerDBInterface.addUpdateExecutionMasterTestSuite(executionMasterTestSuite);

				executionMasterTestSuite = testManagerDBInterface.getExecutionMasterTestSuiteByReference(executionMasterTestSuiteReference);

				//String executionMasterTestSuiteNextID = "00000" + (Integer.parseInt(nextExecutionMasterTestSuiteNextIDRuntimeConfigParamater.getRuntimeConfigParametersKeyData()) + 1);
				//executionMasterTestSuiteNextID = executionMasterTestSuiteNextID.substring(executionMasterTestSuiteNextID.length() - 5);
				//nextExecutionMasterTestSuiteNextIDRuntimeConfigParamater.setRuntimeConfigParametersKeyData(executionMasterTestSuiteNextID);
				//testManagerDBInterface.addUpdateRuntimeConfigParameter(nextExecutionMasterTestSuiteNextIDRuntimeConfigParamater);

				String executionMasterTestSuiteNextID = Long.toString((Long.parseUnsignedLong(nextExecutionMasterTestSuiteNextIDRuntimeConfigParamater.getRuntimeConfigParametersKeyData()) + 1));
				nextExecutionMasterTestSuiteNextIDRuntimeConfigParamater.setRuntimeConfigParametersKeyData(executionMasterTestSuiteNextID);
				testManagerDBInterface.addUpdateRuntimeConfigParameter(nextExecutionMasterTestSuiteNextIDRuntimeConfigParamater);

				//Test Suite
				currentExecutionTestSuiteDataWorksheet = currentExecutionMasterTestSuiteWorkBookReference.getSheet("TestSuites");

				int currentExecutionTestSuiteReferenceColumn = Integer.parseInt(tableConfigProp.getProperty("TestSuiteReferenceColumn"));
				int currentExecutionTestSuiteMasterTestSuiteReferenceColumn = Integer.parseInt(tableConfigProp.getProperty("TestSuiteMasterTestSuiteReferenceColumn"));
				int currentExecutionTestSuiteExecuteTestSuiteColumn = Integer.parseInt(tableConfigProp.getProperty("TestSuiteExecuteTestSuiteColumn"));

				Iterator<Row> currentExecutionTestSuiteIterator = currentExecutionTestSuiteDataWorksheet.iterator();
				currentExecutionTestSuiteIterator.next();
				while (currentExecutionTestSuiteIterator.hasNext()) {
					Row currentExecutionTestSuiteRow = currentExecutionTestSuiteIterator.next();

					String currentExecutionTestSuiteReference = currentExecutionTestSuiteRow.getCell(currentExecutionTestSuiteReferenceColumn).getStringCellValue();
					String currentExecutionTestSuiteMasterTestSuiteReference = currentExecutionTestSuiteRow.getCell(currentExecutionTestSuiteMasterTestSuiteReferenceColumn).getStringCellValue();
					String currentExecutionTestSuiteExecuteTestSuite = currentExecutionTestSuiteRow.getCell(currentExecutionTestSuiteExecuteTestSuiteColumn).getStringCellValue();

					if (currentExecutionTestSuiteExecuteTestSuite.equalsIgnoreCase("YES") || runAllScenariosOverride) {
						TestSuite currentExecutionTestSuiteByReference = testManagerDBInterface.getTestSuiteByReference(currentExecutionTestSuiteReference);

						if (currentExecutionTestSuiteMasterTestSuiteReference.equalsIgnoreCase(currentExecutionMasterTestSuiteByReference.getMasterTestSuiteReference())) {
							RuntimeConfigParameter nextExecutionTestSuiteNextIDRuntimeConfigParamater = testManagerDBInterface.getRuntimeConfigParameterByReference("ExecutionTestSuiteNextID");
							String executionTestSuiteReference = "ExecutionTestSuite_" + nextExecutionTestSuiteNextIDRuntimeConfigParamater.getRuntimeConfigParametersKeyData();

							ExecutionTestSuites executionTestSuite = new ExecutionTestSuites();
							executionTestSuite.setExecutionTestSuiteReference(executionTestSuiteReference);
							executionTestSuite.setExecutionTestSuiteDescription(executionTestSuiteReference);

							executionTestSuite.setExecutionTestSuiteExecutionMasterTestSuiteID(executionMasterTestSuite.getExecutionMasterTestSuiteID());
							executionTestSuite.setExecutionTestSuiteExecutionMasterTestSuiteReference(executionMasterTestSuite.getExecutionMasterTestSuiteReference());

							executionTestSuite.setExecutionTestSuiteTestPlanID(executionTestPlan.getTestPlanID());
							executionTestSuite.setExecutionTestSuiteTestPlanReference(executionTestPlan.getTestPlanReference());

							executionTestSuite.setExecutionTestSuiteMasterTestSuiteID(currentExecutionMasterTestSuiteByReference.getMasterTestSuiteID());
							executionTestSuite.setExecutionTestSuiteMasterTestSuiteReference(currentExecutionMasterTestSuiteByReference.getMasterTestSuiteReference());

							executionTestSuite.setExecutionTestSuiteTestSuiteID(currentExecutionTestSuiteByReference.getTestSuiteID());
							executionTestSuite.setExecutionTestSuiteTestSuiteReference(currentExecutionTestSuiteByReference.getTestSuiteReference());

							String currentExecutionTestSuiteExcelFilePath = getCurrentTestAutomationSuitesAndTestDataFolderReference().getAbsolutePath().replace("\\", "\\\\") + 
									currentExecutionTestSuiteByReference.getTestSuiteRepositoryAbsoluteFolderPath().replace("\\", "\\\\");
							executionTestSuite.setExecutionTestSuiteTestSuiteRepositoryFile(currentExecutionTestSuiteExcelFilePath);

							executionTestSuite.setExecutionTestSuiteTestSuiteExecutionPriority(currentExecutionTestSuiteByReference.getTestSuiteExecutionPriority());
							executionTestSuite.setExecutionTestSuiteTestSuiteExecutionMode(currentExecutionTestSuiteByReference.getTestSuiteExecutionMode());

							executionTestSuite.setExecutionTestSuiteTestSuiteSerialNumber(tempExecutionMasterTestSuiteSerialNumber);
							executionTestSuite.setExecutionTestSuiteExecutionStatus(0);

							executionTestSuite.setExecutionTestSuiteDeleted(false);

							testManagerDBInterface.addUpdateExecutionTestSuite(executionTestSuite);

							executionTestSuite = testManagerDBInterface.getExecutionTestSuiteByReference(executionTestSuiteReference);

							//String executionTestSuiteNextID = "00000" + (Integer.parseInt(nextExecutionTestSuiteNextIDRuntimeConfigParamater.getRuntimeConfigParametersKeyData()) + 1);
							//executionTestSuiteNextID = executionTestSuiteNextID.substring(executionTestSuiteNextID.length() - 5);
							//nextExecutionTestSuiteNextIDRuntimeConfigParamater.setRuntimeConfigParametersKeyData(executionTestSuiteNextID);
							//testManagerDBInterface.addUpdateRuntimeConfigParameter(nextExecutionTestSuiteNextIDRuntimeConfigParamater);

							String executionTestSuiteNextID = Long.toString((Long.parseUnsignedLong(nextExecutionTestSuiteNextIDRuntimeConfigParamater.getRuntimeConfigParametersKeyData()) + 1));
							nextExecutionTestSuiteNextIDRuntimeConfigParamater.setRuntimeConfigParametersKeyData(executionTestSuiteNextID);
							testManagerDBInterface.addUpdateRuntimeConfigParameter(nextExecutionTestSuiteNextIDRuntimeConfigParamater);

							//Test Scenarios 
							currentExecutionTestSuiteFileInputStreamReference = new FileInputStream(currentExecutionTestSuiteExcelFilePath);
							currentExecutionTestSuiteWorkBookReference = new XSSFWorkbook (currentExecutionTestSuiteFileInputStreamReference);
							currentExecutionTestScenarioDataWorksheet = currentExecutionTestSuiteWorkBookReference.getSheet("TestScenarios");							

							int currentExecutionTestScenarioReferenceColumn = Integer.parseInt(tableConfigProp.getProperty("TestScenarioReferenceColumn"));
							int currentExecutionTestScenarioExecuteTestScenarioColumn = Integer.parseInt(tableConfigProp.getProperty("TestScenarioExecuteTestScenarioColumn"));

							Iterator<Row>  currentExecutionTestScenarioIterator = currentExecutionTestScenarioDataWorksheet.iterator();

							currentExecutionTestScenarioIterator.next();
							while (currentExecutionTestScenarioIterator.hasNext()) {
								Row currentExecutionTestScenarioRow = currentExecutionTestScenarioIterator.next();

								String currentExecutionTestScenarioReference = currentExecutionTestScenarioRow.getCell(currentExecutionTestScenarioReferenceColumn).getStringCellValue();
								String currentExecutionTestScenarioExecuteTestScenario = currentExecutionTestScenarioRow.getCell(currentExecutionTestScenarioExecuteTestScenarioColumn).getStringCellValue();

								if (currentExecutionTestScenarioExecuteTestScenario.equalsIgnoreCase("YES") || runAllScenariosOverride) {
									TestScenario currentExecutionTestScenarioByReference = testManagerDBInterface.getTestScenarioByReference(currentExecutionTestScenarioReference);

									RuntimeConfigParameter nextExecutionTestScenarioNextIDRuntimeConfigParamater = testManagerDBInterface.getRuntimeConfigParameterByReference("ExecutionTestScenarioNextID");
									String executionTestScenarioReference = "ExecutionTestScenario_" + nextExecutionTestScenarioNextIDRuntimeConfigParamater.getRuntimeConfigParametersKeyData();

									ExecutionTestScenarios executionTestScenario = new ExecutionTestScenarios();
									executionTestScenario.setExecutionTestScenarioReference(executionTestScenarioReference);
									executionTestScenario.setExecutionTestScenarioDescription(executionTestScenarioReference + ": " + currentExecutionTestScenarioByReference.getTestScenarioDescription());

									//	executionTestScenario.setExecutionTestScenario_Lob(currentExecutionTestScenarioByReference.getTestScenario_Lob());
									//	executionTestScenario.setExecutionTestScenario_Product(currentExecutionTestScenarioByReference.getTestScenario_Product());
									executionTestScenario.setExecutionTestScenario_Scripter(currentExecutionTestScenarioByReference.getTestScenario_Scripter());


									executionTestScenario.setExecutionTestScenarioExecutionTestSuiteID(executionTestSuite.getExecutionTestSuiteID());
									executionTestScenario.setExecutionTestScenarioExecutionTestSuiteReference(executionTestSuite.getExecutionTestSuiteReference());

									executionTestScenario.setExecutionTestScenarioTestPlanID(executionTestPlan.getTestPlanID());
									executionTestScenario.setExecutionTestScenarioTestPlanReference(executionTestPlan.getTestPlanReference());

									executionTestScenario.setExecutionTestScenarioMasterTestSuiteID(currentExecutionMasterTestSuiteByReference.getMasterTestSuiteID());
									executionTestScenario.setExecutionTestScenarioMasterTestSuiteReference(currentExecutionMasterTestSuiteByReference.getMasterTestSuiteReference());

									executionTestScenario.setExecutionTestScenarioTestSuiteID(currentExecutionTestSuiteByReference.getTestSuiteID());
									executionTestScenario.setExecutionTestScenarioTestSuiteReference(currentExecutionTestSuiteByReference.getTestSuiteReference());

									executionTestScenario.setExecutionTestScenarioTestScenarioID(currentExecutionTestScenarioByReference.getTestScenarioID());
									executionTestScenario.setExecutionTestScenarioTestScenarioReference(currentExecutionTestScenarioByReference.getTestScenarioReference());

									executionTestScenario.setExecutionTestScenarioSerialNumber(currentExecutionTestScenarioByReference.getTestScenarioSerialNumber());
									executionTestScenario.setExecutionTestScenarioExecutionStatus(0);

									executionTestScenario.setExecutionTestScenarioNextSnapShotNumber(1);

									executionTestScenario.setExecutionTestScenarioDeleted(false);

									testManagerDBInterface.addUpdateExecutionTestScenario(executionTestScenario);

									executionTestScenario = testManagerDBInterface.getExecutionTestScenarioByReference(executionTestScenarioReference);

									//String executionTestScenarioNextID = "00000" + (Integer.parseInt(nextExecutionTestScenarioNextIDRuntimeConfigParamater.getRuntimeConfigParametersKeyData()) + 1);
									//executionTestScenarioNextID = executionTestScenarioNextID.substring(executionTestScenarioNextID.length() - 5);
									//nextExecutionTestScenarioNextIDRuntimeConfigParamater.setRuntimeConfigParametersKeyData(executionTestScenarioNextID);
									//testManagerDBInterface.addUpdateRuntimeConfigParameter(nextExecutionTestScenarioNextIDRuntimeConfigParamater);

									String executionTestScenarioNextID = Long.toString((Long.parseUnsignedLong(nextExecutionTestScenarioNextIDRuntimeConfigParamater.getRuntimeConfigParametersKeyData()) + 1));
									nextExecutionTestScenarioNextIDRuntimeConfigParamater.setRuntimeConfigParametersKeyData(executionTestScenarioNextID);
									testManagerDBInterface.addUpdateRuntimeConfigParameter(nextExecutionTestScenarioNextIDRuntimeConfigParamater);

									int stepCounter = 1;
									uploadExecutionTestSteps(executionTestPlan, executionMasterTestSuite, executionTestSuite, executionTestScenario, 
											currentExecutionMasterTestSuiteRow, currentExecutionTestSuiteRow, currentExecutionTestScenarioRow, 
											currentExecutionMasterTestSuiteDataWorksheet, currentExecutionTestSuiteDataWorksheet, currentExecutionTestScenarioDataWorksheet,
											currentExecutionMasterTestSuiteByReference, currentExecutionTestSuiteByReference, currentExecutionTestScenarioByReference, 
											stepCounter);
								}
							}

							currentExecutionTestSuiteWorkBookReference.close();
							currentExecutionTestSuiteFileInputStreamReference.close();
						}
						else {
							throw new RuntimeException("Invalid mapping between Master Test Suite - Test Suite.");
						}
					}
				}
			}
		}		

		currentExecutionMasterTestSuiteWorkBookReference.close();
		currentExecutionMasterTestSuiteFileInputStreamReference.close();
	}

	public void uploadExecutionTestSteps(TestPlan executionTestPlan, ExecutionMasterTestSuites executionMasterTestSuite, ExecutionTestSuites executionTestSuite, ExecutionTestScenarios executionTestScenario, 
			Row currentExecutionMasterTestSuiteRow, Row currentExecutionTestSuiteRow, Row currentExecutionTestScenarioRow, 
			XSSFSheet currentExecutionMasterTestSuiteDataWorksheet, XSSFSheet currentExecutionTestSuiteDataWorksheet, XSSFSheet currentExecutionTestScenarioDataWorksheet,
			MasterTestSuite currentExecutionMasterTestSuiteByReference, TestSuite currentExecutionTestSuiteByReference, TestScenario currentExecutionTestScenarioByReference, 
			int stepCounter) {

		/*String iterateTestScenarioPrerequisiteTestSuiteID = currentExecutionTestScenarioByReference.getTestScenarioPrerequisiteTestSuiteID();
		String iterateTestScenarioPrerequisiteTestSuiteReference = currentExecutionTestScenarioByReference.getTestScenarioPrerequisiteTestSuiteReference();
		TestSuite iterateTestSuiteByReference = getTestSuiteByReference(iterateTestScenarioPrerequisiteTestSuiteReference);

		String iterateTestScenarioPrerequisiteTestScenarioID = currentExecutionTestScenarioByReference.getTestScenarioPrerequisiteTestScenarioID();
		String iterateTestScenarioPrerequisiteTestScenarioReference = currentExecutionTestScenarioByReference.getTestScenarioPrerequisiteTestScenarioReference();
		TestScenario iterateTestScenarioByReference = getTestScenarioByReference(iterateTestScenarioPrerequisiteTestScenarioReference);

		String iterateTestScenarioMasterAutomationScriptID = currentExecutionTestScenarioByReference.getTestScenarioMasterAutomationScriptID();
		String iterateTestScenarioMasterAutomationScriptReference = currentExecutionTestScenarioByReference.getTestScenarioMasterAutomationScriptReference();

		if (iterateTestScenarioPrerequisiteTestSuiteReference != null && 
				!(iterateTestScenarioPrerequisiteTestSuiteReference.equalsIgnoreCase("") || iterateTestScenarioPrerequisiteTestSuiteReference.length() == 0)) { 
			uploadExecutionTestSteps(executionTestPlan, executionMasterTestSuite, executionTestSuite, executionTestScenario, 
					currentExecutionMasterTestSuiteRow, currentExecutionTestSuiteRow, currentExecutionTestScenarioRow, 
					currentExecutionMasterTestSuiteDataWorksheet, currentExecutionTestSuiteDataWorksheet, currentExecutionTestScenarioDataWorksheet,
					currentExecutionMasterTestSuiteByReference, iterateTestSuiteByReference, iterateTestScenarioByReference, 
					stepCounter);
		}*/

		MasterAutomationScript masterAutomationScriptByReference = testManagerDBInterface.getMasterAutomationScriptByReference(currentExecutionTestScenarioByReference.getTestScenarioMasterAutomationScriptReference());
		List<MasterAutomationScriptStep> masterAutomationScriptStepByReference = testManagerDBInterface.getMasterAutomationScriptStepsByMasterAutomationScriptReference(currentExecutionTestScenarioByReference.getTestScenarioMasterAutomationScriptReference());

		for (MasterAutomationScriptStep masterAutomationScriptStep : masterAutomationScriptStepByReference) {
			ExecutionTestSteps currentExecutionTestStep = new ExecutionTestSteps();

			RuntimeConfigParameter nextExecutionTestStepNextIDRuntimeConfigParamater = testManagerDBInterface.getRuntimeConfigParameterByReference("ExecutionTestStepNextID");
			String executionTestStepReference = "ExecutionTestStep_" + nextExecutionTestStepNextIDRuntimeConfigParamater.getRuntimeConfigParametersKeyData();

			currentExecutionTestStep.setExecutionTestStepReference(executionTestStepReference);
			currentExecutionTestStep.setExecutionTestStepDescription(executionTestStepReference);
			currentExecutionTestStep.setExecutionTestStepExecutionTestScenarioID(executionTestScenario.getExecutionTestScenarioID());
			currentExecutionTestStep.setExecutionTestStepExecutionTestScenarioReference(executionTestScenario.getExecutionTestScenarioReference());
			currentExecutionTestStep.setExecutionTestStepTestPlanID(executionTestPlan.getTestPlanID());
			currentExecutionTestStep.setExecutionTestStepTestPlanReference(executionTestPlan.getTestPlanReference());
			currentExecutionTestStep.setExecutionTestStepMasterTestSuiteID(currentExecutionMasterTestSuiteByReference.getMasterTestSuiteID());
			currentExecutionTestStep.setExecutionTestStepMasterTestSuiteReference(currentExecutionMasterTestSuiteByReference.getMasterTestSuiteReference());
			currentExecutionTestStep.setExecutionTestStepTestSuiteID(currentExecutionTestSuiteByReference.getTestSuiteID());
			currentExecutionTestStep.setExecutionTestStepTestSuiteReference(currentExecutionTestSuiteByReference.getTestSuiteReference());
			currentExecutionTestStep.setExecutionTestStepTestScenarioID(currentExecutionTestScenarioByReference.getTestScenarioID());
			currentExecutionTestStep.setExecutionTestStepTestScenarioReference(currentExecutionTestScenarioByReference.getTestScenarioReference());
			currentExecutionTestStep.setExecutionTestStepMasterAutomationScriptID(masterAutomationScriptByReference.getMasterAutomationScriptID());
			currentExecutionTestStep.setExecutionTestStepMasterAutomationScriptReference(masterAutomationScriptByReference.getMasterAutomationScriptReference());
			currentExecutionTestStep.setExecutionTestStepMasterAutomationScriptStepID(masterAutomationScriptStep.getMasterAutomationScriptStepID());
			currentExecutionTestStep.setExecutionTestStepMasterAutomationScriptStepReference(masterAutomationScriptStep.getMasterAutomationScriptStepReference());
			currentExecutionTestStep.setExecutionTestStepMasterAutomationScriptStepKeyword(masterAutomationScriptStep.getMasterAutomationScriptStepStepKeyword());
			currentExecutionTestStep.setExecutionTestStepMasterAutomationScriptStepConfigData(masterAutomationScriptStep.getMasterAutomationScriptStepConfigData());
			currentExecutionTestStep.setExecutionTestStepMasterAutomationScriptStepStepGroup(masterAutomationScriptStep.getMasterAutomationScriptStepStepGroup());
			currentExecutionTestStep.setExecutionTestStepMasterAutomationScriptStepExecutionSequence(stepCounter);
			stepCounter++;
			currentExecutionTestStep.setExecutionTestStepMasterAutomationScriptStepSkipStep(masterAutomationScriptStep.getMasterAutomationScriptStepSkipStep());
			currentExecutionTestStep.setExecutionTestStepMasterAutomationScriptStepToBeExecutedOnMachine(masterAutomationScriptStep.getMasterAutomationScriptStepToBeExecutedByMachine());
			currentExecutionTestStep.setExecutionTestStepExecutionStatus(0);
			currentExecutionTestStep.setExecutionTestStepDeleted(false);

			testManagerDBInterface.addUpdateExecutionTestStep(currentExecutionTestStep);

			currentExecutionTestStep = testManagerDBInterface.getExecutionTestStepByReference(executionTestStepReference);

			//String executionTestStepNextID = "00000" + (Integer.parseInt(nextExecutionTestStepNextIDRuntimeConfigParamater.getRuntimeConfigParametersKeyData()) + 1);
			//executionTestStepNextID = executionTestStepNextID.substring(executionTestStepNextID.length() - 5);
			//nextExecutionTestStepNextIDRuntimeConfigParamater.setRuntimeConfigParametersKeyData(executionTestStepNextID);
			//testManagerDBInterface.addUpdateRuntimeConfigParameter(nextExecutionTestStepNextIDRuntimeConfigParamater);

			String executionTestStepNextID = Long.toString((Long.parseUnsignedLong(nextExecutionTestStepNextIDRuntimeConfigParamater.getRuntimeConfigParametersKeyData()) + 1));
			nextExecutionTestStepNextIDRuntimeConfigParamater.setRuntimeConfigParametersKeyData(executionTestStepNextID);
			testManagerDBInterface.addUpdateRuntimeConfigParameter(nextExecutionTestStepNextIDRuntimeConfigParamater);
		}
	}

	public void createFolderStructureForResults(String executionResultsBaseFolder) {
		DateFormat dateFormat = new SimpleDateFormat("yyyy_MM_dd_HH_mm_ss_zzz");
		Date date = new Date();
		currentExecutionResultsFolder = executionResultsBaseFolder + "\\ExecutionResults_" + dateFormat.format(date);
		this.currentExecutionResultsFolderReference = new File(currentExecutionResultsFolder);
		currentExecutionResultsFolderReference.mkdirs();

		String testAutomationSuitesAndTestDataFolder = currentExecutionResultsFolder + "\\TestAutomationSuitesAndTestData";
		this.currentTestAutomationSuitesAndTestDataFolderReference = new File(testAutomationSuitesAndTestDataFolder);
		currentTestAutomationSuitesAndTestDataFolderReference.mkdirs();

		String currentScenarioExecutionResultsFolder = currentExecutionResultsFolder + "\\ScenarioExecutionResults";
		this.currentScenarioExecutionResultsFolderReference = new File(currentScenarioExecutionResultsFolder);
		currentScenarioExecutionResultsFolderReference.mkdirs();

		String currentTestNGExecutionResultsFolder = currentExecutionResultsFolder + "\\TestNGResults";
		this.currentTestNGExecutionResultsFolderReference = new File(currentTestNGExecutionResultsFolder);
		currentTestNGExecutionResultsFolderReference.mkdirs();

	}



	public String getCurrentExecutionResultsFolder() {
		return currentExecutionResultsFolder;
	}

	public File getCurrentExecutionResultsFolderReference() {
		return currentExecutionResultsFolderReference;
	}

	public File getCurrentScenarioExecutionResultsFolderReference() {
		return currentScenarioExecutionResultsFolderReference;
	}

	public File getCurrentTestNGExecutionResultsFolderReference() {
		return currentTestNGExecutionResultsFolderReference;
	}

	public File getCurrentTestAutomationSuitesAndTestDataFolderReference() {
		return currentTestAutomationSuitesAndTestDataFolderReference;
	}

	public TestPlan getCurrentExecutionTestPlan() {
		return currentExecutionTestPlan;
	}

	public void setCurrentExecutionTestPlan(TestPlan currentExecutionTestPlan) {
		this.currentExecutionTestPlan = currentExecutionTestPlan;
	}

	public void launchExecution() {
		TestPlan testPlan = getCurrentExecutionTestPlan();
		List <ExecutionMasterTestSuites> allMasterTestSuites = testManagerDBInterface.getAllExecutionMasterTestSuiteByTestPlanReference(testPlan.getTestPlanReference());

		for (ExecutionMasterTestSuites masterTestSuite : allMasterTestSuites) {
			List <ExecutionTestSuites> allTestSuites = testManagerDBInterface.getAllExecutionTestSuiteByExecutionMasterTestSuiteReference(masterTestSuite.getExecutionMasterTestSuiteReference());

			TestNG testNGReference = new TestNG();
			testNGReference.setOutputDirectory(getCurrentTestNGExecutionResultsFolderReference().getAbsolutePath());

			List<XmlSuite> suites = new ArrayList<XmlSuite>();

			for (ExecutionTestSuites testSuite : allTestSuites) {
				XmlSuite suite = new XmlSuite();
				suite.setName(testSuite.getExecutionTestSuiteReference());
				if (testSuite.getExecutionTestSuiteTestSuiteExecutionMode().equalsIgnoreCase("Parallel"))
					suite.setParallel("tests"); 
				else if (testSuite.getExecutionTestSuiteTestSuiteExecutionMode().equalsIgnoreCase("Sequential"))
					suite.setParallel("none");
				else
					suite.setParallel("none");

				List <ExecutionTestScenarios> allTestScenarios = testManagerDBInterface.getAllExecutionTestScenariosByExecutionTestSuiteReference(testSuite.getExecutionTestSuiteReference());
				for (ExecutionTestScenarios testScenario : allTestScenarios) {
					XmlTest test = new XmlTest(suite);
					//Commented by PKP as Random incrementing number is been set
					//test.setName(testScenario.getExecutionTestScenarioReference());
					test.setName(testScenario.getExecutionTestScenarioTestScenarioReference());
					test.setVerbose(2);
					//jellyFish
					Map<String, String> parameters = new HashMap<String, String>();
					parameters.put("platform", testPlan.getTestPlanPlatformToTestOn());
					parameters.put("browsername", testPlan.getTestPlanBrowserToTestOn());
					parameters.put("browserversion", testPlan.getTestPlanBrowserVersionToTestOn());
					parameters.put("testPlan_Reference", testPlan.getTestPlanReference());
					parameters.put("executionMasterTestSuite_Reference", masterTestSuite.getExecutionMasterTestSuiteReference());
					parameters.put("executionMasterTestSuite_MasterTestSuiteReference", masterTestSuite.getExecutionMasterTestSuiteMasterTestSuiteReference());
					parameters.put("executionTestSuite_Reference", testSuite.getExecutionTestSuiteReference());
					parameters.put("executionTestSuite_TestSuiteReference", testSuite.getExecutionTestSuiteTestSuiteReference());
					parameters.put("executionTestScenario_Reference", testScenario.getExecutionTestScenarioReference());
					parameters.put("executionTestScenario_TestScenarioReference", testScenario.getExecutionTestScenarioTestScenarioReference());
					parameters.put("executionTestScenario_Description", testScenario.getExecutionTestScenarioDescription());

					test.setParameters(parameters);

					List<XmlClass> classes = new ArrayList<XmlClass>();
					classes.add(new XmlClass("com.aqm.tests.AutomationDriverScript"));
					test.setXmlClasses(classes);
				}

				suites.add(suite);
			}
			testNGReference.setXmlSuites(suites);

			List<Class> listenerClassList = new ArrayList<Class>();
			listenerClassList.add(org.uncommons.reportng.HTMLReporter.class);
			listenerClassList.add(org.uncommons.reportng.JUnitXMLReporter.class);
			testNGReference.setListenerClasses(listenerClassList);
			try {
				testNGReference.run();
			}
			catch(Exception e) {
				e.printStackTrace();
			}
		}
	}

	/*public void updateExecutionLog() throws IOException {
		String currentExecutionMasterTestSuiteExcelFilePath;				
		File currentExecutionMasterTestSuiteExcelFileReference;
		FileInputStream currentExecutionMasterTestSuiteFileInputStreamReference;
		FileOutputStream currentExecutionMasterTestSuiteFileOutputStreamReference;
		XSSFWorkbook currentExecutionMasterTestSuiteWorkBookReference;
		XSSFSheet currentExecutionMasterTestSuiteDataWorksheet;
		XSSFSheet currentExecutionTestSuiteDataWorksheet;

		File currentExecutionTestSuiteExcelFileReference;
		FileInputStream currentExecutionTestSuiteFileInputStreamReference;
		XSSFWorkbook currentExecutionTestSuiteWorkBookReference;
		XSSFSheet currentExecutionTestScenarioDataWorksheet;

		currentExecutionMasterTestSuiteExcelFilePath = getCurrentTestAutomationSuitesAndTestDataFolderReference().getAbsolutePath().replace("\\", "\\\\") + 
				"\\\\" + this.baseMasterTestSuiteFileForExecution;

		currentExecutionMasterTestSuiteExcelFileReference = new File(currentExecutionMasterTestSuiteExcelFilePath);
		currentExecutionMasterTestSuiteFileInputStreamReference = new FileInputStream(currentExecutionMasterTestSuiteExcelFileReference);
		currentExecutionMasterTestSuiteWorkBookReference = new XSSFWorkbook (currentExecutionMasterTestSuiteFileInputStreamReference);

		TestPlan executionTestPlan = getCurrentExecutionTestPlan();

		//Master Test Suite
		currentExecutionMasterTestSuiteDataWorksheet = currentExecutionMasterTestSuiteWorkBookReference.getSheet("MasterTestSuites");

		int currentExecutionMasterTestSuiteReferenceColumn = Integer.parseInt(tableConfigProp.getProperty("MasterTestSuiteReferenceColumn"));
		int currentExecutionMasterTestSuiteExecuteMasterTestSuiteColumn = Integer.parseInt(tableConfigProp.getProperty("MasterTestSuiteExecuteMasterTestSuiteColumn"));
		int currentExecutionMasterTestSuiteExecutionStatusColumn = Integer.parseInt(tableConfigProp.getProperty("MasterTestSuiteExecutionStatusColumn"));

		Iterator<Row> currentExecutionMasterTestSuiteIterator = currentExecutionMasterTestSuiteDataWorksheet.iterator();

		currentExecutionMasterTestSuiteIterator.next();
		while (currentExecutionMasterTestSuiteIterator.hasNext()) {
			Row currentExecutionMasterTestSuiteRow = currentExecutionMasterTestSuiteIterator.next();

			String currentExecutionMasterTestSuiteReference = currentExecutionMasterTestSuiteRow.getCell(currentExecutionMasterTestSuiteReferenceColumn).getStringCellValue();
			String currentExecutionMasterTestSuiteExecuteMasterTestSuite = currentExecutionMasterTestSuiteRow.getCell(currentExecutionMasterTestSuiteExecuteMasterTestSuiteColumn).getStringCellValue();

			if (currentExecutionMasterTestSuiteExecuteMasterTestSuite.equalsIgnoreCase("YES")) {
				Properties executionMasterTestSuiteFilterProperties = new Properties();
				executionMasterTestSuiteFilterProperties.put("executionMasterTestSuite_TestPlanReference", executionTestPlan.getTestPlanReference());
				executionMasterTestSuiteFilterProperties.put("executionMasterTestSuite_MasterTestSuiteReference", currentExecutionMasterTestSuiteReference);

				ExecutionMasterTestSuites executionMasterTestSuite = testManagerDBInterface.getExecutionMasterTestSuiteByCriteria(executionMasterTestSuiteFilterProperties);

				switch (executionMasterTestSuite.getExecutionMasterTestSuiteExecutionStatus()) {
					case 0:
						currentExecutionMasterTestSuiteRow.getCell(currentExecutionMasterTestSuiteExecutionStatusColumn).setCellValue("Not Executed");
						break;
					case 1:
						currentExecutionMasterTestSuiteRow.getCell(currentExecutionMasterTestSuiteExecutionStatusColumn).setCellValue("Executed");
						break;
					case 2:
						currentExecutionMasterTestSuiteRow.getCell(currentExecutionMasterTestSuiteExecutionStatusColumn).setCellValue("Execution Failed");
						break;
					default:
						currentExecutionMasterTestSuiteRow.getCell(currentExecutionMasterTestSuiteExecutionStatusColumn).setCellValue("Error!!!");
						break;
				}

				//Test Suite
				currentExecutionTestSuiteDataWorksheet = currentExecutionMasterTestSuiteWorkBookReference.getSheet("TestSuites");

				int currentExecutionTestSuiteReferenceColumn = Integer.parseInt(tableConfigProp.getProperty("TestSuiteReferenceColumn"));
				int currentExecutionTestSuiteMasterTestSuiteReferenceColumn = Integer.parseInt(tableConfigProp.getProperty("TestSuiteMasterTestSuiteReferenceColumn"));
				int currentExecutionTestSuiteExecuteTestSuiteColumn = Integer.parseInt(tableConfigProp.getProperty("TestSuiteExecuteTestSuiteColumn"));
				int currentExecutionTestSuiteExecutionStatusColumn = Integer.parseInt(tableConfigProp.getProperty("TestSuiteExecutionStatusColumn"));

				Iterator<Row> currentExecutionTestSuiteIterator = currentExecutionTestSuiteDataWorksheet.iterator();
				currentExecutionTestSuiteIterator.next();
				while (currentExecutionTestSuiteIterator.hasNext()) {
					Row currentExecutionTestSuiteRow = currentExecutionTestSuiteIterator.next();

					String currentExecutionTestSuiteReference = currentExecutionTestSuiteRow.getCell(currentExecutionTestSuiteReferenceColumn).getStringCellValue();
					String currentExecutionTestSuiteMasterTestSuiteReference = currentExecutionTestSuiteRow.getCell(currentExecutionTestSuiteMasterTestSuiteReferenceColumn).getStringCellValue();
					String currentExecutionTestSuiteExecuteTestSuite = currentExecutionTestSuiteRow.getCell(currentExecutionTestSuiteExecuteTestSuiteColumn).getStringCellValue();

					if (currentExecutionTestSuiteExecuteTestSuite.equalsIgnoreCase("YES")) {
						TestSuite currentExecutionTestSuiteByReference = testManagerDBInterface.getTestSuiteByReference(currentExecutionTestSuiteReference);

						Properties executionTestSuiteFilterProperties = new Properties();
						executionTestSuiteFilterProperties.put("executionTestSuite_TestPlanReference", executionTestPlan.getTestPlanReference());
						executionTestSuiteFilterProperties.put("executionTestSuite_MasterTestSuiteReference", currentExecutionTestSuiteMasterTestSuiteReference);
						executionTestSuiteFilterProperties.put("executionTestSuite_TestSuiteReference", currentExecutionTestSuiteReference);

						ExecutionTestSuites executionTestSuite = testManagerDBInterface.getExecutionTestSuiteByCriteria(executionTestSuiteFilterProperties);

						switch (executionTestSuite.getExecutionTestSuiteExecutionStatus()) {
							case 0:
								currentExecutionTestSuiteRow.getCell(currentExecutionTestSuiteExecutionStatusColumn).setCellValue("Not Executed");
								break;
							case 1:
								currentExecutionTestSuiteRow.getCell(currentExecutionTestSuiteExecutionStatusColumn).setCellValue("Executed");
								break;
							case 2:
								currentExecutionTestSuiteRow.getCell(currentExecutionTestSuiteExecutionStatusColumn).setCellValue("Execution Failed");
								break;
							default:
								currentExecutionTestSuiteRow.getCell(currentExecutionTestSuiteExecutionStatusColumn).setCellValue("Error!!!");
								break;
						}

						//Test Scenarios 
						String currentExecutionTestSuiteExcelFilePath = executionTestSuite.getExecutionTestSuiteTestSuiteRepositoryFile();
						currentExecutionTestSuiteFileInputStreamReference = new FileInputStream(currentExecutionTestSuiteExcelFilePath);
						currentExecutionTestSuiteWorkBookReference = new XSSFWorkbook (currentExecutionTestSuiteFileInputStreamReference);
						currentExecutionTestScenarioDataWorksheet = currentExecutionTestSuiteWorkBookReference.getSheet("TestScenarios");							

						int currentExecutionTestScenarioReferenceColumn = Integer.parseInt(tableConfigProp.getProperty("TestScenarioReferenceColumn"));
						int currentExecutionTestScenarioExecuteTestScenarioColumn = Integer.parseInt(tableConfigProp.getProperty("TestScenarioExecuteTestScenarioColumn"));
						int currentExecutionTestScenarioExecutionStatusColumn = Integer.parseInt(tableConfigProp.getProperty("TestScenarioExecutionStatusColumn"));

						Iterator<Row>  currentExecutionTestScenarioIterator = currentExecutionTestScenarioDataWorksheet.iterator();

						currentExecutionTestScenarioIterator.next();
						while (currentExecutionTestScenarioIterator.hasNext()) {
							Row currentExecutionTestScenarioRow = currentExecutionTestScenarioIterator.next();

							String currentExecutionTestScenarioReference = currentExecutionTestScenarioRow.getCell(currentExecutionTestScenarioReferenceColumn).getStringCellValue();
							String currentExecutionTestScenarioExecuteTestScenario = currentExecutionTestScenarioRow.getCell(currentExecutionTestScenarioExecuteTestScenarioColumn).getStringCellValue();

							if (currentExecutionTestScenarioExecuteTestScenario.equalsIgnoreCase("YES")) {
								TestScenario currentExecutionTestScenarioByReference = testManagerDBInterface.getTestScenarioByReference(currentExecutionTestScenarioReference);

								Properties executionTestScenarioFilterProperties = new Properties();
								executionTestScenarioFilterProperties.put("executionTestScenario_TestPlanReference", executionTestPlan.getTestPlanReference());
								executionTestScenarioFilterProperties.put("executionTestScenario_MasterTestSuiteReference", currentExecutionTestScenarioByReference.getTestScenarioMasterTestSuiteReference());
								executionTestScenarioFilterProperties.put("executionTestScenario_TestSuiteReference", currentExecutionTestScenarioByReference.getTestScenarioTestSuiteReference());
								executionTestScenarioFilterProperties.put("executionTestScenario_TestScenarioReference", currentExecutionTestScenarioReference);

								ExecutionTestScenarios executionTestScenario = testManagerDBInterface.getExecutionTestScenarioByCriteria(executionTestScenarioFilterProperties);

								switch (executionTestScenario.getExecutionTestScenarioExecutionStatus()) {
									case 0:
										currentExecutionTestScenarioRow.getCell(currentExecutionTestScenarioExecutionStatusColumn).setCellValue("Not Executed");
										break;
									case 1:
										currentExecutionTestScenarioRow.getCell(currentExecutionTestScenarioExecutionStatusColumn).setCellValue("Passed");
										break;
									case 2:
										currentExecutionTestScenarioRow.getCell(currentExecutionTestScenarioExecutionStatusColumn).setCellValue("Failed");
										break;
									default:
										currentExecutionTestScenarioRow.getCell(currentExecutionTestScenarioExecutionStatusColumn).setCellValue("Error!!!");
										break;
								}
							}
						}

						currentExecutionTestSuiteFileInputStreamReference.close();

						File executionTestSuiteOutputFile = new File(currentExecutionTestSuiteExcelFilePath);
						FileOutputStream executionTestSuiteFileOutputStreamReference = new FileOutputStream(executionTestSuiteOutputFile);

						currentExecutionTestSuiteWorkBookReference.write(executionTestSuiteFileOutputStreamReference);

						executionTestSuiteFileOutputStreamReference.close();
						currentExecutionTestSuiteWorkBookReference.close();
					}
				}
			}
		}		

		currentExecutionMasterTestSuiteFileInputStreamReference.close();

		File currentExecutionMasterTestSuiteOutputFile = new File(currentExecutionMasterTestSuiteExcelFilePath);
		FileOutputStream masterTestSuiteFileOutputStreamReference = new FileOutputStream(currentExecutionMasterTestSuiteOutputFile);

		currentExecutionMasterTestSuiteWorkBookReference.write(masterTestSuiteFileOutputStreamReference);
		masterTestSuiteFileOutputStreamReference.close();

		currentExecutionMasterTestSuiteWorkBookReference.close();
	}*/

	@SuppressWarnings("resource")
	public void updateExecutionLog() throws IOException {
		String currentExecutionMasterTestSuiteExcelFilePath;				
		File currentExecutionMasterTestSuiteExcelFileReference;
		FileInputStream currentExecutionMasterTestSuiteFileInputStreamReference;
		FileOutputStream currentExecutionMasterTestSuiteFileOutputStreamReference;
		XSSFWorkbook currentExecutionMasterTestSuiteWorkBookReference;
		XSSFSheet currentExecutionMasterTestSuiteDataWorksheet;
		XSSFSheet currentExecutionTestSuiteDataWorksheet;

		File currentExecutionTestSuiteExcelFileReference;
		FileInputStream currentExecutionTestSuiteFileInputStreamReference;
		XSSFWorkbook currentExecutionTestSuiteWorkBookReference;
		XSSFSheet currentExecutionTestScenarioDataWorksheet;

		currentExecutionMasterTestSuiteExcelFilePath = getCurrentTestAutomationSuitesAndTestDataFolderReference().getAbsolutePath().replace("\\", "\\\\") + 
				"\\\\" + this.baseMasterTestSuiteFileForExecution;

		currentExecutionMasterTestSuiteExcelFileReference = new File(currentExecutionMasterTestSuiteExcelFilePath);
		currentExecutionMasterTestSuiteFileInputStreamReference = new FileInputStream(currentExecutionMasterTestSuiteExcelFileReference);
		currentExecutionMasterTestSuiteWorkBookReference = new XSSFWorkbook (currentExecutionMasterTestSuiteFileInputStreamReference);

		TestPlan executionTestPlan = getCurrentExecutionTestPlan();

		//Master Test Suite
		currentExecutionMasterTestSuiteDataWorksheet = currentExecutionMasterTestSuiteWorkBookReference.getSheet("MasterTestSuites");

		int currentExecutionMasterTestSuiteReferenceColumn = Integer.parseInt(tableConfigProp.getProperty("MasterTestSuiteReferenceColumn"));
		int currentExecutionMasterTestSuiteExecuteMasterTestSuiteColumn = Integer.parseInt(tableConfigProp.getProperty("MasterTestSuiteExecuteMasterTestSuiteColumn"));
		int currentExecutionMasterTestSuiteExecutionStatusColumn = Integer.parseInt(tableConfigProp.getProperty("MasterTestSuiteExecutionStatusColumn"));

		List<ExecutionMasterTestSuites> currentExecutionMasterTestSuiteIterator = testManagerDBInterface.getAllExecutionMasterTestSuiteByTestPlanReference(executionTestPlan.getTestPlanReference());

		for (ExecutionMasterTestSuites currentExecutionMasterTestSuite : currentExecutionMasterTestSuiteIterator) {
			String executionMasterTestSuite = currentExecutionMasterTestSuite.getExecutionMasterTestSuiteReference();
			String masterTestSuite = currentExecutionMasterTestSuite.getExecutionMasterTestSuiteMasterTestSuiteReference();

			Row currentExecutionMasterTestSuiteRow = findRowInSheet (currentExecutionMasterTestSuiteDataWorksheet, masterTestSuite, currentExecutionMasterTestSuiteReferenceColumn);

			if (currentExecutionMasterTestSuiteRow == null) {
				throw new DataUploadException("Unable to find Master Test Suite \"" + masterTestSuite + "\" in Log upload files. ");
			}

			switch (currentExecutionMasterTestSuite.getExecutionMasterTestSuiteExecutionStatus()) {
			case 0:
				currentExecutionMasterTestSuiteRow.getCell(currentExecutionMasterTestSuiteExecutionStatusColumn).setCellValue("Not Executed");
				break;
			case 1:
				currentExecutionMasterTestSuiteRow.getCell(currentExecutionMasterTestSuiteExecutionStatusColumn).setCellValue("Executed");
				break;
			case 2:
				currentExecutionMasterTestSuiteRow.getCell(currentExecutionMasterTestSuiteExecutionStatusColumn).setCellValue("Executed with Warnings");
				break;
			case 3:
				currentExecutionMasterTestSuiteRow.getCell(currentExecutionMasterTestSuiteExecutionStatusColumn).setCellValue("Execution Failed");
				break;
			default:
				currentExecutionMasterTestSuiteRow.getCell(currentExecutionMasterTestSuiteExecutionStatusColumn).setCellValue("Error!!!");
				break;
			}

			currentExecutionTestSuiteDataWorksheet = currentExecutionMasterTestSuiteWorkBookReference.getSheet("TestSuites");

			int currentExecutionTestSuiteReferenceColumn = Integer.parseInt(tableConfigProp.getProperty("TestSuiteReferenceColumn"));
			int currentExecutionTestSuiteMasterTestSuiteReferenceColumn = Integer.parseInt(tableConfigProp.getProperty("TestSuiteMasterTestSuiteReferenceColumn"));
			int currentExecutionTestSuiteExecuteTestSuiteColumn = Integer.parseInt(tableConfigProp.getProperty("TestSuiteExecuteTestSuiteColumn"));
			int currentExecutionTestSuiteExecutionStatusColumn = Integer.parseInt(tableConfigProp.getProperty("TestSuiteExecutionStatusColumn"));

			List<ExecutionTestSuites> currentExecutionTestSuites = testManagerDBInterface.getAllExecutionTestSuiteByExecutionMasterTestSuiteReference(executionMasterTestSuite);
			for (ExecutionTestSuites currentExecutionTestSuite : currentExecutionTestSuites) {
				String executionTestSuite = currentExecutionTestSuite.getExecutionTestSuiteReference();
				String testSuite = currentExecutionTestSuite.getExecutionTestSuiteTestSuiteReference();

				Row currentExecutionTestSuiteRow = findRowInSheet (currentExecutionTestSuiteDataWorksheet, testSuite, currentExecutionTestSuiteReferenceColumn);
				if (currentExecutionTestSuiteRow == null) {
					throw new DataUploadException("Unable to find Test Suite \"" + executionTestSuite + "\" in Log upload files. ");
				}

				switch (currentExecutionTestSuite.getExecutionTestSuiteExecutionStatus()) {
				case 0:
					currentExecutionTestSuiteRow.getCell(currentExecutionTestSuiteExecutionStatusColumn).setCellValue("Not Executed");
					break;
				case 1:
					currentExecutionTestSuiteRow.getCell(currentExecutionTestSuiteExecutionStatusColumn).setCellValue("Executed");
					break;
				case 2:
					currentExecutionTestSuiteRow.getCell(currentExecutionTestSuiteExecutionStatusColumn).setCellValue("Executed with Warnings");
					break;
				case 3:
					currentExecutionTestSuiteRow.getCell(currentExecutionTestSuiteExecutionStatusColumn).setCellValue("Execution Failed");
					break;
				default:
					currentExecutionTestSuiteRow.getCell(currentExecutionTestSuiteExecutionStatusColumn).setCellValue("Error!!!");
					break;
				}			

				//Test Scenarios 
				String currentExecutionTestSuiteExcelFilePath = currentExecutionTestSuite.getExecutionTestSuiteTestSuiteRepositoryFile();
				currentExecutionTestSuiteFileInputStreamReference = new FileInputStream(currentExecutionTestSuiteExcelFilePath);
				currentExecutionTestSuiteWorkBookReference = new XSSFWorkbook (currentExecutionTestSuiteFileInputStreamReference);
				currentExecutionTestScenarioDataWorksheet = currentExecutionTestSuiteWorkBookReference.getSheet("TestScenarios");							

				int currentExecutionTestScenarioReferenceColumn = Integer.parseInt(tableConfigProp.getProperty("TestScenarioReferenceColumn"));
				int currentExecutionTestScenarioExecuteTestScenarioColumn = Integer.parseInt(tableConfigProp.getProperty("TestScenarioExecuteTestScenarioColumn"));
				int currentExecutionTestScenarioExecutionStatusColumn = Integer.parseInt(tableConfigProp.getProperty("TestScenarioExecutionStatusColumn"));

				List<ExecutionTestScenarios> currentExecutionTestScenarios = testManagerDBInterface.getAllExecutionTestScenariosByExecutionTestSuiteReference(executionTestSuite);
				for (ExecutionTestScenarios currentExecutionTestScenario : currentExecutionTestScenarios) {
					String executionTestScenario =  currentExecutionTestScenario.getExecutionTestScenarioReference();
					String testScenario =  currentExecutionTestScenario.getExecutionTestScenarioTestScenarioReference();


					Row currentExecutionTestScenarioRow = findRowInSheet (currentExecutionTestScenarioDataWorksheet, testScenario, currentExecutionTestScenarioReferenceColumn);
					if (currentExecutionTestScenarioRow == null) {
						throw new DataUploadException("Unable to find Test Scenario \"" + executionTestScenario + "\" in Log upload files. ");
					}

					switch (currentExecutionTestScenario.getExecutionTestScenarioExecutionStatus()) {
					case 0:
						currentExecutionTestScenarioRow.getCell(currentExecutionTestScenarioExecutionStatusColumn).setCellValue("Not Executed");
						break;
					case 1:
						currentExecutionTestScenarioRow.getCell(currentExecutionTestScenarioExecutionStatusColumn).setCellValue("Passed");
						break;
					case 2:
						currentExecutionTestScenarioRow.getCell(currentExecutionTestScenarioExecutionStatusColumn).setCellValue("Passed with Warnings");
						break;
					case 3:
						currentExecutionTestScenarioRow.getCell(currentExecutionTestScenarioExecutionStatusColumn).setCellValue("Failed");
						break;
					default:
						currentExecutionTestScenarioRow.getCell(currentExecutionTestScenarioExecutionStatusColumn).setCellValue("Error!!!");
						break;
					}
				}

				currentExecutionTestSuiteFileInputStreamReference.close();

				File executionTestSuiteOutputFile = new File(currentExecutionTestSuiteExcelFilePath);
				FileOutputStream executionTestSuiteFileOutputStreamReference = new FileOutputStream(executionTestSuiteOutputFile);

				currentExecutionTestSuiteWorkBookReference.write(executionTestSuiteFileOutputStreamReference);

				executionTestSuiteFileOutputStreamReference.close();
				currentExecutionTestSuiteWorkBookReference.close();
			}
		}

		currentExecutionMasterTestSuiteFileInputStreamReference.close();

		File currentExecutionMasterTestSuiteOutputFile = new File(currentExecutionMasterTestSuiteExcelFilePath);
		FileOutputStream masterTestSuiteFileOutputStreamReference = new FileOutputStream(currentExecutionMasterTestSuiteOutputFile);

		currentExecutionMasterTestSuiteWorkBookReference.write(masterTestSuiteFileOutputStreamReference);
		masterTestSuiteFileOutputStreamReference.close();

		currentExecutionMasterTestSuiteWorkBookReference.close();
	}

	private Row findRowInSheet(XSSFSheet dataWorksheet, String searchText, int searchColumn) {
		Row returnRow = null;

		Iterator<Row> rowIterator = dataWorksheet.iterator();

		rowIterator.next();
		while (rowIterator.hasNext()) {
			Row currentRow = rowIterator.next();
			String textFromRowSearchColumn = currentRow.getCell(searchColumn).getStringCellValue();
			if (textFromRowSearchColumn.trim().equalsIgnoreCase(searchText.trim())) {
				returnRow = currentRow;
				break;
			}
		}

		return returnRow;
	}
}
