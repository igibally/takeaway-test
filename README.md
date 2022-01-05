
Game Of three App
------------------------------------------------------------
Description
<br>
When a player starts, it incepts a random (whole) number and sends it to the second
player as an approach of starting the game. The receiving player can now always choose 
between adding one of {-1, 0, 1} to get to a number that is divisible by 3. Divide it by three. The 
resulting whole number is then sent back to the original sender.
The same rules are applied until one player reaches the number 1(after the division).
-------------------------------------------------------------------------------------------------------------------------------
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

<h4 style="color:red;font-size:14px">======= Game Started : =======</h4>
<p style="font-size:12px;">
<span style="color:blue;">First player:</span>
<br />
 Enter your Name :
<br>
<span style="color:blue">Islam</span>
<br />
2- Enter your Type : {Manual:(M) | Automatic:(A)} choose A for Automatic M for Manual
<br>
<span style="color:blue">M</span>
<hr />
<span style="color:blue;">Second player:</span>
<br/>
open another terminal  repeat the "this for the client steps" to add another player
you will be prompted to enter the same details
<br/>
Then you will prompeted to add the first starting number
Send the first number then the other player will be received it and respond back to you
<br/>
Thank you
</p>
