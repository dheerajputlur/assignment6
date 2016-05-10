package assign.resources;

import java.io.IOException; 
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.List;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.StreamingOutput;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;

import assign.domain.Meetings;
import assign.domain.ErrorProject;
import assign.domain.Projects;
import assign.domain.Results;
import assign.domain.ResultsFinal;
import assign.services.EavesdropService;

@Path("/projects")
public class EavesdropResource {

	EavesdropService eavesdropService;

	public EavesdropResource() {
		this.eavesdropService = new EavesdropService();
	}

	@GET
	@Path("/")
	@Produces("application/xml")
	public StreamingOutput generalProjects() {
		final Projects projects = eavesdropService.allProjects(); 
		return new StreamingOutput() {
			public void write(OutputStream outputStream) throws IOException, WebApplicationException {
				outputProjects(outputStream, projects);
			}
		};	
	}

	@GET
	@Path("/getResults")
	@Produces("application/xml")
	public StreamingOutput getAllSolumTeamMeetings() throws Exception {
		//final ResultsFinal results = eavesdropService.getSolumTeamMeetings(year); 
		final ResultsFinal rf = new ResultsFinal(); 
		List<Results> results = new ArrayList<Results>(); 		
		
		List<String> yearsToSend = new ArrayList<String>(); 
		yearsToSend.add("2013"); 
		yearsToSend.add("2014"); 
		yearsToSend.add("2015"); 
		yearsToSend.add("2016"); 

		for(String y : yearsToSend)
		{
			List<String> meetingList = eavesdropService.getSolumTeamMeetings(y); 			
			//List<String> newMeetingList = new ArrayList<String>(); 
			int count = meetingList.size(); 
			Results r = new Results(); 
			r.setYear(y);
			r.setCount(count);
			results.add(r); 
		}

		rf.setResults(results);

		return new StreamingOutput()
		{
			public void write(OutputStream outputStream) throws IOException, WebApplicationException
			{
				outputMeetings(outputStream, rf); 
			}
		}; 
	}
	
	//	@GET
	//	@Path("/{specificProject}/{year}/meetings")
	//	@Produces("application/xml")
	//	public StreamingOutput getAllMeetings(@PathParam("specificProject") final String specificProject, @PathParam("year") final String year) throws Exception {
	//		final Meetings meetings = eavesdropService.oneProject(specificProject, year); 
	//		if(meetings != null)
	//		{
	//			return meetings.getYear().size(); 
	//		}
	//		return 0; 
	//	}


	protected void outputError(OutputStream os, ErrorProject ep) throws IOException {
		try { 
			JAXBContext jaxbContext = JAXBContext.newInstance(ErrorProject.class);
			Marshaller jaxbMarshaller = jaxbContext.createMarshaller();

			jaxbMarshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
			jaxbMarshaller.marshal(ep, os);
		} catch (JAXBException jaxb) {
			jaxb.printStackTrace();
			throw new WebApplicationException();
		}
	}

	protected void outputMeetings(OutputStream os, ResultsFinal results) throws IOException {

		try { 
			JAXBContext jaxbContext = JAXBContext.newInstance(ResultsFinal.class);
			Marshaller jaxbMarshaller = jaxbContext.createMarshaller();

			jaxbMarshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
			jaxbMarshaller.marshal(results, os);
		} catch (JAXBException jaxb) {
			jaxb.printStackTrace();
			throw new WebApplicationException();
		}
	}

	protected void outputProjects(OutputStream os, Projects projects) throws IOException {
		try { 
			JAXBContext jaxbContext = JAXBContext.newInstance(Projects.class);
			Marshaller jaxbMarshaller = jaxbContext.createMarshaller();

			jaxbMarshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
			jaxbMarshaller.marshal(projects, os);
		} catch (JAXBException jaxb) {
			jaxb.printStackTrace();
			throw new WebApplicationException();
		}
	}	

}