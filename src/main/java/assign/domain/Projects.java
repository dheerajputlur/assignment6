package assign.domain;

import java.util.List;

import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlAccessType;

@XmlRootElement(name = "projects")
@XmlAccessorType(XmlAccessType.FIELD)
public class Projects {
	    
    private List<String> project = null;
    
    //list of strings instead of list of project
    public List<String> getProjects() {
    	return project;
    }
    
    public void setProjects(List<String> project) {
    	this.project = project;
    }   
}