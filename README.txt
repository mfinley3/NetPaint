Project: NetPaint
For computer science 335

Authors:
Mike Finley & Katelyn Hudspeth

Description:
A simple painting tool that allows groups of people to all work in the same document at any given time. (Much like a Google Doc) 

Overview of Project:
NetPaint was created to first learn how to create a GUI that could be drawn on by a user. After creation of a GUI, networking capabilities were added. Katelyn and I coded a server and client in java so multiple users could "paint" on the same screen at the same time anywhere they wanted as long as the server was running.

**Instructions**
To start NetPaint first download the repository and import it to your IDE of choice. (Eclipse is recommended) Once imported navigate to NetPaint using your IDE and in the src folder go to the networking package and open server.java. Using your IDE run server.java, your console will inform you that a server has been started on port 9001. Now run client.java in the same fashion as server.java. You will be prompted to enter a host and a port. The host will be "localHost" and the port will be "9001" as the server you are connecting to is locally hosted on your computer on port 9001. At this point you can enter a username and begin. You can then start up another client in the same fashion, with a different user name, to see the full functions of NetPaint across a server. 

**Notes**
NetPaints server code currently is hard coded to start the server on your current computer as a local host on port 9001. So use across multiple computers can only be achieved with modified code and a personal server. 

