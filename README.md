# takeaway-test
Game Of three App
------------------------------------------------------------
	2- This is for server  
		1-  pull image from docker hub
			docker pull igibally/gameofthree_server:gameofthreeserver-lastupdate
		2- run docker server 
			docker run -p 8085:8085 <imageId> --net=gameofthree --name=gameofthreeserver
-------------------------------------------------------------------------------------------------------------------------------
	2- This is for client  
		1- build docker client image
			docker pull igibally/gameofthree_client:gameofthreeclient-latest-update.
		2- run docker client 
			docker run -i -t testtakeaway/gameofthreeclient --net=bridge

<h4 color="red">======= Game Started : =======</h4>
<p style="font-size:12px;color:black">
<br />
Enter your Name :
<br>
Islam
<br />
Enter your Type : {Manual:(M) | Automatic:(A)} choose A for Automatic M for Manual
<br>
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
</p>
