package assign.domain;

import java.util.List;

import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "meetings")
@XmlAccessorType
public class Meetings {

    private List<String> year = null;
 
    public List<String> getYear() {
        return year;
    }
 
    public void setYear(List<String> year) {
        this.year = year;
    }	
}
