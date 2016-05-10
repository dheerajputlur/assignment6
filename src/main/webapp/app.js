//from devdattakulkarni github javascript example

var xmlhttp;
function loadXMLDoc(url,cfunc)
{
	if (window.XMLHttpRequest)
	{// code for IE7+, Firefox, Chrome, Opera, Safari
		xmlhttp=new XMLHttpRequest();
	}
	else
	{// code for IE6, IE5
		xmlhttp=new ActiveXObject("Microsoft.XMLHTTP");
	}
	xmlhttp.onreadystatechange=cfunc;
	xmlhttp.open("GET",url,true);
	xmlhttp.send();
}

function addInformation()
{
	loadXMLDoc("http://52.32.117.121:8080/assignment3-0.0.1/myeavesdrop/projects/getResults",function()
			{
		if (xmlhttp.readyState==4 && xmlhttp.status==200)
		{
			console.log("in xml"); 
			var xml = xmlhttp.responseXML, xmlDoc = $.parseXML(xml), $xml = $(xmlDoc); 
			$(function() {
				$(xml).find('results').each(function(){
						var $results = $(this); 
						console.log($results.text()); 
						var year = $results.find('year').text(); 
						var meetingCount = $results.find('count').text(); 
						addRow(year, meetingCount); 								
				})
			})
		}
			});
}

function addRow(content, morecontent){
	if(!document.getElementsByTagName) return; 
	tabBody = document.getElementsByTagName("tbody").item(0); 
	row = document.createElement("tr"); 
	yearCell = document.createElement("td"); 
	countCell = document.createElement("td"); 
	yearNode = document.createTextNode(content); 
	countNode = document.createTextNode(morecontent); 
	yearCell.appendChild(yearNode); 
	countCell.appendChild(countNode); 
	row.appendChild(yearCell); 
	row.appendChild(countCell); 
	tabBody.appendChild(row); 
}