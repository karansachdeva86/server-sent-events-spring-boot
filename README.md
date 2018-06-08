# Server Sent Events with Spring Boot

#####Objective

The objectve is to make a system which can keep an interative user experience while doing heavy processing on the backend. 

#####Description

End user uploads a file and then has to wait till the backend processing is complete. This posts an event on "INCOMING" queue. The external system picks up the received event from the queue and processes the file and obtains its meta data and stores it in the database. When the backend process is completed an event is posted on the queue - PROCESSED. The listener picks up the event and updates the file status to "PROCESSED" in the database and also raises the server side event. The UI picks up the server side event and the UI redirects to a page which fetches the processed details from database and displays on the UI.

#####Technlogies Used

Active MQ

Spring Boot

MySQL

Hibernate


#####Demo

<a href="http://www.youtube.com/watch?feature=player_embedded&v=Dfxt3WpHonA
" target="_blank"><img src="http://img.youtube.com/vi/Dfxt3WpHonA/0.jpg" 
alt="IMAGE ALT TEXT HERE" width="440" height="360" border="10" /></a>