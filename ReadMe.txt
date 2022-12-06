Included in the main project file are the source java files that must be stored on your local
	drive. 

Next in the project are the bat files.  The bat files must be stored on the same level in the 
	project directory at the package level, so move the bat files into the approriate package.

In the project file is the SQL file.  The sql file must be run in your MySQL server.  Once
	your MySQL server is running, and the sql file is stored locally, you run the sql file 
	with the command:
		source filepath/file.sql
	This will create the necessary tables needed to run the game locally.

To run the program, you must first start the MySQL database using the XAMPP application. Once 
	the database is running, you run the lab5outServer_1.bat file and once the GUI displays
	you set the server to listen.  Next you run the lab5outClient_1.bat file twice, once
	for each player needed to run the game minimum.  

If its your first time playing the game, you would press the create game button, and go 
	through the steps to create an account with a valid username and password.

Next you login with your username and password.

There will next be a rules button if you need to see the rules for the game or you can start
	the game.  If you start the game, you'll be navigated to the Game Lobby.  Once, at 
	least two players have joined the game, the game will start. 