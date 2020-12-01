package sample

import static com.kms.katalon.core.checkpoint.CheckpointFactory.findCheckpoint
import static com.kms.katalon.core.testcase.TestCaseFactory.findTestCase
import static com.kms.katalon.core.testdata.TestDataFactory.findTestData
import static com.kms.katalon.core.testobject.ObjectRepository.findTestObject
import static com.kms.katalon.core.testobject.ObjectRepository.findWindowsObject

import com.kms.katalon.core.annotation.Keyword
import com.kms.katalon.core.checkpoint.Checkpoint
import com.kms.katalon.core.cucumber.keyword.CucumberBuiltinKeywords as CucumberKW
import com.kms.katalon.core.mobile.keyword.MobileBuiltInKeywords as Mobile
import com.kms.katalon.core.model.FailureHandling
import com.kms.katalon.core.testcase.TestCase
import com.kms.katalon.core.testdata.TestData
import com.kms.katalon.core.testobject.TestObject
import com.kms.katalon.core.webservice.keyword.WSBuiltInKeywords as WS
import com.kms.katalon.core.webui.keyword.WebUiBuiltInKeywords as WebUI
import com.kms.katalon.core.windows.keyword.WindowsBuiltinKeywords as Windows

import groovy.json.JsonSlurper
import internal.GlobalVariable
import org.openqa.selenium.Cookie
import com.kms.katalon.core.testobject.ResponseObject

import org.openqa.selenium.WebElement
import org.openqa.selenium.WebDriver
import org.openqa.selenium.By

import com.kms.katalon.core.mobile.keyword.internal.MobileDriverFactory
import com.kms.katalon.core.webui.driver.DriverFactory

public class Common {
	public static JsonSlurper jsonSlurper = new JsonSlurper()

	/**
	 * Set token to cookies to bypass Jira authorization
	 */
	@Keyword
	public static void setToken() {
		import org.openqa.selenium.WebDriver
		import com.kms.katalon.core.webui.driver.DriverFactory
		'get cookies'
		ResponseObject response = WS.sendRequest(findTestObject('Post an authorization'))
		String fullToken = response.getHeaderField("Set-Cookie").split(";")[0]
		String tokenValue = fullToken.split("=")[1]
		'add "JSESSIONID" token to cookies'
		Cookie accessToken = new Cookie("JSESSIONID", tokenValue)
		WebDriver driver = DriverFactory.getWebDriver()
		driver.manage().addCookie(accessToken)
	}

	/**
	 * Access to Jira Server and bypass authorization
	 * @return
	 */
	@Keyword
	def accessJira() {
		WebUI.openBrowser(GlobalVariable.serverURL)
		sample.Common.setToken()
		WebUI.navigateToUrl(GlobalVariable.serverURL)
	}

	/**
	 * Create a new ticket
	 * @param project: project id, e.x: 'KT'
	 * @param issueType: type of ticket, e.x: 'Task'
	 * @param summary: name of ticket
	 * @expectedStatus: (optional) expected code returned after API run
	 * @return
	 */
	@Keyword
	def createNewTicket(String project, String issueType, String summary, int expectedStatus = 201) {
		def response = WS.sendRequestAndVerify(findTestObject("Object Repository/Post a new ticket",
				["project": project, "issueType": issueType, "summary": summary, "expectedStatus": expectedStatus]))
		def jsonResponse = jsonSlurper.parseText(response.getResponseText())
		return jsonResponse.key
	}


	/**
	 * Update Summary field of a ticket
	 * @param ticketKey: ticket id, e.x: 'KT-21'
	 * @param newSummaryValue: new value to replace existing summary value
	 * @return
	 */
	@Keyword
	def updateExistingTicket(String ticketKey, String newSummaryValue) {
		WebUI.setText(findTestObject('WebUI/Ticket Details/txtSearch'), ticketKey)
		WebUI.click(findTestObject('WebUI/Ticket Details/lblTicketKeySearchResult', ['ticketKey': ticketKey]))
		WebUI.click(findTestObject('WebUI/Ticket Details/icoEdit'))
		WebUI.setText(findTestObject('WebUI/Ticket Details/txtSummary'), newSummaryValue)
		WebUI.click(findTestObject('WebUI/Ticket Details/icoSave'))
		WebUI.waitForElementPresent(findTestObject('Object Repository/WebUI/Ticket Details/dynamicSummary',['summary': newSummaryValue]), GlobalVariable.presentTimeout)
		String newVal = WebUI.getText(findTestObject('Object Repository/WebUI/Ticket Details/lblSummary'))
		WebUI.verifyEqual(newVal, newSummaryValue)
		WebUI.closeBrowser()
	}
}
