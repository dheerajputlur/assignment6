package assign.domain;

import java.util.List;

import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlAccessType;


@XmlRootElement(name = "resultsFinal")
@XmlAccessorType(XmlAccessType.FIELD)
public class ResultsFinal {
	
	private List<Results> results = null; 
	
	public List<Results> getResults()
	{
		return results; 
	}
	
	public void setResults(List<Results> results)
	{
		this.results = results; 
	}	
}