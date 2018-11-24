package com.prateek.jira.jira_issue_tracker;


import java.time.LocalDate;
import java.util.HashMap;

import org.json.JSONException;
import org.json.JSONObject;

/**
 * @author prate
 *
 */
/**
 * @author prate
 *
 */
public class App 
{
	public static void main(String[] args){
		createIssue();
	}
		
	public static void createIssue()
    {
        String endpointURL = "http://localhost:8081/rest/api/2/issue/";
        JiraBean createBean = new JiraBean();
        createBean.setProjectName("TESTSWAZ");
        createBean.setSummary("Test Summary");
        createBean.setDescription("Test Description");
        createBean.setIssueTypeName("Bug");
        System.out.println(LocalDate.now().plusDays(5).toString());
        createBean.setDueDate(LocalDate.now().plusDays(5).toString());
        try {
        	String id = createIssue(endpointURL,createBean);
        	viewIssue(endpointURL+id);
        	//deleteIssue(endpointURL+"TESTSWAZ-34");
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        
    }
	
	
	/**
	 * This method creates an Issue
	 * @param endpoint
	 * @return
	 * @throws JSONException
	 */
	public static String createIssue(String endpoint,JiraBean createBean) throws JSONException{
		String id="";
    	HashMap<String,String> inputmap = new HashMap<String,String>();
    	inputmap.put("uristr", endpoint);
    	inputmap.put("payload", getCreateIssueJSON(createBean));
    	try {
    		id = parseGetIssueId(RestAPIInvokeService.InvokeRestPostCall(inputmap));
		} catch (Exception e) {
			e.printStackTrace();
		}
    	return id;
    }
	
	/**
	 * This method is used to view an Issue
	 * @param endpoint
	 * @throws JSONException
	 */
	public static void viewIssue(String endpoint) throws JSONException{
    	HashMap<String,String> inputmap = new HashMap<String,String>();
    	inputmap.put("uristr", endpoint);
    	try {
    		System.out.println(RestAPIInvokeService.InvokeRestGetCall(inputmap));
		} catch (Exception e) {
			e.printStackTrace();
		}
    }
	
	/**
	 * This method is used to delete an Issue
	 * @param endpoint
	 * @throws JSONException
	 */
	public static void deleteIssue(String endpoint) throws JSONException{
    	HashMap<String,String> inputmap = new HashMap<String,String>();
    	inputmap.put("uristr", endpoint);
    	try {
    		System.out.println(RestAPIInvokeService.InvokeRestDeleteCall(inputmap));
		} catch (Exception e) {
			e.printStackTrace();
		}
    }
	
	/**
	 * This method is used to fetch the Key value after an Issue is created
	 * @param issueIdJSON
	 * @return
	 */
	public static String parseGetIssueId(String issueIdJSON){
		String keyId="";
		try {
			JSONObject issueJSON = new JSONObject(issueIdJSON.toString());
			keyId = (String) issueJSON.get("key");
			System.out.println("Issue id created: "+keyId);
		} catch (Exception e) {
			System.out.println(e);
		}
		return keyId;
    }
	
	private static String getCreateIssueJSON(JiraBean createBean){
	  JSONObject JiraIssue = new JSONObject();
	  try{
		JSONObject Fields = new JSONObject();
		JSONObject Project = new JSONObject();
		JSONObject Issuetype = new JSONObject();
		Project.put("key",createBean.getProjectName());
		Issuetype.put("name",createBean.getIssueTypeName());
		Fields.put("project",Project);
		Fields.put("summary",createBean.getSummary());
		Fields.put("description",createBean.getDescription());
		Fields.put("duedate",createBean.getDueDate());
		Fields.put("issuetype",Issuetype);
		JiraIssue.put("fields",Fields);
		} catch (JSONException e) {
			e.printStackTrace();
		}
        return JiraIssue.toString();
	}
}

