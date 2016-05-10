package assign.services;

import java.util.ArrayList;
import java.util.List;
import java.util.ListIterator;

import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import assign.domain.Meetings;
import assign.domain.Projects;
import assign.domain.Results;
import assign.domain.ResultsFinal;


public class EavesdropService {

	JSoupHandler jsoupHandler;

	public EavesdropService() {
		if (jsoupHandler == null) {
			jsoupHandler = new JSoupHandler();
		}
	}
	public List<String> getSolumTeamMeetings(String year) 
	{
		try {		    
			String source = "http://eavesdrop.openstack.org/meetings/solum_team_meeting/" + year;	
			Elements links = jsoupHandler.getElements(source);
			
			if (links != null) {
				ListIterator<Element> iter = links.listIterator();

//				final ResultsFinal resultsFinal = new ResultsFinal(); 
//				final Results results = new Results(); 
//				final Meetings meetings = new Meetings();
//				List<String> meetingList = new ArrayList<String>();
				//final list
				List<String> resultsList = new ArrayList<String>(); 
				int count = 0; 
				while(iter.hasNext()) {
					Element e = (Element) iter.next();
					count++; 
					if(count > 5)	
					{
						String s = e.html();
						if ( s != null) {
							
							resultsList.add(s);  
							e = (Element) iter.next();
							e = (Element) iter.next();
							e = (Element) iter.next();
						}
					}		    			
				}	  
			
				return resultsList; 
			}
			else {
				System.out.println("not working");
				//		    		return null; 
			}
		} catch (Exception exp) {
			exp.printStackTrace();
		}
		return null;	
	}

	public Projects allProjects() 
	{
		try {		    
			String source = "http://eavesdrop.openstack.org/meetings";	
			Elements links = jsoupHandler.getElements(source);

			if (links != null) {
				ListIterator<Element> iter = links.listIterator();

				final Projects projects = new Projects();
				List<String> projectList = new ArrayList<String>();
				int count = 0; 
				while(iter.hasNext()) {
					Element e = (Element) iter.next();
					count++; 
					if(count > 5)
					{
						String s = e.html();
						if ( s != null) {		
							projectList.add(s);    				
						}
					}		    			
				}	  
				projects.setProjects(projectList); 
				return projects; 
			}
			else {
				System.out.println("not working");
				//		    		return null; 
			}
		} catch (Exception exp) {
			exp.printStackTrace();
		}
		return null;	
	}
}