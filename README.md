# takeaway-test
Game Of three App
-----------------------------
1-build the docker image for server
	1- 
	
	This is for serever
	1- build the docker server image
		docker build --build-arg JAR_FILE=gameofthreeserver-0.1.jar -t testtakeaway/gameofthreeserver .
	2- run docker continer for A server
		docker run -p 8085:8085 testtakeaway/gameofthreeserver --net=bridge --name=gameofthreeserver
-------------------------------------------------------------------------------------------------------------------------------
	This is for client  
	1-  build docker client image
		docker build --build-arg JAR_FILE=gameofthreeclient-0.1.jar -t testtakeaway/gameofthreeclient .
	2- run docker client 
		docker run -i -t testtakeaway/gameofthreeclient --net=bridge
When the Game is started you will prompeted with the folowing
:  ======= Game Started : =======
Enter your Name :
Islam
Enter your Type : {Manual:(M) | Automatic:(A)} choose A for Automatic M for Manual
M
then go and repeat the "this for the client steps" to add another player
you will be prompted to enter the same details
Enter your Name :
Ali
Enter your Type : {Manual:(M) | Automatic:(A)} choose A for Automatic M for Manual
M
then you will prompeted to add the first starting number
send the first number then the other player will be received it and respond back to you

Happy Playing :)

