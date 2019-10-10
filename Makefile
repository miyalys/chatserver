# 1st letter: c = compile, r = run, t = test
# 2nd letter: c = client, l = libs, s = server, le = lexer

CC = javac12

crs: all rs

crc: all rc

all: cl cc cs

rc:
	$(CC) chat/client/ChatClient.java

rs:
	$(CC) chat/server/ChatServer.java

cl:
	$(CC) chat/libs/protocol/*.java
	$(CC) chat/libs/*.java

cc:
	$(CC) chat/client/*.java

cs:
	$(CC) chat/server/*.java

tle:
	$(CC) chat/libs/protocol/LexerTester.java
	cat tokens.txt
