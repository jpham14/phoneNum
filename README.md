# phoneNum
Parses phone numbers in the query string and outputs them in JSON format by using Google's libphonenumber.

# Functions
Currently it is only able to read the query string, but additional features will be implemented over time to meet 0.1 requirements.

# Required for Deployment
To run this Java servlet, you will need in addition to this project:
* [Eclipse IDE of Java EE Developers Oxygen 2](http://www.eclipse.org/downloads/download.php?file=/technology/epp/downloads/release/oxygen/2/eclipse-jee-oxygen-2-win32-x86_64.zip)
* [Apache Tomcat 7 or later](https://tomcat.apache.org/whichversion.html)

1. Install Eclipse IDE
1. Install Apache Tomcat
1. Run Eclipse and create a workspace
1. A. Clone the repository (You can choose to save it inside the Eclipse workspace if you like), and in Eclipse go to File > Open Projects from File System. Beside Import Source, click Directory, then find and select the respository folder. Then click Finish.
	
	Or
	
	B. Download the WAR file. Then in Eclipse, go to File > Import, expand the Web folder and select WAR file you downloaded.
1. Create a server in Eclipse. In Eclipse, click on the Servers tab at the bottom of the screen. In the empty space, right click and select New > Server. Select Apache v7.0 Server or later. Then click Next.
1. Add phoneNum. 
1. Back in the Servers tab, right click on the server and select Start.
1. Now you can open your browser and go to http://localhost:8080/phoneNum/parse? to enter phone numbers.