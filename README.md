# takeaway-test
Game Of three App
-----------------------------
First create the network between them
docker network create gameofthree
--------------------------------------
1-build the docker image for server
	1- This is for serever run:
		docker pull igibally/gameofthree_server:gameofthreeserver-lastupdate
		2- run docker continer for A server
			docker run -p 8085:8085 <imageId> --net=gameofthree --name=gameofthreeserver
-------------------------------------------------------------------------------------------------------------------------------
	2- This is for client  
		docker pull igibally/gameofthree_client:gameofthreeclient-latest-update
		2- run docker client 
			docker run -i -t <imageId> --net=gameofthree

2- :  ======= Game Started : =======
<br />
Enter your Name :
Islam
<br />
Enter your Type : {Manual:(M) | Automatic:(A)} choose A for Automatic M for Manual
M
<br />
3- Then go and repeat the "this for the client steps" to add another player
you will be prompted to enter the same details
<br />
Enter your Name :
Ali
<br/>
Enter your Type : {Manual:(M) | Automatic:(A)} choose A for Automatic M for Manual
M
<br />
Then you will prompeted to add the first starting number
Send the first number then the other player will be received it and respond back to you

<br/>
Thank you
