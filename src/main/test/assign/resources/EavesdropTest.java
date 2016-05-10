package assign.resources;

import static org.junit.Assert.*;

import java.io.StringReader;
import java.util.ArrayList;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.xml.xpath.XPath;
import javax.xml.xpath.XPathConstants;
import javax.xml.xpath.XPathFactory;

import org.junit.Test;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.InputSource;

public class EavesdropTest {

	@Test
	public void testAllProjects() throws Exception
	{
		Client client = ClientBuilder.newClient();
		String data = client.target("http://localhost:8080/assignment3/myeavesdrop/projects").request().get(String.class);

		XPath xpath = XPathFactory.newInstance().newXPath();
		String expression = "/projects/project";
		InputSource inputSource = new InputSource(new StringReader(data));
		NodeList nodes = (NodeList) xpath.evaluate(expression, inputSource, XPathConstants.NODESET);
		ArrayList<String> list = new ArrayList<String>();
		ArrayList<String> test = new ArrayList<String>();

		for(int i=0; i<nodes.getLength(); i++) {
			Node n = nodes.item(i);
			list.add(n.getTextContent());
			//				System.out.println("Node value:" + n.getTextContent());
		}

		test.add("cloudkitty/");
		test.add("defore/");
		test.add("fuel/");
		test.add("kolla/");
		test.add("networkings/");
		test.add("olla/");
		test.add("tricircle/");
		test.add("quantum/");
		test.add("vitrage/");
		test.add("xenapi/");

		assertTrue(list.containsAll(test));
	}
	
	@Test
	public void testThirdPartCi() throws Exception
	{
		Client client = ClientBuilder.newClient();
		String data = client.target("http://localhost:8080/assignment3/myeavesdrop/projects/3rd_party_ci/meetings").request().get(String.class);

		XPath xpath = XPathFactory.newInstance().newXPath();
		String expression = "/meetings/year";
		InputSource inputSource = new InputSource(new StringReader(data));
		NodeList nodes = (NodeList) xpath.evaluate(expression, inputSource, XPathConstants.NODESET);
		ArrayList<String> list = new ArrayList<String>();
		ArrayList<String> test = new ArrayList<String>();

		for(int i=0; i<nodes.getLength(); i++) {
			Node n = nodes.item(i);
			list.add(n.getTextContent());
			//				System.out.println("Node value:" + n.getTextContent());
		}

		test.add("2014/");
		

		assertTrue(list.containsAll(test));
	}
	
	@Test
	public void testError() throws Exception
	{
		Client client = ClientBuilder.newClient();
		String data = client.target("http://localhost:8080/assignment3/myeavesdrop/projects/non-existent-project/meetings").request().get(String.class);

		XPath xpath = XPathFactory.newInstance().newXPath();
		String expression = "/output/error";
		InputSource inputSource = new InputSource(new StringReader(data));
		NodeList nodes = (NodeList) xpath.evaluate(expression, inputSource, XPathConstants.NODESET);
		ArrayList<String> list = new ArrayList<String>();
		ArrayList<String> test = new ArrayList<String>();

		for(int i=0; i<nodes.getLength(); i++) {
			Node n = nodes.item(i);
			list.add(n.getTextContent());
			//				System.out.println("Node value:" + n.getTextContent());
		}

		test.add("Project non-existent-project does not exist");
		assertTrue(list.containsAll(test));
	}
	
	@Test
	public void testSolumTeamMeeting() throws Exception
	{
		Client client = ClientBuilder.newClient();
		String data = client.target("http://localhost:8080/assignment3/myeavesdrop/projects/solum_team_meeting/meetings").request().get(String.class);

		XPath xpath = XPathFactory.newInstance().newXPath();
		String expression = "/meetings/year";
		InputSource inputSource = new InputSource(new StringReader(data));
		NodeList nodes = (NodeList) xpath.evaluate(expression, inputSource, XPathConstants.NODESET);
		ArrayList<String> list = new ArrayList<String>();
		ArrayList<String> test = new ArrayList<String>();

		for(int i=0; i<nodes.getLength(); i++) {
			Node n = nodes.item(i);
			list.add(n.getTextContent());
			//				System.out.println("Node value:" + n.getTextContent());
		}

		test.add("2013/");
		test.add("2014/");
		test.add("2015/");
		test.add("2016/");

		assertTrue(list.containsAll(test));
	}
}